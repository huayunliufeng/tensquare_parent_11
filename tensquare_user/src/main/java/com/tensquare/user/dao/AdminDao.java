package com.tensquare.user.dao;

import com.tensquare.user.pojo.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
*
* @author 华韵流风
* @Date 2021-10-09 10:48:19
*/
public interface AdminDao extends JpaRepository<Admin, String>, JpaSpecificationExecutor<Admin> {

    /**
     * 查询管理员
     *
     * @param loginname loginname
     * @return Admin
     */
    Admin findByLoginname(String loginname);




}
