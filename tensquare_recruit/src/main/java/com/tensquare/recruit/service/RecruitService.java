package com.tensquare.recruit.service;

import com.tensquare.recruit.dao.RecruitDao;
import com.tensquare.recruit.pojo.Recruit;
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
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class RecruitService {
    @Autowired
    private RecruitDao recruitDao;
    @Autowired
    private IdWorker idWorker;

    public void add(Recruit recruit) {
        //增加和修改都由save来完成,底层会判断id主键在表中是否存在，若存在执行修改否则执行添加
        recruit.setId(String.valueOf(idWorker.nextId()));
        recruit.setCreatetime(new Date());
        recruitDao.save(recruit);
    }

    public void remove(String id) {
        recruitDao.deleteById(id);
    }

    public void update(String id, Recruit recruit) {
        recruit.setId(id);
        recruitDao.save(recruit);
    }

    public Recruit findById(String id) {
        return recruitDao.findById(id).get();
    }

    public List<Recruit> findAll() {
        return recruitDao.findAll();
    }

    /**
     * 根据条件查询招聘列表
     *
     * @param recruit recruit
     * @return List<Recruit>
     */
    public List<Recruit> findByRecruit(Recruit recruit) {
        List<Predicate> list = new ArrayList<>();
        return recruitDao.findAll((Specification<Recruit>) (root, criteriaQuery, cb) -> {
            if (!StringUtils.isEmpty(recruit.getJobname())) {
                Predicate predicate = cb.like(root.get("jobname").as(String.class), "%" + recruit.getJobname() + "%");
                list.add(predicate);
            }
            if (!StringUtils.isEmpty(recruit.getCondition())) {
                Predicate predicate = cb.like(root.get("condition").as(String.class), "%" + recruit.getCondition() + "%");
                list.add(predicate);
            }
            if (!StringUtils.isEmpty(recruit.getAddress())) {
                Predicate predicate = cb.like(root.get("address").as(String.class), "%" + recruit.getAddress() + "%");
                list.add(predicate);
            }
            if (!StringUtils.isEmpty(recruit.getEducation())) {
                Predicate predicate = cb.like(root.get("education").as(String.class), "%" + recruit.getEducation() + "%");
                list.add(predicate);
            }
            return cb.and(list.toArray(new Predicate[0]));
        });
    }

    /**
     * 根据条件分页查询招聘列表
     *
     * @param recruit recruit
     * @param page    page
     * @param size    size
     * @return Page<Recruit>
     */
    public Page<Recruit> findByRecruitPage(Recruit recruit, int page, int size) {
        List<Predicate> list = new ArrayList<>();
        return recruitDao.findAll((Specification<Recruit>) (root, criteriaQuery, cb) -> {
            if (!StringUtils.isEmpty(recruit.getJobname())) {
                Predicate predicate = cb.like(root.get("jobname").as(String.class), "%" + recruit.getJobname() + "%");
                list.add(predicate);
            }
            if (!StringUtils.isEmpty(recruit.getCondition())) {
                Predicate predicate = cb.like(root.get("condition").as(String.class), "%" + recruit.getCondition() + "%");
                list.add(predicate);
            }
            if (!StringUtils.isEmpty(recruit.getAddress())) {
                Predicate predicate = cb.like(root.get("address").as(String.class), "%" + recruit.getAddress() + "%");
                list.add(predicate);
            }
            if (!StringUtils.isEmpty(recruit.getEducation())) {
                Predicate predicate = cb.like(root.get("education").as(String.class), "%" + recruit.getEducation() + "%");
                list.add(predicate);
            }
            return cb.and(list.toArray(new Predicate[0]));
        }, PageRequest.of(page - 1, size));
    }

    /**
     * 根据State查询招聘列表，按照时间降序
     *
     * @return List<Recruit>
     */
    public List<Recruit> findByStateOrderByCreatetimeDesc() {
        return recruitDao.findByStateOrderByCreatetimeDesc("2");
    }

    /**
     * 根据NotState查询招聘列表，按照时间降序
     *
     * @return List<Recruit>
     */
    public List<Recruit> findByStateNotOrderByCreatetimeDesc() {
        return recruitDao.findByStateNotOrderByCreatetimeDesc("0");
    }
}
