package com.tensquare.spit.dao;

import com.tensquare.spit.pojo.Spit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;


/**
 * @author 华韵流风
 * @ClassName SpitDao
 * @Date 2021/9/22 20:21
 * @packageName com.tensquare.spit.dao
 * @Description TODO
 */
public interface SpitDao extends MongoRepository<Spit, String> {
    /**
     * 根据上级id查询spit
     *
     * @param parentId parentId
     * @param pageable pageable
     * @return Page<Spit>
     */
    Page<Spit> findByParentid(String parentId, Pageable pageable);


}
