package com.dstz.bpm.core.manager.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.dstz.base.api.exception.BusinessException;
import com.dstz.base.api.exception.BusinessMessage;
import com.dstz.base.api.query.QueryFilter;
import com.dstz.base.api.query.QueryOP;
import com.dstz.base.core.id.IdUtil;
import com.dstz.base.core.util.AppUtil;
import com.dstz.base.core.util.StringUtil;
import com.dstz.base.db.model.query.DefaultQueryFilter;
import com.dstz.base.manager.impl.BaseManager;
import com.dstz.bpm.api.engine.event.BpmDefinitionUpdateEvent;
import com.dstz.bpm.api.service.BpmProcessDefService;
import com.dstz.bpm.core.dao.BpmDefinitionDao;
import com.dstz.bpm.core.manager.BpmDefinitionManager;
import com.dstz.bpm.core.manager.BpmInstanceManager;
import com.dstz.bpm.core.model.BpmDefinition;
import com.dstz.bpm.engine.model.DefaultBpmProcessDef;
import com.dstz.sys.api.constant.EnvironmentConstant;
import com.dstz.sys.api.constant.RightsObjectConstants;
import com.dstz.sys.api.service.SysAuthorizationService;
import com.dstz.sys.util.ContextUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.activiti.bpmn.converter.BpmnXMLConverter;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.bpmn.model.Process;
import org.activiti.editor.language.json.converter.BpmnJsonConverter;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.impl.bpmn.deployer.BpmnDeployer;
import org.activiti.engine.impl.bpmn.parser.BpmnParse;
import org.activiti.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.activiti.engine.impl.context.Context;
import org.activiti.engine.impl.persistence.deploy.DeploymentManager;
import org.activiti.engine.impl.persistence.entity.DeploymentEntity;
import org.activiti.engine.impl.util.IoUtil;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.Model;
import org.activiti.engine.repository.ProcessDefinition;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.PNGTranscoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service("bpmDefinitionManager")
public class BpmDefinitionManagerImpl extends BaseManager<String, BpmDefinition> implements BpmDefinitionManager {
	protected Logger LOG = LoggerFactory.getLogger(this.getClass());
	@Resource
	BpmDefinitionDao bpmDefinitionDao;
	@Resource
	BpmProcessDefService bpmProcessDefService;
	@Resource
	RepositoryService repositoryService;
	@Resource
	ProcessEngineConfiguration processEngineConfiguration;
	@Resource
	SysAuthorizationService sysAuthorizationService;
	@Resource
	BpmInstanceManager bpmInstanceManager;

	public void create(BpmDefinition bpmDefinition) {
		if (StringUtil.isNotEmpty(bpmDefinition.getId())) {
			update(bpmDefinition);

			return;
		}
		List<BpmDefinition> defList = bpmDefinitionDao.getByKey(bpmDefinition.getKey());
		if (CollectionUtil.isNotEmpty(defList)) {
			throw new BusinessMessage("流程定义Key重复：" + bpmDefinition.getKey());
		}

		bpmDefinition.setIsMain("Y");
		bpmDefinition.setStatus("draft");
		bpmDefinition.setVersion(Integer.valueOf(1));

		String defId = IdUtil.getSuid();
		bpmDefinition.setId(defId);
		bpmDefinition.setMainDefId(defId);

		String modelId = createActModel(bpmDefinition);

		bpmDefinition.setActModelId(modelId);
		bpmDefinitionDao.create(bpmDefinition);
	}

