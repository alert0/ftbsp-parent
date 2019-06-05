package com.dstz.bpm.act.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.dstz.bpm.act.service.ActTaskService;
import com.dstz.bpm.act.util.ActivitiUtil;
import java.util.Map;
import javax.annotation.Resource;
import org.activiti.engine.TaskService;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.impl.persistence.entity.TaskEntity;
import org.activiti.engine.task.TaskQuery;
import org.springframework.stereotype.Service;

@Service
public class ActTaskServiceImpl implements ActTaskService {
	@Resource
	TaskService taskService;

	public DelegateTask getByTaskId(String taskId) {
		return (TaskEntity) ((TaskQuery) this.taskService.createTaskQuery().taskId(taskId)).singleResult();
	}

	public void completeTask(String taskId, String... destinations) {
		completeTask(taskId, null, destinations);
	}

	public void completeTask(String taskId, Map<String, Object> variables) {
		if (CollectionUtil.isEmpty(variables)) {
			this.taskService.complete(taskId);
		} else {
			this.taskService.complete(taskId, variables);
		}
	}

	public void completeTask(String taskId, Map<String, Object> variables, String... destinations) {
		TaskEntity task = (TaskEntity) ((TaskQuery) this.taskService.createTaskQuery().taskId(taskId)).singleResult();

		String curNodeId = task.getTaskDefinitionKey();
		String actDefId = task.getProcessDefinitionId();

		Map<String, Object> activityMap = ActivitiUtil.a(actDefId, curNodeId, destinations);
		try {
			completeTask(taskId, variables);
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		} finally {

			ActivitiUtil.a(activityMap);
		}
	}

	public Object getVariable(String taskId, String variableName) {
		return this.taskService.getVariable(taskId, variableName);
	}

	public Map<String, Object> getVariables(String taskId) {
		return this.taskService.getVariables(taskId);
	}

	public void completeTask(String taskId) {
		this.taskService.complete(taskId);
	}

	public void setVariable(String taskId, String variableName, Object value) {
		this.taskService.setVariable(taskId, variableName, value);
	}

	public void setVariables(String taskId, Map<String, ? extends Object> variables) {
		this.taskService.setVariables(taskId, variables);
	}
}