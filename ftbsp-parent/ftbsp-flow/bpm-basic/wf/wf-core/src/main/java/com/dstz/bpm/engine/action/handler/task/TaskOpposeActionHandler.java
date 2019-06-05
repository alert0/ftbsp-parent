/*    */ package com.dstz.bpm.engine.action.handler.task;
/*    */ 
/*    */ import com.dstz.bpm.api.constant.ActionType;
/*    */ import com.dstz.bpm.api.constant.NodeType;
/*    */ import com.dstz.bpm.api.engine.action.cmd.BaseActionCmd;
/*    */ import com.dstz.bpm.api.model.nodedef.BpmNodeDef;
/*    */ import com.dstz.bpm.engine.action.cmd.DefualtTaskActionCmd;
/*    */ import org.springframework.stereotype.Component;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Component
/*    */ public abstract class TaskOpposeActionHandler
/*    */   extends AbstractTaskActionHandler<DefualtTaskActionCmd>
/*    */ {
/* 19 */   public ActionType getActionType() { return ActionType.OPPOSE; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void h(DefualtTaskActionCmd actionModel) {}
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void g(DefualtTaskActionCmd actionModel) {}
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 35 */   public int getSn() { return 2; }
/*    */ 
/*    */ 
/*    */   
/*    */   public Boolean isSupport(BpmNodeDef nodeDef) {
/* 40 */     NodeType nodeType = nodeDef.getType();
/*    */     
/* 42 */     if (nodeType == NodeType.USERTASK || nodeType == NodeType.SIGNTASK) {
/* 43 */       return Boolean.valueOf(true);
/*    */     }
/*    */     
/* 46 */     return Boolean.valueOf(false);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 51 */   public String getConfigPage() { return "/bpm/task/taskOpinionDialog.html"; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 56 */   public Boolean isDefault() { return Boolean.valueOf(false); }
/*    */ }


/* Location:              D:\Repository\com\dstz\agilebpm\wf-core\1.5.1\wf-core-1.5.1-pg.jar!\com\dstz\bpm\engine\action\handler\task\TaskOpposeActionHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.0
 */