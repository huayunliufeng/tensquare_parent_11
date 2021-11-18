package com.tensquare.user.controller;

import com.tensquare.entity.Result;
import com.tensquare.entity.StatusCode;
import com.tensquare.user.pojo.User;
import com.tensquare.user.service.UserService;
import com.tensquare.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 华韵流风
 * @ClassName UserController
 * @Date 2021/10/9 11:07
 * @packageName com.tensquare.user.controller
 * @Description TODO
 */
@RestController
@RequestMapping("/user")
@RefreshScope
public class UserController {

    @Autowired
    private UserService userService;

    @Resource
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private JwtUtil jwtUtil;

    /**
     * 增加用户
     *
     * @param user user
     * @return Result
     */
    @PostMapping
    public Result add(@RequestBody User user) {
        try {
            user.setFollowcount(0);
            user.setFanscount(0);
            user.setOnline(0L);
            user.setRegdate(new Date());
            user.setUpdatedate(new Date());
            user.setLastdate(new Date());
            userService.add(user);
            return new Result(StatusCode.OK, true, "添加成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result(StatusCode.FAILER, false, "添加失败");
    }

    /**
     * 用户全部列表
     *
     * @return Result
     */
    @GetMapping
    public Result findAll() {
        try {
            return new Result(StatusCode.OK, true, "查询成功", userService.findAll());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result(StatusCode.FAILER, false, "查询失败");
    }

    /**
     * 根据ID查询Admin
     *
     * @param userId userId
     * @return Result
     */
    @GetMapping("/{userId}")
    public Result findById(@PathVariable String userId) {
        try {
            return new Result(StatusCode.OK, true, "查询成功", userService.findById(userId));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result(StatusCode.FAILER, false, "查询失败");
    }

    /**
     * 修改user
     *
     * @param userId userId
     * @param user   user
     * @return Result
     */
    @PutMapping("/{userId}")
    public Result update(@PathVariable String userId, @RequestBody User user) {
        try {
            userService.edit(user, userId);
            return new Result(StatusCode.OK, true, "修改成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result(StatusCode.FAILER, false, "修改失败");
    }

    /**
     * 删除user
     *
     * @param userId userId
     * @return Result
     */
    @DeleteMapping("/{userId}")
    public Result remove(@PathVariable String userId) {
        try {
            //实现用户身份认证
            Claims claims = (Claims) request.getAttribute("claims_admin");
            if (claims == null || !"admin".equals(claims.get("roles"))) {
                return new Result(StatusCode.NOPERMISSION, false, "权限不足");
            }
            userService.remove(userId);
            return new Result(StatusCode.OK, true, "删除成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result(StatusCode.FAILER, false, "删除失败");
    }

    /**
     * 发送手机验证码
     *
     * @param mobile mobile
     * @return Result
     */
    @PostMapping("/sendsms/{mobile}")
    public Result sendSms(@PathVariable String mobile) {
        try {
            userService.sendSms(mobile);
            return new Result(StatusCode.OK, true, "发送成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result(StatusCode.FAILER, false, "发送失败");
    }

    /**
     * 注册用户
     *
     * @param user user
     * @param code code
     * @return Result
     */
    @PostMapping("/register/{code}")
    public Result register(@RequestBody User user, @PathVariable String code) {
        try {
            String codeSave = redisTemplate.opsForValue().get("smscode_" + user.getMobile());
            if (!code.equals(codeSave)) {
                return new Result(StatusCode.FAILER, false, "验证码错误");
            }
            user.setFollowcount(0);
            user.setFanscount(0);
            user.setOnline(0L);
            user.setRegdate(new Date());
            user.setUpdatedate(new Date());
            user.setLastdate(new Date());
            userService.register(user);
            return new Result(StatusCode.OK, true, "注册成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result(StatusCode.FAILER, false, "注册失败");
    }

    /**
     * 用户登录
     *
     * @param user user
     * @return Result
     */
    @PostMapping("/login")
    public Result login(@RequestBody User user) {
        try {
            User login = userService.login(user.getMobile(), user.getPassword());

            if (login != null) {
                String token = jwtUtil.createJWT(login.getId(), login.getMobile(), "user");
                Map<String, String> map = new HashMap<>();
                map.put("token", token);
                map.put("name", user.getMobile());
                map.put("avatar", "");
                return new Result(StatusCode.OK, true, "登录成功", map);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result(StatusCode.FAILER, false, "登录失败");
    }

    /**
     * 获取用户信息
     *
     * @return Result
     */
    @GetMapping("/info")
    public Result getUserInfo() {
        try {
            Claims claims = (Claims) request.getAttribute("claims_user");
            if (claims == null) {
                return new Result(StatusCode.NOPERMISSION, false, "权限不足");
            }
            return new Result(StatusCode.OK, true, "获取成功", userService.findById(claims.getId()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result(StatusCode.FAILER, false, "获取失败");
    }

    /**
     * 增加粉丝数
     *
     * @param userid userid
     * @param x      x
     * @return Result
     */
    @PostMapping("/incfans/{userid}/{x}")
    public Result incFansCount(@PathVariable String userid, @PathVariable int x) {
        try {
            userService.incFansCount(userid, x);
            return new Result(StatusCode.OK, true, "更新成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result(StatusCode.FAILER, false, "更新失败");
    }

    /**
     * 增加关注数
     *
     * @param userid userid
     * @param x      x
     * @return Result
     */
    @PostMapping("/incfollow/{userid}/{x}")
    public Result incFollowCount(@PathVariable String userid, @PathVariable int x) {
        try {
            userService.incFollowCount(userid, x);
            return new Result(StatusCode.OK, true, "更新成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result(StatusCode.FAILER, false, "更新失败");
    }

    /**
     * 关注某用户
     *
     * @param userId userId
     * @return Result
     */
    @PutMapping("/follow/{userId}")
    public Result followUser(@PathVariable String userId) {

        try {
            Claims claims = (Claims) request.getAttribute("claims_user");
            if (claims == null) {
                return new Result(StatusCode.NOPERMISSION, false, "权限不足");
            }
            userService.followUser(claims.getId(), userId);
            return new Result(StatusCode.OK, true, "关注成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result(StatusCode.FAILER, false, "关注失败");
    }




}