	public String createActModel(BpmDefinition bpmDefinition) {
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			ObjectNode editorNode = objectMapper.createObjectNode();
			editorNode.put("id", "canvas");
			editorNode.put("resourceId", "canvas");
			ObjectNode stencilSetNode = objectMapper.createObjectNode();
			stencilSetNode.put("namespace", "http://b3mn.org/stencilset/bpmn2.0#");
			editorNode.set("stencilset", stencilSetNode);
			Model modelData = this.repositoryService.newModel();

			ObjectNode modelObjectNode = objectMapper.createObjectNode();
			modelObjectNode.put("name", bpmDefinition.getName());
			modelObjectNode.put("revision", 1);
			modelObjectNode.put("key", bpmDefinition.getKey());

			modelObjectNode.put("description", bpmDefinition.getDesc());
			modelData.setMetaInfo(modelObjectNode.toString());
			modelData.setName(bpmDefinition.getName());
			modelData.setKey(bpmDefinition.getKey());

			this.repositoryService.saveModel(modelData);

			this.repositoryService.addModelEditorSource(modelData.getId(), editorNode.toString().getBytes("utf-8"));
			return modelData.getId();
		} catch (UnsupportedEncodingException var7) {
			throw new RuntimeException("创建activiti流程定义失败！", var7);
		}
	}

	/**
	 * 保存模型
	 */
	public void updateBpmnModel(Model model, Map<String, String> values) throws Exception {
		String bpmDefSettingJSON = (String) values.get("bpmDefSetting");

		// 查询act_re_model数据，并设置bpm_definition
		BpmDefinition bpmDef = this.getDefByActModelId(model.getId());
		bpmDef.setName((String) values.get("name"));
		bpmDef.setDesc((String) values.get("description"));
		bpmDef.setRev(Integer.valueOf((String) values.get("rev")));
		bpmDef.setDefSetting(bpmDefSettingJSON);

		byte[] jsonXml = ((String) values.get("json_xml")).getBytes("utf-8");
		ObjectNode modelNode = (ObjectNode) (new ObjectMapper()).readTree(jsonXml);
		// 构造工作流模型
		BpmnModel bpmnModel = (new BpmnJsonConverter()).convertToBpmnModel(modelNode);
		if (CollectionUtil.isEmpty(bpmnModel.getProcesses())) {
			throw new BusinessMessage("请绘制流程图后再保存！");

		} else {
			if (!bpmnModel.getProcesses().isEmpty()) {
				((Process) bpmnModel.getProcesses().get(0)).setName(bpmDef.getName());
				((Process) bpmnModel.getProcesses().get(0)).setDocumentation(bpmDef.getDesc());
			}

			byte[] bpmnBytes = (new BpmnXMLConverter()).convertToXML(bpmnModel);

			boolean publish = Boolean.parseBoolean((String) values.get("publish"));
			// 判断bpm_definition id不为空且未发布
			if (!StringUtil.isEmpty(bpmDef.getActDefId()) && !publish) {
				this.saveModelAndBytearray(model, values);
				this.updateBpmDefinition(bpmDef, model, bpmnBytes);
			} else {
				this.create(bpmDef, model, values, bpmnBytes);
			}
		}
	}

	private void saveModelAndBytearray(Model model, Map<String, String> values) {
		try {
			byte[] jsonXml = ((String) values.get("json_xml")).getBytes("utf-8");
			InputStream svgStream = new ByteArrayInputStream(((String) values.get("svg_xml")).getBytes("utf-8"));
			TranscoderInput input = new TranscoderInput(svgStream);// 通过Apache™ Batik SVG Toolkit将模型的SVG图像数据转换成PNG格式
			PNGTranscoder transcoder = new PNGTranscoder();

			ByteArrayOutputStream outStream = new ByteArrayOutputStream();
			TranscoderOutput output = new TranscoderOutput(outStream);

			transcoder.transcode(input, output);
			byte[] svg_xml = outStream.toByteArray();

			this.repositoryService.saveModel(model);// 将模型的元数据存入数据库的ACT_RE_MODEL表
			this.repositoryService.addModelEditorSourceExtra(model.getId(), svg_xml);// 将PNG图像存入数据库的ACT_GE_BYTEARRAY表
			this.repositoryService.addModelEditorSource(model.getId(), jsonXml);// 将模型JSON数据UTF8字符串存入数据库的ACT_GE_BYTEARRAY表
			outStream.close();
		} catch (Exception var10) {
			throw new BusinessException("保存Model信息失败！", var10);
		}
	}

	private void updateBpmDefinition(BpmDefinition definition, Model model, byte[] bpmnBytes)
			throws JsonProcessingException, IOException {
		ProcessDefinition bpmnProcessDef = this.repositoryService.getProcessDefinition(definition.getActDefId());

		ProcessEngineConfigurationImpl conf = (ProcessEngineConfigurationImpl) this.processEngineConfiguration;
		Context.setProcessEngineConfiguration(conf);

		DeploymentManager deploymentManager = conf.getDeploymentManager();
		BpmnDeployer deployer = (BpmnDeployer) deploymentManager.getDeployers().get(0);
		DeploymentEntity deploy = (DeploymentEntity) this.repositoryService.createDeploymentQuery()
				.deploymentId(definition.getActDeployId()).list().get(0);

		ByteArrayInputStream inputStream = new ByteArrayInputStream(bpmnBytes);

		BpmnParse bpmnParse = deployer.getBpmnParser().createParse().sourceInputStream(inputStream)
				.setSourceSystemId(model.getKey() + ".bpmn20.xml").deployment(deploy)
				.name(model.getKey() + ".bpmn20.xml");

		bpmnParse.execute();
		BpmnModel bpmnModel = bpmnParse.getBpmnModel();

		deploymentManager.getBpmnModelCache().add(bpmnProcessDef.getId(), bpmnModel);

		byte[] diagramBytes = IoUtil.readInputStream(this.processEngineConfiguration.getProcessDiagramGenerator()
				.generateDiagram(bpmnModel, "png", this.processEngineConfiguration.getActivityFontName(),
						this.processEngineConfiguration.getLabelFontName(),
						this.processEngineConfiguration.getAnnotationFontName(),
						this.processEngineConfiguration.getClassLoader()),
				(String) null);
		// 根据DEPLOYMENT_ID_、NAME_更新ACT_GE_BYTEARRAY表BYTES_
		bpmDefinitionDao.updateActResourceEntity(bpmnProcessDef.getDeploymentId(), model.getKey() + ".bpmn20.xml",
				bpmnBytes);
		// 根据DEPLOYMENT_ID_、NAME_更新ACT_GE_BYTEARRAY表BYTES_
		bpmDefinitionDao.updateActResourceEntity(bpmnProcessDef.getDeploymentId(),
				model.getKey() + "." + bpmnProcessDef.getKey() + ".png", diagramBytes);
		// 设置BpmDefinition setStatus、setSupportMobile
		this.setDefinitionProp(definition);
		// 更新实体对象 bpm_definition
		this.update(definition);

		this.getByKey(definition);
	}

	private void setDefinitionProp(BpmDefinition bpmDef) {
		DefaultBpmProcessDef def = (DefaultBpmProcessDef) this.bpmProcessDefService.initBpmProcessDef(bpmDef);

		if ("deploy".equals(bpmDef.getStatus()) && "deploy".equals(def.getExtProperties().getStatus())
				&& !AppUtil.getCtxEnvironment().contains(EnvironmentConstant.PROD.key())) {

			throw new BusinessMessage("除了生产环境外，已发布状态的流程禁止修改！");

		} else {
			bpmDef.setStatus(def.getExtProperties().getStatus());
			bpmDef.setSupportMobile(def.getExtProperties().getSupportMobile());
		}
	}

	/**
	 * 流程定义
	 * 
	 * @param definition   bpm_definition
	 * @param preModel     act_re_model
	 * @param values       流程图页面属性xml
	 * @param bpmnModelXml bpmn流程xml
	 * @return
	 */
	private BpmDefinition create(BpmDefinition definition, Model preModel, Map<String, String> values,
			byte[] bpmnModelXml) {
		String processName = definition.getKey() + ".bpmn20.xml";
		// 流程部署对象,发布act_re_deployment->act_re_procdef->act_ge_bytearray
		Deployment deployment = this.repositoryService.createDeployment().name(definition.getKey())
				.addString(processName, new String(bpmnModelXml)).deploy();
		// act_procdef_info
		ProcessDefinition proDefinition = (ProcessDefinition) this.repositoryService.createProcessDefinitionQuery()
				.deploymentId(deployment.getId()).singleResult();

		if (proDefinition == null) {
			throw new RuntimeException("error   ");

		} else if (StringUtil.isEmpty(definition.getActDefId())) {
			definition.setActDefId(proDefinition.getId());
			definition.setActDeployId(deployment.getId());
			this.update(definition);// 更新bpm_definition表act_def_id_、act_deploy_id_
			this.saveModelAndBytearray(preModel, values);
			return definition;

		} else {
			String modelId = this.createActModel(definition);
			Model model = this.repositoryService.getModel(modelId);
			model.setDeploymentId(deployment.getId());
			this.repositoryService.saveModel(model);
			this.saveModelAndBytearray(model, values);

			String newDefId = IdUtil.getSuid();
			BpmDefinition def = (BpmDefinition) this.get(definition.getId());
			def.setIsMain("N");
			def.setMainDefId(newDefId);
			this.update(def);

			definition.setId(newDefId);
			definition.setIsMain("Y");
			definition.setRev(0);
			definition.setMainDefId(newDefId);
			definition.setVersion(definition.getVersion() + 1);
			definition.setCreateBy(ContextUtil.getCurrentUser().getUserId());
			definition.setCreateTime(new Date());

			definition.setActDefId(proDefinition.getId());
			definition.setActDeployId(deployment.getId());
			definition.setActModelId(model.getId());
			bpmDefinitionDao.create(definition);
			return definition;
		}
	}

	public BpmDefinition getDefByActModelId(String actModelId) {
		// 根据act_re_model表id，查询流程定义数据
		List<BpmDefinition> list = bpmDefinitionDao.getDefByActModelId(actModelId);
		if (CollectionUtil.isEmpty(list)) {
			throw new BusinessException("getDefByActModelId 查找失败modelId：" + actModelId);
		}
		if (list.size() > 1) {
			this.LOG.warn("getDefByActModelId 查找多条 modelId：" + actModelId);
		}

		for (BpmDefinition def : list) {
			if ("Y".equals(def.getIsMain())) {
				return def;
			}
		}
		return (BpmDefinition) list.get(0);
	}

	private void getByKey(BpmDefinition def) {
		List<BpmDefinition> defList = bpmDefinitionDao.getByKey(def.getKey());
		Iterator var3 = defList.iterator();
		while (var3.hasNext()) {
			BpmDefinition defEntity = (BpmDefinition) var3.next();
			AppUtil.publishEvent(new BpmDefinitionUpdateEvent(defEntity));
		}
		AppUtil.publishEvent(new BpmDefinitionUpdateEvent(def));
	}

	public BpmDefinition getDefinitionByActDefId(String actDefId) {
		return bpmDefinitionDao.getByActDefId(actDefId);
	}

	public BpmDefinition getByKey(String flowKey) {
		return bpmDefinitionDao.getMainByDefKey(flowKey);
	}

	public List<BpmDefinition> getMyDefinitionList(String userId, QueryFilter queryFilter) {
		Map map = this.sysAuthorizationService.getUserRightsSql(RightsObjectConstants.FLOW, userId, "key_");
		queryFilter.addParams(map);

		return bpmDefinitionDao.getMyDefinitionList(queryFilter);
	}

	public void remove(String entityId) {
		BpmDefinition definition = (BpmDefinition) bpmDefinitionDao.get(entityId);

		if (this.query(definition.getId())) {
			throw new BusinessMessage("该流程定义下存在流程实例，请勿删除！<br> 请清除数据后再来删除");

		} else {
			List<BpmDefinition> definitionList = bpmDefinitionDao.getByKey(definition.getKey());
			Iterator var4 = definitionList.iterator();
			while (var4.hasNext()) {
				BpmDefinition def = (BpmDefinition) var4.next();
				AppUtil.publishEvent(new BpmDefinitionUpdateEvent(def));
				bpmDefinitionDao.remove(def.getId());
				if (StringUtil.isNotEmpty(def.getActDeployId())) {
					this.repositoryService.deleteDeployment(def.getActDeployId());
				}
			}

			if (StringUtil.isNotEmpty(definition.getActModelId())) {
				this.repositoryService.deleteModel(definition.getActModelId());
			}
		}
	}

	private boolean query(String defId) {
		DefaultQueryFilter query = new DefaultQueryFilter();
		query.addFilter("def_id_", defId, QueryOP.EQUAL);
		List list = this.bpmInstanceManager.query(query);
		return CollectionUtil.isNotEmpty(list);
	}

	public void update(BpmDefinition entity) {
		entity.setUpdateTime(new Date());
		int updateRows = bpmDefinitionDao.update(entity);
		AppUtil.publishEvent(new BpmDefinitionUpdateEvent(entity));

		if (updateRows == 0) {
			throw new RuntimeException("流程定义更新失败，当前版本并非最新版本！已经刷新当前服务器缓存，请刷新页面重新修改再提交。id:" + entity.getId()
					+ " reversion:" + entity.getRev());

		}
	}
}
