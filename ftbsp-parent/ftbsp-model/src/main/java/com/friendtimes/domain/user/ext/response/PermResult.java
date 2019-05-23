package com.friendtimes.domain.user.ext.response;

import java.util.List;
import com.friendtimes.common.model.response.ResponseResult;
import com.friendtimes.common.model.response.ResultCode;
import com.friendtimes.domain.user.ext.Permissions;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public class PermResult extends ResponseResult {

	// 权限信息
	private List<Permissions> permissions;

	public PermResult(ResultCode resultCode, List<Permissions> permissions) {
		super(resultCode);
		this.permissions = permissions;
	}

}
