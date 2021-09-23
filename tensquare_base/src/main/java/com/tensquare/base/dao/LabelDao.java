package com.tensquare.base.dao;

import com.tensquare.base.pojo.Label;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @author 华韵流风
 * @ClassName LabelDao
 * @Date 2021/9/17 17:11
 * @packageName com.tensquare.base.dao
 * @Description TODO
 */
public interface LabelDao extends JpaRepository<Label, String>, JpaSpecificationExecutor<Label> {

    /**
     * 有效标签列表
     *
     * @param state state
     * @return List<Label>
     */
    List<Label> findByState(String state);

    /**
     * 推荐标签列表
     *
     * @param recommend recommend
     * @return List<Label>
     */
    List<Label> findByRecommend(String recommend);
}
