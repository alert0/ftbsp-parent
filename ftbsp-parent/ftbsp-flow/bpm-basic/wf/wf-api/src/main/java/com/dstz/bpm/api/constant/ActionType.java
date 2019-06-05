/*    */ package com.dstz.bpm.api.constant;
/*    */ 
/*    */ import com.dstz.base.api.exception.BusinessException;
/*    */ import com.dstz.bpm.api.exception.BpmStatusCode;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public enum ActionType
/*    */ {
/* 13 */   DRAFT("draft", "保存草稿", "instanceSaveActionHandler"),
/* 14 */   START("start", "启动", "instanceStartActionHandler"),
/* 15 */   AGREE("agree", "同意", "taskAgreeActionHandler"),
/* 16 */   SIGNAGREE("signAgree", "会签同意", "taskSignAgreeActionHandler"),
/* 17 */   SAVE("save", "保存", "taskSaveActionHandler"),
/* 18 */   OPPOSE("oppose", "反对", "taskOpposeActionHandler"),
/* 19 */   SIGNOPPOSE("signOppose", "会签反对", "taskSignOpposeActionHandler"),
/* 20 */   REJECT("reject", "驳回", "taskRejectActionHandler"),
/* 21 */   REJECT2START("reject2Start", "驳回发起人", "taskReject2StartActionHandler"),
/* 22 */   RECOVER("recover", "撤销", "null"),
/* 23 */   DISPENDSE("dispense", "分发", "null"),
/* 24 */   TASKOPINION("taskOpinion", "审批历史", "instanceTaskOpinionActionHandler"),
/* 25 */   FLOWIMAGE("flowImage", "流程图", "instanceImageActionHandler"),
/* 26 */   PRINT("print", "打印", "instancePrintActionHandler"),
/* 27 */   MANUALEND("manualEnd", "人工终止", "instanceManualEndActionHandler"),
/*    */   
/* 29 */   LOCK("lock", "锁定", "taskLockActionHandler"),
/* 30 */   UNLOCK("unlock", "解锁", "taskUnlockActionHandler"),
/*    */   
/* 32 */   TURN("turn", "转办", "taskTurnActionHandler"),
/* 33 */   REMINDER("reminder", "催办", "instanceReminderActionHandler"),
/* 34 */   WITHDRAW("withdraw", "撤回", "instanceWithdrawActionHandler"),
/*    */   
/* 36 */   CREATE("create", "创建时", "null"),
/* 37 */   END("end", "流程结束", "null");
/*    */ 
/*    */   
/* 40 */   private String key = "";
/*    */   
/* 42 */   private String name = "";
/*    */   
/* 44 */   private String beanId = "";
/*    */ 
/*    */   
/*    */   ActionType(String key, String name, String beanId) {
/* 48 */     this.key = key;
/* 49 */     this.name = name;
/* 50 */     this.beanId = beanId;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 55 */   public String getKey() { return this.key; }
/*    */ 
/*    */ 
/*    */   
/* 59 */   public void setKey(String key) { this.key = key; }
/*    */ 
/*    */ 
/*    */   
/* 63 */   public String getName() { return this.name; }
/*    */ 
/*    */ 
/*    */   
/* 67 */   public String getBeanId() { return this.beanId; }
/*    */ 
/*    */ 
/*    */   
/* 71 */   public void setName(String value) { this.name = value; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 76 */   public String toString() { return this.key; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static ActionType fromKey(String key) {
/* 86 */     for (ActionType c : values()) {
/* 87 */       if (c.getKey().equalsIgnoreCase(key))
/* 88 */         return c; 
/*    */     } 
/* 90 */     throw new BusinessException(BpmStatusCode.NO_TASK_ACTION);
/*    */   }
/*    */ }


/* Location:              D:\Repository\com\dstz\agilebpm\wf-api\1.5.1\wf-api-1.5.1.jar!\com\dstz\bpm\api\constant\ActionType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.0
 */