/*    */ package com.dstz.bpm.plugin.global.script.executer;
/*    */ 
/*    */ import com.dstz.base.core.util.StringUtil;
/*    */ import com.dstz.bpm.engine.plugin.runtime.abstact.AbstractBpmExecutionPlugin;
/*    */ import com.dstz.bpm.engine.plugin.session.BpmExecutionPluginSession;
/*    */ import com.dstz.bpm.plugin.global.script.def.NodeScriptPluginDef;
/*    */ import com.dstz.sys.api.groovy.IGroovyScriptEngine;
/*    */ import javax.annotation.Resource;
/*    */ import org.springframework.stereotype.Component;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Component
/*    */ public abstract class NodeScriptPluginExecutor
/*    */   extends AbstractBpmExecutionPlugin<BpmExecutionPluginSession, NodeScriptPluginDef>
/*    */ {
/*    */   @Resource
/*    */   IGroovyScriptEngine r;
/*    */   
/*    */   public Void a(BpmExecutionPluginSession pluginSession, NodeScriptPluginDef pluginDef) {
/* 27 */     String script = pluginDef.a(pluginSession.getEventType());
/* 28 */     if (StringUtil.isEmpty(script)) return null;
/*    */     
/* 30 */     this.r.execute(script, pluginSession);
/*    */     
/* 32 */     this.LOG.info("节点{}执行了{}事件脚本", pluginDef.getNodeId(), pluginSession.getEventType().getValue());
/* 33 */     return null;
/*    */   }
/*    */ }


/* Location:              D:\Repository\com\dstz\agilebpm\wf-plugin\1.5.1\wf-plugin-1.5.1-pg.jar!\com\dstz\bpm\plugin\global\script\executer\NodeScriptPluginExecutor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.0
 */