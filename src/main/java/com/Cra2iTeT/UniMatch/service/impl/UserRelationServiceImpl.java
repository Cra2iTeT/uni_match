package com.Cra2iTeT.UniMatch.service.impl;

import com.Cra2iTeT.UniMatch.mapper.IUserRelationMapper;
import com.Cra2iTeT.UniMatch.model.pojo.UserRelation;
import com.Cra2iTeT.UniMatch.service.IUserRelationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * @author Cra2iTeT
 * @description 好友关系业务
 * @since 2023/7/7 11:23:45
 */
@Service
public class UserRelationServiceImpl extends ServiceImpl<IUserRelationMapper, UserRelation>
        implements IUserRelationService {
    @Override
    public ArrayList<UserRelation> getUserRelations(Long id, Integer page) {
        return null;
    }

    @Override
    public boolean isExistedAndNotFollow(Long id, Long userId) {
        return false;
    }
}
