/*    */ package com.dstz.bpm.api.model.def;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import org.hibernate.validator.constraints.NotBlank;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class NodeInit
/*    */   implements Serializable
/*    */ {
/*    */   @NotBlank(message = "节点不能为空")
/* 13 */   private String nodeId = "";
/*    */   
/*    */   @NotBlank(message = "节点初始化描述不能为空")
/* 16 */   private String desc = "";
/*    */ 
/*    */   
/*    */   private String beforeShow;
/*    */ 
/*    */   
/*    */   private String whenSave;
/*    */ 
/*    */   
/* 25 */   public String getNodeId() { return this.nodeId; }
/*    */ 
/*    */ 
/*    */   
/* 29 */   public void setNodeId(String nodeId) { this.nodeId = nodeId; }
/*    */ 
/*    */ 
/*    */   
/* 33 */   public String getDesc() { return this.desc; }
/*    */ 
/*    */ 
/*    */   
/* 37 */   public void setDesc(String desc) { this.desc = desc; }
/*    */ 
/*    */ 
/*    */   
/* 41 */   public String getBeforeShow() { return this.beforeShow; }
/*    */ 
/*    */ 
/*    */   
/* 45 */   public void setBeforeShow(String beforeShow) { this.beforeShow = beforeShow; }
/*    */ 
/*    */ 
/*    */   
/* 49 */   public String getWhenSave() { return this.whenSave; }
/*    */ 
/*    */ 
/*    */   
/* 53 */   public void setWhenSave(String whenSave) { this.whenSave = whenSave; }
/*    */ }


/* Location:              D:\Repository\com\dstz\agilebpm\wf-api\1.5.1\wf-api-1.5.1.jar!\com\dstz\bpm\api\model\def\NodeInit.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.0
 */