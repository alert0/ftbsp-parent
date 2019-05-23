package com.friendtimes.api.auth;

import com.friendtimes.common.model.response.ResponseResult;
import com.friendtimes.domain.user.ext.request.LoginRequest;
import com.friendtimes.domain.user.ext.response.JwtResult;
import com.friendtimes.domain.user.ext.response.LoginResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Created by Administrator.
 */
@Api(value = "用户认证", description = "用户认证接口")
public interface AuthControllerApi {

	@ApiOperation("登录")
	public LoginResult login(LoginRequest loginRequest);

	@ApiOperation("退出")
	public ResponseResult logout();

	@ApiOperation("查询用户jwt令牌")
	public JwtResult userjwt();
}
