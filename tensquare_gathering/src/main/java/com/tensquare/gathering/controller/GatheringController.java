package com.tensquare.gathering.controller;

import com.tensquare.entity.PageResult;
import com.tensquare.entity.Result;
import com.tensquare.entity.StatusCode;
import com.tensquare.gathering.pojo.Gathering;
import com.tensquare.gathering.service.GatheringService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 华韵流风
 * @ClassName GatheringController
 * @Date 2021/10/24 17:03
 * @packageName com.tensquare.gathering.controller
 * @Description TODO
 */
@RestController
@RequestMapping("/gathering")
@RefreshScope
public class GatheringController {

    @Autowired
    private GatheringService gatheringService;

    @Autowired
    private HttpServletRequest request;

    /**
     * 添加活动
     *
     * @param gathering gathering
     * @return Result
     */
    @PostMapping
    public Result add(@RequestBody Gathering gathering) {
        try {

            Claims claims = (Claims) request.getAttribute("claims_admin");
            if (claims == null) {
                return new Result(StatusCode.NOPERMISSION, false, "权限不足");
            }
            gatheringService.add(gathering);
            return new Result(StatusCode.OK, true, "添加成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result(StatusCode.FAILER, false, "添加失败");
    }


    /**
     * 查询全部活动
     *
     * @return Result
     */
    @GetMapping
    public Result findAll() {
        try {
            return new Result(StatusCode.OK, true, "查询成功", gatheringService.findAll());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result(StatusCode.FAILER, false, "查询失败");
    }

    /**
     * 根据id查询gathering
     *
     * @param gatheringId gatheringId
     * @return Result
     */
    @GetMapping("/{gatheringId}")
    public Result findById(@PathVariable String gatheringId) {
        try {
            return new Result(StatusCode.OK, true, "查询成功", gatheringService.findById(gatheringId));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result(StatusCode.FAILER, false, "查询失败");
    }

    /**
     * 修改gathering
     *
     * @param gatheringId gatheringId
     * @param gathering   gathering
     * @return Result
     */
    @PutMapping("/{gatheringId}")
    public Result update(@PathVariable String gatheringId, @RequestBody Gathering gathering) {
        try {
            Claims claims = (Claims) request.getAttribute("claims_admin");
            if (claims == null) {
                return new Result(StatusCode.NOPERMISSION, false, "权限不足");
            }
            gathering.setId(gatheringId);
            gatheringService.edit(gathering);
            return new Result(StatusCode.OK, true, "修改成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result(StatusCode.FAILER, false, "修改失败");
    }


    /**
     * 根据id删除gathering
     *
     * @param gatheringId gatheringId
     * @return Result
     */
    @DeleteMapping("/{gatheringId}")
    public Result delete(@PathVariable String gatheringId) {
        try {
            Claims claims = (Claims) request.getAttribute("claims_admin");
            if (claims == null) {
                return new Result(StatusCode.NOPERMISSION, false, "权限不足");
            }
            gatheringService.remove(gatheringId);
            return new Result(StatusCode.OK, true, "删除成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result(StatusCode.FAILER, false, "删除失败");
    }

    /**
     * 根据条件查询gathering
     *
     * @param gathering gathering
     * @return Result
     */
    @PostMapping("/search")
    public Result findByGathering(@RequestBody Gathering gathering) {
        try {
            return new Result(StatusCode.OK, true, "查询成功", gatheringService.findByGathering(gathering));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result(StatusCode.FAILER, false, "查询失败");
    }

    /**
     * 活动分页
     *
     * @param gathering gathering
     * @param page      page
     * @param size      size
     * @return Result
     */
    @PostMapping("/search/{page}/{size}")
    public Result findByGatheringPage(@RequestBody Gathering gathering, @PathVariable int page, @PathVariable int size) {
        try {
            Page<Gathering> pageList = gatheringService.findByGatheringPage(gathering, page, size);
            return new Result(StatusCode.OK, true, "查询成功", new PageResult<>(pageList.getTotalElements(), pageList.getContent()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result(StatusCode.FAILER, false, "查询失败");
    }

    /**
     * 根据城市显示分页内容
     *
     * @param city city
     * @param page page
     * @param size size
     * @return Result
     */
    @GetMapping("/city/{city}/{page}/{size}")
    public Result findByCity(@PathVariable String city, @PathVariable int page, @PathVariable int size) {
        try {
            Page<Gathering> pageList = gatheringService.findByCity(city, page, size);
            return new Result(StatusCode.OK, true, "查询成功", new PageResult<>(pageList.getTotalElements(), pageList.getContent()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result(StatusCode.FAILER, false, "删除失败");
    }

}
