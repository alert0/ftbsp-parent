/*    */ package com.dstz.bpm.api.exception;
/*    */ 
/*    */ import com.dstz.base.api.constant.IStatusCode;
/*    */ 
/*    */ 
/*    */ 
/*    */ public enum BpmStatusCode
/*    */   implements IStatusCode
/*    */ {
/* 10 */   SUCCESS("200", "成功"),
/* 11 */   SYSTEM_ERROR("500", "系统异常"),
/* 12 */   TIMEOUT("401", "访问超时"),
/* 13 */   NO_ACCESS("403", "访问受限"),
/* 14 */   PARAM_ILLEGAL("100", "参数校验不通过"),
/*    */   
/* 16 */   ACTIONCMD_ERROR("20000", "线程ActionCmd 数据异常"),
/*    */   
/* 18 */   NO_PERMISSION("20001", "没有操作权限！"),
/*    */   
/* 20 */   DEF_FORBIDDEN("30000", "流程定义被禁用"),
/* 21 */   DEF_LOST("30001", "流程定义丢失"),
/* 22 */   TASK_NOT_FOUND("30001", "查找不到当前任务，任务可能已经被处理完成"),
/*    */   
/* 24 */   NO_TASK_USER("30002", "任务候选执行人为空"),
/* 25 */   USER_CALC_ERROR("30003", "人员计算出现异常"),
/*    */   
/* 27 */   NO_TASK_ACTION("30004", "任务处理ACTION查找不到"),
/* 28 */   HANDLER_ERROR("30005", "执行URL表单处理器处理器失败，请检查"),
/* 29 */   TASK_ACTION_BTN_ERROR("30006", "任务处理器生成按钮异常"),
/*    */   
/* 31 */   VARIABLES_NO_SYNC("30007", "流程变量尚未同步,不应该存在的获取时机"),
/* 32 */   NO_ASSIGN_USER("30008", "任务尚未分配候选人"),
/*    */ 
/*    */   
/* 35 */   NO_BACK_TARGET("30101", "驳回节点未知"),
/* 36 */   CANNOT_BACK_NODE("30102", "不支持的驳回节点"),
/*    */   
/* 38 */   PLUGIN_ERROR("31000", "执行插件异常"),
/*    */ 
/*    */   
/* 41 */   PLUGIN_USERCALC_RULE_CONDITION_ERROR("31100", "用户计算插件前置条件解析异常"),
/*    */ 
/*    */ 
/*    */   
/* 45 */   GATEWAY_ERROR("30051", "网关分支判断脚本异常"),
/*    */ 
/*    */   
/* 48 */   PARSER_FLOW_ERROR("30601", "流程解析器异常"),
/* 49 */   PARSER_NODE_ERROR("30602", "流程节点解析器异常"),
/*    */ 
/*    */   
/* 52 */   FLOW_DATA_GET_BUTTONS_ERROR("30701", "获取流程按钮失败"),
/* 53 */   FLOW_DATA_EXECUTE_SHOWSCRIPT_ERROR("30702", "执行初始化脚本失败"),
/*    */   
/* 55 */   FLOW_BUS_DATA_PK_LOSE("40101", "流程数据保存异常主键缺失"),
/*    */   
/* 57 */   FLOW_FORM_LOSE("50101", "流程配置的表单查找不到"),
/*    */ 
/*    */   
/* 60 */   FLOW_BUS_DATA_LOSE("50101", "流程关联的业务数据丢失"),
/*    */   
/* 62 */   ERROR_UNKNOWN("30100", "未知异常");
/*    */   
/*    */   private String code;
/*    */   private String desc;
/*    */   private String system;
/*    */   
/*    */   BpmStatusCode(String code, String description) {
/* 69 */     setCode(code);
/* 70 */     setDesc(description);
/* 71 */     setSystem("BASE");
/*    */   }
/*    */ 
/*    */   
/* 75 */   public String getCode() { return this.code; }
/*    */ 
/*    */ 
/*    */   
/* 79 */   public void setCode(String code) { this.code = code; }
/*    */ 
/*    */ 
/*    */   
/* 83 */   public String getDesc() { return this.desc; }
/*    */ 
/*    */ 
/*    */   
/* 87 */   public void setDesc(String msg) { this.desc = msg; }
/*    */ 
/*    */ 
/*    */   
/* 91 */   public String getSystem() { return this.system; }
/*    */ 
/*    */ 
/*    */   
/* 95 */   public void setSystem(String system) { this.system = system; }
/*    */ }


/* Location:              D:\Repository\com\dstz\agilebpm\wf-api\1.5.1\wf-api-1.5.1.jar!\com\dstz\bpm\api\exception\BpmStatusCode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.0
 */