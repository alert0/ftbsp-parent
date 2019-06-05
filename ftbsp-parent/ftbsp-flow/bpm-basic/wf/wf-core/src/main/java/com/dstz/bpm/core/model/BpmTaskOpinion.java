/*     */ package com.dstz.bpm.core.model;
/*     */ 
/*     */ import com.dstz.base.api.model.IBaseModel;
/*     */ import com.dstz.bpm.api.model.task.IBpmTaskOpinion;
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
/*     */ public class BpmTaskOpinion
/*     */   implements IBaseModel, IBpmTaskOpinion
/*     */ {
/*     */   protected String id;
/*     */   protected String v;
/*     */   protected String ai;
/*     */   protected String taskId;
/*     */   protected String aj;
/*     */   protected String taskName;
/*     */   protected String ak;
/*     */   private String al;
/*     */   protected String am;
/*     */   protected String an;
/*     */   protected String ao;
/*     */   protected String status;
/*     */   protected String formId;
/*     */   protected String createBy;
/*     */   protected Date createTime;
/*     */   protected Date ae;
/*     */   protected Long af;
/*     */   
/* 106 */   public void setId(String id) { this.id = id; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 114 */   public String getId() { return this.id; }
/*     */ 
/*     */ 
/*     */   
/* 118 */   public void setInstId(String instId) { this.v = instId; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 126 */   public String getInstId() { return this.v; }
/*     */ 
/*     */ 
/*     */   
/* 130 */   public void setSupInstId(String supInstId) { this.ai = supInstId; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 138 */   public String getSupInstId() { return this.ai; }
/*     */ 
/*     */ 
/*     */   
/* 142 */   public void setTaskId(String taskId) { this.taskId = taskId; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 150 */   public String getTaskId() { return this.taskId; }
/*     */ 
/*     */ 
/*     */   
/* 154 */   public void setTaskKey(String taskKey) { this.aj = taskKey; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 162 */   public String getTaskKey() { return this.aj; }
/*     */ 
/*     */ 
/*     */   
/* 166 */   public void setTaskName(String taskName) { this.taskName = taskName; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 174 */   public String getTaskName() { return this.taskName; }
/*     */ 
/*     */ 
/*     */   
/* 178 */   public void setToken(String token) { this.ak = token; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 186 */   public String getToken() { return this.ak; }
/*     */ 
/*     */ 
/*     */   
/* 190 */   public void setApprover(String approver) { this.am = approver; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 198 */   public String getApprover() { return this.am; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 203 */   public void setApproverName(String approverName) { this.an = approverName; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 211 */   public String getApproverName() { return this.an; }
/*     */ 
/*     */ 
/*     */   
/* 215 */   public void setOpinion(String opinion) { this.ao = opinion; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 223 */   public String getOpinion() { return this.ao; }
/*     */ 
/*     */ 
/*     */   
/* 227 */   public Date getApproveTime() { return this.ae; }
/*     */ 
/*     */ 
/*     */   
/* 231 */   public void setApproveTime(Date approveTime) { this.ae = approveTime; }
/*     */ 
/*     */ 
/*     */   
/* 235 */   public void setStatus(String status) { this.status = status; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 243 */   public String getStatus() { return this.status; }
/*     */ 
/*     */ 
/*     */   
/* 247 */   public void setFormId(String formId) { this.formId = formId; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 255 */   public String getFormId() { return this.formId; }
/*     */ 
/*     */ 
/*     */   
/* 259 */   public void setCreateBy(String createBy) { this.createBy = createBy; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 267 */   public String getCreateBy() { return this.createBy; }
/*     */ 
/*     */ 
/*     */   
/* 271 */   public void setCreateTime(Date createTime) { this.createTime = createTime; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 279 */   public Date getCreateTime() { return this.createTime; }
/*     */ 
/*     */ 
/*     */   
/* 283 */   public void setDurMs(Long durMs) { this.af = durMs; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 291 */   public Long getDurMs() { return this.af; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 297 */   public String toString() { return (new ToStringBuilder(this))
/* 298 */       .append("id", this.id)
/* 299 */       .append("instId", this.v)
/* 300 */       .append("supInstId", this.ai)
/* 301 */       .append("taskId", this.taskId)
/* 302 */       .append("taskKey", this.aj)
/* 303 */       .append("taskName", this.taskName)
/* 304 */       .append("token", this.ak)
/* 305 */       .append("approver", this.am)
/* 306 */       .append("approverName", this.an)
/* 307 */       .append("opinion", this.ao)
/* 308 */       .append("status", this.status)
/* 309 */       .append("formId", this.formId)
/* 310 */       .append("createBy", this.createBy)
/* 311 */       .append("createTime", this.createTime)
/* 312 */       .append("approveTime", this.ae)
/* 313 */       .append("durMs", this.af)
/* 314 */       .toString(); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 320 */   public Date getUpdateTime() { return null; }
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
/* 332 */   public String getUpdateBy() { return null; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setUpdateBy(String updateBy) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 342 */   public String getAssignInfo() { return this.al; }
/*     */ 
/*     */ 
/*     */   
/* 346 */   public void setAssignInfo(String assignInfo) { this.al = assignInfo; }
/*     */ }


/* Location:              D:\Repository\com\dstz\agilebpm\wf-core\1.5.1\wf-core-1.5.1-pg.jar!\com\dstz\bpm\core\model\BpmTaskOpinion.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.0
 */