/*    */ package com.dstz.bpm.plugin.node.userassign.def;
/*    */ 
/*    */ import com.dstz.bpm.api.engine.plugin.def.UserAssignRule;
/*    */ import com.dstz.bpm.engine.plugin.plugindef.AbstractBpmExecutionPluginDef;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import javax.validation.Valid;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class UserAssignPluginDef
/*    */   extends AbstractBpmExecutionPluginDef
/*    */ {
/*    */   @Valid
/* 18 */   List<UserAssignRule> w = new ArrayList();
/*    */ 
/*    */ 
/*    */   
/* 22 */   public List<UserAssignRule> getRuleList() { return this.w; }
/*    */ 
/*    */ 
/*    */   
/* 26 */   public void setRuleList(List<UserAssignRule> ruleList) { this.w = ruleList; }
/*    */ }


/* Location:              D:\Repository\com\dstz\agilebpm\wf-plugin\1.5.1\wf-plugin-1.5.1-pg.jar!\com\dstz\bpm\plugin\nod\\userassign\def\UserAssignPluginDef.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.0
 */