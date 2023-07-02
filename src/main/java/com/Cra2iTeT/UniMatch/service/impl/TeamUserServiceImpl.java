package com.Cra2iTeT.UniMatch.service.impl;

import com.Cra2iTeT.UniMatch.mapper.ITeamUserMapper;
import com.Cra2iTeT.UniMatch.model.pojo.TeamUser;
import com.Cra2iTeT.UniMatch.service.ITeamUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author Cra2iTeT
 * @since 2023/6/19 19:24
 */
@Service
public class TeamUserServiceImpl extends ServiceImpl<ITeamUserMapper, TeamUser> implements ITeamUserService {
}
