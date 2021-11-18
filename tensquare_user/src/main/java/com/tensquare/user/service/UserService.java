package com.tensquare.user.service;

import com.tensquare.user.dao.UserDao;
import com.tensquare.user.pojo.User;
import com.tensquare.utils.IdWorker;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;


/**
 * @author 华韵流风
 * @Date 2021-10-09 10:48:19
 */
@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private BCryptPasswordEncoder encoder;

    /**
     * 根据id查询User
     *
     * @param id id
     * @return User
     */
    public User findById(String id) {
        return userDao.findById(id).get();
    }

    /**
     * 根据id删除User
     *
     * @param id id
     */
    @Transactional(rollbackFor = Exception.class)
    public void remove(String id) {
        userDao.deleteById(id);
    }

    /**
     * 新增User
     *
     * @param user user
     */
    @Transactional(rollbackFor = Exception.class)
    public void add(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        user.setId(String.valueOf(idWorker.nextId()));
        userDao.save(user);
    }

    /**
     * 修改User
     *
     * @param user   user
     * @param userId userId
     */
    @Transactional(rollbackFor = Exception.class)
    public void edit(User user, String userId) {
        user.setId(userId);
        user.setPassword(encoder.encode(user.getPassword()));
        userDao.save(user);
    }

    /**
     * 查询所有User
     *
     * @return List<User>
     */
    public List<User> findAll() {
        return userDao.findAll();
    }

    /**
     * 发送手机验证码
     *
     * @param phoneNumber phoneNumber
     */
    public void sendSms(String phoneNumber) {
        int min = 100000;
        int max = 999999;
        String code = String.valueOf(new Random().nextInt(max - min) + min);
        Map<String, String> map = new HashMap<>(0);
        map.put("phoneNumber", phoneNumber);
        map.put("code", code);
        rabbitTemplate.convertAndSend("sms", map);
    }

    /**
     * 注册用户
     *
     * @param user user
     */
    @Transactional(rollbackFor = Exception.class)
    public void register(User user) {
        user.setId(String.valueOf(idWorker.nextId()));
        user.setPassword(encoder.encode(user.getPassword()));
        userDao.save(user);
    }

    /**
     * 用户登录
     *
     * @param mobile   mobile
     * @param password password
     * @return User
     */
    public User login(String mobile, String password) {
        User user = userDao.findByMobile(mobile);
        if (user != null && encoder.matches(password, user.getPassword())) {
            return user;
        } else {
            return null;
        }
    }

    /**
     * 更新粉丝数
     *
     * @param userId userId
     * @param x      x
     */
    @Transactional(rollbackFor = Exception.class)
    public void incFansCount(String userId, int x) {
        userDao.incFansCount(userId, x);
    }

    /**
     * 更新关注数
     *
     * @param userId userId
     * @param x      x
     */
    @Transactional(rollbackFor = Exception.class)
    public void incFollowCount(String userId, int x) {
        userDao.incFollowCount(userId, x);
    }

    /**
     * 关注某用户
     *
     * @param myId myId
     * @param userId userId
     */
    @Transactional(rollbackFor = Exception.class)
    public void followUser(String myId,String userId){

    }
}
