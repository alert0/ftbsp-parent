package com.dstz.bpm.core.manager;

import com.dstz.base.api.query.QueryFilter;
import com.dstz.base.manager.Manager;
import com.dstz.bpm.core.model.BpmDefinition;
import java.util.List;
import java.util.Map;
import org.activiti.engine.repository.Model;

public interface BpmDefinitionManager extends Manager<String, BpmDefinition> {
	void updateBpmnModel(Model paramModel, Map<String, String> paramMap) throws Exception;

	BpmDefinition getDefByActModelId(String paramString);

	BpmDefinition getDefinitionByActDefId(String paramString);

	BpmDefinition getByKey(String paramString);

	List<BpmDefinition> getMyDefinitionList(String paramString, QueryFilter paramQueryFilter);

	String createActModel(BpmDefinition paramBpmDefinition);
}