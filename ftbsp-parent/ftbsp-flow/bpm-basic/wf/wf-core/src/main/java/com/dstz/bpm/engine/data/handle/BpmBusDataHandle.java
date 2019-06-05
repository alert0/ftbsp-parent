package com.dstz.bpm.engine.data.handle;

import cn.hutool.core.map.MapUtil;
import com.dstz.base.api.exception.BusinessError;
import com.dstz.base.core.util.StringUtil;
import com.dstz.base.db.dboper.DbOperator;
import com.dstz.base.db.dboper.DbOperatorFactory;
import com.dstz.bpm.api.engine.action.cmd.BaseActionCmd;
import com.dstz.bpm.api.exception.BpmStatusCode;
import com.dstz.bpm.api.model.def.BpmDataModel;
import com.dstz.bpm.api.model.nodedef.BpmNodeDef;
import com.dstz.bpm.api.service.BpmProcessDefService;
import com.dstz.bpm.api.service.BpmRightsFormService;
import com.dstz.bpm.core.manager.BpmBusLinkManager;
import com.dstz.bpm.core.manager.BpmInstanceManager;
import com.dstz.bpm.core.model.BpmBusLink;
import com.dstz.bpm.core.model.BpmInstance;
import com.dstz.bpm.engine.model.DefaultBpmProcessDef;
import com.dstz.bus.api.model.IBusinessData;
import com.dstz.bus.api.model.IBusinessObject;
import com.dstz.bus.api.model.IBusinessPermission;
import com.dstz.bus.api.service.IBusinessDataService;
import com.dstz.bus.api.service.IBusinessObjectService;
import com.dstz.bus.api.service.IBusinessPermissionService;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BpmBusDataHandle {
	@Resource
	private BpmInstanceManager f;
	@Resource
	private BpmProcessDefService a;
	@Resource
	private BpmBusLinkManager l;
	@Resource
	private BpmRightsFormService aW;
	@Resource
	private IBusinessDataService aD;
	@Autowired
	private IBusinessObjectService n;
	@Autowired
	private IBusinessPermissionService aX;

	public Map<String, IBusinessData> a(IBusinessPermission businessPermission, BpmInstance instance) {
		Map<String, IBusinessData> dataMap = null;

		BpmInstance topInstance = this.f.getTopInstance(instance);

		if (topInstance != null) {
			dataMap = a(topInstance.getId(), businessPermission);
		}
		if (dataMap == null) {
			dataMap = new HashMap<String, IBusinessData>();
		}

		dataMap.putAll(a(instance.getId(), businessPermission));

		DefaultBpmProcessDef processDef = (DefaultBpmProcessDef) this.a.getBpmProcessDef(instance.getDefId());
		List<BpmDataModel> listDataModel = processDef.getDataModelList();
		for (BpmDataModel model : listDataModel) {
			String code = model.getCode();
			if (dataMap.containsKey(code)) {
				continue;
			}
			IBusinessObject businessObject = this.n.getFilledByKey(code);
			businessObject.setPermission(businessPermission.getBusObj(code));

			IBusinessData busData = this.aD.loadData(businessObject, null);
			dataMap.put(code, busData);
		}

		return dataMap;
	}

	public Map<String, IBusinessData> a(String instanceId, IBusinessPermission businessPermission) {
		List<BpmBusLink> busLinks = this.l.getByInstanceId(instanceId);

		Map<String, IBusinessData> dataMap = new HashMap<String, IBusinessData>();
		for (BpmBusLink busLink : busLinks) {
			IBusinessObject businessObject = this.n.getFilledByKey(busLink.getBizCode());
			if (businessPermission != null) {
				businessObject.setPermission(businessPermission.getBusObj(busLink.getBizCode()));
			}
			IBusinessData busData = this.aD.loadData(businessObject, busLink.getBizId());
			if (busData == null) {
				throw new BusinessError(
						String.format("bizCode[%s] bizId[%s]",
								new Object[] { busLink.getBizCode(), busLink.getBizId() }),
						BpmStatusCode.FLOW_BUS_DATA_LOSE);
			}
			dataMap.put(busLink.getBizCode(), busData);
		}

		return dataMap;
	}

	public Map<String, IBusinessData> a(IBusinessPermission businessPermision, String defId) {
		Map<String, IBusinessData> dataMap = new HashMap<String, IBusinessData>();

		DefaultBpmProcessDef processDef = (DefaultBpmProcessDef) this.a.getBpmProcessDef(defId);
		List<BpmDataModel> listDataModel = processDef.getDataModelList();
		for (BpmDataModel model : listDataModel) {
			String code = model.getCode();

			IBusinessObject businessObject = this.n.getFilledByKey(code);
			businessObject.setPermission(businessPermision.getBusObj(code));

			IBusinessData busData = this.aD.loadData(businessObject, null, true);
			dataMap.put(code, busData);
		}

		return dataMap;
	}

	public void n(BaseActionCmd actionCmd) {
		Map<String, IBusinessData> boDataMap = actionCmd.getBizDataMap();
		if (MapUtil.isEmpty(boDataMap)) {
			return;
		}
		BpmInstance instance = (BpmInstance) actionCmd.getBpmInstance();
		String nodeId = actionCmd.getNodeId();
		if (StringUtil.isEmpty(nodeId)) {
			BpmNodeDef startNode = this.a.getStartEvent(instance.getDefId());
			nodeId = startNode.getNodeId();
		}
		IBusinessPermission businessPermision = this.aW.getNodeSavePermission(instance.getDefKey(), nodeId,
				boDataMap.keySet());

		BpmInstance topInstance = this.f.getTopInstance(instance);
		Set<String> topModelCodes = new HashSet<String>();
		if (topInstance != null) {
			DefaultBpmProcessDef topDef = (DefaultBpmProcessDef) this.a.getBpmProcessDef(topInstance.getDefId());
			List<BpmBusLink> topBusLinks = this.l.getByInstanceId(topInstance.getId());

			for (BpmDataModel topModel : topDef.getDataModelList()) {
				String modelCode = topModel.getCode();
				if (boDataMap.containsKey(modelCode)) {
					topModelCodes.add(modelCode);

					IBusinessData businessData = (IBusinessData) boDataMap.get(modelCode);
					businessData.getBusTableRel().getBusObj().setPermission(businessPermision.getBusObj(modelCode));

					this.aD.saveData(businessData);

					a(businessData, modelCode, topInstance, topBusLinks);
				}
			}
		}

		List<BpmBusLink> busLinkList = this.l.getByInstanceId(instance.getId());
		DefaultBpmProcessDef bpmProcessDef = (DefaultBpmProcessDef) this.a.getBpmProcessDef(instance.getDefId());

		for (BpmDataModel dataModel : bpmProcessDef.getDataModelList()) {
			String modelCode = dataModel.getCode();

			if (boDataMap.containsKey(modelCode) && !topModelCodes.contains(modelCode)) {
				IBusinessData businessData = (IBusinessData) boDataMap.get(modelCode);
				businessData.getBusTableRel().getBusObj().setPermission(businessPermision.getBusObj(modelCode));

				this.aD.saveData(businessData);

				a(businessData, modelCode, instance, busLinkList);
			}
		}
	}

	private void a(IBusinessData iBusinessData, String modelCode, BpmInstance instance, List<BpmBusLink> busLinks) {
		for (BpmBusLink link : busLinks) {
			if (link.getBizId().equals(iBusinessData.getPk())) {
				return;
			}
		}

		BpmBusLink busLink = new BpmBusLink();
		busLink.setBizCode(modelCode);
		busLink.setBizId(String.valueOf(iBusinessData.getPk()));
		busLink.setInstId(instance.getId());
		busLink.setDefId(instance.getDefId());

		b(modelCode);
		this.l.create(busLink);
	}

	private static int aY = -1;
	private static Set<String> aZ = Collections.synchronizedSet(new HashSet());

	private static final String tableName = "BPM_BUS_LINK";

	private void b(String partName) {
		DbOperator dbOperator = DbOperatorFactory.getLocal();

		if (StringUtil.isEmpty(partName)) {
			return;
		}
		if (aY == -1) {

			boolean isSupport = dbOperator.supportPartition("BPM_BUS_LINK");
			aY = isSupport ? 1 : 0;
		}

		if (aY == 0) {
			return;
		}
		if (aZ.contains(partName)) {
			return;
		}
		boolean isPartExist = dbOperator.isExsitPartition("BPM_BUS_LINK", partName);
		if (isPartExist) {
			aZ.add(partName);

			return;
		}
		dbOperator.createPartition("BPM_BUS_LINK", partName);

		aZ.add(partName);
	}
}