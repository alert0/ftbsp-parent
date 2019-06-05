/*    */ package com.dstz.bpm.engine.parser.node;
/*    */ 
/*    */ import com.dstz.bpm.api.engine.plugin.def.BpmDef;
/*    */ import com.dstz.bpm.api.model.def.NodeProperties;
/*    */ import com.dstz.bpm.api.model.nodedef.impl.BaseBpmNodeDef;
/*    */ import org.springframework.stereotype.Component;
/*    */ 
/*    */ 
/*    */ 
/*    */ @Component
/*    */ public abstract class NodePropertiesParse
/*    */   extends AbsNodeParse<NodeProperties>
/*    */ {
/* 14 */   public String getKey() { return "propertie"; }
/*    */ 
/*    */ 
/*    */   
/*    */   public void a(BaseBpmNodeDef userNodeDef, Object object) {
/* 19 */     NodeProperties prop = (NodeProperties)object;
/*    */     
/* 21 */     userNodeDef.setNodeProperties(prop);
/*    */   }
/*    */ }


/* Location:              D:\Repository\com\dstz\agilebpm\wf-core\1.5.1\wf-core-1.5.1-pg.jar!\com\dstz\bpm\engine\parser\node\NodePropertiesParse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.0
 */