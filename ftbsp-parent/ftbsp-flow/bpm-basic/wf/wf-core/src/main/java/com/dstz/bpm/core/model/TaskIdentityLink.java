/*     */ package com.dstz.bpm.core.model;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import org.apache.commons.lang3.builder.ToStringBuilder;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TaskIdentityLink
/*     */   implements Serializable
/*     */ {
/*     */   public static final String ar = "user";
/*     */   protected String id;
/*     */   protected String taskId;
/*     */   protected String v;
/*     */   protected String type;
/*     */   protected String as;
/*     */   protected String identity;
/*     */   protected String at;
/*     */   
/*  54 */   public void setId(String id) { this.id = id; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  62 */   public String getId() { return this.id; }
/*     */ 
/*     */ 
/*     */   
/*  66 */   public void setTaskId(String taskId) { this.taskId = taskId; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  74 */   public String getTaskId() { return this.taskId; }
/*     */ 
/*     */ 
/*     */   
/*  78 */   public void setInstId(String instId) { this.v = instId; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  86 */   public String getInstId() { return this.v; }
/*     */ 
/*     */ 
/*     */   
/*  90 */   public void setType(String type) { this.type = type; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  98 */   public String getType() { return this.type; }
/*     */ 
/*     */ 
/*     */   
/* 102 */   public void setIdentityName(String identityName) { this.as = identityName; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 110 */   public String getIdentityName() { return this.as; }
/*     */ 
/*     */ 
/*     */   
/* 114 */   public void setIdentity(String identity) { this.identity = identity; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 122 */   public String getIdentity() { return this.identity; }
/*     */ 
/*     */ 
/*     */   
/* 126 */   public void setPermissionCode(String permissionCode) { this.at = permissionCode; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 134 */   public String getPermissionCode() { return this.at; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 140 */   public String toString() { return (new ToStringBuilder(this))
/* 141 */       .append("id", this.id)
/* 142 */       .append("taskId", this.taskId)
/* 143 */       .append("instId", this.v)
/* 144 */       .append("type", this.type)
/* 145 */       .append("identityName", this.as)
/* 146 */       .append("identity", this.identity)
/* 147 */       .append("permissionCode", this.at)
/* 148 */       .toString(); }
/*     */ }


/* Location:              D:\Repository\com\dstz\agilebpm\wf-core\1.5.1\wf-core-1.5.1-pg.jar!\com\dstz\bpm\core\model\TaskIdentityLink.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.0
 */