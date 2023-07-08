package com.Cra2iTeT.UniMatch.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.DesensitizedUtil;
import cn.hutool.core.util.IdUtil;
import com.Cra2iTeT.UniMatch.common.LoginUserHolder;
import com.Cra2iTeT.UniMatch.common.code.UserCode;
import com.Cra2iTeT.UniMatch.mapper.IUserMapper;
import com.Cra2iTeT.UniMatch.model.dto.TagsTO;
import com.Cra2iTeT.UniMatch.model.dto.UserLoginTO;
import com.Cra2iTeT.UniMatch.model.dto.UserRegTO;
import com.Cra2iTeT.UniMatch.model.pojo.Tag;
import com.Cra2iTeT.UniMatch.model.pojo.User;
import com.Cra2iTeT.UniMatch.model.vo.UserVo;
import com.Cra2iTeT.UniMatch.service.ITagService;
import com.Cra2iTeT.UniMatch.service.IUserService;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author Cra2iTeT
 * @since 2023/6/19 19:23
 */
@Service
public class UserServiceImpl extends ServiceImpl<IUserMapper, User> implements IUserService {

    private final StringRedisTemplate stringRedisTemplate;

    private final ITagService tagService;

    public UserServiceImpl(StringRedisTemplate stringRedisTemplate, ITagService tagService) {
        this.stringRedisTemplate = stringRedisTemplate;
        this.tagService = tagService;
    }

    @Override
    public boolean isUserExisted(boolean flag, UserRegTO userRegTO) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        if (UserCode.REG_FLAG_PHONE == flag) {
            wrapper.eq(User::getPhone, userRegTO.getPhone())
                    .last("limit 1");
        } else {
            wrapper.eq(User::getEmail, userRegTO.getEmail())
                    .last("limit 1");
        }
        return count(wrapper) == 0;
    }

    @Override
    public boolean isRegCodeCorrect(String account, String code) {
        String codeRedis = (String) stringRedisTemplate.opsForHash().get("reg:code", account);
        return StringUtils.hasText(codeRedis) && ObjectUtils.nullSafeEquals(codeRedis, code);
    }

    @Override
    public boolean generateNewUser(boolean flag, UserRegTO userRegTO) {
        User user = BeanUtil.copyProperties(userRegTO, User.class);
        user.setAccount(flag ? userRegTO.getPhone() : userRegTO.getEmail());
        user.setId(IdUtil.getSnowflakeNextId());
        user.setGender(0);
        // TODO 加密
        user.setPassword(userRegTO.getPassword());
        // TODO 默认头像地址
        user.setAvatarUrl("");
        user.setUsername("新用户" + user.getId().toString().substring(0, 7));
        user.setTags("");
        user.setUserRole(UserCode.ROLE_NEW_USER);

        return save(user);
    }

    @Override
    public UserVo login(UserLoginTO userLoginTO) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getAccount, userLoginTO.getAccount())
                .select(User::getId, User::getAccount, User::getUsername, User::getPassword, User::getAvatarUrl,
                        User::getPhone, User::getEmail, User::getGender, User::getUserRole, User::getTags);
        User user = getOne(wrapper);
        if (user.getPassword().equals(userLoginTO.getPassword())) {
            UserVo userVo = BeanUtil.copyProperties(user, UserVo.class);
            userVo.setEmail(DesensitizedUtil.email(userVo.getEmail()));
            userVo.setPhone(DesensitizedUtil.mobilePhone(userVo.getEmail()));
            return userVo;
        }
        return null;
    }

    @Override
    public void saveUserLoginToken(String token, UserVo userVo) {
        stringRedisTemplate.opsForValue().set(UserCode.LOGIN_TOKEN_USER_INFO + token,
                JSON.toJSONString(userVo), UserCode.LOGIN_TOKEN_USER_INFO_TTL, TimeUnit.MILLISECONDS);
    }

    @Override
    public boolean isUserExisted(Long userId) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getId, userId);
        return count(wrapper) != 0;
    }

    @Override
    public void setUserTags(TagsTO tagsTO) {
        List<Long> tagIds = tagsTO.getTagsIds();
        LambdaQueryWrapper<Tag> tagLambdaQueryWrapper = new LambdaQueryWrapper<>();
        tagLambdaQueryWrapper.select(Tag::getId, Tag::getName)
                .in(Tag::getId, tagsTO);
        StringBuilder sb = new StringBuilder();
        List<Tag> tags = tagService.list(tagLambdaQueryWrapper);
        for (Tag tag : tags) {
            sb.append(tag).append(";");
        }
        User user = getById(LoginUserHolder.get().getId());
        user.setTags(sb.toString());
        updateById(user);
    }
}
