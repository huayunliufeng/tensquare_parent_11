package com.tensquare.article.dao;

import com.tensquare.article.pojo.Channel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author 华韵流风
 * @ClassName ChannelDao
 * @Date 2021/9/18 14:08
 * @packageName com.tensquare.article.dao
 * @Description TODO
 */
public interface ChannelDao extends JpaRepository<Channel,String>, JpaSpecificationExecutor<Channel> {
}
