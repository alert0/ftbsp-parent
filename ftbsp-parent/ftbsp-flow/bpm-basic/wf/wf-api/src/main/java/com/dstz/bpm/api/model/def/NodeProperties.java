/*     */ package com.dstz.bpm.api.model.def;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class NodeProperties
/*     */   implements Serializable
/*     */ {
/*     */   public static final String BACK_MODEL_NORMAL = "normal";
/*     */   public static final String BACK_MODEL_BACK = "back";
/*     */   public static final String BACK_USER_MODEL_HISTORY = "history";
/*     */   public static final String BACK_USER_MODEL_NORMAL = "normal";
/*     */   private static final long serialVersionUID = -3157546646728816168L;
/*  28 */   private String nodeId = "";
/*     */   
/*  30 */   private String jumpType = "";
/*     */   
/*     */   private boolean allowExecutorEmpty = true;
/*     */   
/*  34 */   private String backMode = "normal";
/*     */   
/*  36 */   private String backNode = "";
/*     */   
/*  38 */   private String backUserMode = "history";
/*     */   
/*  40 */   private String freeSelectUser = "no";
/*     */   
/*     */   private boolean freeSelectNode = false;
/*     */ 
/*     */   
/*  45 */   public String getNodeId() { return this.nodeId; }
/*     */ 
/*     */ 
/*     */   
/*  49 */   public void setNodeId(String nodeId) { this.nodeId = nodeId; }
/*     */ 
/*     */ 
/*     */   
/*  53 */   public String getJumpType() { return this.jumpType; }
/*     */ 
/*     */ 
/*     */   
/*  57 */   public void setJumpType(String jumpType) { this.jumpType = jumpType; }
/*     */ 
/*     */ 
/*     */   
/*  61 */   public boolean isAllowExecutorEmpty() { return this.allowExecutorEmpty; }
/*     */ 
/*     */ 
/*     */   
/*  65 */   public void setAllowExecutorEmpty(boolean allowExecutorEmpty) { this.allowExecutorEmpty = allowExecutorEmpty; }
/*     */ 
/*     */ 
/*     */   
/*  69 */   public String getBackMode() { return this.backMode; }
/*     */ 
/*     */ 
/*     */   
/*  73 */   public void setBackMode(String backMode) { this.backMode = backMode; }
/*     */ 
/*     */ 
/*     */   
/*  77 */   public String getBackNode() { return this.backNode; }
/*     */ 
/*     */ 
/*     */   
/*  81 */   public void setBackNode(String backNode) { this.backNode = backNode; }
/*     */ 
/*     */ 
/*     */   
/*  85 */   public String getBackUserMode() { return this.backUserMode; }
/*     */ 
/*     */ 
/*     */   
/*  89 */   public boolean isFreeSelectNode() { return this.freeSelectNode; }
/*     */ 
/*     */ 
/*     */   
/*  93 */   public void setFreeSelectNode(boolean freeSelectNode) { this.freeSelectNode = freeSelectNode; }
/*     */ 
/*     */ 
/*     */   
/*  97 */   public void setBackUserMode(String backUserMode) { this.backUserMode = backUserMode; }
/*     */ 
/*     */ 
/*     */   
/* 101 */   public String getFreeSelectUser() { return this.freeSelectUser; }
/*     */ 
/*     */ 
/*     */   
/* 105 */   public void setFreeSelectUser(String freeSelectUser) { this.freeSelectUser = freeSelectUser; }
/*     */ }


/* Location:              D:\Repository\com\dstz\agilebpm\wf-api\1.5.1\wf-api-1.5.1.jar!\com\dstz\bpm\api\model\def\NodeProperties.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.0
 */