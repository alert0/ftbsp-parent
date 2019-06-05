/*    */ package com.dstz.bpm.plugin.global.taskskip.context;
/*    */ 
/*    */ import com.alibaba.fastjson.JSON;
/*    */ import com.dstz.bpm.api.constant.EventType;
/*    */ import com.dstz.bpm.api.engine.plugin.def.BpmPluginDef;
/*    */ import com.dstz.bpm.api.engine.plugin.runtime.RunTimePlugin;
/*    */ import com.dstz.bpm.engine.plugin.context.AbstractBpmPluginContext;
/*    */ import com.dstz.bpm.plugin.global.taskskip.def.TaskSkipPluginDef;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import org.springframework.context.annotation.Scope;
/*    */ import org.springframework.stereotype.Component;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Component
/*    */ @Scope("prototype")
/*    */ public abstract class TaskSkipPluginContext
/*    */   extends AbstractBpmPluginContext<TaskSkipPluginDef>
/*    */ {
/*    */   private static final long serialVersionUID = -8171025388788811778L;
/*    */   
/*    */   public List<EventType> getEventTypes() {
/* 26 */     List<EventType> list = new ArrayList<EventType>();
/* 27 */     list.add(EventType.TASK_POST_CREATE_EVENT);
/* 28 */     return list;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 33 */   public Class<? extends RunTimePlugin> getPluginClass() { return com.dstz.bpm.plugin.global.taskskip.executer.TaskSkipPluginExecutor.class; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 39 */   protected TaskSkipPluginDef d(JSON pluginJson) { return (TaskSkipPluginDef)JSON.toJavaObject(pluginJson, TaskSkipPluginDef.class); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 46 */   public String getTitle() { return "任务跳过"; }
/*    */ }


/* Location:              D:\Repository\com\dstz\agilebpm\wf-plugin\1.5.1\wf-plugin-1.5.1-pg.jar!\com\dstz\bpm\plugin\global\taskskip\context\TaskSkipPluginContext.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.0
 */