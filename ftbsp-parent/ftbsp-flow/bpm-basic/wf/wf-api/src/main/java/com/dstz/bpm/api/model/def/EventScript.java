/*    */ package com.dstz.bpm.api.model.def;
/*    */ 
/*    */ import com.dstz.bpm.api.constant.ScriptType;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EventScript
/*    */ {
/*    */   private ScriptType scriptType;
/* 12 */   private String content = "";
/*    */ 
/*    */   
/*    */   public EventScript() {}
/*    */   
/*    */   public EventScript(ScriptType scriptType, String content) {
/* 18 */     this.scriptType = scriptType;
/* 19 */     this.content = content;
/*    */   }
/*    */ 
/*    */   
/* 23 */   public ScriptType getScriptType() { return this.scriptType; }
/*    */ 
/*    */ 
/*    */   
/* 27 */   public void setScriptType(ScriptType scriptType) { this.scriptType = scriptType; }
/*    */ 
/*    */ 
/*    */   
/* 31 */   public String getContent() { return this.content; }
/*    */ 
/*    */ 
/*    */   
/* 35 */   public void setContent(String content) { this.content = content; }
/*    */ }


/* Location:              D:\Repository\com\dstz\agilebpm\wf-api\1.5.1\wf-api-1.5.1.jar!\com\dstz\bpm\api\model\def\EventScript.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.0
 */