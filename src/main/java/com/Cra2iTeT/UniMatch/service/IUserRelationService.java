package com.Cra2iTeT.UniMatch.service;

import com.Cra2iTeT.UniMatch.model.pojo.UserRelation;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author Cra2iTeT
 * @description 好友关系业务接口
 * @since 2023/7/7 11:23:45
 */
public interface IUserRelationService extends IService<UserRelation> {
    List<UserRelation> getUserRelations(Integer page);

    boolean isNotFollow(Long userId);

    boolean follow(Long userId);

    List<UserRelation> getRelationFans(Integer page);

    boolean unFollow(Long userId);

    boolean isNotFan(Long userId);

    boolean fansBlock(Long userId);
}
