package com.tensquare.article.service;

import com.tensquare.article.dao.ColumnDao;
import com.tensquare.article.pojo.Column;
import com.tensquare.utils.IdWorker;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author 华韵流风
 * @ClassName ColumnService
 * @Date 2021/9/18 14:09
 * @packageName com.tensquare.article.service
 * @Description TODO
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ColumnService {

    @Autowired
    private ColumnDao columnDao;

    @Autowired
    private IdWorker idWorker;

    /**
     * 添加column
     *
     * @param column column
     */
    public void add(Column column) {
        column.setId(String.valueOf(idWorker.nextId()));
        column.setCreatetime(new Date());
        columnDao.save(column);
    }

    /**
     * 根据id删除column
     *
     * @param id id
     */
    public void remove(String id) {
        columnDao.deleteById(id);
    }

    /**
     * 根据id修改column
     *
     * @param id     id
     * @param column column
     */
    public void update(String id, Column column) {
        column.setId(id);
        columnDao.save(column);
    }

    /**
     * 根据id查询column
     *
     * @param id id
     * @return column
     */
    public Column findById(String id) {
        return columnDao.findById(id).get();
    }

    /**
     * 查询所有column
     *
     * @return List<Column>
     */
    public List<Column> findAll() {
        return columnDao.findAll();
    }

    /**
     * 根据条件查询专栏列表
     *
     * @param column column
     * @return List<Column>
     */
    public List<Column> findByColumn(Column column) {
        return columnDao.findAll(getSpecification(column));
    }

    /**
     * 专栏分页
     *
     * @param column column
     * @param page   page
     * @param size   size
     * @return Page<Column>
     */
    public Page<Column> findByColumnPage(Column column, int page, int size) {
        return columnDao.findAll(getSpecification(column), PageRequest.of(page - 1, size));
    }

    /**
     * 专栏申请
     *
     * @param column column
     */
    public void columnApply(Column column){
        column.setId(String.valueOf(idWorker.nextId()));
        columnDao.save(column);
    }

    /**
     * 申请审核
     *
     * @param columnId columnId
     */
    public void examine(String columnId){
        Column column = columnDao.findById(columnId).get();
        column.setChecktime(new Date());
        column.setState("1");
        columnDao.save(column);
    }

    public List<Column> findByUserid(String userId){
        return columnDao.findByUserid(userId);
    }


    private Specification<Column> getSpecification(Column column){
        List<Predicate> list = new ArrayList<>();
        return (Specification<Column>) (root, criteriaQuery, criteriaBuilder) -> {
            if (!StringUtils.isEmpty(column.getId())) {
                Predicate predicate = criteriaBuilder.like(root.get("id").as(String.class), "%" + column.getId() + "%");
                list.add(predicate);
            }
            if (!StringUtils.isEmpty(column.getName())) {
                Predicate predicate = criteriaBuilder.like(root.get("name").as(String.class), "%" + column.getName() + "%");
                list.add(predicate);
            }
            if (!StringUtils.isEmpty(column.getState())) {
                Predicate predicate = criteriaBuilder.like(root.get("state").as(String.class), column.getState());
                list.add(predicate);
            }
            if (!StringUtils.isEmpty(column.getSummary())) {
                Predicate predicate = criteriaBuilder.like(root.get("summary").as(String.class), "%" + column.getState() + "%");
                list.add(predicate);
            }
            if (!StringUtils.isEmpty(column.getUserid())) {
                Predicate predicate = criteriaBuilder.like(root.get("userid").as(String.class), "%" + column.getState() + "%");
                list.add(predicate);
            }
            if (column.getCreatetime() != null) {
                list.add(criteriaBuilder.equal(root.get("createtime").as(Date.class), column.getCreatetime()));
            }
            if (column.getChecktime() != null) {
                list.add(criteriaBuilder.equal(root.get("checktime").as(Date.class), column.getChecktime()));
            }
            return criteriaBuilder.and(list.toArray(new Predicate[0]));
        };

    }

}
