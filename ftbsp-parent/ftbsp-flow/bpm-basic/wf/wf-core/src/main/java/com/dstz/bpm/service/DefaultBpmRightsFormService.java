package com.dstz.bpm.service;

import cn.hutool.core.collection.CollectionUtil;
import com.dstz.base.api.exception.BusinessException;
import com.dstz.base.core.util.StringUtil;
import com.dstz.bpm.api.engine.data.result.BpmFlowData;
import com.dstz.bpm.api.exception.BpmStatusCode;
import com.dstz.bpm.api.model.form.BpmForm;
import com.dstz.bpm.api.model.form.DefaultForm;
import com.dstz.bpm.api.model.nodedef.BpmNodeDef;
import com.dstz.bpm.api.service.BpmProcessDefService;
import com.dstz.bpm.api.service.BpmRightsFormService;
import com.dstz.bpm.engine.model.DefaultBpmProcessDef;
import com.dstz.bus.api.constant.BusinessPermissionObjType;
import com.dstz.bus.api.model.IBusinessPermission;
import com.dstz.bus.api.service.IBusinessPermissionService;
import com.dstz.form.api.model.FormCategory;
import com.dstz.form.api.model.FormType;
import java.util.Set;
import javax.annotation.Resource;
import org.springframework.stereotype.Component;

@Component("defaultBpmFormService")
public class DefaultBpmRightsFormService implements BpmRightsFormService {
	@Resource
	BpmProcessDefService bpmProcessDefService;
	@Resource
	IBusinessPermissionService businessPermissionService;

	public IBusinessPermission getInstanceFormPermission(BpmFlowData flowData, String nodeId, FormType formType,
			boolean isReadOnly) {
		String str;
		IBusinessPermission permision = null;
		BpmForm form = null;
		boolean isMobile = (FormType.MOBILE == formType);
		DefaultBpmProcessDef processDef = (DefaultBpmProcessDef) this.bpmProcessDefService.getBpmProcessDef(flowData.getDefId());

		if (StringUtil.isEmpty(nodeId)) {
			form = isMobile ? processDef.getInstMobileForm() : processDef.getInstForm();
			nodeId = "instance";
			str = "实例";
		} else {
			BpmNodeDef nodeDef = this.bpmProcessDefService.getBpmNodeDef(flowData.getDefId(), nodeId);
			form = isMobile ? nodeDef.getMobileForm() : nodeDef.getForm();
			str = nodeDef.getName();
		}

		if (form == null || form.isFormEmpty()) {
			form = isMobile ? processDef.getGlobalMobileForm() : processDef.getGlobalForm();
			nodeId = "global";
			str = "全局";
		}

		if (form == null || form.isFormEmpty()) {
			throw new BusinessException(String.format("请配置流程“%s”的“%s”表单", new Object[] { processDef.getName(), str }),
					BpmStatusCode.FLOW_FORM_LOSE);
		}

		if (FormCategory.INNER.equals(form.getType())) {
			permision = this.businessPermissionService.getByObjTypeAndObjVal(BusinessPermissionObjType.FLOW.getKey(),
					processDef.getDefKey() + "-" + nodeId, processDef.getDataModelKeys(), true);
			flowData.setPermission(permision.getPermission(isReadOnly));
			flowData.setTablePermission(permision.getTablePermission(isReadOnly));
		}

		DefaultForm bpmForm = new DefaultForm();
		bpmForm.setFormHandler(form.getFormHandler());
		bpmForm.setFormHtml(form.getFormHtml());
		bpmForm.setFormValue(form.getFormValue());
		bpmForm.setName(form.getName());
		bpmForm.setType(form.getType());
		flowData.setForm(bpmForm);

		return permision;
	}

	public IBusinessPermission getNodeSavePermission(String defKey, String nodeId, Set<String> bocodes) {
		String boCodes = null;
		String[] ss = null;
		if (CollectionUtil.isNotEmpty(bocodes)) {
			boCodes = StringUtil.join(bocodes);
		}
		return this.businessPermissionService.getByObjTypeAndObjVal(BusinessPermissionObjType.FLOW.getKey(), defKey + "-" + nodeId, boCodes,
				true);
	}
}