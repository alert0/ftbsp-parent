package com.dstz.bpm.core.manager.impl;

import com.dstz.base.api.query.QueryFilter;
import com.dstz.base.core.id.IdUtil;
import com.dstz.base.core.util.StringUtil;
import com.dstz.base.manager.impl.BaseManager;
import com.dstz.bpm.act.service.ActInstanceService;
import com.dstz.bpm.api.constant.InstanceStatus;
import com.dstz.bpm.api.model.def.IBpmDefinition;
import com.dstz.bpm.core.dao.BpmInstanceDao;
import com.dstz.bpm.core.manager.BpmBusLinkManager;
import com.dstz.bpm.core.manager.BpmInstanceManager;
import com.dstz.bpm.core.manager.BpmTaskManager;
import com.dstz.bpm.core.manager.BpmTaskOpinionManager;
import com.dstz.bpm.core.manager.BpmTaskStackManager;
import com.dstz.bpm.core.model.BpmBusLink;
import com.dstz.bpm.core.model.BpmInstance;
import com.dstz.bpm.core.model.BpmTaskApprove;
import com.dstz.bus.api.model.IBusinessObject;
import com.dstz.bus.api.service.IBusinessDataService;
import com.dstz.bus.api.service.IBusinessObjectService;
import com.dstz.org.api.model.IUser;
import com.dstz.sys.util.ContextUtil;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("bpmInstanceManager")
public class BpmInstanceManagerImpl extends BaseManager<String, BpmInstance> implements BpmInstanceManager {
	@Resource
	BpmInstanceDao g;
	@Autowired
	BpmTaskManager h;
	@Autowired
	ActInstanceService i;
	@Autowired
	BpmTaskOpinionManager j;
	@Autowired
	BpmTaskStackManager k;
	@Autowired
	BpmBusLinkManager l;
	@Autowired
	IBusinessDataService m;
	@Autowired
	IBusinessObjectService n;

	public boolean isSuspendByInstId(String instId) {
		return false;
	}

	public List<BpmInstance> getApplyList(String userId, QueryFilter queryFilter) {
		queryFilter.addParamsFilter("userId", userId);
		return this.g.getApplyList(queryFilter);
	}

	public List<BpmTaskApprove> getApproveHistoryList(String userId, QueryFilter queryFilter) {
		queryFilter.addParamsFilter("approver", userId);
		return this.g.getApproveHistoryList(queryFilter);
	}

	public BpmInstance getTopInstance(BpmInstance instance) {
		if (instance == null || StringUtil.isZeroEmpty(instance.getParentInstId())) {
			return null;
		}
		BpmInstance parentInstance = (BpmInstance) get(instance.getParentInstId());

		BpmInstance topInstance = getTopInstance(parentInstance);
		if (topInstance != null) {
			return topInstance;
		}

		return parentInstance;
	}

	public void a(BpmInstance entity) {
		entity.setCreateOrgId(ContextUtil.getCurrentGroupId());
		super.create(entity);
	}

	public BpmInstance genInstanceByDefinition(IBpmDefinition bpmDefinition) {
		BpmInstance instance = new BpmInstance();
		instance.setId(IdUtil.getSuid());
		instance.setSubject(bpmDefinition.getName());

		instance.setDefId(bpmDefinition.getId());
		instance.setTypeId(bpmDefinition.getTypeId());
		instance.setDefKey(bpmDefinition.getKey());
		instance.setActDefId(bpmDefinition.getActDefId());
		instance.setDefName(bpmDefinition.getName());
		instance.setStatus(InstanceStatus.STATUS_RUNNING.getKey());

		IUser user = ContextUtil.getCurrentUser();
		instance.setCreateBy(user.getUserId());
		instance.setCreator(user.getFullname());
		instance.setCreateTime(new Date());

		instance.setSupportMobile(bpmDefinition.getSupportMobile());

		instance.setParentInstId("0");

		if (bpmDefinition.getStatus().equals("deploy")) {
			instance.setIsFormmal("Y");
		} else if (bpmDefinition.getStatus().equals("draft")) {
			instance.setIsFormmal("N");
		}

		instance.setHasCreate(Boolean.valueOf(false));
		return instance;
	}

	public List<BpmInstance> getByPId(String parentInstId) {
		return this.g.getByPId(parentInstId);
	}

	public BpmInstance getByActInstId(String actInstId) {
		return this.g.getByActInstId(actInstId);
	}

	public void delete(String instId) {
		BpmInstance inst = (BpmInstance) get(instId);
		if (inst == null)
			return;
		BpmInstance topInst = getTopInstance(inst);
		if (topInst != null) {
			b(topInst);
		} else {
			b(inst);
		}
	}

	private void b(BpmInstance instance) {
		String instId = instance.getId();
		remove(instId);
		this.h.removeByInstId(instId);
		this.j.removeByInstId(instId);
		this.k.removeByInstanceId(instId);

		List<BpmBusLink> links = this.l.getByInstanceId(instId);
		for (BpmBusLink link : links) {
			IBusinessObject bo = this.n.getFilledByKey(link.getBizCode());
			if (bo == null) {
				continue;
			}
			this.m.removeData(bo, link.getBizId());
			this.l.remove(link.getId());
		}

		if (StringUtil.isNotEmpty(instance.getActInstId())
				&& this.i.getProcessInstance(instance.getActInstId()) != null) {
			this.i.deleteProcessInstance(instance.getActInstId(), String.format("用户：%s[%s]执行删除实例", new Object[] {
					ContextUtil.getCurrentUser().getFullname(), ContextUtil.getCurrentUser().getAccount() }));
		}

		List<BpmInstance> subInsts = getByPId(instId);
		for (BpmInstance subInst : subInsts)
			b(subInst);
	}
}
