package com.tensquare.article.controller;

import com.tensquare.article.pojo.Channel;
import com.tensquare.article.service.ChannelService;
import com.tensquare.entity.Result;
import com.tensquare.entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author 华韵流风
 * @ClassName ChannelController
 * @Date 2021/9/18 14:17
 * @packageName com.tensquare.article.controller
 * @Description TODO
 */
@RestController
@RequestMapping("/article/channel")
@CrossOrigin
public class ChannelController {


    @Autowired
    private ChannelService channelService;

    /**
     * 添加channel
     *
     * @param channel channel
     * @return Result
     */
    @PostMapping
    public Result add(@RequestBody Channel channel) {
        try {
            channelService.add(channel);
            return new Result(StatusCode.OK, true, "添加成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result(StatusCode.FAILER, false, "添加失败");
    }

    /**
     * 查询所有channel
     *
     * @return Result
     */
    @GetMapping
    public Result findAll() {
        try {
            return new Result(StatusCode.OK, true, "查询成功", channelService.findAll());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result(StatusCode.FAILER, false, "查询失败");
    }

    /**
     * 根据id查询channel
     *
     * @param channelId channelId
     * @return Result
     */
    @GetMapping("/{channelId}")
    public Result findById(@PathVariable("channelId") String channelId) {
        try {
            return new Result(StatusCode.OK, true, "查询成功", channelService.findById(channelId));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result(StatusCode.FAILER, false, "查询失败");
    }

    /**
     * 根据id修稿channel
     *
     * @param channelId channelId
     * @param channel   channel
     * @return Result
     */
    @PutMapping("/{channelId}")
    public Result update(@PathVariable("channelId") String channelId, @RequestBody Channel channel) {
        try {
            channelService.update(channelId, channel);
            return new Result(StatusCode.OK, true, "修改成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result(StatusCode.FAILER, false, "修改失败");
    }

    /**
     * 根据id删除channel
     *
     * @param channelId channelId
     * @return Result
     */
    @DeleteMapping("/{channelId}")
    public Result remove(@PathVariable("channelId") String channelId) {
        try {
            channelService.remove(channelId);
            return new Result(StatusCode.OK, true, "删除成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result(StatusCode.FAILER, false, "删除失败");
    }

    @PostMapping("/search")
    public Result findByChannel(@RequestBody Channel channel) {
        try {
            return new Result(StatusCode.OK, true, "查询成功", channelService.findByChannel(channel));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result(StatusCode.FAILER, false, "查询失败");
    }

    @PostMapping("/search/{page}/{size}")
    public Result findByChannel(@RequestBody Channel channel, @PathVariable int page, @PathVariable int size) {
        try {
            return new Result(StatusCode.OK, true, "查询成功", channelService.findByChannelPage(channel, page, size));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result(StatusCode.FAILER, false, "查询失败");
    }

}
