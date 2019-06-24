package com.friendtimes.common.model.response;

import lombok.ToString;

@ToString
public enum CommonCode implements ResultCode{
	
    SUCCESS(true,200,"操作成功！"),
    FAIL(false,10000,"操作失败！"),
    UNAUTHENTICATED(false,10001,"此操作需要登陆系统！"),
    UNAUTHORISE(false,10002,"权限不足，无权操作！"),
    INVALID_PARAM(false,10003,"非法参数！"),
    SERVER_ERROR(false,19999,"抱歉，系统繁忙，请稍后重试！");
	//private static ImmutableMap<Integer, CommonCode> codes ;
    //操作是否成功
    boolean isOk;
    //操作代码
    int code;
    //提示信息
    String msg;
    private CommonCode(boolean success,int code, String message){
        this.isOk = success;
        this.code = code;
        this.msg = message;
    }

    @Override
    public boolean isOk() {
        return isOk;
    }
    
    @Override
    public int code() {
        return code;
    }

    @Override
    public String msg() {
        return msg;
    }
}
