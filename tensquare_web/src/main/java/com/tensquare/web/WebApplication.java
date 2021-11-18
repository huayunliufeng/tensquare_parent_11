package com.tensquare.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @author 华韵流风
 * @ClassName WebApplication
 * @Date 2021/10/20 17:03
 * @packageName com.tensquare.web
 * @Description TODO
 */
@SpringBootApplication
@EnableZuulProxy
@EnableDiscoveryClient
public class WebApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class);
    }
}
