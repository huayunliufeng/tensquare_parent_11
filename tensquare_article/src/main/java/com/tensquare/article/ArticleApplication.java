package com.tensquare.article;

import com.tensquare.utils.IdWorker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @author 华韵流风
 * @ClassName ArticleApplication
 * @Date 2021/9/16 21:34
 * @packageName com.tensquare.base
 * @Description TODO
 */
@SpringBootApplication
public class ArticleApplication {
    public static void main(String[] args) {
        SpringApplication.run(ArticleApplication.class);
    }


    @Bean
    public IdWorker idWorker(){
        return new IdWorker();
    }
}
