package com.dstz.bpm.core.manager;

import com.dstz.base.api.query.QueryFilter;
import com.dstz.base.manager.Manager;
import com.dstz.bpm.core.model.BpmTask;
import com.dstz.sys.api.model.SysIdentity;
import java.util.List;

public interface BpmTaskManager extends Manager<String, BpmTask> {
	List<BpmTask> getByInstIdNodeId(String paramString1, String paramString2);

	List<BpmTask> getByInstId(String paramString);

	List<BpmTask> getTodoList(String paramString, QueryFilter paramQueryFilter);

	void assigneeTask(String paramString1, String paramString2, String paramString3);

	void unLockTask(String paramString);

	void removeByInstId(String paramString);

	List getTodoList(QueryFilter paramQueryFilter);

	List<SysIdentity> getAssignUserById(BpmTask paramBpmTask);
}