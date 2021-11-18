package com.tensquare.gathering;

import com.tensquare.utils.IdWorker;
import com.tensquare.utils.JwtUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import javax.persistence.Id;

/**
 * @author 华韵流风
 * @ClassName GatheringApplication
 * @Date 2021/10/24 16:16
 * @packageName com.tensquare.gathering
 * @Description TODO
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class GatheringApplication {
    public static void main(String[] args) {
        SpringApplication.run(GatheringApplication.class);
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
