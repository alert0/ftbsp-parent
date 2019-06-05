package com.dstz.bpm.engine.parser;

import com.dstz.bpm.api.constant.NodeType;
import com.dstz.bpm.api.model.nodedef.BpmNodeDef;
import com.dstz.bpm.api.model.nodedef.impl.BaseBpmNodeDef;
import com.dstz.bpm.api.model.nodedef.impl.CallActivityNodeDef;
import com.dstz.bpm.api.model.nodedef.impl.GateWayBpmNodeDef;
import com.dstz.bpm.api.model.nodedef.impl.SubProcessNodeDef;
import com.dstz.bpm.api.model.nodedef.impl.UserTaskNodeDef;
import com.dstz.bpm.engine.model.DefaultBpmProcessDef;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import org.activiti.bpmn.model.Activity;
import org.activiti.bpmn.model.CallActivity;
import org.activiti.bpmn.model.EndEvent;
import org.activiti.bpmn.model.ExclusiveGateway;
import org.activiti.bpmn.model.FlowElement;
import org.activiti.bpmn.model.Gateway;
import org.activiti.bpmn.model.InclusiveGateway;
import org.activiti.bpmn.model.MultiInstanceLoopCharacteristics;
import org.activiti.bpmn.model.ParallelGateway;
import org.activiti.bpmn.model.SequenceFlow;
import org.activiti.bpmn.model.ServiceTask;
import org.activiti.bpmn.model.StartEvent;
import org.activiti.bpmn.model.SubProcess;
import org.activiti.bpmn.model.UserTask;
import org.springframework.stereotype.Component;

@Component
public class BpmDefNodeHandler {
	private Class<FlowElement>[] bt = new Class[] { StartEvent.class, EndEvent.class, ParallelGateway.class,
			InclusiveGateway.class, ExclusiveGateway.class, UserTask.class, ServiceTask.class, CallActivity.class,
			SubProcess.class };

	public void a(DefaultBpmProcessDef bpmProcessDef, Collection<FlowElement> collection) {
		this.a((BpmNodeDef) null, (Collection) collection, (DefaultBpmProcessDef) bpmProcessDef);
	}

	private void a(BpmNodeDef parentNodeDef, Collection<FlowElement> flowElementList,
			DefaultBpmProcessDef bpmProcessDef) {
		Map<String, FlowElement> nodeMap = this.a(flowElementList);
		// 流程连接线list
		List<SequenceFlow> seqList = this.b(flowElementList);
		// 流程节点map
		Map<String, BpmNodeDef> nodeDefMap = this.a(nodeMap, parentNodeDef, bpmProcessDef);

		// 流程节点排序
		this.a(nodeDefMap, seqList);

		List<BpmNodeDef> nodeDefList = new ArrayList(nodeDefMap.values());

		// 流程定义
		bpmProcessDef.setBpmnNodeDefs(nodeDefList);
		Iterator var8 = nodeDefList.iterator();
		while (var8.hasNext()) {
			BpmNodeDef nodeDef = (BpmNodeDef) var8.next();
			BaseBpmNodeDef node = (BaseBpmNodeDef) nodeDef;
			node.setBpmProcessDef(bpmProcessDef);
		}
	}

	private Map<String, BpmNodeDef> a(Map<String, FlowElement> nodeMap, BpmNodeDef parentNodeDef,
			DefaultBpmProcessDef bpmProcessDef) {
		Map<String, BpmNodeDef> map = new HashMap();
		Set<Entry<String, FlowElement>> set = nodeMap.entrySet();
		Iterator it = set.iterator();
		while (it.hasNext()) {
			Entry<String, FlowElement> ent = (Entry) it.next();
			FlowElement flowEl = (FlowElement) ent.getValue();

			BaseBpmNodeDef nodeDef = this.a(parentNodeDef, flowEl, bpmProcessDef);

			map.put(ent.getKey(), nodeDef);
		}
		return map;
	}

	// seqList:[StartNoneEvent1 --> UserTask1, UserTask1 --> UserTask2, UserTask2
	// --> EndNoneEvent1]
	private void a(Map<String, BpmNodeDef> nodeDefMap, List<SequenceFlow> seqList) {
		Iterator var3 = seqList.iterator();
		while (var3.hasNext()) {
			SequenceFlow seq = (SequenceFlow) var3.next();
			BpmNodeDef sourceDef = (BpmNodeDef) nodeDefMap.get(seq.getSourceRef());// 源节点
			BpmNodeDef targetDef = (BpmNodeDef) nodeDefMap.get(seq.getTargetRef());// 目标节点
			sourceDef.addOutcomeNode(targetDef);// 出去节点
			targetDef.addIncomeNode(sourceDef);// 进入节点
		}
	}

	private Map<String, FlowElement> a(Collection<FlowElement> flowElementList) {
		Map<String, FlowElement> map = new HashMap();
		Iterator var3 = flowElementList.iterator();
		while (var3.hasNext()) {
			FlowElement flowElement = (FlowElement) var3.next();
			this.a((FlowElement) flowElement, (Map) map, (Class[]) this.bt);
		}
		return map;
	}

