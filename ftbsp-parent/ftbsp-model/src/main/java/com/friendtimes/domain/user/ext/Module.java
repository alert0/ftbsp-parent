package com.friendtimes.domain.user.ext;

import lombok.Data;
import lombok.ToString;


@Data
@ToString
public class Module {
    private String module_id;
    private String module_name;
    private String icon;
    private String detail;
    private String url;
    private String colour;
}
