/*    */ package com.dstz.bpm.engine.parser.flow;
/*    */ 
/*    */ import com.dstz.bpm.api.engine.plugin.def.BpmDef;
/*    */ import com.dstz.bpm.api.model.form.DefaultForm;
/*    */ import com.dstz.bpm.engine.model.DefaultBpmProcessDef;
/*    */ import org.springframework.stereotype.Component;
/*    */ 
/*    */ 
/*    */ 
/*    */ @Component
/*    */ public abstract class GloBalPcFormParse
/*    */   extends AbsFlowParse<DefaultForm>
/*    */ {
/* 14 */   public String getKey() { return "globalForm"; }
/*    */ 
/*    */ 
/*    */   
/*    */   public void a(DefaultBpmProcessDef bpmProcessDef, Object object) {
/* 19 */     DefaultForm form = (DefaultForm)object;
/* 20 */     bpmProcessDef.setGlobalForm(form);
/*    */   }
/*    */ }


/* Location:              D:\Repository\com\dstz\agilebpm\wf-core\1.5.1\wf-core-1.5.1-pg.jar!\com\dstz\bpm\engine\parser\flow\GloBalPcFormParse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.0
 */