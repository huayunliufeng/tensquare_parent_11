package com.tensquare.spit.service;

import com.tensquare.spit.dao.SpitDao;
import com.tensquare.spit.pojo.Spit;
import com.tensquare.utils.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author 华韵流风
 * @ClassName SpitService
 * @Date 2021/9/22 20:24
 * @packageName com.tensquare.spit.service
 * @Description TODO
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SpitService {

    @Autowired
    private SpitDao spitDao;

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 添加spit
     *
     * @param spit spit
     */
    public void add(Spit spit) {
        spit.setVisits(0);
        spit.setShare(0);
        spit.setThumbup(0);
        spit.setComment(0);
        spit.set_id(String.valueOf(idWorker.nextId()));
        spitDao.save(spit);
    }

    /**
     * 修改spit
     *
     * @param id   id
     * @param spit spit
     */
    public void update(String id, Spit spit) {
        spit.set_id(id);
        spitDao.save(spit);
    }

    /**
     * 删除spit
     *
     * @param id id
     */
    public void deleteById(String id) {
        spitDao.deleteById(id);
    }

    /**
     * 查询所有spit
     *
     * @return List<Spit>
     */
    public List<Spit> findAll() {
        return spitDao.findAll();
    }

    /**
     * 根据id查询
     *
     * @param id id
     * @return Spit
     */
    public Spit findById(String id) {
        return spitDao.findById(id).get();
    }

    /**
     * 根据上级ID查询吐槽数据（分页）
     *
     * @param parentId pagentId
     * @param page     page
     * @param size     size
     * @return Page<Spit>
     */
    public Page<Spit> findByParentId(String parentId, int page, int size) {
        return spitDao.findByParentid(parentId, PageRequest.of(page - 1, size));
    }

    /**
     * 吐槽点赞
     *
     * @param spitId spitId
     */
    public void thumbUp(String spitId) {
        //查询本id的记录，然后点赞数+1
        /*Spit spit = spitDao.findById(spitId).get();
        spit.setThumbup(spit.getThumbup()+1);
        spitDao.save(spit);*/
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(spitId));
        Update update = new Update();
        update.inc("thumbup");
        mongoTemplate.updateFirst(query, update, Spit.class);
    }

    /**
     * 条件查询
     *
     * @param spit spit
     * @return List<Spit>
     */
    public List<Spit> findBySpit(Spit spit) {
        Query query = new Query();
        //构建对象
        ExampleMatcher matcher = ExampleMatcher.matching()
                //改变默认字符串匹配方式：模糊查询
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
                //改变默认大小写忽略方式：忽略大小写
                .withIgnoreCase(true)
                //标题采用“包含匹配”的方式查询
                .withMatcher("content", ExampleMatcher.GenericPropertyMatchers.contains())
                //忽略属性，不参与查询
                .withIgnorePaths("_id", "parentid", "visits", "thumbup", "share", "comment");
        Example<Spit> example = Example.of(spit, matcher);
        query.addCriteria(Criteria.byExample(example));
        return mongoTemplate.find(query, Spit.class);
    }

    /**
     * 分页查询
     *
     * @param spit spit
     * @param page page
     * @param size size
     * @return List<Spit>
     */
    public Page<Spit> findBySpitPage(Spit spit, int page, int size) {
        Query query = new Query();
        Sort sort = Sort.by(Sort.Direction.ASC, "_id");
        Pageable pageable = PageRequest.of(page - 1, size, sort);
        //构建对象
        ExampleMatcher matcher = ExampleMatcher.matching()
                //改变默认字符串匹配方式：模糊查询
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
                //改变默认大小写忽略方式：忽略大小写
                .withIgnoreCase(true)
                //标题采用“包含匹配”的方式查询
                .withMatcher("content", ExampleMatcher.GenericPropertyMatchers.contains())
                //忽略属性，不参与查询
                .withIgnorePaths("_id", "parentid", "visits", "thumbup", "share", "comment");
        Example<Spit> example = Example.of(spit, matcher);

        query.addCriteria(Criteria.byExample(example));

        List<Spit> spits = mongoTemplate.find(query.with(pageable), Spit.class);
        long total = mongoTemplate.count(query, Spit.class);
        return new PageImpl<>(spits, pageable, total);
    }

}
