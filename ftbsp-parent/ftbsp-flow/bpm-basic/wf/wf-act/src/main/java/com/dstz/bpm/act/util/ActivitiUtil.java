/*     */ package com.dstz.bpm.act.util;
/*     */ 
/*     */ import cn.hutool.core.util.ObjectUtil;
/*     */ import com.dstz.base.core.util.AppUtil;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.activiti.engine.RepositoryService;
/*     */ import org.activiti.engine.impl.ProcessEngineImpl;
/*     */ import org.activiti.engine.impl.RepositoryServiceImpl;
/*     */ import org.activiti.engine.impl.interceptor.CommandExecutor;
/*     */ import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
/*     */ import org.activiti.engine.impl.pvm.PvmActivity;
/*     */ import org.activiti.engine.impl.pvm.PvmTransition;
/*     */ import org.activiti.engine.impl.pvm.process.ActivityImpl;
/*     */ import org.activiti.engine.impl.pvm.process.TransitionImpl;
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
/*     */ public class ActivitiUtil
/*     */ {
/*     */   public static CommandExecutor getCommandExecutor() {
/*  31 */     ProcessEngineImpl engine = (ProcessEngineImpl)AppUtil.getBean(org.activiti.engine.ProcessEngine.class);
/*  32 */     return engine.getProcessEngineConfiguration().getCommandExecutor();
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
/*     */   public static Map<String, Object> a(String actDefId, String nodeId, String[] aryDestination) {
/*  45 */     Map<String, Object> map = new HashMap<String, Object>();
/*     */     
/*  47 */     RepositoryService repositoryService = (RepositoryService)AppUtil.getBean(RepositoryService.class);
/*     */ 
/*     */     
/*  50 */     ProcessDefinitionEntity processDefinition = (ProcessDefinitionEntity)((RepositoryServiceImpl)repositoryService).getDeployedProcessDefinition(actDefId);
/*     */     
/*  52 */     ActivityImpl curAct = processDefinition.findActivity(nodeId);
/*  53 */     List<PvmTransition> outTrans = curAct.getOutgoingTransitions();
/*     */     try {
/*  55 */       List<PvmTransition> cloneOutTrans = (List)ObjectUtil.cloneByStream(outTrans);
/*  56 */       map.put("outTrans", cloneOutTrans);
/*  57 */     } catch (Exception ex) {
/*  58 */       ex.printStackTrace();
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  65 */     for (Iterator<PvmTransition> it = outTrans.iterator(); it.hasNext(); ) {
/*  66 */       PvmTransition transition = (PvmTransition)it.next();
/*  67 */       PvmActivity activity = transition.getDestination();
/*  68 */       List<PvmTransition> inTrans = activity.getIncomingTransitions();
/*  69 */       for (Iterator<PvmTransition> itIn = inTrans.iterator(); itIn.hasNext(); ) {
/*  70 */         PvmTransition inTransition = (PvmTransition)itIn.next();
/*  71 */         if (inTransition.getSource().getId().equals(curAct.getId())) {
/*  72 */           itIn.remove();
/*     */         }
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/*  78 */     curAct.getOutgoingTransitions().clear();
/*     */     
/*  80 */     if (aryDestination != null && aryDestination.length > 0) {
/*  81 */       for (String dest : aryDestination) {
/*     */         
/*  83 */         ActivityImpl destAct = processDefinition.findActivity(dest);
/*  84 */         TransitionImpl transitionImpl = curAct.createOutgoingTransition();
/*  85 */         transitionImpl.setDestination(destAct);
/*     */       } 
/*     */     }
/*     */     
/*  89 */     map.put("activity", curAct);
/*     */ 
/*     */     
/*  92 */     return map;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void a(Map<String, Object> map) {
/* 102 */     ActivityImpl curAct = (ActivityImpl)map.get("activity");
/* 103 */     List<PvmTransition> outTrans = (List)map.get("outTrans");
/* 104 */     curAct.getOutgoingTransitions().clear();
/* 105 */     curAct.getOutgoingTransitions().addAll(outTrans);
/*     */   }
/*     */ }


/* Location:              D:\Repository\com\dstz\agilebpm\wf-act\1.5.1\wf-act-1.5.1-pg.jar!\com\dstz\bpm\ac\\util\ActivitiUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.0
 */