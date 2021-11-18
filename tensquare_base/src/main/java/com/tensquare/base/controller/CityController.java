package com.tensquare.base.controller;

import com.tensquare.base.pojo.City;
import com.tensquare.base.service.CityService;
import com.tensquare.entity.PageResult;
import com.tensquare.entity.Result;
import com.tensquare.entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

/**
 * @author 华韵流风
 * @ClassName CityController
 * @Date 2021/9/18 13:31
 * @packageName com.tensquare.base.controller
 * @Description TODO
 */
@RestController
@RequestMapping("/city")
@RefreshScope
public class CityController {

    @Autowired
    private CityService cityService;

    /**
     * 添加city
     *
     * @param city city
     * @return Result
     */
    @PostMapping
    public Result add(@RequestBody City city) {
        try {
            cityService.add(city);
            return new Result(StatusCode.OK, true, "添加成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result(StatusCode.FAILER, false, "添加失败");
    }

    /**
     * 删除city
     *
     * @param cityId cityId
     * @return Result
     */
    @DeleteMapping("/{cityId}")
    public Result remove(@PathVariable("cityId") String cityId) {
        try {
            cityService.remove(cityId);
            return new Result(StatusCode.OK, true, "删除成功");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result(StatusCode.FAILER, false, "删除失败");

    }

    /**
     * 根据id修改city
     *
     * @param cityId cityId
     * @param city  city
     * @return Result
     */
    @PutMapping("/{cityId}")
    public Result update(@PathVariable("cityId") String cityId, @RequestBody City city) {
        try {
            cityService.update(cityId, city);
            return new Result(StatusCode.OK, true, "修改成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result(StatusCode.FAILER, false, "修改失败");
    }

    /**
     * 根据id查询city
     *
     * @param cityId cityId
     * @return Result
     */
    @GetMapping("/{cityId}")
    public Result findById(@PathVariable("cityId") String cityId) {
        try {
            return new Result(StatusCode.OK, true, "查询成功", cityService.findById(cityId));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result(StatusCode.FAILER, false, "查询失败");

    }

    /**
     * 查询所有city
     *
     * @return Result
     */
    @GetMapping
    public Result findAll() {
        try {
            return new Result(StatusCode.OK, true, "查询成功", cityService.findAll());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result(StatusCode.FAILER, false, "查询失败");
    }

    /**
     * 根据条件查询城市列表
     *
     * @param city city
     * @return Result
     */
    @PostMapping("/search")
    public Result findByCity(@RequestBody City city){
        try {
            return new Result(StatusCode.OK,true,"查询成功",cityService.findByCity(city));
        }catch (Exception e){
            e.printStackTrace();
        }
        return new Result(StatusCode.FAILER,false,"查询失败");
    }

    /**
     * 城市列表分页
     *
     * @param city city
     * @return Result
     */
    @PostMapping("/search/{page}/{size}")
    public Result findByCityPage(@RequestBody City city, @PathVariable int page, @PathVariable int size){
        try {
            Page<City> pageList = cityService.findByCityPage(city, page, size);
            return new Result(StatusCode.OK,true,"查询成功",new PageResult<>(pageList.getTotalElements(),pageList.getContent()));
        }catch (Exception e){
            e.printStackTrace();
        }
        return new Result(StatusCode.FAILER,false,"查询失败");
    }

}
