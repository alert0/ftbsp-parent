/*    */ package com.dstz.bpm.engine.constant;
/*    */ 
/*    */ public enum TaskSkipType {
/*  4 */   NO_SKIP("noSkip", "不跳过"),
/*  5 */   ALL_SKIP("allSkip", "所有节点跳过"),
/*  6 */   FIRSTNODE_SKIP("firstNodeSkip", "开始节点跳过"),
/*  7 */   SAME_USER_SKIP("sameUserSkip", "前一节点相同用户则跳过"),
/*  8 */   USER_EMPTY_SKIP("userEmptySkip", "执行人为空则跳过"),
/*  9 */   SCRIPT_SKIP("scriptSkip", "脚本跳过");
/*    */ 
/*    */   
/* 12 */   private String key = "";
/*    */   
/* 14 */   private String value = "";
/*    */ 
/*    */   
/*    */   TaskSkipType(String key, String value) {
/* 18 */     this.key = key;
/* 19 */     this.value = value;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 24 */   public String getKey() { return this.key; }
/*    */ 
/*    */ 
/*    */   
/* 28 */   public void setKey(String key) { this.key = key; }
/*    */ 
/*    */ 
/*    */   
/* 32 */   public String getValue() { return this.value; }
/*    */ 
/*    */ 
/*    */   
/* 36 */   public void setValue(String value) { this.value = value; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 41 */   public String toString() { return this.key; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static TaskSkipType fromKey(String key) {
/* 51 */     for (TaskSkipType c : values()) {
/* 52 */       if (c.getKey().equalsIgnoreCase(key))
/* 53 */         return c; 
/*    */     } 
/* 55 */     throw new IllegalArgumentException(key);
/*    */   }
/*    */ }


/* Location:              D:\Repository\com\dstz\agilebpm\wf-core\1.5.1\wf-core-1.5.1-pg.jar!\com\dstz\bpm\engine\constant\TaskSkipType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.0
 */