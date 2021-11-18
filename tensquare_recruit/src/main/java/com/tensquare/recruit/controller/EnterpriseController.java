package com.tensquare.recruit.controller;

import com.tensquare.entity.PageResult;
import com.tensquare.entity.Result;
import com.tensquare.entity.StatusCode;
import com.tensquare.recruit.pojo.Enterprise;
import com.tensquare.recruit.service.EnterpriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

/**
 * @author 华韵流风
 */
@RestController
@RequestMapping("/enterprise")
@RefreshScope
public class EnterpriseController {
    @Autowired
    private EnterpriseService enterpriseService;

    /**
     * 添加企业
     *
     * @param enterprise enterprise
     * @return Result
     */
    @PostMapping("")
    public Result add(@RequestBody Enterprise enterprise) {
        try {
            enterpriseService.add(enterprise);
            return new Result(StatusCode.OK, true, "添加成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result(StatusCode.FAILER, false, "添加失败");
    }

    /**
     * 根据id删除企业
     *
     * @param enterpriseId enterpriseId
     * @return Result
     */
    @DeleteMapping("/{enterpriseId}")
    public Result remove(@PathVariable("enterpriseId") String enterpriseId) {
        try {
            enterpriseService.remove(enterpriseId);
            return new Result(StatusCode.OK, true, "删除成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result(StatusCode.FAILER, false, "删除失败");
    }

    /**
     * 根据id修改enterprise
     *
     * @param enterpriseId enterpriseId
     * @param enterprise   enterprise
     * @return Result
     */
    @PutMapping("/{enterpriseId}")
    public Result update(@PathVariable("enterpriseId") String enterpriseId, @RequestBody Enterprise enterprise) {
        try {
            enterpriseService.update(enterpriseId, enterprise);
            return new Result(StatusCode.OK, true, "修改成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result(StatusCode.FAILER, false, "修改失败");
    }

    /**
     * 根据id查询enterprise
     *
     * @param enterpriseId enterpriseId
     * @return Result
     */
    @GetMapping("/{enterpriseId}")
    public Result findById(@PathVariable("enterpriseId") String enterpriseId) {
        try {
            return new Result(StatusCode.OK, true, "查询成功", enterpriseService.findById(enterpriseId));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result(StatusCode.FAILER, false, "查询失败");
    }

    /**
     * 查询所有enterprise
     *
     * @return Result
     */
    @GetMapping("")
    public Result findAll() {
        try {
            return new Result(StatusCode.OK, true, "查询成功", enterpriseService.findAll());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result(StatusCode.FAILER, false, "查询失败");
    }

    /**
     * 根据ishot查询enterprise
     *
     * @return Result
     */
    @GetMapping("/search/hotlist")
    public Result findByIshot() {
        try {
            return new Result(StatusCode.OK, true, "查询成功", enterpriseService.findByIshot());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result(StatusCode.FAILER, false, "查询失败");
    }

    /**
     * 根据条件查询企业列表
     *
     * @param enterprise enterprise
     * @return Result
     */
    @PostMapping("/search")
    public Result findByEnterprise(@RequestBody Enterprise enterprise) {
        try {
            return new Result(StatusCode.OK, true, "查询成功", enterpriseService.findByEnterprise(enterprise));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result(StatusCode.FAILER, false, "查询失败");
    }

    /**
     * 根据条件分页查询企业列表
     *
     * @param enterprise enterprise
     * @param page       page
     * @param size       size
     * @return Result
     */
    @PostMapping("/search/{page}/{size}")
    public Result findByLabelPage(@RequestBody Enterprise enterprise, @PathVariable int page, @PathVariable int size) {
        try {
            Page<Enterprise> pageList = enterpriseService.findByEnterprisePage(enterprise, page, size);
            return new Result(StatusCode.OK, true, "查询成功", new PageResult<>(pageList.getTotalElements(), pageList.getContent()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result(StatusCode.FAILER, false, "查询失败");
    }
}
