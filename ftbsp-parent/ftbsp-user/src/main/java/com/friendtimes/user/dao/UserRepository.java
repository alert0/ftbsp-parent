package com.friendtimes.user.dao;

import com.friendtimes.domain.user.ext.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Administrator.
 */
public interface UserRepository extends JpaRepository<User, String> {

	// 根据账号查询用户信息
	User findByUsername(String username);
}
