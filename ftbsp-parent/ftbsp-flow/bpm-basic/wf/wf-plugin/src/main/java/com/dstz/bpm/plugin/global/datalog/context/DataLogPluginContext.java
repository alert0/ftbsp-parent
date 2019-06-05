/*    */ package com.dstz.bpm.plugin.global.datalog.context;
/*    */ 
/*    */ import com.alibaba.fastjson.JSON;
/*    */ import com.dstz.bpm.api.constant.EventType;
/*    */ import com.dstz.bpm.api.engine.plugin.def.BpmPluginDef;
/*    */ import com.dstz.bpm.api.engine.plugin.runtime.RunTimePlugin;
/*    */ import com.dstz.bpm.engine.plugin.context.AbstractBpmPluginContext;
/*    */ import com.dstz.bpm.plugin.global.datalog.def.DataLogPluginDef;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import org.springframework.context.annotation.Scope;
/*    */ import org.springframework.stereotype.Component;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Component("dataLogPluginContext")
/*    */ @Scope("prototype")
/*    */ public abstract class DataLogPluginContext
/*    */   extends AbstractBpmPluginContext<DataLogPluginDef>
/*    */ {
/*    */   private static final long serialVersionUID = -1563849340056571771L;
/*    */   
/*    */   public List getEventTypes() {
/* 27 */     List<EventType> eventTypes = new ArrayList<EventType>();
/* 28 */     eventTypes.add(EventType.TASK_PRE_COMPLETE_EVENT);
/* 29 */     eventTypes.add(EventType.START_EVENT);
/* 30 */     return eventTypes;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 35 */   public Class<? extends RunTimePlugin> getPluginClass() { return com.dstz.bpm.plugin.global.datalog.executer.DataLogPluginExecutor.class; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 40 */   public String getTitle() { return "BO 数据提交日志"; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 45 */   protected DataLogPluginDef a(JSON json) { return (DataLogPluginDef)JSON.toJavaObject(json, DataLogPluginDef.class); }
/*    */ }


/* Location:              D:\Repository\com\dstz\agilebpm\wf-plugin\1.5.1\wf-plugin-1.5.1-pg.jar!\com\dstz\bpm\plugin\global\datalog\context\DataLogPluginContext.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.0
 */