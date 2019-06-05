/*    */ package com.dstz.bpm.plugin.node.ruleskip.def;
/*    */ 
/*    */ import com.dstz.bpm.engine.plugin.plugindef.AbstractBpmExecutionPluginDef;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import javax.validation.Valid;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RuleSkipPluginDef
/*    */   extends AbstractBpmExecutionPluginDef
/*    */ {
/*    */   @Valid
/* 16 */   private List<JumpRule> v = new ArrayList();
/*    */ 
/*    */ 
/*    */   
/* 20 */   public List<JumpRule> getJumpRules() { return this.v; }
/*    */ 
/*    */ 
/*    */   
/* 24 */   public void setJumpRules(List<JumpRule> jumpRules) { this.v = jumpRules; }
/*    */ }


/* Location:              D:\Repository\com\dstz\agilebpm\wf-plugin\1.5.1\wf-plugin-1.5.1-pg.jar!\com\dstz\bpm\plugin\node\ruleskip\def\RuleSkipPluginDef.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.0
 */