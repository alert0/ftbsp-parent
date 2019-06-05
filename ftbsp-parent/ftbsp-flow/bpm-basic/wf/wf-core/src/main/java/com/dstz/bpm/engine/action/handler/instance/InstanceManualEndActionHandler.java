/*     */ package com.dstz.bpm.engine.action.handler.instance;
/*     */ 
/*     */ import com.dstz.base.api.exception.BusinessException;
/*     */ import com.dstz.base.core.util.StringUtil;
/*     */ import com.dstz.bpm.act.service.ActInstanceService;
/*     */ import com.dstz.bpm.api.constant.ActionType;
/*     */ import com.dstz.bpm.api.constant.EventType;
/*     */ import com.dstz.bpm.api.constant.InstanceStatus;
/*     */ import com.dstz.bpm.api.constant.NodeType;
/*     */ import com.dstz.bpm.api.constant.OpinionStatus;
/*     */ import com.dstz.bpm.api.engine.action.cmd.ActionCmd;
/*     */ import com.dstz.bpm.api.engine.action.cmd.BaseActionCmd;
/*     */ import com.dstz.bpm.api.engine.action.cmd.InstanceActionCmd;
/*     */ import com.dstz.bpm.api.engine.context.BpmContext;
/*     */ import com.dstz.bpm.api.engine.plugin.cmd.ExecutionCommand;
/*     */ import com.dstz.bpm.api.exception.BpmStatusCode;
/*     */ import com.dstz.bpm.api.model.inst.IBpmInstance;
/*     */ import com.dstz.bpm.api.model.nodedef.BpmNodeDef;
/*     */ import com.dstz.bpm.core.manager.BpmTaskManager;
/*     */ import com.dstz.bpm.core.manager.BpmTaskOpinionManager;
/*     */ import com.dstz.bpm.core.manager.BpmTaskStackManager;
/*     */ import com.dstz.bpm.core.model.BpmInstance;
/*     */ import com.dstz.bpm.core.model.BpmTask;
/*     */ import com.dstz.bpm.core.model.BpmTaskOpinion;
/*     */ import com.dstz.bpm.engine.action.cmd.DefaultInstanceActionCmd;
/*     */ import com.dstz.bpm.engine.action.cmd.DefualtTaskActionCmd;
/*     */ import com.dstz.bpm.engine.action.handler.AbsActionHandler;
/*     */ import com.dstz.org.api.model.IUser;
/*     */ import com.dstz.sys.util.ContextUtil;
/*     */ import java.util.Date;
/*     */ import java.util.List;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Component;
/*     */ import org.springframework.transaction.annotation.Transactional;
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
/*     */ @Component
/*     */ public abstract class InstanceManualEndActionHandler
/*     */   extends AbsActionHandler<DefualtTaskActionCmd>
/*     */ {
/*     */   @Autowired
/*     */   BpmTaskOpinionManager j;
/*     */   @Autowired
/*     */   ActInstanceService i;
/*     */   @Autowired
/*     */   BpmTaskManager h;
/*     */   @Autowired
/*     */   ExecutionCommand aF;
/*     */   @Autowired
/*     */   BpmTaskStackManager k;
/*     */   
/*     */   @Transactional
/*     */   public void a(DefualtTaskActionCmd model) {
/*  63 */     BpmTask task = (BpmTask)this.h.get(model.getTaskId());
/*  64 */     if (task == null) {
/*  65 */       throw new BusinessException(BpmStatusCode.TASK_NOT_FOUND);
/*     */     }
/*  67 */     model.setBpmTask(task);
/*  68 */     model.setDefId(task.getDefId());
/*  69 */     model.setBpmInstance((IBpmInstance)this.f.get(model.getInstanceId()));
/*     */ 
/*     */     
/*  72 */     k(model);
/*     */     
/*  74 */     BpmContext.setActionModel(model);
/*     */     
/*  76 */     b(model);
/*     */     
/*  78 */     c(model);
/*     */     
/*  80 */     BpmContext.removeActionModel();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*  85 */   public ActionType getActionType() { return ActionType.MANUALEND; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  90 */   public int getSn() { return 6; }
/*     */ 
/*     */ 
/*     */   
/*     */   public Boolean isSupport(BpmNodeDef nodeDef) {
/*  95 */     NodeType nodeType = nodeDef.getType();
/*     */     
/*  97 */     if (nodeType == NodeType.USERTASK || nodeType == NodeType.SIGNTASK) {
/*  98 */       return Boolean.valueOf(true);
/*     */     }
/*     */     
/* 101 */     return Boolean.valueOf(false);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 106 */   public Boolean isDefault() { return Boolean.valueOf(false); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 114 */   public String getConfigPage() { return "/bpm/task/taskOpinionDialog.html"; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 119 */   public String getDefaultGroovyScript() { return ""; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 124 */   public String getDefaultBeforeScript() { return ""; }
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
/*     */   protected void b(DefualtTaskActionCmd actionModel) {
/* 136 */     BpmTaskOpinion bpmTaskOpinion = this.j.getByTaskId(actionModel.getTaskId());
/* 137 */     if (bpmTaskOpinion == null) {
/*     */       return;
/*     */     }
/*     */     
/* 141 */     bpmTaskOpinion.setStatus(OpinionStatus.getByActionName(actionModel.getActionName()).getKey());
/* 142 */     bpmTaskOpinion.setApproveTime(new Date());
/*     */     
/* 144 */     bpmTaskOpinion.setDurMs(Long.valueOf(bpmTaskOpinion.getApproveTime().getTime() - bpmTaskOpinion.getCreateTime().getTime()));
/* 145 */     bpmTaskOpinion.setOpinion(actionModel.getOpinion());
/*     */     
/* 147 */     IUser user = ContextUtil.getCurrentUser();
/* 148 */     if (user != null) {
/* 149 */       bpmTaskOpinion.setApprover(user.getUserId());
/* 150 */       bpmTaskOpinion.setApproverName(user.getFullname());
/*     */     } 
/*     */     
/* 153 */     this.j.update(bpmTaskOpinion);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void c(DefualtTaskActionCmd model) {
/* 162 */     BpmInstance instance = (BpmInstance)model.getBpmInstance();
/* 163 */     if (instance == null) {
/* 164 */       instance = (BpmInstance)this.f.get(model.getInstanceId());
/* 165 */       model.setBpmInstance(instance);
/*     */     } 
/*     */     
/* 168 */     this.i.deleteProcessInstance(instance.getActInstId(), model.getOpinion());
/*     */     
/* 170 */     this.h.removeByInstId(instance.getId());
/*     */     
/* 172 */     this.p.removeByInstId(instance.getId());
/*     */     
/* 174 */     instance.setStatus(InstanceStatus.STATUS_MANUAL_END.getKey());
/* 175 */     instance.setEndTime(new Date());
/* 176 */     instance.setDuration(Long.valueOf(instance.getEndTime().getTime() - instance.getCreateTime().getTime()));
/* 177 */     this.f.update(instance);
/*     */     
/* 179 */     this.k.removeByInstanceId(model.getInstanceId());
/*     */ 
/*     */     
/* 182 */     this.aF.execute(EventType.MANUAL_END, d1(model));
/*     */ 
/*     */     
/* 185 */     if (StringUtil.isZeroEmpty(instance.getParentInstId())) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/* 190 */     List<BpmInstance> subs = this.f.getByPId(instance.getParentInstId());
/* 191 */     for (BpmInstance inst : subs) {
/*     */       
/* 193 */       if (InstanceStatus.STATUS_RUNNING.getKey().equals(inst.getStatus())) {
/*     */         return;
/*     */       }
/*     */     } 
/*     */     
/* 198 */     model.setInstanceId(instance.getParentInstId());
/* 199 */     c(model);
/* 200 */     model.setInstanceId(instance.getId());
/*     */   }
/*     */   
/*     */   private InstanceActionCmd d1(DefualtTaskActionCmd model) {
/* 204 */     DefaultInstanceActionCmd instanceActionCmd = new DefaultInstanceActionCmd();
/* 205 */     instanceActionCmd.setActionName(model.getActionName());
/* 206 */     instanceActionCmd.setActionVariables(model.getActionVariables());
/* 207 */     instanceActionCmd.setBizDataMap(model.getBizDataMap());
/* 208 */     instanceActionCmd.setBpmDefinition(model.getBpmDefinition());
/* 209 */     instanceActionCmd.setBpmIdentities(model.getBpmIdentities());
/* 210 */     instanceActionCmd.setBpmInstance(model.getBpmInstance());
/* 211 */     instanceActionCmd.setBusData(model.getBusData());
/* 212 */     instanceActionCmd.setBusinessKey(model.getBusinessKey());
/* 213 */     instanceActionCmd.setDataMode(model.getDataMode());
/* 214 */     instanceActionCmd.setDefId(model.getDefId());
/* 215 */     instanceActionCmd.setDestination(model.getDestination());
/* 216 */     instanceActionCmd.setFormId(model.getFormId());
/* 217 */     return instanceActionCmd;
/*     */   }
/*     */   
/*     */   protected void e(DefualtTaskActionCmd model) {}
/*     */   
/*     */   protected void f(DefualtTaskActionCmd data) {}
/*     */   
/*     */   public void g(DefualtTaskActionCmd actionModel) {}
/*     */   
/*     */   protected void h(DefualtTaskActionCmd actionModel) {}
/*     */ }


/* Location:              D:\Repository\com\dstz\agilebpm\wf-core\1.5.1\wf-core-1.5.1-pg.jar!\com\dstz\bpm\engine\action\handler\instance\InstanceManualEndActionHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.0
 */