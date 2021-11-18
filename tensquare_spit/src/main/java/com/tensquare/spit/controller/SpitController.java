package com.tensquare.spit.controller;

import com.tensquare.entity.PageResult;
import com.tensquare.entity.Result;
import com.tensquare.entity.StatusCode;
import com.tensquare.spit.pojo.Spit;
import com.tensquare.spit.service.SpitService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author 华韵流风
 * @ClassName SpitController
 * @Date 2021/9/22 20:20
 * @packageName com.tensquare.spit.controller
 * @Description TODO
 */
@RestController
@RequestMapping("/spit")
@RefreshScope
public class SpitController {

    @Autowired
    private SpitService spitService;

    @Resource
    private RedisTemplate<String, Boolean> redisTemplate;

    @Autowired
    private HttpServletRequest request;

    /**
     * 添加spit
     *
     * @param spit spit
     * @return Result
     */
    @PostMapping
    public Result add(@RequestBody Spit spit) {
        try {
            //认证
            Claims claims = (Claims) request.getAttribute("claims_user");
            if (claims == null) {
                return new Result(StatusCode.NOPERMISSION, false, "权限不足");
            }
            spitService.add(spit);
            return new Result(StatusCode.OK, true, "添加成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result(StatusCode.FAILER, false, "添加失败");
    }

    /**
     * 修改spit
     *
     * @param spit   spit
     * @param spitId spitId
     * @return Result
     */
    @PutMapping("/{spitId}")
    public Result update(@PathVariable String spitId, @RequestBody Spit spit) {
        try {
            spitService.update(spitId, spit);
            return new Result(StatusCode.OK, true, "修改成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result(StatusCode.FAILER, false, "修改失败");
    }

    /**
     * 查询所有spit
     *
     * @return Result
     */
    @GetMapping
    public Result findAll() {
        try {
            return new Result(StatusCode.OK, true, "查询成功", spitService.findAll());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result(StatusCode.FAILER, false, "查询失败");
    }

    /**
     * 根据id查询spit
     *
     * @param spitId spitId
     * @return Result
     */
    @GetMapping("/{spitId}")
    public Result findById(@PathVariable String spitId) {
        try {
            return new Result(StatusCode.OK, true, "查询成功", spitService.findById(spitId));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result(StatusCode.FAILER, false, "查询失败");
    }

    /**
     * 删除spit
     *
     * @param spitId spitId
     * @return Result
     */
    @DeleteMapping("/{spitId}")
    public Result remove(@PathVariable String spitId) {
        try {
            spitService.deleteById(spitId);
            return new Result(StatusCode.OK, true, "删除成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result(StatusCode.FAILER, false, "删除失败");
    }

    /**
     * 根据上级ID查询吐槽数据（分页）
     *
     * @param parentid parentid
     * @param page     page
     * @param size     size
     * @return Result
     */
    @GetMapping("/comment/{parentid}/{page}/{size}")
    public Result findByParentId(@PathVariable String parentid, @PathVariable int page, @PathVariable int size) {
        try {
            Page<Spit> pageList = spitService.findByParentId(parentid, page, size);
            return new Result(StatusCode.OK, true, "查询成功", new PageResult<>(pageList.getTotalElements(), pageList.getContent()));
        } catch (Exception e) {
            return new Result(StatusCode.FAILER, false, "查询失败");
        }
    }

    /**
     * 吐槽点赞
     *
     * @param spitId spitId
     * @return Result
     */
    @PutMapping("/thumbup/{spitId}")
    public Result thumbUp(@PathVariable String spitId) {
        try {
            //不允许重复点赞，用户点赞后需要在redis中记录下来。
            Claims claims = (Claims) request.getAttribute("claims_user");
            String username = claims.getSubject();
            if (redisTemplate.opsForValue().get("spit_thumbUp_" + spitId + "_" + username) != null) {
                return new Result(StatusCode.FAILER, false, "重复点赞");
            }
            spitService.thumbUp(spitId);
            redisTemplate.opsForValue().set("spit_thumbUp_" + spitId + "_" + username, true);
            return new Result(StatusCode.OK, true, "点赞成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result(StatusCode.FAILER, false, "点赞失败");
    }

    /**
     * 条件查询
     *
     * @param spit spit
     * @return Result
     */
    @PostMapping("/search")
    public Result findBySpit(@RequestBody Spit spit) {
        try {
            return new Result(StatusCode.FAILER, true, "查询成功", spitService.findBySpit(spit));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result(StatusCode.FAILER, false, "查询失败");
    }

    /**
     * 条件查询
     *
     * @param spit spit
     * @param page page
     * @param size size
     * @return Result
     */
    @PostMapping("/search/{page}/{size}")
    public Result findBySpitPage(@RequestBody Spit spit, @PathVariable int page, @PathVariable int size) {
        try {
            Page<Spit> pageList = spitService.findBySpitPage(spit, page, size);
            return new Result(StatusCode.OK, true, "查询成功", new PageResult<>(pageList.getTotalElements(), pageList.getContent()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result(StatusCode.FAILER, false, "查询失败");
    }

}
