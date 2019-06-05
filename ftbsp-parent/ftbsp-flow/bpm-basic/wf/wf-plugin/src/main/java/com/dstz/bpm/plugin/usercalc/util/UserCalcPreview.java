/*    */ package com.dstz.bpm.plugin.usercalc.util;
/*    */ 
/*    */ import cn.hutool.core.collection.CollectionUtil;
/*    */ import com.dstz.bpm.api.constant.EventType;
/*    */ import com.dstz.bpm.api.engine.plugin.context.BpmPluginContext;
/*    */ import com.dstz.bpm.api.engine.plugin.def.BpmPluginDef;
/*    */ import com.dstz.bpm.api.engine.plugin.def.UserAssignRule;
/*    */ import com.dstz.bpm.api.model.nodedef.BpmNodeDef;
/*    */ import com.dstz.bpm.engine.action.cmd.DefualtTaskActionCmd;
/*    */ import com.dstz.bpm.engine.plugin.factory.BpmPluginSessionFactory;
/*    */ import com.dstz.bpm.engine.plugin.session.BpmExecutionPluginSession;
/*    */ import com.dstz.bpm.engine.plugin.session.BpmUserCalcPluginSession;
/*    */ import com.dstz.bpm.plugin.node.userassign.def.UserAssignPluginDef;
/*    */ import com.dstz.bpm.plugin.node.userassign.executer.UserAssignRuleCalc;
/*    */ import com.dstz.sys.api.model.SysIdentity;
/*    */ import java.util.Collections;
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class UserCalcPreview
/*    */ {
/*    */   public static List<SysIdentity> calcNodeUsers(BpmNodeDef userNode, DefualtTaskActionCmd taskModel) {
/* 26 */     for (BpmPluginContext bpmPluginContext : userNode.getBpmPluginContexts()) {
/* 27 */       BpmPluginDef bpmPluginDef = bpmPluginContext.getBpmPluginDef();
/* 28 */       if (!(bpmPluginDef instanceof UserAssignPluginDef)) {
/*    */         continue;
/*    */       }
/* 31 */       UserAssignPluginDef userAssignPluginDef = (UserAssignPluginDef)bpmPluginDef;
/* 32 */       BpmExecutionPluginSession bpmTaskSession = BpmPluginSessionFactory.buildTaskPluginSession(taskModel, EventType.TASK_COMPLETE_EVENT);
/* 33 */       List<UserAssignRule> ruleList = userAssignPluginDef.getRuleList();
/* 34 */       if (CollectionUtil.isEmpty(ruleList)) {
/*    */         continue;
/*    */       }
/* 37 */       BpmUserCalcPluginSession bpmUserCalcPluginSession = BpmPluginSessionFactory.buildBpmUserCalcPluginSession(bpmTaskSession);
/* 38 */       return UserAssignRuleCalc.a(bpmUserCalcPluginSession, ruleList, Boolean.valueOf(true));
/*    */     } 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 44 */     return Collections.emptyList();
/*    */   }
/*    */ }


/* Location:              D:\Repository\com\dstz\agilebpm\wf-plugin\1.5.1\wf-plugin-1.5.1-pg.jar!\com\dstz\bpm\plugi\\usercal\\util\UserCalcPreview.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.0
 */