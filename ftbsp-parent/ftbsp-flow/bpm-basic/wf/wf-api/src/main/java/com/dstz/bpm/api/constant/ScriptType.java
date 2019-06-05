/*    */ package com.dstz.bpm.api.constant;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public enum ScriptType
/*    */ {
/*  9 */   START("start", "开始脚本"),
/* 10 */   END("end", "结束脚本"),
/* 11 */   CREATE("create", "创建脚本"),
/* 12 */   COMPLETE("complete", "结束脚本"),
/* 13 */   MANUALEND("manualEnd", "人工终止");
/*    */ 
/*    */   
/* 16 */   private String key = "";
/*    */   
/* 18 */   private String value = "";
/*    */ 
/*    */   
/*    */   ScriptType(String key, String value) {
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
/*    */   public static ScriptType fromKey(String key) {
/* 55 */     for (ScriptType c : values()) {
/* 56 */       if (c.getKey().equalsIgnoreCase(key))
/* 57 */         return c; 
/*    */     } 
/* 59 */     throw new IllegalArgumentException(key);
/*    */   }
/*    */ }


/* Location:              D:\Repository\com\dstz\agilebpm\wf-api\1.5.1\wf-api-1.5.1.jar!\com\dstz\bpm\api\constant\ScriptType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.0
 */