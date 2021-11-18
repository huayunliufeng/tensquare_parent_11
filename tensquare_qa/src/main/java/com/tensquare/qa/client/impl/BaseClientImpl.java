package com.tensquare.qa.client.impl;

import com.tensquare.entity.Result;
import com.tensquare.entity.StatusCode;
import com.tensquare.qa.client.BaseClient;
import org.springframework.stereotype.Component;

/**
 * @author 华韵流风
 * @ClassName BaseClientImpl
 * @Date 2021/10/19 19:35
 * @packageName com.tensquare.qa.client
 * @Description TODO
 */
@Component
public class BaseClientImpl implements BaseClient {

    @Override
    public Result findById(String labelId) {
        return new Result(StatusCode.FAILER,false,"该服务暂时无法访问，请稍后再试……");
    }

    @Override
    public Result findAll() {
        return new Result(StatusCode.FAILER,false,"该服务暂时无法访问，请稍后再试……");
    }
}
