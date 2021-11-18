package com.tensquare.user.config;

import com.tensquare.user.interceptor.JJwtInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @author 华韵流风
 * @ClassName JwtInterceptorConfig
 * @Date 2021/10/14 15:32
 * @packageName com.tensquare.user.config
 * @Description TODO
 */
@Configuration
public class JwtInterceptorConfig extends WebMvcConfigurationSupport {

    @Autowired
    private JJwtInterceptor jJwtInterceptor;

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jJwtInterceptor).addPathPatterns("/**").excludePathPatterns("/*/login", "/*/register/**");
    }
}
