package com.friendtimes.domain.user.ext;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * AuthToken模型类，存储申请的令牌，包括身份令牌、刷新令牌、jwt令牌
 */
@Data
@ToString
@NoArgsConstructor
public class AuthToken {
	String access_token;// 访问token就是短令牌，用户身份令牌
	String refresh_token;// 刷新token
	String jwt_token;// jwt令牌
}
