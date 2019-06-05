package com.dstz.bpm.engine.listener;

import com.dstz.bpm.act.listener.ActEventListener;
import com.dstz.bpm.api.engine.context.BpmContext;
import com.dstz.bpm.core.manager.BpmTaskStackManager;
import com.dstz.bpm.core.model.BpmTask;
import com.dstz.bpm.engine.action.cmd.DefualtTaskActionCmd;
import javax.annotation.Resource;
import org.activiti.engine.delegate.event.ActivitiEvent;
import org.activiti.engine.delegate.event.impl.ActivitiActivityEventImpl;
import org.springframework.stereotype.Component;

@Component
public class ActivityStartedListener implements ActEventListener {
	@Resource
	BpmTaskStackManager bb;

	public void notify(ActivitiEvent event) {
		if (!(event instanceof ActivitiActivityEventImpl))
			return;
		ActivitiActivityEventImpl activitEvent = (ActivitiActivityEventImpl) event;

		if (activitEvent.getActivityType().equals("callActivity")) {
			c(activitEvent);
		}
	}

	private void c(ActivitiActivityEventImpl activitEvent) {
		DefualtTaskActionCmd action = (DefualtTaskActionCmd) BpmContext.getActionModel();
		BpmTask task = new BpmTask();
		task.setId(activitEvent.getExecutionId());
		task.setName(activitEvent.getActivityName());
		task.setNodeId(activitEvent.getActivityId());
		task.setInstId(action.getInstanceId());

		this.bb.createStackByTask(task, action.getExecutionStack());
	}
}