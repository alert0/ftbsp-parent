/*    */ package com.dstz.bpm.api.constant;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public enum ExtractType
/*    */ {
/* 13 */   EXACT_NOEXACT("no", "不抽取"),
/*    */ 
/*    */ 
/*    */   
/* 17 */   EXACT_EXACT_USER("extract", "抽取用户");
/*    */ 
/*    */   
/* 20 */   private String key = "";
/*    */   
/* 22 */   private String value = "";
/*    */ 
/*    */   
/*    */   ExtractType(String key, String value) {
/* 26 */     this.key = key;
/* 27 */     this.value = value;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 32 */   public String getKey() { return this.key; }
/*    */ 
/*    */ 
/*    */   
/* 36 */   public void setKey(String key) { this.key = key; }
/*    */ 
/*    */ 
/*    */   
/* 40 */   public String getValue() { return this.value; }
/*    */ 
/*    */ 
/*    */   
/* 44 */   public void setValue(String value) { this.value = value; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 49 */   public String toString() { return this.key; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static ExtractType fromKey(String key) {
/* 59 */     for (ExtractType c : values()) {
/* 60 */       if (c.getKey().equalsIgnoreCase(key))
/* 61 */         return c; 
/*    */     } 
/* 63 */     throw new IllegalArgumentException(key);
/*    */   }
/*    */ }


/* Location:              D:\Repository\com\dstz\agilebpm\wf-api\1.5.1\wf-api-1.5.1.jar!\com\dstz\bpm\api\constant\ExtractType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.0
 */