package com.Cra2iTeT.UniMatch.controller;

import com.Cra2iTeT.UniMatch.common.code.SystemCode;
import com.Cra2iTeT.UniMatch.model.pojo.UserRelation;
import com.Cra2iTeT.UniMatch.model.vo.R;
import com.Cra2iTeT.UniMatch.service.IUserRelationService;
import com.Cra2iTeT.UniMatch.service.IUserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Cra2iTeT
 * @description
 * @since 2023/7/7 13:06:03
 */
@RestController
@RequestMapping("/relation")
public class UserRelationController {

    private final IUserRelationService userRelationService;

    private final IUserService userService;

    public UserRelationController(IUserRelationService userRelationService, IUserService userService) {
        this.userRelationService = userRelationService;
        this.userService = userService;
    }

    @GetMapping("/{page}")
    public R<List<UserRelation>> getRelations(@PathVariable("page") Integer page) {
        R<List<UserRelation>> res = new R<>();
        List<UserRelation> relations = userRelationService.getUserRelations(page);
        res.setCode(SystemCode.RELATION_SUCCESS.getCode());
        res.setMsg(SystemCode.RELATION_SUCCESS.getMsg());
        res.setData(relations);
        return res;
    }

    @PostMapping("/follow/{id}")
    public R<Boolean> followUser(@PathVariable("id") Long userId) {
        R<Boolean> res = new R<>();
        if (!userRelationService.isNotFollow(userId)) {
            res.setCode(SystemCode.RELATION_IS_FOLLOWED.getCode());
            res.setMsg(SystemCode.RELATION_IS_FOLLOWED.getMsg());
        } else {
            if (!userService.isUserExisted(userId)) {
                res.setCode(SystemCode.USER_NOT_EXISTED.getCode());
                res.setMsg(SystemCode.USER_NOT_EXISTED.getMsg());
            } else {
                if (userRelationService.follow(userId)) {
                    res.setCode(SystemCode.RELATION_FOLLOW_SUCCESS.getCode());
                    res.setMsg(SystemCode.RELATION_FOLLOW_SUCCESS.getMsg());
                } else {
                    res.setCode(SystemCode.RELATION_FOLLOW_FAIL.getCode());
                    res.setMsg(SystemCode.RELATION_FOLLOW_FAIL.getMsg());
                }
            }
        }
        return res;
    }

    @PostMapping("/unFollow/{id}")
    public R<Boolean> unFollow(@PathVariable("id") Long userId) {
        R<Boolean> res = new R<>();
        if (userRelationService.isNotFollow(userId)) {
            res.setCode(SystemCode.RELATION_NOT_FOLLOWED.getCode());
            res.setMsg(SystemCode.RELATION_NOT_FOLLOWED.getMsg());
        } else {
            if (!userService.isUserExisted(userId)) {
                res.setCode(SystemCode.USER_NOT_EXISTED.getCode());
                res.setMsg(SystemCode.USER_NOT_EXISTED.getMsg());
            } else {
                if (userRelationService.unFollow(userId)) {
                    res.setCode(SystemCode.RELATION_UNFOLLOW_SUCCESS.getCode());
                    res.setMsg(SystemCode.RELATION_UNFOLLOW_SUCCESS.getMsg());
                } else {
                    res.setCode(SystemCode.RELATION_UNFOLLOW_FAIL.getCode());
                    res.setMsg(SystemCode.RELATION_UNFOLLOW_FAIL.getMsg());
                }
            }
        }
        return res;
    }

    @GetMapping("/fans/{page}")
    public R<List<UserRelation>> getRelationFans(@PathVariable("page") Integer page) {
        R<List<UserRelation>> res = new R<>();
        List<UserRelation> relations = userRelationService.getRelationFans(page);
        res.setCode(SystemCode.RELATION_SUCCESS.getCode());
        res.setMsg(SystemCode.RELATION_SUCCESS.getMsg());
        res.setData(relations);
        return res;
    }

    @PostMapping("/fans/block/{id}")
    public R<Boolean> fansBlock(@PathVariable("id") Long userId) {
        R<Boolean> res = new R<>();
        if (userRelationService.isNotFan(userId)) {
            res.setCode(SystemCode.RELATION_NOT_FAN.getCode());
            res.setMsg(SystemCode.RELATION_NOT_FAN.getMsg());
        } else {
            if (!userService.isUserExisted(userId)) {
                res.setCode(SystemCode.USER_NOT_EXISTED.getCode());
                res.setMsg(SystemCode.USER_NOT_EXISTED.getMsg());
            } else {
                if (userRelationService.fansBlock(userId)) {
                    res.setCode(SystemCode.RELATION_BLOCK_SUCCESS.getCode());
                    res.setMsg(SystemCode.RELATION_BLOCK_SUCCESS.getMsg());
                } else {
                    res.setCode(SystemCode.RELATION_BLOCK_FAIl.getCode());
                    res.setMsg(SystemCode.RELATION_BLOCK_FAIl.getMsg());
                }
            }
        }
        return res;
    }
}
