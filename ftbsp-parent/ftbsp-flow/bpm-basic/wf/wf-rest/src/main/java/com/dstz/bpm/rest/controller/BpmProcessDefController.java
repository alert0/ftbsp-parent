package com.dstz.bpm.rest.controller;

import cn.hutool.core.collection.CollectionUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dstz.base.api.aop.annotion.CatchErr;
import com.dstz.base.api.exception.BusinessException;
import com.dstz.base.api.response.impl.ResultMsg;
import com.dstz.base.core.util.EnumUtil;
import com.dstz.base.core.util.StringUtil;
import com.dstz.base.rest.ControllerTools;
import com.dstz.base.rest.util.RequestUtil;
import com.dstz.bpm.api.engine.action.button.ButtonFactory;
import com.dstz.bpm.api.model.def.BpmDataModel;
import com.dstz.bpm.api.model.def.BpmVariableDef;
import com.dstz.bpm.api.model.nodedef.BpmNodeDef;
import com.dstz.bpm.api.model.nodedef.Button;
import com.dstz.bpm.api.service.BpmProcessDefService;
import com.dstz.bpm.core.manager.BpmDefinitionManager;
import com.dstz.bpm.core.model.BpmDefinition;
import com.dstz.bpm.engine.model.DefaultBpmProcessDef;
import com.dstz.bus.api.model.IBusinessColumn;
import com.dstz.bus.api.model.IBusinessObject;
import com.dstz.bus.api.service.IBusinessDataService;
import com.dstz.bus.api.service.IBusinessObjectService;
import com.dstz.org.api.service.GroupService;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({ "/bpm/processDef/" })
public class BpmProcessDefController extends ControllerTools {
	@Resource
	BpmDefinitionManager bpmDefinitionManager;
	@Resource
	BpmProcessDefService bpmProcessDefService;
	@Resource
	IBusinessDataService bizDataService;
	@Resource
	GroupService userGroupService;
	@Autowired
	IBusinessObjectService businessObjectService;

	@RequestMapping({ "getDefaultNodeBtns" })
	public List<Button> getDefaultNodeBtns(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String nodeId = RequestUtil.getString(request, "nodeId");
		String defId = RequestUtil.getString(request, "defId");
		Boolean isDefault = Boolean.valueOf(RequestUtil.getBoolean(request, "isDefault", false));

		BpmNodeDef nodeDef = this.bpmProcessDefService.getBpmNodeDef(defId, nodeId);

		return ButtonFactory.generateButtons(nodeDef, isDefault.booleanValue());
	}

	@RequestMapping({ "variablesTree" })
	public Object variablesTree(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String defId = RequestUtil.getString(request, "defId");
		String flowKey = RequestUtil.getString(request, "flowKey");

		if (StringUtil.isEmpty(defId)) {
			BpmDefinition definition = this.bpmDefinitionManager.getByKey(flowKey);
			defId = definition.getId();
		}

		DefaultBpmProcessDef bpmProcessDef = (DefaultBpmProcessDef) this.bpmProcessDefService.getBpmProcessDef(defId);
		JSONArray treeJA = new JSONArray();

		List<BpmDataModel> boDefs = bpmProcessDef.getDataModelList();
		if (CollectionUtil.isNotEmpty(boDefs)) {
			for (BpmDataModel boDef : boDefs) {
				List<JSONObject> jsonObject = this.businessObjectService.boTreeData(boDef.getCode());
				treeJA.addAll(jsonObject);
			}
		}

		JSONObject flowVarJson = getFlowVarJson(bpmProcessDef);
		if (flowVarJson != null) {
			treeJA.add(flowVarJson);
		}
		return treeJA;
	}

	private JSONObject getFlowVarJson(DefaultBpmProcessDef procDef) {
		List<BpmVariableDef> variables = procDef.getVariableList();
		JSONObject flowVariable = JSONObject
				.parseObject("{name:\"流程变量\",icon:\"fa fa-bold dark\",\"nodeType\":\"root\"}");

		JSONArray varList = new JSONArray();
		if (CollectionUtil.isNotEmpty(variables)) {
			for (BpmVariableDef variable : variables) {
				String name = variable.getName();
				variable.setName(variable.getKey());

				JSONObject obj = (JSONObject) JSONObject.toJSON(variable);
				obj.put("nodeType", "var");
				varList.add(obj);
			}
		}

		flowVariable.put("children", varList);
		return flowVariable;
	}

	@RequestMapping({ "getGroupTypes" })
	@CatchErr
	public ResultMsg<JSONArray> getGroupTypes(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return getSuccessResult(EnumUtil.toJSONArray(com.dstz.org.api.constant.GroupTypeConstant.class));
	}

	@RequestMapping({ "getSubjectVariable" })
	@CatchErr
	public JSONArray getSubjectVariable(@RequestParam String defId) throws Exception {
		JSONArray jsonArray = new JSONArray();
		BpmDefinition def = (BpmDefinition) this.bpmDefinitionManager.get(defId);
		if (def == null || StringUtil.isEmpty(def.getActDefId())) {
			return new JSONArray();
		}

		DefaultBpmProcessDef bpmProcessDef = (DefaultBpmProcessDef) this.bpmProcessDefService.getBpmProcessDef(defId);
		List<BpmDataModel> boDefs = bpmProcessDef.getDataModelList();
		if (CollectionUtil.isNotEmpty(boDefs)) {
			for (BpmDataModel boDef : boDefs) {
				IBusinessObject bo = this.businessObjectService.getFilledByKey(boDef.getCode());
				if (bo == null) {
					throw new BusinessException("业务对象丢失！请核查[" + boDef.getCode() + "]");
				}

				for (IBusinessColumn column : bo.getRelation().getTable().getColumns()) {
					JSONObject json = new JSONObject();
					json.put("name", bo.getName() + "-" + column.getComment());
					json.put("key", bo.getKey() + "." + column.getKey());
					jsonArray.add(json);
				}
			}
		}

		JSONObject json = JSONObject.parseObject("{name:\"发起人\",key:\"startorName\"}");
		jsonArray.add(json);

		JSONObject json1 = JSONObject.parseObject("{name:\"发起时间\",key:\"startDate\"}");
		jsonArray.add(json1);

		JSONObject json2 = JSONObject.parseObject("{name:\"流程标题\",key:\"title\"}");
		jsonArray.add(json2);

		return jsonArray;
	}
}