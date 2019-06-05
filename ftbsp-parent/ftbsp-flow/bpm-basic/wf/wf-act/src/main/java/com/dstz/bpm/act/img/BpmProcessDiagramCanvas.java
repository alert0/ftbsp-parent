/*     */ package com.dstz.bpm.act.img;
/*     */ 
/*     */ import com.dstz.base.core.util.ThreadMapUtil;
/*     */ import java.awt.Color;
/*     */ import java.awt.Paint;
/*     */ import java.awt.Stroke;
/*     */ import java.awt.geom.Line2D;
/*     */ import java.awt.geom.RoundRectangle2D;
/*     */ import java.util.Map;
/*     */ import org.activiti.bpmn.model.AssociationDirection;
/*     */ import org.activiti.bpmn.model.FlowNode;
/*     */ import org.activiti.bpmn.model.GraphicInfo;
/*     */ import org.activiti.image.impl.DefaultProcessDiagramCanvas;
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
/*     */ public class BpmProcessDiagramCanvas
/*     */   extends DefaultProcessDiagramCanvas
/*     */ {
/*     */   public BpmProcessDiagramCanvas(int width, int height, int minX, int minY, String imageType, String activityFontName, String labelFontName, String annotationFontName, ClassLoader customClassLoader) {
/*  29 */     super(width, height, minX, minY, imageType, activityFontName, labelFontName, annotationFontName, customClassLoader);
/*  30 */     LABEL_COLOR = Color.BLACK;
/*  31 */     LABEL_FONT = null;
/*     */   }
/*     */   
/*     */   public void drawHighLight(int x, int y, int width, int height, Paint paint) {
/*  35 */     Paint originalPaint = this.g.getPaint();
/*  36 */     Stroke originalStroke = this.g.getStroke();
/*     */     
/*  38 */     this.g.setPaint(paint);
/*  39 */     this.g.setStroke(THICK_TASK_BORDER_STROKE);
/*     */     
/*  41 */     RoundRectangle2D rect = new RoundRectangle2D.Double(x, y, width, height, 20.0D, 20.0D);
/*  42 */     this.g.draw(rect);
/*     */     
/*  44 */     this.g.setPaint(originalPaint);
/*  45 */     this.g.setStroke(originalStroke);
/*     */   }
/*     */   
/*     */   public void drawSequenceflow(int[] xPoints, int[] yPoints, boolean drawConditionalIndicator, boolean isDefault, boolean highLighted, Paint paint, double scaleFactor) {
/*  49 */     String connectionType = "sequenceFlow";
/*  50 */     AssociationDirection associationDirection = AssociationDirection.ONE;
/*  51 */     boolean conditional = drawConditionalIndicator;
/*     */     
/*  53 */     Paint originalPaint = this.g.getPaint();
/*  54 */     Stroke originalStroke = this.g.getStroke();
/*     */     
/*  56 */     this.g.setPaint(CONNECTION_COLOR);
/*  57 */     if (connectionType.equals("association")) {
/*  58 */       this.g.setStroke(ASSOCIATION_STROKE);
/*  59 */     } else if (highLighted) {
/*  60 */       this.g.setPaint(paint);
/*  61 */       this.g.setStroke(HIGHLIGHT_FLOW_STROKE);
/*     */     } 
/*     */     
/*  64 */     for (int i = 1; i < xPoints.length; i++) {
/*  65 */       Integer sourceX = Integer.valueOf(xPoints[i - 1]);
/*  66 */       Integer sourceY = Integer.valueOf(yPoints[i - 1]);
/*  67 */       Integer targetX = Integer.valueOf(xPoints[i]);
/*  68 */       Integer targetY = Integer.valueOf(yPoints[i]);
/*  69 */       Line2D.Double line = new Line2D.Double(sourceX.intValue(), sourceY.intValue(), targetX.intValue(), targetY.intValue());
/*  70 */       this.g.draw(line);
/*     */     } 
/*     */     
/*  73 */     if (isDefault) {
/*  74 */       Line2D.Double line = new Line2D.Double(xPoints[0], yPoints[0], xPoints[1], yPoints[1]);
/*  75 */       drawDefaultSequenceFlowIndicator(line, scaleFactor);
/*     */     } 
/*     */     
/*  78 */     if (conditional) {
/*  79 */       Line2D.Double line = new Line2D.Double(xPoints[0], yPoints[0], xPoints[1], yPoints[1]);
/*  80 */       drawConditionalSequenceFlowIndicator(line, scaleFactor);
/*     */     } 
/*     */     
/*  83 */     if (associationDirection.equals(AssociationDirection.ONE) || associationDirection.equals(AssociationDirection.BOTH)) {
/*  84 */       Line2D.Double line = new Line2D.Double(xPoints[xPoints.length - 2], yPoints[xPoints.length - 2], xPoints[xPoints.length - 1], yPoints[xPoints.length - 1]);
/*  85 */       drawArrowHead(line, scaleFactor);
/*     */     } 
/*  87 */     if (associationDirection.equals(AssociationDirection.BOTH)) {
/*  88 */       Line2D.Double line = new Line2D.Double(xPoints[1], yPoints[1], xPoints[0], yPoints[0]);
/*  89 */       drawArrowHead(line, scaleFactor);
/*     */     } 
/*  91 */     this.g.setPaint(originalPaint);
/*  92 */     this.g.setStroke(originalStroke);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void drawExclusiveGateway(GraphicInfo graphicInfo, double scaleFactor) {
/* 100 */     Paint paint = this.g.getPaint();
/* 101 */     Map<String, Paint> gateMap = (Map)ThreadMapUtil.get("DefaultInstHistImgService_gateMap");
/* 102 */     FlowNode flowNode = (FlowNode)ThreadMapUtil.get("BpmProcessDiagramGenerator_flowNode");
/* 103 */     this.g.setPaint((Paint)gateMap.getOrDefault(flowNode.getId(), paint));
/* 104 */     super.drawExclusiveGateway(graphicInfo, scaleFactor);
/* 105 */     this.g.setPaint(paint);
/*     */   }
/*     */ }


/* Location:              D:\Repository\com\dstz\agilebpm\wf-act\1.5.1\wf-act-1.5.1-pg.jar!\com\dstz\bpm\act\img\BpmProcessDiagramCanvas.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.0
 */