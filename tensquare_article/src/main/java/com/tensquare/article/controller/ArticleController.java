package com.tensquare.article.controller;

import com.tensquare.article.pojo.Article;
import com.tensquare.article.service.ArticleService;
import com.tensquare.entity.PageResult;
import com.tensquare.entity.Result;
import com.tensquare.entity.StatusCode;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 华韵流风
 * @ClassName ArticleController
 * @Date 2021/9/18 14:17
 * @packageName com.tensquare.article.controller
 * @Description TODO
 */
@RestController
@RequestMapping("/article")
@RefreshScope
public class ArticleController {


    @Autowired
    private ArticleService articleService;

    @Autowired
    private HttpServletRequest request;

    /**
     * 添加article
     *
     * @param article article
     * @return Result
     */
    @PostMapping
    public Result add(@RequestBody Article article) {
        try {
            //认证
            Claims claims = (Claims) request.getAttribute("claims_user");
            if (claims == null) {
                return new Result(StatusCode.NOPERMISSION, false, "权限不足");
            }
            articleService.add(article);
            return new Result(StatusCode.OK, true, "添加成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result(StatusCode.FAILER, false, "添加失败");
    }

    /**
     * 查询所有article
     *
     * @return Result
     */
    @GetMapping
    public Result findAll() {
        try {
            return new Result(StatusCode.OK, true, "查询成功", articleService.findAll());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result(StatusCode.FAILER, false, "查询失败");
    }

    /**
     * 根据id查询article
     *
     * @param articleId articleId
     * @return Result
     */
    @GetMapping("/{articleId}")
    public Result findById(@PathVariable("articleId") String articleId) {
        try {
            return new Result(StatusCode.OK, true, "查询成功", articleService.findById(articleId));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result(StatusCode.FAILER, false, "查询失败");
    }

    /**
     * 修改articleId
     *
     * @param articleId articleId
     * @param article   article
     * @return Result
     */
    @PutMapping("/{articleId}")
    public Result update(@PathVariable("articleId") String articleId, @RequestBody Article article) {
        try {
            articleService.update(articleId, article);
            return new Result(StatusCode.OK, true, "修改成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result(StatusCode.FAILER, false, "修改失败");
    }

    /**
     * 根据id删除article
     *
     * @param articled articled
     * @return Result
     */
    @DeleteMapping("/{articleId}")
    public Result remove(@PathVariable("articleId") String articled) {
        try {
            articleService.remove(articled);
            return new Result(StatusCode.OK, true, "删除成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result(StatusCode.FAILER, false, "删除失败");
    }

    /**
     * 根据条件查询文章列表
     *
     * @param article article
     * @return Result
     */
    @PostMapping("/search")
    public Result findByColumn(@RequestBody Article article) {
        try {
            return new Result(StatusCode.OK, true, "查询成功", articleService.findByArticle(article));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result(StatusCode.FAILER, false, "查询失败");
    }

    /**
     * 文章分页
     *
     * @param article article
     * @param page    page
     * @param size    size
     * @return Result
     */
    @PostMapping("/search/{page}/{size}")
    public Result findByColumnPage(@RequestBody Article article, @PathVariable int page, @PathVariable int size) {
        try {
            return new Result(StatusCode.OK, true, "查询成功", articleService.findByArticlePage(article, page, size));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result(StatusCode.FAILER, false, "查询失败");
    }

    /**
     * 点赞
     *
     * @param articleId articleId
     * @return Result
     */
    @PutMapping("/thumbup/{articleId}")
    public Result thumbUp(@PathVariable String articleId) {
        try {
            articleService.thumbUp(articleId);
            return new Result(StatusCode.OK, true, "成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result(StatusCode.FAILER, false, "失败");
    }

    /**
     * 根据频道ID获取文章列表
     *
     * @param channelId channelId
     * @param page      page
     * @param size      size
     * @return Result
     */
    @PostMapping("/channel/{channelId}/{page}/{size}")
    public Result findByChannelid(@PathVariable String channelId, @PathVariable int page, @PathVariable int size) {
        try {
            Page<Article> pageList = articleService.findByChannelid(channelId, page, size);
            return new Result(StatusCode.OK, true, "成功", new PageResult<>(pageList.getTotalElements(), pageList.getContent()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result(StatusCode.FAILER, false, "失败");
    }


    /**
     * 根据专栏ID获取文章列表
     *
     * @param columnId columnId
     * @param page     page
     * @param size     size
     * @return Result
     */
    @PostMapping("/column/{columnId}/{page}/{size}")
    public Result findByColumnid(@PathVariable String columnId, @PathVariable int page, @PathVariable int size) {
        try {
            Page<Article> pageList = articleService.findByColumnid(columnId, page, size);
            return new Result(StatusCode.OK, true, "成功", new PageResult<>(pageList.getTotalElements(), pageList.getContent()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result(StatusCode.FAILER, false, "失败");
    }

    /**
     * 文章审核
     *
     * @param articleId articleId
     * @return Result
     */
    @PutMapping("/examine/{articleId}")
    public Result examine(@PathVariable String articleId) {
        try {
            articleService.examine(articleId);
            return new Result(StatusCode.OK, true, "成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result(StatusCode.FAILER, false, "失败");
    }

    /**
     * 头条文章
     *
     * @return Result
     */
    @GetMapping("/top")
    public Result articleTop() {
        try {
            return new Result(StatusCode.OK, true, "成功", articleService.articleTop());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result(StatusCode.FAILER, false, "失败");
    }


}
