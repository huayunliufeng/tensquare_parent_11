package com.tensquare.recruit.service;

import com.tensquare.recruit.dao.EnterpriseDao;
import com.tensquare.recruit.pojo.Enterprise;
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
 */
@Service
@Transactional
public class EnterpriseService {
    @Autowired
    private EnterpriseDao enterpriseDao;
    @Autowired
    private IdWorker idWorker;

    public void add(Enterprise enterprise) {
        //增加和修改都由save来完成,底层会判断id主键在表中是否存在，若存在执行修改否则执行添加
        enterprise.setId(String.valueOf(idWorker.nextId()));
        enterpriseDao.save(enterprise);
    }

    public void remove(String id) {
        enterpriseDao.deleteById(id);
    }

    public void update(String id, Enterprise enterprise) {
        enterprise.setId(id);
        enterpriseDao.save(enterprise);
    }

    public Enterprise findById(String id) {
        return enterpriseDao.findById(id).get();
    }

    public List<Enterprise> findAll() {
        return enterpriseDao.findAll();
    }

    /**
     * 根据ishot查询企业列表
     *
     * @return List<Enterprise>
     */
    public List<Enterprise> findByIshot() {
        return enterpriseDao.findByIshot("1");
    }

    /**
     * 根据条件查询企业列表
     *
     * @param enterprise enterprise
     * @return List<Enterprise>
     */
    public List<Enterprise> findByEnterprise(Enterprise enterprise) {
        List<Predicate> list = new ArrayList<>();
        return enterpriseDao.findAll((Specification<Enterprise>) (root, criteriaQuery, cb) -> {
            if (!StringUtils.isEmpty(enterprise.getName())) {
                Predicate predicate = cb.like(root.get("name").as(String.class), "%" + enterprise.getName() + "%");
                list.add(predicate);
            }
            if (!StringUtils.isEmpty(enterprise.getSummary())) {
                Predicate predicate = cb.like(root.get("summary").as(String.class), "%" + enterprise.getSummary() + "%");
                list.add(predicate);
            }
            if (!StringUtils.isEmpty(enterprise.getAddress())) {
                Predicate predicate = cb.like(root.get("address").as(String.class), "%" + enterprise.getAddress() + "%");
                list.add(predicate);
            }
            if (!StringUtils.isEmpty(enterprise.getLabels())) {
                Predicate predicate = cb.like(root.get("labels").as(String.class), "%" + enterprise.getLabels() + "%");
                list.add(predicate);
            }
            return cb.and(list.toArray(new Predicate[0]));
        });
    }

    /**
     * 根据条件分页查询企业列表
     *
     * @param enterprise enterprise
     * @param page       page
     * @param size       size
     * @return Page<Enterprise>
     */
    public Page<Enterprise> findByEnterprisePage(Enterprise enterprise, int page, int size) {
        List<Predicate> list = new ArrayList<>();
        return enterpriseDao.findAll((Specification<Enterprise>) (root, criteriaQuery, cb) -> {
            if (!StringUtils.isEmpty(enterprise.getName())) {
                Predicate predicate = cb.like(root.get("name").as(String.class), "%" + enterprise.getName() + "%");
                list.add(predicate);
            }
            if (!StringUtils.isEmpty(enterprise.getSummary())) {
                Predicate predicate = cb.like(root.get("summary").as(String.class), "%" + enterprise.getSummary() + "%");
                list.add(predicate);
            }
            if (!StringUtils.isEmpty(enterprise.getAddress())) {
                Predicate predicate = cb.like(root.get("address").as(String.class), "%" + enterprise.getAddress() + "%");
                list.add(predicate);
            }
            if (!StringUtils.isEmpty(enterprise.getLabels())) {
                Predicate predicate = cb.like(root.get("labels").as(String.class), "%" + enterprise.getLabels() + "%");
                list.add(predicate);
            }
            return cb.and(list.toArray(new Predicate[0]));
        }, PageRequest.of(page - 1, size));
    }
}
