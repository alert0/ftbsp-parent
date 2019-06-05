/*     */ package com.dstz.bpm.act.cache;
/*     */ 
/*     */ import cn.hutool.core.util.ObjectUtil;
/*     */ import com.dstz.base.core.cache.ICache;
/*     */ import com.dstz.base.core.util.AppUtil;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import javax.annotation.Resource;
/*     */ import org.activiti.engine.impl.persistence.deploy.DeploymentCache;
/*     */ import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
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
/*     */ public class ActivitiDefCache
/*     */   extends Object
/*     */   implements DeploymentCache<ProcessDefinitionEntity>
/*     */ {
/*     */   public static void clearLocal() {
/*  28 */     ActivitiDefCache cache = (ActivitiDefCache)AppUtil.getBean(ActivitiDefCache.class);
/*  29 */     cache.clearProcessCache();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  37 */   public static ActivitiDefCache a = null;
/*     */   
/*     */   public static void clearByDefId(String actDefId) {
/*  40 */     if (a == null) {
/*  41 */       a = (ActivitiDefCache)AppUtil.getBean(ActivitiDefCache.class);
/*     */     }
/*  43 */     a.clearProcessDefinitionEntity(actDefId);
/*  44 */     a.clearProcessCache();
/*     */   }
/*     */   
/*  47 */   private ThreadLocal<Map<String, ProcessDefinitionEntity>> b = new ThreadLocal();
/*     */   
/*     */   private void clearProcessDefinitionEntity(String defId) {
/*  50 */     remove(defId);
/*  51 */     this.b.remove();
/*     */   }
/*     */ 
/*     */   
/*  55 */   private void clearProcessCache() { this.b.remove(); }
/*     */ 
/*     */   
/*     */   private void setThreadLocalDef(ProcessDefinitionEntity processEnt) {
/*  59 */     if (this.b.get() == null) {
/*  60 */       Map<String, ProcessDefinitionEntity> map = new HashMap<String, ProcessDefinitionEntity>();
/*  61 */       map.put(processEnt.getId(), processEnt);
/*  62 */       this.b.set(map);
/*     */     } else {
/*  64 */       Map<String, ProcessDefinitionEntity> map = (Map)this.b.get();
/*  65 */       map.put(processEnt.getId(), processEnt);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private ProcessDefinitionEntity getThreadLocalDef(String processDefinitionId) {
/*  71 */     if (this.b.get() == null) {
/*  72 */       return null;
/*     */     }
/*     */     
/*  75 */     Map<String, ProcessDefinitionEntity> map = (Map)this.b.get();
/*  76 */     if (!map.containsKey(processDefinitionId)) {
/*  77 */       return null;
/*     */     }
/*     */     
/*  80 */     return (ProcessDefinitionEntity)map.get(processDefinitionId);
/*     */   }
/*     */ 
/*     */   
/*     */   @Resource
/*     */   ICache c;
/*     */ 
/*     */   
/*     */   public ProcessDefinitionEntity get(String id) {
/*  89 */     ProcessDefinitionEntity p = getThreadLocalDef(id);
/*     */     
/*  91 */     if (p != null) return p;
/*     */ 
/*     */     
/*  94 */     ProcessDefinitionEntity ent = (ProcessDefinitionEntity)this.c.getByKey(id);
/*  95 */     if (ent == null) return null;
/*     */     
/*  97 */     ProcessDefinitionEntity cloneEnt = null;
/*     */     
/*     */     try {
/* 100 */       cloneEnt = (ProcessDefinitionEntity)ObjectUtil.cloneByStream(ent);
/* 101 */     } catch (Exception e) {
/* 102 */       e.printStackTrace();
/*     */     } 
/*     */     
/* 105 */     setThreadLocalDef(cloneEnt);
/* 106 */     return cloneEnt;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 112 */   public void add(String id, ProcessDefinitionEntity object) { this.c.add(id, object); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 118 */   public void remove(String id) { this.c.delByKey(id); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 124 */   public void clear() { this.c.clearAll(); }
/*     */ }


/* Location:              D:\Repository\com\dstz\agilebpm\wf-act\1.5.1\wf-act-1.5.1-pg.jar!\com\dstz\bpm\act\cache\ActivitiDefCache.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.0
 */