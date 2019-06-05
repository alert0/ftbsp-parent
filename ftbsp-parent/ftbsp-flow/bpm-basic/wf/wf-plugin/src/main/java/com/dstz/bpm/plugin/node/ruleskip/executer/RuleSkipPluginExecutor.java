/*    */ package com.dstz.bpm.plugin.node.ruleskip.executer;
/*    */ 
/*    */ import cn.hutool.core.collection.CollectionUtil;
/*    */ import com.dstz.base.core.util.StringUtil;
/*    */ import com.dstz.bpm.api.engine.action.cmd.TaskActionCmd;
/*    */ import com.dstz.bpm.api.engine.context.BpmContext;
/*    */ import com.dstz.bpm.engine.plugin.runtime.abstact.AbstractBpmExecutionPlugin;
/*    */ import com.dstz.bpm.engine.plugin.session.impl.DefaultBpmTaskPluginSession;
/*    */ import com.dstz.bpm.plugin.node.ruleskip.def.JumpRule;
/*    */ import com.dstz.bpm.plugin.node.ruleskip.def.RuleSkipPluginDef;
/*    */ import com.dstz.sys.api.groovy.IGroovyScriptEngine;
/*    */ import javax.annotation.Resource;
/*    */ import org.springframework.stereotype.Component;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Component
/*    */ public abstract class RuleSkipPluginExecutor
/*    */   extends AbstractBpmExecutionPlugin<DefaultBpmTaskPluginSession, RuleSkipPluginDef>
/*    */ {
/*    */   @Resource
/*    */   IGroovyScriptEngine n;
/*    */   
/*    */   public Void a(DefaultBpmTaskPluginSession pluginSession, RuleSkipPluginDef pluginDef) {
/* 28 */     if (CollectionUtil.isEmpty(pluginDef.getJumpRules())) return null;
/*    */ 
/*    */     
/* 31 */     TaskActionCmd taskAction = (TaskActionCmd)BpmContext.getActionModel();
/* 32 */     if (StringUtil.isNotEmpty(taskAction.getDestination())) {
/* 33 */       this.LOG.info("任务【{}】已经指定了跳转节点【{}】，规则跳转将忽略", pluginSession.getBpmTask().getName(), taskAction.getDestination());
/* 34 */       return null;
/*    */     } 
/*    */     
/* 37 */     for (JumpRule jumpRule : pluginDef.getJumpRules()) {
/* 38 */       if (StringUtil.isEmpty(jumpRule.getScript()) || StringUtil.isEmpty(jumpRule.getScript())) {
/*    */         continue;
/*    */       }
/*    */       
/* 42 */       boolean isJump = this.n.executeBoolean(jumpRule.getScript(), pluginSession);
/*    */       
/* 44 */       if (isJump) {
/* 45 */         taskAction.setDestination(jumpRule.getTargetNode());
/*    */         
/* 47 */         this.LOG.info("节点【{}】规则跳转【{}】条件满足，即将跳转至【{}】", new Object[] { pluginSession.getBpmTask().getName(), jumpRule.getName(), jumpRule.getTargetNode() });
/* 48 */         this.LOG.debug(jumpRule.getScript());
/* 49 */         return null;
/*    */       } 
/*    */     } 
/*    */     
/* 53 */     this.LOG.info("节点{}规则跳转，共{}条，均不符合条件，将正常跳转", pluginSession.getBpmTask().getName(), Integer.valueOf(pluginDef.getJumpRules().size()));
/* 54 */     return null;
/*    */   }
/*    */ }


/* Location:              D:\Repository\com\dstz\agilebpm\wf-plugin\1.5.1\wf-plugin-1.5.1-pg.jar!\com\dstz\bpm\plugin\node\ruleskip\executer\RuleSkipPluginExecutor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.0
 */