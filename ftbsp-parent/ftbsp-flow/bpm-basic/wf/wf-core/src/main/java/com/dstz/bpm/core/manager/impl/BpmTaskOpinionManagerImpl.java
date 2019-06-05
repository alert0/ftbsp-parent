package com.dstz.bpm.core.manager.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.dstz.base.manager.impl.BaseManager;
import com.dstz.bpm.api.constant.OpinionStatus;
import com.dstz.bpm.api.engine.action.cmd.InstanceActionCmd;
import com.dstz.bpm.api.engine.action.cmd.TaskActionCmd;
import com.dstz.bpm.api.model.inst.IBpmInstance;
import com.dstz.bpm.api.model.task.IBpmTask;
import com.dstz.bpm.core.dao.BpmTaskOpinionDao;
import com.dstz.bpm.core.manager.BpmTaskOpinionManager;
import com.dstz.bpm.core.model.BpmTaskOpinion;
import com.dstz.bpm.engine.action.cmd.DefaultInstanceActionCmd;
import com.dstz.org.api.model.IUser;
import com.dstz.sys.api.model.SysIdentity;
import com.dstz.sys.util.ContextUtil;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.springframework.stereotype.Service;

@Service("bpmTaskOpinionManager")
public class BpmTaskOpinionManagerImpl extends BaseManager<String, BpmTaskOpinion> implements BpmTaskOpinionManager {
	@Resource
	BpmTaskOpinionDao r;

	public BpmTaskOpinion getByTaskId(String taskId) {
		return this.r.getByTaskId(taskId);
	}

	public void createOpinionByInstance(InstanceActionCmd actionModel, boolean isCreateEvent) {
		IBpmInstance bpmInstance = actionModel.getBpmInstance();
		String formIdentity = actionModel.getFormId();
		DefaultInstanceActionCmd actionCmd = (DefaultInstanceActionCmd) actionModel;
		ExecutionEntity entity = actionCmd.getExecutionEntity();
		entity.getActivityId();

		BpmTaskOpinion bpmTaskOpinion = new BpmTaskOpinion();
		bpmTaskOpinion.setCreateTime(new Date());
		bpmTaskOpinion.setApproveTime(new Date());
		bpmTaskOpinion.setUpdateTime(new Date());
		bpmTaskOpinion.setDurMs(Long.valueOf(0L));

		bpmTaskOpinion.setInstId(bpmInstance.getId());
		bpmTaskOpinion.setSupInstId(bpmInstance.getParentInstId());

		bpmTaskOpinion.setOpinion(isCreateEvent ? "发起流程" : "流程结束");
		bpmTaskOpinion.setStatus(isCreateEvent ? "start" : "end");
		bpmTaskOpinion.setTaskId("0");
		bpmTaskOpinion.setTaskKey(entity.getActivityId());
		bpmTaskOpinion.setTaskName(entity.getCurrentActivityName());
		bpmTaskOpinion.setFormId(formIdentity);

		IUser user = ContextUtil.getCurrentUser();
		if (user != null) {
			bpmTaskOpinion.setApprover(user.getUserId());
			bpmTaskOpinion.setApproverName(user.getFullname());
			bpmTaskOpinion.setCreateBy(user.getUserId());
			bpmTaskOpinion.setUpdateBy(user.getUserId());
		}

		create(bpmTaskOpinion);
	}

	public void createOpinionByTask(TaskActionCmd taskActionModel) {
		List<SysIdentity> taskIdentitys = taskActionModel.getBpmIdentity(taskActionModel.getNodeId());
		createOpinion(taskActionModel.getBpmTask(), taskActionModel.getBpmInstance(), taskIdentitys,
				taskActionModel.getOpinion(), taskActionModel.getActionName(), taskActionModel.getFormId());
	}

	public void createOpinion(IBpmTask task, IBpmInstance bpmInstance, List<SysIdentity> taskIdentitys, String opinion,
			String actionName, String formId) {
		createOpinion(task, bpmInstance, taskIdentitys, opinion, actionName, formId, null);
	}

	public void createOpinion(IBpmTask task, IBpmInstance bpmInstance, List<SysIdentity> taskIdentitys, String opinion,
			String actionName, String formId, String token) {
		BpmTaskOpinion bpmTaskOpinion = new BpmTaskOpinion();
		bpmTaskOpinion.setCreateTime(new Date());
		bpmTaskOpinion.setUpdateTime(new Date());
		bpmTaskOpinion.setDurMs(Long.valueOf(0L));

		bpmTaskOpinion.setInstId(bpmInstance.getId());
		bpmTaskOpinion.setSupInstId(bpmInstance.getParentInstId());

		bpmTaskOpinion.setOpinion(opinion);
		bpmTaskOpinion.setStatus(OpinionStatus.getByActionName(actionName).getKey());
		bpmTaskOpinion.setTaskId(task.getId());
		bpmTaskOpinion.setTaskKey(task.getNodeId());
		bpmTaskOpinion.setTaskName(task.getName());
		bpmTaskOpinion.setFormId(formId);

		IUser user = ContextUtil.getCurrentUser();
		if (user != null) {
			bpmTaskOpinion.setCreateBy(user.getUserId());
			bpmTaskOpinion.setUpdateBy(user.getUserId());
		}

		StringBuilder assignInfo = new StringBuilder();
		if (CollectionUtil.isNotEmpty(taskIdentitys)) {
			for (SysIdentity identity : taskIdentitys) {
				assignInfo.append(identity.getType()).append("-").append(identity.getName()).append("-")
						.append(identity.getId()).append(",");
			}
		}
		bpmTaskOpinion.setAssignInfo(assignInfo.toString());

		bpmTaskOpinion.setToken(token);

		create(bpmTaskOpinion);
	}

	public List<BpmTaskOpinion> getByInstAndNode(String instId, String nodeId) {
		return this.r.getByInstAndNode(instId, nodeId, null);
	}

	public List<BpmTaskOpinion> getByInstAndToken(String instId, String token) {
		return this.r.getByInstAndNode(instId, null, token);
	}

	public List<BpmTaskOpinion> getByInstId(String instId) {
		return this.r.getByInstAndNode(instId, null, null);
	}

	public void removeByInstId(String instId) {
		this.r.removeByInstId(instId);
	}
}