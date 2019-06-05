/*    */ package com.dstz.bpm.plugin.usercalc.samenode.executer;
/*    */ 
/*    */ import cn.hutool.core.collection.CollectionUtil;
/*    */ import com.dstz.bpm.api.engine.plugin.def.BpmUserCalcPluginDef;
/*    */ import com.dstz.bpm.api.model.task.IBpmTaskOpinion;
/*    */ import com.dstz.bpm.core.manager.BpmTaskOpinionManager;
/*    */ import com.dstz.bpm.core.model.BpmTaskOpinion;
/*    */ import com.dstz.bpm.engine.model.BpmIdentity;
/*    */ import com.dstz.bpm.engine.plugin.runtime.abstact.AbstractUserCalcPlugin;
/*    */ import com.dstz.bpm.engine.plugin.session.BpmUserCalcPluginSession;
/*    */ import com.dstz.bpm.plugin.usercalc.samenode.def.SameNodePluginDef;
/*    */ import com.dstz.sys.api.model.SysIdentity;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import javax.annotation.Resource;
/*    */ import org.springframework.stereotype.Component;
/*    */ 
/*    */ 
/*    */ @Component
/*    */ public abstract class SameNodePluginExecutor
/*    */   extends AbstractUserCalcPlugin<SameNodePluginDef>
/*    */ {
/*    */   @Resource
/*    */   BpmTaskOpinionManager y;
/*    */   
/*    */   public List<SysIdentity> a(BpmUserCalcPluginSession pluginSession, SameNodePluginDef sameNodeDef) {
/* 27 */     List<SysIdentity> bpmIdentities = new ArrayList<SysIdentity>();
/*    */     
/* 29 */     List<BpmTaskOpinion> taskOpinionList = this.y.getByInstAndNode(pluginSession.getBpmTask().getInstId(), sameNodeDef.getNodeId());
/* 30 */     if (CollectionUtil.isEmpty(taskOpinionList)) return bpmIdentities;
/*    */     
/* 32 */     IBpmTaskOpinion taskOpinion = (IBpmTaskOpinion)taskOpinionList.get(taskOpinionList.size() - 1);
/*    */     
/* 34 */     BpmIdentity bpmIdentity1 = new BpmIdentity(taskOpinion.getApprover(), taskOpinion.getApproverName(), "user");
/* 35 */     bpmIdentities.add(bpmIdentity1);
/*    */     
/* 37 */     return bpmIdentities;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 42 */   public boolean supportPreView() { return false; }
/*    */ }


/* Location:              D:\Repository\com\dstz\agilebpm\wf-plugin\1.5.1\wf-plugin-1.5.1-pg.jar!\com\dstz\bpm\plugi\\usercalc\samenode\executer\SameNodePluginExecutor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.0
 */