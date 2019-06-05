/*    */ package com.dstz.bpm.api.engine.data.result;
/*    */ 
/*    */ import com.dstz.bpm.api.model.inst.IBpmInstance;
/*    */ import io.swagger.annotations.ApiModelProperty;
/*    */ 
/*    */ 
/*    */ public class BpmFlowInstanceData
/*    */   extends BpmFlowData
/*    */ {
/*    */   @ApiModelProperty("流程实例信息")
/*    */   private IBpmInstance instance;
/*    */   
/* 13 */   public IBpmInstance getInstance() { return this.instance; }
/*    */ 
/*    */   
/*    */   public void setInstance(IBpmInstance instance) {
/* 17 */     this.instance = instance;
/* 18 */     this.defId = instance.getDefId();
/*    */   }
/*    */ }


/* Location:              D:\Repository\com\dstz\agilebpm\wf-api\1.5.1\wf-api-1.5.1.jar!\com\dstz\bpm\api\engine\data\result\BpmFlowInstanceData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.0
 */