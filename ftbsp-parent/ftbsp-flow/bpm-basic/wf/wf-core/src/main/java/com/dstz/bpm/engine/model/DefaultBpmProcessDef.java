package com.dstz.bpm.engine.model;

import cn.hutool.core.collection.CollectionUtil;
import com.alibaba.fastjson.JSONObject;
import com.dstz.base.core.util.StringUtil;
import com.dstz.bpm.api.constant.NodeType;
import com.dstz.bpm.api.engine.plugin.context.BpmPluginContext;
import com.dstz.bpm.api.model.def.BpmDataModel;
import com.dstz.bpm.api.model.def.BpmDefProperties;
import com.dstz.bpm.api.model.def.BpmProcessDef;
import com.dstz.bpm.api.model.def.BpmVariableDef;
import com.dstz.bpm.api.model.def.NodeInit;
import com.dstz.bpm.api.model.form.BpmForm;
import com.dstz.bpm.api.model.nodedef.BpmNodeDef;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DefaultBpmProcessDef implements BpmProcessDef {
	private static final long serialVersionUID = 1L;
	private String F = "";//key
	private String name = "";//name
	private String processDefinitionId = "";//流程定义id
	private List<BpmNodeDef> bf;//节点定义
	private BpmProcessDef bg = null;//流程定义
	private List<BpmPluginContext> bh = new ArrayList();//插件
	private List<BpmVariableDef> bi = new ArrayList();//变量
	private List<BpmDataModel> bj = new ArrayList();//数据模型
	private BpmForm instForm = null;//表单
	private BpmForm instMobileForm = null;
	private BpmForm globalForm = null;
	private BpmForm globalMobileForm = null;
	private BpmDefProperties bo = new BpmDefProperties();//工作流属性
	private List<NodeInit> bp = new ArrayList();//节点
	private JSONObject bq;

	public BpmProcessDef getParentProcessDef() {
		return this.bg;
	}

	public BpmDefProperties getExtProperties() {
		return this.bo;
	}

	public void setExtProperties(BpmDefProperties extPropertys) {
		this.bo = extPropertys;
	}

	public List<BpmPluginContext> getBpmPluginContexts() {
		return this.bh;
	}

	public BpmPluginContext a(Class<?> clazz) {
		List<BpmPluginContext> Plugins = getBpmPluginContexts();
		if (CollectionUtil.isEmpty(Plugins))
			return null;

		for (BpmPluginContext pulgin : Plugins) {
			if (pulgin.getClass().isAssignableFrom(clazz))
				return pulgin;
		}
		return null;
	}

	public List<BpmVariableDef> getVariableList() {
		return this.bi;
	}

	public BpmForm getInstForm() {
		return this.instForm;
	}

	public BpmForm getInstMobileForm() {
		return this.instMobileForm;
	}

	public BpmForm getGlobalForm() {
		return this.globalForm;
	}

	public BpmForm getGlobalMobileForm() {
		return this.globalMobileForm;
	}

	public List<BpmPluginContext> getPluginContextList() {
		return this.bh;
	}

	public void setPluginContextList(List<BpmPluginContext> pluginContextList) {
		Collections.sort(pluginContextList);
		this.bh = pluginContextList;
	}

	public List<BpmVariableDef> getVarList() {
		return this.bi;
	}

	public void setVarList(List<BpmVariableDef> varList) {
		this.bi = varList;
	}

	public void setInstForm(BpmForm instForm) {
		this.instForm = instForm;
	}

	public void setInstMobileForm(BpmForm instMobileForm) {
		this.instMobileForm = instMobileForm;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void setGlobalForm(BpmForm globalForm) {
		this.globalForm = globalForm;
	}

	public void setGlobalMobileForm(BpmForm globalMobileForm) {
		this.globalMobileForm = globalMobileForm;
	}

	public String getDefKey() {
		return this.F;
	}

	public void setDefKey(String defKey) {
		this.F = defKey;
	}

	public void setProcessDefinitionId(String processDefinitionId) {
		this.processDefinitionId = processDefinitionId;
	}

	public String getProcessDefinitionId() {
		return this.processDefinitionId;
	}

	public void setBpmnNodeDefs(List<BpmNodeDef> bpmnNodeDefs) {
		this.bf = bpmnNodeDefs;
	}

	public List<BpmNodeDef> getBpmnNodeDefs() {
		return this.bf;
	}

	public BpmNodeDef getBpmnNodeDef(String nodeId) {
		for (BpmNodeDef nodeDef : this.bf) {
			if (nodeId.equals(nodeDef.getNodeId())) {
				return nodeDef;
			}
		}
		return null;
	}

	public BpmNodeDef getStartEvent() {
		for (BpmNodeDef nodeDef : this.bf) {
			if (nodeDef.getType().equals(NodeType.START)) {
				return nodeDef;
			}
		}
		return null;
	}

	public List<NodeInit> getNodeInitList() {
		return this.bp;
	}

	public List<NodeInit> d(String nodeId) {
		List<NodeInit> initList = new ArrayList<NodeInit>();
		for (NodeInit init : this.bp) {
			if (StringUtil.isNotEmpty(nodeId) && init.getNodeId().equals(nodeId)) {
				initList.add(init);
			}
		}

		return initList;
	}

	public void setNodeInitList(List<NodeInit> nodeInitList) {
		this.bp = nodeInitList;
	}

	public List<BpmNodeDef> getStartNodes() {
		BpmNodeDef startNode = getStartEvent();
		if (startNode == null)
			return null;
		return startNode.getOutcomeNodes();
	}

	public List<BpmNodeDef> getEndEvents() {
		List<BpmNodeDef> rtnList = new ArrayList<BpmNodeDef>();
		for (BpmNodeDef nodeDef : this.bf) {
			if (nodeDef.getType().equals(NodeType.END)) {
				rtnList.add(nodeDef);
			}
		}
		return rtnList;
	}

	public List<BpmDataModel> getDataModelList() {
		return this.bj;
	}

	public String getDataModelKeys() {
		List<String> keys = new ArrayList<String>();
		for (BpmDataModel model : this.bj) {
			keys.add(model.getCode());
		}
		return StringUtil.join(keys);
	}

	public void setDataModelList(List<BpmDataModel> dataModelList) {
		this.bj = dataModelList;
	}

	public void setParentProcessDef(DefaultBpmProcessDef processDef) {
		this.bg = processDef;
	}

	public void setJson(JSONObject bpmDefSetting) {
		this.bq = bpmDefSetting;
	}

	public JSONObject getJson() {
		return this.bq;
	}
}