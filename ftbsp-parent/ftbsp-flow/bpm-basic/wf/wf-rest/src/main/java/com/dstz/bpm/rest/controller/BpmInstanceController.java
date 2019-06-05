package com.dstz.bpm.rest.controller;

import com.dstz.base.api.aop.annotion.CatchErr;
import com.dstz.base.api.exception.BusinessException;
import com.dstz.base.api.query.QueryFilter;
import com.dstz.base.api.query.QueryOP;
import com.dstz.base.api.response.impl.ResultMsg;
import com.dstz.base.core.util.StringUtil;
import com.dstz.base.db.model.page.PageResult;
import com.dstz.base.rest.ControllerTools;
import com.dstz.base.rest.util.RequestUtil;
import com.dstz.bpm.act.service.ActInstanceService;
import com.dstz.bpm.api.engine.action.cmd.FlowRequestParam;
import com.dstz.bpm.api.engine.data.BpmFlowDataAccessor;
import com.dstz.bpm.api.engine.data.result.BpmFlowData;
import com.dstz.bpm.api.engine.data.result.FlowData;
import com.dstz.bpm.api.exception.BpmStatusCode;
import com.dstz.bpm.api.model.inst.IBpmInstance;
import com.dstz.bpm.api.service.BpmImageService;
import com.dstz.bpm.core.manager.BpmDefinitionManager;
import com.dstz.bpm.core.manager.BpmInstanceManager;
import com.dstz.bpm.core.manager.BpmTaskOpinionManager;
import com.dstz.bpm.core.model.BpmDefinition;
import com.dstz.bpm.core.model.BpmInstance;
import com.dstz.bpm.core.model.BpmTaskOpinion;
import com.dstz.bpm.engine.action.cmd.DefaultInstanceActionCmd;
import com.dstz.form.api.model.FormType;
import com.dstz.sys.util.ContextUtil;
import com.github.pagehelper.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({ "/bpm/instance" })
@Api(description = "流程实例")
public class BpmInstanceController extends ControllerTools {
	@Resource
	BpmInstanceManager bpmInstanceManager;
	@Resource
	BpmFlowDataAccessor bpmFlowDataAccessor;
	@Resource
	BpmTaskOpinionManager bpmTaskOpinionManager;
	@Resource
	BpmImageService bpmImageService;
	@Resource
	BpmDefinitionManager bpmDefinitionMananger;
	@Resource
	ActInstanceService actInstanceService;

