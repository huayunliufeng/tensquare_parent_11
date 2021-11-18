package com.tensquare.article.config;

import com.tensquare.article.interceptor.JJwtInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @author 华韵流风
 * @ClassName JJwtInterceptorConfig
 * @Date 2021/10/14 16:56
 * @packageName com.tensquare.spit.config
 * @Description TODO
 */
@Configuration
public class JJwtInterceptorConfig extends WebMvcConfigurationSupport {
    @Autowired
    private JJwtInterceptor jJwtInterceptor;

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jJwtInterceptor).addPathPatterns("/**");
    }
}
