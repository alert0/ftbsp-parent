/*     */ package com.dstz.bpm.api.engine.data.result;
/*     */ 
/*     */ import com.alibaba.fastjson.JSONObject;
/*     */ import com.dstz.bpm.api.model.form.BpmForm;
/*     */ import com.dstz.bpm.api.model.nodedef.Button;
/*     */ import com.dstz.bus.api.model.IBusinessData;
/*     */ import io.swagger.annotations.ApiModel;
/*     */ import io.swagger.annotations.ApiModelProperty;
/*     */ import java.util.List;
/*     */ import java.util.Map;
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
/*     */ @ApiModel("流程数据")
/*     */ public class BpmFlowData
/*     */   implements FlowData
/*     */ {
/*     */   @ApiModelProperty("流程定义ID")
/*     */   protected String defId;
/*     */   @ApiModelProperty("流程定义名字")
/*     */   protected String defName;
/*     */   @ApiModelProperty("流程当前节点表单")
/*     */   protected BpmForm Form;
/*     */   @ApiModelProperty("流程自定义表单业务数据")
/*     */   protected JSONObject data;
/*     */   @ApiModelProperty("流程自定义表单 权限")
/*     */   protected JSONObject permission;
/*     */   @ApiModelProperty("流程自定义表单 表权限")
/*     */   protected JSONObject tablePermission;
/*     */   @ApiModelProperty("流程自定义表单 初始化数据，可用于子表数据复制赋值")
/*     */   protected JSONObject initData;
/*     */   @ApiModelProperty("流程 当前节点按钮信息")
/*     */   protected List<Button> buttonList;
/*     */   Map<String, IBusinessData> dataMap;
/*     */   
/*  48 */   public String getDefId() { return this.defId; }
/*     */ 
/*     */ 
/*     */   
/*  52 */   public void setDefId(String defId) { this.defId = defId; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  60 */   public BpmForm getForm() { return this.Form; }
/*     */ 
/*     */ 
/*     */   
/*  64 */   public void setForm(BpmForm Form) { this.Form = Form; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  69 */   public JSONObject getPermission() { return this.permission; }
/*     */ 
/*     */ 
/*     */   
/*  73 */   public JSONObject getTablePermission() { return this.tablePermission; }
/*     */ 
/*     */ 
/*     */   
/*  77 */   public void setTablePermission(JSONObject tablePermission) { this.tablePermission = tablePermission; }
/*     */ 
/*     */ 
/*     */   
/*  81 */   public void setPermission(JSONObject permission) { this.permission = permission; }
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
/*  95 */   public JSONObject getData() { return this.data; }
/*     */ 
/*     */ 
/*     */   
/*  99 */   public String getDefName() { return this.defName; }
/*     */ 
/*     */ 
/*     */   
/* 103 */   public void setDefName(String defName) { this.defName = defName; }
/*     */ 
/*     */ 
/*     */   
/* 107 */   public void setData(JSONObject dataModel) { this.data = dataModel; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 112 */   public List<Button> getButtonList() { return this.buttonList; }
/*     */ 
/*     */ 
/*     */   
/* 116 */   public Map<String, IBusinessData> getDataMap() { return this.dataMap; }
/*     */ 
/*     */ 
/*     */   
/* 120 */   public void setDataMap(Map<String, IBusinessData> dataMap) { this.dataMap = dataMap; }
/*     */ 
/*     */ 
/*     */   
/* 124 */   public void setButtonList(List<Button> list) { this.buttonList = list; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 129 */   public JSONObject getInitData() { return this.initData; }
/*     */ 
/*     */ 
/*     */   
/* 133 */   public void setInitData(JSONObject initData) { this.initData = initData; }
/*     */ }


/* Location:              D:\Repository\com\dstz\agilebpm\wf-api\1.5.1\wf-api-1.5.1.jar!\com\dstz\bpm\api\engine\data\result\BpmFlowData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.0
 */