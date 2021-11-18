package com.tensquare.sms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import java.util.concurrent.Callable;

/**
 * @author 华韵流风
 * @ClassName SmsApplication
 * @Date 2021/10/9 14:46
 * @packageName com.tensquare.sms
 * @Description TODO
 */
@SpringBootApplication
@EnableDiscoveryClient
public class SmsApplication {
    public static void main(String[] args) {
        SpringApplication.run(SmsApplication.class);
    }
}

