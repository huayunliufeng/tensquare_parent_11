package com.tensquare.article;

import com.tensquare.utils.IdWorker;
import com.tensquare.utils.JwtUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

/**
 * @author 华韵流风
 * @ClassName ArticleApplication
 * @Date 2021/9/16 21:34
 * @packageName com.tensquare.base
 * @Description TODO
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ArticleApplication {
    public static void main(String[] args) {
        SpringApplication.run(ArticleApplication.class);
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
