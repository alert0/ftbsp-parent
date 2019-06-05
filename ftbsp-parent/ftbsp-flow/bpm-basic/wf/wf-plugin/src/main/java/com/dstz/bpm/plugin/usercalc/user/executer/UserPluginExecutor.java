/*    */ package com.dstz.bpm.plugin.usercalc.user.executer;
/*    */ 
/*    */ import cn.hutool.core.collection.CollectionUtil;
/*    */ import com.dstz.base.api.exception.BusinessException;
/*    */ import com.dstz.bpm.api.engine.plugin.def.BpmUserCalcPluginDef;
/*    */ import com.dstz.bpm.api.exception.BpmStatusCode;
/*    */ import com.dstz.bpm.api.model.def.BpmProcessDef;
/*    */ import com.dstz.bpm.api.service.BpmProcessDefService;
/*    */ import com.dstz.bpm.core.manager.BpmTaskOpinionManager;
/*    */ import com.dstz.bpm.core.model.BpmTaskOpinion;
/*    */ import com.dstz.bpm.engine.model.BpmIdentity;
/*    */ import com.dstz.bpm.engine.plugin.runtime.abstact.AbstractUserCalcPlugin;
/*    */ import com.dstz.bpm.engine.plugin.session.BpmUserCalcPluginSession;
/*    */ import com.dstz.bpm.plugin.usercalc.user.def.UserPluginDef;
/*    */ import com.dstz.org.api.model.IUser;
/*    */ import com.dstz.org.api.service.UserService;
/*    */ import com.dstz.sys.api.model.SysIdentity;
/*    */ import com.dstz.sys.util.ContextUtil;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import javax.annotation.Resource;
/*    */ import org.springframework.stereotype.Component;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Component
/*    */ public abstract class UserPluginExecutor
/*    */   extends AbstractUserCalcPlugin<UserPluginDef>
/*    */ {
/*    */   @Resource
/*    */   BpmTaskOpinionManager y;
/*    */   @Resource
/*    */   UserService I;
/*    */   @Resource
/*    */   BpmProcessDefService J;
/*    */   
/*    */   public List<SysIdentity> a(BpmUserCalcPluginSession pluginSession, UserPluginDef def) {
/* 39 */     List<SysIdentity> list = new ArrayList<SysIdentity>();
/*    */     
/* 41 */     String source = def.getSource();
/* 42 */     if ("start".equals(source)) {
/*    */       
/* 44 */       BpmProcessDef processDef = this.J.getBpmProcessDef(pluginSession.getBpmTask().getDefId());
/* 45 */       String startNodeId = processDef.getStartEvent().getNodeId();
/*    */       
/* 47 */       List<BpmTaskOpinion> opinions = this.y.getByInstAndNode(pluginSession.getBpmTask().getInstId(), startNodeId);
/* 48 */       if (CollectionUtil.isEmpty(opinions)) {
/* 49 */         throw new BusinessException("开始节点意见丢失，无法获取发起人！", BpmStatusCode.USER_CALC_ERROR);
/*    */       }
/* 51 */       BpmTaskOpinion firstNode = (BpmTaskOpinion)opinions.get(0);
/*    */       
/* 53 */       BpmIdentity bpmIdentity1 = new BpmIdentity(firstNode.getApprover(), firstNode.getApproverName(), "user");
/* 54 */       list.add(bpmIdentity1);
/*    */     } 
/* 56 */     if ("currentUser".equals(source)) {
/* 57 */       BpmIdentity bpmIdentity1 = new BpmIdentity(ContextUtil.getCurrentUser());
/* 58 */       list.add(bpmIdentity1);
/* 59 */     } else if ("spec".equals(source)) {
/* 60 */       String userKeys = def.getAccount();
/* 61 */       String[] aryAccount = userKeys.split(",");
/* 62 */       for (String account : aryAccount) {
/* 63 */         IUser user = this.I.getUserByAccount(account);
/* 64 */         if (user == null) {
/* 65 */           throw new BusinessException(account + "用户丢失", BpmStatusCode.USER_CALC_ERROR);
/*    */         }
/*    */         
/* 68 */         BpmIdentity bpmIdentity1 = new BpmIdentity(user);
/* 69 */         list.add(bpmIdentity1);
/*    */       } 
/*    */     } 
/* 72 */     return list;
/*    */   }
/*    */ }


/* Location:              D:\Repository\com\dstz\agilebpm\wf-plugin\1.5.1\wf-plugin-1.5.1-pg.jar!\com\dstz\bpm\plugi\\usercal\\user\executer\UserPluginExecutor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.0
 */