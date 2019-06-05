package com.dstz.bpm.rest.controller;

import com.dstz.base.api.aop.annotion.CatchErr;
import com.dstz.base.api.exception.BusinessException;
import com.dstz.base.api.query.QueryFilter;
import com.dstz.base.api.response.impl.ResultMsg;
import com.dstz.base.core.util.StringUtil;
import com.dstz.base.db.model.page.PageResult;
import com.dstz.base.rest.ControllerTools;
import com.dstz.bpm.api.engine.action.cmd.FlowRequestParam;
import com.dstz.bpm.api.engine.context.BpmContext;
import com.dstz.bpm.api.engine.data.BpmFlowDataAccessor;
import com.dstz.bpm.api.engine.data.result.BpmFlowData;
import com.dstz.bpm.api.engine.data.result.FlowData;
import com.dstz.bpm.api.exception.BpmStatusCode;
import com.dstz.bpm.api.model.nodedef.BpmNodeDef;
import com.dstz.bpm.api.service.BpmProcessDefService;
import com.dstz.bpm.core.manager.BpmTaskManager;
import com.dstz.bpm.core.model.BpmTask;
import com.dstz.bpm.engine.action.cmd.DefualtTaskActionCmd;
import com.dstz.bpm.plugin.usercalc.util.UserCalcPreview;
import com.dstz.form.api.model.FormType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({ "/bpm/task" })
@Api(description = "流程任务")
public class BpmTaskController extends ControllerTools {
	@Resource
	BpmTaskManager bpmTaskManager;
	@Resource
	BpmFlowDataAccessor bpmFlowDataAccessor;
	@Autowired
	BpmProcessDefService bpmProcessDefService;

