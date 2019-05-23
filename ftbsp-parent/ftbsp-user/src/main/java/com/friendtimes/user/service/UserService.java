package com.friendtimes.user.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.friendtimes.domain.user.ext.Permissions;
import com.friendtimes.domain.user.ext.User;
import com.friendtimes.domain.user.ext.UserExt;
import com.friendtimes.user.dao.CompanyUserRepository;
import com.friendtimes.user.dao.MenuMapper;
import com.friendtimes.user.dao.UserRepository;

import java.util.List;

/**
 * @author Administrator
 * @version 1.0
 **/
@Service
public class UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	CompanyUserRepository companyUserRepository;

	@Autowired
	MenuMapper menuMapper;

	// 根据账号查询xcUser信息
	public User findUserByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	// 根据账号查询用户信息
	public UserExt getUserExt(String username) {
		// 根据账号查询User信息
		User user = this.findUserByUsername(username);
		if (user == null) {
			return null;
		}
		// 用户id
		String userId = user.getId();
		// 查询用户所有权限
		List<Permissions> menus = menuMapper.selectPermissionByUserId(userId);

		// 根据用户id查询用户所属公司id
		// CompanyUser companyUser = companyUserRepository.findByUserId(userId);
		// 取到用户的公司id
		/*
		 * String companyId = null; if (companyUser != null) { companyId =
		 * companyUser.getCompanyId(); }
		 */
		UserExt userExt = new UserExt();
		BeanUtils.copyProperties(user, userExt);
		// userExt.setCompanyId(companyId);
		// 设置权限
		userExt.setPermissions(menus);
		// userExt.setPassword(null);
		return userExt;
	}

	public List<Permissions> getUserPerm(String username) {
		// 根据账号查询User信息
		User user = this.findUserByUsername(username);
		if (user == null) {
			return null;
		}
		// 用户id
		String userId = user.getId();
		// 查询用户所有权限
		List<Permissions> perm = menuMapper.selectPermissionByUserId(userId);
		return perm;
	}
}
