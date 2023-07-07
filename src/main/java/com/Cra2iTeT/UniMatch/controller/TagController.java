package com.Cra2iTeT.UniMatch.controller;

import com.Cra2iTeT.UniMatch.common.LoginUserHolder;
import com.Cra2iTeT.UniMatch.common.code.SystemCode;
import com.Cra2iTeT.UniMatch.model.dto.TagsTO;
import com.Cra2iTeT.UniMatch.model.vo.R;
import com.Cra2iTeT.UniMatch.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

/**
 * @author Cra2iTeT
 * @description
 * @since 2023/7/7 13:07:15
 */
@RestController
@RequestMapping("/tag")
public class TagController {

    private final IUserService userService;

    public TagController(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping("/user/tag")
    public R<Object> userTags(@RequestBody TagsTO tagsTO) {
        R<Object> res = new R<>();
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

    // TODO 创建新标签
}
