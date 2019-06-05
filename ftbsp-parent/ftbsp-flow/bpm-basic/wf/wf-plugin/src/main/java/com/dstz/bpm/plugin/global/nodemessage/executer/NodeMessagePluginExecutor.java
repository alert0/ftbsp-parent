/*     */ package com.dstz.bpm.plugin.global.nodemessage.executer;
/*     */ 
/*     */ import cn.hutool.core.collection.CollectionUtil;
/*     */ import com.dstz.base.core.util.StringUtil;
/*     */ import com.dstz.bpm.api.engine.action.cmd.BaseActionCmd;
/*     */ import com.dstz.bpm.api.engine.context.BpmContext;
/*     */ import com.dstz.bpm.api.engine.plugin.def.UserAssignRule;
/*     */ import com.dstz.bpm.engine.plugin.factory.BpmPluginSessionFactory;
/*     */ import com.dstz.bpm.engine.plugin.runtime.abstact.AbstractBpmExecutionPlugin;
/*     */ import com.dstz.bpm.engine.plugin.session.BpmExecutionPluginSession;
/*     */ import com.dstz.bpm.engine.plugin.session.BpmUserCalcPluginSession;
/*     */ import com.dstz.bpm.plugin.global.nodemessage.def.NodeMessage;
/*     */ import com.dstz.bpm.plugin.global.nodemessage.def.NodeMessagePluginDef;
/*     */ import com.dstz.bpm.plugin.node.userassign.executer.UserAssignRuleCalc;
/*     */ import com.dstz.sys.api.freemark.IFreemarkerEngine;
/*     */ import com.dstz.sys.api.groovy.IGroovyScriptEngine;
/*     */ import com.dstz.sys.api.jms.model.DefaultJmsDTO;
/*     */ import com.dstz.sys.api.jms.model.JmsDTO;
/*     */ import com.dstz.sys.api.jms.model.msg.NotifyMessage;
/*     */ import com.dstz.sys.api.jms.producer.JmsProducer;
/*     */ import com.dstz.sys.api.model.SysIdentity;
/*     */ import com.dstz.sys.util.ContextUtil;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import javax.annotation.Resource;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Component;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Component
/*     */ public abstract class NodeMessagePluginExecutor
/*     */   extends AbstractBpmExecutionPlugin<BpmExecutionPluginSession, NodeMessagePluginDef>
/*     */ {
/*     */   @Resource
/*     */   IGroovyScriptEngine n;
/*     */   @Resource
/*     */   JmsProducer o;
/*     */   @Autowired
/*     */   IFreemarkerEngine p;
/*     */   
/*     */   public Void a(BpmExecutionPluginSession pluginSession, NodeMessagePluginDef pluginDef) {
/*  50 */     List<NodeMessage> messages = pluginDef.getNodeMessageList();
/*  51 */     for (NodeMessage nodeMessage : messages) {
/*     */       
/*  53 */       if (!a(pluginSession, nodeMessage)) {
/*     */         continue;
/*     */       }
/*     */       
/*  57 */       List<JmsDTO> JmsDto = a(nodeMessage, pluginSession);
/*  58 */       this.o.sendToQueue(JmsDto);
/*  59 */       this.LOG.debug("【节点消息插件】发送消息成功！时机：{}，消息标题：{}", pluginSession.getEventType().getValue(), nodeMessage.getDesc());
/*     */     } 
/*     */     
/*  62 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private List<JmsDTO> a(NodeMessage nodeMessage, BpmExecutionPluginSession session) {
/*     */     List<SysIdentity> userList = null;
/*  72 */     String[] msgType = nodeMessage.getMsgType().split(",");
/*     */     
/*  74 */     String htmlTemplate = nodeMessage.getHtmlTemplate();
/*  75 */     String textTemplate = nodeMessage.getTextTemplate();
/*     */     
/*     */     try {
/*  78 */       if (StringUtil.isNotEmpty(htmlTemplate)) {
/*  79 */         htmlTemplate = htmlTemplate.replaceAll("&lt;", "<").replaceAll("&gt;", ">");
/*     */         
/*  81 */         htmlTemplate = this.p.parseByString(htmlTemplate, session);
/*     */       } 
/*  83 */       if (StringUtil.isNotEmpty(textTemplate)) {
/*  84 */         textTemplate = this.p.parseByString(textTemplate, session);
/*     */       }
/*     */     }
/*  87 */     catch (Exception e) {
/*  88 */       this.LOG.error("htmlTemplate:{};textTempalte:{}", htmlTemplate, textTemplate);
/*  89 */       this.LOG.error("instId[{}]消息发送插件解析消息模板失败，可能原因为:{}", new Object[] { session.getBpmInstance().getId(), ((Throwable) userList).getMessage(), userList });
/*     */       
/*  91 */       ((Throwable) userList).printStackTrace();
/*     */     } 
/*     */ 
/*     */     
/*  95 */     if (CollectionUtil.isEmpty(nodeMessage.getUserRules())) {
/*  96 */       BaseActionCmd cmd = (BaseActionCmd)BpmContext.getActionModel();
/*  97 */       userList = cmd.getBpmIdentity(cmd.getNodeId());
/*     */     } else {
/*  99 */       userList = a(session, nodeMessage.getUserRules());
/*     */     } 
/*     */     
/* 102 */     if (CollectionUtil.isEmpty(userList)) {
/* 103 */       this.LOG.debug("【节点消息插件】没有需要发送的消息！原因：接收消息人员为空。 节点：{}，时机：{}，消息标题：{}", new Object[] { getActivitiId(session), session.getEventType().getValue(), nodeMessage.getDesc() });
/* 104 */       return Collections.emptyList();
/*     */     } 
/*     */ 
/*     */     
/* 108 */     List<JmsDTO> jmsDto = new ArrayList<JmsDTO>();
/*     */     
/* 110 */     for (String type : msgType) {
/* 111 */       NotifyMessage message = new NotifyMessage(nodeMessage.getDesc(), htmlTemplate, ContextUtil.getCurrentUser(), userList);
/* 112 */       jmsDto.add(new DefaultJmsDTO(type, message));
/*     */     } 
/*     */     
/* 115 */     return jmsDto;
/*     */   }
/*     */ 
/*     */   
/*     */   private List<SysIdentity> a(BpmExecutionPluginSession pluginSession, List<UserAssignRule> ruleList) {
/* 120 */     BpmUserCalcPluginSession calcSession = BpmPluginSessionFactory.buildBpmUserCalcPluginSession(pluginSession);
/* 121 */     return UserAssignRuleCalc.a(calcSession, ruleList, Boolean.valueOf(false));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean a(BpmExecutionPluginSession session, NodeMessage nodeMessage) {
/* 133 */     if (StringUtil.isNotEmpty(nodeMessage.getEvent()) && 
/* 134 */       !nodeMessage.getEvent().equals(session.getEventType().getKey())) {
/* 135 */       return false;
/*     */     }
/*     */     
/* 138 */     if (StringUtil.isNotEmpty(nodeMessage.getNodeId()) && 
/* 139 */       !nodeMessage.getNodeId().equals(getActivitiId(session))) {
/* 140 */       return false;
/*     */     }
/*     */     
/* 143 */     if (StringUtil.isNotEmpty(nodeMessage.getCondition())) {
/* 144 */       Boolean support = Boolean.valueOf(this.n.executeBoolean(nodeMessage.getCondition(), session));
/* 145 */       if (!support.booleanValue()) return false;
/*     */     
/*     */     } 
/* 148 */     return true;
/*     */   }
/*     */ }


/* Location:              D:\Repository\com\dstz\agilebpm\wf-plugin\1.5.1\wf-plugin-1.5.1-pg.jar!\com\dstz\bpm\plugin\global\nodemessage\executer\NodeMessagePluginExecutor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.0
 */