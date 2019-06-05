/*     */ package com.dstz.bpm.core.model;
/*     */ 
/*     */ import com.dstz.base.api.model.IBaseModel;
/*     */ import com.dstz.bpm.api.model.inst.IBpmInstance;
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
/*     */ public class BpmInstance
/*     */   implements IBaseModel, IBpmInstance
/*     */ {
/*     */   protected String id;
/*     */   protected String subject;
/*     */   protected String defId;
/*     */   protected String y;
/*     */   protected String F;
/*     */   protected String defName;
/*     */   protected String G;
/*     */   protected String status;
/*     */   protected Date endTime;
/*     */   protected Long H;
/*     */   protected String x;
/*     */   protected String I;
/*     */   protected String createBy;
/*     */   protected String creator;
/*     */   protected Date createTime;
/*     */   protected String D;
/*     */   protected String updateBy;
/*     */   protected Date updateTime;
/*     */   protected String J;
/*     */   protected String K;
/* 119 */   protected Short L = Short.valueOf((short)0);
/*     */ 
/*     */ 
/*     */   
/*     */   protected String M;
/*     */ 
/*     */ 
/*     */   
/*     */   protected Integer supportMobile;
/*     */ 
/*     */ 
/*     */   
/*     */   protected String N;
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean O = true;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 140 */   public void setId(String id) { this.id = id; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 148 */   public String getId() { return this.id; }
/*     */ 
/*     */ 
/*     */   
/* 152 */   public void setSubject(String subject) { this.subject = subject; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 160 */   public String getSubject() { return this.subject; }
/*     */ 
/*     */ 
/*     */   
/* 164 */   public void setDefId(String defId) { this.defId = defId; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 172 */   public String getDefId() { return this.defId; }
/*     */ 
/*     */ 
/*     */   
/* 176 */   public void setActDefId(String actDefId) { this.y = actDefId; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 184 */   public String getActDefId() { return this.y; }
/*     */ 
/*     */ 
/*     */   
/* 188 */   public void setDefKey(String defKey) { this.F = defKey; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 196 */   public String getDefKey() { return this.F; }
/*     */ 
/*     */ 
/*     */   
/* 200 */   public void setDefName(String defName) { this.defName = defName; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 208 */   public String getDefName() { return this.defName; }
/*     */ 
/*     */ 
/*     */   
/* 212 */   public void setBizKey(String bizKey) { this.G = bizKey; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getBizKey() {
/* 220 */     if (this.G == null) {
/* 221 */       return "";
/*     */     }
/* 223 */     return this.G;
/*     */   }
/*     */ 
/*     */   
/* 227 */   public void setStatus(String status) { this.status = status; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 235 */   public String getStatus() { return this.status; }
/*     */ 
/*     */ 
/*     */   
/* 239 */   public void setEndTime(Date endTime) { this.endTime = endTime; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 247 */   public Date getEndTime() { return this.endTime; }
/*     */ 
/*     */ 
/*     */   
/* 251 */   public void setDuration(Long duration) { this.H = duration; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 259 */   public Long getDuration() { return this.H; }
/*     */ 
/*     */ 
/*     */   
/* 263 */   public void setTypeId(String typeId) { this.x = typeId; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 271 */   public String getTypeId() { return this.x; }
/*     */ 
/*     */ 
/*     */   
/* 275 */   public void setActInstId(String actInstId) { this.I = actInstId; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 283 */   public String getActInstId() { return this.I; }
/*     */ 
/*     */ 
/*     */   
/* 287 */   public void setCreateBy(String createBy) { this.createBy = createBy; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 295 */   public String getCreateBy() { return this.createBy; }
/*     */ 
/*     */ 
/*     */   
/* 299 */   public void setCreator(String creator) { this.creator = creator; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 307 */   public String getCreator() { return this.creator; }
/*     */ 
/*     */ 
/*     */   
/* 311 */   public void setCreateTime(Date createTime) { this.createTime = createTime; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 319 */   public Date getCreateTime() { return this.createTime; }
/*     */ 
/*     */ 
/*     */   
/* 323 */   public void setCreateOrgId(String createOrgId) { this.D = createOrgId; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 331 */   public String getCreateOrgId() { return this.D; }
/*     */ 
/*     */ 
/*     */   
/* 335 */   public void setUpdateBy(String updateBy) { this.updateBy = updateBy; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 343 */   public String getUpdateBy() { return this.updateBy; }
/*     */ 
/*     */ 
/*     */   
/* 347 */   public void setUpdateTime(Date updateTime) { this.updateTime = updateTime; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 355 */   public Date getUpdateTime() { return this.updateTime; }
/*     */ 
/*     */ 
/*     */   
/* 359 */   public void setIsFormmal(String isFormmal) { this.J = isFormmal; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 367 */   public String getIsFormmal() { return this.J; }
/*     */ 
/*     */ 
/*     */   
/* 371 */   public void setParentInstId(String parentInstId) { this.K = parentInstId; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 379 */   public String getParentInstId() { return this.K; }
/*     */ 
/*     */ 
/*     */   
/* 383 */   public void setIsForbidden(Short isForbidden) { this.L = isForbidden; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 391 */   public Short getIsForbidden() { return this.L; }
/*     */ 
/*     */ 
/*     */   
/* 395 */   public void setDataMode(String dataMode) { this.M = dataMode; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 403 */   public String getDataMode() { return this.M; }
/*     */ 
/*     */ 
/*     */   
/* 407 */   public void setSupportMobile(Integer supportMobile) { this.supportMobile = supportMobile; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 415 */   public Integer getSupportMobile() { return this.supportMobile; }
/*     */ 
/*     */ 
/*     */   
/* 419 */   public void setSuperNodeId(String superNodeId) { this.N = superNodeId; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 427 */   public String getSuperNodeId() { return this.N; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 433 */   public String toString() { return (new ToStringBuilder(this))
/* 434 */       .append("id", this.id)
/* 435 */       .append("subject", this.subject)
/* 436 */       .append("defId", this.defId)
/* 437 */       .append("actDefId", this.y)
/* 438 */       .append("defKey", this.F)
/* 439 */       .append("defName", this.defName)
/* 440 */       .append("bizKey", this.G)
/* 441 */       .append("status", this.status)
/* 442 */       .append("endTime", this.endTime)
/* 443 */       .append("duration", this.H)
/* 444 */       .append("typeId", this.x)
/* 445 */       .append("actInstId", this.I)
/* 446 */       .append("createBy", this.createBy)
/* 447 */       .append("creator", this.creator)
/* 448 */       .append("createTime", this.createTime)
/* 449 */       .append("createOrgId", this.D)
/* 450 */       .append("updateBy", this.updateBy)
/* 451 */       .append("updateTime", this.updateTime)
/* 452 */       .append("isFormmal", this.J)
/* 453 */       .append("parentInstId", this.K)
/* 454 */       .append("isForbidden", this.L)
/* 455 */       .append("dataMode", this.M)
/* 456 */       .append("supportMobile", this.supportMobile)
/* 457 */       .append("superNodeId", this.N)
/* 458 */       .toString(); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 463 */   public Boolean hasCreate() { return Boolean.valueOf(this.O); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 468 */   public void setHasCreate(Boolean hasCreate) { this.O = hasCreate.booleanValue(); }
/*     */ }


/* Location:              D:\Repository\com\dstz\agilebpm\wf-core\1.5.1\wf-core-1.5.1-pg.jar!\com\dstz\bpm\core\model\BpmInstance.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.0
 */