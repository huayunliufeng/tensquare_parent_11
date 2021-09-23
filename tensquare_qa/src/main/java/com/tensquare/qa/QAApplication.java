package com.tensquare.qa;

import com.tensquare.utils.IdWorker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


/**
 * @author 华韵流风
 */
@SpringBootApplication
public class QAApplication {
    public static void main(String[] args) {
        SpringApplication.run(QAApplication.class);
    }
    @Bean
    public IdWorker idWorker(){
        return new IdWorker();
    }
}
