/*    */ package com.dstz.bpm.core.listener;
/*    */ 
/*    */ import com.dstz.bpm.act.cache.ActivitiDefCache;
/*    */ import com.dstz.bpm.api.engine.event.BpmDefinitionUpdateEvent;
/*    */ import com.dstz.bpm.api.service.BpmProcessDefService;
/*    */ import com.dstz.bpm.core.model.BpmDefinition;
/*    */ import javax.annotation.Resource;
/*    */ import org.springframework.context.ApplicationEvent;
/*    */ import org.springframework.context.ApplicationListener;
/*    */ import org.springframework.stereotype.Component;
/*    */ 
/*    */ @Component
/*    */ public class BpmDefinitionUpdateEventListener
/*    */   extends Object implements ApplicationListener<BpmDefinitionUpdateEvent> {
/*    */   @Resource
/*    */   BpmProcessDefService a;
/*    */   
/*    */   public void a(BpmDefinitionUpdateEvent event) {
/* 19 */     BpmDefinition bpmDef = (BpmDefinition)event.getSource();
/*    */     
/* 21 */     this.a.clean(bpmDef.getId());
/* 22 */     ActivitiDefCache.clearByDefId(bpmDef.getActDefId());
/*    */   }
/*    */
@Override
public void onApplicationEvent(BpmDefinitionUpdateEvent event) {
	// TODO Auto-generated method stub
	
} }


/* Location:              D:\Repository\com\dstz\agilebpm\wf-core\1.5.1\wf-core-1.5.1-pg.jar!\com\dstz\bpm\core\listener\BpmDefinitionUpdateEventListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.0
 */