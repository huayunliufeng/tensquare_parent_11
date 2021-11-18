package com.tensquare.friend.client;

import com.tensquare.entity.Result;
import com.tensquare.friend.client.impl.UserClientImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author 华韵流风
 * @ClassName UserClient
 * @Date 2021/10/20 16:26
 * @packageName com.tensquare.friend.client
 * @Description TODO
 */
@FeignClient(value = "tensquare-user",fallback = UserClientImpl.class)
public interface UserClient {

    /**
     * 增加粉丝数
     *
     * @param userid userid
     * @param x      x
     * @return Result
     */
    @PostMapping("/user/user/incfans/{userid}/{x}")
    Result incFansCount(@PathVariable String userid, @PathVariable int x);


    /**
     * 增加关注数
     *
     * @param userid userid
     * @param x      x
     * @return Result
     */
    @PostMapping("/user/user/incfollow/{userid}/{x}")
    Result incFollowCount(@PathVariable String userid, @PathVariable int x);
}