	private BaseBpmNodeDef a(BpmNodeDef parentNodeDef, FlowElement flowElement, DefaultBpmProcessDef bpmProcessDef) {
		BaseBpmNodeDef nodeDef = null;
		if (flowElement instanceof Activity) {
			String multi = this.a((Activity) flowElement);

			if (flowElement instanceof UserTask) {
				if (multi == null) {
					UserTaskNodeDef userTaskDef = new UserTaskNodeDef();
					nodeDef = userTaskDef;
					userTaskDef.setType(NodeType.USERTASK);
				}
			} else if (!(flowElement instanceof ServiceTask)) {
				if (flowElement instanceof CallActivity) {

					CallActivityNodeDef callNodeDef = new CallActivityNodeDef();
					CallActivity call = (CallActivity) flowElement;
					String flowKey = call.getCalledElement();
					callNodeDef.setType(NodeType.CALLACTIVITY);
					callNodeDef.setFlowKey(flowKey);
					nodeDef = callNodeDef;
				} else if (flowElement instanceof SubProcess) {
					SubProcessNodeDef subProcessDef = new SubProcessNodeDef();

					nodeDef = subProcessDef;
					subProcessDef.setNodeId(flowElement.getId());
					subProcessDef.setName(flowElement.getName());
					subProcessDef.setParentBpmNodeDef(parentNodeDef);

					subProcessDef.setBpmProcessDef(bpmProcessDef);
					SubProcess subProcess = (SubProcess) flowElement;

					this.a((BaseBpmNodeDef) subProcessDef, (SubProcess) subProcess,
							(DefaultBpmProcessDef) bpmProcessDef);
				}
			}
		} else if (flowElement instanceof StartEvent) {
			nodeDef = new BaseBpmNodeDef();
			((BaseBpmNodeDef) nodeDef).setType(NodeType.START);

		} else if (flowElement instanceof EndEvent) {
			nodeDef = new BaseBpmNodeDef();
			((BaseBpmNodeDef) nodeDef).setType(NodeType.END);

		} else if (flowElement instanceof Gateway) {
			nodeDef = new GateWayBpmNodeDef();

			if (flowElement instanceof ParallelGateway) {
				((BaseBpmNodeDef) nodeDef).setType(NodeType.PARALLELGATEWAY);

			} else if (flowElement instanceof InclusiveGateway) {
				((BaseBpmNodeDef) nodeDef).setType(NodeType.INCLUSIVEGATEWAY);

			} else if (flowElement instanceof ExclusiveGateway) {
				((BaseBpmNodeDef) nodeDef).setType(NodeType.EXCLUSIVEGATEWAY);
			}
		}

		((BaseBpmNodeDef) nodeDef).setParentBpmNodeDef(parentNodeDef);
		((BaseBpmNodeDef) nodeDef).setNodeId(flowElement.getId());
		((BaseBpmNodeDef) nodeDef).setName(flowElement.getName());
		return (BaseBpmNodeDef) nodeDef;
	}

	private void a(BaseBpmNodeDef nodeDef, SubProcess subProcess, DefaultBpmProcessDef parentProcessDef) {
		DefaultBpmProcessDef bpmProcessDef = new DefaultBpmProcessDef();
		bpmProcessDef.setProcessDefinitionId(subProcess.getId());
		bpmProcessDef.setName(subProcess.getName());
		bpmProcessDef.setDefKey(subProcess.getId());
		bpmProcessDef.setParentProcessDef(parentProcessDef);

		SubProcessNodeDef subNodeDef = (SubProcessNodeDef) nodeDef;

		subNodeDef.setBpmProcessDef(parentProcessDef);
		subNodeDef.setChildBpmProcessDef(bpmProcessDef);
		Collection<FlowElement> list = subProcess.getFlowElements();
		this.a((BpmNodeDef) nodeDef, (Collection) list, (DefaultBpmProcessDef) bpmProcessDef);
	}

	private void a(FlowElement flowElement, Map<String, FlowElement> map, Class... flowTypes) {
		Class[] var4 = flowTypes;
		int var5 = flowTypes.length;

		for (int var6 = 0; var6 < var5; ++var6) {
			Class<? extends FlowElement> flowType = var4[var6];
			if (flowType.isInstance(flowElement)) {
				map.put(flowElement.getId(), flowElement);
				break;
			}
		}

	}

	private String a(Activity flowElement) {
		MultiInstanceLoopCharacteristics jaxbloop = flowElement.getLoopCharacteristics();
		if (jaxbloop == null) {
			return null;
		} else {
			return jaxbloop.isSequential() ? "sequence" : "parallel";
		}
	}

	private List<SequenceFlow> b(Collection<FlowElement> flowElementList) {
		List<SequenceFlow> nodeList = new ArrayList();
		Iterator var3 = flowElementList.iterator();
		while (var3.hasNext()) {
			FlowElement flowElement = (FlowElement) var3.next();
			if (flowElement instanceof SequenceFlow) {
				nodeList.add((SequenceFlow) flowElement);
			}
		}

		return nodeList;
	}
}
