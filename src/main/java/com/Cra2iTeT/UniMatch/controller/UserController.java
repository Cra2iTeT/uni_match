package com.Cra2iTeT.UniMatch.controller;

import cn.hutool.core.util.IdUtil;
import com.Cra2iTeT.UniMatch.common.LoginUserHolder;
import com.Cra2iTeT.UniMatch.common.code.SystemCode;
import com.Cra2iTeT.UniMatch.common.code.UserCode;
import com.Cra2iTeT.UniMatch.model.dto.TagsTO;
import com.Cra2iTeT.UniMatch.model.dto.UserLoginTO;
import com.Cra2iTeT.UniMatch.model.dto.UserRegTO;
import com.Cra2iTeT.UniMatch.model.vo.R;
import com.Cra2iTeT.UniMatch.model.vo.UserVo;
import com.Cra2iTeT.UniMatch.service.IUserService;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

/**
 * @author Cra2iTeT
 * @since 2023/6/19 19:25
 */
@RestController
@RequestMapping("/user")
public class UserController {
    // TODO 用户基础功能 添加不同情况的参数校验
    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping("/email")
    public R regByEmail(@RequestBody UserRegTO userRegTO) {
        return reg(UserCode.REG_FLAG_EMAIL, userRegTO);
    }

    @PostMapping("/phone")
    public R regByPhone(@RequestBody UserRegTO userRegTO) {
        return reg(UserCode.REG_FLAG_PHONE, userRegTO);
    }

    @PostMapping("/login")
    public R login(@RequestBody UserLoginTO userLoginTO) {
        R res = new R<>();
        UserVo userVo = userService.login(userLoginTO);
        if (userVo == null) {
            res.setCode(SystemCode.LOGIN_FAIL.getCode());
            res.setMsg(SystemCode.LOGIN_FAIL.getMsg());
            return res;
        }
        String token = IdUtil.simpleUUID();
        userVo.setToken(token);
        userService.saveUserLoginToken(token, userVo);
        res.setCode(SystemCode.LOGIN_SUCCESS.getCode());
        res.setMsg(SystemCode.LOGIN_SUCCESS.getMsg());
        res.setData(userVo);
        return res;
    }

    @PostMapping("/tags")
    public R userTags(@RequestBody TagsTO tagsTO) {
        R res = new R();
        boolean isLocalLoginUser = Objects.equals(LoginUserHolder.get().getId(), tagsTO.getUserId());
        if (!isLocalLoginUser) {
            res.setCode(SystemCode.VALID_USER.getCode());
            res.setMsg(SystemCode.VALID_USER.getMsg());
            return res;
        }
        userService.userTags(tagsTO);
        res.setCode(SystemCode.TAGS_SUCCESS.getCode());
        res.setMsg(SystemCode.TAGS_SUCCESS.getMsg());
        return res;
    }

    @PostMapping("/follow/{id}")
    public R followUser(@PathVariable("id") Long userId) {
        // TODO 创建好友表
        boolean isExistedAndNotFollow = userService.isExistedAndNotFollow(LoginUserHolder.get().getId(), userId);
    }

    private R reg(boolean flag, UserRegTO userRegTO) {
        R res = new R<>();
        if (userService.isRegCodeCorrect(userRegTO.getEmail(), userRegTO.getCode())) {
            res.setCode(SystemCode.REG_CODE_ERROR.getCode());
            res.setMsg(SystemCode.REG_CODE_ERROR.getMsg());
            return res;
        }
        boolean isUserExisted = userService.isUserExisted(flag, userRegTO);
        if (isUserExisted) {
            res.setCode(SystemCode.REG_USER_EXISTED.getCode());
            res.setMsg(SystemCode.REG_USER_EXISTED.getMsg());
            return res;
        }
        boolean isRegSuccess = userService.generateNewUser(flag, userRegTO);
        if (!isRegSuccess) {
            res.setCode(SystemCode.REG_FAIL.getCode());
            res.setMsg(SystemCode.REG_FAIL.getMsg());
            return res;
        }
        res.setCode(SystemCode.REG_SUCCESS.getCode());
        res.setMsg(SystemCode.REG_SUCCESS.getMsg());
        return res;
    }
}
