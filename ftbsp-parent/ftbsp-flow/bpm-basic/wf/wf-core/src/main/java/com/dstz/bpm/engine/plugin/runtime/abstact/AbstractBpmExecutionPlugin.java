/*    */ package com.dstz.bpm.engine.plugin.runtime.abstact;
/*    */ 
/*    */ import com.dstz.bpm.api.engine.action.cmd.ActionCmd;
/*    */ import com.dstz.bpm.api.engine.action.cmd.TaskActionCmd;
/*    */ import com.dstz.bpm.api.engine.context.BpmContext;
/*    */ import com.dstz.bpm.api.engine.plugin.def.BpmExecutionPluginDef;
/*    */ import com.dstz.bpm.api.model.task.IBpmTask;
/*    */ import com.dstz.bpm.engine.plugin.runtime.BpmExecutionPlugin;
/*    */ import com.dstz.bpm.engine.plugin.session.BpmExecutionPluginSession;
/*    */ import org.activiti.engine.delegate.VariableScope;
/*    */ import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
/*    */ import org.activiti.engine.impl.persistence.entity.TaskEntity;
/*    */ import org.slf4j.Logger;
/*    */ import org.slf4j.LoggerFactory;
/*    */ 
/*    */ 
/*    */ public abstract class AbstractBpmExecutionPlugin<S extends BpmExecutionPluginSession, M extends BpmExecutionPluginDef>
/*    */   extends Object
/*    */   implements BpmExecutionPlugin<S, M>
/*    */ {
/* 21 */   protected Logger LOG = LoggerFactory.getLogger(getClass());
/*    */   
/*    */   protected boolean isTask() {
/* 24 */     ActionCmd actionCmd = BpmContext.getActionModel();
/*    */     
/* 26 */     if (actionCmd instanceof TaskActionCmd) {
/* 27 */       return true;
/*    */     }
/*    */     
/* 30 */     return false;
/*    */   }
/*    */   
/*    */   protected IBpmTask getTaskIfTask() {
/* 34 */     ActionCmd actionCmd = BpmContext.getActionModel();
/*    */     
/* 36 */     if (actionCmd instanceof TaskActionCmd) {
/* 37 */       TaskActionCmd taskCmd = (TaskActionCmd)actionCmd;
/* 38 */       return taskCmd.getBpmTask();
/*    */     } 
/*    */     
/* 41 */     return null;
/*    */   }
/*    */   
/*    */   protected String getActivitiId(BpmExecutionPluginSession session) {
/* 45 */     VariableScope scope = session.getVariableScope();
/*    */ 
/*    */     
/* 48 */     if (scope instanceof ExecutionEntity) {
/* 49 */       ExecutionEntity execution = (ExecutionEntity)scope;
/* 50 */       return execution.getActivityId();
/* 51 */     }  if (scope instanceof TaskEntity) {
/* 52 */       TaskEntity task = (TaskEntity)scope;
/* 53 */       return task.getTaskDefinitionKey();
/*    */     } 
/*    */     
/* 56 */     return null;
/*    */   }
/*    */ }


/* Location:              D:\Repository\com\dstz\agilebpm\wf-core\1.5.1\wf-core-1.5.1-pg.jar!\com\dstz\bpm\engine\plugin\runtime\abstact\AbstractBpmExecutionPlugin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.0
 */