package com.tensquare.friend.service;

import com.tensquare.friend.client.UserClient;
import com.tensquare.friend.dao.FriendDao;
import com.tensquare.friend.dao.NoFriendDao;
import com.tensquare.friend.pojo.Friend;
import com.tensquare.friend.pojo.NoFriend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author 华韵流风
 * @ClassName FriendService
 * @Date 2021/10/20 15:17
 * @packageName com.tensquare.friend.service
 * @Description TODO
 */
@Service
public class FriendService {

    @Autowired
    private FriendDao friendDao;

    @Autowired
    private NoFriendDao noFriendDao;

    @Resource
    private UserClient userClient;

    @Transactional(rollbackFor = Exception.class)
    public int addFriend(String userId, String friendId) {
        //判断是否以添加该好友
        if (friendDao.selectCount(userId, friendId) > 0) {
            return 0;
        }
        Friend friend = new Friend();
        friend.setUserid(userId);
        friend.setFriendid(friendId);
        friend.setIslike("0");
        userClient.incFansCount(friendId, 1);
        userClient.incFollowCount(userId, 1);
        friendDao.save(friend);

        //判断是否互相喜欢
        if (friendDao.selectCount(friendId, userId) > 0) {
            friendDao.updateLike(userId, friendId, "1");
            friendDao.updateLike(friendId, userId, "1");
        }

        return 1;
    }

    /**
     * 添加不喜欢的好友
     *
     * @param userId   userId
     * @param friendId friendId
     */
    @Transactional(rollbackFor = Exception.class)
    public void addNoFriend(String userId, String friendId) {
        NoFriend noFriend = new NoFriend();
        noFriend.setUserid(userId);
        noFriend.setFriendid(friendId);
        noFriendDao.save(noFriend);
    }

    /**
     * 删除好友
     *
     * @param userId   userId
     * @param friendId friendId
     */
    @Transactional(rollbackFor = Exception.class)
    public void deleteFriend(String userId, String friendId) {
        userClient.incFansCount(friendId, -1);
        userClient.incFollowCount(userId, -1);
        friendDao.deleteFriend(userId, friendId);
        friendDao.updateLike(userId, friendId, "0");
        addNoFriend(userId, friendId);
    }
}
