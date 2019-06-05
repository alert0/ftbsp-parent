package com.dstz.bpm.engine.listener;

import com.dstz.bpm.act.listener.ActEventListener;
import com.dstz.bpm.api.constant.EventType;
import com.dstz.bpm.api.constant.ScriptType;
import com.dstz.bpm.api.engine.action.cmd.TaskActionCmd;
import com.dstz.bpm.api.engine.plugin.cmd.TaskCommand;
import javax.annotation.Resource;
import org.activiti.engine.delegate.event.ActivitiEntityEvent;
import org.activiti.engine.delegate.event.ActivitiEvent;
import org.activiti.engine.impl.persistence.entity.TaskEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractTaskListener<T extends TaskActionCmd> extends Object implements ActEventListener {
	protected Logger LOG = LoggerFactory.getLogger(getClass());

	@Resource
	protected TaskCommand aK;

	public abstract EventType getBeforeTriggerEventType();

	public abstract EventType getAfterTriggerEventType();

	public abstract void a(T paramT);

	public abstract void b(T paramT);

	public abstract void c(T paramT);

	public void notify(ActivitiEvent event) {
		ActivitiEntityEvent entityEvent = (ActivitiEntityEvent) event;
		TaskEntity taskEntity = (TaskEntity) entityEvent.getEntity();

		T model = (T) a(taskEntity);

		a(model);

		if (getBeforeTriggerEventType() != null) {
			this.aK.execute(getBeforeTriggerEventType(), model);
		}

		b(model);

		if (getAfterTriggerEventType() != null) {
			this.aK.execute(getAfterTriggerEventType(), model);
		}

		c(model);
	}

	protected abstract ScriptType getScriptType();

	public abstract T a(TaskEntity paramTaskEntity);
}