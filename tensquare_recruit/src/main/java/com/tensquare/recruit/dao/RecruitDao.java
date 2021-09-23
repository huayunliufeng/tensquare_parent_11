package com.tensquare.recruit.dao;

import com.tensquare.recruit.pojo.Recruit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface RecruitDao extends JpaRepository<Recruit,String>,JpaSpecificationExecutor<Recruit> {
    /**
     * 根据State查询招聘列表，按照时间降序
     *
     * @param state state
     * @return List<Recruit>
     */
    List<Recruit> findByStateOrderByCreatetimeDesc(String state);

    /**
     * 根据NotState查询招聘列表，按照时间降序
     *
     * @param state state
     * @return List<Recruit>
     */
    List<Recruit> findByStateNotOrderByCreatetimeDesc(String state);
}
