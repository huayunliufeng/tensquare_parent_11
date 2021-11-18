package com.tensquare.user.dao;

import com.tensquare.user.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * @author 华韵流风
 * @Date 2021-10-09 10:48:19
 */
public interface UserDao extends JpaRepository<User, String> {

    /**
     * 用户登录
     *
     * @param mobile mobile
     * @return User
     */
    User findByMobile(String mobile);

    /**
     * 更新粉丝数
     *
     * @param userid userid
     * @param x      x
     */
    @Modifying
    @Query("update User u set u.fanscount=u.fanscount+?2 where u.id=?1")
    void incFansCount(String userid, int x);

    /**
     * 更新关注数
     *
     * @param userid userid
     * @param x      x
     */
    @Modifying
    @Query("update User u set u.followcount=u.followcount+?2 where u.id=?1")
    void incFollowCount(String userid, int x);
}
