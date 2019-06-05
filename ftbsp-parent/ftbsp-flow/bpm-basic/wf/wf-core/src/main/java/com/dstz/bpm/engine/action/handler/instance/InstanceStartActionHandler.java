/*    */ package com.dstz.bpm.engine.action.handler.instance;
/*    */ 
/*    */ import com.dstz.base.core.util.StringUtil;
/*    */ import com.dstz.bpm.act.service.ActInstanceService;
/*    */ import com.dstz.bpm.api.constant.ActionType;
/*    */ import com.dstz.bpm.api.constant.NodeType;
/*    */ import com.dstz.bpm.api.engine.action.cmd.BaseActionCmd;
/*    */ import com.dstz.bpm.api.model.nodedef.BpmNodeDef;
/*    */ import com.dstz.bpm.api.service.BpmProcessDefService;
/*    */ import com.dstz.bpm.core.manager.BpmInstanceManager;
/*    */ import com.dstz.bpm.core.model.BpmInstance;
/*    */ import com.dstz.bpm.engine.action.cmd.DefaultInstanceActionCmd;
/*    */ import javax.annotation.Resource;
/*    */ import org.springframework.stereotype.Component;
/*    */ 
/*    */ 
/*    */ 
/*    */ @Component
/*    */ public abstract class InstanceStartActionHandler
/*    */   extends InstanceSaveActionHandler
/*    */ {
/*    */   @Resource
/*    */   BpmInstanceManager f;
/*    */   @Resource
/*    */   ActInstanceService i;
/*    */   @Resource
/*    */   BpmProcessDefService a;
/*    */   
/*    */   public void b(DefaultInstanceActionCmd startActionModel) {
/* 30 */     String destination = startActionModel.getDestination();
/*    */     
/* 32 */     BpmInstance instance = (BpmInstance)startActionModel.getBpmInstance();
/*    */     
/* 34 */     String actInstId = null;
/* 35 */     if (StringUtil.isEmpty(destination)) {
/*    */       
/* 37 */       actInstId = this.i.startProcessInstance(instance.getActDefId(), instance.getBizKey(), startActionModel.getActionVariables());
/*    */     } else {
/*    */       
/* 40 */       actInstId = this.i.startProcessInstance(instance.getActDefId(), instance.getBizKey(), startActionModel.getActionVariables(), new String[] { destination });
/*    */     } 
/*    */     
/* 43 */     instance.setActInstId(actInstId);
/* 44 */     f(startActionModel);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void e(DefaultInstanceActionCmd actionModel) {}
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 55 */   public int getSn() { return 1; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 60 */   public ActionType getActionType() { return ActionType.START; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 65 */   public Boolean isSupport(BpmNodeDef nodeDef) { return Boolean.valueOf((nodeDef.getType() == NodeType.START)); }
/*    */ }


/* Location:              D:\Repository\com\dstz\agilebpm\wf-core\1.5.1\wf-core-1.5.1-pg.jar!\com\dstz\bpm\engine\action\handler\instance\InstanceStartActionHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.0
 */