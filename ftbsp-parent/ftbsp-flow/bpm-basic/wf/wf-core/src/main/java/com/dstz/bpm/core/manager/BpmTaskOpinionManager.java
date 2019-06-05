package com.dstz.bpm.core.manager;

import com.dstz.base.manager.Manager;
import com.dstz.bpm.api.engine.action.cmd.InstanceActionCmd;
import com.dstz.bpm.api.engine.action.cmd.TaskActionCmd;
import com.dstz.bpm.api.model.inst.IBpmInstance;
import com.dstz.bpm.api.model.task.IBpmTask;
import com.dstz.bpm.core.model.BpmTaskOpinion;
import com.dstz.sys.api.model.SysIdentity;
import java.util.List;

public interface BpmTaskOpinionManager extends Manager<String, BpmTaskOpinion> {
	BpmTaskOpinion getByTaskId(String paramString);

	void createOpinionByTask(TaskActionCmd paramTaskActionCmd);

	List<BpmTaskOpinion> getByInstAndNode(String paramString1, String paramString2);

	List<BpmTaskOpinion> getByInstId(String paramString);

	void createOpinion(IBpmTask paramIBpmTask, IBpmInstance paramIBpmInstance, List<SysIdentity> paramList,
			String paramString1, String paramString2, String paramString3);

	void createOpinionByInstance(InstanceActionCmd paramInstanceActionCmd, boolean paramBoolean);

	void createOpinion(IBpmTask paramIBpmTask, IBpmInstance paramIBpmInstance, List<SysIdentity> paramList,
			String paramString1, String paramString2, String paramString3, String paramString4);

	List<BpmTaskOpinion> getByInstAndToken(String paramString1, String paramString2);

	void removeByInstId(String paramString);
}