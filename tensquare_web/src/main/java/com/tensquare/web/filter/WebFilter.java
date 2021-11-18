package com.tensquare.web.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 华韵流风
 * @ClassName WebFilter
 * @Date 2021/10/20 17:11
 * @packageName com.tensquare.web.filter
 * @Description TODO
 */
@Component
public class WebFilter extends ZuulFilter {
    @Override
    public String filterType() {
//        pre ：可以在请求被路由之前调用
//        route ：在路由请求时候被调用
//        post ：在route和error过滤器之后被调用
//        error ：处理请求时发生错误时被调用
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        System.out.println("zuul的过滤器已执行……");

        //向header中添加鉴权令牌
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        String authorization = request.getHeader("Authorization");
        if (authorization != null) {
            requestContext.addZuulRequestHeader("Authorization", authorization);

        }
        return null;
    }
}