	@RequestMapping(value = { "listJson" }, method = { RequestMethod.POST })
	@ApiOperation(value = "流程任务列表", notes = "获取流程任务的列表数据")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "form", dataType = "String", name = "offset", value = "offset"),
			@ApiImplicitParam(paramType = "form", dataType = "String", name = "limit", value = "分页大小"),
			@ApiImplicitParam(paramType = "form", dataType = "String", name = "sort", value = "排序字段"),
			@ApiImplicitParam(paramType = "form", dataType = "String", name = "order", value = "order", defaultValue = "ASC"),
			@ApiImplicitParam(paramType = "form", dataType = "String", name = "filter$VEQ", value = "其他过滤参数，“filter”为数据库过滤字段名，“V”一位代表字段类型，“EQ/IN/LK/..”代表查询类型“等于/in/小于/..”") })
	public PageResult listJson(HttpServletRequest request, HttpServletResponse reponse) throws Exception {
		QueryFilter queryFilter = getQueryFilter(request);

		List<BpmTask> bpmTaskList = this.bpmTaskManager.query(queryFilter);
		return new PageResult(bpmTaskList);
	}

	@RequestMapping(value = { "getBpmTask" }, method = { RequestMethod.POST, RequestMethod.GET })
	@CatchErr
	@ApiOperation(value = "流程任务", notes = "获取流程任务")
	public ResultMsg<BpmTask> getBpmTask(@RequestParam @ApiParam(value = "任务ID", required = true) String id)
			throws Exception {
		BpmTask bpmTask = null;
		if (StringUtil.isNotEmpty(id)) {
			bpmTask = (BpmTask) this.bpmTaskManager.get(id);
		}

		return getSuccessResult(bpmTask);
	}

	@RequestMapping({ "remove" })
	@CatchErr("删除流程任务失败")
	public ResultMsg<String> remove(@RequestParam String id) throws Exception {
		String[] aryIds = StringUtil.getStringAryByStr(id);

		this.bpmTaskManager.removeByIds(aryIds);

		return getSuccessResult("删除流程任务成功");
	}

	@RequestMapping(value = { "getTaskData" }, method = { RequestMethod.POST, RequestMethod.GET })
	@CatchErr
	@ApiOperation(value = "获取流程任务相关数据", notes = "获取任务业务数据，表单按钮等信息")
	public ResultMsg<FlowData> getTaskData(@RequestParam @ApiParam(value = "任务ID", required = true) String taskId,
			@RequestParam(required = false) @ApiParam(value = "表单类型", defaultValue = "pc") String formType)
			throws Exception {
		if (StringUtil.isEmpty(formType)) {
			formType = FormType.PC.value();
		}

		BpmFlowData bpmFlowData = this.bpmFlowDataAccessor.getFlowTaskData(taskId, FormType.fromValue(formType));
		return getSuccessResult(bpmFlowData);
	}

	@RequestMapping(value = { "doAction" }, method = { RequestMethod.POST })
	@CatchErr
	@ApiOperation(value = "执行任务相关动作", notes = "执行同意，驳回，反对，锁定，解锁，人工终止，会签任务等相关操作")
	public ResultMsg<String> doAction(@RequestBody FlowRequestParam flowParam) throws Exception {
		DefualtTaskActionCmd taskModel = new DefualtTaskActionCmd(flowParam);

		BpmTask task = (BpmTask) this.bpmTaskManager.get(taskModel.getTaskId());

		String actionName = taskModel.executeCmd();

		List<BpmTask> tasks = this.bpmTaskManager.getByInstId(task.getInstId());
		tasks.forEach(t -> this.bpmTaskManager.getAssignUserById(t));

		return getSuccessResult(String.format("执行%s操作成功", new Object[] { actionName }));
	}

	@RequestMapping(value = { "unLock" }, method = { RequestMethod.POST, RequestMethod.GET })
	@CatchErr
	@ApiOperation(value = "任务取消指派", notes = "管理员将任务取消指派，若任务原先无候选人，则无法取消指派。")
	public ResultMsg<String> unLock(@RequestParam String taskId) throws Exception {
		this.bpmTaskManager.unLockTask(taskId);
		return getSuccessResult("取消指派成功");
	}

	@RequestMapping(value = { "assignTask" }, method = { RequestMethod.POST, RequestMethod.GET })
	@CatchErr
	@ApiOperation(value = "任务指派", notes = "管理员将任务指派给某一个用户处理")
	public ResultMsg<String> assignTask(@RequestParam String taskId, @RequestParam String userName,
			@RequestParam String userId) throws Exception {
		this.bpmTaskManager.assigneeTask(taskId, userId, userName);
		return getSuccessResult("指派成功");
	}

	@RequestMapping(value = { "handleNodeFreeSelectUser" }, method = { RequestMethod.POST, RequestMethod.GET })
	@CatchErr
	@ApiOperation(value = "处理节点 【自由选择候选人】功能", notes = "根据配置，处理节点可自由选择下一个节点的执行人的逻辑")
	public ResultMsg<Map> handleNodeFreeSelectUser(@RequestBody FlowRequestParam flowParam) throws Exception {
		HashedMap hashedMap = new HashedMap();
		BpmTask task = (BpmTask) this.bpmTaskManager.get(flowParam.getTaskId());
		if (task == null) {
			throw new BusinessException(BpmStatusCode.TASK_NOT_FOUND);
		}

		BpmNodeDef nodeDef = this.bpmProcessDefService.getBpmNodeDef(task.getDefId(), task.getNodeId());
		String freeSelectUser = nodeDef.getNodeProperties().getFreeSelectUser();
		hashedMap.put("type", freeSelectUser);
		boolean freeSelectNode = nodeDef.getNodeProperties().isFreeSelectNode();
		hashedMap.put("freeSelectNode", Boolean.valueOf(freeSelectNode));

		if (!"no".equals(freeSelectUser) || freeSelectNode) {
			handleNodeInfo(flowParam, task, nodeDef, hashedMap, freeSelectUser);
		}

		return getSuccessResult(hashedMap);
	}

	private void handleNodeInfo(FlowRequestParam flowParam, BpmTask task, BpmNodeDef nodeDef,
			Map<String, Object> result, String freeSelectUser) {
		DefualtTaskActionCmd taskModel = new DefualtTaskActionCmd(flowParam);
		taskModel.setBpmTask(task);

		BpmContext.setActionModel(taskModel);
		HashedMap hashedMap1 = new HashedMap();
		HashedMap hashedMap2 = new HashedMap();

		for (BpmNodeDef node : nodeDef.getOutcomeTaskNodes()) {
			hashedMap1.put(node.getNodeId(), node.getName());
			hashedMap2.put(node.getNodeId(), UserCalcPreview.calcNodeUsers(node, taskModel));
		}

		result.put("nodeIdentitysMap", hashedMap2);
		result.put("nodeNameMap", hashedMap1);
		BpmContext.cleanTread();
	}
}