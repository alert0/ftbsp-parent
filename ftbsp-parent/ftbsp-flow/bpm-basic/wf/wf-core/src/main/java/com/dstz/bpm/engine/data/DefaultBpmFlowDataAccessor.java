package com.dstz.bpm.engine.data;

import cn.hutool.core.map.MapUtil;
import com.alibaba.fastjson.JSONObject;
import com.dstz.base.api.exception.BusinessError;
import com.dstz.base.api.exception.BusinessException;
import com.dstz.base.api.exception.BusinessMessage;
import com.dstz.base.core.util.StringUtil;
import com.dstz.bpm.api.engine.action.button.ButtonFactory;
import com.dstz.bpm.api.engine.context.BpmContext;
import com.dstz.bpm.api.engine.data.BpmFlowDataAccessor;
import com.dstz.bpm.api.engine.data.result.BpmFlowData;
import com.dstz.bpm.api.engine.data.result.BpmFlowInstanceData;
import com.dstz.bpm.api.engine.data.result.BpmFlowTaskData;
import com.dstz.bpm.api.exception.BpmStatusCode;
import com.dstz.bpm.api.model.def.NodeInit;
import com.dstz.bpm.api.model.form.BpmForm;
import com.dstz.bpm.api.model.inst.IBpmInstance;
import com.dstz.bpm.api.model.nodedef.BpmNodeDef;
import com.dstz.bpm.api.model.nodedef.Button;
import com.dstz.bpm.api.model.task.IBpmTask;
import com.dstz.bpm.api.service.BpmProcessDefService;
import com.dstz.bpm.api.service.BpmRightsFormService;
import com.dstz.bpm.core.manager.BpmInstanceManager;
import com.dstz.bpm.core.manager.BpmTaskManager;
import com.dstz.bpm.core.model.BpmInstance;
import com.dstz.bpm.engine.data.handle.BpmBusDataHandle;
import com.dstz.bpm.engine.model.DefaultBpmProcessDef;
import com.dstz.bus.api.model.IBusinessData;
import com.dstz.bus.api.model.IBusinessPermission;
import com.dstz.bus.api.service.IBusinessDataService;
import com.dstz.form.api.model.FormCategory;
import com.dstz.form.api.model.FormType;
import com.dstz.form.api.model.IFormDef;
import com.dstz.form.api.service.FormService;
import com.dstz.sys.api.groovy.IGroovyScriptEngine;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class DefaultBpmFlowDataAccessor implements BpmFlowDataAccessor {
	protected Logger LOG = LoggerFactory.getLogger(getClass());

	@Resource
	BpmInstanceManager bpmInstanceManager;

	@Resource
	BpmRightsFormService bpmRightsFormService;
	@Resource
	BpmProcessDefService bpmProcessDefService;
	@Resource
	BpmTaskManager bpmTaskManager;
	@Resource
	FormService formService;
	@Resource
	BpmBusDataHandle bpmBusDataHandle;
	@Resource
	IGroovyScriptEngine groovyScriptEngine;
	@Resource
	IBusinessDataService businessDataService;

	public BpmFlowInstanceData getInstanceData(String instanceId, FormType formType, String nodeId) {
		BpmContext.cleanTread();
		BpmFlowInstanceData data = new BpmFlowInstanceData();

		BpmInstance instance = (BpmInstance) this.bpmInstanceManager.get(instanceId);
		data.setInstance(instance);

		a(data, instanceId, nodeId, formType, true);
		a(data, nodeId, true);
		return data;
	}

	public Map<String, IBusinessData> getTaskBusData(String taskId) {
		IBpmTask task = (IBpmTask) this.bpmTaskManager.get(taskId);
		if (task == null)
			return null;

		if (a(task))
			return null;

		return this.bpmBusDataHandle.a(task.getId(), null);
	}

	private boolean a(IBpmTask task) {
		DefaultBpmProcessDef processDef = (DefaultBpmProcessDef) this.bpmProcessDefService.getBpmProcessDef(task.getDefId());
		if (processDef.getGlobalForm() == null) {
			return true;
		}
		return (processDef.getGlobalForm().getType() == FormCategory.FRAME);
	}

	public BpmFlowData getStartFlowData(String defId, String instanceId, FormType formType, Boolean readonly) {
		BpmContext.cleanTread();
		if (StringUtil.isEmpty(instanceId) && StringUtil.isEmpty(defId)) {
			throw new BusinessException("获取发起流程数据失败!流程定义id或者实例id缺失", BpmStatusCode.PARAM_ILLEGAL);
		}

		BpmFlowInstanceData data = new BpmFlowInstanceData();

		if (StringUtil.isEmpty(instanceId)) {
			data.setDefId(defId);
			a(data, formType);
		} else {
			BpmInstance instance = (BpmInstance) this.bpmInstanceManager.get(instanceId);
			data.setInstance(instance);
			BpmNodeDef startNode = this.bpmProcessDefService.getStartEvent(instance.getDefId());
			a(data, instanceId, readonly.booleanValue() ? "" : startNode.getNodeId(), formType,
					readonly.booleanValue());
		}

		BpmNodeDef startNode = this.bpmProcessDefService.getStartEvent(data.getDefId());
		a(data, startNode.getNodeId(), readonly.booleanValue());

		return data;
	}

	public BpmFlowData getFlowTaskData(String taskId, FormType formType) {
		BpmContext.cleanTread();

		BpmFlowTaskData taskData = new BpmFlowTaskData();

		IBpmTask task = (IBpmTask) this.bpmTaskManager.get(taskId);
		if (task == null) {
			throw new BusinessMessage("任务可能已经办理完成", BpmStatusCode.TASK_NOT_FOUND);
		}

		taskData.setTask(task);

		a(taskData, task.getInstId(), task.getNodeId(), formType, false);

		a(taskData, task.getNodeId(), false);

		return taskData;
	}

	private void a(BpmFlowInstanceData flowData, FormType formType) {
		String defId = flowData.getDefId();
		BpmNodeDef startNode = this.bpmProcessDefService.getStartEvent(defId);//开始节点
		flowData.setDefName(this.bpmProcessDefService.getBpmProcessDef(defId).getName());//流程名称
		// 1.获取业务权限
		IBusinessPermission permission = this.bpmRightsFormService.getInstanceFormPermission(flowData, startNode.getNodeId(), formType,
				false);
		BpmForm form = flowData.getForm();

		if (FormCategory.INNER.equals(form.getType())) {
			Map<String, IBusinessData> dataMap = this.bpmBusDataHandle.a(permission, defId);

			IFormDef formDef = this.formService.getByFormKey(form.getFormValue());
			if (formDef == null) {
				throw new BusinessException(form.getFormValue(), BpmStatusCode.FLOW_FORM_LOSE);
			}

			form.setFormHtml(formDef.getHtml());
			flowData.setDataMap(dataMap);

			a(flowData, startNode.getNodeId());
		} else {
			String url = form.getFormValue().replace("{bizId}", "");
			form.setFormValue(url);
		}
	}

	private void a(BpmFlowData flowData, String instaneId, String nodeId, FormType formType, boolean isReadOnly) {
		BpmInstance instance = (BpmInstance) this.bpmInstanceManager.get(instaneId);

		IBusinessPermission businessPermission = this.bpmRightsFormService.getInstanceFormPermission(flowData, nodeId, formType,
				isReadOnly);

		BpmForm form = flowData.getForm();
		if (FormCategory.INNER.equals(form.getType())) {
			Map<String, IBusinessData> dataModel = this.bpmBusDataHandle.a(businessPermission, instance);
			flowData.setDataMap(dataModel);

			IFormDef formDef = this.formService.getByFormKey(form.getFormValue());
			if (formDef == null) {
				throw new BusinessException(form.getFormValue(), BpmStatusCode.FLOW_FORM_LOSE);
			}

			form.setFormHtml(formDef.getHtml());

			a(flowData, nodeId);
		}
		a(form, instance);
	}

	private void a(BpmFlowData flowData, String nodeId) {
		Map<String, IBusinessData> bos = flowData.getDataMap();
		if (MapUtil.isEmpty(bos))
			return;
		DefaultBpmProcessDef def = (DefaultBpmProcessDef) this.bpmProcessDefService.getBpmProcessDef(flowData.getDefId());

		Map<String, Object> param = new HashMap<String, Object>();
		param.putAll(bos);
		if (flowData instanceof BpmFlowTaskData) {
			param.put("task", ((BpmFlowTaskData) flowData).getTask());
		} else if (flowData instanceof BpmFlowInstanceData) {
			param.put("instance", ((BpmFlowInstanceData) flowData).getInstance());
		}

		for (NodeInit init : def.d(nodeId)) {
			if (StringUtil.isNotEmpty(init.getBeforeShow())) {
				try {
					this.groovyScriptEngine.execute(init.getBeforeShow(), param);
				} catch (Exception e) {
					throw new BusinessError("执行脚本初始化失败", BpmStatusCode.FLOW_DATA_EXECUTE_SHOWSCRIPT_ERROR, e);
				}
				this.LOG.debug("执行节点数据初始化脚本{}", init.getBeforeShow());
			}
		}

		JSONObject json = new JSONObject();
		JSONObject initJson = new JSONObject();
		for (String key : bos.keySet()) {
			IBusinessData bd = (IBusinessData) bos.get(key);
			JSONObject boJson = this.businessDataService.assemblyFormDefData(bd);
			json.put(key, boJson);

			bd.fullBusDataInitData(initJson);
		}

		flowData.setData(json);
		flowData.setInitData(initJson);
	}

	private void a(BpmFlowData flowData, String nodeId, boolean isReadOnly) {
		BpmNodeDef nodeDef = this.bpmProcessDefService.getBpmNodeDef(flowData.getDefId(), nodeId);
		List<Button> buttons = nodeDef.getButtons();

		if (isReadOnly) {
			buttons = ButtonFactory.getInstanceButtons();
		}

		Map<String, Object> param = new HashMap<String, Object>();
		if (MapUtil.isNotEmpty(flowData.getDataMap())) {
			param.putAll(flowData.getDataMap());
		}
		if (flowData instanceof BpmFlowTaskData) {
			IBpmTask task = ((BpmFlowTaskData) flowData).getTask();
			param.put("task", task);
			param.put("bpmTask", task);
		} else if (flowData instanceof BpmFlowInstanceData) {
			param.put("instance", ((BpmFlowInstanceData) flowData).getInstance());
			param.put("bpmInstance", ((BpmFlowInstanceData) flowData).getInstance());
		}
		List<Button> btns = new ArrayList<Button>();
		for (Button btn : buttons) {
			if (StringUtil.isNotEmpty(btn.getGroovyScript())) {

				try {
					boolean result = this.groovyScriptEngine.executeBoolean(btn.getGroovyScript(), param);
					this.LOG.debug("任务节点按钮Groovy脚本{},执行结果{}", btn.getGroovyScript(), Boolean.valueOf(result));
					if (!result)
						continue;
				} catch (Exception e) {
					throw new BusinessError("按钮脚本执行失败，脚本：" + btn.getGroovyScript(),
							BpmStatusCode.FLOW_DATA_GET_BUTTONS_ERROR, e);
				}

			}
			btns.add(btn);
		}

		btns = ButtonFactory.specialTaskBtnHandler(btns, flowData);
		flowData.setButtonList(btns);
	}

	private void a(BpmForm form, IBpmInstance instance) {
		if (form == null || form.isFormEmpty() || FormCategory.INNER == form.getType()) {
			return;
		}
		String url = form.getFormValue().replace("{bizId}", instance.getBizKey());

		form.setFormValue(url);
	}
}