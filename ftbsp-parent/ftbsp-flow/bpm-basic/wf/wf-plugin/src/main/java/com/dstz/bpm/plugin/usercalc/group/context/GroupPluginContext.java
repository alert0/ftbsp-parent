/*    */ package com.dstz.bpm.plugin.usercalc.group.context;
/*    */ 
/*    */ import com.alibaba.fastjson.JSONObject;
/*    */ import com.dstz.base.core.util.JsonUtil;
/*    */ import com.dstz.bpm.api.engine.plugin.def.BpmUserCalcPluginDef;
/*    */ import com.dstz.bpm.api.engine.plugin.runtime.RunTimePlugin;
/*    */ import com.dstz.bpm.engine.plugin.context.AbstractUserCalcPluginContext;
/*    */ import com.dstz.bpm.plugin.usercalc.group.def.GroupPluginDef;
/*    */ import org.springframework.context.annotation.Scope;
/*    */ import org.springframework.stereotype.Component;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Component
/*    */ @Scope("prototype")
/*    */ public class GroupPluginContext
/*    */   extends AbstractUserCalcPluginContext
/*    */ {
/*    */   private static final long serialVersionUID = -6084686546165511275L;
/*    */   
/*    */   public String getDescription() {
/* 24 */     GroupPluginDef def = (GroupPluginDef)getBpmPluginDef();
/* 25 */     if (def == null) return "";
/*    */     
/* 27 */     return String.format("%s[%s]", new Object[] { def.getTypeName(), def.getGroupName() });
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 32 */   public String getTitle() { return "组"; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 37 */   public Class<? extends RunTimePlugin> getPluginClass() { return com.dstz.bpm.plugin.usercalc.group.executer.GroupPluginExecutor.class; }
/*    */ 
/*    */ 
/*    */   
/*    */   protected BpmUserCalcPluginDef parseJson(JSONObject pluginJson) {
/* 42 */     GroupPluginDef def = new GroupPluginDef();
/* 43 */     String groupType = JsonUtil.getString(pluginJson, "type");
/* 44 */     String groupTypeName = JsonUtil.getString(pluginJson, "typeName");
/*    */     
/* 46 */     def.setType(groupType);
/* 47 */     def.setTypeName(groupTypeName);
/*    */     
/* 49 */     String groupKey = JsonUtil.getString(pluginJson, "groupKey");
/* 50 */     String groupName = JsonUtil.getString(pluginJson, "groupName");
/* 51 */     def.setGroupKey(groupKey);
/* 52 */     def.setGroupName(groupName);
/* 53 */     return def;
/*    */   }
/*    */ }


/* Location:              D:\Repository\com\dstz\agilebpm\wf-plugin\1.5.1\wf-plugin-1.5.1-pg.jar!\com\dstz\bpm\plugi\\usercalc\group\context\GroupPluginContext.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.0
 */