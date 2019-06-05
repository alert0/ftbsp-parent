/*     */ package com.dstz.bpm.core.model;
/*     */ 
/*     */ import com.alibaba.fastjson.annotation.JSONField;
/*     */ import com.dstz.base.api.model.IBaseModel;
/*     */ import com.dstz.bpm.api.model.def.IBpmDefinition;
/*     */ import java.util.Date;
/*     */ import javax.xml.bind.annotation.XmlAccessType;
/*     */ import javax.xml.bind.annotation.XmlAccessorType;
/*     */ import javax.xml.bind.annotation.XmlAttribute;
/*     */ import javax.xml.bind.annotation.XmlElement;
/*     */ import javax.xml.bind.annotation.XmlRootElement;
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
/*     */ @XmlRootElement(name = "BpmDefinition")
/*     */ @XmlAccessorType(XmlAccessType.NONE)
/*     */ public class BpmDefinition
/*     */   implements IBaseModel, IBpmDefinition
/*     */ {
/*     */   protected String id;
/*     */   @XmlAttribute(name = "name")
/*     */   protected String name;
/*     */   @XmlAttribute(name = "key")
/*     */   protected String key;
/*     */   @XmlAttribute(name = "desc")
/*     */   protected String desc;
/*     */   @XmlAttribute(name = "typeId")
/*     */   protected String x;
/*     */   @XmlAttribute(name = "status")
/*     */   protected String status;
/*     */   protected String y;
/*     */   protected String z;
/*     */   protected String A;
/*     */   protected Integer version;
/*     */   protected String B;
/*     */   protected String C;
/*     */   protected String createBy;
/*     */   protected Date createTime;
/*     */   protected String D;
/*     */   protected String updateBy;
/*     */   protected Date updateTime;
/*     */   @XmlAttribute(name = "supportMobile")
/* 119 */   protected Integer supportMobile = Integer.valueOf(0); public BpmDefinition() { this.supportMobile = Integer.valueOf(0); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @XmlElement(name = "defSetting")
/*     */   @JSONField(serialize = false)
/*     */   protected String E;
/*     */ 
/*     */ 
/*     */   
/*     */   protected Integer rev;
/*     */ 
/*     */ 
/*     */   
/* 135 */   public void setId(String id) { this.id = id; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 144 */   public String getId() { return this.id; }
/*     */ 
/*     */ 
/*     */   
/* 148 */   public void setName(String name) { this.name = name; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 156 */   public String getName() { return this.name; }
/*     */ 
/*     */ 
/*     */   
/* 160 */   public void setKey(String key) { this.key = key; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 168 */   public String getKey() { return this.key; }
/*     */ 
/*     */ 
/*     */   
/* 172 */   public void setDesc(String desc) { this.desc = desc; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 180 */   public String getDesc() { return this.desc; }
/*     */ 
/*     */ 
/*     */   
/* 184 */   public void setTypeId(String typeId) { this.x = typeId; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 192 */   public String getTypeId() { return this.x; }
/*     */ 
/*     */ 
/*     */   
/* 196 */   public void setStatus(String status) { this.status = status; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 204 */   public String getStatus() { return this.status; }
/*     */ 
/*     */ 
/*     */   
/* 208 */   public void setActDefId(String actDefId) { this.y = actDefId; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 216 */   public String getActDefId() { return this.y; }
/*     */ 
/*     */ 
/*     */   
/* 220 */   public void setActModelId(String actModelId) { this.z = actModelId; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 228 */   public String getActModelId() { return this.z; }
/*     */ 
/*     */ 
/*     */   
/* 232 */   public void setActDeployId(String actDeployId) { this.A = actDeployId; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 240 */   public String getActDeployId() { return this.A; }
/*     */ 
/*     */ 
/*     */   
/* 244 */   public void setVersion(Integer version) { this.version = version; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 252 */   public Integer getVersion() { return this.version; }
/*     */ 
/*     */ 
/*     */   
/* 256 */   public void setMainDefId(String mainDefId) { this.B = mainDefId; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 264 */   public String getMainDefId() { return this.B; }
/*     */ 
/*     */ 
/*     */   
/* 268 */   public void setIsMain(String isMain) { this.C = isMain; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 276 */   public String getIsMain() { return this.C; }
/*     */ 
/*     */ 
/*     */   
/* 280 */   public void setCreateBy(String createBy) { this.createBy = createBy; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 288 */   public String getCreateBy() { return this.createBy; }
/*     */ 
/*     */ 
/*     */   
/* 292 */   public void setCreateTime(Date createTime) { this.createTime = createTime; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 300 */   public Date getCreateTime() { return this.createTime; }
/*     */ 
/*     */ 
/*     */   
/* 304 */   public void setCreateOrgId(String createOrgId) { this.D = createOrgId; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 312 */   public String getCreateOrgId() { return this.D; }
/*     */ 
/*     */ 
/*     */   
/* 316 */   public void setUpdateBy(String updateBy) { this.updateBy = updateBy; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 324 */   public String getUpdateBy() { return this.updateBy; }
/*     */ 
/*     */ 
/*     */   
/* 328 */   public void setUpdateTime(Date updateTime) { this.updateTime = updateTime; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 336 */   public Date getUpdateTime() { return this.updateTime; }
/*     */ 
/*     */ 
/*     */   
/* 340 */   public void setSupportMobile(Integer supportMobile) { this.supportMobile = supportMobile; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 348 */   public Integer getSupportMobile() { return this.supportMobile; }
/*     */ 
/*     */ 
/*     */   
/* 352 */   public void setDefSetting(String defSetting) { this.E = defSetting; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 360 */   public String getDefSetting() { return this.E; }
/*     */ 
/*     */ 
/*     */   
/* 364 */   public void setRev(Integer rev) { this.rev = rev; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 372 */   public Integer getRev() { return this.rev; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 378 */   public String toString() { return (new ToStringBuilder(this))
/* 379 */       .append("id", this.id)
/* 380 */       .append("name", this.name)
/* 381 */       .append("key", this.key)
/* 382 */       .append("desc", this.desc)
/* 383 */       .append("typeId", this.x)
/* 384 */       .append("status", this.status)
/* 385 */       .append("actDefId", this.y)
/* 386 */       .append("actModelId", this.z)
/* 387 */       .append("actDeployId", this.A)
/* 388 */       .append("version", this.version)
/* 389 */       .append("mainDefId", this.B)
/* 390 */       .append("isMain", this.C)
/* 391 */       .append("createBy", this.createBy)
/* 392 */       .append("createTime", this.createTime)
/* 393 */       .append("createOrgId", this.D)
/* 394 */       .append("updateBy", this.updateBy)
/* 395 */       .append("updateTime", this.updateTime)
/* 396 */       .append("supportMobile", this.supportMobile)
/* 397 */       .append("defSetting", this.E)
/* 398 */       .append("rev", this.rev)
/* 399 */       .toString(); }
/*     */ }


/* Location:              D:\Repository\com\dstz\agilebpm\wf-core\1.5.1\wf-core-1.5.1-pg.jar!\com\dstz\bpm\core\model\BpmDefinition.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.0
 */