package com.Cra2iTeT.UniMatch.controller;

import cn.hutool.core.util.IdUtil;
import com.Cra2iTeT.UniMatch.common.code.SystemCode;
import com.Cra2iTeT.UniMatch.common.code.UserCode;
import com.Cra2iTeT.UniMatch.model.dto.UserLoginTO;
import com.Cra2iTeT.UniMatch.model.dto.UserRegTO;
import com.Cra2iTeT.UniMatch.model.vo.R;
import com.Cra2iTeT.UniMatch.model.vo.UserVo;
import com.Cra2iTeT.UniMatch.service.IUserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Cra2iTeT
 * @since 2023/6/19 19:25
 */
@RestController
@RequestMapping("/user")
public class UserController {
    // TODO 用户基础功能 添加不同情况的参数校验
    // TODO 用户举报，后台审核等
    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping("/email")
    public R<Object> regByEmail(@RequestBody UserRegTO userRegTO) {
        return reg(UserCode.REG_FLAG_EMAIL, userRegTO);
    }

    @PostMapping("/phone")
    public R<Object> regByPhone(@RequestBody UserRegTO userRegTO) {
        return reg(UserCode.REG_FLAG_PHONE, userRegTO);
    }

    @PostMapping("/login")
    public R<UserVo> login(@RequestBody UserLoginTO userLoginTO) {
        R<UserVo> res = new R<>();
        UserVo userVo = userService.login(userLoginTO);
        if (userVo == null) {
            res.setCode(SystemCode.LOGIN_FAIL.getCode());
            res.setMsg(SystemCode.LOGIN_FAIL.getMsg());
            return res;
        } else {
            String token = IdUtil.simpleUUID();
            userVo.setToken(token);
            userService.saveUserLoginToken(token, userVo);
            res.setCode(SystemCode.LOGIN_SUCCESS.getCode());
            res.setMsg(SystemCode.LOGIN_SUCCESS.getMsg());
            res.setData(userVo);
        }
        return res;
    }

    private R<Object> reg(boolean flag, UserRegTO userRegTO) {
        R<Object> res = new R<>();
        if (userService.isRegCodeCorrect(userRegTO.getEmail(), userRegTO.getCode())) {
            res.setCode(SystemCode.REG_CODE_ERROR.getCode());
            res.setMsg(SystemCode.REG_CODE_ERROR.getMsg());
            return res;
        } else {
            if (userService.isUserExisted(flag, userRegTO)) {
                res.setCode(SystemCode.REG_USER_EXISTED.getCode());
                res.setMsg(SystemCode.REG_USER_EXISTED.getMsg());
            } else {
                if (userService.generateNewUser(flag, userRegTO)) {
                    res.setCode(SystemCode.REG_FAIL.getCode());
                    res.setMsg(SystemCode.REG_FAIL.getMsg());
                } else {

                    res.setCode(SystemCode.REG_SUCCESS.getCode());
                    res.setMsg(SystemCode.REG_SUCCESS.getMsg());
                }
            }
        }
        return res;
    }
}
