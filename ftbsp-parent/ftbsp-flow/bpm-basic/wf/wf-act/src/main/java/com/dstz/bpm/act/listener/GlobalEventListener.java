/*    */ package com.dstz.bpm.act.listener;
/*    */ 
/*    */ import com.dstz.base.core.util.AppUtil;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ import org.activiti.engine.delegate.event.ActivitiEvent;
/*    */ import org.activiti.engine.delegate.event.ActivitiEventListener;
/*    */ import org.activiti.engine.delegate.event.impl.ActivitiEventImpl;
/*    */ import org.apache.commons.logging.Log;
/*    */ import org.apache.commons.logging.LogFactory;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class GlobalEventListener
/*    */   implements ActivitiEventListener
/*    */ {
/* 19 */   public Log logger = LogFactory.getLog(GlobalEventListener.class);
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 24 */   private Map<String, String> g = new HashMap();
/*    */ 
/*    */   
/*    */   public void onEvent(ActivitiEvent event) {
/* 28 */     String eventType = event.getType().name();
/*    */     
/* 30 */     String eventHandlerBeanId = (String)this.g.get(eventType);
/* 31 */     if (eventHandlerBeanId != null) {
/* 32 */       ActEventListener handler = (ActEventListener)AppUtil.getBean(eventHandlerBeanId);
/* 33 */       ActivitiEventImpl e = (ActivitiEventImpl)event;
/* 34 */       handler.notify(e);
/*    */     } else {
/* 36 */       this.logger.debug("eventListener:" + eventType + " skiped");
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 42 */   public boolean isFailOnException() { return true; }
/*    */ 
/*    */ 
/*    */   
/* 46 */   public Map<String, String> getHandlers() { return this.g; }
/*    */ 
/*    */ 
/*    */   
/* 50 */   public void setHandlers(Map<String, String> handlers) { this.g = handlers; }
/*    */ }


/* Location:              D:\Repository\com\dstz\agilebpm\wf-act\1.5.1\wf-act-1.5.1-pg.jar!\com\dstz\bpm\act\listener\GlobalEventListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.0
 */