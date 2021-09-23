package com.tensquare.qa.dao;

import com.tensquare.qa.pojo.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @author 华韵流风
 */
public interface ReplyDao extends JpaRepository<Reply,String>, JpaSpecificationExecutor<Reply> {
    List<Reply> findReplyByProblemid(String problemid);
}
