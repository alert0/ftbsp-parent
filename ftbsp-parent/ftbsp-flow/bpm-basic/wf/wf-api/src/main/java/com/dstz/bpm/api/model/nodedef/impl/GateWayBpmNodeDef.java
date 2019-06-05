/*    */ package com.dstz.bpm.api.model.nodedef.impl;
/*    */ 
/*    */ import com.dstz.bpm.api.engine.plugin.context.BpmPluginContext;
/*    */ import com.dstz.bpm.api.model.def.BpmProcessDef;
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class GateWayBpmNodeDef
/*    */   extends BaseBpmNodeDef
/*    */ {
/* 18 */   public List<BpmPluginContext> getBpmPluginContexts() { throw new RuntimeException("GateWayBpmNodeDef not support getBpmPluginContexts method"); }
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
/* 29 */   public BpmProcessDef getChildBpmProcessDef() { throw new RuntimeException("GateWayBpmNodeDef not support getChildBpmProcessDef method"); }
/*    */ }


/* Location:              D:\Repository\com\dstz\agilebpm\wf-api\1.5.1\wf-api-1.5.1.jar!\com\dstz\bpm\api\model\nodedef\impl\GateWayBpmNodeDef.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.0
 */