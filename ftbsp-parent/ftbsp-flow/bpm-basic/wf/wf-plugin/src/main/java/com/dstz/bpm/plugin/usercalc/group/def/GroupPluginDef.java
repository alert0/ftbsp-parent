/*    */ package com.dstz.bpm.plugin.usercalc.group.def;
/*    */ 
/*    */ import com.dstz.bpm.engine.plugin.plugindef.AbstractUserCalcPluginDef;
/*    */ import org.hibernate.validator.constraints.NotEmpty;
/*    */ 
/*    */ public class GroupPluginDef
/*    */   extends AbstractUserCalcPluginDef
/*    */ {
/*    */   @NotEmpty(message = "人员插件组类型不能为空")
/* 10 */   private String type = "";
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 16 */   private String typeName = "";
/*    */ 
/*    */   
/*    */   @NotEmpty(message = "人员插件组KEY不能为空")
/* 20 */   private String z = "";
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 26 */   private String groupName = "";
/*    */ 
/*    */ 
/*    */   
/* 30 */   public String getGroupKey() { return this.z; }
/*    */ 
/*    */ 
/*    */   
/* 34 */   public void setGroupKey(String groupKey) { this.z = groupKey; }
/*    */ 
/*    */ 
/*    */   
/* 38 */   public String getGroupName() { return this.groupName; }
/*    */ 
/*    */ 
/*    */   
/* 42 */   public void setGroupName(String groupName) { this.groupName = groupName; }
/*    */ 
/*    */ 
/*    */   
/* 46 */   public String getType() { return this.type; }
/*    */ 
/*    */ 
/*    */   
/* 50 */   public void setType(String type) { this.type = type; }
/*    */ 
/*    */ 
/*    */   
/* 54 */   public String getTypeName() { return this.typeName; }
/*    */ 
/*    */ 
/*    */   
/* 58 */   public void setTypeName(String typeName) { this.typeName = typeName; }
/*    */ }


/* Location:              D:\Repository\com\dstz\agilebpm\wf-plugin\1.5.1\wf-plugin-1.5.1-pg.jar!\com\dstz\bpm\plugi\\usercalc\group\def\GroupPluginDef.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.0
 */