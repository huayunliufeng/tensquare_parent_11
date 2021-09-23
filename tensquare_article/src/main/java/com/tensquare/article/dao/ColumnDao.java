package com.tensquare.article.dao;

import com.tensquare.article.pojo.Column;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @author 华韵流风
 * @ClassName ColumnDao
 * @Date 2021/9/18 14:09
 * @packageName com.tensquare.article.dao
 * @Description TODO
 */
public interface ColumnDao extends JpaRepository<Column,String>, JpaSpecificationExecutor<Column> {

    /**
     * 根据用户ID查询专栏列表
     *
     * @param userId userId
     * @return List<Column>
     */
    List<Column> findByUserid(String userId);
}
