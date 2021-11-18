package com.tensquare.user.controller;

import com.tensquare.entity.PageResult;
import com.tensquare.entity.Result;
import com.tensquare.entity.StatusCode;
import com.tensquare.user.pojo.Admin;
import com.tensquare.user.service.AdminService;
import com.tensquare.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 华韵流风
 * @ClassName AdminController
 * @Date 2021/10/9 11:08
 * @packageName com.tensquare.user.controller
 * @Description TODO
 */
@RestController
@RequestMapping("/admin")
@RefreshScope
public class AdminController {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AdminService adminService;

    /**
     * 增加管理员
     *
     * @param admin admin
     * @return Result
     */
    @PostMapping
    public Result add(@RequestBody Admin admin) {
        try {
            adminService.add(admin);
            return new Result(StatusCode.OK, true, "添加成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result(StatusCode.FAILER, false, "添加失败");
    }


    /**
     * 管理员全部列表
     *
     * @return Result
     */
    @GetMapping
    public Result findAll() {
        try {
            return new Result(StatusCode.OK, true, "查询成功", adminService.findAll());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result(StatusCode.FAILER, false, "查询失败");
    }

    /**
     * 根据ID查询Admin
     *
     * @param adminId adminId
     * @return Result
     */
    @GetMapping("/{adminId}")
    public Result findById(@PathVariable String adminId) {
        try {
            return new Result(StatusCode.OK, true, "查询成功", adminService.findById(adminId));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result(StatusCode.FAILER, false, "查询失败");
    }

    /**
     * 修改管理员
     *
     * @param adminId adminId
     * @param admin   admin
     * @return Result
     */
    @PutMapping("/{adminId}")
    public Result update(@PathVariable String adminId, @RequestBody Admin admin) {
        try {
            adminService.edit(admin, adminId);
            return new Result(StatusCode.OK, true, "修改成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result(StatusCode.FAILER, false, "修改失败");
    }

    /**
     * 删除admin
     *
     * @param adminId adminId
     * @return Result
     */
    @DeleteMapping("/{adminId}")
    public Result remove(@PathVariable String adminId) {
        try {
            adminService.remove(adminId);
            return new Result(StatusCode.OK, true, "删除成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result(StatusCode.FAILER, false, "删除失败");
    }

    /**
     * 管理员登录
     *
     * @param admin admin
     * @return Result
     */
    @PostMapping("/login")
    public Result login(@RequestBody Admin admin) {
        try {
            Admin login = adminService.login(admin.getLoginname(), admin.getPassword());
            if (login != null) {
                //生成token并返回给前端
                String token = jwtUtil.createJWT(login.getId(), login.getLoginname(), "admin");
                Map<String, String> map = new HashMap<>();
                map.put("token", token);
                map.put("name", admin.getLoginname());
                map.put("avatar", "");
                return new Result(StatusCode.OK, true, "登录成功", map);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result(StatusCode.USERANDPASSWORDERROR, false, "登录失败");
    }

    /**
     * 管理员分页
     *
     * @param page  page
     * @param size  size
     * @param admin admin
     * @return Result
     */
    @PostMapping("/search/{page}/{size}")
    public Result findByAdminPage(@PathVariable int page, @PathVariable int size, @RequestBody Admin admin) {
        try {
            Page<Admin> pageList = adminService.findByAdminPage(admin, page, size);
            return new Result(StatusCode.OK, true, "查询成功", new PageResult<>(pageList.getTotalElements(), pageList.getContent()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result(StatusCode.FAILER, false, "查询失败");
    }

    /**
     * 获取管理员信息
     *
     * @param token token
     * @return Result
     */
    @GetMapping("/info")
    public Result getInfo(@RequestParam String token) {
        try {
            Claims claims = jwtUtil.parseJWT(token);
            if (claims == null) {
                return new Result(StatusCode.NOPERMISSION, false, "权限不足");
            }
            Admin admin = adminService.findById(claims.getId());
            if (admin == null) {
                return new Result(StatusCode.FAILER, false, "账号或密码错误");
            }
            Map<String, String> map = new HashMap<>();
            map.put("token", token);
            map.put("name", admin.getLoginname());
            map.put("avatar", "");
            return new Result(StatusCode.OK, true, "获取成功", map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result(StatusCode.OK, false, "登录失败");
    }

    /**
     * 登出
     *
     * @return Result
     */
    @GetMapping("/logout")
    public Result logout() {
        return new Result(StatusCode.OK, true, "成功");
    }

}
