package com.tensquare.friend.client.impl;

import com.tensquare.entity.Result;
import com.tensquare.entity.StatusCode;
import com.tensquare.friend.client.UserClient;
import org.springframework.stereotype.Component;

/**
 * @author 华韵流风
 * @ClassName UserClientImpl
 * @Date 2021/10/20 16:27
 * @packageName com.tensquare.friend.client.impl
 * @Description TODO
 */
@Component
public class UserClientImpl implements UserClient {
    @Override
    public Result incFansCount(String userid, int x) {
        return new Result(StatusCode.FAILER, false, "该服务暂时无法使用，请稍后再试……");
    }

    @Override
    public Result incFollowCount(String userid, int x) {
        return new Result(StatusCode.FAILER, false, "该服务暂时无法使用，请稍后再试……");
    }
}
