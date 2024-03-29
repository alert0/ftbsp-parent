/*    */ package com.dstz.bpm.engine.plugin.factory;
/*    */ 
/*    */ import cn.hutool.core.collection.CollectionUtil;
/*    */ import com.dstz.base.core.util.AppUtil;
/*    */ import com.dstz.bpm.api.constant.EventType;
/*    */ import com.dstz.bpm.api.engine.plugin.context.BpmPluginContext;
/*    */ import com.dstz.bpm.engine.plugin.runtime.BpmExecutionPlugin;
/*    */ import org.slf4j.Logger;
/*    */ import org.slf4j.LoggerFactory;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BpmPluginFactory
/*    */ {
/* 15 */   protected static Logger LOG = LoggerFactory.getLogger(BpmPluginFactory.class);
/*    */   
/*    */   public static BpmExecutionPlugin buildExecutionPlugin(BpmPluginContext bpmPluginContext, EventType eventType) {
/* 18 */     if (CollectionUtil.isEmpty(bpmPluginContext.getEventTypes())) {
/* 19 */       return null;
/*    */     }
/*    */     
/* 22 */     if (bpmPluginContext.getEventTypes().contains(eventType)) {
/*    */       try {
/* 24 */         return (BpmExecutionPlugin)AppUtil.getBean(bpmPluginContext.getPluginClass());
/*    */       }
/* 26 */       catch (Exception e) {
/* 27 */         e.printStackTrace();
/*    */       } 
/*    */     } else {
/* 30 */       LOG.trace("插件【{}】未注册[{}]事件，跳过执行！ 插件时机：{}", new Object[] { bpmPluginContext.getTitle(), eventType, bpmPluginContext.getEventTypes() });
/*    */     } 
/* 32 */     return null;
/*    */   }
/*    */ }


/* Location:              D:\Repository\com\dstz\agilebpm\wf-core\1.5.1\wf-core-1.5.1-pg.jar!\com\dstz\bpm\engine\plugin\factory\BpmPluginFactory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.0
 */