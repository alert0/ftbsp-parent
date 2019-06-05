/*     */ package com.dstz.bpm.act.img;
/*     */ 
/*     */ import com.dstz.base.core.util.AppUtil;
/*     */ import com.dstz.base.core.util.StringUtil;
/*     */ import com.dstz.base.core.util.ThreadMapUtil;
/*     */ import java.awt.Paint;
/*     */ import java.io.InputStream;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.activiti.bpmn.model.Activity;
/*     */ import org.activiti.bpmn.model.Artifact;
/*     */ import org.activiti.bpmn.model.BpmnModel;
/*     */ import org.activiti.bpmn.model.FlowElement;
/*     */ import org.activiti.bpmn.model.FlowElementsContainer;
/*     */ import org.activiti.bpmn.model.FlowNode;
/*     */ import org.activiti.bpmn.model.Gateway;
/*     */ import org.activiti.bpmn.model.GraphicInfo;
/*     */ import org.activiti.bpmn.model.Lane;
/*     */ import org.activiti.bpmn.model.MultiInstanceLoopCharacteristics;
/*     */ import org.activiti.bpmn.model.Pool;
/*     */ import org.activiti.bpmn.model.Process;
/*     */ import org.activiti.bpmn.model.SequenceFlow;
/*     */ import org.activiti.bpmn.model.SubProcess;
/*     */ import org.activiti.engine.ProcessEngineConfiguration;
/*     */ import org.activiti.image.impl.DefaultProcessDiagramGenerator;
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
/*     */ public class BpmProcessDiagramGenerator
/*     */   extends DefaultProcessDiagramGenerator
/*     */ {
/*     */   private static ProcessEngineConfiguration processEngineConfiguration;
/*     */   
/*     */   public InputStream generateDiagram(BpmnModel bpmnModel, String imageType, Map<String, Paint> nodeMap, Map<String, Paint> flowMap) {
/*  57 */     prepareBpmnModel(bpmnModel);
/*     */     
/*  59 */     BpmProcessDiagramCanvas processDiagramCanvas = initProcessDiagramCanvas(bpmnModel, imageType, processEngineConfiguration().getActivityFontName(), processEngineConfiguration().getActivityFontName(), processEngineConfiguration().getAnnotationFontName(), processEngineConfiguration().getClassLoader());
/*     */ 
/*     */     
/*  62 */     for (Pool pool : bpmnModel.getPools()) {
/*  63 */       GraphicInfo graphicInfo = bpmnModel.getGraphicInfo(pool.getId());
/*  64 */       processDiagramCanvas.drawPoolOrLane(pool.getName(), graphicInfo);
/*     */     } 
/*     */ 
/*     */     
/*  68 */     for (Process process : bpmnModel.getProcesses()) {
/*  69 */       for (Lane lane : process.getLanes()) {
/*  70 */         GraphicInfo graphicInfo = bpmnModel.getGraphicInfo(lane.getId());
/*  71 */         processDiagramCanvas.drawPoolOrLane(lane.getName(), graphicInfo);
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/*  76 */     for (Process process : bpmnModel.getProcesses()) {
/*  77 */       for (FlowNode flowNode : process.findFlowElementsOfType(FlowNode.class)) {
/*  78 */         drawActivity(processDiagramCanvas, bpmnModel, flowNode, nodeMap, flowMap, 1.0D);
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/*  83 */     for (Process process : bpmnModel.getProcesses()) {
/*     */       
/*  85 */       for (Artifact artifact : process.getArtifacts()) {
/*  86 */         drawArtifact(processDiagramCanvas, bpmnModel, artifact);
/*     */       }
/*     */       
/*  89 */       List<SubProcess> subProcesses = process.findFlowElementsOfType(SubProcess.class, true);
/*  90 */       if (subProcesses != null) {
/*  91 */         for (SubProcess subProcess : subProcesses) {
/*  92 */           for (Artifact subProcessArtifact : subProcess.getArtifacts()) {
/*  93 */             drawArtifact(processDiagramCanvas, bpmnModel, subProcessArtifact);
/*     */           }
/*     */         } 
/*     */       }
/*     */     } 
/*     */     
/*  99 */     return processDiagramCanvas.generateImage(imageType);
/*     */   }
/*     */ 
/*     */   
/*     */   private void drawActivity(BpmProcessDiagramCanvas processDiagramCanvas, BpmnModel bpmnModel, FlowNode flowNode, Map<String, Paint> nodes, Map<String, Paint> flows, double scaleFactor) {
/* 104 */     ThreadMapUtil.put("BpmProcessDiagramGenerator_flowNode", flowNode);
/*     */     
/* 106 */     DefaultProcessDiagramGenerator.ActivityDrawInstruction drawInstruction = (DefaultProcessDiagramGenerator.ActivityDrawInstruction)this.activityDrawInstructions.get(flowNode.getClass());
/* 107 */     if (drawInstruction != null) {
/* 108 */       drawInstruction.draw(processDiagramCanvas, bpmnModel, flowNode);
/*     */ 
/*     */       
/* 111 */       boolean multiInstanceSequential = false, multiInstanceParallel = false, collapsed = false;
/* 112 */       if (flowNode instanceof Activity) {
/* 113 */         Activity activity = (Activity)flowNode;
/* 114 */         MultiInstanceLoopCharacteristics multiInstanceLoopCharacteristics = activity.getLoopCharacteristics();
/* 115 */         if (multiInstanceLoopCharacteristics != null) {
/* 116 */           multiInstanceSequential = multiInstanceLoopCharacteristics.isSequential();
/* 117 */           multiInstanceParallel = !multiInstanceSequential;
/*     */         } 
/*     */       } 
/*     */ 
/*     */       
/* 122 */       GraphicInfo graphicInfo = bpmnModel.getGraphicInfo(flowNode.getId());
/* 123 */       if (flowNode instanceof SubProcess) {
/* 124 */         collapsed = (graphicInfo.getExpanded() != null && !graphicInfo.getExpanded().booleanValue());
/* 125 */       } else if (flowNode instanceof org.activiti.bpmn.model.CallActivity) {
/* 126 */         collapsed = true;
/*     */       } 
/*     */       
/* 129 */       if (scaleFactor == 1.0D)
/*     */       {
/* 131 */         processDiagramCanvas.drawActivityMarkers((int)graphicInfo.getX(), (int)graphicInfo.getY(), (int)graphicInfo.getWidth(), (int)graphicInfo.getHeight(), multiInstanceSequential, multiInstanceParallel, collapsed);
/*     */       }
/*     */ 
/*     */       
/* 135 */       if (nodes.keySet().contains(flowNode.getId())) {
/* 136 */         drawHighLight(processDiagramCanvas, bpmnModel.getGraphicInfo(flowNode.getId()), (Paint)nodes.get(flowNode.getId()));
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 142 */     for (SequenceFlow sequenceFlow : flowNode.getOutgoingFlows()) {
/* 143 */       boolean highLighted = flows.keySet().contains(sequenceFlow.getId());
/* 144 */       String defaultFlow = null;
/* 145 */       if (flowNode instanceof Activity) {
/* 146 */         defaultFlow = ((Activity)flowNode).getDefaultFlow();
/* 147 */       } else if (flowNode instanceof Gateway) {
/* 148 */         defaultFlow = ((Gateway)flowNode).getDefaultFlow();
/*     */       } 
/*     */       
/* 151 */       boolean isDefault = false;
/* 152 */       if (defaultFlow != null && defaultFlow.equalsIgnoreCase(sequenceFlow.getId())) {
/* 153 */         isDefault = true;
/*     */       }
/* 155 */       boolean drawConditionalIndicator = (sequenceFlow.getConditionExpression() != null && !(flowNode instanceof Gateway));
/*     */       
/* 157 */       String sourceRef = sequenceFlow.getSourceRef();
/* 158 */       String targetRef = sequenceFlow.getTargetRef();
/* 159 */       FlowElement sourceElement = bpmnModel.getFlowElement(sourceRef);
/* 160 */       FlowElement targetElement = bpmnModel.getFlowElement(targetRef);
/* 161 */       List<GraphicInfo> graphicInfoList = bpmnModel.getFlowLocationGraphicInfo(sequenceFlow.getId());
/* 162 */       if (graphicInfoList != null && graphicInfoList.size() > 0) {
/* 163 */         graphicInfoList = connectionPerfectionizer(processDiagramCanvas, bpmnModel, sourceElement, targetElement, graphicInfoList);
/* 164 */         int[] xPoints = new int[graphicInfoList.size()];
/* 165 */         int[] yPoints = new int[graphicInfoList.size()];
/*     */         
/* 167 */         for (int i = 1; i < graphicInfoList.size(); i++) {
/* 168 */           GraphicInfo graphicInfo = (GraphicInfo)graphicInfoList.get(i);
/* 169 */           GraphicInfo previousGraphicInfo = (GraphicInfo)graphicInfoList.get(i - 1);
/*     */           
/* 171 */           if (i == 1) {
/* 172 */             xPoints[0] = (int)previousGraphicInfo.getX();
/* 173 */             yPoints[0] = (int)previousGraphicInfo.getY();
/*     */           } 
/* 175 */           xPoints[i] = (int)graphicInfo.getX();
/* 176 */           yPoints[i] = (int)graphicInfo.getY();
/*     */         } 
/*     */ 
/*     */         
/* 180 */         processDiagramCanvas.drawSequenceflow(xPoints, yPoints, drawConditionalIndicator, isDefault, highLighted, (Paint)flows.get(sequenceFlow.getId()), scaleFactor);
/*     */ 
/*     */ 
/*     */         
/* 184 */         if (StringUtil.isNotEmpty(sequenceFlow.getName()) && !sequenceFlow.getName().startsWith("连线")) {
/* 185 */           GraphicInfo info = new GraphicInfo();
/* 186 */           info.setX(((xPoints[0] + xPoints[1]) / 2));
/* 187 */           info.setY(((yPoints[0] + yPoints[1]) / 2 - 15));
/* 188 */           processDiagramCanvas.drawLabel(sequenceFlow.getName(), info, false);
/*     */         } 
/*     */ 
/*     */         
/* 192 */         GraphicInfo labelGraphicInfo = bpmnModel.getLabelGraphicInfo(sequenceFlow.getId());
/* 193 */         if (labelGraphicInfo != null) {
/* 194 */           processDiagramCanvas.drawLabel(sequenceFlow.getName(), labelGraphicInfo, false);
/*     */         }
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 200 */     if (flowNode instanceof FlowElementsContainer) {
/* 201 */       for (FlowElement nestedFlowElement : ((FlowElementsContainer)flowNode).getFlowElements()) {
/* 202 */         if (nestedFlowElement instanceof FlowNode) {
/* 203 */           drawActivity(processDiagramCanvas, bpmnModel, (FlowNode)nestedFlowElement, nodes, flows, scaleFactor);
/*     */         }
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/* 209 */     if (flowNode instanceof org.activiti.bpmn.model.ExclusiveGateway) {
/* 210 */       GraphicInfo graphicInfo = bpmnModel.getGraphicInfo(flowNode.getId());
/* 211 */       GraphicInfo info = new GraphicInfo();
/* 212 */       info.setX(graphicInfo.getX() + 40.0D);
/* 213 */       info.setY(graphicInfo.getY() + 40.0D);
/* 214 */       processDiagramCanvas.drawLabel(flowNode.getName(), info, true);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 220 */   private void drawHighLight(BpmProcessDiagramCanvas processDiagramCanvas, GraphicInfo graphicInfo, Paint paint) { processDiagramCanvas.drawHighLight((int)graphicInfo.getX(), (int)graphicInfo.getY(), (int)graphicInfo.getWidth(), (int)graphicInfo.getHeight(), paint); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static BpmProcessDiagramCanvas initProcessDiagramCanvas(BpmnModel bpmnModel, String imageType, String activityFontName, String labelFontName, String annotationFontName, ClassLoader customClassLoader) {
/* 227 */     double minX = Double.MAX_VALUE;
/* 228 */     double maxX = 0.0D;
/* 229 */     double minY = Double.MAX_VALUE;
/* 230 */     double maxY = 0.0D;
/*     */     
/* 232 */     for (Pool pool : bpmnModel.getPools()) {
/* 233 */       GraphicInfo graphicInfo = bpmnModel.getGraphicInfo(pool.getId());
/* 234 */       minX = graphicInfo.getX();
/* 235 */       maxX = graphicInfo.getX() + graphicInfo.getWidth();
/* 236 */       minY = graphicInfo.getY();
/* 237 */       maxY = graphicInfo.getY() + graphicInfo.getHeight();
/*     */     } 
/*     */     
/* 240 */     List<FlowNode> flowNodes = gatherAllFlowNodes(bpmnModel);
/* 241 */     for (FlowNode flowNode : flowNodes) {
/*     */       
/* 243 */       GraphicInfo flowNodeGraphicInfo = bpmnModel.getGraphicInfo(flowNode.getId());
/*     */ 
/*     */       
/* 246 */       if (flowNodeGraphicInfo.getX() + flowNodeGraphicInfo.getWidth() > maxX) {
/* 247 */         maxX = flowNodeGraphicInfo.getX() + flowNodeGraphicInfo.getWidth();
/*     */       }
/* 249 */       if (flowNodeGraphicInfo.getX() < minX) {
/* 250 */         minX = flowNodeGraphicInfo.getX();
/*     */       }
/*     */       
/* 253 */       if (flowNodeGraphicInfo.getY() + flowNodeGraphicInfo.getHeight() > maxY) {
/* 254 */         maxY = flowNodeGraphicInfo.getY() + flowNodeGraphicInfo.getHeight();
/*     */       }
/* 256 */       if (flowNodeGraphicInfo.getY() < minY) {
/* 257 */         minY = flowNodeGraphicInfo.getY();
/*     */       }
/*     */       
/* 260 */       for (SequenceFlow sequenceFlow : flowNode.getOutgoingFlows()) {
/* 261 */         List<GraphicInfo> graphicInfoList = bpmnModel.getFlowLocationGraphicInfo(sequenceFlow.getId());
/* 262 */         if (graphicInfoList != null) {
/* 263 */           for (GraphicInfo graphicInfo : graphicInfoList) {
/*     */             
/* 265 */             if (graphicInfo.getX() > maxX) {
/* 266 */               maxX = graphicInfo.getX();
/*     */             }
/* 268 */             if (graphicInfo.getX() < minX) {
/* 269 */               minX = graphicInfo.getX();
/*     */             }
/*     */             
/* 272 */             if (graphicInfo.getY() > maxY) {
/* 273 */               maxY = graphicInfo.getY();
/*     */             }
/* 275 */             if (graphicInfo.getY() < minY) {
/* 276 */               minY = graphicInfo.getY();
/*     */             }
/*     */           } 
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 283 */     List<Artifact> artifacts = gatherAllArtifacts(bpmnModel);
/* 284 */     for (Artifact artifact : artifacts) {
/*     */       
/* 286 */       GraphicInfo artifactGraphicInfo = bpmnModel.getGraphicInfo(artifact.getId());
/*     */       
/* 288 */       if (artifactGraphicInfo != null) {
/*     */         
/* 290 */         if (artifactGraphicInfo.getX() + artifactGraphicInfo.getWidth() > maxX) {
/* 291 */           maxX = artifactGraphicInfo.getX() + artifactGraphicInfo.getWidth();
/*     */         }
/* 293 */         if (artifactGraphicInfo.getX() < minX) {
/* 294 */           minX = artifactGraphicInfo.getX();
/*     */         }
/*     */         
/* 297 */         if (artifactGraphicInfo.getY() + artifactGraphicInfo.getHeight() > maxY) {
/* 298 */           maxY = artifactGraphicInfo.getY() + artifactGraphicInfo.getHeight();
/*     */         }
/* 300 */         if (artifactGraphicInfo.getY() < minY) {
/* 301 */           minY = artifactGraphicInfo.getY();
/*     */         }
/*     */       } 
/*     */       
/* 305 */       List<GraphicInfo> graphicInfoList = bpmnModel.getFlowLocationGraphicInfo(artifact.getId());
/* 306 */       if (graphicInfoList != null) {
/* 307 */         for (GraphicInfo graphicInfo : graphicInfoList) {
/*     */           
/* 309 */           if (graphicInfo.getX() > maxX) {
/* 310 */             maxX = graphicInfo.getX();
/*     */           }
/* 312 */           if (graphicInfo.getX() < minX) {
/* 313 */             minX = graphicInfo.getX();
/*     */           }
/*     */           
/* 316 */           if (graphicInfo.getY() > maxY) {
/* 317 */             maxY = graphicInfo.getY();
/*     */           }
/* 319 */           if (graphicInfo.getY() < minY) {
/* 320 */             minY = graphicInfo.getY();
/*     */           }
/*     */         } 
/*     */       }
/*     */     } 
/*     */     
/* 326 */     int nrOfLanes = 0;
/* 327 */     for (Process process : bpmnModel.getProcesses()) {
/* 328 */       for (Lane l : process.getLanes()) {
/*     */         
/* 330 */         nrOfLanes++;
/*     */         
/* 332 */         GraphicInfo graphicInfo = bpmnModel.getGraphicInfo(l.getId());
/*     */         
/* 334 */         if (graphicInfo.getX() + graphicInfo.getWidth() > maxX) {
/* 335 */           maxX = graphicInfo.getX() + graphicInfo.getWidth();
/*     */         }
/* 337 */         if (graphicInfo.getX() < minX) {
/* 338 */           minX = graphicInfo.getX();
/*     */         }
/*     */         
/* 341 */         if (graphicInfo.getY() + graphicInfo.getHeight() > maxY) {
/* 342 */           maxY = graphicInfo.getY() + graphicInfo.getHeight();
/*     */         }
/* 344 */         if (graphicInfo.getY() < minY) {
/* 345 */           minY = graphicInfo.getY();
/*     */         }
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 351 */     if (flowNodes.isEmpty() && bpmnModel.getPools().isEmpty() && nrOfLanes == 0) {
/*     */       
/* 353 */       minX = 0.0D;
/* 354 */       minY = 0.0D;
/*     */     } 
/*     */     
/* 357 */     return new BpmProcessDiagramCanvas((int)maxX + 10, (int)maxY + 10, (int)minX, (int)minY, imageType, activityFontName, labelFontName, annotationFontName, customClassLoader);
/*     */   }
/*     */   
/*     */   private static ProcessEngineConfiguration processEngineConfiguration() {
/* 361 */     if (processEngineConfiguration == null) {
/* 362 */       processEngineConfiguration = (ProcessEngineConfiguration)AppUtil.getBean(ProcessEngineConfiguration.class);
/*     */     }
/* 364 */     return processEngineConfiguration;
/*     */   }
/*     */ }


/* Location:              D:\Repository\com\dstz\agilebpm\wf-act\1.5.1\wf-act-1.5.1-pg.jar!\com\dstz\bpm\act\img\BpmProcessDiagramGenerator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.0
 */