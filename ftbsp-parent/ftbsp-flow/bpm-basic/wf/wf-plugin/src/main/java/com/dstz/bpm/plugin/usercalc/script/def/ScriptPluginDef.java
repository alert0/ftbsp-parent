/*    */ package com.dstz.bpm.plugin.usercalc.script.def;
/*    */ 
/*    */ import com.dstz.bpm.engine.plugin.plugindef.AbstractUserCalcPluginDef;
/*    */ import org.hibernate.validator.constraints.NotEmpty;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ScriptPluginDef
/*    */   extends AbstractUserCalcPluginDef
/*    */ {
/*    */   @NotEmpty(message = "脚本插件，脚本不能为空")
/* 12 */   private String script = "";
/*    */   @NotEmpty(message = "脚本插件，脚本描述不能为空")
/* 14 */   private String description = "";
/*    */ 
/*    */ 
/*    */   
/* 18 */   public String getScript() { return this.script; }
/*    */ 
/*    */ 
/*    */   
/* 22 */   public void setScript(String script) { this.script = script; }
/*    */ 
/*    */ 
/*    */   
/* 26 */   public String getDescription() { return this.description; }
/*    */ 
/*    */ 
/*    */   
/* 30 */   public void setDescription(String description) { this.description = description; }
/*    */ }


/* Location:              D:\Repository\com\dstz\agilebpm\wf-plugin\1.5.1\wf-plugin-1.5.1-pg.jar!\com\dstz\bpm\plugi\\usercalc\script\def\ScriptPluginDef.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.0
 */