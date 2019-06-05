/*    */ package com.dstz.bpm.engine.plugin.session.impl;
/*    */ 
/*    */ import com.dstz.bpm.api.model.task.IBpmTask;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DefaultBpmTaskPluginSession
/*    */   extends DefaultBpmExecutionPluginSession
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private IBpmTask az;
/*    */   
/* 19 */   public IBpmTask getBpmTask() { return this.az; }
/*    */ 
/*    */   
/*    */   public void setBpmTask(IBpmTask bpmTask) {
/* 23 */     put("bpmTask", bpmTask);
/* 24 */     this.az = bpmTask;
/*    */   }
/*    */ }


/* Location:              D:\Repository\com\dstz\agilebpm\wf-core\1.5.1\wf-core-1.5.1-pg.jar!\com\dstz\bpm\engine\plugin\session\impl\DefaultBpmTaskPluginSession.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.0
 */