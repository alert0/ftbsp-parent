/*    */ package com.dstz.bpm.api.constant;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public enum NodeType
/*    */ {
/* 11 */   START("StartNoneEvent", "开始节点"),
/* 12 */   END("EndNoneEvent", "结束节点"),
/* 13 */   USERTASK("UserTask", "用户任务节点"),
/* 14 */   SIGNTASK("SignTask", "会签任务节点"),
/* 15 */   SUBPROCESS("SubProcess", "子流程"),
/* 16 */   CALLACTIVITY("CallActivity", "外部子流程"),
/* 17 */   EXCLUSIVEGATEWAY("ExclusiveGateway", "分支网关"),
/* 18 */   PARALLELGATEWAY("ParallelGateway", "同步网关"),
/* 19 */   INCLUSIVEGATEWAY("InclusiveGateway", "条件网关"),
/* 20 */   SUBSTARTGATEWAY("SubStartGateway", "内嵌子流程开始网关"),
/* 21 */   SUBENDGATEWAY("SubEndGateway", "内嵌子流程结束网关"),
/* 22 */   SUBMULTISTARTGATEWAY("SubMultiStartGateway", "多实例内嵌子流程开始网关"),
/*    */   
/* 24 */   SERVICETASK("ServiceTask", "服务任务节点");
/*    */ 
/*    */   
/* 27 */   private String key = "";
/*    */   
/* 29 */   private String value = "";
/*    */ 
/*    */   
/*    */   NodeType(String key, String value) {
/* 33 */     this.key = key;
/* 34 */     this.value = value;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 39 */   public String getKey() { return this.key; }
/*    */ 
/*    */ 
/*    */   
/* 43 */   public void setKey(String key) { this.key = key; }
/*    */ 
/*    */ 
/*    */   
/* 47 */   public String getValue() { return this.value; }
/*    */ 
/*    */ 
/*    */   
/* 51 */   public void setValue(String value) { this.value = value; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 56 */   public String toString() { return this.key; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static NodeType fromKey(String key) {
/* 66 */     for (NodeType c : values()) {
/* 67 */       if (c.getKey().equalsIgnoreCase(key))
/* 68 */         return c; 
/*    */     } 
/* 70 */     throw new IllegalArgumentException(key);
/*    */   }
/*    */ }


/* Location:              D:\Repository\com\dstz\agilebpm\wf-api\1.5.1\wf-api-1.5.1.jar!\com\dstz\bpm\api\constant\NodeType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.0
 */