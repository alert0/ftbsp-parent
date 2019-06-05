/*    */ package com.dstz.bpm.engine.parser.node;
/*    */ 
/*    */ import cn.hutool.core.collection.CollectionUtil;
/*    */ import cn.hutool.core.map.MapUtil;
/*    */ import cn.hutool.core.util.ArrayUtil;
/*    */ import com.alibaba.fastjson.JSON;
/*    */ import com.alibaba.fastjson.JSONArray;
/*    */ import com.alibaba.fastjson.JSONObject;
/*    */ import com.dstz.base.core.util.StringUtil;
/*    */ import com.dstz.bpm.api.engine.plugin.def.BpmDef;
/*    */ import com.dstz.bpm.api.model.nodedef.Button;
/*    */ import com.dstz.bpm.api.model.nodedef.impl.BaseBpmNodeDef;
/*    */ import java.util.Collection;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import org.springframework.stereotype.Component;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Component
/*    */ public abstract class ButtonsParse
/*    */   extends AbsNodeParse<Button>
/*    */ {
/* 27 */   public String getKey() { return "btnList"; }
/*    */ 
/*    */ 
/*    */   
/*    */   public void a(BaseBpmNodeDef userNodeDef, Object object) {
/* 32 */     List<Button> btnList = (List)object;
/*    */     
/* 34 */     userNodeDef.setButtons(btnList);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 39 */   public boolean isArray() { return true; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void a(BaseBpmNodeDef userNodeDef, Object object, JSON thisJson) {
/* 47 */     JSONObject jsonConfig = (JSONObject)thisJson;
/*    */     
/* 49 */     if (isEmpty(object)) {
/* 50 */       jsonConfig.put("btnList", JSON.toJSON(userNodeDef.getButtons()));
/*    */     }
/*    */   }
/*    */   
/*    */   private boolean isEmpty(Object object) {
/* 55 */     if (object == null) return true;
/*    */     
/* 57 */     if (object instanceof String) return StringUtil.isEmpty((String)object);
/*    */     
/* 59 */     if (object instanceof Collection) {
/* 60 */       return CollectionUtil.isEmpty((Collection)object);
/*    */     }
/*    */     
/* 63 */     if (object.getClass().isArray()) {
/* 64 */       return ArrayUtil.isEmpty((Object[])object);
/*    */     }
/*    */     
/* 67 */     if (object instanceof Map) {
/* 68 */       return MapUtil.isEmpty((Map)object);
/*    */     }
/*    */     
/* 71 */     if (object instanceof JSON) {
/* 72 */       return ((JSONObject)object).isEmpty();
/*    */     }
/*    */     
/* 75 */     if (object instanceof JSONArray) {
/* 76 */       return ((JSONArray)object).isEmpty();
/*    */     }
/* 78 */     return false;
/*    */   }
/*    */ }


/* Location:              D:\Repository\com\dstz\agilebpm\wf-core\1.5.1\wf-core-1.5.1-pg.jar!\com\dstz\bpm\engine\parser\node\ButtonsParse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.0
 */