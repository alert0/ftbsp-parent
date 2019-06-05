/*    */ package com.dstz.bpm.plugin.usercalc.user.context;
/*    */ 
/*    */ import com.alibaba.fastjson.JSONObject;
/*    */ import com.dstz.bpm.api.engine.plugin.def.BpmUserCalcPluginDef;
/*    */ import com.dstz.bpm.api.engine.plugin.runtime.RunTimePlugin;
/*    */ import com.dstz.bpm.engine.plugin.context.AbstractUserCalcPluginContext;
/*    */ import com.dstz.bpm.plugin.usercalc.user.def.UserPluginDef;
/*    */ import org.springframework.context.annotation.Scope;
/*    */ import org.springframework.stereotype.Component;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Component
/*    */ @Scope("prototype")
/*    */ public abstract class UserPluginContext
/*    */   extends AbstractUserCalcPluginContext<UserPluginDef>
/*    */ {
/*    */   private static final long serialVersionUID = 8757352972830358986L;
/*    */   
/*    */   public String getDescription() {
/* 23 */     String str = "";
/* 24 */     UserPluginDef def = (UserPluginDef)getBpmPluginDef();
/* 25 */     if (def == null) return ""; 
/* 26 */     String source = def.getSource();
/* 27 */     if ("currentUser".equals(source)) {
/* 28 */       str = "当前登录人";
/*    */     }
/* 30 */     if ("start".equals(source)) {
/* 31 */       str = "发起人";
/* 32 */     } else if ("prev".equals(source)) {
/* 33 */       str = "上一步执行人";
/* 34 */     } else if ("spec".equals(source)) {
/* 35 */       str = def.getUserName();
/*    */     } 
/*    */     
/* 38 */     return str;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 43 */   public String getTitle() { return "用户"; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 48 */   public Class<? extends RunTimePlugin> getPluginClass() { return com.dstz.bpm.plugin.usercalc.user.executer.UserPluginExecutor.class; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected UserPluginDef d(JSONObject pluginJson) {
/* 54 */     String source = pluginJson.getString("source");
/*    */     
/* 56 */     UserPluginDef def = new UserPluginDef();
/* 57 */     def.setSource(source);
/*    */     
/* 59 */     if ("spec".equals(source)) {
/* 60 */       String accounts = pluginJson.getString("account");
/* 61 */       String userNames = pluginJson.getString("userName");
/* 62 */       def.setAccount(accounts);
/* 63 */       def.setUserName(userNames);
/*    */     } 
/*    */     
/* 66 */     return def;
/*    */   }
/*    */ }


/* Location:              D:\Repository\com\dstz\agilebpm\wf-plugin\1.5.1\wf-plugin-1.5.1-pg.jar!\com\dstz\bpm\plugi\\usercal\\user\context\UserPluginContext.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.0
 */