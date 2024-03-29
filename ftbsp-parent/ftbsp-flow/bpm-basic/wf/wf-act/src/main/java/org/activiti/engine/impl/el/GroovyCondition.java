/*    */ package org.activiti.engine.impl.el;
/*    */ 
/*    */ import com.dstz.base.api.exception.BusinessException;
/*    */ import com.dstz.base.core.util.AppUtil;
/*    */ import com.dstz.bpm.api.engine.action.cmd.ActionCmd;
/*    */ import com.dstz.bpm.api.engine.action.cmd.BaseActionCmd;
/*    */ import com.dstz.bpm.api.engine.context.BpmContext;
/*    */ import com.dstz.bpm.api.exception.BpmStatusCode;
/*    */ import com.dstz.sys.api.groovy.IGroovyScriptEngine;
/*    */ import java.util.Map;
/*    */ import org.activiti.engine.delegate.DelegateExecution;
/*    */ import org.activiti.engine.impl.Condition;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class GroovyCondition
/*    */   implements Condition
/*    */ {
/*    */   private static final long serialVersionUID = -5577703954744892854L;
/* 30 */   private String script = "";
/*    */ 
/*    */   
/* 33 */   public GroovyCondition(String condition) { this.script = condition; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean evaluate(String arg0, DelegateExecution execution) {
/* 39 */     Map<String, Object> maps = execution.getVariables();
/*    */     
/* 41 */     maps.put("variableScope", execution);
/* 42 */     ActionCmd cmd = BpmContext.getActionModel();
/* 43 */     maps.putAll(cmd.getBizDataMap());
/*    */     
/* 45 */     BaseActionCmd submitAction = (BaseActionCmd)BpmContext.submitActionModel();
/* 46 */     maps.put("submitActionName", submitAction.getActionType().getKey());
/* 47 */     maps.put("bpmInstance", submitAction.getBpmInstance());
/*    */     
/* 49 */     IGroovyScriptEngine engine = (IGroovyScriptEngine)AppUtil.getBean(IGroovyScriptEngine.class);
/*    */     try {
/* 51 */       return engine.executeBoolean(this.script, maps);
/* 52 */     } catch (Exception e) {
/* 53 */       e.printStackTrace();
/* 54 */       StringBuilder message = new StringBuilder("条件脚本解析异常！请联系管理员。");
/* 55 */       message.append("\n节点：" + execution.getCurrentActivityName() + "——" + execution.getCurrentActivityId());
/* 56 */       message.append("\n脚本：" + this.script);
/* 57 */       message.append("\n异常：" + e.getMessage());
/* 58 */       message.append("\n\n流程变量：" + maps.toString());
/* 59 */       throw new BusinessException(message.toString(), BpmStatusCode.GATEWAY_ERROR, e);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\Repository\com\dstz\agilebpm\wf-act\1.5.1\wf-act-1.5.1-pg.jar!\org\activiti\engine\impl\el\GroovyCondition.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.0
 */