package com.friendtimes.domain.user.ext;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ModuleData {
    private String name;
    //判断新老员工,"old"为老员工，"new"为新员工
    private String status;
    //通用模块模块信息
    private List<Module> modules;
    //专用模块信息
    private List<Module> dedicatedModule;

    public ModuleData(List<Module> dedicatedModule) {
        this.dedicatedModule = dedicatedModule;
    }
}
