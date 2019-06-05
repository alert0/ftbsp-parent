/*    */ package com.dstz.bpm.api.engine.constant;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public enum LogicType
/*    */ {
/* 10 */   AND("and", "与"),
/* 11 */   OR("or", "或"),
/* 12 */   EXCLUDE("exclude", "非");
/*    */ 
/*    */   
/* 15 */   private String key = "";
/*    */   
/* 17 */   private String value = "";
/*    */ 
/*    */   
/*    */   LogicType(String key, String value) {
/* 21 */     this.key = key;
/* 22 */     this.value = value;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 27 */   public String getKey() { return this.key; }
/*    */ 
/*    */ 
/*    */   
/* 31 */   public void setKey(String key) { this.key = key; }
/*    */ 
/*    */ 
/*    */   
/* 35 */   public String getValue() { return this.value; }
/*    */ 
/*    */ 
/*    */   
/* 39 */   public void setValue(String value) { this.value = value; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 44 */   public String toString() { return this.key; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static LogicType fromKey(String key) {
/* 54 */     for (LogicType c : values()) {
/* 55 */       if (c.getKey().equalsIgnoreCase(key))
/* 56 */         return c; 
/*    */     } 
/* 58 */     throw new IllegalArgumentException(key);
/*    */   }
/*    */ }


/* Location:              D:\Repository\com\dstz\agilebpm\wf-api\1.5.1\wf-api-1.5.1.jar!\com\dstz\bpm\api\engine\constant\LogicType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.0
 */