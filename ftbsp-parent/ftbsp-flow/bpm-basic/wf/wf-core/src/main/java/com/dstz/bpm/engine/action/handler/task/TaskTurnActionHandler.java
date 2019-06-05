/*    */ package com.dstz.bpm.engine.action.handler.task;
/*    */ 
/*    */ import cn.hutool.core.bean.BeanUtil;
/*    */ import com.dstz.base.api.exception.BusinessException;
/*    */ import com.dstz.base.core.id.IdUtil;
/*    */ import com.dstz.bpm.api.constant.ActionType;
/*    */ import com.dstz.bpm.api.constant.TaskStatus;
/*    */ import com.dstz.bpm.api.engine.action.cmd.ActionCmd;
/*    */ import com.dstz.bpm.api.engine.action.cmd.BaseActionCmd;
/*    */ import com.dstz.bpm.api.exception.BpmStatusCode;
/*    */ import com.dstz.bpm.core.manager.BpmTaskOpinionManager;
/*    */ import com.dstz.bpm.core.model.BpmTask;
/*    */ import com.dstz.bpm.core.model.BpmTaskOpinion;
/*    */ import com.dstz.bpm.engine.action.cmd.DefualtTaskActionCmd;
/*    */ import com.dstz.sys.api.model.SysIdentity;
/*    */ import com.dstz.sys.util.ContextUtil;
/*    */ import java.util.Date;
/*    */ import java.util.List;
/*    */ import javax.annotation.Resource;
/*    */ import org.springframework.stereotype.Component;
/*    */ 
/*    */ 
/*    */ 
/*    */ @Component
/*    */ public abstract class TaskTurnActionHandler
/*    */   extends AbstractTaskActionHandler<DefualtTaskActionCmd>
/*    */ {
/*    */   @Resource
/*    */   BpmTaskOpinionManager j;
/*    */   
/* 31 */   public ActionType getActionType() { return ActionType.TURN; }
/*    */ 
/*    */ 
/*    */   
/*    */   public void a(DefualtTaskActionCmd model) {
/* 36 */     f(model);
/* 37 */     k(model);
/*    */     
/* 39 */     BpmTask task = (BpmTask)model.getBpmTask();
/*    */     
/* 41 */     List<SysIdentity> userSetting = model.getBpmIdentity(task.getNodeId());
/*    */     
/* 43 */     if (BeanUtil.isEmpty(userSetting)) {
/* 44 */       throw new BusinessException(BpmStatusCode.NO_ASSIGN_USER);
/*    */     }
/*    */     
/* 47 */     task.setAssigneeId(((SysIdentity)userSetting.get(0)).getId());
/* 48 */     task.setAssigneeNames(((SysIdentity)userSetting.get(0)).getName());
/* 49 */     task.setStatus(TaskStatus.TURN.getKey());
/* 50 */     this.aJ.update(task);
/*    */     
/* 52 */     a(task, model.getOpinion());
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private void a(BpmTask task, String taskOpinion) {
/* 60 */     BpmTaskOpinion opinion = this.j.getByTaskId(task.getId());
/* 61 */     String opinionId = opinion.getId();
/* 62 */     opinion.setId(IdUtil.getSuid());
/* 63 */     this.j.create(opinion);
/*    */     
/* 65 */     opinion.setId(opinionId);
/* 66 */     opinion.setTaskId("-");
/* 67 */     opinion.setOpinion(taskOpinion);
/* 68 */     opinion.setStatus(ActionType.TURN.getKey());
/* 69 */     opinion.setApproveTime(new Date());
/* 70 */     opinion.setApprover(ContextUtil.getCurrentUserId());
/* 71 */     opinion.setApproverName(ContextUtil.getCurrentUser().getFullname());
/* 72 */     opinion.setDurMs(Long.valueOf(opinion.getApproveTime().getTime() - opinion.getCreateTime().getTime()));
/* 73 */     this.j.update(opinion);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void h(DefualtTaskActionCmd actionModel) {}
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void g(DefualtTaskActionCmd actionModel) {}
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 89 */   public int getSn() { return 6; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 94 */   public Boolean isDefault() { return Boolean.valueOf(true); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 99 */   public String getConfigPage() { return "/bpm/task/taskTrunActionDialog.html"; }
/*    */ }


/* Location:              D:\Repository\com\dstz\agilebpm\wf-core\1.5.1\wf-core-1.5.1-pg.jar!\com\dstz\bpm\engine\action\handler\task\TaskTurnActionHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.0
 */