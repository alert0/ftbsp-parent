package com.dstz.bpm.engine.listener;

import com.dstz.base.core.id.IdUtil;
import com.dstz.base.core.util.StringUtil;
import com.dstz.bpm.act.listener.ActEventListener;
import com.dstz.bpm.api.engine.action.cmd.BaseActionCmd;
import com.dstz.bpm.api.engine.context.BpmContext;
import com.dstz.bpm.api.model.inst.BpmExecutionStack;
import com.dstz.bpm.core.manager.BpmTaskStackManager;
import com.dstz.bpm.core.model.BpmTaskStack;
import java.util.Date;
import org.activiti.engine.delegate.event.ActivitiEvent;
import org.activiti.engine.delegate.event.impl.ActivitiSequenceFlowTakenEventImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SequenceFlowTakenListener implements ActEventListener {
	@Autowired
	private BpmTaskStackManager k;

	public void notify(ActivitiEvent event) {
		ActivitiSequenceFlowTakenEventImpl sequenceFlowEvent = (ActivitiSequenceFlowTakenEventImpl) event;

		BaseActionCmd actionCmd = (BaseActionCmd) BpmContext.getActionModel();
		BpmExecutionStack oldTaskStack = actionCmd.getExecutionStack();

		BpmTaskStack sequenceFlowStack = new BpmTaskStack();
		String id = IdUtil.getSuid();
		sequenceFlowStack.setId(id);
		sequenceFlowStack.setNodeId(sequenceFlowEvent.getId());
		if (StringUtil.isEmpty(sequenceFlowStack.getNodeId())) {
			sequenceFlowStack.setNodeId("back");
		}
		sequenceFlowStack.setNodeName(String.format("%s-》%s",
				new Object[] { sequenceFlowEvent.getSourceActivityId(), sequenceFlowEvent.getTargetActivityId() }));
		sequenceFlowStack.setTaskId(event.getExecutionId());

		sequenceFlowStack.setStartTime(new Date());
		sequenceFlowStack.setEndTime(new Date());
		sequenceFlowStack.setInstId(actionCmd.getInstanceId());
		sequenceFlowStack.setNodeType("sequenceFlow");
		sequenceFlowStack.setActionName(BpmContext.getActionModel());

		if (oldTaskStack == null) {
			sequenceFlowStack.setParentId("0");
		} else {
			sequenceFlowStack.setParentId(oldTaskStack.getId());
		}
		this.k.create(sequenceFlowStack);

		actionCmd.setExecutionStack(sequenceFlowStack);
	}
}