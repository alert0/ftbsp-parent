package com.friendtimes.user.dao;

import com.friendtimes.domain.user.ext.Module;
import com.friendtimes.user.PoJo.UserModule;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ModuleMapper {
    List<Module> getDedicatedModuleByUserName(String username);

    List<Module> getModulesByUserName(String username);

    void addModuleByIds(@Param("ids") List<UserModule> idsArray);

    void deleteModuleByIds(@Param("ids") List<String> idsArray, @Param("username") String username);
}
