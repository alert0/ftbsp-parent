package com.dstz.bpm.core.manager.impl;

import com.dstz.base.core.id.IdUtil;
import com.dstz.base.manager.impl.BaseManager;
import com.dstz.bpm.api.engine.context.BpmContext;
import com.dstz.bpm.api.model.inst.BpmExecutionStack;
import com.dstz.bpm.api.model.task.IBpmTask;
import com.dstz.bpm.core.dao.BpmTaskStackDao;
import com.dstz.bpm.core.manager.BpmTaskStackManager;
import com.dstz.bpm.core.model.BpmTaskStack;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

@Service("bpmExecutionStackManager")
public class BpmTaskStackManagerImpl extends BaseManager<String, BpmTaskStack> implements BpmTaskStackManager {
	@Resource
	BpmTaskStackDao s;

	public BpmTaskStack getByTaskId(String taskId) {
		return this.s.getByTaskId(taskId);
	}

	public BpmTaskStack createStackByTask(IBpmTask task, BpmExecutionStack parentStack) {
		BpmTaskStack stack = new BpmTaskStack();
		String id = IdUtil.getSuid();
		stack.setId(id);
		stack.setNodeId(task.getNodeId());
		stack.setNodeName(task.getName());
		stack.setTaskId(task.getId());

		stack.setStartTime(new Date());
		stack.setInstId(task.getInstId());
		stack.setNodeType("userTask");
		stack.setActionName(BpmContext.getActionModel());

		if (parentStack == null) {
			stack.setParentId("0");
		} else {
			stack.setParentId(parentStack.getId());
		}
		create(stack);

		return stack;
	}

	@Deprecated
	public void removeByInstanceId(String instId) {
	}

	public List<BpmTaskStack> getByInstanceId(String instId) {
		return this.s.getByInstanceId(instId);
	}
}