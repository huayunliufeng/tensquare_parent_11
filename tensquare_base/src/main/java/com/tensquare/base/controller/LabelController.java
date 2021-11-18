package com.tensquare.base.controller;

import com.tensquare.base.pojo.Label;
import com.tensquare.base.service.LabelService;
import com.tensquare.entity.PageResult;
import com.tensquare.entity.Result;
import com.tensquare.entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

/**
 * @author 华韵流风
 * @ClassName LabelController
 * @Date 2021/9/17 17:38
 * @packageName com.tensquare.base.controller
 * @Description TODO
 */
@RestController
@RequestMapping("/label")
@RefreshScope
public class LabelController {

    @Autowired
    private LabelService labelService;

    /**
     * 添加label
     *
     * @param label label
     * @return Result
     */
    @PostMapping
    public Result add(@RequestBody Label label) {
        try {
            labelService.add(label);
            return new Result(StatusCode.OK, true, "添加成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result(StatusCode.FAILER, false, "添加失败");
    }

    /**
     * 删除
     *
     * @param labelId labelId
     * @return Result
     */
    @DeleteMapping("/{labelId}")
    public Result remove(@PathVariable("labelId") String labelId) {
        try {
            labelService.remove(labelId);
            return new Result(StatusCode.OK, true, "删除成功");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result(StatusCode.FAILER, false, "删除失败");

    }

    /**
     * 根据id修改label
     *
     * @param labelId labelId
     * @param label   label
     * @return Result
     */
    @PutMapping("/{labelId}")
    public Result update(@PathVariable("labelId") String labelId, @RequestBody Label label) {
        try {
            labelService.update(labelId, label);
            return new Result(StatusCode.OK, true, "修改成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result(StatusCode.FAILER, false, "修改失败");
    }

    /**
     * 根据id查询label
     *
     * @param labelId labelId
     * @return Result
     */
    @GetMapping("/{labelId}")
    public Result findById(@PathVariable("labelId") String labelId) {
        try {
            return new Result(StatusCode.OK, true, "查询成功", labelService.findById(labelId));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result(StatusCode.FAILER, false, "查询失败");

    }

    /**
     * 查询所有label
     *
     * @return Result
     */
    @GetMapping
    public Result findAll() {
        try {
            return new Result(StatusCode.OK, true, "查询成功", labelService.findAll());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result(StatusCode.FAILER, false, "查询失败");
    }


    /**
     * 标签综合查询
     *
     * @param label label
     * @return Result
     */
    @PostMapping("/search")
    public Result findByLabel(@RequestBody Label label) {
        try {
            return new Result(StatusCode.OK, true, "查询成功", labelService.findByLabel(label));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result(StatusCode.FAILER, false, "查询失败");
    }

    /**
     * 标签分页
     *
     * @param label label
     * @param page  page
     * @param size  size
     * @return Result
     */
    @PostMapping("/search/{page}/{size}")
    public Result findByLabel(@RequestBody Label label, @PathVariable("page") int page, @PathVariable("size") int size) {
        try {
            Page<Label> pageList = labelService.findByLabelPage(label, page, size);
            return new Result(StatusCode.OK, true, "查询成功", new PageResult<>(pageList.getTotalElements(), pageList.getContent()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result(StatusCode.FAILER, false, "查询失败");
    }


    /**
     * 有效标签列表
     *
     * @return Result
     */
    @GetMapping("/list")
    public Result findByState(){
        try {
            return new Result(StatusCode.OK,true,"查询成功",labelService.findByState());
        }catch (Exception e){
            e.printStackTrace();
        }
        return new Result(StatusCode.FAILER,false,"查询失败");
    }

    /**
     * 推荐标签列表
     *
     * @return Result
     */
    @GetMapping("/toplist")
    public Result findByRecommend(){
        try {
            return new Result(StatusCode.OK,true,"查询成功",labelService.findByRecommend());
        }catch (Exception e){
            e.printStackTrace();
        }
        return new Result(StatusCode.FAILER,false,"查询失败");
    }



}
