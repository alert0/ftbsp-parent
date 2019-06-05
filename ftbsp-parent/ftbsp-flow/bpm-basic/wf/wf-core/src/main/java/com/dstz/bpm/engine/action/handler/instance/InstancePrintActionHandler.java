/*    */ package com.dstz.bpm.engine.action.handler.instance;
/*    */ 
/*    */ import com.dstz.bpm.api.constant.ActionType;
/*    */ import com.dstz.bpm.api.engine.action.cmd.ActionCmd;
/*    */ import com.dstz.bpm.api.engine.action.handler.ActionHandler;
/*    */ import com.dstz.bpm.api.model.nodedef.BpmNodeDef;
/*    */ import org.springframework.stereotype.Component;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Component
/*    */ public class InstancePrintActionHandler
/*    */   implements ActionHandler
/*    */ {
/*    */   public void execute(ActionCmd model) {}
/*    */   
/* 19 */   public ActionType getActionType() { return ActionType.PRINT; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 24 */   public int getSn() { return 7; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 29 */   public Boolean isSupport(BpmNodeDef nodeDef) { return Boolean.valueOf(true); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 34 */   public Boolean isDefault() { return Boolean.valueOf(true); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 39 */   public String getConfigPage() { return ""; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 44 */   public String getDefaultGroovyScript() { return ""; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 49 */   public String getDefaultBeforeScript() { return "print(); return false;"; }
/*    */ }


/* Location:              D:\Repository\com\dstz\agilebpm\wf-core\1.5.1\wf-core-1.5.1-pg.jar!\com\dstz\bpm\engine\action\handler\instance\InstancePrintActionHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.0
 */