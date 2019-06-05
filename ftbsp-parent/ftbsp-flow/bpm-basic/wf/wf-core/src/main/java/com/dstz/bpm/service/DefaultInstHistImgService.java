package com.dstz.bpm.service;

import com.dstz.base.api.exception.BusinessException;
import com.dstz.base.core.util.StringUtil;
import com.dstz.base.core.util.ThreadMapUtil;
import com.dstz.bpm.act.img.BpmProcessDiagramGenerator;
import com.dstz.bpm.api.constant.OpinionStatus;
import com.dstz.bpm.api.exception.BpmStatusCode;
import com.dstz.bpm.api.service.BpmImageService;
import com.dstz.bpm.core.manager.BpmInstanceManager;
import com.dstz.bpm.core.manager.BpmTaskStackManager;
import com.dstz.bpm.core.model.BpmInstance;
import com.dstz.bpm.core.model.BpmTaskStack;
import java.awt.Color;
import java.awt.Paint;
import java.io.InputStream;
import java.io.Reader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.RepositoryService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DefaultInstHistImgService implements BpmImageService {
	@Autowired
	private RepositoryService repositoryService;
	@Autowired
	private ProcessEngineConfiguration processEngineConfiguration;
	@Autowired
	private BpmInstanceManager f;
	@Autowired
	private BpmTaskStackManager k;
	private Object imageStream;

	public InputStream draw(String actDefId, String actInstId) throws Exception {
		if (StringUtil.isEmpty(actDefId)) {
			throw new BusinessException("流程定义actDefId不能缺失", BpmStatusCode.PARAM_ILLEGAL);
		}
		if (StringUtil.isEmpty(actInstId)) {
			return j(actDefId);
		}

		Map<String, Paint> nodeMap = new HashMap<String, Paint>();
		Map<String, Paint> flowMap = new HashMap<String, Paint>();
		Map<String, Paint> gateMap = new HashMap<String, Paint>();
		BpmInstance instance = this.f.getByActInstId(actInstId);
		List<BpmTaskStack> stacks = this.k.getByInstanceId(instance.getId());
		for (BpmTaskStack stack : stacks) {
			if ("sequenceFlow".equals(stack.getNodeType())) {
				flowMap.put(stack.getNodeId(), i(stack.getActionName()));
				continue;
			}
			if ("exclusiveGateway".equals(stack.getNodeType())) {
				gateMap.put(stack.getNodeId(), i(stack.getActionName()));
				continue;
			}
			nodeMap.put(stack.getNodeId(), i(stack.getActionName()));
		}

		ThreadMapUtil.put("DefaultInstHistImgService_nodeMap", nodeMap);
		ThreadMapUtil.put("DefaultInstHistImgService_flowMap", flowMap);
		ThreadMapUtil.put("DefaultInstHistImgService_gateMap", gateMap);

		imageStream = null;

		try {
			BpmnModel bpmnModel = this.repositoryService.getBpmnModel(actDefId);

			BpmProcessDiagramGenerator diagramGenerator = new BpmProcessDiagramGenerator();
			imageStream = diagramGenerator.generateDiagram(bpmnModel, "png", nodeMap, flowMap);
		} finally {
			IOUtils.closeQuietly((Reader) imageStream);
		}
		return (InputStream) imageStream;
	}

	private Paint i(String action) {
		if (StringUtil.isEmpty(action)) {
			return Color.GREEN;
		}
		OpinionStatus status = OpinionStatus.fromKey(action);
		if (status == null)
			return Color.GREEN;

		switch (status.ordinal()) {
		case 1:
			return Color.YELLOW;
		case 2:
			return Color.RED;
		case 3:
			return Color.BLACK;
		case 4:
			return Color.RED;
		case 5:
			return Color.GREEN;
		case 6:
			return Color.PINK;
		case 7:
			return Color.GRAY;
		case 8:
			return Color.PINK;
		case 9:
			return Color.PINK;
		case 10:
			return Color.PINK;
		case 11:
			return Color.PINK;
		case 12:
			return Color.GRAY;
		case 13:
			return Color.RED;
		}
		return Color.RED;
	}

	private InputStream j(String actDefId) {
		BpmnModel bpmnModel = this.repositoryService.getBpmnModel(actDefId);
		return this.processEngineConfiguration.getProcessDiagramGenerator().generateDiagram(bpmnModel, "png",
				this.processEngineConfiguration.getActivityFontName(),
				this.processEngineConfiguration.getLabelFontName(),
				this.processEngineConfiguration.getAnnotationFontName(),
				this.processEngineConfiguration.getClassLoader());
	}
}