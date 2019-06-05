/*    */ package com.dstz.bpm.api.exception;
/*    */ 
/*    */ import com.dstz.base.api.constant.IStatusCode;
/*    */ import com.dstz.base.api.exception.BusinessException;
/*    */ 
/*    */ public class WorkFlowException
/*    */   extends BusinessException
/*    */ {
/*  9 */   public WorkFlowException(String msg, IStatusCode errorCode) { super(msg, errorCode); }
/*    */ 
/*    */ 
/*    */   
/* 13 */   public WorkFlowException(IStatusCode errorCode) { super(errorCode); }
/*    */ 
/*    */ 
/*    */   
/* 17 */   public WorkFlowException(IStatusCode errorCode, Throwable throwable) { super(errorCode, throwable); }
/*    */ }


/* Location:              D:\Repository\com\dstz\agilebpm\wf-api\1.5.1\wf-api-1.5.1.jar!\com\dstz\bpm\api\exception\WorkFlowException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.0
 */