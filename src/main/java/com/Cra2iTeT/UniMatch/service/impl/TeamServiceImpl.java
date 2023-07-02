package com.Cra2iTeT.UniMatch.service.impl;

import com.Cra2iTeT.UniMatch.mapper.ITeamMapper;
import com.Cra2iTeT.UniMatch.model.pojo.Team;
import com.Cra2iTeT.UniMatch.service.ITeamService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author Cra2iTeT
 * @since 2023/6/19 19:23
 */
@Service
public class TeamServiceImpl extends ServiceImpl<ITeamMapper, Team> implements ITeamService {
}
