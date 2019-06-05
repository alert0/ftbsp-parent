package com.dstz.bpm.core.manager;

import com.dstz.base.manager.Manager;
import com.dstz.bpm.api.model.task.IBpmTask;
import com.dstz.bpm.core.model.TaskIdentityLink;
import com.dstz.sys.api.model.SysIdentity;
import java.util.List;
import java.util.Set;

public interface TaskIdentityLinkManager extends Manager<String, TaskIdentityLink> {
	void removeByTaskId(String paramString);

	void bulkCreate(List<TaskIdentityLink> paramList);

	void removeByInstId(String paramString);

	Boolean checkUserOperatorPermission(String paramString1, String paramString2, String paramString3);

	void createIdentityLink(IBpmTask paramIBpmTask, List<SysIdentity> paramList);

	Set<String> getUserRights(String paramString);

	List<TaskIdentityLink> getByTaskId(String paramString);
}