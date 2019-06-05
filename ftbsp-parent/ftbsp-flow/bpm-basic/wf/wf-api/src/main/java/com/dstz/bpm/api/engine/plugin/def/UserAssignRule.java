/*    */ package com.dstz.bpm.api.engine.plugin.def;
/*    */ 
/*    */ import com.dstz.base.core.util.StringUtil;
/*    */ import com.dstz.bpm.api.engine.plugin.context.UserCalcPluginContext;
/*    */ import java.io.Serializable;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class UserAssignRule
/*    */   extends Object
/*    */   implements Comparable<UserAssignRule>, Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/* 17 */   private String name = "";
/*    */   
/* 19 */   private String description = "";
/*    */   
/* 21 */   private String condition = "";
/*    */ 
/*    */ 
/*    */   
/* 25 */   private int groupNo = 1;
/*    */   
/* 27 */   private List<UserCalcPluginContext> calcPluginContextList = new ArrayList();
/*    */ 
/*    */ 
/*    */   
/* 31 */   public String getName() { return this.name; }
/*    */ 
/*    */ 
/*    */   
/* 35 */   public void setName(String name) { this.name = name; }
/*    */ 
/*    */   
/*    */   public String getDescription() {
/* 39 */     if (StringUtil.isEmpty(this.description)) {
/* 40 */       String desc = "";
/* 41 */       for (UserCalcPluginContext ctx : this.calcPluginContextList) {
/* 42 */         desc = desc + "　　　【" + ctx.getTitle() + "】" + ctx.getDescription() + ";";
/*    */       }
/* 44 */       return desc;
/*    */     } 
/* 46 */     return this.description;
/*    */   }
/*    */ 
/*    */   
/* 50 */   public void setDescription(String description) { this.description = description; }
/*    */ 
/*    */ 
/*    */   
/* 54 */   public String getCondition() { return this.condition; }
/*    */ 
/*    */ 
/*    */   
/* 58 */   public void setCondition(String condition) { this.condition = condition; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 71 */   public int getGroupNo() { return this.groupNo; }
/*    */ 
/*    */ 
/*    */   
/* 75 */   public void setGroupNo(int groupNo) { this.groupNo = groupNo; }
/*    */ 
/*    */ 
/*    */   
/* 79 */   public List<UserCalcPluginContext> getCalcPluginContextList() { return this.calcPluginContextList; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 84 */   public void setCalcPluginContextList(List<UserCalcPluginContext> calcPluginContextList) { this.calcPluginContextList = calcPluginContextList; }
/*    */ 
/*    */ 
/*    */   
/*    */   public int compareTo(UserAssignRule userRule) {
/* 89 */     if (this.groupNo > userRule.groupNo) return 1; 
/* 90 */     if (this.groupNo < userRule.groupNo) return -1; 
/* 91 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\Repository\com\dstz\agilebpm\wf-api\1.5.1\wf-api-1.5.1.jar!\com\dstz\bpm\api\engine\plugin\def\UserAssignRule.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.0
 */