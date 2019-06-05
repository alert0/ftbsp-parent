/*    */ package com.dstz.bpm.api.model.nodedef.impl;
/*    */ 
/*    */ import com.dstz.bpm.api.constant.NodeType;
/*    */ import com.dstz.bpm.api.model.def.BpmProcessDef;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SubProcessNodeDef
/*    */   extends BaseBpmNodeDef
/*    */ {
/*    */   private static final long serialVersionUID = -1165886168391484970L;
/*    */   private BpmProcessDef bpmChildProcessDef;
/*    */   
/* 14 */   public SubProcessNodeDef() { setType(NodeType.SUBPROCESS); }
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
/* 27 */   public BpmProcessDef getChildBpmProcessDef() { return this.bpmChildProcessDef; }
/*    */ 
/*    */ 
/*    */   
/* 31 */   public void setChildBpmProcessDef(BpmProcessDef bpmChildProcessDef) { this.bpmChildProcessDef = bpmChildProcessDef; }
/*    */ }


/* Location:              D:\Repository\com\dstz\agilebpm\wf-api\1.5.1\wf-api-1.5.1.jar!\com\dstz\bpm\api\model\nodedef\impl\SubProcessNodeDef.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.0
 */