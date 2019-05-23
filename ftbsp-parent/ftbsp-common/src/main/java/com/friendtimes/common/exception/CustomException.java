package com.friendtimes.common.exception;

import com.friendtimes.common.model.response.ResultCode;

/**
 * 自定义异常类型
 * 
 * @author Administrator
 * @version 1.0
 **/
public class CustomException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	// 错误代码
	ResultCode resultCode;

	public CustomException(ResultCode resultCode) {
		this.resultCode = resultCode;
	}

	public ResultCode getResultCode() {
		return resultCode;
	}

}
