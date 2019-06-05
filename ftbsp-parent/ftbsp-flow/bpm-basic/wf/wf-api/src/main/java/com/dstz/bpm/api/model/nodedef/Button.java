/*     */ package com.dstz.bpm.api.model.nodedef;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Button
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   
/*     */   public Button() {}
/*     */   
/*     */   public Button(String name, String alias) {
/*  16 */     this.name = name;
/*  17 */     this.alias = alias;
/*     */   }
/*     */ 
/*     */   
/*     */   public Button(String name, String alias, String groovyScript, String configPage) {
/*  22 */     this.name = name;
/*  23 */     this.alias = alias;
/*  24 */     this.groovyScript = groovyScript;
/*  25 */     this.configPage = configPage;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  31 */   protected String name = "";
/*     */ 
/*     */ 
/*     */   
/*  35 */   protected String alias = "";
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  40 */   protected String beforeScript = "";
/*     */ 
/*     */ 
/*     */   
/*  44 */   protected String afterScript = "";
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  49 */   protected String groovyScript = "";
/*     */ 
/*     */ 
/*     */   
/*  53 */   protected String configPage = "";
/*     */ 
/*     */   
/*  56 */   public String getName() { return this.name; }
/*     */ 
/*     */ 
/*     */   
/*  60 */   public void setName(String name) { this.name = name; }
/*     */ 
/*     */ 
/*     */   
/*  64 */   public String getAlias() { return this.alias; }
/*     */ 
/*     */ 
/*     */   
/*  68 */   public void setAlias(String alias) { this.alias = alias; }
/*     */ 
/*     */ 
/*     */   
/*  72 */   public String getBeforeScript() { return this.beforeScript; }
/*     */ 
/*     */ 
/*     */   
/*  76 */   public void setBeforeScript(String beforeScript) { this.beforeScript = beforeScript; }
/*     */ 
/*     */ 
/*     */   
/*  80 */   public String getAfterScript() { return this.afterScript; }
/*     */ 
/*     */ 
/*     */   
/*  84 */   public void setAfterScript(String afterScript) { this.afterScript = afterScript; }
/*     */ 
/*     */ 
/*     */   
/*  88 */   public String getGroovyScript() { return this.groovyScript; }
/*     */ 
/*     */ 
/*     */   
/*  92 */   public void setGroovyScript(String groovyScript) { this.groovyScript = groovyScript; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  97 */   public String getConfigPage() { return this.configPage; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 102 */   public void setConfigPage(String configPage) { this.configPage = configPage; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 108 */   public String toString() { return "[name=" + this.name + ", alias=" + this.alias + "]"; }
/*     */ }


/* Location:              D:\Repository\com\dstz\agilebpm\wf-api\1.5.1\wf-api-1.5.1.jar!\com\dstz\bpm\api\model\nodedef\Button.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.0
 */