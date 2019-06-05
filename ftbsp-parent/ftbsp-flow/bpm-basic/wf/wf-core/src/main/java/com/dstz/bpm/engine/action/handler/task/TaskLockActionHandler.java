/*    */ package com.dstz.bpm.engine.action.handler.task;
/*    */ 
/*    */ import com.dstz.base.api.exception.BusinessMessage;
/*    */ import com.dstz.bpm.api.constant.ActionType;
/*    */ import com.dstz.bpm.api.constant.TaskStatus;
/*    */ import com.dstz.bpm.api.engine.action.cmd.ActionCmd;
/*    */ import com.dstz.bpm.api.engine.action.cmd.BaseActionCmd;
/*    */ import com.dstz.bpm.core.model.BpmTask;
/*    */ import com.dstz.bpm.engine.action.cmd.DefualtTaskActionCmd;
/*    */ import com.dstz.sys.util.ContextUtil;
/*    */ import org.springframework.stereotype.Component;
/*    */ 
/*    */ @Component
/*    */ public abstract class TaskLockActionHandler
/*    */   extends AbstractTaskActionHandler<DefualtTaskActionCmd> {
/* 16 */   public ActionType getActionType() { return ActionType.LOCK; }
/*    */ 
/*    */ 
/*    */   
/*    */   public void a(DefualtTaskActionCmd model) {
/* 21 */     f(model);
/* 22 */     k(model);
/*    */     
/* 24 */     BpmTask task = (BpmTask)model.getBpmTask();
/* 25 */     if (!task.getAssigneeId().equals("0")) {
/* 26 */       throw new BusinessMessage("该任务只有一个候选人没有锁定的必要。");
/*    */     }
/*    */     
/* 29 */     task.setAssigneeId(ContextUtil.getCurrentUserId());
/* 30 */     task.setAssigneeNames(ContextUtil.getCurrentUser().getFullname());
/* 31 */     task.setStatus(TaskStatus.LOCK.getKey());
/* 32 */     this.aJ.update(task);
/*    */   }
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
/* 47 */   public int getSn() { return 6; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 52 */   public Boolean isDefault() { return Boolean.valueOf(false); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 57 */   public String getDefaultGroovyScript() { return "return task.getAssigneeId().equals(\"0\")"; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 62 */   public String getConfigPage() { return ""; }
/*    */ }


/* Location:              D:\Repository\com\dstz\agilebpm\wf-core\1.5.1\wf-core-1.5.1-pg.jar!\com\dstz\bpm\engine\action\handler\task\TaskLockActionHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.0
 */