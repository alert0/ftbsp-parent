/*     */ package com.dstz.bpm.api.engine.action.cmd;
/*     */ 
/*     */ import com.alibaba.fastjson.JSONObject;
/*     */ import com.dstz.form.api.model.FormCategory;
/*     */ import io.swagger.annotations.ApiModel;
/*     */ import io.swagger.annotations.ApiModelProperty;
/*     */ import org.hibernate.validator.constraints.NotBlank;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @ApiModel(value = "流程 ActionCmd 请求参数", description = "流程实例、任务 ActionCmd 请求参数，请参考FlowRequestParam.java 或者文档了解 ")
/*     */ public class FlowRequestParam
/*     */ {
/*     */   @ApiModelProperty("流程定义id，流程启动等场景必须")
/*     */   private String defId;
/*     */   @ApiModelProperty("流程实例id，流程草稿等场景")
/*     */   private String instanceId;
/*     */   @ApiModelProperty("流程任务id，流程任务处理时必须")
/*     */   private String taskId;
/*     */   @NotBlank
/*     */   @ApiModelProperty("action name 必须")
/*     */   private String action;
/*     */   @ApiModelProperty("前端节点人员设置")
/*     */   private JSONObject nodeUsers;
/*     */   @ApiModelProperty("流程业务数据，JSON格式：{boCodeA:{},boCodeB:{}}")
/*     */   private JSONObject data;
/*     */   @ApiModelProperty("流程业务主键。 URL表单，可以直接赋值调用rest接口启动流程")
/*     */   private String businessKey;
/*     */   @ApiModelProperty("表单类型")
/*  50 */   private String formType = FormCategory.INNER
/*  51 */     .value();
/*     */ 
/*     */ 
/*     */   
/*     */   @ApiModelProperty("流程任务审批意见")
/*     */   private String opinion;
/*     */ 
/*     */ 
/*     */   
/*     */   @ApiModelProperty("目标节点")
/*     */   private String destination;
/*     */ 
/*     */ 
/*     */   
/*     */   @ApiModelProperty("特殊属性扩展配置：可以再 ActionCmd 中拿到此配置")
/*     */   private JSONObject extendConf;
/*     */ 
/*     */ 
/*     */   
/*     */   public FlowRequestParam(String taskId, String action, JSONObject data, String opinion) {
/*  71 */     this.taskId = taskId;
/*  72 */     this.action = action;
/*  73 */     this.data = data;
/*  74 */     this.opinion = opinion;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public FlowRequestParam(String defId, String action, JSONObject data) {
/*  85 */     this.defId = defId;
/*  86 */     this.action = action;
/*  87 */     this.data = data;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public FlowRequestParam() {}
/*     */ 
/*     */   
/*  95 */   public String getDefId() { return this.defId; }
/*     */ 
/*     */ 
/*     */   
/*  99 */   public void setDefId(String defId) { this.defId = defId; }
/*     */ 
/*     */ 
/*     */   
/* 103 */   public String getInstanceId() { return this.instanceId; }
/*     */ 
/*     */ 
/*     */   
/* 107 */   public void setInstanceId(String instanceId) { this.instanceId = instanceId; }
/*     */ 
/*     */ 
/*     */   
/* 111 */   public String getTaskId() { return this.taskId; }
/*     */ 
/*     */ 
/*     */   
/* 115 */   public void setTaskId(String taskId) { this.taskId = taskId; }
/*     */ 
/*     */ 
/*     */   
/* 119 */   public String getAction() { return this.action; }
/*     */ 
/*     */ 
/*     */   
/* 123 */   public void setAction(String action) { this.action = action; }
/*     */ 
/*     */ 
/*     */   
/* 127 */   public String getBusinessKey() { return this.businessKey; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 132 */   public void setBusinessKey(String businessKey) { this.businessKey = businessKey; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 137 */   public JSONObject getNodeUsers() { return this.nodeUsers; }
/*     */ 
/*     */ 
/*     */   
/* 141 */   public void setNodeUsers(JSONObject nodeUsers) { this.nodeUsers = nodeUsers; }
/*     */ 
/*     */ 
/*     */   
/* 145 */   public JSONObject getData() { return this.data; }
/*     */ 
/*     */ 
/*     */   
/* 149 */   public void setData(JSONObject data) { this.data = data; }
/*     */ 
/*     */ 
/*     */   
/* 153 */   public String getFormType() { return this.formType; }
/*     */ 
/*     */ 
/*     */   
/* 157 */   public void setFormType(String formType) { this.formType = formType; }
/*     */ 
/*     */ 
/*     */   
/* 161 */   public String getOpinion() { return this.opinion; }
/*     */ 
/*     */ 
/*     */   
/* 165 */   public void setOpinion(String opinion) { this.opinion = opinion; }
/*     */ 
/*     */ 
/*     */   
/* 169 */   public String getDestination() { return this.destination; }
/*     */ 
/*     */ 
/*     */   
/* 173 */   public void setDestination(String destination) { this.destination = destination; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 178 */   public JSONObject getExtendConf() { return this.extendConf; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 183 */   public void setExtendConf(JSONObject extendConf) { this.extendConf = extendConf; }
/*     */ }


/* Location:              D:\Repository\com\dstz\agilebpm\wf-api\1.5.1\wf-api-1.5.1.jar!\com\dstz\bpm\api\engine\action\cmd\FlowRequestParam.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.0
 */