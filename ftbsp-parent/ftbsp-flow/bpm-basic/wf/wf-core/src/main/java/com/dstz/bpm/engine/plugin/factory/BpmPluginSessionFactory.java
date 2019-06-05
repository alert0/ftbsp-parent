/*    */ package com.dstz.bpm.engine.plugin.factory;
/*    */ 
/*    */ import com.dstz.bpm.api.constant.EventType;
/*    */ import com.dstz.bpm.api.engine.action.cmd.ActionCmd;
/*    */ import com.dstz.bpm.api.engine.action.cmd.InstanceActionCmd;
/*    */ import com.dstz.bpm.api.engine.action.cmd.TaskActionCmd;
/*    */ import com.dstz.bpm.api.engine.context.BpmContext;
/*    */ import com.dstz.bpm.engine.action.cmd.DefaultInstanceActionCmd;
/*    */ import com.dstz.bpm.engine.action.cmd.DefualtTaskActionCmd;
/*    */ import com.dstz.bpm.engine.plugin.session.BpmExecutionPluginSession;
/*    */ import com.dstz.bpm.engine.plugin.session.BpmPluginSession;
/*    */ import com.dstz.bpm.engine.plugin.session.BpmUserCalcPluginSession;
/*    */ import com.dstz.bpm.engine.plugin.session.impl.DefaultBpmExecutionPluginSession;
/*    */ import com.dstz.bpm.engine.plugin.session.impl.DefaultBpmTaskPluginSession;
/*    */ import com.dstz.bpm.engine.plugin.session.impl.DefaultBpmUserCalcPluginSession;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BpmPluginSessionFactory
/*    */ {
/*    */   public static BpmExecutionPluginSession buildExecutionPluginSession(InstanceActionCmd actionModel, EventType eventType) {
/* 23 */     DefaultBpmExecutionPluginSession executionPluginSession = new DefaultBpmExecutionPluginSession();
/* 24 */     executionPluginSession.setBoDatas(actionModel.getBizDataMap());
/* 25 */     executionPluginSession.setBpmInstance(actionModel.getBpmInstance());
/* 26 */     executionPluginSession.setEventType(eventType);
/* 27 */     executionPluginSession.setVariableScope(((DefaultInstanceActionCmd)actionModel).getExecutionEntity());
/* 28 */     return executionPluginSession;
/*    */   }
/*    */   
/*    */   public static BpmExecutionPluginSession buildTaskPluginSession(TaskActionCmd actionModel, EventType eventType) {
/* 32 */     DefualtTaskActionCmd taskActionModel = (DefualtTaskActionCmd)actionModel;
/*    */     
/* 34 */     DefaultBpmTaskPluginSession session = new DefaultBpmTaskPluginSession();
/* 35 */     session.setBoDatas(actionModel.getBizDataMap());
/* 36 */     session.setBpmInstance(actionModel.getBpmInstance());
/* 37 */     session.setEventType(eventType);
/* 38 */     session.setVariableScope(taskActionModel.getDelagateTask());
/* 39 */     session.setBpmTask(taskActionModel.getBpmTask());
/* 40 */     return session;
/*    */   }
/*    */ 
/*    */   
/*    */   public static DefaultBpmExecutionPluginSession buildExecutionPluginSession(TaskActionCmd actionModel, EventType eventType) {
/* 45 */     DefualtTaskActionCmd taskActionModel = (DefualtTaskActionCmd)actionModel;
/*    */     
/* 47 */     DefaultBpmExecutionPluginSession executionPluginSession = new DefaultBpmExecutionPluginSession();
/*    */     
/* 49 */     executionPluginSession.setBoDatas(actionModel.getBizDataMap());
/* 50 */     executionPluginSession.setBpmInstance(actionModel.getBpmInstance());
/* 51 */     executionPluginSession.setVariableScope(taskActionModel.getDelagateTask());
/* 52 */     executionPluginSession.setEventType(eventType);
/* 53 */     executionPluginSession.put("bpmTask", actionModel.getBpmTask());
/* 54 */     return executionPluginSession;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static BpmUserCalcPluginSession buildBpmUserCalcPluginSession(BpmPluginSession pluginSession) {
/* 64 */     DefaultBpmExecutionPluginSession plugin = (DefaultBpmExecutionPluginSession)pluginSession;
/*    */     
/* 66 */     DefaultBpmUserCalcPluginSession userCalcPluginSession = new DefaultBpmUserCalcPluginSession();
/* 67 */     userCalcPluginSession.setBoDatas(pluginSession.getBoDatas());
/* 68 */     userCalcPluginSession.setVariableScope(plugin.getVariableScope());
/*    */     
/* 70 */     if (pluginSession instanceof DefaultBpmTaskPluginSession) {
/* 71 */       DefaultBpmTaskPluginSession taskSession = (DefaultBpmTaskPluginSession)pluginSession;
/*    */       
/* 73 */       userCalcPluginSession.setBpmTask(taskSession.getBpmTask());
/*    */     } 
/* 75 */     ActionCmd action = BpmContext.getActionModel();
/* 76 */     if (action != null && action instanceof TaskActionCmd) {
/*    */       
/* 78 */       TaskActionCmd taskCmd = (TaskActionCmd)action;
/* 79 */       userCalcPluginSession.setBpmTask(taskCmd.getBpmTask());
/*    */     } 
/*    */     
/* 82 */     return userCalcPluginSession;
/*    */   }
/*    */ }


/* Location:              D:\Repository\com\dstz\agilebpm\wf-core\1.5.1\wf-core-1.5.1-pg.jar!\com\dstz\bpm\engine\plugin\factory\BpmPluginSessionFactory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.0
 */