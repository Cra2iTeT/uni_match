package com.Cra2iTeT.UniMatch.service.impl;

import cn.hutool.core.util.IdUtil;
import com.Cra2iTeT.UniMatch.common.LoginUserHolder;
import com.Cra2iTeT.UniMatch.common.SystemStander;
import com.Cra2iTeT.UniMatch.mapper.IUserRelationMapper;
import com.Cra2iTeT.UniMatch.model.pojo.UserRelation;
import com.Cra2iTeT.UniMatch.service.IUserRelationService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Cra2iTeT
 * @description 好友关系业务
 * @since 2023/7/7 11:23:45
 */
@Service
public class UserRelationServiceImpl extends ServiceImpl<IUserRelationMapper, UserRelation>
        implements IUserRelationService {
    @Override
    public List<UserRelation> getUserRelations(Integer page) {
        // TODO 加入本地缓存以后可以存储这个，给page做一个判断，page大于count就不给查了，缓存设置一个过期时间

        LambdaQueryWrapper<UserRelation> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserRelation::getUserOneId, LoginUserHolder.get().getId());
        long size = count(wrapper);
        Page<UserRelation> userRelationPage = new Page<>(page, SystemStander.PAGE_SIZE);
        return page(userRelationPage, wrapper).getRecords();
    }

    @Override
    public boolean isNotFollow(Long userId) {
        LambdaQueryWrapper<UserRelation> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserRelation::getUserOneId, LoginUserHolder.get().getId())
                .eq(UserRelation::getUserTwoId, userId)
                .last("limit 1");
        return count(wrapper) == 0;
    }

    @Override
    public boolean follow(Long userId) {
        UserRelation userRelation = generateUserRelation(LoginUserHolder.get().getId(), userId);
        return save(userRelation);
    }

    @Override
    public List<UserRelation> getRelationFans(Integer page) {
        LambdaQueryWrapper<UserRelation> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserRelation::getUserTwoId, LoginUserHolder.get().getId());
        Page<UserRelation> userRelationPage = new Page<>(page, SystemStander.PAGE_SIZE);
        return page(userRelationPage).getRecords();
    }

    @Override
    public boolean unFollow(Long userId) {
        LambdaUpdateWrapper<UserRelation> wrapper = new LambdaUpdateWrapper<>();
        wrapper.set(UserRelation::getIsDeleted, SystemStander.IS_DELETED)
                .eq(UserRelation::getUserOneId, LoginUserHolder.get().getId())
                .eq(UserRelation::getUserTwoId, userId);
        return update(wrapper);
    }

    @Override
    public boolean isNotFan(Long userId) {
        LambdaQueryWrapper<UserRelation> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserRelation::getUserOneId, userId)
                .eq(UserRelation::getUserTwoId, LoginUserHolder.get().getId())
                .last("limit 1");
        return count(wrapper) == 0;
    }

    @Override
    public boolean fansBlock(Long userId) {
        LambdaUpdateWrapper<UserRelation> wrapper = new LambdaUpdateWrapper<>();
        wrapper.set(UserRelation::getIsDeleted, SystemStander.IS_DELETED)
                .eq(UserRelation::getUserOneId, userId)
                .eq(UserRelation::getUserTwoId, LoginUserHolder.get().getId());
        return update(wrapper);
    }

    private UserRelation generateUserRelation(Long id, Long userId) {
        UserRelation userRelation = new UserRelation();
        userRelation.setId(IdUtil.getSnowflakeNextId());
        userRelation.setUserOneId(id);
        userRelation.setUserTwoId(userId);
        userRelation.setIsDeleted(SystemStander.IS_NOT_DELETED);
        return userRelation;
    }
}
