/*    */ package com.dstz.bpm.api.constant;
/*    */ 
/*    */ 
/*    */ 
/*    */ public enum TaskType
/*    */ {
/*  7 */   NORMAL("NORMAL", "普通任务"),
/*  8 */   SIGN("SIGN", "会签任务"),
/*  9 */   SIGN_SOURCE("SIGN_SOURCE", "会签任务_源"),
/* 10 */   SUBFLOW("SUBFLOW", "子流程任务"),
/* 11 */   AGENT("AGENT", "代理任务"),
/* 12 */   DELIVERTO("DELIVERTO", "转办任务"),
/* 13 */   TRANSFORMING("TRANSFORMING", "事项任务");
/*    */ 
/*    */   
/* 16 */   private String key = "";
/*    */   
/* 18 */   private String value = "";
/*    */ 
/*    */   
/*    */   TaskType(String key, String value) {
/* 22 */     this.key = key;
/* 23 */     this.value = value;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 28 */   public String getKey() { return this.key; }
/*    */ 
/*    */ 
/*    */   
/* 32 */   public void setKey(String key) { this.key = key; }
/*    */ 
/*    */ 
/*    */   
/* 36 */   public String getValue() { return this.value; }
/*    */ 
/*    */ 
/*    */   
/* 40 */   public void setValue(String value) { this.value = value; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 45 */   public String toString() { return this.key; }
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
/* 57 */   public boolean equalsWithKey(String key) { return this.key.equals(key); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static TaskType fromKey(String key) {
/* 67 */     for (TaskType c : values()) {
/* 68 */       if (c.getKey().equalsIgnoreCase(key))
/* 69 */         return c; 
/*    */     } 
/* 71 */     throw new IllegalArgumentException(key);
/*    */   }
/*    */ }


/* Location:              D:\Repository\com\dstz\agilebpm\wf-api\1.5.1\wf-api-1.5.1.jar!\com\dstz\bpm\api\constant\TaskType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.0
 */