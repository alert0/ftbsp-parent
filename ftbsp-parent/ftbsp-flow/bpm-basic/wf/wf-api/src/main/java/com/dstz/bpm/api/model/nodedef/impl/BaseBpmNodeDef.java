/*     */ package com.dstz.bpm.api.model.nodedef.impl;
/*     */ 
/*     */ import com.dstz.base.api.exception.BusinessException;
/*     */ import com.dstz.bpm.api.constant.NodeType;
/*     */ import com.dstz.bpm.api.engine.action.button.ButtonFactory;
/*     */ import com.dstz.bpm.api.engine.plugin.context.BpmPluginContext;
/*     */ import com.dstz.bpm.api.exception.BpmStatusCode;
/*     */ import com.dstz.bpm.api.model.def.BpmProcessDef;
/*     */ import com.dstz.bpm.api.model.def.NodeInit;
/*     */ import com.dstz.bpm.api.model.def.NodeProperties;
/*     */ import com.dstz.bpm.api.model.form.BpmForm;
/*     */ import com.dstz.bpm.api.model.nodedef.BpmNodeDef;
/*     */ import com.dstz.bpm.api.model.nodedef.Button;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class BaseBpmNodeDef
/*     */   implements BpmNodeDef
/*     */ {
/*     */   private static final long serialVersionUID = -2044846605763777657L;
/*     */   private String nodeId;
/*     */   private String name;
/*     */   private NodeType type;
/*     */   private BpmNodeDef parentBpmNodeDef;
/*  28 */   private List<BpmNodeDef> incomeNodes = new ArrayList();
/*  29 */   private List<BpmNodeDef> outcomeNodes = new ArrayList();
/*     */   
/*  31 */   private List<BpmPluginContext> bpmPluginContexts = new ArrayList();
/*  32 */   private Map<String, String> attrMap = new HashMap();
/*     */ 
/*     */ 
/*     */   
/*     */   private BpmProcessDef bpmProcessDef;
/*     */ 
/*     */ 
/*     */   
/*     */   private BpmForm mobileForm;
/*     */ 
/*     */   
/*     */   private BpmForm form;
/*     */ 
/*     */   
/*  46 */   private List<NodeInit> nodeInits = new ArrayList();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  51 */   private NodeProperties nodeProperties = new NodeProperties();
/*     */   
/*  53 */   private List<Button> buttons = null;
/*     */   
/*  55 */   private List<Button> buttonList = null;
/*     */ 
/*     */   
/*  58 */   public String getNodeId() { return this.nodeId; }
/*     */ 
/*     */ 
/*     */   
/*  62 */   public void setNodeId(String nodeId) { this.nodeId = nodeId; }
/*     */ 
/*     */ 
/*     */   
/*  66 */   public String getName() { return this.name; }
/*     */ 
/*     */ 
/*     */   
/*  70 */   public void setName(String name) { this.name = name; }
/*     */ 
/*     */ 
/*     */   
/*  74 */   public NodeType getType() { return this.type; }
/*     */ 
/*     */ 
/*     */   
/*  78 */   public void setType(NodeType type) { this.type = type; }
/*     */ 
/*     */ 
/*     */   
/*  82 */   public List<BpmNodeDef> getIncomeNodes() { return this.incomeNodes; }
/*     */ 
/*     */ 
/*     */   
/*  86 */   public void setIncomeNodes(List<BpmNodeDef> incomeNodes) { this.incomeNodes = incomeNodes; }
/*     */ 
/*     */ 
/*     */   
/*  90 */   public List<BpmNodeDef> getOutcomeNodes() { return this.outcomeNodes; }
/*     */ 
/*     */ 
/*     */   
/*  94 */   public void setOutcomeNodes(List<BpmNodeDef> outcomeNodes) { this.outcomeNodes = outcomeNodes; }
/*     */ 
/*     */ 
/*     */   
/*  98 */   public List<BpmPluginContext> getBpmPluginContexts() { return this.bpmPluginContexts; }
/*     */ 
/*     */   
/*     */   public void setBpmPluginContexts(List<BpmPluginContext> bpmPluginContexts) {
/* 102 */     Collections.sort(bpmPluginContexts);
/* 103 */     this.bpmPluginContexts = bpmPluginContexts;
/*     */   }
/*     */   
/*     */   public void setAttribute(String name, String value) {
/* 107 */     name = name.toLowerCase().trim();
/* 108 */     this.attrMap.put(name.toLowerCase(), value);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getAttribute(String name) {
/* 114 */     name = name.toLowerCase().trim();
/* 115 */     return (String)this.attrMap.get(name);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 121 */   public void addIncomeNode(BpmNodeDef bpmNodeDef) { this.incomeNodes.add(bpmNodeDef); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 126 */   public void addOutcomeNode(BpmNodeDef bpmNodeDef) { this.outcomeNodes.add(bpmNodeDef); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 132 */   public BpmNodeDef getParentBpmNodeDef() { return this.parentBpmNodeDef; }
/*     */ 
/*     */ 
/*     */   
/* 136 */   public void setParentBpmNodeDef(BpmNodeDef parentBpmNodeDef) { this.parentBpmNodeDef = parentBpmNodeDef; }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getRealPath() {
/* 141 */     String id = getNodeId();
/* 142 */     BpmNodeDef parent = getParentBpmNodeDef();
/* 143 */     while (parent != null) {
/* 144 */       id = parent.getNodeId() + "/" + id;
/* 145 */       parent = parent.getParentBpmNodeDef();
/*     */     } 
/* 147 */     return id;
/*     */   }
/*     */ 
/*     */   
/* 151 */   public BpmProcessDef getBpmProcessDef() { return this.bpmProcessDef; }
/*     */ 
/*     */ 
/*     */   
/* 155 */   public void setBpmProcessDef(BpmProcessDef bpmProcessDef) { this.bpmProcessDef = bpmProcessDef; }
/*     */ 
/*     */   
/*     */   public BpmProcessDef getRootProcessDef() {
/* 159 */     BpmProcessDef processDef = this.bpmProcessDef;
/* 160 */     BpmProcessDef parent = processDef.getParentProcessDef();
/* 161 */     while (parent != null) {
/* 162 */       processDef = parent;
/* 163 */       parent = parent.getParentProcessDef();
/*     */     } 
/* 165 */     return processDef;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 171 */   public List<BpmNodeDef> getOutcomeTaskNodes() { return getNodeDefList(this.outcomeNodes); }
/*     */ 
/*     */   
/*     */   private List<BpmNodeDef> getNodeDefList(List<BpmNodeDef> bpmNodeDefs) {
/* 175 */     List<BpmNodeDef> bpmNodeList = new ArrayList<BpmNodeDef>();
/* 176 */     for (BpmNodeDef def : bpmNodeDefs) {
/* 177 */       if (NodeType.USERTASK.equals(def.getType()) || NodeType.SIGNTASK.equals(def.getType())) {
/* 178 */         bpmNodeList.add(def); continue;
/* 179 */       }  if (NodeType.SUBPROCESS.equals(def.getType())) {
/* 180 */         SubProcessNodeDef subProcessNodeDef = (SubProcessNodeDef)def;
/*     */         
/* 182 */         BpmNodeDef startNodeDef = subProcessNodeDef.getChildBpmProcessDef().getStartEvent();
/* 183 */         bpmNodeList.addAll(getNodeDefList(startNodeDef.getOutcomeNodes())); continue;
/* 184 */       }  if (NodeType.END.equals(def.getType()) && def.getParentBpmNodeDef() != null && NodeType.SUBPROCESS.equals(def.getParentBpmNodeDef().getType())) {
/* 185 */         SubProcessNodeDef subProcessNodeDef = (SubProcessNodeDef)def.getParentBpmNodeDef();
/* 186 */         bpmNodeList.addAll(getNodeDefList(subProcessNodeDef.getOutcomeNodes())); continue;
/*     */       } 
/* 188 */       bpmNodeList.addAll(getNodeDefList(def.getOutcomeNodes()));
/*     */     } 
/*     */     
/* 191 */     return bpmNodeList;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 196 */   public List<BpmNodeDef> getInnerOutcomeTaskNodes(boolean includeSignTask) { return getInnerOutcomeTaskNodes(this.outcomeNodes, includeSignTask); }
/*     */ 
/*     */ 
/*     */   
/*     */   private List<BpmNodeDef> getInnerOutcomeTaskNodes(List<BpmNodeDef> bpmNodeDefs, boolean includeSignTask) {
/* 201 */     List<BpmNodeDef> bpmNodeList = new ArrayList<BpmNodeDef>();
/* 202 */     for (BpmNodeDef def : bpmNodeDefs) {
/* 203 */       if (NodeType.USERTASK.equals(def.getType()) || (includeSignTask && NodeType.SIGNTASK.equals(def.getType()))) {
/* 204 */         bpmNodeList.add(def); continue;
/* 205 */       }  if (NodeType.SUBPROCESS.equals(def.getType()) || NodeType.CALLACTIVITY.equals(def.getType()) || NodeType.END.equals(def.getType())) {
/*     */         continue;
/*     */       }
/* 208 */       bpmNodeList.addAll(getInnerOutcomeTaskNodes(def.getOutcomeNodes(), includeSignTask));
/*     */     } 
/*     */     
/* 211 */     return bpmNodeList;
/*     */   }
/*     */ 
/*     */   
/*     */   public <T> T getPluginContext(Class<T> cls) {
/* 216 */     BpmPluginContext ctx = null;
/* 217 */     List<BpmPluginContext> list = this.bpmPluginContexts;
/* 218 */     for (BpmPluginContext context : list) {
/* 219 */       if (context.getClass().isAssignableFrom(cls)) {
/* 220 */         ctx = context;
/*     */         break;
/*     */       } 
/*     */     } 
/* 224 */     return (T)ctx;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 229 */   public NodeProperties getNodeProperties() { return this.nodeProperties; }
/*     */ 
/*     */ 
/*     */   
/* 233 */   public void setNodeProperties(NodeProperties nodeProperties) { this.nodeProperties = nodeProperties; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 238 */   public void setButtons(List<Button> buttons) { this.buttons = buttons; }
/*     */ 
/*     */ 
/*     */   
/*     */   public List<Button> getButtons() {
/* 243 */     if (this.buttons != null) return this.buttons; 
/* 244 */     if (this.buttonList != null) return this.buttonList;
/*     */     
/*     */     try {
/* 247 */       this.buttonList = ButtonFactory.generateButtons(this, true);
/* 248 */     } catch (Exception e) {
/* 249 */       throw new BusinessException(BpmStatusCode.TASK_ACTION_BTN_ERROR, e);
/*     */     } 
/* 251 */     return this.buttonList;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isDefaultBtn() {
/* 260 */     if (this.buttons != null)
/* 261 */       return false; 
/* 262 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 267 */     StringBuilder sb = new StringBuilder();
/* 268 */     sb.append(getName()).append(":").append(getNodeId())
/* 269 */       .append(super.toString());
/* 270 */     return sb.toString();
/*     */   }
/*     */ 
/*     */   
/* 274 */   public BpmForm getMobileForm() { return this.mobileForm; }
/*     */ 
/*     */ 
/*     */   
/* 278 */   public void setMobileForm(BpmForm mobileForm) { this.mobileForm = mobileForm; }
/*     */ 
/*     */ 
/*     */   
/* 282 */   public BpmForm getForm() { return this.form; }
/*     */ 
/*     */ 
/*     */   
/* 286 */   public void setForm(BpmForm form) { this.form = form; }
/*     */ }


/* Location:              D:\Repository\com\dstz\agilebpm\wf-api\1.5.1\wf-api-1.5.1.jar!\com\dstz\bpm\api\model\nodedef\impl\BaseBpmNodeDef.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.0
 */