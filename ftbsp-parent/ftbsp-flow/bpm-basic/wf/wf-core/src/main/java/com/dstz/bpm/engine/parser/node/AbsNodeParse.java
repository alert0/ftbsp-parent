/*   */ package com.dstz.bpm.engine.parser.node;
/*   */ 
/*   */ import com.dstz.bpm.api.model.nodedef.BpmNodeDef;
/*   */ import com.dstz.bpm.api.model.nodedef.impl.BaseBpmNodeDef;
/*   */ import com.dstz.bpm.engine.parser.BaseBpmDefParser;
/*   */ 
/*   */ public abstract class AbsNodeParse<T>
/*   */   extends BaseBpmDefParser<T, BaseBpmNodeDef> {
/* 9 */   public boolean a(BpmNodeDef nodeDef) { return true; }
/*   */ }


/* Location:              D:\Repository\com\dstz\agilebpm\wf-core\1.5.1\wf-core-1.5.1-pg.jar!\com\dstz\bpm\engine\parser\node\AbsNodeParse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.0
 */