	@RequestMapping(value = { "listJson" }, method = { RequestMethod.POST })
	@ApiOperation(value = "流程实例列表", notes = "获取流程实例列表")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "form", dataType = "String", name = "offset", value = "offset"),
			@ApiImplicitParam(paramType = "form", dataType = "String", name = "limit", value = "分页大小"),
			@ApiImplicitParam(paramType = "form", dataType = "String", name = "sort", value = "排序字段"),
			@ApiImplicitParam(paramType = "form", dataType = "String", name = "order", value = "order", defaultValue = "ASC"),
			@ApiImplicitParam(paramType = "form", dataType = "String", name = "filter$VEQ", value = "其他过滤参数，“filter”为数据库过滤字段名，“V”一位代表字段类型，“EQ/IN/LK/..”代表查询类型“等于/in/小于/..”") })
	public PageResult listJson(HttpServletRequest request, HttpServletResponse reponse) throws Exception {
		QueryFilter queryFilter = getQueryFilter(request);

		Page<BpmInstance> bpmInstanceList = (Page) this.bpmInstanceManager.query(queryFilter);
		return new PageResult(bpmInstanceList);
	}

	@RequestMapping(value = { "listJson_currentOrg" }, method = { RequestMethod.POST })
	@ApiOperation(value = "流程实例列表-当前组织", notes = "获取流程实例列表-当前组织")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "form", dataType = "String", name = "offset", value = "offset"),
			@ApiImplicitParam(paramType = "form", dataType = "String", name = "limit", value = "分页大小"),
			@ApiImplicitParam(paramType = "form", dataType = "String", name = "sort", value = "排序字段"),
			@ApiImplicitParam(paramType = "form", dataType = "String", name = "order", value = "order", defaultValue = "ASC"),
			@ApiImplicitParam(paramType = "form", dataType = "String", name = "filter$VEQ", value = "其他过滤参数，“filter”为数据库过滤字段名，“V”一位代表字段类型，“EQ/IN/LK/..”代表查询类型“等于/in/小于/..”") })
	public PageResult listJson_currentOrg(HttpServletRequest request, HttpServletResponse reponse) throws Exception {
		QueryFilter queryFilter = getQueryFilter(request);
		String orgId = ContextUtil.getCurrentGroupId();
		if (StringUtil.isEmpty(orgId)) {
			return new PageResult();
		}

		queryFilter.addFilter("create_org_id_", ContextUtil.getCurrentGroupId(), QueryOP.EQUAL);
		Page<BpmInstance> bpmInstanceList = (Page) this.bpmInstanceManager.query(queryFilter);
		return new PageResult(bpmInstanceList);
	}

	@RequestMapping(value = { "getById" }, method = { RequestMethod.POST })
	@CatchErr
	@ApiOperation(value = "流程实例", notes = "获取流程实例")
	public ResultMsg<IBpmInstance> getBpmInstance(@RequestParam @ApiParam("ID") String id) throws Exception {
		IBpmInstance bpmInstance = null;
		if (StringUtil.isNotEmpty(id)) {
			bpmInstance = (IBpmInstance) this.bpmInstanceManager.get(id);
		}
		return getSuccessResult(bpmInstance);
	}

	@RequestMapping(value = { "getInstanceData" }, method = { RequestMethod.POST, RequestMethod.GET })
	@CatchErr
	@ApiOperation(value = "流程实例数据", notes = "获取流程实例相关数据")
	@ApiImplicitParams({
			@ApiImplicitParam(paramType = "form", dataType = "String", name = "instanceId", value = "流程实例ID"),
			@ApiImplicitParam(paramType = "form", dataType = "String", name = "readonly", value = "是否只读实例", defaultValue = "false"),
			@ApiImplicitParam(paramType = "form", dataType = "String", name = "defId", value = "流程定义ID，启动时使用"),
			@ApiImplicitParam(paramType = "form", dataType = "String", name = "flowKey", value = "流程定义Key，启动时使用,与DefId二选一"),
			@ApiImplicitParam(paramType = "form", dataType = "String", name = "formType", value = "表单类型", defaultValue = "pc") })
	public ResultMsg<FlowData> getInstanceData(HttpServletRequest request) throws Exception {
		String instanceId = request.getParameter("instanceId");
		Boolean readonly = Boolean.valueOf(RequestUtil.getBoolean(request, "readonly", false));

		String defId = request.getParameter("defId");
		String flowKey = RequestUtil.getString(request, "flowKey");
		if (StringUtil.isEmpty(defId) && StringUtil.isNotEmpty(flowKey)) {
			BpmDefinition def = this.bpmDefinitionMananger.getByKey(flowKey);
			if (def == null) {
				throw new BusinessException("流程定义查找失败！ flowKey： " + flowKey, BpmStatusCode.DEF_LOST);
			}
			defId = def.getId();
		}

		String formType = RequestUtil.getString(request, "formType", FormType.PC.value());

		BpmFlowData bpmFlowData = this.bpmFlowDataAccessor.getStartFlowData(defId, instanceId,
				FormType.fromValue(formType), readonly);
		return getSuccessResult(bpmFlowData);
	}

	@RequestMapping(value = { "doAction" }, method = { RequestMethod.POST })
	@CatchErr
	@ApiOperation(value = "执行流程实例相关动作", notes = "流程启动，流程保存草稿，草稿启动等流程实例相关的动作")
	public ResultMsg<String> doAction(@RequestBody FlowRequestParam flowParam) throws Exception {
		DefaultInstanceActionCmd instanceCmd = new DefaultInstanceActionCmd(flowParam);
		String actionName = instanceCmd.executeCmd();

		return getSuccessResult(instanceCmd.getInstanceId(), actionName + "成功");
	}

	@RequestMapping(value = { "getInstanceOpinion" }, method = { RequestMethod.GET, RequestMethod.POST })
	@CatchErr
	@ApiOperation("获取流程意见")
	public ResultMsg<List<BpmTaskOpinion>> getInstanceOpinion(@RequestParam @ApiParam("流程实例ID") String instId)
			throws Exception {
		List<BpmTaskOpinion> taskOpinion = this.bpmTaskOpinionManager.getByInstId(instId);

		return getSuccessResult(taskOpinion, "获取流程意见成功");
	}

	@RequestMapping(value = { "flowImage" }, method = { RequestMethod.GET })
	@ApiOperation("获取流程图流文件")
	public void flowImage(@RequestParam(required = false) @ApiParam("流程实例ID") String instId,
			@RequestParam(required = false) @ApiParam("流程定义ID，流程未启动时使用") String defId, HttpServletResponse response)
			throws Exception {
		String actDefId, actInstId = null;

		if (StringUtil.isNotEmpty(instId)) {
			BpmInstance inst = (BpmInstance) this.bpmInstanceManager.get(instId);
			actInstId = inst.getActInstId();
			actDefId = inst.getActDefId();
		} else {
			BpmDefinition def = (BpmDefinition) this.bpmDefinitionMananger.get(defId);
			actDefId = def.getActDefId();
		}

		response.setContentType("image/png");
		IOUtils.copy(this.bpmImageService.draw(actDefId, actInstId), response.getOutputStream());
	}

	@RequestMapping(value = { "toForbidden" }, method = { RequestMethod.GET, RequestMethod.POST })
	@CatchErr("操作失败")
	@ApiOperation("流程实例禁用/启用")
	public ResultMsg<String> toForbidden(@RequestParam @ApiParam("流程实例ID") String id,
			@RequestParam @ApiParam("禁用/启用") Boolean forbidden) throws Exception {
		BpmInstance inst = (BpmInstance) this.bpmInstanceManager.get(id);
		String msg = "";

		if (forbidden.booleanValue() && inst.getIsForbidden().shortValue() == 0) {
			inst.setIsForbidden(IBpmInstance.INSTANCE_FORBIDDEN);
			msg = "禁用成功";
		} else if (!forbidden.booleanValue()) {
			inst.setIsForbidden(IBpmInstance.INSTANCE_NO_FORBIDDEN);
			msg = "取消禁用成功";
		}

		this.bpmInstanceManager.update(inst);

		return getSuccessResult(msg);
	}

	@RequestMapping({ "startTest" })
	@CatchErr
	public ResultMsg<String> startTest(@RequestParam String flowData) throws Exception {
		this.actInstanceService.startProcessInstance("process:2:10000000890004", "test", null);

		return getSuccessResult("成功");
	}

	@RequestMapping({ "delete" })
	@CatchErr
	public ResultMsg<String> delete(@RequestParam String id) throws Exception {
		this.bpmInstanceManager.delete(id);
		return getSuccessResult("删除实例成功");
	}
}