package com.tensquare.friend.controller;

import com.tensquare.entity.Result;
import com.tensquare.entity.StatusCode;
import com.tensquare.friend.service.FriendService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 华韵流风
 * @ClassName FriendController
 * @Date 2021/10/19 20:54
 * @packageName com.tensquare.friend.controller
 * @Description TODO
 */
@RestController
@RequestMapping("/friend")
@RefreshScope
public class FriendController {

    @Autowired
    private FriendService friendService;

    @Autowired
    private HttpServletRequest request;


    /**
     * 添加好友
     *
     * @param friendid friendid
     * @param type     type
     * @return Result
     */
    @PutMapping("/like/{friendid}/{type}")
    public Result addFriend(@PathVariable String friendid, @PathVariable String type) {

        try {
            Claims claims = (Claims) request.getAttribute("claims_user");
            if (claims == null) {
                return new Result(StatusCode.NOPERMISSION, false, "权限不足");
            }
            if ("1".equals(type)) {
                if (friendService.addFriend(claims.getId(), friendid) == 0) {
                    return new Result(StatusCode.REPEATDO, false, "已经添加该好友");
                }
            } else {
                friendService.addNoFriend(claims.getId(), friendid);
            }
            return new Result(StatusCode.OK, true, "操作成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result(StatusCode.FAILER, false, "操作失败");
    }

    /**
     * 删除好友
     *
     * @param friendid friendid
     * @return Result
     */
    @DeleteMapping("/{friendid}")
    public Result remove(@PathVariable String friendid) {
        try {
            Claims claims = (Claims) request.getAttribute("claims_user");
            if (claims == null) {
                return new Result(StatusCode.NOPERMISSION, false, "权限不足");
            }
            friendService.deleteFriend(claims.getId(), friendid);
            return new Result(StatusCode.OK, true, "删除成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result(StatusCode.FAILER, false, "删除失败");

    }


}
