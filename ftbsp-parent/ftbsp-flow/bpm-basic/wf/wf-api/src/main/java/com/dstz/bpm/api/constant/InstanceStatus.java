/*    */ package com.dstz.bpm.api.constant;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public enum InstanceStatus
/*    */ {
/* 10 */   STATUS_DRAFT("draft", "草稿"),
/*    */ 
/*    */ 
/*    */   
/* 14 */   STATUS_RUNNING("running", "运行中"),
/*    */ 
/*    */ 
/*    */   
/* 18 */   STATUS_END("end", "结束"),
/*    */ 
/*    */ 
/*    */   
/* 22 */   STATUS_MANUAL_END("manualend", "人工结束"),
/*    */   
/* 24 */   STATUS_BACK("back", "驳回"),
/*    */   
/* 26 */   STATUS_UNDEFINED("undefined", "未定义"),
/*    */   
/* 28 */   STATUS_REVOKE("revoke", "撤销");
/*    */ 
/*    */   
/* 31 */   private String key = "";
/*    */   
/* 33 */   private String value = "";
/*    */ 
/*    */   
/*    */   InstanceStatus(String key, String value) {
/* 37 */     this.key = key;
/* 38 */     this.value = value;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 43 */   public String getKey() { return this.key; }
/*    */ 
/*    */ 
/*    */   
/* 47 */   public void setKey(String key) { this.key = key; }
/*    */ 
/*    */ 
/*    */   
/* 51 */   public String getValue() { return this.value; }
/*    */ 
/*    */ 
/*    */   
/* 55 */   public void setValue(String value) { this.value = value; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 60 */   public String toString() { return this.key; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static InstanceStatus fromKey(String key) {
/* 70 */     for (InstanceStatus c : values()) {
/* 71 */       if (c.getKey().equalsIgnoreCase(key))
/* 72 */         return c; 
/*    */     } 
/* 74 */     throw new IllegalArgumentException(key);
/*    */   }
/*    */   
/*    */   public static InstanceStatus getByActionName(String actionName) {
/* 78 */     ActionType action = ActionType.fromKey(actionName);
/*    */     
/* 80 */     switch (action) {
/*    */       case AGREE:
/* 82 */         return STATUS_RUNNING;
/*    */       case OPPOSE:
/* 84 */         return STATUS_RUNNING;
/*    */       case REJECT:
/* 86 */         return STATUS_BACK;
/*    */       case REJECT2START:
/* 88 */         return STATUS_BACK;
/*    */       case RECOVER:
/* 90 */         return STATUS_REVOKE;
/*    */       case TASKOPINION:
/* 92 */         return STATUS_RUNNING;
/*    */       case MANUALEND:
/* 94 */         return STATUS_MANUAL_END;
/*    */     } 
/* 96 */     return STATUS_RUNNING;
/*    */   }
/*    */ }


/* Location:              D:\Repository\com\dstz\agilebpm\wf-api\1.5.1\wf-api-1.5.1.jar!\com\dstz\bpm\api\constant\InstanceStatus.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.0
 */