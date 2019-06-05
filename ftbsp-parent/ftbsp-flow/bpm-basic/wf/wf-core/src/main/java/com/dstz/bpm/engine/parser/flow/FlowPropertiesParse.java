/*    */ package com.dstz.bpm.engine.parser.flow;
/*    */ 
/*    */ import com.alibaba.fastjson.JSONObject;
/*    */ import com.dstz.bpm.api.engine.plugin.def.BpmDef;
/*    */ import com.dstz.bpm.api.model.def.BpmDefProperties;
/*    */ import com.dstz.bpm.engine.model.DefaultBpmProcessDef;
/*    */ import org.springframework.stereotype.Component;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Component
/*    */ public abstract class FlowPropertiesParse
/*    */   extends AbsFlowParse<BpmDefProperties>
/*    */ {
/*    */   public void b(DefaultBpmProcessDef def, JSONObject flowConf) {
/* 18 */     JSONObject properties = (JSONObject)JSONObject.toJSON(def.getExtProperties());
/* 19 */     if (flowConf.containsKey(getKey())) {
/* 20 */       properties = flowConf.getJSONObject(getKey());
/*    */     }
/* 22 */     BpmDefProperties bpmDefproperties = (BpmDefProperties)JSONObject.toJavaObject(properties, BpmDefProperties.class);
/* 23 */     def.setExtProperties(bpmDefproperties);
/* 24 */     flowConf.put(getKey(), properties);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 29 */   public String getKey() { return "properties"; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void a(DefaultBpmProcessDef bpmProcessDef, Object object) {
/* 35 */     BpmDefProperties properties = (BpmDefProperties)object;
/* 36 */     bpmProcessDef.setExtProperties(properties);
/*    */   }
/*    */ }


/* Location:              D:\Repository\com\dstz\agilebpm\wf-core\1.5.1\wf-core-1.5.1-pg.jar!\com\dstz\bpm\engine\parser\flow\FlowPropertiesParse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.0
 */