package com.tensquare.spit.dao;

import com.tensquare.spit.pojo.Spit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author 华韵流风
 * @ClassName SpitDao
 * @Date 2021/9/22 20:21
 * @packageName com.tensquare.spit.dao
 * @Description TODO
 */
public interface SpitDao extends JpaRepository<Spit,String>, JpaSpecificationExecutor<Spit> {
}
