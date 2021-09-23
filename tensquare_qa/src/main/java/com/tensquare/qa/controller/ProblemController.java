package com.tensquare.qa.controller;

import com.tensquare.entity.PageResult;
import com.tensquare.entity.Result;
import com.tensquare.entity.StatusCode;
import com.tensquare.qa.pojo.Problem;
import com.tensquare.qa.service.ProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/qa/problem")
@CrossOrigin
public class ProblemController {
    @Autowired
    private ProblemService problemService;

    /**
     * 添加问题
     *
     * @param problem problem
     * @return Result
     */
    @PostMapping
    public Result add(@RequestBody Problem problem) {
        try {
            problemService.add(problem);
            return new Result(StatusCode.OK, true, "添加成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result(StatusCode.FAILER, false, "添加失败");
    }

    /**
     * 所有问题
     *
     * @return Result
     */
    @GetMapping
    public Result findAll() {
        try {
            List<Problem> all = problemService.findAll();
            return new Result(StatusCode.OK, true, "查询成功", all);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result(StatusCode.FAILER, false, "查询失败");
    }

    /**
     * 通过id查找
     *
     * @param problemId id
     * @return Result
     */
    @GetMapping("/{problemId}")
    public Result findById(@PathVariable String problemId) {
        try {
            Problem problem = problemService.findById(problemId);
            return new Result(StatusCode.OK, true, "查询成功", problem);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result(StatusCode.FAILER, false, "查询失败");
    }

    /**
     * 通过id修改问题
     *
     * @param problemId id
     * @param problem   problem
     * @return Result
     */
    @PutMapping("/{problemId}")
    public Result update(@PathVariable String problemId, @RequestBody Problem problem) {
        try {
            problemService.update(problemId, problem);
            return new Result(StatusCode.OK, true, "修改成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result(StatusCode.FAILER, false, "修改失败");
    }

    /**
     * 根据id删除问题
     *
     * @param problemId id
     * @return Result
     */
    @DeleteMapping("/{problemId}")
    public Result delete(@PathVariable String problemId) {
        try {
            problemService.remove(problemId);
            return new Result(StatusCode.OK, true, "删除成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result(StatusCode.FAILER, false, "删除失败");
    }

    /**
     * 根据条件查询问题列表
     *
     * @param problem problem
     * @return Result
     */
    @PostMapping("/search")
    public Result findByProblem(@RequestBody Problem problem) {
        try {
            List<Problem> problems = problemService.findByProblem(problem);
            return new Result(StatusCode.OK, true, "查询成功", problems);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result(StatusCode.FAILER, false, "查询失败");
    }

    /**
     * 问题分页
     *
     * @param problem pro
     * @param page    页码
     * @param size    页码大小
     * @return Page
     */
    @PostMapping("/search/{page}/{size}")
    public Result findByLabelPage(@RequestBody Problem problem, @PathVariable int page, @PathVariable int size) {
        try {
            Page<Problem> pageList = problemService.findByProblemPages(problem, page, size);
            return new Result(StatusCode.OK, true, "查询成功", new PageResult<Problem>(pageList.getTotalElements(), pageList.getContent()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new Result(StatusCode.FAILER, false, "查询失败");
    }

    /**
     * 最新问题列表
     *
     * @param label label
     * @param page  page
     * @param size  size
     * @return Result
     */
    @GetMapping("/newlist/{label}/{page}/{size}")
    public Result newList(@PathVariable("label") String label, @PathVariable("page") int page, @PathVariable("size") int size) {
        try {
            List<Problem> problems = problemService.newList(label, page, size);
            return new Result(StatusCode.OK, true, "查询成功", problems);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result(StatusCode.FAILER, false, "查询失败");
    }

    /**
     * 热门问答列表
     *
     * @param label label
     * @param page  page
     * @param size  size
     * @return Result
     */
    @GetMapping("/hotlist/{label}/{page}/{size}")
    public Result hotList(@PathVariable("label") String label, @PathVariable("page") int page, @PathVariable("size") int size) {
        try {
            Page<Problem> pagelist = problemService.hotList(label, page, size);
            return new Result(StatusCode.OK, true, "查询成功", new PageResult<>(pagelist.getTotalElements(),pagelist.getContent()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result(StatusCode.FAILER, false, "查询失败");
    }

    /**
     * 等待回答列表
     *
     * @param label label
     * @param page  page
     * @param size  size
     * @return Result
     */
    @GetMapping("/waitlist/{label}/{page}/{size}")
    public Result waitList(@PathVariable("label") String label, @PathVariable("page") int page, @PathVariable("size") int size) {
        try {
            Page<Problem> pageList = problemService.waitList(label, page, size);
            return new Result(StatusCode.OK, true, "查询成功", new PageResult<>(pageList.getTotalElements(),pageList.getContent()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result(StatusCode.FAILER, false, "查询失败");
    }

    @PostMapping("/all/{label}/{page}/{size}")
    public Result all(@PathVariable("label") String label, @PathVariable("page") int page, @PathVariable("size") int size) {
        List<Problem> problems = null;
        try {
            Page<Problem> pageList = problemService.allList(label, page, size);
            return new Result(StatusCode.OK, true, "查询成功", new PageResult<>(pageList.getTotalElements(),pageList.getContent()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result(StatusCode.FAILER, false, "查询失败");
    }


}
