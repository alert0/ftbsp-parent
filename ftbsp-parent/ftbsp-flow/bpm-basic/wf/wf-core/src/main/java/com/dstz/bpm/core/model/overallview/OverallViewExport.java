/*    */ package com.dstz.bpm.core.model.overallview;
/*    */ 
/*    */ import com.alibaba.fastjson.JSONArray;
/*    */ import com.dstz.bpm.core.model.BpmDefinition;
/*    */ import javax.xml.bind.annotation.XmlAccessType;
/*    */ import javax.xml.bind.annotation.XmlAccessorType;
/*    */ import javax.xml.bind.annotation.XmlElement;
/*    */ import javax.xml.bind.annotation.XmlRootElement;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @XmlRootElement(name = "agilebpmXml")
/*    */ @XmlAccessorType(XmlAccessType.FIELD)
/*    */ public class OverallViewExport
/*    */ {
/*    */   @XmlElement(name = "bpmDefinition", type = BpmDefinition.class)
/*    */   private BpmDefinition S;
/*    */   @XmlElement(name = "bpmnXml")
/*    */   private String W;
/*    */   @XmlElement(name = "modelEditorJson")
/*    */   private String aw;
/*    */   @XmlElement(name = "permission")
/* 26 */   private JSONArray av = new JSONArray();
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 31 */   public BpmDefinition getBpmDefinition() { return this.S; }
/*    */ 
/*    */   
/* 34 */   public void setBpmDefinition(BpmDefinition bpmDefinition) { this.S = bpmDefinition; }
/*    */ 
/*    */   
/* 37 */   public String getBpmnXml() { return this.W; }
/*    */ 
/*    */   
/* 40 */   public JSONArray getPermission() { return this.av; }
/*    */ 
/*    */   
/* 43 */   public void setPermission(JSONArray permission) { this.av = permission; }
/*    */ 
/*    */   
/* 46 */   public void setBpmnXml(String bpmnXml) { this.W = bpmnXml; }
/*    */ 
/*    */   
/* 49 */   public String getModelEditorJson() { return this.aw; }
/*    */ 
/*    */   
/* 52 */   public void setModelEditorJson(String modelEditorJson) { this.aw = modelEditorJson; }
/*    */ }


/* Location:              D:\Repository\com\dstz\agilebpm\wf-core\1.5.1\wf-core-1.5.1-pg.jar!\com\dstz\bpm\core\model\overallview\OverallViewExport.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.0
 */