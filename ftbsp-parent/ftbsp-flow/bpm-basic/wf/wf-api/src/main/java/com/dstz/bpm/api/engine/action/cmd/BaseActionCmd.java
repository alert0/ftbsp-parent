/*     */ package com.dstz.bpm.api.engine.action.cmd;
/*     */ 
/*     */ import cn.hutool.core.collection.CollectionUtil;
/*     */ import com.alibaba.fastjson.JSON;
/*     */ import com.alibaba.fastjson.JSONArray;
/*     */ import com.alibaba.fastjson.JSONObject;
/*     */ import com.dstz.base.api.exception.BusinessException;
/*     */ import com.dstz.base.core.util.AppUtil;
/*     */ import com.dstz.base.core.util.StringUtil;
/*     */ import com.dstz.bpm.api.constant.ActionType;
/*     */ import com.dstz.bpm.api.engine.action.handler.ActionHandler;
/*     */ import com.dstz.bpm.api.engine.context.BpmContext;
/*     */ import com.dstz.bpm.api.exception.BpmStatusCode;
/*     */ import com.dstz.bpm.api.model.def.IBpmDefinition;
/*     */ import com.dstz.bpm.api.model.inst.BpmExecutionStack;
/*     */ import com.dstz.bpm.api.model.inst.IBpmInstance;
/*     */ import com.dstz.bus.api.model.IBusinessData;
/*     */ import com.dstz.form.api.model.FormCategory;
/*     */ import com.dstz.org.api.model.IUser;
/*     */ import com.dstz.org.api.service.UserService;
/*     */ import com.dstz.sys.api.model.SysIdentity;
/*     */ import com.dstz.sys.util.ContextUtil;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class BaseActionCmd
/*     */   implements ActionCmd
/*     */ {
/*  40 */   protected Map<String, Object> variable_ = new HashMap();
/*     */ 
/*     */ 
/*     */   
/*  44 */   protected Map<String, List<SysIdentity>> identityMap_ = new HashMap();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected JSONObject busData;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  55 */   protected IBpmDefinition bpmDefinition = null;
/*     */ 
/*     */ 
/*     */   
/*     */   protected String defId;
/*     */ 
/*     */   
/*  62 */   protected IBpmInstance bpmInstance = null;
/*     */ 
/*     */ 
/*     */   
/*     */   protected String instanceId;
/*     */ 
/*     */   
/*  69 */   protected Map<String, IBusinessData> bizDataMap = new HashMap();
/*     */ 
/*     */ 
/*     */   
/*     */   private String actionName;
/*     */ 
/*     */   
/*     */   private String businessKey;
/*     */ 
/*     */   
/*     */   private String dataMode;
/*     */ 
/*     */   
/*     */   private String opinion;
/*     */ 
/*     */   
/*     */   protected String destination;
/*     */ 
/*     */   
/*     */   protected String formId;
/*     */ 
/*     */   
/*     */   protected boolean isSource = false;
/*     */ 
/*     */   
/*     */   private BpmExecutionStack executionStack;
/*     */ 
/*     */   
/*     */   protected JSONObject extendConf;
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract void initSpecialParam(FlowRequestParam paramFlowRequestParam);
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract String getNodeId();
/*     */ 
/*     */ 
/*     */   
/*     */   public BaseActionCmd() {}
/*     */ 
/*     */ 
/*     */   
/*     */   public BaseActionCmd(FlowRequestParam flowParam) {
/* 114 */     this.isSource = true;
/* 115 */     setActionName(flowParam.getAction());
/*     */     
/* 117 */     setDefId(flowParam.getDefId());
/* 118 */     setInstanceId(flowParam.getInstanceId());
/* 119 */     setBusinessKey(flowParam.getBusinessKey());
/*     */ 
/*     */     
/* 122 */     initSpecialParam(flowParam);
/*     */     
/* 124 */     if (CollectionUtil.isNotEmpty(flowParam.getNodeUsers())) {
/* 125 */       handleUserSetting(flowParam.getNodeUsers());
/*     */     }
/*     */ 
/*     */     
/* 129 */     setBusData(flowParam.getData());
/*     */ 
/*     */     
/* 132 */     String formType = FormCategory.INNER.value();
/* 133 */     if (StringUtil.isNotEmpty(flowParam.getFormType())) {
/* 134 */       formType = flowParam.getFormType();
/*     */     }
/* 136 */     if (FormCategory.INNER.value().equals(formType)) {
/* 137 */       setDataMode("bo");
/*     */     } else {
/* 139 */       setDataMode("pk");
/*     */     } 
/*     */     
/* 142 */     setExtendConf(flowParam.getExtendConf());
/*     */     
/* 144 */     setOpinion(flowParam.getOpinion());
/*     */   }
/*     */   
/*     */   private void handleUserSetting(JSONObject jsonObject) {
/* 148 */     if (jsonObject.isEmpty())
/*     */       return; 
/* 150 */     Map<String, List<SysIdentity>> map = new HashMap<String, List<SysIdentity>>();
/*     */     
/* 152 */     Set<String> nodeIds = jsonObject.keySet();
/* 153 */     for (String nodeId : nodeIds) {
/* 154 */       JSONArray users = jsonObject.getJSONArray(nodeId);
/* 155 */       if (users == null || users.isEmpty())
/* 156 */         continue;  List<SysIdentity> userList = new ArrayList<SysIdentity>();
/*     */       
/* 158 */       for (Object userObj : users) {
/* 159 */         JSONObject user = (JSONObject)userObj;
/* 160 */         SysIdentity bpmInentity = (SysIdentity)JSON.toJavaObject(user, SysIdentity.class);
/* 161 */         if (StringUtil.isEmpty(bpmInentity.getId()))
/* 162 */           continue;  userList.add(bpmInentity);
/*     */       } 
/* 164 */       map.put(nodeId, userList);
/*     */     } 
/*     */     
/* 167 */     setBpmIdentities(map);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 176 */   public Map<String, Object> getActionVariables() { return this.variable_; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 185 */   public void setActionVariables(Map<String, Object> variables) { this.variable_ = variables; }
/*     */ 
/*     */ 
/*     */   
/* 189 */   public void setBpmIdentities(Map<String, List<SysIdentity>> map) { this.identityMap_ = map; }
/*     */ 
/*     */ 
/*     */   
/* 193 */   public void clearBpmIdentities() { this.identityMap_.clear(); }
/*     */ 
/*     */   
/*     */   public void addBpmIdentity(String key, SysIdentity bpmIdentity) {
/* 197 */     List<SysIdentity> list = (List)this.identityMap_.get(key);
/* 198 */     if (CollectionUtil.isEmpty(list)) {
/* 199 */       list = new ArrayList<SysIdentity>();
/* 200 */       list.add(bpmIdentity);
/* 201 */       this.identityMap_.put(key, list);
/*     */     } else {
/* 203 */       list.add(bpmIdentity);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addBpmIdentity(String key, List<SysIdentity> bpmIdentityList) {
/* 214 */     List<SysIdentity> list = (List)this.identityMap_.get(key);
/* 215 */     if (CollectionUtil.isEmpty(list)) {
/* 216 */       list = new ArrayList<SysIdentity>();
/* 217 */       list.addAll(bpmIdentityList);
/* 218 */       this.identityMap_.put(key, list);
/*     */     } else {
/* 220 */       list.addAll(bpmIdentityList);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setBpmIdentity(String key, List<SysIdentity> bpmIdentityList) {
/* 231 */     List<SysIdentity> list = (List)this.identityMap_.get(key);
/* 232 */     if (CollectionUtil.isEmpty(list)) {
/* 233 */       list = new ArrayList<SysIdentity>();
/* 234 */       list.addAll(bpmIdentityList);
/* 235 */       this.identityMap_.put(key, list);
/*     */     } else {
/* 237 */       list.clear();
/* 238 */       list.addAll(bpmIdentityList);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 244 */   public List<SysIdentity> getBpmIdentity(String nodeId) { return (List)this.identityMap_.get(nodeId); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 249 */   public Map<String, List<SysIdentity>> getBpmIdentities() { return this.identityMap_; }
/*     */ 
/*     */ 
/*     */   
/* 253 */   public boolean isSource() { return this.isSource; }
/*     */ 
/*     */ 
/*     */   
/* 257 */   public void setSource(boolean isSource) { this.isSource = isSource; }
/*     */ 
/*     */ 
/*     */   
/* 261 */   public JSONObject getBusData() { return this.busData; }
/*     */ 
/*     */ 
/*     */   
/* 265 */   public void setBusData(JSONObject busData) { this.busData = busData; }
/*     */ 
/*     */ 
/*     */   
/* 269 */   public IBpmInstance getBpmInstance() { return this.bpmInstance; }
/*     */ 
/*     */ 
/*     */   
/* 273 */   public void setBpmInstance(IBpmInstance bpmInstance) { this.bpmInstance = bpmInstance; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 279 */   public String getDataMode() { return this.dataMode; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 284 */   public void setDataMode(String mode) { this.dataMode = mode; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 289 */   public String getBusinessKey() { return this.businessKey; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 294 */   public String getFormId() { return this.formId; }
/*     */ 
/*     */ 
/*     */   
/* 298 */   public BpmExecutionStack getExecutionStack() { return this.executionStack; }
/*     */ 
/*     */ 
/*     */   
/* 302 */   public void setExecutionStack(BpmExecutionStack executionStack) { this.executionStack = executionStack; }
/*     */ 
/*     */   
/*     */   public String getInstanceId() {
/* 306 */     if (StringUtil.isEmpty(this.instanceId) && this.bpmInstance != null) {
/* 307 */       return this.bpmInstance.getId();
/*     */     }
/* 309 */     return this.instanceId;
/*     */   }
/*     */ 
/*     */   
/* 313 */   public void setInstanceId(String instanceId) { this.instanceId = instanceId; }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getDefId() {
/* 318 */     if (StringUtil.isEmpty(this.defId) && this.bpmInstance != null) {
/* 319 */       return this.bpmInstance.getDefId();
/*     */     }
/*     */     
/* 322 */     return this.defId;
/*     */   }
/*     */ 
/*     */   
/* 326 */   public JSONObject getExtendConf() { return this.extendConf; }
/*     */ 
/*     */ 
/*     */   
/* 330 */   public void setExtendConf(JSONObject extendConf) { this.extendConf = extendConf; }
/*     */ 
/*     */ 
/*     */   
/* 334 */   public void setDefId(String defId) { this.defId = defId; }
/*     */ 
/*     */ 
/*     */   
/* 338 */   public String getOpinion() { return this.opinion; }
/*     */ 
/*     */ 
/*     */   
/* 342 */   public void setOpinion(String opinion) { this.opinion = opinion; }
/*     */ 
/*     */ 
/*     */   
/* 346 */   public Map<String, IBusinessData> getBizDataMap() { return this.bizDataMap; }
/*     */ 
/*     */ 
/*     */   
/* 350 */   public void setBizDataMap(Map<String, IBusinessData> bizDataMap) { this.bizDataMap = bizDataMap; }
/*     */ 
/*     */ 
/*     */   
/* 354 */   public void setFormId(String formId) { this.formId = formId; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 359 */   public void setBusinessKey(String businessKey) { this.businessKey = businessKey; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCurAccount(String curAccount) {
/* 369 */     UserService userService = (UserService)AppUtil.getBean(UserService.class);
/* 370 */     IUser user = userService.getUserByAccount(curAccount);
/* 371 */     ContextUtil.setCurrentUser(user);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 376 */   public String getActionName() { return this.actionName; }
/*     */ 
/*     */   
/* 379 */   public ActionType getActionType() { return ActionType.fromKey(getActionName()); }
/*     */ 
/*     */ 
/*     */   
/* 383 */   public void setActionName(String actionName) { this.actionName = actionName; }
/*     */ 
/*     */ 
/*     */   
/* 387 */   public IBpmDefinition getBpmDefinition() { return this.bpmDefinition; }
/*     */ 
/*     */ 
/*     */   
/* 391 */   public void setBpmDefinition(IBpmDefinition bpmDefinition) { this.bpmDefinition = bpmDefinition; }
/*     */ 
/*     */ 
/*     */   
/* 395 */   public String getDestination() { return this.destination; }
/*     */ 
/*     */ 
/*     */   
/* 399 */   public void setDestination(String destination) { this.destination = destination; }
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean hasExecuted = false;
/*     */ 
/*     */   
/*     */   public String executeCmd() {
/* 407 */     if (this.hasExecuted) {
/* 408 */       throw new BusinessException("action cmd caonot be invoked twice", BpmStatusCode.NO_PERMISSION);
/*     */     }
/* 410 */     this.hasExecuted = true;
/*     */     
/* 412 */     ActionType actonType = ActionType.fromKey(getActionName());
/*     */     
/* 414 */     ActionHandler handler = (ActionHandler)AppUtil.getBean(actonType.getBeanId());
/* 415 */     if (handler == null) {
/* 416 */       throw new BusinessException("action beanId cannot be found :" + actonType.getName(), BpmStatusCode.NO_TASK_ACTION);
/*     */     }
/*     */     
/* 419 */     BpmContext.cleanTread();
/*     */     
/* 421 */     handler.execute(this);
/*     */     
/* 423 */     return handler.getActionType().getName();
/*     */   }
/*     */ }


/* Location:              D:\Repository\com\dstz\agilebpm\wf-api\1.5.1\wf-api-1.5.1.jar!\com\dstz\bpm\api\engine\action\cmd\BaseActionCmd.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.0
 */