package com.friendtimes.auth.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.friendtimes.common.client.ServiceList;
import com.friendtimes.domain.user.ext.UserExt;

@FeignClient(value = ServiceList.FT_SERVICE_UCENTER)
public interface UserClient {
	// 根据账号查询用户信息
	@GetMapping("/user/getuserinfo")
	public UserExt getUserext(@RequestParam("username") String username);
}
