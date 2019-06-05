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
/*    */ 
/*    */ @Component
/*    */ public class InstanceImageActionHandler
/*    */   implements ActionHandler
/*    */ {
/*    */   public void execute(ActionCmd model) {}
/*    */   
/* 20 */   public ActionType getActionType() { return ActionType.FLOWIMAGE; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 25 */   public int getSn() { return 6; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 30 */   public Boolean isSupport(BpmNodeDef nodeDef) { return Boolean.valueOf(true); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 35 */   public Boolean isDefault() { return Boolean.valueOf(true); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 42 */   public String getConfigPage() { return "/bpm/instance/instanceImageDialog.html"; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 47 */   public String getDefaultGroovyScript() { return ""; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 52 */   public String getDefaultBeforeScript() { return ""; }
/*    */ }


/* Location:              D:\Repository\com\dstz\agilebpm\wf-core\1.5.1\wf-core-1.5.1-pg.jar!\com\dstz\bpm\engine\action\handler\instance\InstanceImageActionHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.0
 */