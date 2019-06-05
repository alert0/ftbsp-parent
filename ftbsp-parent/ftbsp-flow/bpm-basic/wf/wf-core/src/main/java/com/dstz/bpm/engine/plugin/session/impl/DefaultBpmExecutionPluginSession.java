/*    */ package com.dstz.bpm.engine.plugin.session.impl;
/*    */ 
/*    */ import com.dstz.bpm.api.constant.ActionType;
/*    */ import com.dstz.bpm.api.constant.EventType;
/*    */ import com.dstz.bpm.api.engine.action.cmd.BaseActionCmd;
/*    */ import com.dstz.bpm.api.engine.context.BpmContext;
/*    */ import com.dstz.bpm.api.model.inst.IBpmInstance;
/*    */ import com.dstz.bpm.engine.action.cmd.DefualtTaskActionCmd;
/*    */ import com.dstz.bpm.engine.plugin.session.BpmExecutionPluginSession;
/*    */ import com.dstz.bus.api.model.IBusinessData;
/*    */ import com.dstz.sys.util.ContextUtil;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ import org.activiti.engine.delegate.VariableScope;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DefaultBpmExecutionPluginSession
/*    */   extends HashMap<String, Object>
/*    */   implements BpmExecutionPluginSession
/*    */ {
/*    */   private static final long serialVersionUID = 4225343560381914372L;
/*    */   private Map<String, IBusinessData> cb;
/*    */   private EventType cc;
/*    */   private VariableScope variableScope;
/*    */   private IBpmInstance bpmInstance;
/*    */   
/*    */   public DefaultBpmExecutionPluginSession() {
/* 32 */     BaseActionCmd cmd = (BaseActionCmd)BpmContext.getActionModel();
/* 33 */     ActionType actionType = cmd.getActionType();
/* 34 */     put("actionCmd", cmd);
/* 35 */     put("submitActionDesc", actionType.getName());
/* 36 */     put("submitActionName", actionType.getKey());
/* 37 */     put("currentUser", ContextUtil.getCurrentUser());
/*    */     
/* 39 */     if (cmd instanceof DefualtTaskActionCmd) {
/* 40 */       DefualtTaskActionCmd taskCmd = (DefualtTaskActionCmd)cmd;
/* 41 */       put("isTask", Boolean.valueOf(true));
/* 42 */       put("submitOpinion", taskCmd.getOpinion());
/* 43 */       put("submitTaskName", taskCmd.getBpmTask().getName());
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 49 */   public Map<String, IBusinessData> getBoDatas() { return this.cb; }
/*    */ 
/*    */   
/*    */   public void setBoDatas(Map<String, IBusinessData> boDatas) {
/* 53 */     putAll(boDatas);
/* 54 */     this.cb = boDatas;
/*    */   }
/*    */ 
/*    */   
/* 58 */   public IBpmInstance getBpmInstance() { return this.bpmInstance; }
/*    */ 
/*    */   
/*    */   public void setBpmInstance(IBpmInstance bpmInstance) {
/* 62 */     put("bpmInstance", bpmInstance);
/* 63 */     this.bpmInstance = bpmInstance;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 68 */   public EventType getEventType() { return this.cc; }
/*    */ 
/*    */   
/*    */   public void setEventType(EventType eventType) {
/* 72 */     put("eventType", eventType.getKey());
/* 73 */     this.cc = eventType;
/*    */   }
/*    */ 
/*    */   
/* 77 */   public VariableScope getVariableScope() { return this.variableScope; }
/*    */ 
/*    */   
/*    */   public void setVariableScope(VariableScope variableScope) {
/* 81 */     put("variableScope", variableScope);
/* 82 */     this.variableScope = variableScope;
/*    */   }
/*    */ }


/* Location:              D:\Repository\com\dstz\agilebpm\wf-core\1.5.1\wf-core-1.5.1-pg.jar!\com\dstz\bpm\engine\plugin\session\impl\DefaultBpmExecutionPluginSession.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.0
 */