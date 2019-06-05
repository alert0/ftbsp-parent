/*    */ package com.dstz.bpm.plugin.global.script.def;
/*    */ 
/*    */ import com.dstz.bpm.api.constant.EventType;
/*    */ import com.dstz.bpm.engine.plugin.plugindef.AbstractBpmExecutionPluginDef;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ import org.hibernate.validator.constraints.NotEmpty;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class NodeScriptPluginDef
/*    */   extends AbstractBpmExecutionPluginDef
/*    */ {
/*    */   @NotEmpty(message = "事件脚本节点ID不能为空")
/* 16 */   private String h = "";
/*    */   
/* 18 */   private Map<EventType, String> q = new HashMap();
/*    */ 
/*    */   
/* 21 */   public String a(EventType event) { return (String)this.q.get(event); }
/*    */ 
/*    */ 
/*    */   
/* 25 */   public void a(EventType event, String scritp) { this.q.put(event, scritp); }
/*    */ 
/*    */ 
/*    */   
/* 29 */   public Map<EventType, String> getScript() { return this.q; }
/*    */ 
/*    */ 
/*    */   
/* 33 */   public void setScript(Map<EventType, String> script) { this.q = script; }
/*    */ 
/*    */ 
/*    */   
/* 37 */   public String getNodeId() { return this.h; }
/*    */ 
/*    */ 
/*    */   
/* 41 */   public void setNodeId(String nodeId) { this.h = nodeId; }
/*    */ }


/* Location:              D:\Repository\com\dstz\agilebpm\wf-plugin\1.5.1\wf-plugin-1.5.1-pg.jar!\com\dstz\bpm\plugin\global\script\def\NodeScriptPluginDef.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.0
 */