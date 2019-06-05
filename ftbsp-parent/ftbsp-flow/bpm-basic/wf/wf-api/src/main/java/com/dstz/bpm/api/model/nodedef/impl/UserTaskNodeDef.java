/*    */ package com.dstz.bpm.api.model.nodedef.impl;
/*    */ 
/*    */ import com.dstz.bpm.api.model.def.BpmVariableDef;
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class UserTaskNodeDef
/*    */   extends BaseBpmNodeDef
/*    */ {
/*    */   private List<BpmVariableDef> variableList;
/*    */   
/* 16 */   public List<BpmVariableDef> getVariableList() { return this.variableList; }
/*    */ 
/*    */ 
/*    */   
/* 20 */   public void setVariableList(List<BpmVariableDef> variableList) { this.variableList = variableList; }
/*    */ }


/* Location:              D:\Repository\com\dstz\agilebpm\wf-api\1.5.1\wf-api-1.5.1.jar!\com\dstz\bpm\api\model\nodedef\impl\UserTaskNodeDef.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.0
 */