package com.dstz.bpm.core.manager.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.dstz.base.api.exception.BusinessException;
import com.dstz.base.api.exception.BusinessMessage;
import com.dstz.base.api.query.QueryFilter;
import com.dstz.base.api.query.QueryOP;
import com.dstz.base.core.util.StringUtil;
import com.dstz.base.manager.impl.BaseManager;
import com.dstz.bpm.api.constant.TaskStatus;
import com.dstz.bpm.api.exception.BpmStatusCode;
import com.dstz.bpm.core.dao.BpmTaskDao;
import com.dstz.bpm.core.manager.BpmInstanceManager;
import com.dstz.bpm.core.manager.BpmTaskManager;
import com.dstz.bpm.core.manager.BpmTaskOpinionManager;
import com.dstz.bpm.core.manager.TaskIdentityLinkManager;
import com.dstz.bpm.core.model.BpmTask;
import com.dstz.bpm.core.model.BpmTaskOpinion;
import com.dstz.bpm.core.model.TaskIdentityLink;
import com.dstz.bpm.engine.model.BpmIdentity;
import com.dstz.sys.api.model.SysIdentity;
import com.dstz.sys.util.ContextUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

@Service("bpmTaskManager")
public class BpmTaskManagerImpl extends BaseManager<String, BpmTask> implements BpmTaskManager {
	@Resource
	BpmTaskDao o;
	@Resource
	TaskIdentityLinkManager p;
	@Resource
	BpmInstanceManager q;
	@Resource
	BpmTaskOpinionManager j;

	public List<BpmTask> getByInstIdNodeId(String instId, String nodeId) {
		return this.o.getByInstIdNodeId(instId, nodeId);
	}

	public List<BpmTask> getByInstId(String instId) {
		return this.o.getByInstIdNodeId(instId, null);
	}

	public void removeByInstId(String instId) {
		this.o.removeByInstId(instId);
	}

	public List<BpmTask> getTodoList(String userId, QueryFilter queryFilter) {
		Set<String> userRights = this.p.getUserRights(userId);
		queryFilter.addParamsFilter("userRights", userRights);
		queryFilter.addParamsFilter("userId", ContextUtil.getCurrentUserId());

		return this.o.getTodoList(queryFilter);
	}

	public List getTodoList(QueryFilter queryFilter) {
		String userId = ContextUtil.getCurrentUserId();
		String type = (String) queryFilter.getParams().get("type");
		String title = (String) queryFilter.getParams().get("subject");

		if (StringUtil.isNotEmpty(title)) {
			queryFilter.addFilter("subject_", title, QueryOP.LIKE);
		}

		if ("done".equals(type)) {
			return this.q.getApproveHistoryList(userId, queryFilter);
		}

		Set<String> userRights = this.p.getUserRights(userId);
		queryFilter.addParamsFilter("userRights", userRights);
		queryFilter.addParamsFilter("userId", userId);

		return this.o.getTodoList(queryFilter);
	}

	public void assigneeTask(String taskId, String userId, String userName) {
		BpmTask task = (BpmTask) get(taskId);
		if (task == null) {
			throw new BusinessException("任务可能已经被处理，请刷新。", BpmStatusCode.TASK_NOT_FOUND);
		}

		task.setAssigneeId(userId);
		task.setAssigneeNames(userName);
		task.setStatus(TaskStatus.DESIGNATE.getKey());
		update(task);

		BpmTaskOpinion taskOpinion = this.j.getByTaskId(taskId);
		taskOpinion.setAssignInfo(String.format("user-%s-%s,", new Object[] { userName, userId }));
		this.j.update(taskOpinion);
	}

	public void unLockTask(String taskId) {
		BpmTask task = (BpmTask) get(taskId);

		List<TaskIdentityLink> identitys = this.p.getByTaskId(task.getId());
		if (CollectionUtil.isEmpty(identitys)) {
			throw new BusinessMessage("该任务并非多候选人状态，无效的操作！");
		}

		List<String> names = new ArrayList<String>();
		for (TaskIdentityLink identity : identitys) {
			names.add(identity.getIdentityName());
		}

		task.setAssigneeId("0");
		task.setAssigneeNames(StringUtil.join(names));
		task.setStatus(TaskStatus.NORMAL.getKey());
		update(task);

		StringBuilder assignInfo = new StringBuilder();
		if (CollectionUtil.isNotEmpty(identitys)) {
			for (TaskIdentityLink identity : identitys) {
				assignInfo.append(identity.getType()).append("-").append(identity.getIdentityName()).append("-")
						.append(identity.getIdentity()).append(",");
			}
		}

		BpmTaskOpinion taskOpinion = this.j.getByTaskId(taskId);
		taskOpinion.setAssignInfo(assignInfo.toString());
		this.j.update(taskOpinion);
	}

	public List<SysIdentity> getAssignUserById(BpmTask task) {
		if (StringUtil.isNotZeroEmpty(task.getAssigneeId()) && !"-1".equals(task.getAssigneeId())) {
			return Arrays.asList(
					new SysIdentity[] { new BpmIdentity(task.getAssigneeId(), task.getAssigneeNames(), "user") });
		}

		List<SysIdentity> identityList = new ArrayList<SysIdentity>();
		List<TaskIdentityLink> identitys = this.p.getByTaskId(task.getId());
		for (TaskIdentityLink link : identitys) {
			identityList.add(new BpmIdentity(link.getIdentity(), link.getIdentityName(), link.getType()));
		}
		return identityList;
	}
}
