/*    */ package com.dstz.bpm.engine.action.handler.task;
/*    */ 
/*    */ import com.dstz.base.api.exception.BusinessException;
/*    */ import com.dstz.base.core.util.StringUtil;
/*    */ import com.dstz.bpm.act.service.ActTaskService;
/*    */ import com.dstz.bpm.api.constant.EventType;
/*    */ import com.dstz.bpm.api.constant.NodeType;
/*    */ import com.dstz.bpm.api.engine.action.cmd.BaseActionCmd;
/*    */ import com.dstz.bpm.api.engine.plugin.cmd.TaskCommand;
/*    */ import com.dstz.bpm.api.exception.BpmStatusCode;
/*    */ import com.dstz.bpm.api.model.inst.IBpmInstance;
/*    */ import com.dstz.bpm.api.model.nodedef.BpmNodeDef;
/*    */ import com.dstz.bpm.core.manager.BpmTaskManager;
/*    */ import com.dstz.bpm.core.model.BpmTask;
/*    */ import com.dstz.bpm.engine.action.cmd.DefualtTaskActionCmd;
/*    */ import com.dstz.bpm.engine.action.handler.AbsActionHandler;
/*    */ import javax.annotation.Resource;
/*    */ 
/*    */ public abstract class AbstractTaskActionHandler<T extends DefualtTaskActionCmd>
/*    */   extends AbsActionHandler<T> {
/*    */   @Resource
/*    */   protected ActTaskService aI;
/*    */   @Resource
/*    */   protected BpmTaskManager aJ;
/*    */   @Resource
/*    */   protected TaskCommand aK;
/*    */   
/*    */   public void e(T actionModel) {
/* 29 */     BpmTask bpmTask = (BpmTask)actionModel.getBpmTask();
/*    */     
/* 31 */     String taskId = bpmTask.getTaskId();
/*    */     
/* 33 */     String destinationNode = bpmTask.getBackNode();
/* 34 */     if (StringUtil.isEmpty(destinationNode)) {
/* 35 */       destinationNode = actionModel.getDestination();
/*    */     }
/*    */     
/* 38 */     if (StringUtil.isEmpty(destinationNode)) {
/* 39 */       this.aI.completeTask(taskId, actionModel.getActionVariables());
/*    */     }
/*    */     else {
/*    */       
/* 43 */       this.aI.completeTask(taskId, actionModel.getActionVariables(), new String[] { destinationNode });
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void f(T data) {
/* 50 */     if (data.getBpmTask() != null)
/*    */       return; 
/* 52 */     BpmTask task = (BpmTask)this.aJ.get(data.getTaskId());
/* 53 */     if (task == null) {
/* 54 */       throw new BusinessException(BpmStatusCode.TASK_NOT_FOUND);
/*    */     }
/*    */     
/* 57 */     data.setBpmTask(task);
/* 58 */     data.setDefId(task.getDefId());
/* 59 */     data.setBpmInstance((IBpmInstance)this.f.get(task.getInstId()));
/*    */     
/* 61 */     l(data);
/*    */     
/* 63 */     a(data, this.a.getBpmNodeDef(task.getDefId(), task.getNodeId()));
/*    */   }
/*    */ 
/*    */   
/*    */   public Boolean isSupport(BpmNodeDef nodeDef) {
/* 68 */     NodeType nodeType = nodeDef.getType();
/*    */     
/* 70 */     if (nodeType == NodeType.USERTASK || nodeType == NodeType.SIGNTASK) {
/* 71 */       return Boolean.valueOf(true);
/*    */     }
/*    */     
/* 74 */     return Boolean.valueOf(false);
/*    */   }
/*    */ 
/*    */   
/* 78 */   protected void i(DefualtTaskActionCmd actionModel) { this.aK.execute(EventType.TASK_PRE_COMPLETE_EVENT, actionModel); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 87 */   public String getDefaultGroovyScript() { return ""; }
/*    */ }


/* Location:              D:\Repository\com\dstz\agilebpm\wf-core\1.5.1\wf-core-1.5.1-pg.jar!\com\dstz\bpm\engine\action\handler\task\AbstractTaskActionHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.0
 */