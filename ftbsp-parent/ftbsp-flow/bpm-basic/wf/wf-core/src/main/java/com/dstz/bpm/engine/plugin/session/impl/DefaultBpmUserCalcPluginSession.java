/*    */ package com.dstz.bpm.engine.plugin.session.impl;
/*    */ 
/*    */ import com.dstz.bpm.api.model.task.IBpmTask;
/*    */ import com.dstz.bpm.engine.plugin.session.BpmUserCalcPluginSession;
/*    */ 
/*    */ 
/*    */ public class DefaultBpmUserCalcPluginSession
/*    */   extends DefaultBpmExecutionPluginSession
/*    */   implements BpmUserCalcPluginSession
/*    */ {
/*    */   private static final long serialVersionUID = 1132300282829841447L;
/*    */   private IBpmTask az;
/* 13 */   private Boolean cd = Boolean.valueOf(false);
/*    */ 
/*    */   
/* 16 */   public IBpmTask getBpmTask() { return this.az; }
/*    */ 
/*    */   
/*    */   public void setBpmTask(IBpmTask bpmTask) {
/* 20 */     put("bpmTask", bpmTask);
/* 21 */     this.az = bpmTask;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 26 */   public Boolean isPreViewModel() { return this.cd; }
/*    */ 
/*    */ 
/*    */   
/* 30 */   public void setIsPreVrewModel(Boolean isPreViewModel) { this.cd = isPreViewModel; }
/*    */ }


/* Location:              D:\Repository\com\dstz\agilebpm\wf-core\1.5.1\wf-core-1.5.1-pg.jar!\com\dstz\bpm\engine\plugin\session\impl\DefaultBpmUserCalcPluginSession.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.0
 */