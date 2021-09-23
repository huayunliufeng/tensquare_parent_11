package com.tensquare.base.dao;

import com.tensquare.base.pojo.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author 华韵流风
 * @ClassName CityDao
 * @Date 2021/9/18 13:25
 * @packageName com.tensquare.base.dao
 * @Description TODO
 */
public interface CityDao extends JpaRepository<City,String>, JpaSpecificationExecutor<City> {
}
