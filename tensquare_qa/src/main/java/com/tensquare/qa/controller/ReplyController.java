package com.tensquare.qa.controller;

import com.tensquare.entity.PageResult;
import com.tensquare.entity.Result;
import com.tensquare.entity.StatusCode;
import com.tensquare.qa.pojo.Reply;
import com.tensquare.qa.service.ReplyService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/reply")
@RefreshScope
public class ReplyController {
    @Autowired
    private ReplyService replyService;

    @Autowired
    private HttpServletRequest request;

    /**
     * 添加问题
     *
     * @param reply reply
     * @return Result
     */
    @PostMapping
    public Result add(@RequestBody Reply reply) {
        try {
            replyService.add(reply);
            return new Result(StatusCode.OK, true, "添加成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result(StatusCode.FAILER, false, "添加失败");
    }

    /**
     * 查询所有问题
     *
     * @return Result
     */
    @GetMapping
    public Result findAll() {
        try {
            List<Reply> replies = replyService.findAll();
            return new Result(StatusCode.OK, true, "查询成功", replies);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result(StatusCode.FAILER, false, "查询失败");
    }

    /**
     * 根据回答id查询
     *
     * @param replyId id
     * @return Result
     */
    @GetMapping("/{replyId}")
    public Result findById(@PathVariable String replyId) {
        try {
            Reply reply = replyService.findById(replyId);
            return new Result(StatusCode.OK, true, "查询成功", reply);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result(StatusCode.FAILER, false, "查询失败");
    }

    /**
     * 修改回答
     *
     * @param replyId id
     * @param reply   reply
     * @return Result
     */
    @PutMapping("/{replyId}")
    public Result update(@PathVariable String replyId, @RequestBody Reply reply) {
        try {
            replyService.update(replyId, reply);
            return new Result(StatusCode.OK, true, "修改成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result(StatusCode.FAILER, false, "修改失败");
    }

    /**
     * 根据id删除回答
     *
     * @param replyId id
     * @return Result
     */
    @DeleteMapping("/{replyId}")
    public Result delete(@PathVariable String replyId) {
        try {
            replyService.remove(replyId);
            return new Result(StatusCode.OK, true, "删除成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result(StatusCode.FAILER, false, "删除失败");
    }

    /**
     * 我的回答列表
     *
     * @param page 页码
     * @param size 页大小
     * @return Result
     */
    @GetMapping("/list/{page}/{size}")
    public Result mylist(@PathVariable int page, @PathVariable int size) {
        try {
            Page<Reply> replies = replyService.myList(page, size);
            return new Result(StatusCode.OK, true, "查询成功", new PageResult<>(replies.getTotalElements(), replies.getContent()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result(StatusCode.FAILER, false, "查询失败");
    }

    /**
     * 根据问题id查询回答
     *
     * @param problemId id
     * @return Result
     */
    @GetMapping("/problem/{problemId}")
    public Result findReplyByProblemid(@PathVariable String problemId) {
        try {
            List<Reply> replyByProblemId = replyService.findReplyByProblemId(problemId);
            return new Result(StatusCode.OK, true, "查询成功", replyByProblemId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result(StatusCode.FAILER, false, "查询失败");
    }


    /**
     * 回答问题
     *
     * @param reply reply
     * @return Result
     */
    @PostMapping("/save")
    public Result save(@RequestBody Reply reply) {
        try {
            //认证
            Claims claims = (Claims) request.getAttribute("claims_user");
            if (claims == null) {
                return new Result(StatusCode.NOPERMISSION, false, "权限不足");
            }
            replyService.add(reply);
            return new Result(StatusCode.OK, true, "回复成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result(StatusCode.FAILER, false, "回复失败");
    }


    /**
     * 回答分页
     *
     * @param reply reply
     * @param page  页码
     * @param size  页大小
     * @return Result
     */
    @PostMapping("/search/{page}/{size}")
    public Result findByReplyPage(@RequestBody Reply reply, @PathVariable int page, @PathVariable int size) {
        Page<Reply> pageList = null;
        try {
            pageList = replyService.findByReplyPage(reply, page, size);
            return new Result(StatusCode.OK, true, "查询成功", new PageResult<>(pageList.getTotalElements(), pageList.getContent()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new Result(StatusCode.FAILER, false, "查询失败");
    }
}
