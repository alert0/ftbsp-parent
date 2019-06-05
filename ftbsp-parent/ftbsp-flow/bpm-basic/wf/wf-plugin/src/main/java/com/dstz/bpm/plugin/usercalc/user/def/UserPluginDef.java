/*    */ package com.dstz.bpm.plugin.usercalc.user.def;
/*    */ 
/*    */ import com.dstz.bpm.engine.plugin.plugindef.AbstractUserCalcPluginDef;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class UserPluginDef
/*    */   extends AbstractUserCalcPluginDef
/*    */ {
/* 10 */   private String source = "";
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 15 */   private String account = "";
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 20 */   private String userName = "";
/*    */ 
/*    */ 
/*    */   
/* 24 */   public String getAccount() { return this.account; }
/*    */ 
/*    */ 
/*    */   
/* 28 */   public void setAccount(String account) { this.account = account; }
/*    */ 
/*    */ 
/*    */   
/* 32 */   public String getUserName() { return this.userName; }
/*    */ 
/*    */ 
/*    */   
/* 36 */   public void setUserName(String userName) { this.userName = userName; }
/*    */ 
/*    */ 
/*    */   
/* 40 */   public String getSource() { return this.source; }
/*    */ 
/*    */ 
/*    */   
/* 44 */   public void setSource(String source) { this.source = source; }
/*    */ }


/* Location:              D:\Repository\com\dstz\agilebpm\wf-plugin\1.5.1\wf-plugin-1.5.1-pg.jar!\com\dstz\bpm\plugi\\usercal\\user\def\UserPluginDef.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.0
 */