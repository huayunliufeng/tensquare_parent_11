package com.tensquare.qa.config;

import com.tensquare.qa.interecptor.JjwtInterceptor;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @author 华韵流风
 * @ClassName JJwtInterceptorConfig
 * @Date 2021/10/14 16:24
 * @packageName com.tensquare.qa.config
 * @Description TODO
 */
@Configuration
public class JJwtInterceptorConfig extends WebMvcConfigurationSupport {

    @Autowired
    private JjwtInterceptor jjwtInterceptor;

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jjwtInterceptor).addPathPatterns("/**");
    }
}
