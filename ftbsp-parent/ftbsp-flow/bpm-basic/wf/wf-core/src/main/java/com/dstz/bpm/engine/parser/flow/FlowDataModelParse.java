/*    */ package com.dstz.bpm.engine.parser.flow;
/*    */ 
/*    */ import com.dstz.bpm.api.engine.plugin.def.BpmDef;
/*    */ import com.dstz.bpm.api.model.def.BpmDataModel;
/*    */ import com.dstz.bpm.engine.model.DefaultBpmProcessDef;
/*    */ import java.util.HashSet;
/*    */ import java.util.List;
/*    */ import java.util.Set;
/*    */ import org.springframework.stereotype.Component;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Component
/*    */ public abstract class FlowDataModelParse
/*    */   extends AbsFlowParse<BpmDataModel>
/*    */ {
/* 18 */   public String getKey() { return "dataModelList"; }
/*    */ 
/*    */ 
/*    */   
/*    */   public String validate(Object object) {
/* 23 */     List<BpmDataModel> list = (List)object;
/*    */     
/* 25 */     Set<String> keys = new HashSet<String>();
/* 26 */     for (BpmDataModel def : list) {
/* 27 */       String key = def.getCode();
/*    */       
/* 29 */       if (keys.contains(key)) {
/* 30 */         throw new RuntimeException("解析流程数据模型出错code：" + key + "在流程中重复配置！");
/*    */       }
/* 32 */       keys.add(def.getCode());
/*    */     } 
/* 34 */     return "";
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(DefaultBpmProcessDef bpmProcessDef, Object object) {
/* 39 */     List<BpmDataModel> list = (List)object;
/*    */     
/* 41 */     bpmProcessDef.setDataModelList(list);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 46 */   public boolean isArray() { return true; }
/*    */ }


/* Location:              D:\Repository\com\dstz\agilebpm\wf-core\1.5.1\wf-core-1.5.1-pg.jar!\com\dstz\bpm\engine\parser\flow\FlowDataModelParse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.0
 */