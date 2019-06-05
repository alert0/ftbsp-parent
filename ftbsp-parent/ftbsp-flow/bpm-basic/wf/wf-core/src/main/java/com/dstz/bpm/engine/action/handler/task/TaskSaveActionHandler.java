/*    */ package com.dstz.bpm.engine.action.handler.task;
/*    */ 
/*    */ import com.dstz.bpm.api.constant.ActionType;
/*    */ import com.dstz.bpm.api.engine.action.cmd.BaseActionCmd;
/*    */ import com.dstz.bpm.engine.action.cmd.DefualtTaskActionCmd;
/*    */ import org.springframework.stereotype.Component;
/*    */ 
/*    */ @Component
/*    */ public abstract class TaskSaveActionHandler
/*    */   extends AbstractTaskActionHandler<DefualtTaskActionCmd>
/*    */ {
/* 12 */   public ActionType getActionType() { return ActionType.SAVE; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void e(DefualtTaskActionCmd actionModel) {}
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
/* 32 */   public int getSn() { return 1; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 38 */   public String getConfigPage() { return ""; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 43 */   public Boolean isDefault() { return Boolean.valueOf(true); }
/*    */ }


/* Location:              D:\Repository\com\dstz\agilebpm\wf-core\1.5.1\wf-core-1.5.1-pg.jar!\com\dstz\bpm\engine\action\handler\task\TaskSaveActionHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.0
 */