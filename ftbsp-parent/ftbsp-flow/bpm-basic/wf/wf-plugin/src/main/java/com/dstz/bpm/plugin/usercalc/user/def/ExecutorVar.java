/*     */ package com.dstz.bpm.plugin.usercalc.user.def;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ExecutorVar
/*     */ {
/*     */   public static final String B = "BO";
/*     */   public static final String C = "flowVar";
/*     */   public static final String D = "user";
/*     */   public static final String E = "group";
/*  15 */   private String source = "";
/*     */ 
/*     */   
/*  18 */   private String name = "";
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  23 */   private String F = "";
/*     */ 
/*     */   
/*  26 */   private String G = "";
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  31 */   private String H = "";
/*  32 */   private String dimension = "";
/*     */ 
/*     */   
/*     */   private String value;
/*     */ 
/*     */ 
/*     */   
/*     */   public ExecutorVar() {}
/*     */ 
/*     */ 
/*     */   
/*     */   public ExecutorVar(String source, String name, String executorType, String userValType, String groupValType, String dimension) {
/*  44 */     this.source = source;
/*  45 */     this.name = name;
/*  46 */     this.F = executorType;
/*  47 */     this.G = userValType;
/*  48 */     this.H = groupValType;
/*  49 */     this.dimension = dimension;
/*     */   }
/*     */ 
/*     */   
/*  53 */   public String getSource() { return this.source; }
/*     */ 
/*     */ 
/*     */   
/*  57 */   public void setSource(String source) { this.source = source; }
/*     */ 
/*     */ 
/*     */   
/*  61 */   public String getName() { return this.name; }
/*     */ 
/*     */ 
/*     */   
/*  65 */   public void setName(String name) { this.name = name; }
/*     */ 
/*     */ 
/*     */   
/*  69 */   public String getExecutorType() { return this.F; }
/*     */ 
/*     */ 
/*     */   
/*  73 */   public void setExecutorType(String executorType) { this.F = executorType; }
/*     */ 
/*     */ 
/*     */   
/*  77 */   public String getUserValType() { return this.G; }
/*     */ 
/*     */ 
/*     */   
/*  81 */   public void setUserValType(String userValType) { this.G = userValType; }
/*     */ 
/*     */ 
/*     */   
/*  85 */   public String getGroupValType() { return this.H; }
/*     */ 
/*     */ 
/*     */   
/*  89 */   public void setGroupValType(String groupValType) { this.H = groupValType; }
/*     */ 
/*     */ 
/*     */   
/*  93 */   public String getDimension() { return this.dimension; }
/*     */ 
/*     */ 
/*     */   
/*  97 */   public void setDimension(String dimension) { this.dimension = dimension; }
/*     */ 
/*     */ 
/*     */   
/* 101 */   public String getValue() { return this.value; }
/*     */ 
/*     */ 
/*     */   
/* 105 */   public void setValue(String value) { this.value = value; }
/*     */ }


/* Location:              D:\Repository\com\dstz\agilebpm\wf-plugin\1.5.1\wf-plugin-1.5.1-pg.jar!\com\dstz\bpm\plugi\\usercal\\user\def\ExecutorVar.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.0
 */