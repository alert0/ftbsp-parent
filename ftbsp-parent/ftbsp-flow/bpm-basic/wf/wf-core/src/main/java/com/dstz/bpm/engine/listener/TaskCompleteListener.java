package com.dstz.bpm.engine.listener;

import cn.hutool.core.collection.CollectionUtil;
import com.dstz.bpm.api.constant.EventType;
import com.dstz.bpm.api.constant.InstanceStatus;
import com.dstz.bpm.api.constant.OpinionStatus;
import com.dstz.bpm.api.constant.ScriptType;
import com.dstz.bpm.api.engine.action.cmd.TaskActionCmd;
import com.dstz.bpm.api.engine.context.BpmContext;
import com.dstz.bpm.core.manager.BpmInstanceManager;
import com.dstz.bpm.core.manager.BpmTaskManager;
import com.dstz.bpm.core.manager.BpmTaskOpinionManager;
import com.dstz.bpm.core.manager.BpmTaskStackManager;
import com.dstz.bpm.core.manager.TaskIdentityLinkManager;
import com.dstz.bpm.core.model.BpmInstance;
import com.dstz.bpm.core.model.BpmTaskOpinion;
import com.dstz.bpm.core.model.BpmTaskStack;
import com.dstz.bpm.engine.action.cmd.DefualtTaskActionCmd;
import com.dstz.org.api.model.IUser;
import com.dstz.sys.util.ContextUtil;
import java.util.Date;
import java.util.Map;
import javax.annotation.Resource;
import org.activiti.engine.impl.persistence.entity.TaskEntity;
import org.springframework.stereotype.Component;

@Component
public abstract class TaskCompleteListener extends AbstractTaskListener<DefualtTaskActionCmd> {
	private static final long serialVersionUID = 6844821899585103714L;
	@Resource
	BpmInstanceManager f;
	@Resource
	BpmTaskManager h;
	@Resource
	TaskIdentityLinkManager p;
	@Resource
	BpmTaskOpinionManager j;
	@Resource
	BpmTaskStackManager k;

	public EventType getBeforeTriggerEventType() {
		return EventType.TASK_COMPLETE_EVENT;
	}

	public EventType getAfterTriggerEventType() {
		return EventType.TASK_POST_COMPLETE_EVENT;
	}

	public void j(DefualtTaskActionCmd taskActionModel) {
		this.LOG.debug("任务【{}】执行完成事件 - TaskID: {}", taskActionModel.getBpmTask().getName(),
				taskActionModel.getBpmTask().getId());

		Map<String, Object> actionVariables = taskActionModel.getActionVariables();
		if (CollectionUtil.isEmpty(actionVariables)) {
			return;
		}

		for (String key : actionVariables.keySet()) {
			taskActionModel.addVariable(key, actionVariables.get(key));
		}
		this.LOG.debug("设置流程变量【{}】", actionVariables.keySet().toString());
	}

	public void k(DefualtTaskActionCmd taskActionModel) {
		DefualtTaskActionCmd complateModel = taskActionModel;

		this.LOG.trace("执行任务完成动作=====》更新任务意见状态");
		b(complateModel);

		this.LOG.trace("执行任务完成动作=====》更新任务堆栈记录");
		m(complateModel);

		this.LOG.trace("执行任务完成动作=====》删除任务相关信息 - 任务、任务后续人");
		n(complateModel);
	}

	public void l(DefualtTaskActionCmd taskActionModel) {
	}

	protected ScriptType getScriptType() {
		return ScriptType.COMPLETE;
	}

	public DefualtTaskActionCmd b(TaskEntity taskEntity) {
		DefualtTaskActionCmd model = (DefualtTaskActionCmd) BpmContext.getActionModel();
		model.setDelagateTask(taskEntity);
		return model;
	}

	public void b(DefualtTaskActionCmd taskActionModel) {
		InstanceStatus flowStatus = InstanceStatus.getByActionName(taskActionModel.getActionName());

		BpmInstance instance = (BpmInstance) taskActionModel.getBpmInstance();
		if (!flowStatus.getKey().equals(instance.getStatus())) {
			instance.setStatus(flowStatus.getKey());
			this.f.update(instance);
		}

		BpmTaskOpinion bpmTaskOpinion = this.j.getByTaskId(taskActionModel.getTaskId());
		if (bpmTaskOpinion == null)
			return;
		OpinionStatus opnionStatus = OpinionStatus.getByActionName(taskActionModel.getActionName());
		bpmTaskOpinion.setStatus(opnionStatus.getKey());
		bpmTaskOpinion.setApproveTime(new Date());

		bpmTaskOpinion.setDurMs(
				Long.valueOf(bpmTaskOpinion.getApproveTime().getTime() - bpmTaskOpinion.getCreateTime().getTime()));
		bpmTaskOpinion.setOpinion(taskActionModel.getOpinion());

		IUser user = ContextUtil.getCurrentUser();
		if (user != null) {
			bpmTaskOpinion.setApprover(user.getUserId());
			bpmTaskOpinion.setApproverName(user.getFullname());
		}

		this.j.update(bpmTaskOpinion);
	}

	private void m(DefualtTaskActionCmd taskActionModel) {
		BpmTaskStack bpmTaskStack = this.k.getByTaskId(taskActionModel.getTaskId());

		bpmTaskStack.setEndTime(new Date());
		bpmTaskStack.setActionName(BpmContext.getActionModel());
		this.k.update(bpmTaskStack);

		taskActionModel.setExecutionStack(bpmTaskStack);
	}

	private void n(DefualtTaskActionCmd taskActionModel) {
		this.p.removeByTaskId(taskActionModel.getTaskId());
		this.h.remove(taskActionModel.getTaskId());
	}
}