/*    */ package com.dstz.bpm.plugin.global.nodemessage.def;
/*    */ 
/*    */ import com.dstz.bpm.api.engine.plugin.def.UserAssignRule;
/*    */ import com.dstz.bpm.engine.plugin.plugindef.AbstractBpmExecutionPluginDef;
/*    */ import java.util.List;
/*    */ import org.hibernate.validator.constraints.NotBlank;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class NodeMessage
/*    */   extends AbstractBpmExecutionPluginDef
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   @NotBlank(message = "节点消息描述不能为空")
/*    */   private String desc;
/*    */   private String h;
/*    */   @NotBlank
/*    */   private String event;
/*    */   private String condition;
/*    */   private List<UserAssignRule> i;
/*    */   @NotBlank
/*    */   private String j;
/*    */   private String k;
/*    */   private String l;
/*    */   
/* 29 */   public String getDesc() { return this.desc; }
/*    */ 
/*    */ 
/*    */   
/* 33 */   public void setDesc(String desc) { this.desc = desc; }
/*    */ 
/*    */ 
/*    */   
/* 37 */   public String getNodeId() { return this.h; }
/*    */ 
/*    */ 
/*    */   
/* 41 */   public void setNodeId(String nodeId) { this.h = nodeId; }
/*    */ 
/*    */ 
/*    */   
/* 45 */   public String getEvent() { return this.event; }
/*    */ 
/*    */ 
/*    */   
/* 49 */   public void setEvent(String event) { this.event = event; }
/*    */ 
/*    */ 
/*    */   
/* 53 */   public String getCondition() { return this.condition; }
/*    */ 
/*    */ 
/*    */   
/* 57 */   public void setCondition(String condition) { this.condition = condition; }
/*    */ 
/*    */ 
/*    */   
/* 61 */   public List<UserAssignRule> getUserRules() { return this.i; }
/*    */ 
/*    */ 
/*    */   
/* 65 */   public void setUserRules(List<UserAssignRule> userRules) { this.i = userRules; }
/*    */ 
/*    */ 
/*    */   
/* 69 */   public String getMsgType() { return this.j; }
/*    */ 
/*    */ 
/*    */   
/* 73 */   public void setMsgType(String msgType) { this.j = msgType; }
/*    */ 
/*    */ 
/*    */   
/* 77 */   public String getHtmlTemplate() { return this.k; }
/*    */ 
/*    */ 
/*    */   
/* 81 */   public void setHtmlTemplate(String htmlTemplate) { this.k = htmlTemplate; }
/*    */ 
/*    */ 
/*    */   
/* 85 */   public String getTextTemplate() { return this.l; }
/*    */ 
/*    */ 
/*    */   
/* 89 */   public void setTextTemplate(String textTemplate) { this.l = textTemplate; }
/*    */ }


/* Location:              D:\Repository\com\dstz\agilebpm\wf-plugin\1.5.1\wf-plugin-1.5.1-pg.jar!\com\dstz\bpm\plugin\global\nodemessage\def\NodeMessage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.0
 */