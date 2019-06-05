/*    */ package com.dstz.bpm.engine.action.helper;

/*    */
/*    */ import com.alibaba.fastjson.JSON;
/*    */ import com.dstz.base.api.exception.BusinessException;
/*    */ import com.dstz.base.core.util.AppUtil;
/*    */ import com.dstz.bpm.api.engine.action.cmd.FlowRequestParam;
/*    */ import com.dstz.bpm.api.engine.action.cmd.InstanceActionCmd;
/*    */ import com.dstz.bpm.engine.action.cmd.DefaultInstanceActionCmd;
/*    */ import com.dstz.org.api.model.IUser;
/*    */ import com.dstz.sys.util.ContextUtil;
/*    */ import java.util.concurrent.CountDownLatch;
/*    */ import java.util.concurrent.ExecutorService;
/*    */ import java.util.concurrent.Executors;
/*    */ import org.slf4j.Logger;
/*    */ import org.slf4j.LoggerFactory;
/*    */ import org.springframework.transaction.support.TransactionSynchronizationManager;

/*    */
/*    */
/*    */
/*    */ public class NewThreadActionUtil
/*    */ {
	/* 22 */ protected static Logger LOG = LoggerFactory.getLogger(NewThreadActionUtil.class);

	/*    */
	/*    */
	/*    */
	/*    */
	/*    */
	/*    */
	/*    */ public static void a(InstanceActionCmd newFlowCmd, IUser startUser) {
		/* 30 */ StartNewFlow triggerNewFlow = (StartNewFlow) AppUtil.getBean(StartNewFlow.class);
		/* 31 */ ExecutorService executor = Executors.newCachedThreadPool();
		/*    */
		/*    */ try {
			/* 34 */ CountDownLatch latch = new CountDownLatch(1);//true
			// true
			/* 35 */ triggerNewFlow.setLatch(latch);
			/* 36 */ triggerNewFlow.setUser(startUser);
			/* 37 */ triggerNewFlow.setInstanceCmd(newFlowCmd);
			/* 38 */ triggerNewFlow.setTransactionResource(TransactionSynchronizationManager.getResourceMap());
			/*    */
			/* 40 */ executor.execute(triggerNewFlow);
			/*    */
			/* 42 */ latch.await();
			/*    */
			/* 44 */ if (triggerNewFlow.getException() != null) {
				/* 45 */ throw new RuntimeException(triggerNewFlow.getException());
				/*    */ }
			/* 47 */ } catch (Exception e) {
			/* 48 */ e.printStackTrace();
			/* 49 */ throw new BusinessException("触发新流程失败 ！ ：" + e.getMessage());
			/*    */ } finally {
			/* 51 */ executor.shutdown();
			/*    */ }
		/*    */ }

	/*    */
	/*    */
	/*    */
	/*    */
	/*    */ public static void c() {
/* 59 */     String jsonData = "{\"Demo\":{\"bmId\":\"20000000280001\",\"bm\":\"科技部\",\"zd1\":\"JS初始化\",\"DemoSubList\":[{\"ms\":\"请开启控制台，或者查看表单源码，来查看我是如何初始化的\"}],\"mz\":\"测试新线程处理任务！\"}}";
/* 60 */    
FlowRequestParam param = new FlowRequestParam("404120998984024065", "start", JSON.parseObject(jsonData));
/* 61 */     DefaultInstanceActionCmd defaultInstanceActionCmd = new DefaultInstanceActionCmd(param);
/*    */     
/* 63 */     a(defaultInstanceActionCmd, ContextUtil.getCurrentUser());
/* 64 */     LOG.debug("启动成功！");
/*    */   }
	/*    */ }

/*
 * Location:
 * D:\Repository\com\dstz\agilebpm\wf-core\1.5.1\wf-core-1.5.1-pg.jar!\com\dstz\
 * bpm\engine\action\helper\NewThreadActionUtil.class Java compiler version: 8
 * (52.0) JD-Core Version: 1.0.0
 */