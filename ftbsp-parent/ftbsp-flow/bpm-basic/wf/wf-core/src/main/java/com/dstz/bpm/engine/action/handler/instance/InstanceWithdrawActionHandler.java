/*    */ package com.dstz.bpm.engine.action.handler.instance;
/*    */ 
/*    */ import com.dstz.base.core.util.JsonUtil;
/*    */ import com.dstz.bpm.api.constant.ActionType;
/*    */ import com.dstz.bpm.api.engine.action.cmd.ActionCmd;
/*    */ import com.dstz.bpm.api.engine.action.cmd.BaseActionCmd;
/*    */ import com.dstz.bpm.api.model.nodedef.BpmNodeDef;
/*    */ import com.dstz.bpm.core.manager.BpmTaskManager;
/*    */ import com.dstz.bpm.engine.action.cmd.DefualtTaskActionCmd;
/*    */ import com.dstz.bpm.engine.action.handler.AbsActionHandler;
/*    */ import com.dstz.sys.api.jms.producer.JmsProducer;
/*    */ import javax.annotation.Resource;
/*    */ import org.springframework.stereotype.Component;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Component
/*    */ public abstract class InstanceWithdrawActionHandler
/*    */   extends AbsActionHandler<DefualtTaskActionCmd>
/*    */ {
/*    */   @Resource
/*    */   BpmTaskManager aG;
/*    */   @Resource
/*    */   JmsProducer aH;
/*    */   
/*    */   public void a(DefualtTaskActionCmd model) {
/* 30 */     String opinion = model.getOpinion();
/*    */     
/* 32 */     String withdrawNodeId = JsonUtil.getString(model.getExtendConf(), "withdrawNodeId");
/* 33 */     String msgType = JsonUtil.getString(model.getExtendConf(), "msgType");
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 41 */   public ActionType getActionType() { return ActionType.WITHDRAW; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 46 */   public int getSn() { return 7; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 54 */   public Boolean isSupport(BpmNodeDef nodeDef) { return Boolean.valueOf(false); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 59 */   public Boolean isDefault() { return Boolean.valueOf(false); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 64 */   public String getConfigPage() { return null; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 69 */   public String getDefaultGroovyScript() { return null; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 74 */   public String getDefaultBeforeScript() { return null; }
/*    */   
/*    */   protected void e(DefualtTaskActionCmd model) {}
/*    */   
/*    */   protected void f(DefualtTaskActionCmd data) {}
/*    */   
/*    */   public void g(DefualtTaskActionCmd actionModel) {}
/*    */   
/*    */   protected void h(DefualtTaskActionCmd actionModel) {}
/*    */ }


/* Location:              D:\Repository\com\dstz\agilebpm\wf-core\1.5.1\wf-core-1.5.1-pg.jar!\com\dstz\bpm\engine\action\handler\instance\InstanceWithdrawActionHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.0
 */