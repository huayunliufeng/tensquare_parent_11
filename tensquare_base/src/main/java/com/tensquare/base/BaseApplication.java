package com.tensquare.base;

import com.tensquare.utils.IdWorker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 华韵流风
 * @ClassName BaseApplication
 * @Date 2021/9/16 21:34
 * @packageName com.tensquare.base
 * @Description TODO
 */
@SpringBootApplication
@EnableDiscoveryClient
public class BaseApplication {
    public static void main(String[] args) {
        SpringApplication.run(BaseApplication.class);
    }


    @Bean
    public IdWorker idWorker(){
        return new IdWorker();
    }
}
