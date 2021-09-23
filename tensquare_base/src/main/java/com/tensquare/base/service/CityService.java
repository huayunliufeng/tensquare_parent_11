package com.tensquare.base.service;

import com.tensquare.base.dao.CityDao;
import com.tensquare.base.pojo.City;
import com.tensquare.base.pojo.Label;
import com.tensquare.utils.IdWorker;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 华韵流风
 * @ClassName CityService
 * @Date 2021/9/18 13:26
 * @packageName com.tensquare.base.service
 * @Description TODO
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class CityService {

    @Autowired
    private CityDao cityDao;

    @Autowired
    private IdWorker idWorker;

    /**
     * 添加city
     *
     * @param city city
     */
    public void add(City city){
        //增加和修改都由save来完成，底层会判断id主键在表中是否存在，如果存在执行修改，否则执行添加。
        city.setId(String.valueOf(idWorker.nextId()));
        cityDao.save(city);
    }

    /**
     * 删除city
     *
     * @param id id
     */
    public void remove(String id){
        cityDao.deleteById(id);
    }

    /**
     * 根据id修改city
     *
     * @param id id
     * @param city city
     */
    public void update(String id,City city){
        city.setId(id);
        cityDao.save(city);
    }

    /**
     * 根据id查询city
     *
     * @param id id
     * @return Label
     */
    public City findById(String id){
        return cityDao.findById(id).get();
    }

    /**
     * 查询所有city
     *
     * @return List<City>
     */
    public List<City> findAll(){
        return cityDao.findAll();
    }

    /**
     * 根据条件查询城市列表
     *
     * @param city city
     * @return List<City>
     */
    public List<City> findByCity(City city){
        return cityDao.findAll(getSpecification(city));
    }

    /**
     * 城市分页
     *
     * @param city city
     * @param page page
     * @param size size
     * @return Page<City>
     */
    public Page<City> findByCityPage(City city, int page, int size){
        return cityDao.findAll(getSpecification(city), PageRequest.of(page-1, size));
    }

    private Specification<City> getSpecification(City city){
        List<Predicate> list = new ArrayList<>();
        return (Specification<City>) (root, criteriaQuery, criteriaBuilder) -> {
            if (!StringUtils.isEmpty(city.getId())) {
                Predicate predicate = criteriaBuilder.like(root.get("id").as(String.class), "%" + city.getId() + "%");
                list.add(predicate);
            }
            if (!StringUtils.isEmpty(city.getName())) {
                Predicate predicate = criteriaBuilder.like(root.get("name").as(String.class), "%" + city.getName() + "%");
                list.add(predicate);
            }
            if (!StringUtils.isEmpty(city.getIshot())) {
                Predicate predicate = criteriaBuilder.like(root.get("ishot").as(String.class), city.getIshot());
                list.add(predicate);
            }

            return criteriaBuilder.and(list.toArray(new Predicate[0]));
        };

    }
}
