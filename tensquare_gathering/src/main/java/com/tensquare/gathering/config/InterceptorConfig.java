package com.tensquare.gathering.config;

import com.tensquare.gathering.interceptor.GatheringInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @author 华韵流风
 * @ClassName InterceptorConfig
 * @Date 2021/10/24 17:15
 * @packageName com.tensquare.gathering.config
 * @Description TODO
 */
@Configuration
public class InterceptorConfig extends WebMvcConfigurationSupport {

    @Autowired
    private GatheringInterceptor gatheringInterceptor;

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(gatheringInterceptor).addPathPatterns("/**");
    }
}
