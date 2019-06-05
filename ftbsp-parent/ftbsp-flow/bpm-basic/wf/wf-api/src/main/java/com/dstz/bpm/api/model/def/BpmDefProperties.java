/*    */ package com.dstz.bpm.api.model.def;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import org.hibernate.validator.constraints.NotBlank;
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
/*    */ public class BpmDefProperties
/*    */   implements Serializable
/*    */ {
/*    */   @NotBlank
/* 19 */   protected String subjectRule = "{发起人:startorName}在{发起时间:startDate}发起{流程标题:title}";
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 24 */   protected String description = "";
/*    */ 
/*    */   
/*    */   protected boolean allowExecutorEmpty = true;
/*    */ 
/*    */   
/* 30 */   protected Integer supportMobile = Integer.valueOf(0);
/*    */ 
/*    */ 
/*    */   
/*    */   protected boolean logSubmitData = true;
/*    */ 
/*    */ 
/*    */   
/*    */   @NotBlank
/* 39 */   protected String status = "draft";
/*    */ 
/*    */ 
/*    */   
/* 43 */   public String getSubjectRule() { return this.subjectRule; }
/*    */ 
/*    */ 
/*    */   
/* 47 */   public void setSubjectRule(String subjectRule) { this.subjectRule = subjectRule; }
/*    */ 
/*    */   
/*    */   public String getDescription() {
/* 51 */     if (this.description == null) return ""; 
/* 52 */     return this.description;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 57 */   public void setDescription(String description) { this.description = description; }
/*    */ 
/*    */ 
/*    */   
/* 61 */   public boolean isAllowExecutorEmpty() { return this.allowExecutorEmpty; }
/*    */ 
/*    */ 
/*    */   
/* 65 */   public void setAllowExecutorEmpty(boolean allowExecutorEmpty) { this.allowExecutorEmpty = allowExecutorEmpty; }
/*    */ 
/*    */ 
/*    */   
/* 69 */   public void setSupportMobile(Integer supportMobile) { this.supportMobile = supportMobile; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 77 */   public Integer getSupportMobile() { return this.supportMobile; }
/*    */ 
/*    */ 
/*    */   
/* 81 */   public String getStatus() { return this.status; }
/*    */ 
/*    */ 
/*    */   
/* 85 */   public boolean isLogSubmitData() { return this.logSubmitData; }
/*    */ 
/*    */ 
/*    */   
/* 89 */   public void setLogSubmitData(boolean logSubmitData) { this.logSubmitData = logSubmitData; }
/*    */ 
/*    */ 
/*    */   
/* 93 */   public void setStatus(String status) { this.status = status; }
/*    */ }


/* Location:              D:\Repository\com\dstz\agilebpm\wf-api\1.5.1\wf-api-1.5.1.jar!\com\dstz\bpm\api\model\def\BpmDefProperties.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.0
 */