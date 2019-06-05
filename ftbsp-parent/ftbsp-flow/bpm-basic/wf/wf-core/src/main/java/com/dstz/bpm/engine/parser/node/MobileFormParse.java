/*    */ package com.dstz.bpm.engine.parser.node;
/*    */ 
/*    */ import com.dstz.bpm.api.engine.plugin.def.BpmDef;
/*    */ import com.dstz.bpm.api.model.form.DefaultForm;
/*    */ import com.dstz.bpm.api.model.nodedef.impl.BaseBpmNodeDef;
/*    */ import org.springframework.stereotype.Component;
/*    */ 
/*    */ 
/*    */ 
/*    */ @Component
/*    */ public abstract class MobileFormParse
/*    */   extends AbsNodeParse<DefaultForm>
/*    */ {
/* 14 */   public String getKey() { return "mobileForm"; }
/*    */ 
/*    */ 
/*    */   
/*    */   public void a(BaseBpmNodeDef userNodeDef, Object object) {
/* 19 */     DefaultForm form = (DefaultForm)object;
/* 20 */     userNodeDef.setMobileForm(form);
/*    */   }
/*    */ }


/* Location:              D:\Repository\com\dstz\agilebpm\wf-core\1.5.1\wf-core-1.5.1-pg.jar!\com\dstz\bpm\engine\parser\node\MobileFormParse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.0
 */