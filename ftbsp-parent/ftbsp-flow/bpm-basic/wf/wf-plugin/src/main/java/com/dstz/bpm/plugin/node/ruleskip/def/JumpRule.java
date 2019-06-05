/*    */ package com.dstz.bpm.plugin.node.ruleskip.def;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import org.hibernate.validator.constraints.NotEmpty;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class JumpRule
/*    */   implements Serializable
/*    */ {
/*    */   @NotEmpty(message = "跳转规则名字不能为空")
/* 15 */   private String name = "";
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotEmpty(message = "跳转规则目标节点不能为空")
/* 21 */   private String u = "";
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotEmpty(message = "跳转规则条件不能为空")
/* 27 */   private String script = "";
/*    */ 
/*    */ 
/*    */   
/*    */   public JumpRule() {}
/*    */ 
/*    */ 
/*    */   
/*    */   public JumpRule(String ruleName, String targetNode, String condition) {
/* 36 */     this.name = ruleName;
/* 37 */     this.u = targetNode;
/* 38 */     this.script = condition;
/*    */   }
/*    */ 
/*    */   
/* 42 */   public String getName() { return this.name; }
/*    */ 
/*    */ 
/*    */   
/* 46 */   public void setName(String ruleName) { this.name = ruleName; }
/*    */ 
/*    */ 
/*    */   
/* 50 */   public String getTargetNode() { return this.u; }
/*    */ 
/*    */ 
/*    */   
/* 54 */   public void setTargetNode(String targetNode) { this.u = targetNode; }
/*    */ 
/*    */ 
/*    */   
/* 58 */   public String getScript() { return this.script; }
/*    */ 
/*    */ 
/*    */   
/* 62 */   public void setScript(String script) { this.script = script; }
/*    */ }


/* Location:              D:\Repository\com\dstz\agilebpm\wf-plugin\1.5.1\wf-plugin-1.5.1-pg.jar!\com\dstz\bpm\plugin\node\ruleskip\def\JumpRule.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.0
 */