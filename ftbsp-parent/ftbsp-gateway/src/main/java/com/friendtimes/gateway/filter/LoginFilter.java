package com.friendtimes.gateway.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.fastjson.JSON;
import com.friendtimes.common.model.response.CommonCode;
import com.friendtimes.common.model.response.ResponseResult;
import com.friendtimes.gateway.service.AuthService;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

/**
 * 身份校验过虑器
 * 
 * @author Administrator
 * @version 1.0
 **/

//@Component
public class LoginFilter extends ZuulFilter {

	@Autowired
	AuthService authService;

	@Override
	public boolean shouldFilter() {
		// 返回true表示要执行此过虑器
		return true;
	}

	/**
	 * 过虑器的内容
	 * 
	 * 过虑所有请求，判断头部信息是否有Authorization，如果没有则拒绝访问，否则转发到微服务。
	 */
	@Override
	public Object run() throws ZuulException {
		RequestContext requestContext = RequestContext.getCurrentContext();
		// 得到request
		HttpServletRequest request = requestContext.getRequest();
		// 得到response
		// HttpServletResponse response = requestContext.getResponse();

		// 取cookie中的身份令牌
		String tokenFromCookie = authService.getTokenFromCookie(request);
		if (StringUtils.isEmpty(tokenFromCookie)) {
			// 拒绝访问
			access_denied();
			return null;
		}
		// 从header中取jwt
		String jwtFromHeader = authService.getJwtFromHeader(request);
		if (StringUtils.isEmpty(jwtFromHeader)) {
			// 拒绝访问
			access_denied();
			return null;
		}
		// 从redis取出jwt的过期时间
		long expire = authService.getExpire(tokenFromCookie);
		if (expire < 0) {
			// 拒绝访问
			access_denied();
			return null;
		}

		return null;
	}

	// 拒绝访问
	private void access_denied() {
		RequestContext requestContext = RequestContext.getCurrentContext();
		// 得到response
		HttpServletResponse response = requestContext.getResponse();
		// 拒绝访问
		requestContext.setSendZuulResponse(false);
		// 设置响应代码
		requestContext.setResponseStatusCode(200);
		// 构建响应的信息
		ResponseResult responseResult = new ResponseResult(CommonCode.UNAUTHENTICATED);
		// 转成json
		String jsonString = JSON.toJSONString(responseResult);
		requestContext.setResponseBody(jsonString);
		// 转成json，设置contentType
		response.setContentType("application/json;charset=utf-8");
	}

	/**
	 * 过滤器的类型
	 * 
	 * pre：请求在被路由之前执行
	 * 
	 * routing：在路由请求时调用
	 * 
	 * post：在routing和errror过滤器之后调用
	 * 
	 * error：处理请求时发生错误调用
	 */
	@Override
	public String filterType() {
		return "pre";
	}

	/**
	 * 过虑器序号，越小越被优先执行
	 */
	@Override
	public int filterOrder() {
		return 0;
	}

}
