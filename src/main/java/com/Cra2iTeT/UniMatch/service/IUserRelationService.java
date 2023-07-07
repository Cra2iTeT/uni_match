package com.Cra2iTeT.UniMatch.service;

import com.Cra2iTeT.UniMatch.model.pojo.UserRelation;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.ArrayList;

/**
 * @author Cra2iTeT
 * @description 好友关系业务接口
 * @since 2023/7/7 11:23:45
 */
public interface IUserRelationService extends IService<UserRelation> {
    ArrayList<UserRelation> getUserRelations(Long id, Integer page);

    boolean isExistedAndNotFollow(Long id, Long userId);
}
