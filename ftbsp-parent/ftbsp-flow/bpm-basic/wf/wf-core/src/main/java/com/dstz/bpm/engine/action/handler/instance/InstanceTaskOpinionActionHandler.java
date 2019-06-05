/*    */ package com.dstz.bpm.engine.action.handler.instance;
/*    */ 
/*    */ import com.dstz.bpm.api.constant.ActionType;
/*    */ import com.dstz.bpm.api.constant.NodeType;
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
/*    */ public class InstanceTaskOpinionActionHandler
/*    */   implements ActionHandler
/*    */ {
/*    */   public void execute(ActionCmd model) {}
/*    */   
/* 20 */   public ActionType getActionType() { return ActionType.TASKOPINION; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 25 */   public int getSn() { return 5; }
/*    */ 
/*    */ 
/*    */   
/*    */   public Boolean isSupport(BpmNodeDef nodeDef) {
/* 30 */     if (nodeDef.getType() == NodeType.START) return Boolean.valueOf(false);
/*    */     
/* 32 */     return Boolean.valueOf(true);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 37 */   public Boolean isDefault() { return Boolean.valueOf(true); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 44 */   public String getConfigPage() { return "/bpm/instance/taskOpinionHistoryDialog.html"; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 49 */   public String getDefaultGroovyScript() { return ""; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 54 */   public String getDefaultBeforeScript() { return ""; }
/*    */ }


/* Location:              D:\Repository\com\dstz\agilebpm\wf-core\1.5.1\wf-core-1.5.1-pg.jar!\com\dstz\bpm\engine\action\handler\instance\InstanceTaskOpinionActionHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.0
 */