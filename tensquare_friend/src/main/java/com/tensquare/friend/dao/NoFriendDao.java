package com.tensquare.friend.dao;

import com.tensquare.friend.pojo.Friend;
import com.tensquare.friend.pojo.NoFriend;
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
public interface NoFriendDao extends JpaRepository<NoFriend, String> {
}
