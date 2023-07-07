package com.Cra2iTeT.UniMatch.controller;

import com.Cra2iTeT.UniMatch.common.LoginUserHolder;
import com.Cra2iTeT.UniMatch.model.pojo.UserRelation;
import com.Cra2iTeT.UniMatch.model.vo.R;
import com.Cra2iTeT.UniMatch.service.IUserRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

/**
 * @author Cra2iTeT
 * @description
 * @since 2023/7/7 13:06:03
 */
@RestController
@RequestMapping("/relation")
public class UserRelationController {

    @Autowired
    private IUserRelationService userRelationService;

    @GetMapping("/{page}")
    public R<ArrayList<UserRelation>> getRelations(@PathVariable("page") Integer page) {
        R<ArrayList<UserRelation>> res = new R<>();

        ArrayList<UserRelation> relations = userRelationService.getUserRelations(LoginUserHolder.get().getId(), page);
        return res;
    }
    // TODO 创建好友表

    @PostMapping("/follow/{id}")
    public R<Boolean> followUser(@PathVariable("id") Long userId) {
        R<Boolean> res = new R<>();
        boolean isExistedAndNotFollow = userRelationService.isExistedAndNotFollow(LoginUserHolder.get().getId(), userId);

        return res;
    }
}
