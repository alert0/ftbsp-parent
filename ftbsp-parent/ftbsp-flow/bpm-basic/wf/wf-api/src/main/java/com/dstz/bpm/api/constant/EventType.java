/*    */ package com.dstz.bpm.api.constant;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public  enum EventType
/*    */ {
/* 11 */   START_EVENT("startEvent", "流程启动事件"),
/*    */ 
/*    */ 
/*    */   
/* 15 */   START_POST_EVENT("postStartEvent", "流程启动后置事件"),
/*    */ 
/*    */ 
/*    */   
/* 19 */   END_EVENT("endEvent", "流程结束事件"),
/* 20 */   MANUAL_END("manualEnd", "流程人工终止事件"),
/*    */ 
/*    */ 
/*    */   
/* 24 */   END_POST_EVENT("postEndEvent", "流程结束后置事件"),
/*    */   
/* 26 */   TASK_PRE_COMPLETE_EVENT("preTaskComplete", "任务完成前置事件"),
/*    */ 
/*    */ 
/*    */   
/* 30 */   TASK_CREATE_EVENT("taskCreate", "任务创建事件"),
/*    */ 
/*    */ 
/*    */   
/* 34 */   TASK_POST_CREATE_EVENT("postTaskCreate", "任务创建后置事件"),
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 39 */   TASK_COMPLETE_EVENT("taskComplete", "任务完成事件"),
/*    */ 
/*    */ 
/*    */   
/* 43 */   TASK_POST_COMPLETE_EVENT("postTaskComplete", "任务完成后置事件"),
/*    */ 
/*    */ 
/*    */   
/* 47 */   TASK_SIGN_CREATE_EVENT("taskSignCreate", "会签任务创建"),
/*    */ 
/*    */ 
/*    */   
/* 51 */   TASK_SIGN_POST_CREATE_EVENT("postTaskSignCreate", "会签任务创建后置事件");
/*    */ 
/*    */   
/* 54 */   private String key = "";
/*    */   
/* 56 */   private String value = "";
/*    */ 
/*    */   
/*    */   EventType(String key, String value) {
/* 60 */     this.key = key;
/* 61 */     this.value = value;
/*    */   }
/*    */ 
/*    */   
/* 65 */   public String getKey() { return this.key; }
/*    */ 
/*    */ 
/*    */   
/* 69 */   public void setKey(String key) { this.key = key; }
/*    */ 
/*    */ 
/*    */   
/* 73 */   public String getValue() { return this.value; }
/*    */ 
/*    */ 
/*    */   
/* 77 */   public void setValue(String value) { this.value = value; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 82 */   public String toString() { return this.key; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static EventType fromKey(String key) {
/* 92 */     for (EventType c : values()) {
/* 93 */       if (c.getKey().equalsIgnoreCase(key))
/* 94 */         return c; 
/*    */     } 
/* 96 */     throw new IllegalArgumentException(key);
/*    */   }
/*    */ }


/* Location:              D:\Repository\com\dstz\agilebpm\wf-api\1.5.1\wf-api-1.5.1.jar!\com\dstz\bpm\api\constant\EventType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.0
 */