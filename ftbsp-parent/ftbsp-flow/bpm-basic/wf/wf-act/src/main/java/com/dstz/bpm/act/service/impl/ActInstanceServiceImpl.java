package com.dstz.bpm.act.service.impl;

import com.dstz.bpm.act.cmd.GetSuperVariableCmd;
import com.dstz.bpm.act.cmd.ProcessInstanceEndCmd;
import com.dstz.bpm.act.service.ActInstanceService;
import com.dstz.bpm.act.util.ActivitiUtil;
import com.dstz.bpm.api.model.nodedef.BpmNodeDef;
import com.dstz.bpm.api.service.BpmProcessDefService;
import com.dstz.org.api.model.IUser;
import com.dstz.sys.util.ContextUtil;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.impl.ProcessEngineImpl;
import org.activiti.engine.impl.identity.Authentication;
import org.activiti.engine.impl.interceptor.CommandExecutor;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.stereotype.Service;

@Service
public class ActInstanceServiceImpl implements ActInstanceService {
	@Resource
	RuntimeService runtimeService;
	@Resource
	ProcessEngine processEngine;
	@Resource
	BpmProcessDefService h;

	public String startProcessInstance(String actDefId, String businessKey, Map<String, Object> variables) {
		try {
			IUser user = ContextUtil.getCurrentUser();
			Authentication.setAuthenticatedUserId(user.getUserId());
			ProcessInstance instance = this.runtimeService.startProcessInstanceById(actDefId, businessKey, variables);
			return instance.getId();
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		} finally {
			Authentication.setAuthenticatedUserId(null);
		}
	}

	public String startProcessInstance(String actDefId, String businessKey, Map<String, Object> variables,
			String[] aryDestination) {
		String defId = this.h.getDefinitionByActDefId(actDefId).getId();
		BpmNodeDef bpmNodeDef = this.h.getStartEvent(defId);
		String nodeId = bpmNodeDef.getNodeId();

		Map<String, Object> activityMap = ActivitiUtil.a(actDefId, nodeId, aryDestination);

		String actInstId = "";
		try {
			actInstId = startProcessInstance(actDefId, businessKey, variables);
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		} finally {

			ActivitiUtil.a(activityMap);
		}
		return actInstId;
	}

	public Map<String, Object> getVariables(String processInstanceId) {
		return this.runtimeService.getVariables(processInstanceId);
	}

	public void setVariable(String executionId, String variableName, Object value) {
		this.runtimeService.setVariable(executionId, variableName, value);
	}

	public void setVariables(String executionId, Map<String, ? extends Object> variables) {
		this.runtimeService.setVariables(executionId, variables);
	}

	public void removeVariable(String executionId, String variableName) {
		this.runtimeService.removeVariable(executionId, variableName);
	}

	public void removeVariables(String executionId, Collection<String> variableNames) {
		this.runtimeService.removeVariables(executionId, variableNames);
	}

	public boolean hasVariable(String executionId, String variableName) {
		return this.runtimeService.hasVariable(executionId, variableName);
	}

	public Object getVariable(String executionId, String variableName) {
		return this.runtimeService.getVariable(executionId, variableName);
	}

	public Map<String, Object> getVariables(String executionId, Collection<String> variableNames) {
		return this.runtimeService.getVariables(executionId, variableNames);
	}

	public void endProcessInstance(String bpmnInstanceId) {
		ProcessEngineImpl engine = (ProcessEngineImpl) this.processEngine;
		CommandExecutor cmdExecutor = engine.getProcessEngineConfiguration().getCommandExecutor();
		cmdExecutor.execute(new ProcessInstanceEndCmd(bpmnInstanceId));
	}

	public void deleteProcessInstance(String bpmnInstId, String reason) {
		this.runtimeService.deleteProcessInstance(bpmnInstId, reason);
	}

	public Object getSuperVariable(String bpmnId, String varName) {
		CommandExecutor executor = ActivitiUtil.getCommandExecutor();
		GetSuperVariableCmd cmd = new GetSuperVariableCmd(bpmnId, varName);
		return executor.execute(cmd);
	}

	public ProcessInstance getProcessInstance(String bpmnInstId) {
		List<ProcessInstance> instances = this.runtimeService.createProcessInstanceQuery().processInstanceId(bpmnInstId)
				.list();
		if (!instances.isEmpty()) {
			return (ProcessInstance) instances.get(0);
		}
		return null;
	}
}