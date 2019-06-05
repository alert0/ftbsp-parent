/*    */ package com.dstz.bpm.plugin.global.taskskip.def;
/*    */ 
/*    */ import com.dstz.bpm.engine.plugin.plugindef.AbstractBpmExecutionPluginDef;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TaskSkipPluginDef
/*    */   extends AbstractBpmExecutionPluginDef
/*    */ {
/*    */   private String s;
/* 12 */   private String script = "";
/*    */ 
/*    */ 
/*    */   
/* 16 */   public String getScript() { return this.script; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 21 */   public void setScript(String script) { this.script = script; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 26 */   public String getSkipTypeArr() { return this.s; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 31 */   public void setSkipTypeArr(String skipTypeArr) { this.s = skipTypeArr; }
/*    */ }


/* Location:              D:\Repository\com\dstz\agilebpm\wf-plugin\1.5.1\wf-plugin-1.5.1-pg.jar!\com\dstz\bpm\plugin\global\taskskip\def\TaskSkipPluginDef.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.0
 */