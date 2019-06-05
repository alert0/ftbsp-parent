package com.dstz.bpm.engine.listener;

import com.dstz.bpm.act.listener.ActEventListener;
import com.dstz.bpm.api.constant.EventType;
import com.dstz.bpm.api.constant.ScriptType;
import com.dstz.bpm.api.engine.action.cmd.InstanceActionCmd;
import com.dstz.bpm.api.engine.plugin.cmd.ExecutionCommand;
import javax.annotation.Resource;
import org.activiti.engine.delegate.event.ActivitiEvent;
import org.activiti.engine.delegate.event.impl.ActivitiEntityEventImpl;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractInstanceListener implements ActEventListener {
	protected Logger LOG = LoggerFactory.getLogger(getClass());

	@Resource
	private ExecutionCommand aF;

	public abstract EventType getBeforeTriggerEventType();

	public abstract EventType getAfterTriggerEventType();

	public abstract void a(InstanceActionCmd paramInstanceActionCmd);

	public abstract void b(InstanceActionCmd paramInstanceActionCmd);

	public abstract void c(InstanceActionCmd paramInstanceActionCmd);

	public void notify(ActivitiEvent event) {
		ActivitiEntityEventImpl processEvent = (ActivitiEntityEventImpl) event;
		ExecutionEntity excutionEntity = (ExecutionEntity) processEvent.getEntity();
		InstanceActionCmd actionModel = a(excutionEntity);

		a(actionModel);

		if (getBeforeTriggerEventType() != null) {
			this.aF.execute(getBeforeTriggerEventType(), actionModel);
		}

		b(actionModel);

		if (getAfterTriggerEventType() != null) {
			this.aF.execute(getAfterTriggerEventType(), actionModel);
		}

		c(actionModel);
	}

	protected abstract InstanceActionCmd a(ExecutionEntity paramExecutionEntity);

	protected abstract ScriptType getScriptType();
}