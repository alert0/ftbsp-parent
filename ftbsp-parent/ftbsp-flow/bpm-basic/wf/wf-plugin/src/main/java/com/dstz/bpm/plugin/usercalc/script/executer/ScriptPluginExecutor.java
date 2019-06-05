/*    */ package com.dstz.bpm.plugin.usercalc.script.executer;
/*    */ 
/*    */ import cn.hutool.core.collection.CollectionUtil;
/*    */ import com.dstz.base.core.util.StringUtil;
/*    */ import com.dstz.bpm.api.engine.plugin.def.BpmUserCalcPluginDef;
/*    */ import com.dstz.bpm.engine.plugin.runtime.abstact.AbstractUserCalcPlugin;
/*    */ import com.dstz.bpm.engine.plugin.session.BpmUserCalcPluginSession;
/*    */ import com.dstz.bpm.plugin.usercalc.script.def.ScriptPluginDef;
/*    */ import com.dstz.sys.api.groovy.IGroovyScriptEngine;
/*    */ import com.dstz.sys.api.model.SysIdentity;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Collections;
/*    */ import java.util.List;
/*    */ import java.util.Set;
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
/*    */ @Component
/*    */ public abstract class ScriptPluginExecutor
/*    */   extends AbstractUserCalcPlugin<ScriptPluginDef>
/*    */ {
/*    */   @Resource
/*    */   IGroovyScriptEngine r;
/*    */   
/*    */   public List<SysIdentity> a(BpmUserCalcPluginSession pluginSession, ScriptPluginDef def) {
/* 33 */     String script = def.getScript();
/* 34 */     if (StringUtil.isEmpty(script)) {
/* 35 */       return Collections.EMPTY_LIST;
/*    */     }
/*    */     
/* 38 */     Set<SysIdentity> set = (Set)this.r.executeObject(script, pluginSession);
/*    */     
/* 40 */     List<SysIdentity> list = new ArrayList<SysIdentity>();
/* 41 */     if (CollectionUtil.isEmpty(set)) return list;
/*    */     
/* 43 */     list.addAll(set);
/* 44 */     return list;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 49 */   public boolean supportPreView() { return false; }
/*    */ }


/* Location:              D:\Repository\com\dstz\agilebpm\wf-plugin\1.5.1\wf-plugin-1.5.1-pg.jar!\com\dstz\bpm\plugi\\usercalc\script\executer\ScriptPluginExecutor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.0
 */