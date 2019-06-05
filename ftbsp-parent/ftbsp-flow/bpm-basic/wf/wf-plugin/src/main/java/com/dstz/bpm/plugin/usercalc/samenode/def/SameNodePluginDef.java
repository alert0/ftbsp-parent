/*    */ package com.dstz.bpm.plugin.usercalc.samenode.def;
/*    */ import com.dstz.bpm.engine.plugin.plugindef.AbstractUserCalcPluginDef;
/*    */ import org.hibernate.validator.constraints.NotEmpty;
/*    */ 
/*    */ public class SameNodePluginDef extends AbstractUserCalcPluginDef {
/*    */   @NotEmpty(message = "人员插件相同节点执行人，节点ID不能为空")
/*  7 */   private String h = "";
/*    */ 
/*    */ 
/*    */   
/* 11 */   public String getNodeId() { return this.h; }
/*    */ 
/*    */ 
/*    */   
/* 15 */   public void setNodeId(String nodeId) { this.h = nodeId; }
/*    */ }


/* Location:              D:\Repository\com\dstz\agilebpm\wf-plugin\1.5.1\wf-plugin-1.5.1-pg.jar!\com\dstz\bpm\plugi\\usercalc\samenode\def\SameNodePluginDef.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.0
 */