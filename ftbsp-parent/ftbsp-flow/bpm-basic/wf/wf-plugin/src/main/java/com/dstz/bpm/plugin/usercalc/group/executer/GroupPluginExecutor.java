/*    */ package com.dstz.bpm.plugin.usercalc.group.executer;
/*    */ 
/*    */ import com.dstz.base.core.util.StringUtil;
/*    */ import com.dstz.bpm.api.engine.plugin.def.BpmUserCalcPluginDef;
/*    */ import com.dstz.bpm.engine.model.BpmIdentity;
/*    */ import com.dstz.bpm.engine.plugin.runtime.abstact.AbstractUserCalcPlugin;
/*    */ import com.dstz.bpm.engine.plugin.session.BpmUserCalcPluginSession;
/*    */ import com.dstz.bpm.plugin.usercalc.group.def.GroupPluginDef;
/*    */ import com.dstz.org.api.model.IGroup;
/*    */ import com.dstz.org.api.service.GroupService;
/*    */ import com.dstz.sys.api.model.SysIdentity;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import javax.annotation.Resource;
/*    */ import org.springframework.stereotype.Component;
/*    */ 
/*    */ 
/*    */ @Component
/*    */ public abstract class GroupPluginExecutor
/*    */   extends AbstractUserCalcPlugin<GroupPluginDef>
/*    */ {
/*    */   @Resource
/*    */   GroupService A;
/*    */   
/*    */   public List<SysIdentity> a(BpmUserCalcPluginSession pluginSession, GroupPluginDef def) {
/* 26 */     if (StringUtil.isEmpty(def.getGroupKey())) return null; 
/* 27 */     String groupType = def.getType();
/*    */     
/* 29 */     List<SysIdentity> identityList = new ArrayList<SysIdentity>();
/* 30 */     for (String key : def.getGroupKey().split(",")) {
/* 31 */       if (!StringUtil.isEmpty(key)) {
/* 32 */         IGroup group = this.A.getByCode(groupType, key);
/* 33 */         if (group != null)
/*    */         {
/* 35 */           identityList.add(new BpmIdentity(group.getGroupId(), group.getGroupName(), group.getGroupType())); } 
/*    */       } 
/*    */     } 
/* 38 */     return identityList;
/*    */   }
/*    */ }


/* Location:              D:\Repository\com\dstz\agilebpm\wf-plugin\1.5.1\wf-plugin-1.5.1-pg.jar!\com\dstz\bpm\plugi\\usercalc\group\executer\GroupPluginExecutor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.0
 */