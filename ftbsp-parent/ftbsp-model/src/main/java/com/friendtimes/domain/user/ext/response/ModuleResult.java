package com.friendtimes.domain.user.ext.response;

import com.friendtimes.common.model.response.ResponseResult;
import com.friendtimes.common.model.response.ResultCode;
import com.friendtimes.domain.user.ext.ModuleData;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@ToString
@NoArgsConstructor
public class ModuleResult extends ResponseResult {

    //返回信息
    private ModuleData data;


    public ModuleResult(ResultCode resultCode) {
        super(resultCode);
    }

    public ModuleResult(ResultCode resultCode,ModuleData data) {
        super(resultCode);
        this.data = data;
    }




}
