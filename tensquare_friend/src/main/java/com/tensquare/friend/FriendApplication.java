package com.tensquare.friend;

import com.tensquare.utils.IdWorker;
import com.tensquare.utils.JwtUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

/**
 * @author 华韵流风
 * @ClassName FriendApplication
 * @Date 2021/10/19 20:43
 * @packageName com.tensquare.friend
 * @Description TODO
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class FriendApplication {
    public static void main(String[] args) {
        SpringApplication.run(FriendApplication.class);
    }

    @Bean
    public IdWorker idWorker() {
        return new IdWorker();
    }

    @Bean
    public JwtUtil jwtUtil() {
        return new JwtUtil();
    }
}
