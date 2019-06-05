/*     */ package com.dstz.bpm.core.model;
/*     */ 
/*     */ import com.dstz.base.api.model.IDModel;
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
/*     */ public class BpmBusLink
/*     */   implements IDModel
/*     */ {
/*     */   protected String id;
/*     */   protected String defId;
/*     */   protected String v;
/*     */   protected String bizId;
/*     */   protected String w;
/*     */   
/*  42 */   public void setId(String id) { this.id = id; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  50 */   public String getId() { return this.id; }
/*     */ 
/*     */ 
/*     */   
/*  54 */   public void setDefId(String defId) { this.defId = defId; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  62 */   public String getDefId() { return this.defId; }
/*     */ 
/*     */ 
/*     */   
/*  66 */   public void setInstId(String instId) { this.v = instId; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  74 */   public String getInstId() { return this.v; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  84 */   public String toString() { return (new ToStringBuilder(this))
/*  85 */       .append("id", this.id)
/*  86 */       .append("defId", this.defId)
/*  87 */       .append("instId", this.v)
/*  88 */       .append("bizKey", this.bizId)
/*  89 */       .append("bizIdentify", this.w)
/*  90 */       .toString(); }
/*     */ 
/*     */ 
/*     */   
/*  94 */   public String getBizId() { return this.bizId; }
/*     */ 
/*     */ 
/*     */   
/*  98 */   public void setBizId(String bizId) { this.bizId = bizId; }
/*     */ 
/*     */ 
/*     */   
/* 102 */   public String getBizCode() { return this.w; }
/*     */ 
/*     */ 
/*     */   
/* 106 */   public void setBizCode(String bizCode) { this.w = bizCode; }
/*     */ }


/* Location:              D:\Repository\com\dstz\agilebpm\wf-core\1.5.1\wf-core-1.5.1-pg.jar!\com\dstz\bpm\core\model\BpmBusLink.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.0
 */