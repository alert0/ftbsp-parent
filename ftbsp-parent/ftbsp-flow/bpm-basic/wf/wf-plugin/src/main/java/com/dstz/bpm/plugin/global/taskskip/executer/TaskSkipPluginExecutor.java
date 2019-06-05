/*     */ package com.dstz.bpm.plugin.global.taskskip.executer;
/*     */ 
/*     */ import cn.hutool.core.collection.CollectionUtil;
/*     */ import com.dstz.base.core.util.StringUtil;
/*     */ import com.dstz.bpm.api.engine.action.cmd.TaskActionCmd;
/*     */ import com.dstz.bpm.api.engine.context.BpmContext;
/*     */ import com.dstz.bpm.api.model.nodedef.BpmNodeDef;
/*     */ import com.dstz.bpm.api.service.BpmProcessDefService;
/*     */ import com.dstz.bpm.engine.action.cmd.DefualtTaskActionCmd;
/*     */ import com.dstz.bpm.engine.constant.TaskSkipType;
/*     */ import com.dstz.bpm.engine.plugin.runtime.abstact.AbstractBpmExecutionPlugin;
/*     */ import com.dstz.bpm.engine.plugin.session.BpmExecutionPluginSession;
/*     */ import com.dstz.bpm.plugin.global.taskskip.def.TaskSkipPluginDef;
/*     */ import com.dstz.sys.api.groovy.IGroovyScriptEngine;
/*     */ import com.dstz.sys.api.model.SysIdentity;
/*     */ import com.dstz.sys.util.ContextUtil;
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
/*     */ 
/*     */ @Component
/*     */ public abstract class TaskSkipPluginExecutor
/*     */   extends AbstractBpmExecutionPlugin<BpmExecutionPluginSession, TaskSkipPluginDef>
/*     */ {
/*     */   @Resource
/*     */   IGroovyScriptEngine n;
/*     */   @Resource
/*     */   BpmProcessDefService d;
/*     */   
/*     */   public Void a(BpmExecutionPluginSession pluginSession, TaskSkipPluginDef pluginDef) {
/*  40 */     DefualtTaskActionCmd model = (DefualtTaskActionCmd)BpmContext.getActionModel();
/*     */ 
/*     */     
/*  43 */     TaskSkipType isSkip = b(pluginSession, pluginDef);
/*     */     
/*  45 */     model.setHasSkipThisTask(isSkip);
/*     */     
/*  47 */     return null;
/*     */   }
/*     */   
/*     */   private TaskSkipType b(BpmExecutionPluginSession pluginSession, TaskSkipPluginDef pluginDef) {
/*  51 */     TaskActionCmd model = (TaskActionCmd)BpmContext.getActionModel();
/*     */     
/*  53 */     TaskSkipType skipResult = TaskSkipType.NO_SKIP;
/*     */     
/*  55 */     if (StringUtil.isEmpty(pluginDef.getSkipTypeArr())) {
/*  56 */       return skipResult;
/*     */     }
/*     */     
/*  59 */     String[] skip = pluginDef.getSkipTypeArr().split(",");
/*     */     
/*  61 */     for (String typeStr : skip) {
/*  62 */       List<BpmNodeDef> list;
List<SysIdentity> identityList1; SysIdentity identity; String userId; List<SysIdentity> identityList; boolean isSkip; TaskSkipType type = TaskSkipType.fromKey(typeStr);
/*  63 */       switch (type.ordinal()) {
/*     */         case 1:
/*  65 */           skipResult = TaskSkipType.ALL_SKIP;
/*     */           break;
/*     */         case 2:
/*  68 */           isSkip = this.n.executeBoolean(pluginDef.getScript(), pluginSession);
/*  69 */           if (isSkip) {
/*  70 */             skipResult = TaskSkipType.SCRIPT_SKIP;
/*     */           }
/*     */           break;
/*     */         
/*     */         case 3:
/*  75 */           identityList = model.getBpmIdentity(model.getBpmTask().getNodeId());
/*  76 */           if (CollectionUtil.isEmpty(identityList) || identityList.size() > 1) {
/*  77 */             return skipResult;
/*     */           }
/*  79 */           userId = ContextUtil.getCurrentUserId();
/*  80 */           identity = (SysIdentity)identityList.get(0);
/*     */           
/*  82 */           if (identity.getId().equals(userId)) {
/*  83 */             skipResult = TaskSkipType.SAME_USER_SKIP;
/*     */           }
/*     */           break;
/*     */         
/*     */         case 4:
/*  88 */           identityList1 = model.getBpmIdentity(model.getBpmTask().getNodeId());
/*  89 */           if (CollectionUtil.isEmpty(identityList1)) {
/*  90 */             skipResult = TaskSkipType.USER_EMPTY_SKIP;
/*     */           }
/*     */           break;
/*     */         
/*     */         case 5:
/*  95 */           list = this.d.getStartNodes(model.getBpmTask().getDefId());
/*  96 */           for (BpmNodeDef def : list) {
/*  97 */             if (def.getNodeId().equals(model.getBpmTask().getNodeId())) {
/*  98 */               skipResult = TaskSkipType.FIRSTNODE_SKIP;
/*     */               break;
/*     */             } 
/*     */           } 
/*     */           break;
/*     */       } 
/*     */ 
/*     */       
/* 106 */       if (skipResult != TaskSkipType.NO_SKIP) {
/* 107 */         this.LOG.info("{}节点【{}】设置了【{}】，将跳过当前任务", new Object[] { model.getBpmTask().getId(), model.getBpmTask().getName(), skipResult.getValue() });
/* 108 */         return skipResult;
/*     */       } 
/*     */     } 
/*     */     
/* 112 */     return skipResult;
/*     */   }
/*     */ }


/* Location:              D:\Repository\com\dstz\agilebpm\wf-plugin\1.5.1\wf-plugin-1.5.1-pg.jar!\com\dstz\bpm\plugin\global\taskskip\executer\TaskSkipPluginExecutor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.0
 */