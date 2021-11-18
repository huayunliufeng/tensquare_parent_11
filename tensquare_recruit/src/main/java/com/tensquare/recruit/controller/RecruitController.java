package com.tensquare.recruit.controller;

import com.tensquare.entity.PageResult;
import com.tensquare.entity.Result;
import com.tensquare.entity.StatusCode;
import com.tensquare.recruit.pojo.Recruit;
import com.tensquare.recruit.service.RecruitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/recruit")
@RefreshScope
public class RecruitController {
    @Autowired
    private RecruitService recruitService;

    /**
     * 添加招聘
     *
     * @param recruit recruit
     * @return Result
     */
    @PostMapping
    public Result add(@RequestBody Recruit recruit) {
        try {
            recruitService.add(recruit);
            return new Result(StatusCode.OK, true, "添加成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result(StatusCode.FAILER, false, "添加失败");
    }

    /**
     * 根据id删除招聘
     *
     * @param recruitId recruitId
     * @return Result
     */
    @DeleteMapping("/{recruitId}")
    public Result remove(@PathVariable("recruitId") String recruitId) {
        try {
            recruitService.remove(recruitId);
            return new Result(StatusCode.OK, true, "删除成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result(StatusCode.FAILER, false, "删除失败");
    }

    /**
     * 根据id修改enterprise
     *
     * @param recruitId recruitId
     * @param recruit   recruit
     * @return Result
     */
    @PutMapping("/{recruitId}")
    public Result update(@PathVariable("recruitId") String recruitId, @RequestBody Recruit recruit) {
        try {
            recruitService.update(recruitId, recruit);
            return new Result(StatusCode.OK, true, "修改成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result(StatusCode.FAILER, false, "修改失败");
    }

    /**
     * 根据id查询recruit
     *
     * @param recruitId recruitId
     * @return Result
     */
    @GetMapping("/{recruitId}")
    public Result findById(@PathVariable("recruitId") String recruitId) {
        try {
            return new Result(StatusCode.OK, true, "查询成功", recruitService.findById(recruitId));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result(StatusCode.FAILER, false, "查询失败");
    }

    /**
     * 查询所有recruit
     *
     * @return Result
     */
    @GetMapping("")
    public Result findAll() {

        try {
            return new Result(StatusCode.OK, true, "查询成功", recruitService.findAll());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result(StatusCode.FAILER, false, "查询失败");
    }

    /**
     * 根据条件查询招聘列表
     *
     * @param recruit recruit
     * @return Result
     */
    @PostMapping("/search")
    public Result findByRecruit(@RequestBody Recruit recruit) {
        try {
            return new Result(StatusCode.OK, true, "查询成功", recruitService.findByRecruit(recruit));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result(StatusCode.FAILER, false, "查询失败");
    }

    /**
     * 根据条件分页查询招聘列表
     *
     * @param recruit recruit
     * @param page    page
     * @param size    size
     * @return Result
     */
    @PostMapping("/search/{page}/{size}")
    public Result findByRecruitPage(@RequestBody Recruit recruit, @PathVariable int page, @PathVariable int size) {
        try {
            Page<Recruit> pageList = recruitService.findByRecruitPage(recruit, page, size);
            return new Result(StatusCode.OK, true, "查询成功", new PageResult<Recruit>(pageList.getTotalElements(), pageList.getContent()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result(StatusCode.FAILER, false, "查询失败");
    }

    /**
     * 根据State查询招聘列表，按照时间降序
     *
     * @return Result
     */
    @GetMapping("/search/recommend")
    public Result findByStateOrderByCreatetimeDesc() {
        try {
            return new Result(StatusCode.OK, true, "查询成功", recruitService.findByStateOrderByCreatetimeDesc());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result(StatusCode.FAILER, false, "查询失败");
    }

    /**
     * 根据NotState查询招聘列表，按照时间降序
     *
     * @return Result
     */
    @GetMapping("/search/newlist")
    public Result findByStateNotOrderByCreatetime() {
        try {
            return new Result(StatusCode.OK, true, "查询成功", recruitService.findByStateNotOrderByCreatetimeDesc());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result(StatusCode.FAILER, false, "查询失败");
    }
}
