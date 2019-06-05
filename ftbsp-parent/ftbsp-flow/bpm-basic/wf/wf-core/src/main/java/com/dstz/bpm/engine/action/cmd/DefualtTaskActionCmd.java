/*     */ package com.dstz.bpm.engine.action.cmd;
/*     */ 
/*     */ import com.dstz.base.api.exception.BusinessException;
/*     */ import com.dstz.base.core.util.AppUtil;
/*     */ import com.dstz.base.core.util.StringUtil;
/*     */ import com.dstz.bpm.api.constant.ActionType;
/*     */ import com.dstz.bpm.api.engine.action.cmd.BaseActionCmd;
/*     */ import com.dstz.bpm.api.engine.action.cmd.FlowRequestParam;
/*     */ import com.dstz.bpm.api.engine.action.cmd.TaskActionCmd;
/*     */ import com.dstz.bpm.api.exception.BpmStatusCode;
/*     */ import com.dstz.bpm.api.exception.WorkFlowException;
/*     */ import com.dstz.bpm.api.model.task.IBpmTask;
/*     */ import com.dstz.bpm.engine.action.handler.AbsActionHandler;
/*     */ import com.dstz.bpm.engine.constant.TaskSkipType;
/*     */ import java.util.Map;
/*     */ import org.activiti.engine.delegate.DelegateTask;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DefualtTaskActionCmd
/*     */   extends BaseActionCmd
/*     */   implements TaskActionCmd
/*     */ {
/*     */   private String taskId;
/*     */   private IBpmTask az;
/*     */   private DelegateTask aA;
/*  32 */   private TaskSkipType aB = TaskSkipType.NO_SKIP;
/*     */ 
/*     */ 
/*     */   
/*     */   public DefualtTaskActionCmd() {}
/*     */ 
/*     */   
/*  39 */   public DefualtTaskActionCmd(FlowRequestParam flowParam) { super(flowParam); }
/*     */ 
/*     */   
/*     */   public String getTaskId() {
/*  43 */     if (this.az != null) {
/*  44 */       return this.az.getId();
/*     */     }
/*     */     
/*  47 */     return this.taskId;
/*     */   }
/*     */ 
/*     */   
/*  51 */   public void setTaskId(String taskId) { this.taskId = taskId; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void initSpecialParam(FlowRequestParam flowParam) {
/*  58 */     String taskId = flowParam.getTaskId();
/*  59 */     if (StringUtil.isEmpty(taskId)) {
/*  60 */       throw new BusinessException("taskId 不能为空", BpmStatusCode.TASK_NOT_FOUND);
/*     */     }
/*  62 */     setTaskId(taskId);
/*     */     
/*  64 */     setDestination(flowParam.getDestination());
/*     */   }
/*     */ 
/*     */   
/*  68 */   public IBpmTask getBpmTask() { return this.az; }
/*     */ 
/*     */ 
/*     */   
/*  72 */   public void setBpmTask(IBpmTask task) { this.az = task; }
/*     */ 
/*     */   
/*     */   public DelegateTask getDelagateTask() {
/*  76 */     if (this.aA == null);
/*     */ 
/*     */     
/*  79 */     return this.aA;
/*     */   }
/*     */ 
/*     */   
/*  83 */   public void setDelagateTask(DelegateTask task) { this.aA = task; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  88 */   public String getNodeId() { return this.az.getNodeId(); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addVariable(String variableName, Object value) {
/*  94 */     if (this.aA == null) {
/*  95 */       throw new WorkFlowException(BpmStatusCode.VARIABLES_NO_SYNC);
/*     */     }
/*  97 */     this.aA.setVariable(variableName, value);
/*     */   }
/*     */ 
/*     */   
/*     */   public Object getVariable(String variableName) {
/* 102 */     if (this.aA == null) {
/* 103 */       throw new WorkFlowException(BpmStatusCode.VARIABLES_NO_SYNC);
/*     */     }
/* 105 */     return this.aA.getVariable(variableName);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasVariable(String variableName) {
/* 110 */     if (this.aA == null) {
/* 111 */       throw new WorkFlowException(BpmStatusCode.VARIABLES_NO_SYNC);
/*     */     }
/* 113 */     return this.aA.hasVariable(variableName);
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeVariable(String variableName) {
/* 118 */     if (this.aA == null) {
/* 119 */       throw new WorkFlowException(BpmStatusCode.VARIABLES_NO_SYNC);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 126 */   public Map<String, Object> getVariables() { return this.aA.getVariables(); }
/*     */ 
/*     */ 
/*     */   
/*     */   public String a() {
/* 131 */     if (this.hasExecuted) {
/* 132 */       throw new BusinessException("action cmd cannot be invoked twice", BpmStatusCode.PARAM_ILLEGAL);
/*     */     }
/* 134 */     this.hasExecuted = true;
/*     */     
/* 136 */     ActionType actonType = ActionType.fromKey(getActionName());
/*     */     
/* 138 */     AbsActionHandler handler = (AbsActionHandler)AppUtil.getBean(actonType.getBeanId());
/* 139 */     if (handler == null) {
/* 140 */       throw new BusinessException("action beanId cannot be found :" + actonType.getName(), BpmStatusCode.NO_TASK_ACTION);
/*     */     }
/* 142 */     handler.g(this);
/* 143 */     return handler.getActionType().getName();
/*     */   }
/*     */ 
/*     */   
/* 147 */   public TaskSkipType b() { return this.aB; }
/*     */ 
/*     */ 
/*     */   
/* 151 */   public void setHasSkipThisTask(TaskSkipType isSkip) { this.aB = isSkip; }
/*     */ }


/* Location:              D:\Repository\com\dstz\agilebpm\wf-core\1.5.1\wf-core-1.5.1-pg.jar!\com\dstz\bpm\engine\action\cmd\DefualtTaskActionCmd.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.0
 */