/*     */ package com.dstz.bpm.plugin.node.userassign.context;
/*     */ 
/*     */ import cn.hutool.core.collection.CollectionUtil;
/*     */ import com.alibaba.fastjson.JSON;
/*     */ import com.alibaba.fastjson.JSONArray;
/*     */ import com.alibaba.fastjson.JSONObject;
/*     */ import com.dstz.base.core.util.AppUtil;
/*     */ import com.dstz.base.core.util.JsonUtil;
/*     */ import com.dstz.base.core.util.ThreadMsgUtil;
/*     */ import com.dstz.bpm.api.constant.EventType;
/*     */ import com.dstz.bpm.api.engine.plugin.context.UserCalcPluginContext;
/*     */ import com.dstz.bpm.api.engine.plugin.context.UserQueryPluginContext;
/*     */ import com.dstz.bpm.api.engine.plugin.def.BpmPluginDef;
/*     */ import com.dstz.bpm.api.engine.plugin.def.UserAssignRule;
/*     */ import com.dstz.bpm.api.engine.plugin.runtime.RunTimePlugin;
/*     */ import com.dstz.bpm.engine.plugin.context.AbstractBpmPluginContext;
/*     */ import com.dstz.bpm.engine.plugin.context.AbstractUserCalcPluginContext;
/*     */ import com.dstz.bpm.plugin.node.userassign.def.UserAssignPluginDef;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.springframework.context.annotation.Scope;
/*     */ import org.springframework.stereotype.Component;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Component
/*     */ @Scope("prototype")
/*     */ public abstract class UserAssignPluginContext
/*     */   extends AbstractBpmPluginContext<UserAssignPluginDef>
/*     */   implements UserQueryPluginContext
/*     */ {
/*  38 */   public Class getPluginClass() { return com.dstz.bpm.plugin.node.userassign.executer.UserAssignPluginExecutor.class; }
/*     */ 
/*     */ 
/*     */   
/*  42 */   public Class<? extends RunTimePlugin> getUserQueryPluginClass() { return com.dstz.bpm.plugin.node.userassign.executer.UserAssignPluginExecutor.class; }
/*     */ 
/*     */ 
/*     */   
/*     */   public List<EventType> getEventTypes() {
/*  47 */     List<EventType> eventTypes = new ArrayList<EventType>();
/*  48 */     eventTypes.add(EventType.TASK_CREATE_EVENT);
/*  49 */     return eventTypes;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JSON getJson() {
/*  64 */     if (getBpmPluginDef() == null) return (JSON)JSON.parse("[]");
/*     */     
/*  66 */     List<UserAssignRule> ruleList = ((UserAssignPluginDef)getBpmPluginDef()).getRuleList();
/*  67 */     if (CollectionUtil.isEmpty(ruleList)) return (JSON)JSON.parse("[]");
/*     */     
/*  69 */     return (JSON)JSON.toJSON(ruleList);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected UserAssignPluginDef f(JSON pluginJson) {
/*  78 */     UserAssignPluginDef def = new UserAssignPluginDef();
/*     */     
/*  80 */     JSONArray userRuleList = null;
/*  81 */     if (pluginJson instanceof JSONObject) {
/*  82 */       JSONObject json = (JSONObject)pluginJson;
/*  83 */       if (!json.containsKey("ruleList")) {
/*  84 */         ThreadMsgUtil.addMsg("人员条件不完整！");
/*  85 */         return def;
/*     */       } 
/*     */       
/*  88 */       userRuleList = json.getJSONArray("ruleList");
/*     */     } else {
/*  90 */       userRuleList = (JSONArray)pluginJson;
/*     */     } 
/*     */     
/*  93 */     List<UserAssignRule> ruleList = new ArrayList<UserAssignRule>();
/*  94 */     for (int i = 0; i < userRuleList.size(); i++) {
/*  95 */       JSONObject ruleJson = userRuleList.getJSONObject(i);
/*  96 */       UserAssignRule rule = (UserAssignRule)JSON.toJavaObject(ruleJson, UserAssignRule.class);
/*  97 */       ruleList.add(rule);
/*     */       
/*  99 */       if (!ruleJson.containsKey("calcs")) {
/* 100 */         ThreadMsgUtil.addMsg("人员条件不完整！");
/*     */       }
/*     */       else {
/*     */         
/* 104 */         JSONArray calcAry = ruleJson.getJSONArray("calcs");
/* 105 */         List<UserCalcPluginContext> calcPluginContextList = new ArrayList<UserCalcPluginContext>();
/* 106 */         for (Object obj : calcAry) {
/* 107 */           JSONObject calcObj = (JSONObject)obj;
/* 108 */           String pluginContext = JsonUtil.getString(calcObj, "pluginType") + "PluginContext";
/*     */           
/* 110 */           AbstractUserCalcPluginContext ctx = (AbstractUserCalcPluginContext)AppUtil.getBean(pluginContext);
/* 111 */           if (ctx == null) {
/* 112 */             this.LOG.warn("插件{}查找失败！", pluginContext);
/*     */             
/*     */             continue;
/*     */           } 
/* 116 */           ctx.parse(calcObj);
/*     */           
/* 118 */           calcPluginContextList.add(ctx);
/*     */         } 
/*     */         
/* 121 */         rule.setCalcPluginContextList(calcPluginContextList);
/*     */       } 
/*     */     } 
/* 124 */     def.setRuleList(ruleList);
/* 125 */     return def;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 130 */   public String getTitle() { return "用户分配插件"; }
/*     */ }


/* Location:              D:\Repository\com\dstz\agilebpm\wf-plugin\1.5.1\wf-plugin-1.5.1-pg.jar!\com\dstz\bpm\plugin\nod\\userassign\context\UserAssignPluginContext.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.0
 */