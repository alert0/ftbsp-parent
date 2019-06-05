/*    */ package com.dstz.bpm.plugin.usercalc.approver.executer;
/*    */ 
/*    */ import com.dstz.base.core.util.StringUtil;
/*    */ import com.dstz.bpm.api.engine.plugin.def.BpmUserCalcPluginDef;
/*    */ import com.dstz.bpm.api.model.task.IBpmTaskOpinion;
/*    */ import com.dstz.bpm.core.manager.BpmTaskOpinionManager;
/*    */ import com.dstz.bpm.core.model.BpmTaskOpinion;
/*    */ import com.dstz.bpm.engine.model.BpmIdentity;
/*    */ import com.dstz.bpm.engine.plugin.runtime.abstact.AbstractUserCalcPlugin;
/*    */ import com.dstz.bpm.engine.plugin.session.BpmUserCalcPluginSession;
/*    */ import com.dstz.bpm.plugin.usercalc.approver.def.ApproverPluginDef;
/*    */ import com.dstz.sys.api.model.SysIdentity;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import javax.annotation.Resource;
/*    */ import org.springframework.stereotype.Component;
/*    */ 
/*    */ 
/*    */ 
/*    */ @Component
/*    */ public abstract class ApproverPluginExecutor
/*    */   extends AbstractUserCalcPlugin<ApproverPluginDef>
/*    */ {
/*    */   @Resource
/*    */   BpmTaskOpinionManager y;
/*    */   
/*    */   public List<SysIdentity> a(BpmUserCalcPluginSession pluginSession, ApproverPluginDef pluginDef) {
/* 28 */     List<SysIdentity> bpmIdentities = new ArrayList<SysIdentity>();
/*    */     
/* 30 */     List<BpmTaskOpinion> taskOpinionList = this.y.getByInstId(pluginSession.getBpmTask().getInstId());
/*    */     
/* 32 */     for (IBpmTaskOpinion taskOpinion : taskOpinionList) {
/* 33 */       if (StringUtil.isEmpty(taskOpinion.getApprover()))
/*    */         continue; 
/* 35 */       BpmIdentity bpmIdentity1 = new BpmIdentity(taskOpinion.getApprover(), taskOpinion.getApproverName(), "user");
/* 36 */       bpmIdentities.add(bpmIdentity1);
/*    */     } 
/*    */     
/* 39 */     return bpmIdentities;
/*    */   }
/*    */ }


/* Location:              D:\Repository\com\dstz\agilebpm\wf-plugin\1.5.1\wf-plugin-1.5.1-pg.jar!\com\dstz\bpm\plugi\\usercalc\approver\executer\ApproverPluginExecutor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.0
 */