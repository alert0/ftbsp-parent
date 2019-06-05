/*    */ package com.dstz.bpm.plugin.usercalc.approver.context;
/*    */ 
/*    */ import com.alibaba.fastjson.JSONObject;
/*    */ import com.dstz.bpm.api.engine.plugin.def.BpmUserCalcPluginDef;
/*    */ import com.dstz.bpm.api.engine.plugin.runtime.RunTimePlugin;
/*    */ import com.dstz.bpm.engine.plugin.context.AbstractUserCalcPluginContext;
/*    */ import com.dstz.bpm.plugin.usercalc.approver.def.ApproverPluginDef;
/*    */ import org.springframework.context.annotation.Scope;
/*    */ import org.springframework.stereotype.Component;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Component
/*    */ @Scope("prototype")
/*    */ public abstract class ApproverPluginContext
/*    */   extends AbstractUserCalcPluginContext<ApproverPluginDef>
/*    */ {
/*    */   private static final long serialVersionUID = 2164348894650802414L;
/*    */   
/* 26 */   public String getDescription() { return "流程历史审批人"; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 31 */   public String getTitle() { return "流程历史审批人"; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 36 */   public Class<? extends RunTimePlugin> getPluginClass() { return com.dstz.bpm.plugin.usercalc.approver.executer.ApproverPluginExecutor.class; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 41 */   protected ApproverPluginDef a(JSONObject pluginJson) { return new ApproverPluginDef(); }
/*    */ }


/* Location:              D:\Repository\com\dstz\agilebpm\wf-plugin\1.5.1\wf-plugin-1.5.1-pg.jar!\com\dstz\bpm\plugi\\usercalc\approver\context\ApproverPluginContext.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.0
 */