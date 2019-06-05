/*    */ package com.dstz.bpm.api.engine.data.result;
/*    */ 
/*    */ import com.dstz.bpm.api.model.task.IBpmTask;
/*    */ import io.swagger.annotations.ApiModelProperty;
/*    */ 
/*    */ public class BpmFlowTaskData
/*    */   extends BpmFlowData
/*    */ {
/*    */   @ApiModelProperty("流程任务信息")
/*    */   private IBpmTask task;
/*    */   
/* 12 */   public IBpmTask getTask() { return this.task; }
/*    */ 
/*    */   
/*    */   public void setTask(IBpmTask task) {
/* 16 */     this.task = task;
/* 17 */     this.defId = task.getDefId();
/*    */   }
/*    */ }


/* Location:              D:\Repository\com\dstz\agilebpm\wf-api\1.5.1\wf-api-1.5.1.jar!\com\dstz\bpm\api\engine\data\result\BpmFlowTaskData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.0
 */