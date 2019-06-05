/*    */ package com.dstz.bpm.plugin.usercalc.script.context;
/*    */ 
/*    */ import com.alibaba.fastjson.JSONObject;
/*    */ import com.dstz.base.core.util.JsonUtil;
/*    */ import com.dstz.bpm.api.engine.plugin.def.BpmUserCalcPluginDef;
/*    */ import com.dstz.bpm.api.engine.plugin.runtime.RunTimePlugin;
/*    */ import com.dstz.bpm.engine.plugin.context.AbstractUserCalcPluginContext;
/*    */ import com.dstz.bpm.plugin.usercalc.script.def.ScriptPluginDef;
/*    */ import org.springframework.context.annotation.Scope;
/*    */ import org.springframework.stereotype.Component;
/*    */ 
/*    */ 
/*    */ 
/*    */ @Component
/*    */ @Scope("prototype")
/*    */ public abstract class ScriptPluginContext
/*    */   extends AbstractUserCalcPluginContext<ScriptPluginDef>
/*    */ {
/*    */   private static final long serialVersionUID = -2353875054502587417L;
/*    */   
/*    */   public String getDescription() {
/* 22 */     ScriptPluginDef def = (ScriptPluginDef)getBpmPluginDef();
/* 23 */     if (def == null) return ""; 
/* 24 */     return def.getDescription();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 29 */   public Class<? extends RunTimePlugin> getPluginClass() { return com.dstz.bpm.plugin.usercalc.script.executer.ScriptPluginExecutor.class; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 34 */   public String getTitle() { return "脚本"; }
/*    */ 
/*    */ 
/*    */   
/*    */   protected ScriptPluginDef c(JSONObject pluginJson) {
/* 39 */     ScriptPluginDef def = new ScriptPluginDef();
/* 40 */     String script = pluginJson.getString("script");
/* 41 */     String description = JsonUtil.getString(pluginJson, "description", "脚本");
/* 42 */     def.setScript(script);
/* 43 */     def.setDescription(description);
/* 44 */     return def;
/*    */   }
/*    */ }


/* Location:              D:\Repository\com\dstz\agilebpm\wf-plugin\1.5.1\wf-plugin-1.5.1-pg.jar!\com\dstz\bpm\plugi\\usercalc\script\context\ScriptPluginContext.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.0
 */