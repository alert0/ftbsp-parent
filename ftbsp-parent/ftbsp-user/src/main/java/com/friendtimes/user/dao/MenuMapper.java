package com.friendtimes.user.dao;

import com.friendtimes.domain.user.ext.Permissions;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

/**
 * Created by Administrator.
 */
@Mapper
public interface MenuMapper {

	// 根据用户id查询用户的权限
	public List<Permissions> selectPermissionByUserId(String userid);
	
	public List<Permissions> selectPermissionByUserName(String userName);
	//public List<Menu> selectPermissionByUserId(String userid);
}
