package com.friendtimes.common.exception;

import com.friendtimes.common.model.response.ResultCode;

/**
 * @author Administrator
 * @version 1.0
 **/
public class ExceptionCast {

	public static void cast(ResultCode resultCode) {
		throw new CustomException(resultCode);
	}
}
