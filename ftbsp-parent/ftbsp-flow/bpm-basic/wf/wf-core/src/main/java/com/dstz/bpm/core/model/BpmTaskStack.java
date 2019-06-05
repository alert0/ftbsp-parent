/*     */ package com.dstz.bpm.core.model;
/*     */ 
/*     */ import com.dstz.base.api.model.IDModel;
/*     */ import com.dstz.bpm.api.engine.action.cmd.ActionCmd;
/*     */ import com.dstz.bpm.api.model.inst.BpmExecutionStack;
/*     */ import java.util.Date;
/*     */ import org.apache.commons.lang3.builder.ToStringBuilder;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BpmTaskStack
/*     */   implements IDModel, BpmExecutionStack
/*     */ {
/*     */   protected String id;
/*     */   protected String taskId;
/*     */   protected String v;
/*     */   protected String parentId;
/*     */   protected String X;
/*     */   protected String nodeName;
/*     */   protected Date startTime;
/*     */   protected Date endTime;
/*     */   protected Short ap;
/*     */   protected String aq;
/*     */   protected String actionName;
/*     */   
/*  74 */   public void setId(String id) { this.id = id; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  82 */   public String getId() { return this.id; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  90 */   public void setTaskId(String taskId) { this.taskId = taskId; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  98 */   public String getTaskId() { return this.taskId; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 106 */   public void setInstId(String instId) { this.v = instId; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 114 */   public String getInstId() { return this.v; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 122 */   public void setParentId(String parentId) { this.parentId = parentId; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 130 */   public String getParentId() { return this.parentId; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 138 */   public void setNodeId(String nodeId) { this.X = nodeId; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 146 */   public String getNodeId() { return this.X; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 154 */   public void setNodeName(String nodeName) { this.nodeName = nodeName; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 162 */   public String getNodeName() { return this.nodeName; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 170 */   public void setStartTime(Date startTime) { this.startTime = startTime; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 178 */   public Date getStartTime() { return this.startTime; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 186 */   public void setEndTime(Date endTime) { this.endTime = endTime; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 194 */   public Date getEndTime() { return this.endTime; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 202 */   public void setIsMulitiTask(Short isMulitiTask) { this.ap = isMulitiTask; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 210 */   public Short getIsMulitiTask() { return this.ap; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 218 */   public String getActionName() { return this.actionName; }
/*     */ 
/*     */ 
/*     */   
/* 222 */   public void setActionName(String actionName) { this.actionName = actionName; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 229 */   public String toString() { return (new ToStringBuilder(this))
/* 230 */       .append("id", this.id)
/* 231 */       .append("taskId", this.taskId)
/* 232 */       .append("instId", this.v)
/* 233 */       .append("parentId", this.parentId)
/* 234 */       .append("nodeId", this.X)
/* 235 */       .append("nodeName", this.nodeName)
/* 236 */       .append("startTime", this.startTime)
/* 237 */       .append("endTime", this.endTime)
/* 238 */       .append("isMulitiTask", this.ap)
/* 239 */       .append("nodeTYpe", this.aq)
/* 240 */       .toString(); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 245 */   public void setNodeType(String nodeType) { this.aq = nodeType; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 251 */   public String getNodeType() { return this.aq; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setActionName(ActionCmd actionModel) {
/* 259 */     if (actionModel != null)
/* 260 */       setActionName(actionModel.getActionName()); 
/*     */   }
/*     */ }


/* Location:              D:\Repository\com\dstz\agilebpm\wf-core\1.5.1\wf-core-1.5.1-pg.jar!\com\dstz\bpm\core\model\BpmTaskStack.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.0
 */