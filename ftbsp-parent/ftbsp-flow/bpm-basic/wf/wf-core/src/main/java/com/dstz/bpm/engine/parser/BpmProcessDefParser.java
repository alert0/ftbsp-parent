package com.dstz.bpm.engine.parser;

import cn.hutool.core.collection.CollectionUtil;
import com.alibaba.fastjson.JSONObject;
import com.dstz.base.core.util.AppUtil;
import com.dstz.bpm.api.model.nodedef.BpmNodeDef;
import com.dstz.bpm.engine.model.DefaultBpmProcessDef;
import com.dstz.bpm.engine.parser.flow.AbsFlowParse;
import com.dstz.bpm.engine.parser.node.AbsNodeParse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BpmProcessDefParser {
	private static List<AbsFlowParse> bu;
	private static List<AbsNodeParse> bv;

	public static void a(DefaultBpmProcessDef bpmProcessDef, JSONObject bpmDefSetting) {
		JSONObject flowConf = bpmDefSetting.getJSONObject("flow");
		for (AbsFlowParse flowParser : getFlowParsers()) {
			flowParser.parse(bpmProcessDef, flowConf);
		}

		JSONObject nodeMap = bpmDefSetting.getJSONObject("nodeMap");
		for (BpmNodeDef nodeDef : bpmProcessDef.getBpmnNodeDefs()) {
			JSONObject nodeConfig = nodeMap.getJSONObject(nodeDef.getNodeId());

			for (AbsNodeParse nodeParser : getNodeParsers()) {
				if (nodeParser.a(nodeDef)) {
					nodeParser.parse(nodeDef, nodeConfig);
				}
			}
		}
	}

	private static List<AbsFlowParse> getFlowParsers() {
		if (CollectionUtil.isNotEmpty(bu))
			return bu;

		Map<String, AbsFlowParse> map = AppUtil.getImplInstance(AbsFlowParse.class);
		bu = new ArrayList(map.values());

		return bu;
	}

	private static List<AbsNodeParse> getNodeParsers() {
		if (CollectionUtil.isNotEmpty(bv))
			return bv;
		Map<String, AbsNodeParse> map = AppUtil.getImplInstance(AbsNodeParse.class);
		bv = new ArrayList(map.values());

		return bv;
	}
}