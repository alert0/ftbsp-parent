/*     */ package com.dstz.bpm.engine.action.handler.task;
/*     */ 
/*     */ import cn.hutool.core.collection.CollectionUtil;
/*     */ import com.dstz.base.api.exception.BusinessException;
/*     */ import com.dstz.base.core.util.StringUtil;
/*     */ import com.dstz.bpm.api.constant.ActionType;
/*     */ import com.dstz.bpm.api.constant.NodeType;
/*     */ import com.dstz.bpm.api.engine.action.cmd.BaseActionCmd;
/*     */ import com.dstz.bpm.api.exception.BpmStatusCode;
/*     */ import com.dstz.bpm.api.exception.WorkFlowException;
/*     */ import com.dstz.bpm.api.model.def.NodeProperties;
/*     */ import com.dstz.bpm.api.model.inst.BpmExecutionStack;
/*     */ import com.dstz.bpm.api.model.nodedef.BpmNodeDef;
/*     */ import com.dstz.bpm.api.model.task.IBpmTask;
/*     */ import com.dstz.bpm.api.service.BpmProcessDefService;
/*     */ import com.dstz.bpm.core.manager.BpmTaskOpinionManager;
/*     */ import com.dstz.bpm.core.manager.BpmTaskStackManager;
/*     */ import com.dstz.bpm.core.model.BpmTask;
/*     */ import com.dstz.bpm.core.model.BpmTaskOpinion;
/*     */ import com.dstz.bpm.core.model.BpmTaskStack;
/*     */ import com.dstz.bpm.engine.action.cmd.DefualtTaskActionCmd;
/*     */ import com.dstz.bpm.engine.model.BpmIdentity;
/*     */ import com.dstz.sys.api.model.SysIdentity;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import javax.annotation.Resource;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ import org.springframework.stereotype.Component;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Component
/*     */ public abstract class TaskRejectActionHandler
/*     */   extends AbstractTaskActionHandler<DefualtTaskActionCmd>
/*     */ {
/*  40 */   private static Logger log = LoggerFactory.getLogger(TaskRejectActionHandler.class);
/*     */   
/*     */   @Resource
/*     */   BpmTaskStackManager k;
/*     */   
/*     */   @Resource
/*     */   BpmTaskOpinionManager aL;
/*     */   
/*     */   @Resource
/*     */   BpmProcessDefService aM;
/*     */   
/*     */   public void g(DefualtTaskActionCmd actionModel) {
/*  52 */     NodeProperties nodeProperties = this.a.getBpmNodeDef(actionModel.getDefId(), actionModel.getNodeId()).getNodeProperties();
/*     */     
/*  54 */     String destinationNode = a(actionModel, nodeProperties);
/*     */ 
/*     */     
/*  57 */     if ("history".equals(nodeProperties.getBackUserMode())) {
/*  58 */       a(actionModel, destinationNode);
/*     */     }
/*     */     
/*  61 */     IBpmTask task = actionModel.getBpmTask();
/*     */     
/*  63 */     if (task.getNodeId().equals(destinationNode)) {
/*  64 */       throw new BusinessException("目标节点不能为当前任务节点", BpmStatusCode.CANNOT_BACK_NODE);
/*     */     }
/*     */     
/*  67 */     actionModel.setDestination(destinationNode);
/*  68 */     log.info("任务【{}-{}】将驳回至节点{}", new Object[] { task.getName(), task.getId(), destinationNode });
/*     */   }
/*     */ 
/*     */   
/*     */   private void a(DefualtTaskActionCmd actionModel, String destinationNode) {
/*  73 */     BpmIdentity bpmIdentity = null;
/*     */     
/*  75 */     List<BpmTaskOpinion> taskOpinions = this.aL.getByInstAndNode(actionModel.getInstanceId(), actionModel.getBpmTask().getNodeId());
/*  76 */     for (BpmTaskOpinion opinion : taskOpinions) {
/*  77 */       if (StringUtil.isNotEmpty(opinion.getApprover())) {
/*  78 */         bpmIdentity = new BpmIdentity(opinion.getApprover(), opinion.getApproverName(), "user");
/*     */       }
/*     */     } 
/*     */     
/*  82 */     if (bpmIdentity != null) {
/*  83 */       List<SysIdentity> list = new ArrayList<SysIdentity>();
/*  84 */       list.add(bpmIdentity);
/*  85 */       actionModel.setBpmIdentity(destinationNode, list);
/*     */     } 
/*     */   }
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
/*     */   protected String a(DefualtTaskActionCmd actionModel, NodeProperties nodeProperties) {
/* 101 */     if (StringUtil.isNotEmpty(actionModel.getDestination())) {
/* 102 */       return actionModel.getDestination();
/*     */     }
/*     */     
/* 105 */     if (nodeProperties != null && StringUtil.isNotEmpty(nodeProperties.getBackNode())) {
/* 106 */       return nodeProperties.getBackNode();
/*     */     }
/*     */ 
/*     */     
/* 110 */     List<BpmTaskStack> stackList = this.k.getByInstanceId(actionModel.getInstanceId());
/* 111 */     BpmTaskStack currentStack = null;
/* 112 */     for (int i = stackList.size() - 1; i > -1; i--) {
/* 113 */       BpmTaskStack stack = (BpmTaskStack)stackList.get(i);
/* 114 */       if (stack.getTaskId().equals(actionModel.getTaskId())) {
/* 115 */         currentStack = stack;
/*     */         
/*     */         break;
/*     */       } 
/*     */     } 
/* 120 */     if (currentStack == null) {
/* 121 */       throw new BusinessException(actionModel.getTaskId() + " currentStack lost 堆栈信息异常 ");
/*     */     }
/*     */ 
/*     */     
/* 125 */     BpmNodeDef nodeDef = this.aM.getBpmNodeDef(actionModel.getDefId(), currentStack.getNodeId());
/* 126 */     if (nodeDef.getIncomeNodes().size() == 1 && ((BpmNodeDef)nodeDef.getIncomeNodes().get(0)).getType() == NodeType.USERTASK) {
/* 127 */       return ((BpmNodeDef)nodeDef.getIncomeNodes().get(0)).getNodeId();
/*     */     }
/*     */ 
/*     */     
/* 131 */     BpmExecutionStack preStack = a(stackList, currentStack.getParentId());
/* 132 */     if (preStack == null) {
/* 133 */       throw new WorkFlowException("上一任务处理记录查找失败，无法驳回！", BpmStatusCode.NO_BACK_TARGET);
/*     */     }
/*     */     
/* 136 */     return preStack.getNodeId();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   BpmExecutionStack a(List<BpmTaskStack> stackList, String id) {
/* 147 */     String parentId = id;
/* 148 */     for (int i = stackList.size() - 1; i > -1; i--) {
/* 149 */       BpmTaskStack stack = (BpmTaskStack)stackList.get(i);
/*     */       
/* 151 */       if (stack.getId().equals(parentId)) {
/* 152 */         parentId = stack.getParentId();
/* 153 */         if ("userTask".equals(stack.getNodeType())) {
/* 154 */           return stack;
/*     */         }
/*     */       } 
/*     */     } 
/* 158 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void h(DefualtTaskActionCmd actionModel) {
/* 164 */     NodeProperties nodeProperties = this.a.getBpmNodeDef(actionModel.getDefId(), actionModel.getNodeId()).getNodeProperties();
/*     */     
/* 166 */     if ("back".equals(nodeProperties.getBackMode())) {
/* 167 */       List<BpmTask> tasks = this.aJ.getByInstIdNodeId(actionModel.getInstanceId(), actionModel.getNodeId());
/* 168 */       if (CollectionUtil.isEmpty(tasks)) {
/* 169 */         throw new WorkFlowException("任务返回节点标记失败，待标记任务查找不到", BpmStatusCode.NO_BACK_TARGET);
/*     */       }
/* 171 */       boolean hasUpdated = false;
/* 172 */       for (BpmTask task : tasks) {
/* 173 */         if (StringUtil.isNotEmpty(task.getActInstId()) && StringUtil.isNotEmpty(task.getTaskId())) {
/* 174 */           if (hasUpdated) {
/* 175 */             throw new WorkFlowException("任务返回节点标记失败，期望查找一条，但是出现多条", BpmStatusCode.NO_BACK_TARGET);
/*     */           }
/*     */           
/* 178 */           task.setBackNode(actionModel.getNodeId());
/* 179 */           this.aJ.update(task);
/* 180 */           hasUpdated = true;
/*     */         } 
/*     */       } 
/* 183 */       if (!hasUpdated) {
/* 184 */         throw new WorkFlowException("任务返回节点标记失败，待标记任务查找不到", BpmStatusCode.NO_BACK_TARGET);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 192 */   public ActionType getActionType() { return ActionType.REJECT; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 197 */   public int getSn() { return 3; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 203 */   public String getConfigPage() { return "/bpm/task/taskOpinionDialog.html"; }
/*     */ }


/* Location:              D:\Repository\com\dstz\agilebpm\wf-core\1.5.1\wf-core-1.5.1-pg.jar!\com\dstz\bpm\engine\action\handler\task\TaskRejectActionHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.0
 */