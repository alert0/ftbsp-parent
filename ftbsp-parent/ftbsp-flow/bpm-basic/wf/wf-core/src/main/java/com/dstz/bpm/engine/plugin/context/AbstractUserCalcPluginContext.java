/*    */ package com.dstz.bpm.engine.plugin.context;
/*    */ 
/*    */ import com.alibaba.fastjson.JSON;
/*    */ import com.alibaba.fastjson.JSONObject;
/*    */ import com.dstz.base.core.util.StringUtil;
/*    */ import com.dstz.bpm.api.constant.ExtractType;
/*    */ import com.dstz.bpm.api.engine.constant.LogicType;
/*    */ import com.dstz.bpm.api.engine.plugin.context.PluginParse;
/*    */ import com.dstz.bpm.api.engine.plugin.context.UserCalcPluginContext;
/*    */ import com.dstz.bpm.api.engine.plugin.def.BpmPluginDef;
/*    */ import com.dstz.bpm.api.engine.plugin.def.BpmUserCalcPluginDef;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class AbstractUserCalcPluginContext<T extends BpmUserCalcPluginDef>
/*    */   extends Object
/*    */   implements PluginParse<T>, UserCalcPluginContext
/*    */ {
/*    */   private static final long serialVersionUID = -4447195857368619545L;
/*    */   private T bX;
/*    */   
/*    */   public T getBpmPluginDef() {
/* 24 */     return (T)this.bX;
/*    */   }
/*    */ 
/*    */   
/* 28 */   public void setBpmPluginDef(T bpmPluginDef) { this.bX = bpmPluginDef; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public T parse(JSON pluginDefJson) {
/* 37 */     JSONObject jsonObject = (JSONObject)pluginDefJson;
/* 38 */     T bpmPluginDef = (T)parseJson(jsonObject);
/*    */     
/* 40 */     String extract = jsonObject.getString("extract");
/* 41 */     String logicCal = jsonObject.getString("logicCal");
/*    */     
/* 43 */     bpmPluginDef.setExtract(ExtractType.fromKey(extract));
/* 44 */     bpmPluginDef.setLogicCal(LogicType.fromKey(logicCal));
/*    */     
/* 46 */     setBpmPluginDef(bpmPluginDef);
/* 47 */     return bpmPluginDef;
/*    */   }
/*    */ 
/*    */   
/*    */   public T parse(String jsonStr) {
/* 52 */     JSON json = (JSON)JSON.parse(jsonStr);
/* 53 */     return (T)parse(json);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 58 */   public JSON getJson() { return (JSON)JSON.toJSON(this.bX); }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 63 */   public String getType() { return StringUtil.lowerFirst(getClass().getSimpleName().replaceAll("PluginContext", "")); }
/*    */   
/*    */   protected abstract T parseJson(JSONObject paramJSONObject);
/*    */ }


/* Location:              D:\Repository\com\dstz\agilebpm\wf-core\1.5.1\wf-core-1.5.1-pg.jar!\com\dstz\bpm\engine\plugin\context\AbstractUserCalcPluginContext.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.0
 */