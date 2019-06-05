/*     */ package com.dstz.bpm.engine.plugin.runtime.abstact;
/*     */ 
/*     */ import cn.hutool.core.collection.CollectionUtil;
/*     */ import com.dstz.bpm.api.constant.ExtractType;
/*     */ import com.dstz.bpm.api.engine.plugin.def.BpmUserCalcPluginDef;
/*     */ import com.dstz.bpm.engine.model.BpmIdentity;
/*     */ import com.dstz.bpm.engine.plugin.plugindef.AbstractUserCalcPluginDef;
/*     */ import com.dstz.bpm.engine.plugin.runtime.BpmUserCalcPlugin;
/*     */ import com.dstz.bpm.engine.plugin.session.BpmUserCalcPluginSession;
/*     */ import com.dstz.org.api.model.IUser;
/*     */ import com.dstz.org.api.service.UserService;
/*     */ import com.dstz.sys.api.model.SysIdentity;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.LinkedHashSet;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import javax.annotation.Resource;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class AbstractUserCalcPlugin<M extends BpmUserCalcPluginDef>
/*     */   extends Object
/*     */   implements BpmUserCalcPlugin<M>
/*     */ {
/*     */   @Resource
/*     */   protected UserService ca;
/*     */   
/*     */   public List<SysIdentity> execute(BpmUserCalcPluginSession pluginSession, M pluginDef) {
/*  44 */     if (pluginSession.isPreViewModel().booleanValue() && 
/*  45 */       !supportPreView()) return Collections.emptyList();
/*     */ 
/*     */     
/*  48 */     List<SysIdentity> list = queryByPluginDef(pluginSession, pluginDef);
/*  49 */     if (CollectionUtil.isEmpty(list)) return list;
/*     */     
/*  51 */     ExtractType extractType = ((AbstractUserCalcPluginDef)pluginDef).getExtract();
/*     */     
/*  53 */     Set<SysIdentity> set = new LinkedHashSet<SysIdentity>();
/*  54 */     List<SysIdentity> rtnList = new ArrayList<SysIdentity>();
/*     */ 
/*     */     
/*  57 */     list = extract(list, extractType);
/*     */     
/*  59 */     set.addAll(list);
/*     */     
/*  61 */     rtnList.addAll(set);
/*     */     
/*  63 */     return rtnList;
/*     */   }
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
/*     */   protected List<SysIdentity> extract(List<SysIdentity> bpmIdentities, ExtractType extractType) {
/*  76 */     if (CollectionUtil.isEmpty(bpmIdentities)) return Collections.EMPTY_LIST;
/*     */     
/*  78 */     if (extractType == ExtractType.EXACT_NOEXACT) {
/*  79 */       return bpmIdentities;
/*     */     }
/*     */     
/*  82 */     return extractBpmIdentity(bpmIdentities);
/*     */   }
/*     */   
/*     */   protected List<SysIdentity> extractBpmIdentity(List<SysIdentity> bpmIdentities) {
/*  86 */     List<SysIdentity> results = new ArrayList<SysIdentity>();
/*  87 */     for (SysIdentity bpmIdentity : bpmIdentities) {
/*     */       
/*  89 */       if ("user".equals(bpmIdentity.getType())) {
/*  90 */         results.add(bpmIdentity);
/*     */         
/*     */         continue;
/*     */       } 
/*  94 */       List<IUser> users = (List<IUser>) this.ca.getUserListByGroup(bpmIdentity.getType(), bpmIdentity.getId());
/*  95 */       for (IUser user : users) {
/*  96 */         results.add(new BpmIdentity(user));
/*     */       }
/*     */     } 
/*     */     
/* 100 */     return results;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 108 */   public boolean supportPreView() { return true; }
/*     */   
/*     */   protected abstract List<SysIdentity> queryByPluginDef(BpmUserCalcPluginSession paramBpmUserCalcPluginSession, M paramM);
/*     */ }


/* Location:              D:\Repository\com\dstz\agilebpm\wf-core\1.5.1\wf-core-1.5.1-pg.jar!\com\dstz\bpm\engine\plugin\runtime\abstact\AbstractUserCalcPlugin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.0
 */