package com.Cra2iTeT.UniMatch.service;

import com.Cra2iTeT.UniMatch.model.pojo.Tag;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author Cra2iTeT
 * @date 2023/7/8 16:01:13
 * @description
 */
public interface ITagService extends IService<Tag> {
    void createNewTag(String name);
}
