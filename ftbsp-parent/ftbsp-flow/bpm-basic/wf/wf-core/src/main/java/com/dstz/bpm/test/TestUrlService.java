/*    */ package com.dstz.bpm.test;
/*    */ 
/*    */ import com.alibaba.fastjson.JSONObject;
/*    */ import com.dstz.bpm.api.engine.action.cmd.ActionCmd;
/*    */ import org.springframework.stereotype.Component;
/*    */ 
/*    */ 
/*    */ @Component
/*    */ public class TestUrlService
/*    */ {
/*    */   public void a(ActionCmd cmd) {
/* 12 */     JSONObject data = cmd.getBusData();
/*    */     
/* 14 */     System.err.println(data.toJSONString());
/* 15 */     cmd.setBusinessKey("123");
/*    */   }
/*    */ }


/* Location:              D:\Repository\com\dstz\agilebpm\wf-core\1.5.1\wf-core-1.5.1-pg.jar!\com\dstz\bpm\test\TestUrlService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.0
 */