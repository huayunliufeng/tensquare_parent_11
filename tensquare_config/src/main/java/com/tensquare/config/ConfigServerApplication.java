package com.tensquare.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * @author 华韵流风
 * @ClassName ConfigServerApplication
 * @Date 2021/10/22 19:43
 * @packageName com.tensquare.config
 * @Description TODO
 */
@SpringBootApplication
@EnableConfigServer//开启配置服务
public class ConfigServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConfigServerApplication.class);
    }
}
