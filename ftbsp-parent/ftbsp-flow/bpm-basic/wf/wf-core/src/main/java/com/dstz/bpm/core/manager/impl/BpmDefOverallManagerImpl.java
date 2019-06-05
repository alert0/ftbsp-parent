package com.dstz.bpm.core.manager.impl;

import cn.hutool.core.io.IoUtil;
import com.alibaba.fastjson.JSON;
import com.dstz.base.core.id.IdUtil;
import com.dstz.base.core.util.StringUtil;
import com.dstz.base.core.util.ThreadMsgUtil;
import com.dstz.bpm.core.dao.BpmDefinitionDao;
import com.dstz.bpm.core.manager.BpmDefOverallManager;
import com.dstz.bpm.core.manager.BpmDefinitionManager;
import com.dstz.bpm.core.model.BpmDefinition;
import com.dstz.bpm.core.model.overallview.BpmOverallView;
import com.dstz.bpm.core.model.overallview.OverallViewExport;
import com.dstz.bpm.core.model.overallview.OverallViewImportXml;
import com.dstz.bpm.core.util.XmlCovertUtil;
import com.dstz.sys.util.ContextUtil;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.Model;
import org.activiti.engine.repository.ProcessDefinition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BpmDefOverallManagerImpl implements BpmDefOverallManager {
	protected Logger LOG = LoggerFactory.getLogger(getClass());

	@Resource
	BpmDefinitionManager c;
	@Autowired
	RepositoryService repositoryService;
	@Resource
	BpmDefinitionDao d;

	public BpmOverallView getBpmOverallView(String defId) {
		BpmDefinition def = (BpmDefinition) this.c.get(defId);

		BpmOverallView overallView = new BpmOverallView();
		overallView.setDefId(def.getId());
		overallView.setBpmDefinition(def);

		overallView.setDefSetting(JSON.parseObject(def.getDefSetting()));
		return overallView;
	}

	public void saveBpmOverallView(BpmOverallView overAllView) {
		BpmDefinition def = overAllView.getBpmDefinition();
		def.setDefSetting(overAllView.getDefSetting().toJSONString());
		this.c.update(def);
	}

	public Map<String, List<BpmOverallView>> importPreview(String flowXml) throws Exception {
		OverallViewImportXml voerallViewExport = (OverallViewImportXml) XmlCovertUtil.a(flowXml,
				new Class[] { OverallViewImportXml.class });

		Map<String, List<BpmOverallView>> map = new HashMap<String, List<BpmOverallView>>();

		List<OverallViewExport> list = voerallViewExport.getBpmXmlList();
		for (OverallViewExport overallViewExport : list) {
			List<BpmOverallView> listAllView = new ArrayList<BpmOverallView>();
			BpmOverallView overallView = new BpmOverallView();
			BpmDefinition definition = overallViewExport.getBpmDefinition();

			overallView.setIsUpdateVersion(Boolean.valueOf(true));
			overallView.setBpmDefinition(definition);
			overallView.setPermission(overallViewExport.getPermission());
			overallView.setBpmnXml(overallViewExport.getBpmnXml());
			overallView.setDefSetting(JSON.parseObject(definition.getDefSetting()));
			overallView.setModelJson(overallViewExport.getModelEditorJson());
			listAllView.add(overallView);

			BpmDefinition def = this.c.getByKey(overallViewExport.getBpmDefinition().getKey());
			if (def != null) {
				BpmOverallView oldOverallView = getBpmOverallView(def.getId());
				listAllView.add(oldOverallView);
			}

			map.put(overallViewExport.getBpmDefinition().getName(), listAllView);
		}

		return map;
	}

	public void importSave(List<BpmOverallView> overAllViewList) {
		ThreadMsgUtil.addMsg("流程导入开始,共：" + overAllViewList.size() + "个流程需要导入");
		for (BpmOverallView overAllView : overAllViewList) {

			if (!a(overAllView))
				continue;
			BpmDefinition newDefinition = overAllView.getBpmDefinition();
			BpmDefinition existDefinition = this.c.getByKey(newDefinition.getKey());

			try {
				if (existDefinition == null) {
					a(overAllView, null);
					ThreadMsgUtil.addMsg(String.format("流程:“%s” key:【%s】创建导入成功！",
							new Object[] { newDefinition.getName(), newDefinition.getKey() }));

					continue;
				}

				if (overAllView.getIsUpdateVersion().booleanValue()) {
					a(overAllView, existDefinition);
					ThreadMsgUtil.addMsg(String.format("对流程“%s”key:【%s】进行版本升级成功！",
							new Object[] { newDefinition.getName(), newDefinition.getKey() }));

					continue;
				}
				if (StringUtil.isNotEmpty(existDefinition.getId())) {
					a(existDefinition, newDefinition);
					existDefinition.setDefSetting(overAllView.getDefSetting().toJSONString());
				}
				ProcessDefinition bpmnProcessDef = this.repositoryService
						.getProcessDefinition(existDefinition.getActDefId());

				this.repositoryService.addModelEditorSource(existDefinition.getActModelId(),
						overAllView.getModelJson().getBytes("utf-8"));
				this.d.updateActResourceEntity(bpmnProcessDef.getDeploymentId(),
						existDefinition.getKey() + ".bpmn20.xml", overAllView.getBpmnXml().getBytes("utf-8"));

				this.c.update(existDefinition);
				ThreadMsgUtil.addMsg(String.format("对流程“%s”key:【%s】进行更新成功！",
						new Object[] { newDefinition.getName(), existDefinition.getKey() }));
			} catch (UnsupportedEncodingException e) {
				this.LOG.error("流程导入异常，utf-8 字符流获取失败！ 不支持的字符集", e);
				ThreadMsgUtil.addMsg(String.format("流程导入失败“%s”key:【%s】导入失败 BPMN XML 转流失败！",
						new Object[] { newDefinition.getName(), newDefinition.getKey() }));
			}
		}
	}

	private void a(BpmOverallView overAllView, BpmDefinition existDefinition) throws UnsupportedEncodingException {
		BpmDefinition bpmDefinition = overAllView.getBpmDefinition();

		String processName = bpmDefinition.getKey() + ".bpmn20.xml";

		Deployment deployment = this.repositoryService.createDeployment().name(bpmDefinition.getKey())
				.addString(processName, overAllView.getBpmnXml()).deploy();
		ProcessDefinition proDefinition = (ProcessDefinition) this.repositoryService.createProcessDefinitionQuery()
				.deploymentId(deployment.getId()).singleResult();

		String modelId = this.c.createActModel(bpmDefinition);
		Model model = this.repositoryService.getModel(modelId);
		model.setDeploymentId(deployment.getId());
		this.repositoryService.saveModel(model);
		this.repositoryService.addModelEditorSource(modelId, overAllView.getModelJson().getBytes("utf-8"));
		int version = 0;

		String newDefId = IdUtil.getSuid();
		if (existDefinition != null) {
			existDefinition.setIsMain("N");
			existDefinition.setMainDefId(newDefId);
			this.c.update(existDefinition);
			version = existDefinition.getVersion().intValue() + 1;
		}

		bpmDefinition.setId(newDefId);
		bpmDefinition.setIsMain("Y");
		bpmDefinition.setRev(Integer.valueOf(0));
		bpmDefinition.setVersion(Integer.valueOf(version));
		bpmDefinition.setCreateBy(ContextUtil.getCurrentUser().getUserId());
		bpmDefinition.setCreateTime(new Date());
		bpmDefinition.setDefSetting(overAllView.getDefSetting().toJSONString());

		bpmDefinition.setActDefId(proDefinition.getId());
		bpmDefinition.setActDeployId(deployment.getId());
		bpmDefinition.setActModelId(modelId);
		this.d.create(bpmDefinition);
	}

	private boolean a(BpmOverallView overAllView) {
		BpmDefinition newDefinition = overAllView.getBpmDefinition();
		if (StringUtil.isEmpty(newDefinition.getKey())) {
			ThreadMsgUtil.addMsg(String.format("流程导入失败“%s”key:【%s】导入失败 BpmDefinition KEY 不能为空 ！",
					new Object[] { newDefinition.getName(), newDefinition.getKey() }));
			return false;
		}

		if (StringUtil.isEmpty(overAllView.getBpmnXml())) {
			ThreadMsgUtil.addMsg(String.format("流程导入失败“%s”key:【%s】导入失败 BPMN XML 为空 ！",
					new Object[] { newDefinition.getName(), newDefinition.getKey() }));
			return false;
		}

		return true;
	}

	private void a(BpmDefinition existDefinition, BpmDefinition newDefinition) {
		existDefinition.setName(newDefinition.getName());
		existDefinition.setDesc(newDefinition.getDesc());
		existDefinition.setTypeId(newDefinition.getTypeId());
		existDefinition.setDefSetting(newDefinition.getDefSetting());
		existDefinition.setSupportMobile(newDefinition.getSupportMobile());
	}

	public Map<String, String> exportBpmDefinitions(String... defIds) throws Exception {
		Map<String, String> exportFiles = new HashMap<String, String>();

		OverallViewImportXml overallViewImportXml = new OverallViewImportXml();
		for (String defId : defIds) {
			BpmDefinition def = (BpmDefinition) this.c.get(defId);
			if (def == null || StringUtil.isEmpty(def.getActDefId())) {
				this.LOG.info("defId : {} 非可用流程，已经跳过导出！", defId);
			} else {

				OverallViewExport importXml = new OverallViewExport();
				importXml.setBpmDefinition(def);

				InputStream inputStream = this.repositoryService.getResourceAsStream(def.getActDeployId(),
						def.getKey() + ".bpmn20.xml");
				importXml.setBpmnXml(IoUtil.read(inputStream, "utf-8"));

				String modelEditorJson = new String(this.repositoryService.getModelEditorSource(def.getActModelId()),
						"utf-8");
				importXml.setModelEditorJson(modelEditorJson);

				overallViewImportXml.a(importXml);
			}
		}
		String xml = XmlCovertUtil.a(overallViewImportXml);

		exportFiles.put("agilebpm-flow.xml", xml);
		return exportFiles;
	}
}