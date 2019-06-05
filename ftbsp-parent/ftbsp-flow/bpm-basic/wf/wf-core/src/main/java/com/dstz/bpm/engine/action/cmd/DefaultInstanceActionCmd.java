/*    */ package com.dstz.bpm.engine.action.cmd;
/*    */ 
/*    */ import com.dstz.bpm.api.engine.action.cmd.BaseActionCmd;
/*    */ import com.dstz.bpm.api.engine.action.cmd.FlowRequestParam;
/*    */ import com.dstz.bpm.api.engine.action.cmd.InstanceActionCmd;
/*    */ import com.dstz.bpm.api.exception.BpmStatusCode;
/*    */ import com.dstz.bpm.api.exception.WorkFlowException;
/*    */ import java.util.Map;
/*    */ import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DefaultInstanceActionCmd
/*    */   extends BaseActionCmd
/*    */   implements InstanceActionCmd
/*    */ {
/*    */   protected ExecutionEntity ay;
/*    */   
/* 23 */   public DefaultInstanceActionCmd(FlowRequestParam flowParam) { super(flowParam); }
/*    */ 
/*    */ 
/*    */   
/*    */   public DefaultInstanceActionCmd() {}
/*    */ 
/*    */ 
/*    */   
/* 31 */   public String getFlowKey() { return getBpmDefinition().getKey(); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 36 */   public String getSubject() { return getBpmInstance().getSubject(); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 41 */   public ExecutionEntity getExecutionEntity() { return this.ay; }
/*    */ 
/*    */ 
/*    */   
/* 45 */   public void setExecutionEntity(ExecutionEntity executionEntity) { this.ay = executionEntity; }
/*    */ 
/*    */ 
/*    */   
/*    */   public Object getVariable(String variableName) {
/* 50 */     if (this.ay == null) {
/* 51 */       throw new WorkFlowException(BpmStatusCode.VARIABLES_NO_SYNC);
/*    */     }
/* 53 */     return this.ay.getVariable(variableName);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean hasVariable(String variableName) {
/* 58 */     if (this.ay == null) {
/* 59 */       throw new WorkFlowException(BpmStatusCode.VARIABLES_NO_SYNC);
/*    */     }
/* 61 */     return this.ay.hasVariable(variableName);
/*    */   }
/*    */ 
/*    */   
/*    */   public void removeVariable(String variableName) {
/* 66 */     if (this.ay == null) {
/* 67 */       throw new WorkFlowException(BpmStatusCode.VARIABLES_NO_SYNC);
/*    */     }
/*    */     
/* 70 */     this.ay.removeVariable(variableName);
/*    */   }
/*    */ 
/*    */   
/*    */   public void addVariable(String name, Object value) {
/* 75 */     if (this.ay == null) {
/* 76 */       throw new WorkFlowException(BpmStatusCode.VARIABLES_NO_SYNC);
/*    */     }
/* 78 */     this.ay.setVariable(name, value);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 83 */   public Map<String, Object> getVariables() { return this.ay.getVariables(); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void initSpecialParam(FlowRequestParam flowParam) {}
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getNodeId() {
/* 94 */     if (this.ay == null) {
/* 95 */       return null;
/*    */     }
/* 97 */     return this.ay.getActivityId();
/*    */   }
/*    */ }


/* Location:              D:\Repository\com\dstz\agilebpm\wf-core\1.5.1\wf-core-1.5.1-pg.jar!\com\dstz\bpm\engine\action\cmd\DefaultInstanceActionCmd.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.0
 */