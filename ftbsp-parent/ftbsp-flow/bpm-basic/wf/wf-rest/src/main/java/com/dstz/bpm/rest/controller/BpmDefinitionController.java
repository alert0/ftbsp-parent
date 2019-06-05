package com.dstz.bpm.rest.controller;

import com.dstz.base.api.aop.annotion.CatchErr;
import com.dstz.base.api.model.IDModel;
import com.dstz.base.api.query.QueryFilter;
import com.dstz.base.api.query.QueryOP;
import com.dstz.base.api.response.impl.ResultMsg;
import com.dstz.base.core.util.AppUtil;
import com.dstz.base.db.model.page.PageResult;
import com.dstz.base.rest.BaseController;
import com.dstz.bpm.core.manager.BpmDefinitionManager;
import com.dstz.bpm.core.model.BpmDefinition;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({ "/bpm/definition" })
@Api(description = "流程定义")
public class BpmDefinitionController extends BaseController<BpmDefinition> {
	@Resource
	BpmDefinitionManager bpmDefinitionManager;

	@RequestMapping({ "listJson" })
	@ApiOperation(value = "流程定义列表", notes = "获取流程定义列表")
	public PageResult listJson(HttpServletRequest request, HttpServletResponse response) throws Exception {
		QueryFilter queryFilter = getQueryFilter(request);
		queryFilter.addFilter("is_main_", "Y", QueryOP.EQUAL);

		List<BpmDefinition> bpmDefinitionList = this.bpmDefinitionManager.query(queryFilter);

		return new PageResult(bpmDefinitionList);
	}

	@RequestMapping({ "save" })
	@ApiOperation(value = "保存流程定义", notes = "保存流程定义")
	@CatchErr(write2response = true, value = "保存流程定义失败")
	public ResultMsg<String> save(@RequestBody BpmDefinition bpmDefinition) throws Exception {
		this.bpmDefinitionManager.create(bpmDefinition);

		return getSuccessResult(bpmDefinition.getActModelId(), "创建成功");
	}

	@RequestMapping({ "clearSysCache" })
	@CatchErr("清除缓存失败")
	public ResultMsg<String> clearCache() throws Exception {
		AppUtil.getCache().clearAll();
		return getSuccessResult("成功清除所有系统缓存");
	}

	protected String getModelDesc() {
		return "流程定义";
	}
}