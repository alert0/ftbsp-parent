/*    */ package com.dstz.bpm.core.model;
/*    */ 
/*    */ import com.alibaba.fastjson.JSONObject;
/*    */ import com.dstz.bus.api.model.IBusinessPermission;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BpmOverallView
/*    */ {
/*    */   public static final String P = "override";
/*    */   public static final String Q = "edit";
/*    */   public static final String R = "newVersion";
/*    */   private BpmDefinition S;
/* 29 */   private List<IBusinessPermission> T = new ArrayList();
/*    */ 
/*    */ 
/*    */   
/* 33 */   private String importType = "edit";
/*    */ 
/*    */   
/*    */   private String defId;
/*    */   
/*    */   private Boolean U;
/*    */   
/*    */   private JSONObject V;
/*    */   
/*    */   private String W;
/*    */ 
/*    */   
/* 45 */   public List<IBusinessPermission> getFormRights() { return this.T; }
/*    */ 
/*    */ 
/*    */   
/* 49 */   public void setFormRights(List<IBusinessPermission> formRights) { this.T = formRights; }
/*    */ 
/*    */ 
/*    */   
/* 53 */   public String getImportType() { return this.importType; }
/*    */ 
/*    */ 
/*    */   
/* 57 */   public void setImportType(String importType) { this.importType = importType; }
/*    */ 
/*    */ 
/*    */   
/* 61 */   public Boolean getIsUpdateVersion() { return this.U; }
/*    */ 
/*    */ 
/*    */   
/* 65 */   public void setIsUpdateVersion(Boolean isUpdateVersion) { this.U = isUpdateVersion; }
/*    */ 
/*    */ 
/*    */   
/* 69 */   public JSONObject getDefSetting() { return this.V; }
/*    */ 
/*    */ 
/*    */   
/* 73 */   public void setDefSetting(JSONObject defSetting) { this.V = defSetting; }
/*    */ 
/*    */ 
/*    */   
/* 77 */   public BpmDefinition getBpmDefinition() { return this.S; }
/*    */ 
/*    */ 
/*    */   
/* 81 */   public void setBpmDefinition(BpmDefinition bpmDefinition) { this.S = bpmDefinition; }
/*    */ 
/*    */ 
/*    */   
/* 85 */   public String getDefId() { return this.defId; }
/*    */ 
/*    */ 
/*    */   
/* 89 */   public void setDefId(String defId) { this.defId = defId; }
/*    */ 
/*    */ 
/*    */   
/* 93 */   public String getBpmnXml() { return this.W; }
/*    */ 
/*    */ 
/*    */   
/* 97 */   public void setBpmnXml(String bpmnXml) { this.W = bpmnXml; }
/*    */ }


/* Location:              D:\Repository\com\dstz\agilebpm\wf-core\1.5.1\wf-core-1.5.1-pg.jar!\com\dstz\bpm\core\model\BpmOverallView.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.0
 */