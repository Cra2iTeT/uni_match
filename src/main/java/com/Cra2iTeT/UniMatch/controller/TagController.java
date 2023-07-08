package com.Cra2iTeT.UniMatch.controller;

import com.Cra2iTeT.UniMatch.common.LoginUserHolder;
import com.Cra2iTeT.UniMatch.common.code.SystemCode;
import com.Cra2iTeT.UniMatch.common.code.TagCode;
import com.Cra2iTeT.UniMatch.model.dto.TagsTO;
import com.Cra2iTeT.UniMatch.model.vo.R;
import com.Cra2iTeT.UniMatch.service.ITagService;
import com.Cra2iTeT.UniMatch.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

/**
 * @author Cra2iTeT
 * @description
 * @since 2023/7/7 13:07:15
 */
@RestController
@RequestMapping("/tag")
public class TagController {
    // TODO 引入本地缓存
    private final IUserService userService;

    private final ITagService tagService;

    private final StringRedisTemplate stringRedisTemplate;

    public TagController(IUserService userService, ITagService tagService, StringRedisTemplate stringRedisTemplate) {
        this.userService = userService;
        this.tagService = tagService;
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @PostMapping("/user/tag")
    public R<Object> userTags(@RequestBody TagsTO tagsTO) {
        R<Object> res = new R<>();
        userService.setUserTags(tagsTO);
        res.setCode(SystemCode.TAGS_SUCCESS.getCode());
        res.setMsg(SystemCode.TAGS_SUCCESS.getMsg());
        return res;
    }

    @PostMapping("/{name}")
    public R<Object> createTag(@PathVariable("name") String name) {
        R<Object> res = new R<>();
        if (Boolean.TRUE.equals(stringRedisTemplate.opsForSet().isMember(TagCode.tagKeyPrefix, name))) {
            res.setCode(SystemCode.TAG_EXISTED.getCode());
            res.setMsg(SystemCode.TAG_EXISTED.getMsg());
        } else {
            tagService.createNewTag(name);
            res.setCode(SystemCode.TAG_New_SUCCESS.getCode());
            res.setMsg(SystemCode.TAG_New_SUCCESS.getMsg());
        }
        return res;
    }
}
