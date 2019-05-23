package com.friendtimes.domain.user.ext;

import lombok.Data;
import lombok.ToString;
import java.util.List;

@Data
@ToString
public class UserExt extends User {

	// 权限信息
	private List<Permissions> permissions;

	// 企业信息
	//private String companyId;
}
