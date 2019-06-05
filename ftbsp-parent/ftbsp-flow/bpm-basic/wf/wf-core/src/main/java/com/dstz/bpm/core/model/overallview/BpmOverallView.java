/*     */ package com.dstz.bpm.core.model.overallview;
/*     */ 
/*     */ import com.alibaba.fastjson.JSONArray;
/*     */ import com.alibaba.fastjson.JSONObject;
/*     */ import com.dstz.bpm.core.model.BpmDefinition;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BpmOverallView
/*     */ {
/*     */   public static final String P = "override";
/*     */   public static final String Q = "edit";
/*     */   public static final String R = "newVersion";
/*     */   private BpmDefinition S;
/*  30 */   private String importType = "edit";
/*     */   
/*     */   private String defId;
/*     */   
/*     */   private Boolean U;
/*     */   
/*     */   private JSONObject V;
/*     */   
/*     */   private String W;
/*     */   
/*     */   private String au;
/*     */   
/*  42 */   private JSONArray av = new JSONArray();
/*     */ 
/*     */ 
/*     */   
/*  46 */   public String getImportType() { return this.importType; }
/*     */ 
/*     */ 
/*     */   
/*  50 */   public void setImportType(String importType) { this.importType = importType; }
/*     */ 
/*     */ 
/*     */   
/*  54 */   public Boolean getIsUpdateVersion() { return this.U; }
/*     */ 
/*     */ 
/*     */   
/*  58 */   public void setIsUpdateVersion(Boolean isUpdateVersion) { this.U = isUpdateVersion; }
/*     */ 
/*     */ 
/*     */   
/*  62 */   public JSONObject getDefSetting() { return this.V; }
/*     */ 
/*     */ 
/*     */   
/*  66 */   public void setDefSetting(JSONObject defSetting) { this.V = defSetting; }
/*     */ 
/*     */ 
/*     */   
/*  70 */   public BpmDefinition getBpmDefinition() { return this.S; }
/*     */ 
/*     */ 
/*     */   
/*  74 */   public void setBpmDefinition(BpmDefinition bpmDefinition) { this.S = bpmDefinition; }
/*     */ 
/*     */ 
/*     */   
/*  78 */   public String getDefId() { return this.defId; }
/*     */ 
/*     */ 
/*     */   
/*  82 */   public void setDefId(String defId) { this.defId = defId; }
/*     */ 
/*     */ 
/*     */   
/*  86 */   public String getBpmnXml() { return this.W; }
/*     */ 
/*     */ 
/*     */   
/*  90 */   public void setBpmnXml(String bpmnXml) { this.W = bpmnXml; }
/*     */ 
/*     */ 
/*     */   
/*  94 */   public JSONArray getPermission() { return this.av; }
/*     */ 
/*     */ 
/*     */   
/*  98 */   public void setPermission(JSONArray permission) { this.av = permission; }
/*     */ 
/*     */ 
/*     */   
/* 102 */   public String getModelJson() { return this.au; }
/*     */ 
/*     */ 
/*     */   
/* 106 */   public void setModelJson(String modelJson) { this.au = modelJson; }
/*     */ }


/* Location:              D:\Repository\com\dstz\agilebpm\wf-core\1.5.1\wf-core-1.5.1-pg.jar!\com\dstz\bpm\core\model\overallview\BpmOverallView.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.0
 */