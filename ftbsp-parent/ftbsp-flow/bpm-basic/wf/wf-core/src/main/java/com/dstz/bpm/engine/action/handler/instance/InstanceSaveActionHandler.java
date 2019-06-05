/*     */ package com.dstz.bpm.engine.action.handler.instance;
/*     */ 
/*     */ import com.dstz.base.api.exception.BusinessException;
/*     */ import com.dstz.base.core.util.StringUtil;
/*     */ import com.dstz.bpm.api.constant.ActionType;
/*     */ import com.dstz.bpm.api.constant.InstanceStatus;
/*     */ import com.dstz.bpm.api.constant.NodeType;
/*     */ import com.dstz.bpm.api.engine.action.cmd.BaseActionCmd;
/*     */ import com.dstz.bpm.api.model.def.IBpmDefinition;
/*     */ import com.dstz.bpm.api.model.nodedef.BpmNodeDef;
/*     */ import com.dstz.bpm.core.model.BpmInstance;
/*     */ import com.dstz.bpm.engine.action.cmd.DefaultInstanceActionCmd;
/*     */ import com.dstz.bpm.engine.action.handler.AbsActionHandler;
/*     */ import org.springframework.stereotype.Component;
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
/*     */ @Component
/*     */ public abstract class InstanceSaveActionHandler
/*     */   extends AbsActionHandler<DefaultInstanceActionCmd>
/*     */ {
/*     */   protected void b(DefaultInstanceActionCmd model) {
/*  35 */     BpmInstance instance = (BpmInstance)model.getBpmInstance();
/*  36 */     instance.setStatus(InstanceStatus.STATUS_DRAFT.getKey());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void d(DefaultInstanceActionCmd actionModel) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void c(DefaultInstanceActionCmd data) {
/*  48 */     data.setBpmDefinition(this.a.getDefinitionById(data.getDefId()));
/*     */     
/*  50 */     g(data);
/*     */     
/*  52 */     l(data);
/*     */     
/*  54 */     a(data, this.a.getStartEvent(data.getDefId()));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  61 */   protected void e(DefaultInstanceActionCmd actionModel) { f(actionModel); }
/*     */ 
/*     */   
/*     */   protected void f(DefaultInstanceActionCmd actionModel) {
/*  65 */     BpmInstance instance = (BpmInstance)actionModel.getBpmInstance();
/*     */     
/*  67 */     if (instance.hasCreate().booleanValue()) {
/*  68 */       this.f.update(instance);
/*     */     } else {
/*  70 */       this.f.create(instance);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void g(DefaultInstanceActionCmd intanceCmdData) {
/*  75 */     String instId = intanceCmdData.getInstanceId();
/*     */     
/*  77 */     BpmInstance instance = null;
/*  78 */     if (StringUtil.isNotEmpty(instId)) {
/*  79 */       instance = (BpmInstance)this.f.get(instId);
/*  80 */       if (StringUtil.isNotEmpty(instance.getActInstId())) {
/*  81 */         throw new BusinessException("草稿已经启动，请勿多次启动该草稿！");
/*     */       }
/*     */     } 
/*     */     
/*  85 */     if (instance == null) {
/*  86 */       IBpmDefinition bpmDefinition = intanceCmdData.getBpmDefinition();
/*  87 */       instance = this.f.genInstanceByDefinition(bpmDefinition);
/*     */     } 
/*     */     
/*  90 */     intanceCmdData.setBpmInstance(instance);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  98 */   public ActionType getActionType() { return ActionType.DRAFT; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 104 */   public int getSn() { return 2; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Boolean isSupport(BpmNodeDef nodeDef) {
/* 110 */     if (nodeDef.getType() == NodeType.START) {
/* 111 */       return Boolean.valueOf(true);
/*     */     }
/* 113 */     return Boolean.valueOf(false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 119 */   public String getConfigPage() { return null; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 126 */   public String getDefaultGroovyScript() { return ""; }
/*     */ }


/* Location:              D:\Repository\com\dstz\agilebpm\wf-core\1.5.1\wf-core-1.5.1-pg.jar!\com\dstz\bpm\engine\action\handler\instance\InstanceSaveActionHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.0
 */