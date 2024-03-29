package com.dstz.activiti.rest.diagram.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProcessInstanceHighlightsResource {
	@Autowired
	private RuntimeService runtimeService;
	@Autowired
	private RepositoryService repositoryService;
	@Autowired
	private HistoryService historyService;
	protected ObjectMapper objectMapper = new ObjectMapper();

	@RequestMapping(value = { "/process-instance/{processInstanceId}/highlights" }, method = {
			RequestMethod.GET }, produces = { "application/json" })
	public ObjectNode getHighlighted(@PathVariable String processInstanceId) {
		ObjectNode responseJSON = this.objectMapper.createObjectNode();

		responseJSON.put("processInstanceId", processInstanceId);

		ArrayNode activitiesArray = this.objectMapper.createArrayNode();
		ArrayNode flowsArray = this.objectMapper.createArrayNode();

		try {
			ProcessInstance processInstance = (ProcessInstance) this.runtimeService.createProcessInstanceQuery()
					.processInstanceId(processInstanceId).singleResult();
			ProcessDefinitionEntity processDefinition = (ProcessDefinitionEntity) this.repositoryService
					.getProcessDefinition(processInstance.getProcessDefinitionId());

			responseJSON.put("processDefinitionId", processInstance.getProcessDefinitionId());

			List<String> highLightedActivities = this.runtimeService.getActiveActivityIds(processInstanceId);
			List<String> highLightedFlows = getHighLightedFlows(processDefinition, processInstanceId);

			for (String activityId : highLightedActivities) {
				activitiesArray.add(activityId);
			}

			for (String flow : highLightedFlows) {
				flowsArray.add(flow);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		responseJSON.put("activities", activitiesArray);
		responseJSON.put("flows", flowsArray);

		return responseJSON;
	}

	private List<String> getHighLightedFlows(ProcessDefinitionEntity processDefinition, String processInstanceId) {
		List<String> highLightedFlows = new ArrayList<String>();

		List<HistoricActivityInstance> historicActivityInstances = this.historyService
				.createHistoricActivityInstanceQuery().processInstanceId(processInstanceId).list();

		LinkedList<HistoricActivityInstance> hisActInstList = new LinkedList<HistoricActivityInstance>();
		hisActInstList.addAll(historicActivityInstances);

		getHighlightedFlows(processDefinition.getActivities(), hisActInstList, highLightedFlows);

		return highLightedFlows;
	}

	private void getHighlightedFlows(List<ActivityImpl> activityList,
			LinkedList<HistoricActivityInstance> hisActInstList, List<String> highLightedFlows) {
		List<ActivityImpl> startEventActList = new ArrayList<ActivityImpl>();
		Map<String, ActivityImpl> activityMap = new HashMap<String, ActivityImpl>(activityList.size());
		for (ActivityImpl activity : activityList) {

			activityMap.put(activity.getId(), activity);

			String actType = (String) activity.getProperty("type");
			if (actType != null && actType.toLowerCase().indexOf("startevent") >= 0) {
				startEventActList.add(activity);
			}
		}

		HistoricActivityInstance firstHistActInst = (HistoricActivityInstance) hisActInstList.getFirst();
		String firstActType = firstHistActInst.getActivityType();
		if (firstActType != null && firstActType.toLowerCase().indexOf("startevent") < 0) {
			PvmTransition startTrans = getStartTransaction(startEventActList, firstHistActInst);
			if (startTrans != null) {
				highLightedFlows.add(startTrans.getId());
			}
		}

		while (!hisActInstList.isEmpty()) {
			HistoricActivityInstance histActInst = (HistoricActivityInstance) hisActInstList.removeFirst();
			ActivityImpl activity = (ActivityImpl) activityMap.get(histActInst.getActivityId());
			if (activity != null) {
				boolean isParallel = false;
				String type = histActInst.getActivityType();
				if ("parallelGateway".equals(type) || "inclusiveGateway".equals(type)) {
					isParallel = true;
				} else if ("subProcess".equals(histActInst.getActivityType())) {
					getHighlightedFlows(activity.getActivities(), hisActInstList, highLightedFlows);
				}

				List<PvmTransition> allOutgoingTrans = new ArrayList<PvmTransition>();
				allOutgoingTrans.addAll(activity.getOutgoingTransitions());
				allOutgoingTrans.addAll(getBoundaryEventOutgoingTransitions(activity));
				List<String> activityHighLightedFlowIds = getHighlightedFlows(allOutgoingTrans, hisActInstList,
						isParallel);
				highLightedFlows.addAll(activityHighLightedFlowIds);
			}
		}
	}

	private PvmTransition getStartTransaction(List<ActivityImpl> startEventActList,
			HistoricActivityInstance firstActInst) {
		for (ActivityImpl startEventAct : startEventActList) {
			for (PvmTransition trans : startEventAct.getOutgoingTransitions()) {
				if (trans.getDestination().getId().equals(firstActInst.getActivityId())) {
					return trans;
				}
			}
		}
		return null;
	}

	private List<PvmTransition> getBoundaryEventOutgoingTransitions(ActivityImpl activity) {
		List<PvmTransition> boundaryTrans = new ArrayList<PvmTransition>();
		for (ActivityImpl subActivity : activity.getActivities()) {
			String type = (String) subActivity.getProperty("type");
			if (type != null && type.toLowerCase().indexOf("boundary") >= 0) {
				boundaryTrans.addAll(subActivity.getOutgoingTransitions());
			}
		}
		return boundaryTrans;
	}

	private List<String> getHighlightedFlows(List<PvmTransition> pvmTransitionList,
			LinkedList<HistoricActivityInstance> hisActInstList, boolean isParallel) {
		List<String> highLightedFlowIds = new ArrayList<String>();

		PvmTransition earliestTrans = null;
		HistoricActivityInstance earliestHisActInst = null;

		for (PvmTransition pvmTransition : pvmTransitionList) {

			String destActId = pvmTransition.getDestination().getId();
			HistoricActivityInstance destHisActInst = findHisActInst(hisActInstList, destActId);
			if (destHisActInst != null) {
				if (isParallel) {
					highLightedFlowIds.add(pvmTransition.getId());
					continue;
				}
				if (earliestHisActInst == null || earliestHisActInst.getId().compareTo(destHisActInst.getId()) > 0) {
					earliestTrans = pvmTransition;
					earliestHisActInst = destHisActInst;
				}
			}
		}

		if (!isParallel && earliestTrans != null) {
			highLightedFlowIds.add(earliestTrans.getId());
		}

		return highLightedFlowIds;
	}

	private HistoricActivityInstance findHisActInst(LinkedList<HistoricActivityInstance> hisActInstList, String actId) {
		for (HistoricActivityInstance hisActInst : hisActInstList) {
			if (hisActInst.getActivityId().equals(actId)) {
				return hisActInst;
			}
		}
		return null;
	}
}