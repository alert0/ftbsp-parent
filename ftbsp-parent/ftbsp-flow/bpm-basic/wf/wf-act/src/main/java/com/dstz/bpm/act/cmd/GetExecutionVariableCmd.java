/*    */ package com.dstz.bpm.act.cmd;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import org.activiti.engine.ActivitiIllegalArgumentException;
/*    */ import org.activiti.engine.impl.interceptor.Command;
/*    */ import org.activiti.engine.impl.interceptor.CommandContext;
/*    */ import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
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
/*    */ public class GetExecutionVariableCmd
/*    */   extends Object
/*    */   implements Serializable, Command<Object>
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   protected String executionId;
/*    */   protected String variableName;
/*    */   protected boolean isLocal;
/*    */   
/*    */   public GetExecutionVariableCmd(String executionId, String variableName, boolean isLocal) {
/* 34 */     this.executionId = executionId;
/* 35 */     this.variableName = variableName;
/* 36 */     this.isLocal = isLocal;
/*    */   }
/*    */   public Object execute(CommandContext commandContext) {
/*    */     Object value;
/* 40 */     if (this.executionId == null) {
/* 41 */       throw new ActivitiIllegalArgumentException("executionId is null");
/*    */     }
/* 43 */     if (this.variableName == null) {
/* 44 */       throw new ActivitiIllegalArgumentException("variableName is null");
/*    */     }
/*    */ 
/*    */ 
/*    */     
/* 49 */     ExecutionEntity execution = commandContext.getExecutionEntityManager().findExecutionById(this.executionId);
/*    */     
/* 51 */     if (execution == null)
/*    */     {
/* 53 */       return null;
/*    */     }
/*    */ 
/*    */ 
/*    */     
/* 58 */     if (this.isLocal) {
/* 59 */       value = execution.getVariableLocal(this.variableName);
/*    */     } else {
/* 61 */       value = execution.getVariable(this.variableName);
/*    */     } 
/*    */     
/* 64 */     return value;
/*    */   }
/*    */ }


/* Location:              D:\Repository\com\dstz\agilebpm\wf-act\1.5.1\wf-act-1.5.1-pg.jar!\com\dstz\bpm\act\cmd\GetExecutionVariableCmd.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.0
 */