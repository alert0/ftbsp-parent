/*    */ package com.dstz.bpm.engine.action.handler.task;
/*    */ 
/*    */ import com.dstz.base.api.exception.BusinessMessage;
/*    */ import com.dstz.bpm.api.constant.ActionType;
/*    */ import com.dstz.bpm.api.constant.TaskStatus;
/*    */ import com.dstz.bpm.api.engine.action.cmd.ActionCmd;
/*    */ import com.dstz.bpm.api.engine.action.cmd.BaseActionCmd;
/*    */ import com.dstz.bpm.core.manager.BpmTaskManager;
/*    */ import com.dstz.bpm.core.model.BpmTask;
/*    */ import com.dstz.bpm.engine.action.cmd.DefualtTaskActionCmd;
/*    */ import javax.annotation.Resource;
/*    */ import org.springframework.stereotype.Component;
/*    */ 
/*    */ @Component
/*    */ public abstract class TaskUnlockActionHandler extends AbstractTaskActionHandler<DefualtTaskActionCmd> {
/*    */   @Resource
/*    */   BpmTaskManager aN;
/*    */   
/* 19 */   public ActionType getActionType() { return ActionType.UNLOCK; }
/*    */ 
/*    */ 
/*    */   
/*    */   public void a(DefualtTaskActionCmd model) {
/* 24 */     f(model);
/* 25 */     k(model);
/*    */     
/* 27 */     BpmTask task = (BpmTask)model.getBpmTask();
/* 28 */     if (!task.getStatus().equals(TaskStatus.LOCK.getKey())) {
/* 29 */       throw new BusinessMessage("该任务并非锁定状态,或已经被解锁，解锁失败");
/*    */     }
/*    */     
/* 32 */     this.aN.unLockTask(task.getId());
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
/* 57 */   public String getDefaultGroovyScript() { return "return task.getStatus().equals(\"LOCK\");"; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 62 */   public String getConfigPage() { return ""; }
/*    */ }


/* Location:              D:\Repository\com\dstz\agilebpm\wf-core\1.5.1\wf-core-1.5.1-pg.jar!\com\dstz\bpm\engine\action\handler\task\TaskUnlockActionHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.0
 */