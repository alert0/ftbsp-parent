/*    */ package com.dstz.bpm.plugin.global.datalog.executer;
/*    */ 
/*    */ import com.dstz.bpm.api.service.BpmProcessDefService;
/*    */ import com.dstz.bpm.engine.plugin.runtime.abstact.AbstractBpmExecutionPlugin;
/*    */ import com.dstz.bpm.engine.plugin.session.BpmExecutionPluginSession;
/*    */ import com.dstz.bpm.plugin.core.manager.BpmSubmitDataLogManager;
/*    */ import com.dstz.bpm.plugin.core.model.BpmSubmitDataLog;
/*    */ import com.dstz.bpm.plugin.global.datalog.def.DataLogPluginDef;
/*    */ import com.dstz.bus.api.service.IBusinessDataService;
/*    */ import javax.annotation.Resource;
/*    */ import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
/*    */ import org.springframework.stereotype.Component;
/*    */ 
/*    */ @Component
/*    */ public abstract class DataLogPluginExecutor extends AbstractBpmExecutionPlugin<BpmExecutionPluginSession, DataLogPluginDef> {
/*    */   @Resource
/*    */   BpmProcessDefService d;
/*    */   @Resource
/*    */   private ThreadPoolTaskExecutor e;
/*    */   @Resource
/*    */   private IBusinessDataService f;
/*    */   @Resource
/*    */   BpmSubmitDataLogManager g;
/*    */   
/*    */   public void a(BpmExecutionPluginSession pluginSession, DataLogPluginDef pluginDef) {
/*    */     // Byte code:
/*    */     //   0: invokestatic getActionModel : ()Lcom/dstz/bpm/api/engine/action/cmd/ActionCmd;
/*    */     //   3: checkcast com/dstz/bpm/api/engine/action/cmd/BaseActionCmd
/*    */     //   6: astore_3
/*    */     //   7: aload_3
/*    */     //   8: invokevirtual getBusData : ()Lcom/alibaba/fastjson/JSONObject;
/*    */     //   11: astore #4
/*    */     //   13: aload #4
/*    */     //   15: ifnull -> 26
/*    */     //   18: aload #4
/*    */     //   20: invokevirtual isEmpty : ()Z
/*    */     //   23: ifeq -> 28
/*    */     //   26: aconst_null
/*    */     //   27: areturn
/*    */     //   28: aload_1
/*    */     //   29: invokeinterface getBpmInstance : ()Lcom/dstz/bpm/api/model/inst/IBpmInstance;
/*    */     //   34: invokeinterface getDefId : ()Ljava/lang/String;
/*    */     //   39: astore #5
/*    */     //   41: aload_0
/*    */     //   42: getfield d : Lcom/dstz/bpm/api/service/BpmProcessDefService;
/*    */     //   45: aload #5
/*    */     //   47: invokeinterface getBpmProcessDef : (Ljava/lang/String;)Lcom/dstz/bpm/api/model/def/BpmProcessDef;
/*    */     //   52: checkcast com/dstz/bpm/engine/model/DefaultBpmProcessDef
/*    */     //   55: astore #6
/*    */     //   57: aload #6
/*    */     //   59: invokevirtual getExtProperties : ()Lcom/dstz/bpm/api/model/def/BpmDefProperties;
/*    */     //   62: invokevirtual isLogSubmitData : ()Z
/*    */     //   65: ifne -> 70
/*    */     //   68: aconst_null
/*    */     //   69: areturn
/*    */     //   70: new com/dstz/bpm/plugin/core/model/BpmSubmitDataLog
/*    */     //   73: dup
/*    */     //   74: invokespecial <init> : ()V
/*    */     //   77: astore #7
/*    */     //   79: aload #7
/*    */     //   81: aload #4
/*    */     //   83: invokevirtual toJSONString : ()Ljava/lang/String;
/*    */     //   86: invokevirtual setData : (Ljava/lang/String;)V
/*    */     //   89: aload #7
/*    */     //   91: aload_3
/*    */     //   92: invokevirtual getDestination : ()Ljava/lang/String;
/*    */     //   95: invokevirtual setDestination : (Ljava/lang/String;)V
/*    */     //   98: aload_3
/*    */     //   99: invokevirtual getExtendConf : ()Lcom/alibaba/fastjson/JSONObject;
/*    */     //   102: ifnull -> 117
/*    */     //   105: aload #7
/*    */     //   107: aload_3
/*    */     //   108: invokevirtual getExtendConf : ()Lcom/alibaba/fastjson/JSONObject;
/*    */     //   111: invokevirtual toJSONString : ()Ljava/lang/String;
/*    */     //   114: invokevirtual setExtendConf : (Ljava/lang/String;)V
/*    */     //   117: aload_3
/*    */     //   118: instanceof com/dstz/bpm/engine/action/cmd/DefualtTaskActionCmd
/*    */     //   121: ifeq -> 141
/*    */     //   124: aload #7
/*    */     //   126: aload_3
/*    */     //   127: checkcast com/dstz/bpm/engine/action/cmd/DefualtTaskActionCmd
/*    */     //   130: invokevirtual getBpmTask : ()Lcom/dstz/bpm/api/model/task/IBpmTask;
/*    */     //   133: invokeinterface getId : ()Ljava/lang/String;
/*    */     //   138: invokevirtual setTaskId : (Ljava/lang/String;)V
/*    */     //   141: aload #7
/*    */     //   143: aload_3
/*    */     //   144: invokevirtual getInstanceId : ()Ljava/lang/String;
/*    */     //   147: invokevirtual setInstId : (Ljava/lang/String;)V
/*    */     //   150: aload_0
/*    */     //   151: getfield e : Lorg/springframework/scheduling/concurrent/ThreadPoolTaskExecutor;
/*    */     //   154: aload_0
/*    */     //   155: aload #7
/*    */     //   157: <illegal opcode> run : (Lcom/dstz/bpm/plugin/global/datalog/executer/DataLogPluginExecutor;Lcom/dstz/bpm/plugin/core/model/BpmSubmitDataLog;)Ljava/lang/Runnable;
/*    */     //   162: invokevirtual execute : (Ljava/lang/Runnable;)V
/*    */     //   165: aload_0
/*    */     //   166: getfield LOG : Lorg/slf4j/Logger;
/*    */     //   169: ldc '记录流程提交业务数据 '
/*    */     //   171: invokeinterface debug : (Ljava/lang/String;)V
/*    */     //   176: aconst_null
/*    */     //   177: areturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #38	-> 0
/*    */     //   #40	-> 7
/*    */     //   #42	-> 13
/*    */     //   #45	-> 28
/*    */     //   #47	-> 41
/*    */     //   #48	-> 57
/*    */     //   #50	-> 70
/*    */     //   #51	-> 79
/*    */     //   #52	-> 89
/*    */     //   #53	-> 98
/*    */     //   #54	-> 105
/*    */     //   #57	-> 117
/*    */     //   #58	-> 124
/*    */     //   #60	-> 141
/*    */     //   #62	-> 150
/*    */     //   #64	-> 165
/*    */     //   #65	-> 176
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	178	0	this	Lcom/dstz/bpm/plugin/global/datalog/executer/DataLogPluginExecutor;
/*    */     //   0	178	1	pluginSession	Lcom/dstz/bpm/engine/plugin/session/BpmExecutionPluginSession;
/*    */     //   0	178	2	pluginDef	Lcom/dstz/bpm/plugin/global/datalog/def/DataLogPluginDef;
/*    */     //   7	171	3	cmd	Lcom/dstz/bpm/api/engine/action/cmd/BaseActionCmd;
/*    */     //   13	165	4	businessData	Lcom/alibaba/fastjson/JSONObject;
/*    */     //   41	137	5	defId	Ljava/lang/String;
/*    */     //   57	121	6	processDef	Lcom/dstz/bpm/engine/model/DefaultBpmProcessDef;
/*    */     //   79	99	7	submitDataLog	Lcom/dstz/bpm/plugin/core/model/BpmSubmitDataLog;
/*    */   }
/*    */ }


/* Location:              D:\Repository\com\dstz\agilebpm\wf-plugin\1.5.1\wf-plugin-1.5.1-pg.jar!\com\dstz\bpm\plugin\global\datalog\executer\DataLogPluginExecutor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.0
 */