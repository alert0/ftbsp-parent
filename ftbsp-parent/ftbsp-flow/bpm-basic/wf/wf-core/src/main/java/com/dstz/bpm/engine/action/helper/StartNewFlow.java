/*     */ package com.dstz.bpm.engine.action.helper;
/*     */ 
/*     */ import com.dstz.base.api.exception.BusinessException;
/*     */ import com.dstz.bpm.api.engine.action.cmd.InstanceActionCmd;
/*     */ import com.dstz.org.api.model.IUser;
/*     */ import com.dstz.sys.util.ContextUtil;
/*     */ import java.util.Map;
/*     */ import java.util.concurrent.CountDownLatch;
/*     */ import org.apache.commons.collections.MapUtils;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ import org.springframework.context.annotation.Scope;
/*     */ import org.springframework.stereotype.Component;
/*     */ import org.springframework.transaction.support.TransactionSynchronizationManager;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Component
/*     */ @Scope("prototype")
/*     */ public class StartNewFlow
/*     */   implements Runnable
/*     */ {
/*  27 */   private static Logger logger = LoggerFactory.getLogger(StartNewFlow.class);
/*  28 */   private static String aO = "startingNewFlow";
/*     */ 
/*     */   
/*     */   private CountDownLatch aP;
/*     */ 
/*     */   
/*     */   private Exception exception;
/*     */ 
/*     */   
/*     */   InstanceActionCmd aQ;
/*     */ 
/*     */   
/*     */   IUser aR;
/*     */   
/*     */   private Map<Object, Object> aS;
/*     */ 
/*     */   
/*     */   public void run() {
/*  46 */     e();
/*  47 */     if (this.aQ == null || this.aR == null) throw new BusinessException("启动新流程失败！ new flow Cmd or starUser cannot be null"); 
/*     */     try {
/*  49 */       ContextUtil.setCurrentUser(this.aR);
/*     */       
/*  51 */       this.aQ.executeCmd();
/*     */       
/*  53 */       logger.debug("新流程启动成功！ ");
/*  54 */       this.aP.countDown();
/*  55 */     } catch (Exception e) {
/*  56 */       this.exception = e;
/*  57 */       this.aP.countDown();
/*  58 */       logger.error("启动新流程流程失败！" + e.getMessage());
/*  59 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  70 */   public static boolean d() { return false; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  75 */   public CountDownLatch getLatch() { return this.aP; }
/*     */ 
/*     */ 
/*     */   
/*  79 */   public void setLatch(CountDownLatch latch) { this.aP = latch; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  84 */   public Exception getException() { return this.exception; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  90 */   public void setException(Exception exception) { this.exception = exception; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  97 */   public void setUser(IUser user) { this.aR = user; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 102 */   public InstanceActionCmd getInstanceCmd() { return this.aQ; }
/*     */ 
/*     */ 
/*     */   
/* 106 */   public void setInstanceCmd(InstanceActionCmd instanceCmd) { this.aQ = instanceCmd; }
/*     */ 
/*     */   
/*     */   private void e() {
/* 110 */     if (MapUtils.isEmpty(this.aS)) {
/*     */       return;
/*     */     }
/*     */     
/* 114 */     for (Map.Entry<Object, Object> entry : this.aS.entrySet()) {
/* 115 */       TransactionSynchronizationManager.bindResource(entry.getKey(), entry.getValue());
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 121 */   public void setTransactionResource(Map<Object, Object> transactionResource) { this.aS = transactionResource; }
/*     */ }


/* Location:              D:\Repository\com\dstz\agilebpm\wf-core\1.5.1\wf-core-1.5.1-pg.jar!\com\dstz\bpm\engine\action\helper\StartNewFlow.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.0
 */