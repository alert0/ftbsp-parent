/*    */ package com.dstz.bpm.act.cmd;
/*    */ 
/*    */ import org.activiti.engine.impl.interceptor.Command;
/*    */ import org.activiti.engine.impl.interceptor.CommandContext;
/*    */ import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
/*    */ 
/*    */ public class ProcessInstanceEndCmd extends Object implements Command<Void> {
/*  8 */   private String processInstanceId = null;
/*    */ 
/*    */   
/* 11 */   public ProcessInstanceEndCmd(String processInstanceId) { this.processInstanceId = processInstanceId; }
/*    */ 
/*    */ 
/*    */   
/*    */   public Void execute(CommandContext cmdContext) {
/* 16 */     ExecutionEntity executionEntity = cmdContext.getExecutionEntityManager().findExecutionById(this.processInstanceId);
/* 17 */     ExecutionEntity parentEnt = a(executionEntity);
/* 18 */     parentEnt.end();
/* 19 */     return null;
/*    */   }
/*    */   
/*    */   private ExecutionEntity a(ExecutionEntity executionEntity) {
/* 23 */     ExecutionEntity parentEnt = executionEntity.getParent();
/* 24 */     if (parentEnt == null) {
/* 25 */       return executionEntity;
/*    */     }
/* 27 */     return a(parentEnt);
/*    */   }
/*    */ }


/* Location:              D:\Repository\com\dstz\agilebpm\wf-act\1.5.1\wf-act-1.5.1-pg.jar!\com\dstz\bpm\act\cmd\ProcessInstanceEndCmd.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.0
 */