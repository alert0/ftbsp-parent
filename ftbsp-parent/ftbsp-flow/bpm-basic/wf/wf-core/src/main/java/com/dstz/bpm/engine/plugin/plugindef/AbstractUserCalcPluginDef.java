/*    */ package com.dstz.bpm.engine.plugin.plugindef;
/*    */ 
/*    */ import com.dstz.bpm.api.constant.ExtractType;
/*    */ import com.dstz.bpm.api.engine.constant.LogicType;
/*    */ import com.dstz.bpm.api.engine.plugin.def.BpmUserCalcPluginDef;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class AbstractUserCalcPluginDef
/*    */   implements BpmUserCalcPluginDef
/*    */ {
/* 14 */   private ExtractType bY = ExtractType.EXACT_NOEXACT;
/* 15 */   private LogicType bZ = LogicType.OR;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 24 */   public ExtractType getExtract() { return this.bY; }
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
/* 35 */   public void setExtract(ExtractType type) { this.bY = type; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 45 */   public LogicType getLogicCal() { return this.bZ; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 55 */   public void setLogicCal(LogicType logicType) { this.bZ = logicType; }
/*    */ }


/* Location:              D:\Repository\com\dstz\agilebpm\wf-core\1.5.1\wf-core-1.5.1-pg.jar!\com\dstz\bpm\engine\plugin\plugindef\AbstractUserCalcPluginDef.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.0
 */