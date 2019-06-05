/*    */ package com.dstz.bpm.api.constant;
/*    */ 
/*    */ 
/*    */ 
/*    */ public enum TaskStatus
/*    */ {
/*  7 */   NORMAL("NORMAL", "普通", "普通订单"),
/*  8 */   SUSPEND("SUSPEND", "挂起", "超管挂起任务"),
/*  9 */   LOCK("LOCK", "锁定", "个人将任务锁定至个人名下"),
/* 10 */   TURN("TURN", "转办", "个人将任务转办给其他人"),
/* 11 */   AGENCY("AGENCY", "代理", "代理其他人的任务"),
/* 12 */   BACK("BACK", "驳回", "被驳回的任务"),
/* 13 */   DESIGNATE("DESIGNATE", "指派", "个人将任务指派到某个人名下"),
/* 14 */   DRAG("DRAG", "捞单", "从捞单池中获取的订单");
/*    */ 
/*    */   
/* 17 */   private String key = "";
/*    */   
/* 19 */   private String value = "";
/*    */   
/* 21 */   private String desc = "";
/*    */ 
/*    */   
/*    */   TaskStatus(String key, String value, String desc) {
/* 25 */     this.key = key;
/* 26 */     this.value = value;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 31 */   public String getKey() { return this.key; }
/*    */ 
/*    */ 
/*    */   
/* 35 */   public void setKey(String key) { this.key = key; }
/*    */ 
/*    */ 
/*    */   
/* 39 */   public String getValue() { return this.value; }
/*    */ 
/*    */ 
/*    */   
/* 43 */   public void setValue(String value) { this.value = value; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 48 */   public String toString() { return this.key; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static TaskStatus fromKey(String key) {
/* 58 */     for (TaskStatus c : values()) {
/* 59 */       if (c.getKey().equalsIgnoreCase(key))
/* 60 */         return c; 
/*    */     } 
/* 62 */     throw new IllegalArgumentException(key);
/*    */   }
/*    */ 
/*    */   
/* 66 */   public String getDesc() { return this.desc; }
/*    */ 
/*    */ 
/*    */   
/* 70 */   public void setDesc(String desc) { this.desc = desc; }
/*    */ }


/* Location:              D:\Repository\com\dstz\agilebpm\wf-api\1.5.1\wf-api-1.5.1.jar!\com\dstz\bpm\api\constant\TaskStatus.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.0
 */