/*    */ package com.dstz.bpm.plugin.usercalc.samenode.context;
/*    */ 
/*    */ import com.alibaba.fastjson.JSONObject;
/*    */ import com.dstz.base.core.util.JsonUtil;
/*    */ import com.dstz.bpm.api.engine.plugin.def.BpmUserCalcPluginDef;
/*    */ import com.dstz.bpm.api.engine.plugin.runtime.RunTimePlugin;
/*    */ import com.dstz.bpm.engine.plugin.context.AbstractUserCalcPluginContext;
/*    */ import com.dstz.bpm.plugin.usercalc.samenode.def.SameNodePluginDef;
/*    */ import org.springframework.context.annotation.Scope;
/*    */ import org.springframework.stereotype.Component;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Component
/*    */ @Scope("prototype")
/*    */ public abstract class SameNodePluginContext
/*    */   extends AbstractUserCalcPluginContext<SameNodePluginDef>
/*    */ {
/*    */   private static final long serialVersionUID = 919433269116580830L;
/*    */   
/*    */   public String getDescription() {
/* 25 */     SameNodePluginDef def = (SameNodePluginDef)getBpmPluginDef();
/* 26 */     if (def == null) return ""; 
/* 27 */     return "节点：" + def.getNodeId();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 32 */   public String getTitle() { return "相同节点执行人"; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 37 */   public Class<? extends RunTimePlugin> getPluginClass() { return com.dstz.bpm.plugin.usercalc.samenode.executer.SameNodePluginExecutor.class; }
/*    */ 
/*    */ 
/*    */   
/*    */   protected SameNodePluginDef b(JSONObject pluginJson) {
/* 42 */     SameNodePluginDef def = new SameNodePluginDef();
/* 43 */     String nodeId = JsonUtil.getString(pluginJson, "nodeId");
/* 44 */     def.setNodeId(nodeId);
/* 45 */     return def;
/*    */   }
/*    */ }


/* Location:              D:\Repository\com\dstz\agilebpm\wf-plugin\1.5.1\wf-plugin-1.5.1-pg.jar!\com\dstz\bpm\plugi\\usercalc\samenode\context\SameNodePluginContext.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.0
 */