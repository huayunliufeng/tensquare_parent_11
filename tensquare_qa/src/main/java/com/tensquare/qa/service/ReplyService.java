package com.tensquare.qa.service;

import com.tensquare.qa.dao.ReplyDao;
import com.tensquare.qa.pojo.Reply;
import com.tensquare.utils.IdWorker;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ReplyService {
    @Autowired
    private ReplyDao replyDao;
    @Autowired
    private IdWorker idWorker;

    /**
     * 添加 回答
     *
     * @param reply reply
     */
    public void add(Reply reply) {
        reply.setCreatetime(new Date());
        reply.setId(String.valueOf(idWorker.nextId()));
        replyDao.save(reply);
    }

    /**
     * 修改回答
     *
     * @param id    id
     * @param reply reply
     */
    public void update(String id, Reply reply) {
        reply.setUpdatetime(new Date());
        reply.setId(id);
        replyDao.save(reply);
    }

    /**
     * 移除回答
     *
     * @param id id
     */
    public void remove(String id) {
        replyDao.deleteById(id);
    }

    /**
     * 通过 id 查找回答
     *
     * @param id id
     * @return Reply
     */
    public Reply findById(String id) {
        return replyDao.findById(id).get();
    }

    /**
     * 查询所有回答
     *
     * @return
     */
    public List<Reply> findAll() {
        return replyDao.findAll();

    }

    /**
     * 分页显示我的回答
     *
     * @param page 页码
     * @param size 页大小
     * @return Reply
     */
    public Page<Reply> myList(int page, int size) {
        return replyDao.findAll(PageRequest.of(page - 1, size));

    }

    /**
     * 通过问题id查询回答
     *
     * @param id id
     * @return Reply
     */
    public List<Reply> findReplyByProblemId(String id) {
        return replyDao.findReplyByProblemid(id);
    }

    /**
     * 回答分页
     *
     * @param reply
     * @param page
     * @param size
     * @return
     */
    public Page<Reply> findByReplyPage(Reply reply, int page, int size) {
        List<Predicate> list = new ArrayList<>();
        return replyDao.findAll((Specification<Reply>) (root, criteriaQuery, criteriaBuilder) -> {
            if (!StringUtils.isEmpty(reply.getContent())) {
                Predicate predicate = criteriaBuilder.like(root.get("content").as(String.class), "%" + reply.getContent() + "%");
                list.add(predicate);
            }
            if (reply.getCreatetime() != null) {
                Predicate predicate = criteriaBuilder.like(root.get("createtime").as(String.class), "%" + reply.getCreatetime() + "%");
                list.add(predicate);
            }
            if (reply.getUpdatetime() != null) {
                Predicate predicate = criteriaBuilder.like(root.get("updatetime").as(String.class), "%" + reply.getUpdatetime() + "%");
                list.add(predicate);
            }

            if (!StringUtils.isEmpty(reply.getUserid())) {
                Predicate predicate = criteriaBuilder.like(root.get("userid").as(String.class), "%" + reply.getUserid() + "%");
                list.add(predicate);
            }
            if (!StringUtils.isEmpty(reply.getNickname())) {
                Predicate predicate = criteriaBuilder.like(root.get("nickname").as(String.class), "%" + reply.getNickname() + "%");
                list.add(predicate);
            }
            return criteriaBuilder.and(list.toArray(new Predicate[0]));
        }, PageRequest.of(page - 1, size));
    }
}
