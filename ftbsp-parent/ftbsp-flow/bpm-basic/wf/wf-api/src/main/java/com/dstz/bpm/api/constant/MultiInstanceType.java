/*    */ package com.dstz.bpm.api.constant;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public enum MultiInstanceType
/*    */ {
/* 11 */   NO("no", "单实例"),
/* 12 */   PARALLEL("parallel", "并行"),
/* 13 */   SEQUENTIAL("sequential", "串行");
/*    */ 
/*    */   
/* 16 */   private String key = "";
/*    */   
/* 18 */   private String value = "";
/*    */ 
/*    */   
/*    */   MultiInstanceType(String key, String value) {
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
/*    */   public static MultiInstanceType fromKey(String key) {
/* 55 */     for (MultiInstanceType c : values()) {
/* 56 */       if (c.getKey().equalsIgnoreCase(key))
/* 57 */         return c; 
/*    */     } 
/* 59 */     throw new IllegalArgumentException(key);
/*    */   }
/*    */ }


/* Location:              D:\Repository\com\dstz\agilebpm\wf-api\1.5.1\wf-api-1.5.1.jar!\com\dstz\bpm\api\constant\MultiInstanceType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.0
 */