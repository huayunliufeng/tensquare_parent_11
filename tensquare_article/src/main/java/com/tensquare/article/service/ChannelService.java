package com.tensquare.article.service;

import com.tensquare.article.dao.ChannelDao;
import com.tensquare.article.pojo.Channel;
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
 * @ClassName ChannelService
 * @Date 2021/9/18 14:09
 * @packageName com.tensquare.article.service
 * @Description TODO
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ChannelService {

    @Autowired
    private ChannelDao channelDao;

    @Autowired
    private IdWorker idWorker;

    /**
     * 添加channel
     *
     * @param channel channel
     */
    public void add(Channel channel) {
        channel.setId(String.valueOf(idWorker.nextId()));
        channelDao.save(channel);
    }

    /**
     * 根据id删除channel
     *
     * @param id id
     */
    public void remove(String id) {
        channelDao.deleteById(id);
    }

    /**
     * 根据id修改channel
     *
     * @param id      id
     * @param channel channel
     */
    public void update(String id, Channel channel) {
        channel.setId(id);
        channelDao.save(channel);
    }

    /**
     * 根据id查询channel
     *
     * @param id id
     * @return channel
     */
    public Channel findById(String id) {
        return channelDao.findById(id).get();
    }

    /**
     * 查询所有channel
     *
     * @return List<Channel>
     */
    public List<Channel> findAll() {
        return channelDao.findAll();
    }

    /**
     * 根据条件查询频道列表
     *
     * @param channel channel
     * @return List<Channel>
     */
    public List<Channel> findByChannel(Channel channel) {
        return channelDao.findAll(getSpecification(channel));
    }

    /**
     * 频道分页
     *
     * @param channel channel
     * @param page    page
     * @param size    size
     * @return Page<Channel>
     */
    public Page<Channel> findByChannelPage(Channel channel, int page, int size) {
        return channelDao.findAll(getSpecification(channel), PageRequest.of(page - 1, size));
    }

    private Specification<Channel> getSpecification(Channel channel) {
        List<Predicate> list = new ArrayList<>();
        return (Specification<Channel>) (root, criteriaQuery, criteriaBuilder) -> {
            if (!StringUtils.isEmpty(channel.getId())) {
                Predicate predicate = criteriaBuilder.like(root.get("id").as(String.class), "%" + channel.getId() + "%");
                list.add(predicate);
            }
            if (!StringUtils.isEmpty(channel.getName())) {
                Predicate predicate = criteriaBuilder.like(root.get("name").as(String.class), "%" + channel.getName() + "%");
                list.add(predicate);
            }
            if (!StringUtils.isEmpty(channel.getState())) {
                Predicate predicate = criteriaBuilder.like(root.get("state").as(String.class), channel.getState());
                list.add(predicate);
            }
            return criteriaBuilder.and(list.toArray(new Predicate[0]));
        };

    }
}
