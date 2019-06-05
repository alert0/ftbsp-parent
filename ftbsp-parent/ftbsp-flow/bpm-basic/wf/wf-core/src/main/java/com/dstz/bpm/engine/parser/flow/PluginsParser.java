/*    */ package com.dstz.bpm.engine.parser.flow;
/*    */ 
/*    */ import com.alibaba.fastjson.JSON;
/*    */ import com.alibaba.fastjson.JSONObject;
/*    */ import com.dstz.base.core.util.AppUtil;
/*    */ import com.dstz.base.core.validate.ValidateUtil;
/*    */ import com.dstz.bpm.api.engine.plugin.context.BpmPluginContext;
/*    */ import com.dstz.bpm.api.engine.plugin.def.BpmDef;
/*    */ import com.dstz.bpm.api.engine.plugin.def.BpmPluginDef;
/*    */ import com.dstz.bpm.engine.model.DefaultBpmProcessDef;
/*    */ import java.util.ArrayList;
/*    */ import org.springframework.stereotype.Component;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Component
/*    */ public abstract class PluginsParser
/*    */   extends AbsFlowParse<BpmPluginContext>
/*    */ {
/*    */   public Object a(DefaultBpmProcessDef bpmProcessDef, String json) {
/* 22 */     JSONObject plugins = JSON.parseObject(json);
/* 23 */     setDefaultPlugins(plugins);
/*    */     
/* 25 */     ArrayList<BpmPluginContext> pluginContextList = new ArrayList<BpmPluginContext>();
/* 26 */     for (String pluginName : plugins.keySet()) {
/* 27 */       BpmPluginContext pluginContext = (BpmPluginContext)AppUtil.getBean(pluginName + "PluginContext");
/* 28 */       if (pluginContext == null) {
/* 29 */         this.LOG.error("插件解析失败，不存在的插件：{}", pluginName + "PluginContext");
/*    */         
/*    */         continue;
/*    */       } 
/* 33 */       if (pluginContext instanceof BpmPluginContext) {
/*    */         try {
/* 35 */           pluginContext.parse((JSON)plugins.get(pluginName));
/* 36 */           BpmPluginDef def = pluginContext.getBpmPluginDef();
/*    */           
/* 38 */           ValidateUtil.validate(def);
/* 39 */         } catch (Exception e) {
/* 40 */           this.LOG.error("插件{}解析失败:{}！", new Object[] { pluginContext.getTitle(), e.getMessage(), e });
/*    */         } 
/*    */       }
/* 43 */       pluginContextList.add(pluginContext);
/*    */     } 
/*    */     
/* 46 */     return pluginContextList;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 52 */   public String getKey() { return "plugins"; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 57 */   public String validate(Object o) { return null; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private void setDefaultPlugins(JSONObject plugins) {
/* 65 */     if (!plugins.containsKey("dataLog")) {
/* 66 */       plugins.put("dataLog", new JSONObject());
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void a(DefaultBpmProcessDef bpmProcessDef, Object object) {
/* 74 */     ArrayList<BpmPluginContext> pluginContextList = (ArrayList)object;
/*    */     
/* 76 */     bpmProcessDef.setPluginContextList(pluginContextList);
/*    */   }
/*    */ }


/* Location:              D:\Repository\com\dstz\agilebpm\wf-core\1.5.1\wf-core-1.5.1-pg.jar!\com\dstz\bpm\engine\parser\flow\PluginsParser.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.0
 */