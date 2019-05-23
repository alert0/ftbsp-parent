package com.friendtimes.api.user;

import javax.servlet.http.HttpServletRequest;

import com.friendtimes.domain.user.ext.UserExt;
import com.friendtimes.domain.user.ext.response.PermResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "用户中心", description = "用户中心管理")
public interface UserControllerApi {

	@ApiOperation("根据用户账号查询用户信息")
	public UserExt getUserext(String username);

	@ApiOperation("根据用户账号查询用户权限")
	public PermResult getUserPerm(String username);

	String logStash(HttpServletRequest data);
}
