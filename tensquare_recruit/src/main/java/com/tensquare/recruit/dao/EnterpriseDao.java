package com.tensquare.recruit.dao;

import com.tensquare.recruit.pojo.Enterprise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @author 华韵流风
 */
public interface EnterpriseDao extends JpaRepository<Enterprise,String>,JpaSpecificationExecutor<Enterprise> {
    /**
     * 根据ishot查询企业列表
     *自定义的接口(依据jpa接口的方法规则，可以拼出各种条件的查询方法)
     * @param ishot ishot
     * @return List<Enterprise>
     */
    List<Enterprise> findByIshot(String ishot);
}
