package com.tensquare.spit;

import com.tensquare.utils.IdWorker;
import com.tensquare.utils.JwtUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

/**
 * @author 华韵流风
 * @ClassName SpitApplication
 * @Date 2021/9/22 20:06
 * @packageName com.tensquare.spit
 * @Description TODO
 */
@SpringBootApplication
@EnableDiscoveryClient
public class SpitApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpitApplication.class);
    }
    @Bean
    public IdWorker idWorker(){
        return new IdWorker();
    }

    @Bean
    public JwtUtil jwtUtil(){
        return new JwtUtil();
    }
}
