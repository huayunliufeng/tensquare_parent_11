package com.tensquare.user.interceptor;

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
 * @ClassName JJwtInterceptor
 * @Date 2021/10/14 15:22
 * @packageName com.tensquare.user.interceptor
 * @Description TODO
 */
@Component
public class JJwtInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //实现用户身份认证
        String auth = request.getHeader("Authorization");
        if (auth != null && StringUtils.startsWith(auth, "Bearer") && !"".equals(auth.substring(7))) {
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
