package com.friendtimes.auth.web;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import com.friendtimes.api.auth.AuthControllerApi;
import com.friendtimes.auth.service.AuthService;
import com.friendtimes.common.exception.ExceptionCast;
import com.friendtimes.common.model.response.CommonCode;
import com.friendtimes.common.model.response.ResponseResult;
import com.friendtimes.domain.user.ext.AuthToken;
import com.friendtimes.domain.user.ext.request.LoginRequest;
import com.friendtimes.domain.user.ext.response.AuthCode;
import com.friendtimes.domain.user.ext.response.JwtResult;
import com.friendtimes.domain.user.ext.response.LoginResult;
import com.friendtimes.utils.CookieUtil;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author Administrator
 * @version 1.0
 **/
@RestController
@RequestMapping("/")
public class AuthController implements AuthControllerApi {

	@Value("${auth.clientId}")
	String clientId;
	@Value("${auth.clientSecret}")
	String clientSecret;
	@Value("${auth.cookieDomain}")
	String cookieDomain;
	@Value("${auth.cookieMaxAge}")
	int cookieMaxAge;

	@Autowired
	AuthService authService;

	@Override
	// @PostMapping("/userlogin")
	@GetMapping("/login")
	public LoginResult login(LoginRequest loginRequest) {
		if (loginRequest == null || StringUtils.isEmpty(loginRequest.getUsername())) {
			ExceptionCast.cast(AuthCode.AUTH_USERNAME_NONE);
		}
		if (loginRequest == null || StringUtils.isEmpty(loginRequest.getPassword())) {
			ExceptionCast.cast(AuthCode.AUTH_PASSWORD_NONE);
		}
		// 账号
		String username = loginRequest.getUsername();
		// 密码
		String password = loginRequest.getPassword();

		// 申请令牌
		AuthToken authToken = authService.login(username, password, clientId, clientSecret);

		// 用户身份令牌
		String access_token = authToken.getAccess_token();
		// 将令牌存储到cookie
		this.saveCookie(access_token);

		return new LoginResult(CommonCode.SUCCESS, access_token);
	}

	// 将令牌存储到cookie
	private void saveCookie(String token) {

		HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getResponse();
		// HttpServletResponse response,String domain,String path, String name, String
		// value, int maxAge,boolean httpOnly
		CookieUtil.addCookie(response, "ft.com", "/", "uid", token, cookieMaxAge, false);
		CookieUtil.addCookie(response, cookieDomain, "/", "uid", token, cookieMaxAge, false);

	}

	// 从cookie删除token
	private void clearCookie(String token) {

		HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getResponse();
		// HttpServletResponse response,String domain,String path, String name, String
		// value, int maxAge,boolean httpOnly
		CookieUtil.addCookie(response, cookieDomain, "/", "uid", token, 0, false);

	}

	// 退出
	@Override
	// @PostMapping("/userlogout")
	@GetMapping("/logout")
	public ResponseResult logout() {
		// 取出cookie中的用户身份令牌
		String uid = getTokenFormCookie();
		// 删除redis中的token
		@SuppressWarnings("unused")
		boolean result = authService.delToken(uid);
		// 清除cookie
		this.clearCookie(uid);
		return new ResponseResult(CommonCode.SUCCESS);
	}

	@Override
	@GetMapping("/userjwt")
	public JwtResult userjwt() {
		// 取出cookie中的用户身份令牌
		String uid = getTokenFormCookie();
		if (uid == null) {
			return new JwtResult(CommonCode.FAIL, null);
		}

		// 拿身份令牌从redis中查询jwt令牌
		AuthToken userToken = authService.getUserToken(uid);
		if (userToken != null) {
			// 将jwt令牌返回给用户
			String jwt_token = userToken.getJwt_token();
			return new JwtResult(CommonCode.SUCCESS, jwt_token);
		}
		return null;
	}

	
	// 取出cookie中的身份令牌
	private String getTokenFormCookie() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		Map<String, String> map = CookieUtil.readCookie(request, "uid");
		if (map != null && map.get("uid") != null) {
			String uid = map.get("uid");
			return uid;
		}
		return null;
	}
}
