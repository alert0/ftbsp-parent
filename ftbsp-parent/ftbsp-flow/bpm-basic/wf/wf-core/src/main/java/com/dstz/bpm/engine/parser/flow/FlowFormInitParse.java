/*    */ package com.dstz.bpm.engine.parser.flow;
/*    */ 
/*    */ import com.dstz.bpm.api.engine.plugin.def.BpmDef;
/*    */ import com.dstz.bpm.api.model.def.NodeInit;
/*    */ import com.dstz.bpm.engine.model.DefaultBpmProcessDef;
/*    */ import java.util.List;
/*    */ import org.springframework.stereotype.Component;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Component
/*    */ public abstract class FlowFormInitParse
/*    */   extends AbsFlowParse<NodeInit>
/*    */ {
/* 16 */   public String getKey() { return "nodeInitList"; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void a(DefaultBpmProcessDef bpmProcessDef, Object object) {
/* 22 */     List<NodeInit> list = (List)object;
/* 23 */     bpmProcessDef.setNodeInitList(list);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 28 */   public boolean isArray() { return true; }
/*    */ }


/* Location:              D:\Repository\com\dstz\agilebpm\wf-core\1.5.1\wf-core-1.5.1-pg.jar!\com\dstz\bpm\engine\parser\flow\FlowFormInitParse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.0
 */