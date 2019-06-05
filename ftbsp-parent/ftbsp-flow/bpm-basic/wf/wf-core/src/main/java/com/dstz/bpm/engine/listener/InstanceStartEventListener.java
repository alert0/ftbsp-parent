package com.dstz.bpm.engine.listener;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import com.dstz.base.api.exception.BusinessException;
import com.dstz.base.core.util.StringUtil;
import com.dstz.bpm.api.constant.ActionType;
import com.dstz.bpm.api.constant.EventType;
import com.dstz.bpm.api.constant.ScriptType;
import com.dstz.bpm.api.engine.action.cmd.ActionCmd;
import com.dstz.bpm.api.engine.action.cmd.InstanceActionCmd;
import com.dstz.bpm.api.engine.context.BpmContext;
import com.dstz.bpm.api.exception.BpmStatusCode;
import com.dstz.bpm.api.service.BpmProcessDefService;
import com.dstz.bpm.core.manager.BpmDefinitionManager;
import com.dstz.bpm.core.manager.BpmInstanceManager;
import com.dstz.bpm.core.manager.BpmTaskOpinionManager;
import com.dstz.bpm.core.model.BpmDefinition;
import com.dstz.bpm.core.model.BpmInstance;
import com.dstz.bpm.engine.action.cmd.DefaultInstanceActionCmd;
import com.dstz.bpm.engine.model.DefaultBpmProcessDef;
import com.dstz.bus.api.model.IBusinessData;
import com.dstz.sys.util.ContextUtil;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.annotation.Resource;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class InstanceStartEventListener extends AbstractInstanceListener {
	@Resource
	BpmTaskOpinionManager j;
	@Resource
	BpmProcessDefService a;
	@Resource
	BpmInstanceManager f;
	@Resource
	BpmDefinitionManager bc;

	public EventType getBeforeTriggerEventType() {
		return EventType.START_EVENT;
	}

	public EventType getAfterTriggerEventType() {
		return EventType.START_POST_EVENT;
	}

	public void a(InstanceActionCmd instanceActionModel) {
		this.LOG.debug("流程实例【{}】执行启动过程 instanceID:[{}]", instanceActionModel.getBpmInstance().getSubject(),
				instanceActionModel.getBpmInstance().getId());

		Map<String, Object> actionVariables = instanceActionModel.getActionVariables();
		if (CollectionUtil.isEmpty(actionVariables)) {
			return;
		}

		for (String key : actionVariables.keySet()) {
			instanceActionModel.addVariable(key, actionVariables.get(key));
		}
		this.LOG.debug("设置流程变量【{}】", actionVariables.keySet().toString());
	}

	public void b(InstanceActionCmd instanceActionModel) {
		this.j.createOpinionByInstance(instanceActionModel, true);

		h((DefaultInstanceActionCmd) instanceActionModel);
	}

	public void c(InstanceActionCmd instanceActionModel) {
		this.LOG.debug("流程实例【{}】完成创建过程   instanceID：{}", instanceActionModel.getBpmInstance().getSubject(),
				instanceActionModel.getBpmInstance().getId());
	}

	protected ScriptType getScriptType() {
		return ScriptType.START;
	}

	protected InstanceActionCmd a(ExecutionEntity executionEntity) {
		ActionCmd actionCmd = BpmContext.getActionModel();
		a(executionEntity, actionCmd);

		DefaultInstanceActionCmd actionModel = (DefaultInstanceActionCmd) BpmContext.getActionModel();
		actionModel.setExecutionEntity(executionEntity);

		BpmInstance instance = (BpmInstance) actionModel.getBpmInstance();
		if (StringUtil.isEmpty(instance.getActInstId())) {
			instance.setActDefId(executionEntity.getProcessDefinitionId());
			instance.setActInstId(executionEntity.getProcessInstanceId());
		}
		return actionModel;
	}

	private void h(DefaultInstanceActionCmd data) {
		BpmInstance instance = (BpmInstance) data.getBpmInstance();

		DefaultBpmProcessDef processDef = (DefaultBpmProcessDef) this.a.getBpmProcessDef(instance.getDefId());
		String subjectRule = processDef.getExtProperties().getSubjectRule();

		if (StringUtil.isEmpty(subjectRule))
			return;
		Map<String, Object> ruleVariables = new HashMap<String, Object>();
		ruleVariables.put("title", processDef.getName());
		ruleVariables.put("startorName", ContextUtil.getCurrentUser().getFullname());
		ruleVariables.put("startDate", DateUtil.format(new Date(), "yyyy-MM-dd"));
		ruleVariables.put("startTime", DateUtil.now());
		ruleVariables.putAll(data.getVariables());

		Map<String, IBusinessData> boMap = data.getBizDataMap();
		if (CollectionUtil.isNotEmpty(boMap)) {
			Set<String> bocodes = boMap.keySet();
			for (String bocode : bocodes) {
				IBusinessData bizData = (IBusinessData) boMap.get(bocode);

				Map<String, Object> dataMap = bizData.getData();
				for (Map.Entry<String, Object> entry : dataMap.entrySet()) {
					ruleVariables.put(bocode + "." + (String) entry.getKey(), entry.getValue());
				}
			}
		}

		String subject = a(subjectRule, ruleVariables);

		instance.setSubject(subject);
		this.LOG.debug("更新流程标题:{}", subject);
	}

	private String a(String subject, Map<String, Object> variables) {
		if (StringUtils.isEmpty(subject))
			return "";
		Pattern regex = Pattern.compile("\\{(.*?)\\}", 98);
		Matcher matcher = regex.matcher(subject);
		while (matcher.find()) {
			String tag = matcher.group(0);
			String rule = matcher.group(1);
			String[] aryRule = rule.split(":");
			String name = "";
			if (aryRule.length == 1) {
				name = rule;
			} else {
				name = aryRule[1];
			}
			if (variables.containsKey(name)) {
				Object obj = variables.get(name);
				if (obj != null)
					try {
						subject = subject.replace(tag, obj.toString());
						continue;
					} catch (Exception e) {
						subject = subject.replace(tag, "");
						continue;
					}
				subject = subject.replace(tag, "");
				continue;
			}
			subject = subject.replace(tag, "");
		}

		return subject;
	}

	private void a(ExecutionEntity excutionEntity, ActionCmd preAction) {
		String preActionDefKey = preAction.getBpmInstance().getDefKey();

		if (preAction instanceof InstanceActionCmd) {
			if (!excutionEntity.getProcessDefinitionKey().equals(preActionDefKey)) {
				throw new BusinessException("流程启动失败，错误的线程数据！", BpmStatusCode.ACTIONCMD_ERROR);
			}

			return;
		}
		ExecutionEntity callActivityNode = excutionEntity.getSuperExecution();

		if (preAction instanceof com.dstz.bpm.api.engine.action.cmd.TaskActionCmd && (callActivityNode == null
				|| !preAction.getBpmInstance().getActInstId().equals(callActivityNode.getProcessInstanceId()))) {
			throw new BusinessException(BpmStatusCode.ACTIONCMD_ERROR);
		}

		BpmDefinition subDefinition = this.bc.getByKey(excutionEntity.getProcessDefinitionKey());
		BpmInstance subInstance = this.f.genInstanceByDefinition(subDefinition);

		subInstance.setActInstId(excutionEntity.getProcessInstanceId());
		subInstance.setParentInstId(preAction.getBpmInstance().getId());
		this.f.create(subInstance);

		DefaultInstanceActionCmd startAction = new DefaultInstanceActionCmd();
		startAction.setBpmDefinition(subDefinition);
		startAction.setBpmInstance(subInstance);
		startAction.setBizDataMap(preAction.getBizDataMap());
		startAction.setActionName(ActionType.START.getKey());

		BpmContext.setActionModel(startAction);
	}
}