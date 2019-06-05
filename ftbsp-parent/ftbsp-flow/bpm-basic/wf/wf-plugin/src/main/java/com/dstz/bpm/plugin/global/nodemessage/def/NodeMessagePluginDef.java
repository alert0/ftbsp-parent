/*    */ package com.dstz.bpm.plugin.global.nodemessage.def;
/*    */ 
/*    */ import com.dstz.bpm.engine.plugin.plugindef.AbstractBpmExecutionPluginDef;
/*    */ import java.util.List;
/*    */ import javax.validation.Valid;
/*    */ import org.hibernate.validator.constraints.NotEmpty;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class NodeMessagePluginDef
/*    */   extends AbstractBpmExecutionPluginDef
/*    */ {
/*    */   @Valid
/*    */   @NotEmpty
/*    */   private List<NodeMessage> m;
/*    */   
/* 20 */   public NodeMessagePluginDef(List<NodeMessage> nodeMessageList) { this.m = nodeMessageList; }
/*    */ 
/*    */ 
/*    */   
/* 24 */   public List<NodeMessage> getNodeMessageList() { return this.m; }
/*    */ 
/*    */ 
/*    */   
/* 28 */   public void setNodeMessageList(List<NodeMessage> nodeMessageList) { this.m = nodeMessageList; }
/*    */ }


/* Location:              D:\Repository\com\dstz\agilebpm\wf-plugin\1.5.1\wf-plugin-1.5.1-pg.jar!\com\dstz\bpm\plugin\global\nodemessage\def\NodeMessagePluginDef.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.0
 */