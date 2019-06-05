/*     */ package com.dstz.bpm.engine.plugin.context;
/*     */ 
/*     */ import com.alibaba.fastjson.JSON;
/*     */ import com.dstz.base.core.util.StringUtil;
/*     */ import com.dstz.bpm.api.engine.plugin.context.BpmPluginContext;
/*     */ import com.dstz.bpm.api.engine.plugin.def.BpmPluginDef;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class AbstractBpmPluginContext<T extends BpmPluginDef>
/*     */   extends Object
/*     */   implements BpmPluginContext<T>
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*  19 */   protected Logger LOG = LoggerFactory.getLogger(getClass());
/*     */   
/*     */   private T bV;
/*     */ 
/*     */   
/*     */   public T getBpmPluginDef() {
/*  25 */     return (T)this.bV;
/*     */   }
/*     */ 
/*     */   
/*  29 */   public void setBpmPluginDef(T bpmPluginDef) { this.bV = bpmPluginDef; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  42 */   public JSON getJson() { return (JSON)JSON.toJSON(this.bV); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public T parse(JSON json) {
/*  50 */     T def = (T)parseFromJson(json);
/*  51 */     setBpmPluginDef(def);
/*  52 */     return (T)this.bV;
/*     */   }
/*     */   
/*     */   public T parse(String json) {
/*  56 */     if (StringUtil.isEmpty(json)) return null;
/*     */     
/*  58 */     JSON j = (JSON)JSON.parse(json);
/*  59 */     return (T)parse(j);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*  64 */   public String getType() { return StringUtil.lowerFirst(getClass().getSimpleName().replaceAll("PluginContext", "")); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String persistnce(String defId) {
/*  74 */     String msg = null;
/*  75 */     if (getJson() == null) msg = "清空改插件";
/*     */ 
/*     */     
/*  78 */     return msg;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  87 */   protected int bW = 100;
/*     */ 
/*     */ 
/*     */   
/*  91 */   public Integer getSn() { return Integer.valueOf(this.bW); }
/*     */ 
/*     */ 
/*     */   
/*     */   public int compareTo(BpmPluginContext pluginContext) {
/*  96 */     if (this.bW == pluginContext.getSn().intValue()) return 0;
/*     */     
/*  98 */     if (this.bW > pluginContext.getSn().intValue()) return 1;
/*     */     
/* 100 */     return 0;
/*     */   }
/*     */   
/*     */   protected abstract T parseFromJson(JSON paramJSON);
/*     */ }


/* Location:              D:\Repository\com\dstz\agilebpm\wf-core\1.5.1\wf-core-1.5.1-pg.jar!\com\dstz\bpm\engine\plugin\context\AbstractBpmPluginContext.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.0
 */