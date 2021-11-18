package com.tensquare.gathering.interceptor;

import com.tensquare.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 华韵流风
 * @ClassName GatheringInterceptor
 * @Date 2021/10/24 17:09
 * @packageName com.tensquare.gathering.interceptor
 * @Description TODO
 */
@Component
public class GatheringInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private JwtUtil jwtUtil;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String auth = request.getHeader("Authorization");
        if (auth != null && auth.startsWith("Bearer ")) {
            String token = auth.substring(7);
            Claims claims = jwtUtil.parseJWT(token);
            if ("admin".equals(claims.get("roles"))) {
                request.setAttribute("claims_admin", claims);
            } else {
                request.setAttribute("claims_user", claims);
            }
        }
        return true;
    }
}
