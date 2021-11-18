package com.tensquare.article.controller;

import com.tensquare.article.pojo.Column;
import com.tensquare.article.service.ColumnService;
import com.tensquare.entity.Result;
import com.tensquare.entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

/**
 * @author 华韵流风
 * @ClassName ColumnController
 * @Date 2021/9/18 14:17
 * @packageName com.tensquare.article.controller
 * @Description TODO
 */
@RestController
@RequestMapping("/column")
@RefreshScope
public class ColumnController {


    @Autowired
    private ColumnService columnService;

    /**
     * 添加column
     *
     * @param column column
     * @return Result
     */
    @PostMapping
    public Result add(@RequestBody Column column) {
        try {
            columnService.add(column);
            return new Result(StatusCode.OK, true, "添加成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result(StatusCode.FAILER, false, "添加失败");
    }

    /**
     * 查询所有column
     *
     * @return Result
     */
    @GetMapping
    public Result findAll() {
        try {
            return new Result(StatusCode.OK, true, "查询成功", columnService.findAll());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result(StatusCode.FAILER, false, "查询失败");
    }

    /**
     * 根据id查询column
     *
     * @param columnId columnId
     * @return Result
     */
    @GetMapping("/{columnId}")
    public Result findById(@PathVariable("columnId") String columnId) {
        try {
            return new Result(StatusCode.OK, true, "查询成功", columnService.findById(columnId));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result(StatusCode.FAILER, false, "查询失败");
    }

    /**
     * 根据id修稿column
     *
     * @param columnId columnId
     * @param column   column
     * @return Result
     */
    @PutMapping("/{columnId}")
    public Result update(@PathVariable("columnId") String columnId, @RequestBody Column column) {
        try {
            columnService.update(columnId, column);
            return new Result(StatusCode.OK, true, "修改成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result(StatusCode.FAILER, false, "修改失败");
    }

    /**
     * 根据id删除column
     *
     * @param columnId columnId
     * @return Result
     */
    @DeleteMapping("/{columnId}")
    public Result remove(@PathVariable("columnId") String columnId) {
        try {
            columnService.remove(columnId);
            return new Result(StatusCode.OK, true, "删除成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result(StatusCode.FAILER, false, "删除失败");
    }

    /**
     * 根据条件查询专栏列表
     *
     * @param column column
     * @return Result
     */
    @PostMapping("/search")
    public Result findByColumn(@RequestBody Column column) {
        try {
            return new Result(StatusCode.OK, true, "查询成功", columnService.findByColumn(column));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result(StatusCode.FAILER, false, "查询失败");
    }

    /**
     * 专栏分页
     *
     * @param column column
     * @param page   page
     * @param size   size
     * @return Result
     */
    @PostMapping("/search/{page}/{size}")
    public Result findByColumnPage(@RequestBody Column column, @PathVariable int page, @PathVariable int size) {
        try {
            return new Result(StatusCode.OK, true, "查询成功", columnService.findByColumnPage(column,page,size));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result(StatusCode.FAILER, false, "查询失败");
    }

    /**
     * 专栏申请
     *
     * @param column column
     * @return Result
     */
    @PostMapping("/apply")
    public Result columnApply(@RequestBody Column column){
        try {
            columnService.columnApply(column);
            return new Result(StatusCode.OK, true, "申请成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result(StatusCode.FAILER, false, "申请失败");
    }

    /**
     * 申请审核
     *
     * @param columnId columnId
     * @return Result
     */
    @PutMapping("/examine/{columnId}")
    public Result examine(@PathVariable String columnId){
        try {
            columnService.examine(columnId);
            return new Result(StatusCode.OK,true,"成功");
        }catch (Exception e){
            e.printStackTrace();
        }
        return new Result(StatusCode.FAILER,false,"失败");
    }

    /**
     * 根据用户ID查询专栏列表
     *
     * @param userId userId
     * @return Result
     */
    @GetMapping("/user/{userId}")
    public Result findByUserid(@PathVariable String userId){
        try {
            return new Result(StatusCode.OK,true,"查询成功",columnService.findByUserid(userId));
        }catch (Exception e){
            e.printStackTrace();
        }
        return new Result(StatusCode.FAILER,false,"查询失败");
    }



}
