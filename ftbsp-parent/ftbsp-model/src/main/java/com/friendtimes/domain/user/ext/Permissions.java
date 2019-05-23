package com.friendtimes.domain.user.ext;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Permissions {
	private String role;
	private String code;
	private String url;
}
