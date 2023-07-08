package com.Cra2iTeT.UniMatch.service;

import com.Cra2iTeT.UniMatch.model.dto.TagsTO;
import com.Cra2iTeT.UniMatch.model.dto.UserLoginTO;
import com.Cra2iTeT.UniMatch.model.dto.UserRegTO;
import com.Cra2iTeT.UniMatch.model.pojo.User;
import com.Cra2iTeT.UniMatch.model.vo.UserVo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author Cra2iTeT
 * @since 2023/6/19 19:22
 */
public interface IUserService extends IService<User> {
    boolean isUserExisted(boolean flag,UserRegTO userRegTO);

    boolean isRegCodeCorrect(String account, String code);

    boolean generateNewUser(boolean flag, UserRegTO userRegTO);

    UserVo login(UserLoginTO userLoginTO);

    void saveUserLoginToken(String token, UserVo userVo);

    boolean isUserExisted(Long userId);

    void setUserTags(TagsTO tagsTO);
}
