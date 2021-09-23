package com.tensquare.base.service;

import com.tensquare.base.dao.LabelDao;
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
 * @ClassName LabelService
 * @Date 2021/9/17 17:24
 * @packageName com.tensquare.base.service
 * @Description TODO
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class LabelService {

    @Autowired
    private LabelDao labelDao;

    @Autowired
    private IdWorker idWorker;

    /**
     * 添加label
     *
     * @param label label
     */
    public void add(Label label) {
        //增加和修改都由save来完成，底层会判断id主键在表中是否存在，如果存在执行修改，否则执行添加。
        label.setId(String.valueOf(idWorker.nextId()));
        labelDao.save(label);
    }

    /**
     * 删除label
     *
     * @param id id
     */
    public void remove(String id) {
        labelDao.deleteById(id);
    }

    /**
     * 根据id修改label
     *
     * @param id    id
     * @param label label
     */
    public void update(String id, Label label) {
        label.setId(id);
        labelDao.save(label);
    }

    /**
     * 根据id查询label
     *
     * @param id id
     * @return Label
     */
    public Label findById(String id) {
        return labelDao.findById(id).get();
    }

    /**
     * 查询所有label
     *
     * @return List<Label>
     */
    public List<Label> findAll() {
        return labelDao.findAll();
    }

    /**
     * 标签综合查询
     *
     * @param label label
     * @return List<Label>
     */
    public List<Label> findByLabel(Label label) {
        return labelDao.findAll(getSpecification(label));
    }

    /**
     * 标签分页
     *
     * @param label label
     * @param page  page
     * @param size  size
     * @return Page<Label>
     */
    public Page<Label> findByLabelPage(Label label, int page, int size) {
        //jpa中的页码是从0开始，所以要减一。
        return labelDao.findAll(getSpecification(label), PageRequest.of(page - 1, size));
    }

    /**
     * 有效标签列表
     *
     * @return List<Label>
     */
    public List<Label> findByState() {
        return labelDao.findByState("1");
    }

    /**
     * 推荐标签列表
     *
     * @return List<Label>
     */
    public List<Label> findByRecommend() {
        return labelDao.findByRecommend("1");
    }


    private Specification<Label> getSpecification(Label label) {
        List<Predicate> list = new ArrayList<>();
        return (Specification<Label>) (root, criteriaQuery, criteriaBuilder) -> {
            if (!StringUtils.isEmpty(label.getLabelname())) {
                Predicate predicate = criteriaBuilder.like(root.get("labelname").as(String.class), "%" + label.getLabelname() + "%");
                list.add(predicate);
            }
            if (!StringUtils.isEmpty(label.getState())) {
                Predicate predicate = criteriaBuilder.like(root.get("state").as(String.class), label.getState());
                list.add(predicate);
            }
            if (!StringUtils.isEmpty(label.getRecommend())) {
                Predicate predicate = criteriaBuilder.like(root.get("recommend").as(String.class), "%" + label.getRecommend() + "%");
                list.add(predicate);
            }
            if (!StringUtils.isEmpty(label.getId())) {
                Predicate predicate = criteriaBuilder.like(root.get("id").as(String.class), "%" + label.getId() + "%");
                list.add(predicate);
            }
            if (label.getCount() != null) {
                list.add(criteriaBuilder.equal(root.get("count").as(Long.class), label.getCount()));
            }
            return criteriaBuilder.and(list.toArray(new Predicate[0]));
        };
    }


}
