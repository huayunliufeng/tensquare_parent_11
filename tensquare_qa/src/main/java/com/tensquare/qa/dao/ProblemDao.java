package com.tensquare.qa.dao;

import com.tensquare.qa.pojo.Problem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProblemDao extends JpaRepository<Problem,String>, JpaSpecificationExecutor<Problem> {
    /**
     *
     * @param labelid labelid 标签id
     * @param page page
     * @return List
     */
    @Query(value = "SELECT * FROM tb_problem p WHERE p.id IN(SELECT id FROM tb_pl WHERE labelid = ?1) order by createtime desc ",nativeQuery = true)
    Page<Problem> newList(String labelid, Pageable page);

    /**
     * 热门
     *
     * @param labelid labelid
     * @param pageable pageable
     * @return Page
     */
    @Query(value = "SELECT * FROM tb_problem p WHERE p.id IN(SELECT id FROM tb_pl WHERE labelid = :labelid) order by reply desc ",nativeQuery = true)
    Page<Problem> hostList(@Param("labelid") String labelid, Pageable pageable);

    /**
     *
     *
     * @param labelid labelid
     * @param pageable pageable
     * @return Page<Problem>
     */
    @Query(value = "SELECT * FROM tb_problem p WHERE p.id IN(SELECT id FROM tb_pl WHERE labelid = :labelid) and reply  = 0 ",nativeQuery = true)
    Page<Problem> waitList(@Param("labelid") String labelid,Pageable pageable);

    @Query(value = "SELECT * FROM tb_problem p WHERE p.id IN(SELECT id FROM tb_pl WHERE labelid = :labelid)  ",nativeQuery = true)
    Page<Problem> allList(@Param("labelid") String labelid,Pageable pageable);
}
