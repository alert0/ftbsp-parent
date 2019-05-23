package com.friendtimes.common.model.response;

/**
 * 10000-- 通用错误代码
 * 22000-- XX服务错误代码
 * 23000-- 用户服务错误代码
 * 24000-- xx服务错误代码
 * 25000-- xx服务错误代码
 */
public interface ResultCode {
    //操作是否成功,true为成功，false操作失败
    boolean isOk();
    //操作代码
    int code();
    //提示信息
    String msg();

}
