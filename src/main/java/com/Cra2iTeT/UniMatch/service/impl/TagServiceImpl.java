package com.Cra2iTeT.UniMatch.service.impl;

import cn.hutool.core.util.IdUtil;
import com.Cra2iTeT.UniMatch.common.LoginUserHolder;
import com.Cra2iTeT.UniMatch.common.SystemStander;
import com.Cra2iTeT.UniMatch.mapper.ITagMapper;
import com.Cra2iTeT.UniMatch.model.pojo.Tag;
import com.Cra2iTeT.UniMatch.service.ITagService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author Cra2iTeT
 * @date 2023/7/8 16:01:27
 * @description
 */
@Service
public class TagServiceImpl extends ServiceImpl<ITagMapper, Tag> implements ITagService {
    @Override
    public void createNewTag(String name) {
        Tag tag = generateTag(LoginUserHolder.get().getId(), name);
        save(tag);
    }

    private Tag generateTag(Long userId, String name) {
        Tag tag = new Tag();
        tag.setId(IdUtil.getSnowflakeNextId());
        tag.setName(name);
        tag.setUserId(userId);
        tag.setIsDeleted(SystemStander.IS_NOT_DELETED);
        return tag;
    }
}
