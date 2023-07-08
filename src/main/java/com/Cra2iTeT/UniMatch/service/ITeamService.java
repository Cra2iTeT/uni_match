package com.Cra2iTeT.UniMatch.service;

import com.Cra2iTeT.UniMatch.model.dto.TeamTO;
import com.Cra2iTeT.UniMatch.model.pojo.Team;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author Cra2iTeT
 * @since 2023/6/19 19:22
 */
public interface ITeamService extends IService<Team> {
    boolean createNewTeam(TeamTO teamTO);
}
