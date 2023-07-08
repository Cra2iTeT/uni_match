package com.Cra2iTeT.UniMatch.service.impl;

import cn.hutool.core.util.IdUtil;
import com.Cra2iTeT.UniMatch.common.LoginUserHolder;
import com.Cra2iTeT.UniMatch.common.SystemStander;
import com.Cra2iTeT.UniMatch.mapper.ITeamMapper;
import com.Cra2iTeT.UniMatch.model.dto.TeamTO;
import com.Cra2iTeT.UniMatch.model.pojo.Team;
import com.Cra2iTeT.UniMatch.model.pojo.TeamUser;
import com.Cra2iTeT.UniMatch.service.ITeamService;
import com.Cra2iTeT.UniMatch.service.ITeamUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Cra2iTeT
 * @since 2023/6/19 19:23
 */
@Service
public class TeamServiceImpl extends ServiceImpl<ITeamMapper, Team> implements ITeamService {

    private final ITeamUserService teamUserService;

    public TeamServiceImpl(ITeamUserService teamUserService) {
        this.teamUserService = teamUserService;
    }

    @Override
    public boolean createNewTeam(TeamTO teamTO) {
        // TODO 这里简单设置，实际需要查询队长的信息判断群的最大人数，还有事务控制
        long captain = LoginUserHolder.get().getId();
        long teamId = IdUtil.getSnowflakeNextId();
        Team team = new Team(teamId, teamTO.getName(), teamTO.getDescription(),
                captain, 0, 20, SystemStander.IS_NOT_DELETED);
        save(team);
        teamTO.getUserIds().add(captain);
        List<TeamUser> teamUsers = new ArrayList<>();
        for (Long userId : teamTO.getUserIds()) {
            TeamUser teamUser = new TeamUser(IdUtil.getSnowflakeNextId(), teamId, userId, SystemStander.IS_NOT_DELETED);
            teamUsers.add(teamUser);
        }
        return teamUserService.saveBatch(teamUsers);
    }
}
