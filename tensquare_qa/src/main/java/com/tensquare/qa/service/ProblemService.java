package com.tensquare.qa.service;

import com.tensquare.qa.dao.ProblemDao;
import com.tensquare.qa.pojo.Problem;
import com.tensquare.utils.IdWorker;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import javax.persistence.criteria.Predicate;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class ProblemService {
    @Autowired
    private ProblemDao problemDao;
    @Autowired
    private IdWorker idWorker;

    /**
     * 添加问题
     *
     * @param problem problem
     */
    public void add(Problem problem) {
        problem.setCreatetime(new Date());
        problem.setId(String.valueOf(idWorker.nextId()));
        problem.setCreatetime(new Date());
        problemDao.save(problem);
    }

    /**
     * 更新问题
     *
     * @param id      问题id
     * @param problem 更新内容
     */
    public void update(String id, Problem problem) {
        problem.setId(id);
        problem.setUpdatetime(new Date());
        problemDao.save(problem);
    }

    /**
     * 删除问题
     *
     * @param id 问题id
     */
    public void remove(String id) {
        problemDao.deleteById(id);
    }

    /**
     * 根据id查找问题
     *
     * @param id id
     * @return Problem
     */
    public Problem findById(String id) {
        return problemDao.findById(id).get();
    }

    /**
     * 查找所有问题
     *
     * @return Problem
     */
    public List<Problem> findAll() {
        return problemDao.findAll();
    }

    /**
     * 最新问答列表
     *
     * @param labelid labelid
     * @param page    page
     * @param size    size
     * @return List
     */
    public List<Problem> newList(String labelid, int page, int size) {
        return problemDao.newList(labelid, PageRequest.of(page - 1, size));
    }

    /**
     * 热门问答列表
     *
     * @param labelid 标签id
     * @param page    page
     * @param size    size
     * @return List
     */
    public Page<Problem> hotList(String labelid, int page, int size) {
        return problemDao.hostList(labelid, PageRequest.of(page - 1, size));
    }

    /**
     * 等待回答列表
     *
     * @param labelid labelid
     * @param page    page
     * @param size    size
     * @return Page
     */
    public Page<Problem> waitList(String labelid, int page, int size) {
        return problemDao.waitList(labelid, PageRequest.of(page - 1, size));
    }

    public Page<Problem> allList(String labelid, int page, int size) {
        return problemDao.allList(labelid, PageRequest.of(page - 1, size));
    }

    /**
     * 根据条件查询问题列表
     *
     * @param problem problem
     * @return List
     */
    public List<Problem> findByProblem(Problem problem) {
        List<Predicate> list = new ArrayList<>();
        return problemDao.findAll((Specification<Problem>) (root, criteriaQuery, criteriaBuilder) -> {
            if (!StringUtils.isEmpty(problem.getTitle())) {
                Predicate predicate = criteriaBuilder.like(root.get("title").as(String.class), "%" + problem.getTitle() + "%");
                list.add(predicate);
            }
            if (!StringUtils.isEmpty(problem.getContent())) {
                Predicate predicate = criteriaBuilder.like(root.get("content").as(String.class), "%" + problem.getContent() + "%");
                list.add(predicate);
            }

            if (problem.getCreatetime() != null) {
                Predicate predicate = criteriaBuilder.like(root.get("createtime").as(String.class), "%" + problem.getCreatetime() + "%");
                list.add(predicate);
            }
            if (problem.getUpdatetime() != null) {
                Predicate predicate = criteriaBuilder.like(root.get("updatetime").as(String.class), "%" + problem.getUpdatetime() + "%");
                list.add(predicate);
            }
            if (problem.getReplytime() != null) {
                Predicate predicate = criteriaBuilder.like(root.get("replytime").as(String.class), "%" + problem.getReplytime() + "%");
                list.add(predicate);
            }

            if (!StringUtils.isEmpty(problem.getUserid())) {
                Predicate predicate = criteriaBuilder.like(root.get("userid").as(String.class), "%" + problem.getUserid() + "%");
                list.add(predicate);
            }
            if (!StringUtils.isEmpty(problem.getNickname())) {
                Predicate predicate = criteriaBuilder.like(root.get("nickname").as(String.class), "%" + problem.getNickname() + "%");
                list.add(predicate);
            }
            if (problem.getVisits() != null) {
                Predicate predicate = criteriaBuilder.like(root.get("visits").as(String.class), "%" + problem.getVisits() + "%");
                list.add(predicate);
            }
            if (problem.getThumbup() != null) {
                Predicate predicate = criteriaBuilder.like(root.get("thumbup").as(String.class), "%" + problem.getThumbup() + "%");
                list.add(predicate);
            }
            if (problem.getReply() != null) {
                Predicate predicate = criteriaBuilder.like(root.get("reply").as(String.class), "%" + problem.getReply() + "%");
                list.add(predicate);
            }
            if (!StringUtils.isEmpty(problem.getSolve())) {
                Predicate predicate = criteriaBuilder.like(root.get("solve").as(String.class), "%" + problem.getSolve() + "%");
                list.add(predicate);
            }
            if (!StringUtils.isEmpty(problem.getReplyname())) {
                Predicate predicate = criteriaBuilder.like(root.get("replyname").as(String.class), "%" + problem.getReplyname() + "%");
                list.add(predicate);
            }
            return criteriaBuilder.and(list.toArray(new Predicate[0]));
        });
    }

    /**
     * 问题分页
     *
     * @param problem prpblem
     * @param page    页码
     * @param size    页大小
     * @return Page
     */
    public Page<Problem> findByProblemPages(Problem problem, int page, int size) {
        List<Predicate> list = new ArrayList<>();
        return problemDao.findAll((Specification<Problem>) (root, criteriaQuery, criteriaBuilder) -> {
            if (!StringUtils.isEmpty(problem.getTitle())) {
                Predicate predicate = criteriaBuilder.like(root.get("title").as(String.class), "%" + problem.getTitle() + "%");
                list.add(predicate);
            }
            if (!StringUtils.isEmpty(problem.getContent())) {
                Predicate predicate = criteriaBuilder.like(root.get("content").as(String.class), "%" + problem.getContent() + "%");
                list.add(predicate);
            }
            if (problem.getCreatetime() != null) {
                Predicate predicate = criteriaBuilder.like(root.get("createtime").as(String.class), "%" + problem.getCreatetime() + "%");
                list.add(predicate);
            }
            if (problem.getUpdatetime() != null) {
                Predicate predicate = criteriaBuilder.like(root.get("updatetime").as(String.class), "%" + problem.getUpdatetime() + "%");
                list.add(predicate);
            }
            if (problem.getReplytime() != null) {
                Predicate predicate = criteriaBuilder.like(root.get("replytime").as(String.class), "%" + problem.getReplytime() + "%");
                list.add(predicate);
            }
            if (!StringUtils.isEmpty(problem.getUserid())) {
                Predicate predicate = criteriaBuilder.like(root.get("userid").as(String.class), "%" + problem.getUserid() + "%");
                list.add(predicate);
            }
            if (!StringUtils.isEmpty(problem.getNickname())) {
                Predicate predicate = criteriaBuilder.like(root.get("nickname").as(String.class), "%" + problem.getNickname() + "%");
                list.add(predicate);
            }
            if (problem.getVisits() != null) {
                Predicate predicate = criteriaBuilder.like(root.get("visits").as(String.class), "%" + problem.getVisits() + "%");
                list.add(predicate);
            }
            if (problem.getThumbup() != null) {
                Predicate predicate = criteriaBuilder.like(root.get("thumbup").as(String.class), "%" + problem.getThumbup() + "%");
                list.add(predicate);
            }
            if (problem.getReply() != null) {
                Predicate predicate = criteriaBuilder.like(root.get("reply").as(String.class), "%" + problem.getReply() + "%");
                list.add(predicate);
            }
            if (!StringUtils.isEmpty(problem.getSolve())) {
                Predicate predicate = criteriaBuilder.like(root.get("solve").as(String.class), "%" + problem.getSolve() + "%");
                list.add(predicate);
            }
            if (!StringUtils.isEmpty(problem.getReplyname())) {
                Predicate predicate = criteriaBuilder.like(root.get("replyname").as(String.class), "%" + problem.getReplyname() + "%");
                list.add(predicate);
            }
            return criteriaBuilder.and(list.toArray(new Predicate[0]));
        }, PageRequest.of(page - 1, size));
    }


}
