package com.friendtimes.user.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.friendtimes.domain.user.ext.CompanyUser;

/**
 * Created by Administrator.
 */
public interface CompanyUserRepository extends JpaRepository<CompanyUser,String> {
    //根据用户id查询该用户所属的公司id
    CompanyUser findByUserId(String userId);
}
