package com.dstz.bpm.engine.listener;

import com.dstz.base.api.exception.BusinessException;
import com.dstz.base.core.id.IdUtil;
import com.dstz.base.core.util.StringUtil;
import com.dstz.bpm.act.listener.ActEventListener;
import com.dstz.bpm.api.engine.action.cmd.BaseActionCmd;
import com.dstz.bpm.api.engine.context.BpmContext;
import com.dstz.bpm.api.exception.BpmStatusCode;
import com.dstz.bpm.api.model.inst.BpmExecutionStack;
import com.dstz.bpm.api.model.inst.IBpmInstance;
import com.dstz.bpm.core.manager.BpmDefinitionManager;
import com.dstz.bpm.core.manager.BpmInstanceManager;
import com.dstz.bpm.core.manager.BpmTaskStackManager;
import com.dstz.bpm.core.model.BpmDefinition;
import com.dstz.bpm.core.model.BpmInstance;
import com.dstz.bpm.core.model.BpmTaskStack;
import com.dstz.bpm.engine.action.cmd.DefualtTaskActionCmd;
import java.util.Date;
import javax.annotation.Resource;
import org.activiti.engine.delegate.event.ActivitiEvent;
import org.activiti.engine.delegate.event.impl.ActivitiActivityEventImpl;
import org.springframework.stereotype.Component;

@Component
public class ActivityComplatedListener implements ActEventListener {
	@Resource
	private BpmInstanceManager ba;
	@Resource
	private BpmDefinitionManager c;
	@Resource
	private BpmTaskStackManager k;

	public void notify(ActivitiEvent event) {
		if (!(event instanceof ActivitiActivityEventImpl)) {
			return;
		}
		ActivitiActivityEventImpl activitEvent = (ActivitiActivityEventImpl) event;

		if (activitEvent.getActivityType().equals("callActivity")) {
			b(activitEvent);
		}

		if (activitEvent.getActivityType().equals("startEvent")
				|| activitEvent.getActivityType().equals("exclusiveGateway")
				|| activitEvent.getActivityType().equals("parallelGateway")) {
			a(activitEvent);
		}
	}

	private void a(ActivitiActivityEventImpl event) {
		BaseActionCmd actionCmd = (BaseActionCmd) BpmContext.getActionModel();
		BpmExecutionStack taskStack = actionCmd.getExecutionStack();

		BpmTaskStack exectionStack = new BpmTaskStack();
		String id = IdUtil.getSuid();
		exectionStack.setId(id);
		exectionStack.setNodeId(event.getActivityId());
		exectionStack.setNodeName(event.getActivityName());
		exectionStack.setTaskId(event.getExecutionId());

		exectionStack.setStartTime(new Date());
		exectionStack.setEndTime(new Date());
		exectionStack.setInstId(actionCmd.getInstanceId());
		exectionStack.setNodeType(event.getActivityType());
		exectionStack.setActionName(BpmContext.getActionModel());

		if (taskStack == null) {
			exectionStack.setParentId("0");
		} else {
			exectionStack.setParentId(taskStack.getId());
		}
		this.k.create(exectionStack);

		actionCmd.setExecutionStack(exectionStack);
	}

	private void b(ActivitiActivityEventImpl activitEvent) {
		BaseActionCmd actionCmd = (BaseActionCmd) BpmContext.getActionModel();
		IBpmInstance childInstance = actionCmd.getBpmInstance();

		if (StringUtil.isZeroEmpty(childInstance.getParentInstId())) {
			throw new BusinessException("子流程提供的线程变量中，流程实例信息异常！", BpmStatusCode.ACTIONCMD_ERROR);
		}

		BpmInstance bpmInstance = (BpmInstance) this.ba.get(childInstance.getParentInstId());
		if (!bpmInstance.getActInstId().equals(activitEvent.getProcessInstanceId())) {
			throw new BusinessException("子流程提供的父流程实例，与外部子流程ACTVITI actInstanceID 不一致！", BpmStatusCode.ACTIONCMD_ERROR);
		}

		BpmTaskStack bpmTaskStack = c(activitEvent.getExecutionId());

		BpmDefinition bpmDefinition = (BpmDefinition) this.c.get(bpmInstance.getDefId());

		DefualtTaskActionCmd callActiviti = new DefualtTaskActionCmd();
		callActiviti.setBpmDefinition(bpmDefinition);
		callActiviti.setBpmInstance(bpmInstance);
		callActiviti.setExecutionStack(bpmTaskStack);

		BpmContext.setActionModel(callActiviti);
	}

	private BpmTaskStack c(String executionId) {
		BpmTaskStack bpmTaskStack = this.k.getByTaskId(executionId);

		bpmTaskStack.setEndTime(new Date());
		bpmTaskStack.setActionName(BpmContext.getActionModel());
		this.k.update(bpmTaskStack);

		return bpmTaskStack;
	}
}