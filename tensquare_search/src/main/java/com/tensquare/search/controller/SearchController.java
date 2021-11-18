package com.tensquare.search.controller;

import com.tensquare.entity.PageResult;
import com.tensquare.entity.Result;
import com.tensquare.entity.StatusCode;
import com.tensquare.search.pojo.Article;
import com.tensquare.search.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

/**
 * @author 华韵流风
 * @ClassName SearchController
 * @Date 2021/9/27 19:41
 * @packageName com.tensquare.search.controller
 * @Description TODO
 */
@RestController
@RequestMapping("/search")
@RefreshScope
public class SearchController {

    @Autowired
    private SearchService searchService;


    /**
     * 添加索引
     *
     * @param article article
     * @return Result
     */
    @PostMapping("/add")
    public Result add(@RequestBody Article article) {
        try {
            searchService.add(article);
            return new Result(StatusCode.OK, true, "添加成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result(StatusCode.FAILER, false, "添加失败");
    }

    /**
     * 查询
     *
     * @param key  key
     * @param page page
     * @param size size
     * @return Result
     */
    @GetMapping("/{key}/{page}/{size}")
    public Result search(@PathVariable String key, @PathVariable int page, @PathVariable int size) {
        try {
            Page<Article> pageList = searchService.findByTitleOrContent(key, key, page, size);
            return new Result(StatusCode.OK, true, "查询成功", new PageResult<>(pageList.getTotalElements(), pageList.getContent()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result(StatusCode.FAILER, false, "查询失败");
    }

}
