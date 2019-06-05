/*     */ package com.dstz.bpm.engine.action.handler;
/*     */ 
/*     */ import cn.hutool.core.collection.CollectionUtil;
/*     */ import cn.hutool.core.map.MapUtil;
/*     */ import com.alibaba.fastjson.JSONObject;
/*     */ import com.dstz.base.api.exception.BusinessError;
/*     */ import com.dstz.base.api.exception.BusinessException;
/*     */ import com.dstz.base.core.util.StringUtil;
/*     */ import com.dstz.bpm.api.constant.ActionType;
/*     */ import com.dstz.bpm.api.engine.action.cmd.ActionCmd;
/*     */ import com.dstz.bpm.api.engine.action.cmd.BaseActionCmd;
/*     */ import com.dstz.bpm.api.engine.action.cmd.TaskActionCmd;
/*     */ import com.dstz.bpm.api.engine.action.handler.ActionHandler;
/*     */ import com.dstz.bpm.api.engine.context.BpmContext;
/*     */ import com.dstz.bpm.api.exception.BpmStatusCode;
/*     */ import com.dstz.bpm.api.exception.WorkFlowException;
/*     */ import com.dstz.bpm.api.model.def.BpmDataModel;
/*     */ import com.dstz.bpm.api.model.def.NodeInit;
/*     */ import com.dstz.bpm.api.model.form.BpmForm;
/*     */ import com.dstz.bpm.api.model.inst.IBpmInstance;
/*     */ import com.dstz.bpm.api.model.nodedef.BpmNodeDef;
/*     */ import com.dstz.bpm.api.model.task.IBpmTask;
/*     */ import com.dstz.bpm.api.service.BpmProcessDefService;
/*     */ import com.dstz.bpm.core.manager.BpmInstanceManager;
/*     */ import com.dstz.bpm.core.manager.TaskIdentityLinkManager;
/*     */ import com.dstz.bpm.core.model.BpmInstance;
/*     */ import com.dstz.bpm.engine.action.cmd.DefualtTaskActionCmd;
/*     */ import com.dstz.bpm.engine.constant.TaskSkipType;
/*     */ import com.dstz.bpm.engine.data.handle.BpmBusDataHandle;
/*     */ import com.dstz.bpm.engine.model.DefaultBpmProcessDef;
/*     */ import com.dstz.bpm.engine.util.HandlerUtil;
/*     */ import com.dstz.bus.api.model.IBusinessData;
/*     */ import com.dstz.bus.api.service.IBusinessDataService;
/*     */ import com.dstz.org.api.model.IUser;
/*     */ import com.dstz.sys.api.groovy.IGroovyScriptEngine;
/*     */ import com.dstz.sys.util.ContextUtil;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import javax.annotation.Resource;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ import org.springframework.transaction.annotation.Transactional;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class AbsActionHandler<T extends BaseActionCmd>
/*     */   extends Object
/*     */   implements ActionHandler<T>
/*     */ {
/*  53 */   protected Logger LOG = LoggerFactory.getLogger(getClass());
/*     */   
/*     */   @Resource
/*     */   protected BpmProcessDefService a;
/*     */   
/*     */   @Resource
/*     */   protected BpmInstanceManager f;
/*     */   @Resource
/*     */   protected BpmBusDataHandle aC;
/*     */   @Resource
/*     */   protected TaskIdentityLinkManager p;
/*     */   @Resource
/*     */   protected IBusinessDataService aD;
/*     */   @Resource
/*     */   protected IGroovyScriptEngine aE;
/*     */   
/*     */   @Transactional
/*     */   public void a(T model) {
/*  71 */     c(model);
/*     */ 
/*     */     
/*  74 */     k(model);
/*     */ 
/*     */     
/*  77 */     BpmContext.setActionModel(model);
/*     */ 
/*     */     
/*  80 */     d(model);
/*     */ 
/*     */     
/*  83 */     b(model);
/*     */     
/*  85 */     f(model);
/*     */ 
/*     */     
/*  88 */     BpmContext.removeActionModel();
/*     */ 
/*     */     
/*  91 */     e(model);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void d(T data) {
/* 112 */     j(data);
/*     */ 
/*     */     
/* 115 */     this.LOG.debug("流程启动处理业务数据...");
/* 116 */     this.aC.n(data);
/*     */     
/* 118 */     h(data);
/*     */   }
/*     */   
/*     */   protected void e(T model) {
/* 122 */     i(model);
/*     */     
/* 124 */     if (getActionType() == ActionType.DRAFT) {
/*     */       return;
/*     */     }
/*     */     
/* 128 */     if (model.isSource()) {
/* 129 */       BpmContext.cleanTread();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private void f(T model) {
/* 135 */     if (getActionType() != ActionType.AGREE && getActionType() != ActionType.OPPOSE && getActionType() != ActionType.START) {
/*     */       return;
/*     */     }
/* 138 */     ActionCmd actionModel = BpmContext.getActionModel();
/* 139 */     if (actionModel == null || actionModel instanceof com.dstz.bpm.api.engine.action.cmd.InstanceActionCmd) {
/*     */       return;
/*     */     }
/*     */     
/* 143 */     DefualtTaskActionCmd taskModel = (DefualtTaskActionCmd)BpmContext.getActionModel();
/*     */     
/* 145 */     if (taskModel.getActionName().equals(ActionType.CREATE))
/*     */       return; 
/* 147 */     if (taskModel.b() == TaskSkipType.NO_SKIP)
/*     */       return; 
/* 149 */     DefualtTaskActionCmd complateModel = new DefualtTaskActionCmd();
/* 150 */     complateModel.setBpmInstance(taskModel.getBpmInstance());
/* 151 */     complateModel.setBpmDefinition(taskModel.getBpmDefinition());
/* 152 */     complateModel.setBizDataMap(taskModel.getBizDataMap());
/* 153 */     complateModel.setBpmIdentities(taskModel.getBpmIdentities());
/* 154 */     complateModel.setBusinessKey(taskModel.getBusinessKey());
/* 155 */     complateModel.setActionName(ActionType.AGREE.getKey());
/* 156 */     complateModel.setBpmTask(taskModel.getBpmTask());
/* 157 */     complateModel.setOpinion(taskModel.b().getValue());
/* 158 */     complateModel.a();
/*     */   }
/*     */   
/*     */   public void g(T model) {
/* 162 */     BpmContext.setActionModel(model);
/* 163 */     h(model);
/* 164 */     b(model);
/* 165 */     f(model);
/* 166 */     BpmContext.removeActionModel();
/* 167 */     e(model);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void j(T actionModel) {
/* 189 */     BpmInstance instance = (BpmInstance)actionModel.getBpmInstance();
/*     */     
/* 191 */     if (StringUtil.isEmpty(actionModel.getBusinessKey()) && StringUtil.isNotEmpty(instance.getBizKey())) {
/* 192 */       actionModel.setBusinessKey(instance.getBizKey());
/*     */     }
/*     */     
/* 195 */     String handler = m(actionModel);
/* 196 */     if (StringUtil.isNotEmpty(handler)) {
/*     */       try {
/* 198 */         HandlerUtil.a(actionModel, handler);
/* 199 */         this.LOG.debug("执行URL表单处理器：{}", handler);
/* 200 */       } catch (Exception ex) {
/* 201 */         throw new WorkFlowException(BpmStatusCode.HANDLER_ERROR, ex);
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/* 206 */     if (StringUtil.isNotEmpty(actionModel.getBusinessKey()) && StringUtil.isEmpty(instance.getBizKey())) {
/* 207 */       instance.setBizKey(actionModel.getBusinessKey());
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected void k(BaseActionCmd actionModel) {
/* 213 */     IBpmInstance instance = actionModel.getBpmInstance();
/*     */     
/* 215 */     if (instance.getIsForbidden().shortValue() == 1) {
/* 216 */       throw new WorkFlowException("流程实例已经被禁止，请联系管理员", BpmStatusCode.DEF_FORBIDDEN);
/*     */     }
/*     */     
/* 219 */     DefaultBpmProcessDef def = (DefaultBpmProcessDef)this.a.getBpmProcessDef(instance.getDefId());
/* 220 */     if ("forbidden".equals(def.getExtProperties().getStatus())) {
/* 221 */       throw new WorkFlowException("流程定义已经被禁用，请联系管理员", BpmStatusCode.DEF_FORBIDDEN);
/*     */     }
/*     */     
/* 224 */     IUser user = ContextUtil.getCurrentUser();
/* 225 */     if (ContextUtil.isAdmin(user))
/*     */       return; 
/* 227 */     String taskId = null;
/* 228 */     String instId = actionModel.getInstanceId();
/* 229 */     if (actionModel instanceof DefualtTaskActionCmd)
/* 230 */     { IBpmTask task = ((DefualtTaskActionCmd)actionModel).getBpmTask();
/* 231 */       if (user.getUserId().equals(task.getAssigneeId())) {
/*     */         return;
/*     */       }
/*     */       
/* 235 */       taskId = task.getId();
/* 236 */       instId = null; }
/* 237 */     else { if (StringUtil.isNotEmpty(def.getProcessDefinitionId())) {
/*     */         return;
/*     */       }
/*     */       
/*     */       return; }
/*     */ 
/*     */     
/* 244 */     Boolean hasPermission = this.p.checkUserOperatorPermission(user.getUserId(), instId, taskId);
/* 245 */     if (!hasPermission.booleanValue()) {
/* 246 */       throw new BusinessException("没有该任务的操作权限", BpmStatusCode.NO_PERMISSION);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void l(T actionModel) {
/* 255 */     IBpmInstance instance = actionModel.getBpmInstance();
/*     */     
/* 257 */     JSONObject busData = actionModel.getBusData();
/* 258 */     if (busData == null || busData.isEmpty()) {
/*     */       return;
/*     */     }
/*     */     
/* 262 */     DefaultBpmProcessDef bpmProcessDef = (DefaultBpmProcessDef)this.a.getBpmProcessDef(instance.getDefId());
/*     */ 
/*     */     
/* 265 */     for (BpmDataModel dataModel : bpmProcessDef.getDataModelList()) {
/* 266 */       String modelCode = dataModel.getCode();
/*     */       
/* 268 */       if (busData.containsKey(modelCode)) {
/* 269 */         IBusinessData businessData = this.aD.parseBusinessData(busData.getJSONObject(modelCode), modelCode);
/* 270 */         actionModel.getBizDataMap().put(modelCode, businessData);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void a(BaseActionCmd cmd, BpmNodeDef nodeDef) {
/* 283 */     String nodeId = nodeDef.getNodeId();
/*     */     
/* 285 */     DefaultBpmProcessDef def = (DefaultBpmProcessDef)this.a.getBpmProcessDef(cmd.getBpmInstance().getDefId());
/* 286 */     List<NodeInit> nodeInitList = def.d(nodeId);
/*     */     
/* 288 */     Map<String, IBusinessData> bos = cmd.getBizDataMap();
/* 289 */     if (MapUtil.isEmpty(bos) || CollectionUtil.isEmpty(nodeInitList)) {
/*     */       return;
/*     */     }
/* 292 */     Map<String, Object> param = new HashMap<String, Object>();
/* 293 */     param.putAll(bos);
/* 294 */     param.put("bpmInstance", cmd.getBpmInstance());
/* 295 */     param.put("actionCmd", cmd);
/* 296 */     ActionType actionType = cmd.getActionType();
/* 297 */     param.put("submitActionDesc", actionType.getName());
/* 298 */     param.put("submitActionName", actionType.getKey());
/* 299 */     param.put("submitOpinion", cmd.getOpinion());
/* 300 */     param.put("isTask", Boolean.valueOf(false));
/*     */ 
/*     */     
/* 303 */     if (cmd instanceof DefualtTaskActionCmd) {
/* 304 */       param.put("isTask", Boolean.valueOf(true));
/* 305 */       param.put("bpmTask", ((DefualtTaskActionCmd)cmd).getBpmTask());
/*     */     } 
/*     */ 
/*     */     
/* 309 */     for (NodeInit init : nodeInitList) {
/* 310 */       if (StringUtil.isNotEmpty(init.getWhenSave())) {
/*     */         try {
/* 312 */           this.aE.execute(init.getWhenSave(), param);
/* 313 */         } catch (Exception e) {
/* 314 */           throw new BusinessError(e.getMessage(), BpmStatusCode.FLOW_DATA_EXECUTE_SHOWSCRIPT_ERROR, e);
/*     */         } 
/* 316 */         this.LOG.debug("执行节点数据初始化脚本{}", init.getBeforeShow());
/*     */       } 
/*     */     } 
/*     */   }
/*     */   private String m(T actionModel) {
/*     */     BpmForm form;
/* 322 */     String defId = actionModel.getDefId();
/*     */ 
/*     */     
/* 325 */     if (actionModel instanceof TaskActionCmd) {
/* 326 */       TaskActionCmd cmd = (TaskActionCmd)actionModel;
/* 327 */       String nodeId = cmd.getNodeId();
/* 328 */       BpmNodeDef nodeDef = this.a.getBpmNodeDef(defId, nodeId);
/* 329 */       form = nodeDef.getForm();
/*     */     } else {
/* 331 */       BpmNodeDef nodeDef = this.a.getStartEvent(defId);
/* 332 */       form = nodeDef.getForm();
/*     */     } 
/* 334 */     if (form == null || form.isFormEmpty()) {
/* 335 */       DefaultBpmProcessDef def = (DefaultBpmProcessDef)this.a.getBpmProcessDef(defId);
/* 336 */       form = def.getGlobalForm();
/*     */     } 
/* 338 */     if (form != null) {
/* 339 */       return form.getFormHandler();
/*     */     }
/*     */     
/* 342 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 348 */   public Boolean isDefault() { return Boolean.valueOf(true); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 353 */   public String getDefaultBeforeScript() { return ""; }
/*     */   
/*     */   protected abstract void b(T paramT);
/*     */   
/*     */   protected abstract void c(T paramT);
/*     */   
/*     */   protected abstract void h(T paramT);
/*     */   
/*     */   protected abstract void i(T paramT);
/*     */ }


/* Location:              D:\Repository\com\dstz\agilebpm\wf-core\1.5.1\wf-core-1.5.1-pg.jar!\com\dstz\bpm\engine\action\handler\AbsActionHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.0
 */