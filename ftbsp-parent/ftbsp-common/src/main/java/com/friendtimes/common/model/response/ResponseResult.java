package com.friendtimes.common.model.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public class ResponseResult implements Response {

    //操作是否成功
    boolean isOk = SUCCESS;

    //操作代码
    int code = SUCCESS_CODE;

    //提示信息
    String msg;

    public ResponseResult(ResultCode resultCode){
        this.isOk = resultCode.isOk();
        this.code = resultCode.code();
        this.msg = resultCode.msg();
    }

    public static ResponseResult SUCCESS(){
        return new ResponseResult(CommonCode.SUCCESS);
    }
    public static ResponseResult FAIL(){
        return new ResponseResult(CommonCode.FAIL);
    }

}
