/*     */ package com.dstz.bpm.engine.action.handler.instance;
/*     */ 
/*     */ import cn.hutool.core.collection.CollectionUtil;
/*     */ import com.dstz.base.api.exception.BusinessMessage;
/*     */ import com.dstz.base.core.util.JsonUtil;
/*     */ import com.dstz.base.core.util.StringUtil;
/*     */ import com.dstz.bpm.api.constant.ActionType;
/*     */ import com.dstz.bpm.api.engine.action.cmd.ActionCmd;
/*     */ import com.dstz.bpm.api.engine.action.cmd.BaseActionCmd;
/*     */ import com.dstz.bpm.api.model.nodedef.BpmNodeDef;
/*     */ import com.dstz.bpm.core.manager.BpmTaskManager;
/*     */ import com.dstz.bpm.core.model.BpmTask;
/*     */ import com.dstz.bpm.engine.action.cmd.DefaultInstanceActionCmd;
/*     */ import com.dstz.bpm.engine.action.handler.AbsActionHandler;
/*     */ import com.dstz.sys.api.jms.model.DefaultJmsDTO;
/*     */ import com.dstz.sys.api.jms.model.JmsDTO;
/*     */ import com.dstz.sys.api.jms.model.msg.NotifyMessage;
/*     */ import com.dstz.sys.api.jms.producer.JmsProducer;
/*     */ import com.dstz.sys.api.model.SysIdentity;
/*     */ import com.dstz.sys.util.ContextUtil;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import javax.annotation.Resource;
/*     */ import org.springframework.stereotype.Component;
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
/*     */ public abstract class InstanceReminderActionHandler
/*     */   extends AbsActionHandler<DefaultInstanceActionCmd>
/*     */ {
/*     */   @Resource
/*     */   BpmTaskManager aG;
/*     */   @Resource
/*     */   JmsProducer aH;
/*     */   
/*     */   public void a(DefaultInstanceActionCmd model) {
/*  44 */     String opinion = model.getOpinion();
/*  45 */     Boolean isUrgent = Boolean.valueOf(JsonUtil.getBoolean(model.getExtendConf(), "isUrgent"));
/*  46 */     String msgType = JsonUtil.getString(model.getExtendConf(), "msgType");
/*  47 */     if (StringUtil.isEmpty(opinion)) {
/*  48 */       throw new BusinessMessage("催办提醒内容不可为空！");
/*     */     }
/*     */ 
/*     */     
/*  52 */     List<BpmTask> taskList = this.aG.getByInstId(model.getInstanceId());
/*  53 */     if (CollectionUtil.isEmpty(taskList)) {
/*  54 */       throw new BusinessMessage("当前实例任务不存在，无需催办！");
/*     */     }
/*     */     
/*  57 */     List<SysIdentity> notifyUsers = new ArrayList<SysIdentity>();
/*  58 */     for (BpmTask task : taskList) {
/*  59 */       List<SysIdentity> identitys = this.aG.getAssignUserById(task);
/*  60 */       notifyUsers.addAll(identitys);
/*     */ 
/*     */       
/*  63 */       if (isUrgent.booleanValue()) {
/*  64 */         task.setPriority(Integer.valueOf(task.getPriority().intValue() + 1));
/*  65 */         this.aG.update(task);
/*     */       } 
/*     */     } 
/*     */     
/*  69 */     if (CollectionUtil.isEmpty(notifyUsers) || StringUtil.isEmpty(opinion))
/*     */       return; 
/*  71 */     opinion = opinion.replaceAll("subject", ((BpmTask)taskList.get(0)).getSubject());
/*     */     
/*  73 */     List<JmsDTO> jmsDto = new ArrayList<JmsDTO>();
/*     */     
/*  75 */     for (String type : msgType.split(",")) {
/*  76 */       NotifyMessage message = new NotifyMessage("任务催办提醒", opinion, ContextUtil.getCurrentUser(), notifyUsers);
/*  77 */       jmsDto.add(new DefaultJmsDTO(type, message));
/*     */     } 
/*  79 */     this.aH.sendToQueue(jmsDto);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  85 */   public ActionType getActionType() { return ActionType.REMINDER; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  90 */   public int getSn() { return 7; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  98 */   public Boolean isSupport(BpmNodeDef nodeDef) { return Boolean.valueOf(false); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 103 */   public Boolean isDefault() { return Boolean.valueOf(false); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 108 */   public String getConfigPage() { return "/bpm/task/reminderActionDialog.html"; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
	/* 113 */ public String getDefaultGroovyScript() { 
	return "return bpmInstance.getStatus().equals(\"running\");"; 
}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 119 */   public String getDefaultBeforeScript() { return null; }
/*     */   
/*     */   protected void b(DefaultInstanceActionCmd model) {}
/*     */   
/*     */   protected void c(DefaultInstanceActionCmd data) {}
/*     */   
/*     */   protected void d(DefaultInstanceActionCmd actionModel) {}
/*     */   
/*     */   protected void e(DefaultInstanceActionCmd actionModel) {}
/*     */ }


/* Location:              D:\Repository\com\dstz\agilebpm\wf-core\1.5.1\wf-core-1.5.1-pg.jar!\com\dstz\bpm\engine\action\handler\instance\InstanceReminderActionHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.0
 */