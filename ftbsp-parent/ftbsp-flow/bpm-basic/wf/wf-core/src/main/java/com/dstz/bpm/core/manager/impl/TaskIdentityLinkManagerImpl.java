package com.dstz.bpm.core.manager.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.dstz.base.api.exception.BusinessException;
import com.dstz.base.core.id.IdUtil;
import com.dstz.base.core.util.StringUtil;
import com.dstz.base.manager.impl.BaseManager;
import com.dstz.bpm.api.exception.BpmStatusCode;
import com.dstz.bpm.api.model.task.IBpmTask;
import com.dstz.bpm.core.dao.TaskIdentityLinkDao;
import com.dstz.bpm.core.manager.TaskIdentityLinkManager;
import com.dstz.bpm.core.model.TaskIdentityLink;
import com.dstz.org.api.model.IGroup;
import com.dstz.org.api.service.GroupService;
import com.dstz.sys.api.model.SysIdentity;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

@Service("taskIdentityLinkManager")
public class TaskIdentityLinkManagerImpl extends BaseManager<String, TaskIdentityLink>
		implements TaskIdentityLinkManager {
	@Resource
	TaskIdentityLinkDao t;
	@Resource
	GroupService u;

	public void removeByTaskId(String taskId) {
		if (this.t.queryTaskIdentityCount(taskId) == 0)
			return;
		this.t.removeByTaskId(taskId);
	}

	public void removeByInstId(String instId) {
		if (this.t.queryInstanceIdentityCount(instId) == 0)
			return;
		this.t.removeByInstId(instId);
	}

	public void bulkCreate(List<TaskIdentityLink> list) {
		this.t.bulkCreate(list);
	}

	public Boolean checkUserOperatorPermission(String userId, String instanceId, String taskId) {
		if (StringUtil.isEmpty(instanceId) && StringUtil.isEmpty(taskId)) {
			throw new BusinessException("检查权限必须提供流程或者任务id", BpmStatusCode.PARAM_ILLEGAL);
		}

		Set<String> rights = getUserRights(userId);

		return Boolean.valueOf((this.t.checkUserOperatorPermission(rights, taskId, instanceId) > 0));
	}

	public Set<String> getUserRights(String userId) {
		List<? extends IGroup> list = this.u.getGroupsByUserId(userId);

		Set<String> rights = new HashSet<String>();
		rights.add(String.format("%s-%s", new Object[] { userId, "user" }));

		if (CollectionUtil.isEmpty(list))
			return rights;

		for (IGroup group : list) {
			rights.add(String.format("%s-%s", new Object[] { group.getGroupId(), group.getGroupType() }));
		}

		return rights;
	}

	public void createIdentityLink(IBpmTask bpmTask, List<SysIdentity> identitys) {
		List<TaskIdentityLink> identityLinks = new ArrayList<TaskIdentityLink>();

		for (SysIdentity identity : identitys) {
			TaskIdentityLink link = new TaskIdentityLink();
			link.setId(IdUtil.getSuid());
			link.setIdentity(identity.getId());
			link.setIdentityName(identity.getName());
			link.setType(identity.getType());
			link.setPermissionCode(identity.getId() + "-" + identity.getType());
			link.setTaskId(bpmTask.getId());
			link.setInstId(bpmTask.getInstId());
			identityLinks.add(link);
		}

		bulkCreate(identityLinks);
	}

	public List<TaskIdentityLink> getByTaskId(String taskId) {
		return this.t.getByTaskId(taskId);
	}
}