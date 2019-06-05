package com.dstz.bpm.engine.listener;

import com.dstz.base.core.id.IdUtil;
import com.dstz.bpm.api.constant.ActionType;
import com.dstz.bpm.api.constant.EventType;
import com.dstz.bpm.api.constant.InstanceStatus;
import com.dstz.bpm.api.constant.ScriptType;
import com.dstz.bpm.api.engine.action.cmd.BaseActionCmd;
import com.dstz.bpm.api.engine.action.cmd.InstanceActionCmd;
import com.dstz.bpm.api.engine.context.BpmContext;
import com.dstz.bpm.api.model.inst.BpmExecutionStack;
import com.dstz.bpm.core.manager.BpmInstanceManager;
import com.dstz.bpm.core.manager.BpmTaskOpinionManager;
import com.dstz.bpm.core.manager.BpmTaskStackManager;
import com.dstz.bpm.core.model.BpmInstance;
import com.dstz.bpm.core.model.BpmTaskStack;
import com.dstz.bpm.engine.action.cmd.DefaultInstanceActionCmd;
import java.util.Date;
import javax.annotation.Resource;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.springframework.stereotype.Component;

@Component
public class InstanceEndEventListener extends AbstractInstanceListener {
	@Resource
	BpmTaskOpinionManager j;
	@Resource
	BpmInstanceManager f;
	@Resource
	BpmTaskStackManager k;

	public EventType getBeforeTriggerEventType() {
		return EventType.END_EVENT;
	}

	public EventType getAfterTriggerEventType() {
		return EventType.END_POST_EVENT;
	}

	public void a(InstanceActionCmd instanceActionModel) {
		this.LOG.debug("流程实例【{}】,ID【{}】开始触发终止事件", instanceActionModel.getBpmInstance().getSubject(),
				instanceActionModel.getBpmInstance().getId());
	}

	public void b(InstanceActionCmd instanceActionModel) {
		this.j.createOpinionByInstance(instanceActionModel, false);

		BpmInstance instance = (BpmInstance) instanceActionModel.getBpmInstance();
		instance.setStatus(InstanceStatus.STATUS_END.getKey());
		instance.setEndTime(new Date());
		instance.setDuration(Long.valueOf(instance.getEndTime().getTime() - instance.getCreateTime().getTime()));
		this.f.update(instance);

		d(instanceActionModel);
	}

	public void c(InstanceActionCmd instanceActionModel) {
		this.LOG.debug("流程实例【{}】,ID【{}】已经终止", instanceActionModel.getBpmInstance().getSubject(),
				instanceActionModel.getBpmInstance().getId());
	}

	protected ScriptType getScriptType() {
		return ScriptType.END;
	}

	protected InstanceActionCmd a(ExecutionEntity executionEntity) {
		BaseActionCmd actionModle = (BaseActionCmd) BpmContext.getActionModel();

		DefaultInstanceActionCmd instanceModel = new DefaultInstanceActionCmd();
		instanceModel.setBpmInstance(actionModle.getBpmInstance());
		instanceModel.setBpmDefinition(actionModle.getBpmDefinition());
		instanceModel.setBizDataMap(actionModle.getBizDataMap());
		instanceModel.setBusinessKey(actionModle.getBusinessKey());
		instanceModel.setActionName(ActionType.END.getKey());
		instanceModel.setExecutionEntity(executionEntity);
		return instanceModel;
	}

	private void d(InstanceActionCmd instanceActionModel) {
		DefaultInstanceActionCmd actionCmd = (DefaultInstanceActionCmd) instanceActionModel;
		ExecutionEntity entity = actionCmd.getExecutionEntity();

		BpmTaskStack endFlowStack = new BpmTaskStack();
		String id = IdUtil.getSuid();
		endFlowStack.setId(id);
		endFlowStack.setNodeId(entity.getActivityId());

		endFlowStack.setNodeName(entity.getCurrentActivityName());
		endFlowStack.setTaskId("0");

		endFlowStack.setStartTime(new Date());
		endFlowStack.setEndTime(new Date());
		endFlowStack.setInstId(actionCmd.getInstanceId());
		endFlowStack.setNodeType("endNoneEvent");
		endFlowStack.setActionName("end");

		BpmExecutionStack parentStack = actionCmd.getExecutionStack();
		if (parentStack != null) {
			endFlowStack.setParentId(parentStack.getId());
		}

		this.k.create(endFlowStack);
	}
}