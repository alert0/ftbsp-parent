package com.dstz.activiti.rest.editor.model;

import com.dstz.base.api.aop.annotion.CatchErr;
import com.dstz.base.api.exception.BusinessException;
import com.dstz.base.rest.util.RequestUtil;
import com.dstz.bpm.core.manager.BpmDefinitionManager;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.activiti.bpmn.exceptions.XMLException;
import org.activiti.editor.constants.ModelDataJsonConstants;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(description = "保存流程设计")
public class ModelSaveRestResource implements ModelDataJsonConstants {
	protected static final Logger LOGGER = LoggerFactory.getLogger(ModelSaveRestResource.class);

	@Autowired
	private BpmDefinitionManager bpmDefinitionManager;
	private ObjectMapper objectMapper = new ObjectMapper();

	@Resource
	RepositoryService repositoryService;

	@RequestMapping(value = { "/model/{modelId}/save" }, method = { RequestMethod.PUT })
	@ResponseStatus(HttpStatus.OK)
	@CatchErr
	@ApiOperation(value = "保存流程设计信息", notes = "保存流程设计信息")
	public void saveModel(HttpServletResponse response, HttpServletRequest request, @PathVariable String modelId,
			@RequestBody(required = false) MultiValueMap<String, String> values) throws Exception {
		Map<String, String> params = null;

		if (values != null) {
			params = new HashMap<String, String>();
			for (String key : values.keySet()) {
				params.put(key, values.getFirst(key));
			}
		} else {
			params = RequestUtil.getParameterValueMap(request, false);
		}

		Model model = this.repositoryService.getModel(modelId);
		ObjectNode modelJson = (ObjectNode) this.objectMapper.readTree(model.getMetaInfo());
		modelJson.put("name", (String) params.get("name"));
		modelJson.put("description", (String) params.get("description"));

		try {
			// 保存模型
			this.bpmDefinitionManager.updateBpmnModel(model, params);
		} catch (XMLException e) {
			throw new BusinessException("流程图解析失败！不合法的流程图：" + e.getMessage(), e);
		}
	}
}