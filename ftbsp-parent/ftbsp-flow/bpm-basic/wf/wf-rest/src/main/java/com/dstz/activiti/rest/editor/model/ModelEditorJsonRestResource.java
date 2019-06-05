package com.dstz.activiti.rest.editor.model;

import com.alibaba.fastjson.JSONObject;
import com.dstz.base.core.util.StringUtil;
import com.dstz.bpm.api.engine.context.BpmContext;
import com.dstz.bpm.api.service.BpmProcessDefService;
import com.dstz.bpm.core.manager.BpmDefinitionManager;
import com.dstz.bpm.core.model.BpmDefinition;
import com.dstz.bpm.engine.model.DefaultBpmProcessDef;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javax.annotation.Resource;
import org.activiti.engine.ActivitiException;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Model;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(description = "流程设计")
public class ModelEditorJsonRestResource {
	protected static final Logger LOGGER = LoggerFactory.getLogger(ModelEditorJsonRestResource.class);

	@Autowired
	private RepositoryService repositoryService;

	@Resource
	private BpmDefinitionManager bpmDefinitionManager;

	@Autowired
	private BpmProcessDefService bpmProcessDefService;

	@RequestMapping(value = { "/model/{modelId}/json" }, method = { RequestMethod.GET })
	@ResponseBody
	@ApiOperation(value = "查询流程设计信息", notes = "查询流程设计信息")
	public JSONObject getEditorJson(@PathVariable String modelId) {
		JSONObject json = null;
		BpmContext.cleanTread();

		Model model = this.repositoryService.getModel(modelId);
		if (model != null) {
			try {
				if (StringUtils.isNotEmpty(model.getMetaInfo())) {
					json = JSONObject.parseObject(model.getMetaInfo());
				} else {
					json = new JSONObject();
					json.put("name", model.getName());
				}
				json.put("modelId", model.getId());
				String editorJson = new String(this.repositoryService.getModelEditorSource(model.getId()), "utf-8");

				JSONObject editorModelJson = JSONObject.parseObject(editorJson);
				BpmDefinition def = this.bpmDefinitionManager.getDefByActModelId(modelId);

				JSONObject bpmDefSetting = new JSONObject();
				if (StringUtil.isNotEmpty(def.getActDefId())) {
					DefaultBpmProcessDef processDef = (DefaultBpmProcessDef) this.bpmProcessDefService
							.getBpmProcessDef(def.getId());
					bpmDefSetting = processDef.getJson();
				}

				def.setDefSetting(null);
				bpmDefSetting.put("bpmDefinition", def);
				json.put("bpmDefSetting", bpmDefSetting);

				if (!editorModelJson.containsKey("properties")) {
					JSONObject initJson = new JSONObject();
					initJson.put("process_id", model.getKey());
					initJson.put("name", model.getName());
					editorModelJson.put("properties", initJson);
				}

				json.put("model", editorModelJson);
			} catch (Exception e) {
				LOGGER.error("Error creating model JSON", e);
				throw new ActivitiException("Error creating model JSON", e);
			}
		}
		return json;
	}
}