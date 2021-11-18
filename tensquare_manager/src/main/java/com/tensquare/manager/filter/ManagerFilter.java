package com.tensquare.manager.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.tensquare.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
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
public class ManagerFilter extends ZuulFilter {

    @Autowired
    private JwtUtil jwtUtil;

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

        if ("OPTIONS".equals(request.getMethod())) {
            return null;
        }
        String url = request.getRequestURL().toString();
        if (url.indexOf("/admin/login") > 0) {
            System.out.println("登陆页面" + url);
            return null;
        }
        //获取头信息
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            Claims claims = jwtUtil.parseJWT(token);
            if (claims != null) {
                if ("admin".equals(claims.get("roles"))) {
                    requestContext.addZuulRequestHeader("Authorization", authHeader);
                    System.out.println("token 验证通过，添加了头信息" + authHeader);
                    return null;
                }
            }
        }
//        //终止运行
//        requestContext.setSendZuulResponse(false);
//        //http状态码
//        requestContext.setResponseStatusCode(401);
//        requestContext.setResponseBody("无权访问");
//        requestContext.getResponse().setContentType("text/html;charset=UTF‐8");

        return null;
    }
}
