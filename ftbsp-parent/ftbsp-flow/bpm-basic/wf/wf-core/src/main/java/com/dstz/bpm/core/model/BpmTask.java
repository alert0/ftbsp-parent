/*     */ package com.dstz.bpm.core.model;
/*     */ 
/*     */ import com.dstz.base.api.model.IBaseModel;
/*     */ import com.dstz.bpm.api.model.task.IBpmTask;
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
/*     */ public class BpmTask
/*     */   implements IBaseModel, IBpmTask
/*     */ {
/*     */   protected String id;
/*     */   protected String name;
/*     */   protected String subject;
/*     */   protected String v;
/*     */   protected String taskId;
/*     */   protected String X;
/*     */   protected String defId;
/*     */   protected String Y;
/*     */   protected String Z;
/*     */   protected String status;
/*     */   protected Integer priority;
/*     */   protected Date aa;
/*     */   protected String ab;
/*     */   protected String parentId;
/*     */   protected String I;
/*     */   protected String ac;
/*     */   protected String x;
/*     */   protected Date createTime;
/*     */   protected String createBy;
/*     */   protected Integer supportMobile;
/*     */   protected String ad;
/*     */   
/* 104 */   public String getTaskType() { return this.ab; }
/*     */ 
/*     */ 
/*     */   
/* 108 */   public void setTaskType(String taskType) { this.ab = taskType; }
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
/* 130 */   public void setId(String id) { this.id = id; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 138 */   public String getId() { return this.id; }
/*     */ 
/*     */ 
/*     */   
/* 142 */   public void setName(String name) { this.name = name; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 150 */   public String getName() { return this.name; }
/*     */ 
/*     */ 
/*     */   
/* 154 */   public void setSubject(String subject) { this.subject = subject; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 162 */   public String getSubject() { return this.subject; }
/*     */ 
/*     */ 
/*     */   
/* 166 */   public String getAssigneeNames() { return this.Z; }
/*     */ 
/*     */ 
/*     */   
/* 170 */   public void setAssigneeNames(String assigneeNames) { this.Z = assigneeNames; }
/*     */ 
/*     */ 
/*     */   
/* 174 */   public void setInstId(String instId) { this.v = instId; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 182 */   public String getInstId() { return this.v; }
/*     */ 
/*     */ 
/*     */   
/* 186 */   public void setTaskId(String taskId) { this.taskId = taskId; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 194 */   public String getTaskId() { return this.taskId; }
/*     */ 
/*     */ 
/*     */   
/* 198 */   public void setNodeId(String nodeId) { this.X = nodeId; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 206 */   public String getNodeId() { return this.X; }
/*     */ 
/*     */ 
/*     */   
/* 210 */   public void setDefId(String defId) { this.defId = defId; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 218 */   public String getDefId() { return this.defId; }
/*     */ 
/*     */ 
/*     */   
/* 222 */   public void setAssigneeId(String assigneeId) { this.Y = assigneeId; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 230 */   public String getAssigneeId() { return this.Y; }
/*     */ 
/*     */ 
/*     */   
/* 234 */   public void setStatus(String status) { this.status = status; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 242 */   public String getStatus() { return this.status; }
/*     */ 
/*     */ 
/*     */   
/* 246 */   public String getBackNode() { return this.ad; }
/*     */ 
/*     */ 
/*     */   
/* 250 */   public void setBackNode(String backNode) { this.ad = backNode; }
/*     */ 
/*     */ 
/*     */   
/* 254 */   public void setPriority(Integer priority) { this.priority = priority; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 262 */   public Integer getPriority() { return this.priority; }
/*     */ 
/*     */ 
/*     */   
/* 266 */   public void setDueTime(Date dueTime) { this.aa = dueTime; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 274 */   public Date getDueTime() { return this.aa; }
/*     */ 
/*     */ 
/*     */   
/* 278 */   public void setParentId(String parentId) { this.parentId = parentId; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 286 */   public String getParentId() { return this.parentId; }
/*     */ 
/*     */ 
/*     */   
/* 290 */   public void setActInstId(String actInstId) { this.I = actInstId; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 298 */   public String getActInstId() { return this.I; }
/*     */ 
/*     */ 
/*     */   
/* 302 */   public void setActExecutionId(String actExecutionId) { this.ac = actExecutionId; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 310 */   public String getActExecutionId() { return this.ac; }
/*     */ 
/*     */ 
/*     */   
/* 314 */   public void setTypeId(String typeId) { this.x = typeId; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 322 */   public String getTypeId() { return this.x; }
/*     */ 
/*     */ 
/*     */   
/* 326 */   public void setCreateTime(Date createTime) { this.createTime = createTime; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 334 */   public Date getCreateTime() { return this.createTime; }
/*     */ 
/*     */ 
/*     */   
/* 338 */   public void setCreateBy(String createBy) { this.createBy = createBy; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 346 */   public String getCreateBy() { return this.createBy; }
/*     */ 
/*     */ 
/*     */   
/* 350 */   public void setSupportMobile(Integer supportMobile) { this.supportMobile = supportMobile; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 358 */   public Integer getSupportMobile() { return this.supportMobile; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 364 */   public String toString() { return (new ToStringBuilder(this))
/* 365 */       .append("id", this.id)
/* 366 */       .append("name", this.name)
/* 367 */       .append("subject", this.subject)
/* 368 */       .append("instId", this.v)
/* 369 */       .append("taskId", this.taskId)
/* 370 */       .append("nodeId", this.X)
/* 371 */       .append("defId", this.defId)
/* 372 */       .append("assigneeId", this.Y)
/* 373 */       .append("status", this.status)
/* 374 */       .append("priority", this.priority)
/* 375 */       .append("dueTime", this.aa)
/* 376 */       .append("taskType", this.ab)
/* 377 */       .append("parentId", this.parentId)
/* 378 */       .append("actInstId", this.I)
/* 379 */       .append("actExecutionId", this.ac)
/* 380 */       .append("typeId", this.x)
/* 381 */       .append("createTime", this.createTime)
/* 382 */       .append("createBy", this.createBy)
/* 383 */       .append("supportMobile", this.supportMobile)
/* 384 */       .toString(); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 389 */   public String getActExecutionIdId() { return this.ac; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 394 */   public Date getUpdateTime() { return null; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setUpdateTime(Date updatetime) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 404 */   public String getUpdateBy() { return null; }
/*     */   
/*     */   public void setUpdateBy(String updateBy) {}
/*     */ }


/* Location:              D:\Repository\com\dstz\agilebpm\wf-core\1.5.1\wf-core-1.5.1-pg.jar!\com\dstz\bpm\core\model\BpmTask.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.0
 */