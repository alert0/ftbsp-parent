package com.dstz.bpm.core.manager;

import com.dstz.base.manager.Manager;
import com.dstz.bpm.api.model.inst.BpmExecutionStack;
import com.dstz.bpm.api.model.task.IBpmTask;
import com.dstz.bpm.core.model.BpmTaskStack;
import java.util.List;

public interface BpmTaskStackManager extends Manager<String, BpmTaskStack> {
	BpmTaskStack getByTaskId(String paramString);

	BpmTaskStack createStackByTask(IBpmTask paramIBpmTask, BpmExecutionStack paramBpmExecutionStack);

	void removeByInstanceId(String paramString);

	List<BpmTaskStack> getByInstanceId(String paramString);
}