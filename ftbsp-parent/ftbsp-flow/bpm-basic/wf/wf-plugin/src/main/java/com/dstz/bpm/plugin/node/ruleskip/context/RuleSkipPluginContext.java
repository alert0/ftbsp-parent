/*    */ package com.dstz.bpm.plugin.node.ruleskip.context;
/*    */ 
/*    */ import com.alibaba.fastjson.JSON;
/*    */ import com.dstz.bpm.api.constant.EventType;
/*    */ import com.dstz.bpm.api.engine.plugin.def.BpmPluginDef;
/*    */ import com.dstz.bpm.api.engine.plugin.runtime.RunTimePlugin;
/*    */ import com.dstz.bpm.engine.plugin.context.AbstractBpmPluginContext;
/*    */ import com.dstz.bpm.plugin.node.ruleskip.def.JumpRule;
/*    */ import com.dstz.bpm.plugin.node.ruleskip.def.RuleSkipPluginDef;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import org.springframework.context.annotation.Scope;
/*    */ import org.springframework.stereotype.Component;
/*    */ 
/*    */ 
/*    */ 
/*    */ @Component
/*    */ @Scope("prototype")
/*    */ public abstract class RuleSkipPluginContext
/*    */   extends AbstractBpmPluginContext<RuleSkipPluginDef>
/*    */ {
/*    */   private static final long serialVersionUID = 8784633971785686365L;
/*    */   
/*    */   public List getEventTypes() {
/* 25 */     List<EventType> eventTypes = new ArrayList<EventType>();
/* 26 */     eventTypes.add(EventType.TASK_PRE_COMPLETE_EVENT);
/* 27 */     return eventTypes;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 32 */   public Class<? extends RunTimePlugin> getPluginClass() { return com.dstz.bpm.plugin.node.ruleskip.executer.RuleSkipPluginExecutor.class; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 37 */   public String getTitle() { return "规则跳转"; }
/*    */ 
/*    */ 
/*    */   
/*    */   protected RuleSkipPluginDef e(JSON json) {
/* 42 */     RuleSkipPluginDef def = new RuleSkipPluginDef();
/*    */     
/* 44 */     List<JumpRule> list = JSON.parseArray(json.toJSONString(), JumpRule.class);
/* 45 */     def.setJumpRules(list);
/* 46 */     return def;
/*    */   }
/*    */ }


/* Location:              D:\Repository\com\dstz\agilebpm\wf-plugin\1.5.1\wf-plugin-1.5.1-pg.jar!\com\dstz\bpm\plugin\node\ruleskip\context\RuleSkipPluginContext.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.0
 */