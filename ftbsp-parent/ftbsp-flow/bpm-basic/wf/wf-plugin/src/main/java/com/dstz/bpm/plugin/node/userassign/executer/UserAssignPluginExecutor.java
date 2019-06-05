/*    */ package com.dstz.bpm.plugin.node.userassign.executer;
/*    */ 
/*    */ import cn.hutool.core.collection.CollectionUtil;
/*    */ import com.alibaba.fastjson.JSON;
/*    */ import com.dstz.bpm.api.engine.action.cmd.TaskActionCmd;
/*    */ import com.dstz.bpm.api.engine.context.BpmContext;
/*    */ import com.dstz.bpm.api.engine.plugin.def.UserAssignRule;
/*    */ import com.dstz.bpm.engine.plugin.factory.BpmPluginSessionFactory;
/*    */ import com.dstz.bpm.engine.plugin.runtime.abstact.AbstractBpmExecutionPlugin;
/*    */ import com.dstz.bpm.engine.plugin.session.BpmExecutionPluginSession;
/*    */ import com.dstz.bpm.engine.plugin.session.BpmUserCalcPluginSession;
/*    */ import com.dstz.bpm.plugin.node.userassign.def.UserAssignPluginDef;
/*    */ import com.dstz.sys.api.groovy.IGroovyScriptEngine;
/*    */ import com.dstz.sys.api.model.SysIdentity;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import javax.annotation.Resource;
/*    */ import org.springframework.stereotype.Component;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Component
/*    */ public abstract class UserAssignPluginExecutor
/*    */   extends AbstractBpmExecutionPlugin<BpmExecutionPluginSession, UserAssignPluginDef>
/*    */ {
/*    */   @Resource
/*    */   IGroovyScriptEngine r;
/*    */   
/*    */   public Void a(BpmExecutionPluginSession pluginSession, UserAssignPluginDef assignPluginDef) {
/* 32 */     TaskActionCmd model = (TaskActionCmd)BpmContext.getActionModel();
/*    */     
/* 34 */     List<SysIdentity> identityList = model.getBpmIdentity(model.getNodeId());
/*    */ 
/*    */     
/* 37 */     if (CollectionUtil.isNotEmpty(identityList)) {
/* 38 */       return null;
/*    */     }
/*    */     
/* 41 */     List<UserAssignRule> ruleList = assignPluginDef.getRuleList();
/* 42 */     if (CollectionUtil.isEmpty(ruleList)) return null;
/*    */     
/* 44 */     BpmUserCalcPluginSession bpmUserCalcPluginSession = BpmPluginSessionFactory.buildBpmUserCalcPluginSession(pluginSession);
/* 45 */     List<SysIdentity> bpmIdentities = UserAssignRuleCalc.a(bpmUserCalcPluginSession, ruleList, Boolean.valueOf(false));
/*    */ 
/*    */     
/* 48 */     List<SysIdentity> identitieList = new ArrayList<SysIdentity>();
/*    */     
/* 50 */     for (SysIdentity identity : bpmIdentities) {
/* 51 */       if (identity == null)
/* 52 */         continue;  identitieList.add(identity);
/*    */     } 
/*    */     
/* 55 */     this.LOG.debug("用户计算插件执行完毕，解析到【{}】条有效人员信息。节点:{}", Integer.valueOf(identitieList.size()), model.getNodeId());
/* 56 */     this.LOG.trace(JSON.toJSONString(identitieList));
/*    */     
/* 58 */     model.setBpmIdentity(model.getNodeId(), identitieList);
/* 59 */     return null;
/*    */   }
/*    */ }


/* Location:              D:\Repository\com\dstz\agilebpm\wf-plugin\1.5.1\wf-plugin-1.5.1-pg.jar!\com\dstz\bpm\plugin\nod\\userassign\executer\UserAssignPluginExecutor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.0
 */