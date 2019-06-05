/*    */ package com.dstz.bpm.engine.action.handler.task;
/*    */ 
/*    */ import com.dstz.bpm.api.constant.ActionType;
/*    */ import com.dstz.bpm.api.model.def.NodeProperties;
/*    */ import com.dstz.bpm.api.model.nodedef.BpmNodeDef;
/*    */ import com.dstz.bpm.engine.action.cmd.DefualtTaskActionCmd;
/*    */ import java.util.List;
/*    */ import org.springframework.stereotype.Component;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Component
/*    */ public abstract class TaskReject2StartActionHandler
/*    */   extends TaskRejectActionHandler
/*    */ {
/*    */   protected String a(DefualtTaskActionCmd actionModel, NodeProperties nodeProperties) {
/* 32 */     List<BpmNodeDef> nodeDefs = this.a.getStartNodes(actionModel.getDefId());
/*    */     
/* 34 */     if (nodeDefs.size() > 1);
/*    */ 
/*    */ 
/*    */     
/* 38 */     return ((BpmNodeDef)nodeDefs.get(0)).getNodeId();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 45 */   public ActionType getActionType() { return ActionType.REJECT2START; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 50 */   public int getSn() { return 4; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 55 */   public Boolean isDefault() { return Boolean.valueOf(false); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 60 */   public String getConfigPage() { return "/bpm/task/taskOpinionDialog.html"; }
/*    */ }


/* Location:              D:\Repository\com\dstz\agilebpm\wf-core\1.5.1\wf-core-1.5.1-pg.jar!\com\dstz\bpm\engine\action\handler\task\TaskReject2StartActionHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.0
 */