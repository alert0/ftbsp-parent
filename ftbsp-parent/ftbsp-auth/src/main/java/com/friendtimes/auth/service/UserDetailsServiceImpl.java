package com.friendtimes.auth.service;

import com.friendtimes.auth.feign.UserClient;
import com.friendtimes.domain.user.ext.Menu;
import com.friendtimes.domain.user.ext.UserExt;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	UserClient userClient;

	@Autowired
	ClientDetailsService clientDetailsService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// 取出身份，如果身份为空说明没有认证
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		// 没有认证统一采用httpbasic认证，httpbasic中存储了client_id和client_secret，开始认证client_id和client_secret
		if (authentication == null) {
			ClientDetails clientDetails = clientDetailsService.loadClientByClientId(username);
			if (clientDetails != null) {
				// 密码
				String clientSecret = clientDetails.getClientSecret();
				return new User(username, clientSecret, AuthorityUtils.commaSeparatedStringToAuthorityList(""));
			}
		}
		if (StringUtils.isEmpty(username)) {
			return null;
		}
		// 远程调用域控服务
		// UserExt userext = userClient.getUserext(username);
		// if(userext == null){
		// 返回空给spring security表示用户不存在
		// return null;
		// }

		UserExt userext = new UserExt();
		userext.setUsername("test");
		userext.setPassword(new BCryptPasswordEncoder().encode("123"));
		//userext.setPermissions(new ArrayList<Menu>());// 权限暂时用静态的

		// 取出正确密码（hash值）
		String password = userext.getPassword();

		// 从数据库获取权限
		/*List<Menu> permissions = userext.getPermissions();
		if (permissions == null) {
			permissions = new ArrayList<>();
		}*/
		List<String> user_permission = new ArrayList<>();
		//permissions.forEach(item -> user_permission.add(item.getCode()));
		// 使用静态的权限表示用户所拥有的权限
		String user_permission_string = StringUtils.join(user_permission.toArray(), ",");
		UserJwt userDetails = new UserJwt(username, password,
				AuthorityUtils.commaSeparatedStringToAuthorityList(user_permission_string));
		userDetails.setId(userext.getId());
		userDetails.setUtype(userext.getUtype());// 用户类型
		//userDetails.setCompanyId(userext.getCompanyId());// 所属企业
		userDetails.setName(userext.getName());// 用户名称
		userDetails.setUserpic(userext.getUserpic());// 用户头像
		return userDetails;
	}
	
	public static void main(String[] args) {
		List<String> user_permission = new ArrayList<>();
		user_permission.add("root");
		user_permission.add("admin");
		user_permission.add("user");
		String user_permission_string = StringUtils.join(user_permission.toArray(), ",");
		System.out.println(user_permission_string);
	}
}
