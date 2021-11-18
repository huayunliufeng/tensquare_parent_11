package com.tensquare.friend.dao;

import com.tensquare.friend.pojo.Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * @author 华韵流风
 * @ClassName FriendDao
 * @Date 2021/10/20 14:40
 * @packageName com.tensquare.friend.dao
 * @Description TODO
 */
public interface FriendDao extends JpaRepository<Friend, String> {


    /**
     * 根据用户ID与被关注用户ID查询记录个数
     *
     * @param userId   userId
     * @param friendId friendId
     * @return int
     */
    @Query("select count(f) from Friend f where f.userid=?1 and f.friendid=?2")
    int selectCount(String userId, String friendId);

    /**
     * 更新为相互喜欢
     *
     * @param userId   userId
     * @param friendId friendId
     * @param isLike   isLike
     */
    @Modifying
    @Query("update Friend f set f.islike=?3 where f.userid=?1 and f.friendid=?2")
    void updateLike(String userId, String friendId, String isLike);

    /**
     * 删除好友
     *
     * @param userId   userId
     * @param friendId friendId
     */
    @Modifying
    @Query("delete from Friend f where f.userid=?1 and f.friendid=?2")
    void deleteFriend(String userId, String friendId);

}
