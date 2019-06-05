/*    */ package com.dstz.bpm.engine.action.handler.task;
/*    */ 
/*    */ import com.dstz.bpm.api.constant.ActionType;
/*    */ import com.dstz.bpm.api.engine.action.cmd.BaseActionCmd;
/*    */ import com.dstz.bpm.engine.action.cmd.DefualtTaskActionCmd;
/*    */ import org.springframework.stereotype.Component;
/*    */ 
/*    */ 
/*    */ 
/*    */ @Component
/*    */ public abstract class TaskAgreeActionHandler
/*    */   extends AbstractTaskActionHandler<DefualtTaskActionCmd>
/*    */ {
/* 14 */   public ActionType getActionType() { return ActionType.AGREE; }
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
/* 25 */   public void g(DefualtTaskActionCmd actionModel) { i(actionModel); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 30 */   public int getSn() { return 1; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 36 */   public String getConfigPage() { return "/bpm/task/taskOpinionDialog.html"; }
/*    */ }


/* Location:              D:\Repository\com\dstz\agilebpm\wf-core\1.5.1\wf-core-1.5.1-pg.jar!\com\dstz\bpm\engine\action\handler\task\TaskAgreeActionHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.0
 */