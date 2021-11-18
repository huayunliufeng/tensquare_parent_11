package com.tensquare.gathering.service;

import com.tensquare.gathering.dao.GatheringDao;
import com.tensquare.gathering.pojo.Gathering;
import com.tensquare.utils.IdWorker;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Predicate;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author 华韵流风
 * @Date 2021-10-24 16:30:11
 */
@Service
public class GatheringService {

    @Autowired
    private GatheringDao gatheringDao;

    @Autowired
    private IdWorker idWorker;

    /**
     * 根据id查询Gathering
     *
     * @param id id
     * @return Gathering
     */
    public Gathering findById(String id) {
        return gatheringDao.findById(id).get();
    }

    /**
     * 根据id删除Gathering
     *
     * @param id id
     */
    @Transactional(rollbackFor = Exception.class)
    public void remove(String id) {
        gatheringDao.deleteById(id);
    }

    /**
     * 新增Gathering
     *
     * @param gathering Gathering
     */
    @Transactional(rollbackFor = Exception.class)
    public void add(Gathering gathering) {
        gathering.setId(String.valueOf(idWorker.nextId()));
        gatheringDao.save(gathering);
    }

    /**
     * 修改gathering
     *
     * @param gathering gathering
     */
    @Transactional(rollbackFor = Exception.class)
    public void edit(Gathering gathering) {
        gatheringDao.save(gathering);
    }

    /**
     * 查询所有TbGathering
     *
     * @return List<Gathering>
     */
    public List<Gathering> findAll() {
        return gatheringDao.findAll();
    }

    /**
     * 根据条件查询
     *
     * @param gathering gathering
     * @return List<Gathering>
     */
    public List<Gathering> findByGathering(Gathering gathering) {
        return gatheringDao.findAll(getSpecification(gathering));
    }

    /**
     * 活动分页
     *
     * @param gathering gathering
     * @return Page<Gathering>
     */
    public Page<Gathering> findByGatheringPage(Gathering gathering, int page, int size) {
        return gatheringDao.findAll(getSpecification(gathering), PageRequest.of(page - 1, size));
    }

    /**
     * 根据城市显示分页内容
     *
     * @param city city
     * @param page page
     * @param size size
     * @return Page<Gathering>
     */
    public Page<Gathering> findByCity(String city, int page, int size) {
        return gatheringDao.findByCity(city, PageRequest.of(page - 1, size));
    }

    private Specification<Gathering> getSpecification(Gathering gathering) {
        List<Predicate> list = new ArrayList<>();
        return (Specification<Gathering>) (root, criteriaQuery, criteriaBuilder) -> {
            if (!StringUtils.isEmpty(gathering.getId())) {
                Predicate predicate = criteriaBuilder.like(root.get("id").as(String.class), "%" + gathering.getId() + "%");
                list.add(predicate);
            }
            if (!StringUtils.isEmpty(gathering.getName())) {
                Predicate predicate = criteriaBuilder.like(root.get("name").as(String.class), "%" + gathering.getName() + "%");
                list.add(predicate);
            }
            if (!StringUtils.isEmpty(gathering.getSummary())) {
                Predicate predicate = criteriaBuilder.like(root.get("summary").as(String.class), "%" + gathering.getSummary() + "%");
                list.add(predicate);
            }
            if (!StringUtils.isEmpty(gathering.getDetail())) {
                Predicate predicate = criteriaBuilder.like(root.get("detail").as(String.class), "%" + gathering.getDetail() + "%");
                list.add(predicate);
            }
            if (!StringUtils.isEmpty(gathering.getSponsor())) {
                Predicate predicate = criteriaBuilder.like(root.get("sponsor").as(String.class), "%" + gathering.getSponsor() + "%");
                list.add(predicate);
            }
            if (!StringUtils.isEmpty(gathering.getAddress())) {
                Predicate predicate = criteriaBuilder.like(root.get("address").as(String.class), "%" + gathering.getAddress() + "%");
                list.add(predicate);
            }
            if (!StringUtils.isEmpty(gathering.getCity())) {
                Predicate predicate = criteriaBuilder.like(root.get("city").as(String.class), "%" + gathering.getCity() + "%");
                list.add(predicate);
            }
            if (gathering.getStarttime() != null) {
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date end = new Date(gathering.getStarttime().getTime() + 1);
                list.add(criteriaBuilder.between(root.get("starttime"), gathering.getStarttime(), gathering.getStarttime()));
            }

            return criteriaBuilder.and(list.toArray(new Predicate[0]));
        };

    }
}
