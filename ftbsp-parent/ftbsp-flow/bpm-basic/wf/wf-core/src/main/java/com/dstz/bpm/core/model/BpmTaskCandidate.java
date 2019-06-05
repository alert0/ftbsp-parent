/*     */ package com.dstz.bpm.core.model;
/*     */ 
/*     */ import com.dstz.base.api.model.IBaseModel;
/*     */ import java.util.Date;
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
/*     */ public class BpmTaskCandidate
/*     */   implements IBaseModel
/*     */ {
/*     */   protected String id;
/*     */   protected String taskId;
/*     */   protected String type;
/*     */   protected String ah;
/*     */   protected String v;
/*     */   
/*  44 */   public void setId(String id) { this.id = id; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  52 */   public String getId() { return this.id; }
/*     */ 
/*     */ 
/*     */   
/*  56 */   public void setTaskId(String taskId) { this.taskId = taskId; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  64 */   public String getTaskId() { return this.taskId; }
/*     */ 
/*     */ 
/*     */   
/*  68 */   public void setType(String type) { this.type = type; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  76 */   public String getType() { return this.type; }
/*     */ 
/*     */ 
/*     */   
/*  80 */   public void setExecutor(String executor) { this.ah = executor; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  88 */   public String getExecutor() { return this.ah; }
/*     */ 
/*     */ 
/*     */   
/*  92 */   public void setInstId(String instId) { this.v = instId; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 100 */   public String getInstId() { return this.v; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 106 */   public String toString() { return (new ToStringBuilder(this))
/* 107 */       .append("id", this.id)
/* 108 */       .append("taskId", this.taskId)
/* 109 */       .append("type", this.type)
/* 110 */       .append("executor", this.ah)
/* 111 */       .append("instId", this.v)
/* 112 */       .toString(); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 118 */   public String getCreateBy() { return null; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCreateBy(String createBy) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 130 */   public Date getCreateTime() { return null; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCreateTime(Date createtime) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 142 */   public Date getUpdateTime() { return null; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setUpdateTime(Date updatetime) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 154 */   public String getUpdateBy() { return null; }
/*     */   
/*     */   public void setUpdateBy(String updateBy) {}
/*     */ }


/* Location:              D:\Repository\com\dstz\agilebpm\wf-core\1.5.1\wf-core-1.5.1-pg.jar!\com\dstz\bpm\core\model\BpmTaskCandidate.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.0
 */