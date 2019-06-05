/*    */ package com.dstz.bpm.plugin.global.script.context;
/*    */ 
/*    */ import com.alibaba.fastjson.JSON;
/*    */ import com.alibaba.fastjson.JSONObject;
/*    */ import com.dstz.bpm.api.constant.EventType;
/*    */ import com.dstz.bpm.api.engine.plugin.def.BpmPluginDef;
/*    */ import com.dstz.bpm.api.engine.plugin.runtime.RunTimePlugin;
/*    */ import com.dstz.bpm.engine.plugin.context.AbstractBpmPluginContext;
/*    */ import com.dstz.bpm.plugin.global.script.def.NodeScriptPluginDef;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import org.springframework.context.annotation.Scope;
/*    */ import org.springframework.stereotype.Component;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Component
/*    */ @Scope("prototype")
/*    */ public abstract class NodeScriptPluginContext
/*    */   extends AbstractBpmPluginContext<NodeScriptPluginDef>
/*    */ {
/*    */   private static final long serialVersionUID = -5958682303600423597L;
/*    */   
/*    */   public List<EventType> getEventTypes() {
/* 27 */     List<EventType> list = new ArrayList<EventType>();
/* 28 */     list.add(EventType.START_EVENT);
/* 29 */     list.add(EventType.END_EVENT);
/* 30 */     list.add(EventType.TASK_COMPLETE_EVENT);
/* 31 */     list.add(EventType.TASK_CREATE_EVENT);
/* 32 */     list.add(EventType.MANUAL_END);
/* 33 */     return list;
/*    */   }
/*    */ 
/*    */   
/* 37 */   public Class<? extends RunTimePlugin> getPluginClass() { return com.dstz.bpm.plugin.global.script.executer.NodeScriptPluginExecutor.class; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected NodeScriptPluginDef c(JSON pluginJson) {
/* 43 */     JSONObject jsonObject = (JSONObject)pluginJson;
/* 44 */     NodeScriptPluginDef def = new NodeScriptPluginDef();
/* 45 */     for (String key : jsonObject.keySet()) {
/*    */       try {
/* 47 */         EventType event = EventType.fromKey(key);
/* 48 */         def.a(event, jsonObject.getString(key));
/* 49 */       } catch (Exception e) {}
/*    */     } 
/*    */ 
/*    */ 
/*    */     
/* 54 */     return def;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 59 */   public String getTitle() { return "脚本"; }
/*    */ }


/* Location:              D:\Repository\com\dstz\agilebpm\wf-plugin\1.5.1\wf-plugin-1.5.1-pg.jar!\com\dstz\bpm\plugin\global\script\context\NodeScriptPluginContext.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.0
 */