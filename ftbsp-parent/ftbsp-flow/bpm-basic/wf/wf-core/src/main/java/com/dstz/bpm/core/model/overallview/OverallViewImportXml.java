/*    */ package com.dstz.bpm.core.model.overallview;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import javax.xml.bind.annotation.XmlAccessType;
/*    */ import javax.xml.bind.annotation.XmlAccessorType;
/*    */ import javax.xml.bind.annotation.XmlElement;
/*    */ import javax.xml.bind.annotation.XmlElements;
/*    */ import javax.xml.bind.annotation.XmlRootElement;
/*    */ 
/*    */ @XmlRootElement(name = "agilebpmXmlList")
/*    */ @XmlAccessorType(XmlAccessType.FIELD)
/*    */ public class OverallViewImportXml
/*    */ {
/*    */   @XmlElements({@XmlElement(name = "agilebpmXml", type = OverallViewExport.class)})
/* 16 */   private List<OverallViewExport> ax = new ArrayList();
/*    */ 
/*    */ 
/*    */   
/* 20 */   public List<OverallViewExport> getBpmXmlList() { return this.ax; }
/*    */ 
/*    */ 
/*    */   
/* 24 */   public void setBpmXmlList(List<OverallViewExport> bpmXmlList) { this.ax = bpmXmlList; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 29 */   public void a(OverallViewExport def) { this.ax.add(def); }
/*    */ }


/* Location:              D:\Repository\com\dstz\agilebpm\wf-core\1.5.1\wf-core-1.5.1-pg.jar!\com\dstz\bpm\core\model\overallview\OverallViewImportXml.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.0
 */