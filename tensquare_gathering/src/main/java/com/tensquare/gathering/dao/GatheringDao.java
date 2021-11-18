package com.tensquare.gathering.dao;

import com.tensquare.gathering.pojo.Gathering;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author 华韵流风
 * @Date 2021-10-24 16:30:11
 */
public interface GatheringDao extends JpaRepository<Gathering, String>, JpaSpecificationExecutor<Gathering> {

    /**
     * 根据城市显示分页内容
     *
     * @param city     city
     * @param pageable pageable
     * @return Page<Gathering>
     */
    Page<Gathering> findByCity(String city, Pageable pageable);

}
