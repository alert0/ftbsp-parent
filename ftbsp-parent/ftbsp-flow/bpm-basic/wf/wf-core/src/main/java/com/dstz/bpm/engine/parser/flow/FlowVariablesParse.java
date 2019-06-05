/*    */ package com.dstz.bpm.engine.parser.flow;
/*    */ 
/*    */ import com.dstz.bpm.api.engine.plugin.def.BpmDef;
/*    */ import com.dstz.bpm.api.model.def.BpmVariableDef;
/*    */ import com.dstz.bpm.engine.model.DefaultBpmProcessDef;
/*    */ import com.dstz.bpm.engine.model.DefaultBpmVariableDef;
/*    */ import java.util.HashSet;
/*    */ import java.util.List;
/*    */ import java.util.Set;
/*    */ import org.springframework.stereotype.Component;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Component
/*    */ public abstract class FlowVariablesParse
/*    */   extends AbsFlowParse<DefaultBpmVariableDef>
/*    */ {
/* 19 */   public String getKey() { return "variableList"; }
/*    */ 
/*    */ 
/*    */   
/*    */   public String validate(Object object) {
/* 24 */     List<BpmVariableDef> varList = (List)object;
/*    */     
/* 26 */     Set<String> keys = new HashSet<String>();
/* 27 */     for (BpmVariableDef def : varList) {
/* 28 */       String key = def.getKey();
/*    */       
/* 30 */       if (keys.contains(key)) {
/* 31 */         throw new RuntimeException("解析流程变量出错：" + key + "在流程中重复！");
/*    */       }
/* 33 */       keys.add(def.getKey());
/*    */     } 
/* 35 */     return "";
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(DefaultBpmProcessDef bpmProcessDef, Object object) {
/* 40 */     List<BpmVariableDef> varList = (List)object;
/*    */     
/* 42 */     bpmProcessDef.setVarList(varList);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 47 */   public boolean isArray() { return true; }
/*    */ }


/* Location:              D:\Repository\com\dstz\agilebpm\wf-core\1.5.1\wf-core-1.5.1-pg.jar!\com\dstz\bpm\engine\parser\flow\FlowVariablesParse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.0
 */