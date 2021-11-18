package com.tensquare.qa.client;

import com.tensquare.entity.Result;
import com.tensquare.qa.client.impl.BaseClientImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author 华韵流风
 * @ClassName BaseClient
 * @Date 2021/10/19 19:33
 * @packageName com.tensquare.qa.client
 * @Description TODO
 */
@FeignClient(value = "tensquare-base", fallback = BaseClientImpl.class)
public interface BaseClient {

    /**
     * 根据id查询label
     *
     * @param labelId labelId
     * @return Result
     */
    @GetMapping("/base/label/{labelId}")
    Result findById(@PathVariable("labelId") String labelId);

    /**
     * 查询所有label
     *
     * @return Result
     */
    @GetMapping
    Result findAll();
}
