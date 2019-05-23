package com.friendtimes.domain.user.ext.request;

import com.friendtimes.common.model.request.RequestData;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class LoginRequest extends RequestData {

	String username;
	String password;
	String verifycode;

}
