/*    */ package com.dstz.bpm.api.engine.event;
/*    */ 
/*    */ import com.dstz.bpm.api.model.def.IBpmDefinition;
/*    */ import org.springframework.context.ApplicationEvent;
/*    */ 
/*    */ public class BpmDefinitionUpdateEvent
/*    */   extends ApplicationEvent {
/*    */   private static final long serialVersionUID = 550560932524738231L;
/*    */   
/* 10 */   public BpmDefinitionUpdateEvent(IBpmDefinition source) { super(source); }
/*    */ }


/* Location:              D:\Repository\com\dstz\agilebpm\wf-api\1.5.1\wf-api-1.5.1.jar!\com\dstz\bpm\api\engine\event\BpmDefinitionUpdateEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.0
 */