/*    */ package com.dstz.bpm.engine.parser.node;
/*    */ 
/*    */ import com.alibaba.fastjson.JSON;
/*    */ import com.alibaba.fastjson.JSONObject;
/*    */ import com.dstz.base.core.util.AppUtil;
/*    */ import com.dstz.bpm.api.engine.plugin.context.BpmPluginContext;
/*    */ import com.dstz.bpm.api.engine.plugin.def.BpmDef;
/*    */ import com.dstz.bpm.api.model.nodedef.impl.BaseBpmNodeDef;
/*    */ import java.util.ArrayList;
/*    */ import org.springframework.stereotype.Component;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Component
/*    */ public abstract class NodePluginsParser
/*    */   extends AbsNodeParse<BpmPluginContext>
/*    */ {
/*    */   public Object a(BaseBpmNodeDef userNodeDef, String json) {
/* 21 */     JSONObject plugins = JSON.parseObject(json);
/*    */     
/* 23 */     ArrayList<BpmPluginContext> pluginContextList = new ArrayList<BpmPluginContext>();
/* 24 */     for (String pluginName : plugins.keySet()) {
/* 25 */       BpmPluginContext pluginContext = (BpmPluginContext)AppUtil.getBean(pluginName + "PluginContext");
/* 26 */       if (pluginContext == null) {
/* 27 */         this.LOG.error("插件解析失败，不存在的插件：{}", pluginName + "PluginContext");
/*    */         
/*    */         continue;
/*    */       } 
/* 31 */       if (pluginContext instanceof BpmPluginContext) {
/* 32 */         Object object = plugins.get(pluginName);
/* 33 */         pluginContext.parse((JSON)object);
/*    */       } 
/* 35 */       pluginContextList.add(pluginContext);
/*    */     } 
/*    */     
/* 38 */     return pluginContextList;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 46 */   public String getKey() { return "plugins"; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 53 */   public String validate(Object o) { return null; }
/*    */ 
/*    */ 
/*    */   
/*    */   public void a(BaseBpmNodeDef userNodeDef, Object object) {
/* 58 */     ArrayList<BpmPluginContext> pluginContextList = (ArrayList)object;
/*    */     
/* 60 */     userNodeDef.setBpmPluginContexts(pluginContextList);
/*    */   }
/*    */ }


/* Location:              D:\Repository\com\dstz\agilebpm\wf-core\1.5.1\wf-core-1.5.1-pg.jar!\com\dstz\bpm\engine\parser\node\NodePluginsParser.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.0
 */