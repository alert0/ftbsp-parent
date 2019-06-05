/*     */ package com.dstz.bpm.api.constant;
/*     */ 
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public enum OpinionStatus
/*     */ {
/*  15 */   START("start", "提交"),
/*     */ 
/*     */ 
/*     */   
/*  19 */   CREATE("create", "创建"),
/*     */ 
/*     */ 
/*     */   
/*  23 */   END("end", "结束"),
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  28 */   AWAITING_CHECK("awaiting_check", "待审批"),
/*     */ 
/*     */ 
/*     */   
/*  32 */   AGREE("agree", "同意"),
/*     */ 
/*     */ 
/*     */   
/*  36 */   OPPOSE("oppose", "反对"),
/*     */ 
/*     */ 
/*     */   
/*  40 */   ABANDON("abandon", "弃权"),
/*     */ 
/*     */ 
/*     */   
/*  44 */   REJECT("reject", "驳回"),
/*     */ 
/*     */ 
/*     */   
/*  48 */   REJECT_TO_START("rejectToStart", "驳回到发起人"),
/*     */ 
/*     */ 
/*     */   
/*  52 */   REVOKER("revoker", "撤回"),
/*     */ 
/*     */ 
/*     */   
/*  56 */   REVOKER_TO_START("revoker_to_start", "撤回到发起人"),
/*     */ 
/*     */ 
/*     */   
/*  60 */   SIGN_PASSED("signPass", "会签通过"),
/*     */ 
/*     */ 
/*     */   
/*  64 */   SIGN_NOT_PASSED("signNotPass", "会签不通过"),
/*     */ 
/*     */ 
/*     */   
/*  68 */   SIGN_RECYCLE("signRecycle", "会签回收"),
/*     */ 
/*     */ 
/*     */   
/*  72 */   SKIP("skip", "跳过执行"),
/*     */   
/*  74 */   TURN("turn", "转办"),
/*     */ 
/*     */ 
/*     */   
/*  78 */   MANUAL_END("manualEnd", "人工终止");
/*     */   
/*  80 */   private String key = "";
/*     */   
/*  82 */   private String value = "";
/*     */   protected static Logger LOG;
/*     */   
/*     */   OpinionStatus(String key, String value) {
/*  86 */     this.key = key;
/*  87 */     this.value = value;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*  92 */   public String getKey() { return this.key; }
/*     */ 
/*     */ 
/*     */   
/*  96 */   public void setKey(String key) { this.key = key; }
/*     */ 
/*     */ 
/*     */   
/* 100 */   public String getValue() { return this.value; }
/*     */ 
/*     */ 
/*     */   
/* 104 */   public void setValue(String value) { this.value = value; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 109 */   public String toString() { return this.key; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static  {
/* 118 */     LOG = LoggerFactory.getLogger(OpinionStatus.class);
/*     */   } public static OpinionStatus fromKey(String key) {
/* 120 */     for (OpinionStatus c : values()) {
/* 121 */       if (c.getKey().equalsIgnoreCase(key))
/* 122 */         return c; 
/*     */     } 
/* 124 */     LOG.warn("OpinionStatus 转换失败！ 无法查找到对应的 值：{}", key);
/* 125 */     return null;
/*     */   }
/*     */   
/*     */   public static OpinionStatus getByActionName(String actionName) {
/* 129 */     ActionType action = ActionType.fromKey(actionName);
/*     */     
/* 131 */     switch (action) {
/*     */       case AGREE:
/* 133 */         return AGREE;
/* 134 */       case SIGNAGREE: return SIGN_PASSED;
/* 135 */       case OPPOSE: return OPPOSE;
/* 136 */       case SIGNOPPOSE: return SIGN_NOT_PASSED;
/* 137 */       case REJECT: return REJECT;
/* 138 */       case REJECT2START: return REJECT_TO_START;
/* 139 */       case RECOVER: return REVOKER;
/* 140 */       case START: return START;
/* 141 */       case MANUALEND: return MANUAL_END;
/*     */     } 
/* 143 */     return AWAITING_CHECK;
/*     */   }
/*     */ }


/* Location:              D:\Repository\com\dstz\agilebpm\wf-api\1.5.1\wf-api-1.5.1.jar!\com\dstz\bpm\api\constant\OpinionStatus.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.0
 */