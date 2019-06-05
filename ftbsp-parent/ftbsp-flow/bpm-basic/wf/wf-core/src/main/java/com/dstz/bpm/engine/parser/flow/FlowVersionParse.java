/*     */ package com.dstz.bpm.engine.parser.flow;
/*     */ 
/*     */ import cn.hutool.core.date.DateTime;
/*     */ import cn.hutool.core.date.DateUtil;
/*     */ import com.alibaba.fastjson.JSONObject;
/*     */ import com.dstz.base.core.encrypt.EncryptUtil;
/*     */ import com.dstz.base.core.util.JsonUtil;
/*     */ import com.dstz.base.core.util.StringUtil;
/*     */ import com.dstz.bpm.api.engine.plugin.def.BpmDef;
/*     */ import com.dstz.bpm.engine.model.DefaultBpmProcessDef;
/*     */ import com.dstz.bpm.engine.model.DefaultBpmVariableDef;
/*     */ import com.dstz.sys.api.groovy.IGroovyScriptEngine;
/*     */ import java.util.Date;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import javax.annotation.Resource;
/*     */ import org.springframework.stereotype.Component;
/*     */ 
/*     */ @Component
/*     */ public abstract class FlowVersionParse
/*     */   extends AbsFlowParse<DefaultBpmVariableDef>
/*     */ {
/*     */   @Resource
/*     */   IGroovyScriptEngine bP;
/*     */   private static boolean bQ = false;
/*  26 */   private static String bR = "b";
/*     */   
/*     */   public void b(DefaultBpmProcessDef def, JSONObject flowConf) {
/*  29 */     if (bQ) {
/*  30 */       flowConf.put("v", bR);
/*  31 */       if (bR.equals("b")) {
/*  32 */         a(flowConf);
/*     */       }
/*     */       return;
/*     */     } 
/*  36 */     bR = e(JsonUtil.getString(flowConf, "version", ""));
/*  37 */     f(bR);
/*     */     
/*  39 */     if (bR.equals("b")) {
/*  40 */       a(flowConf);
/*     */     }
/*     */     
/*  43 */     bQ = true;
/*  44 */     flowConf.put("v", bR);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  50 */   String bS = "import com.dstz.base.core.util.AppUtil; "
/*  51 */     .concat("return AppUtil.getBean(\"sysPropertiesManager\")")
/*  52 */     .concat(".getByAlias(\"s\").concat(\".\").concat(\"k\");");
/*  53 */   String bT = "import com.dstz.base.cor".concat("e.enc").concat("rypt.Encr").concat("yptUtil; ")
/*  54 */     .concat("return Encr").concat("ypt").concat("Util.aes").concat("Decry").concat("pt(theKey,").concat("\"Not afraid of infringement you will be free\")");
/*     */   private String e(String key) {
/*     */     try {
/*  57 */       Map<String, Object> param = new HashMap<String, Object>();//(true, 1.0F)
/*  58 */       String theKey = this.bP.executeString(this.bS, null).replaceFirst(key, "");
/*  59 */       param.put("theKey", theKey);
/*  60 */       String str = this.bP.executeString(this.bT, param);
/*  61 */       if (StringUtil.isEmpty(str)) {
/*  62 */         return bR;
/*     */       }
/*  64 */       String[] msg = str.split("_");
/*  65 */       if (msg.length != 3) {
/*  66 */         return bR;
/*     */       }
/*  68 */       DateTime dateTime = DateUtil.parse(msg[2]);
/*  69 */       if (dateTime.before(new Date())) {
/*  70 */         return bR;
/*     */       }
/*     */       
/*  73 */       return msg[0];
/*     */     }
/*  75 */     catch (Exception e) {
/*  76 */       return bR;
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void main(String[] args) throws Exception {
/*  81 */     String code = "senior_agileBpm敏捷工作流s_2019-05-13";
/*  82 */     System.out.println(EncryptUtil.aesEncrypt(code, "Not afraid of infringement you will be free"));
/*     */     
/*  84 */     String code1 = "37dbccc65c9dc4599e88dff4670d5b2ad2cc1d329abd0d834c7450d672ceebd2b11d547ddd65ae206d57ab3f19d70413";
/*  85 */     System.out.println(EncryptUtil.aesDecrypt("37dbccc65c9dc4599e88dff4670d5b2ad2cc1d329abd0d834c7450d672ceebd2b11d547ddd65ae206d57ab3f19d70413", "Not afraid of infringement you will be free"));
/*     */ 
/*     */     
/*  88 */     System.out.println(EncryptUtil.encrypt(code1));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void a(JSONObject flowConf) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  99 */  
private void f(String string) {
	(new Thread(new Runnable() {//this
		
		@Override
		public void run() {}
		
	})).run();
}

/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 110 */   public String getKey() { return null; }
/*     */   
/*     */   public void a(DefaultBpmProcessDef bpmdef, Object object) {}
/*     */ }


/* Location:              D:\Repository\com\dstz\agilebpm\wf-core\1.5.1\wf-core-1.5.1-pg.jar!\com\dstz\bpm\engine\parser\flow\FlowVersionParse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.0
 */