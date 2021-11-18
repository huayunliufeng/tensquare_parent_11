package com.tensquare.friend.interceptor;

import com.tensquare.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 华韵流风
 * @ClassName JwtInterceptor
 * @Date 2021/10/20 14:47
 * @packageName com.tensquare.friend.interceptor
 * @Description TODO
 */
@Component
public class JwtInterceptor extends HandlerInterceptorAdapter {


    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String auth = request.getHeader("Authorization");
        if (auth != null && StringUtils.startsWith(auth, "Bearer ") && !"".equals(auth.substring(7))) {
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
