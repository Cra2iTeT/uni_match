package com.Cra2iTeT.UniMatch.controller;

import com.Cra2iTeT.UniMatch.common.code.SystemCode;
import com.Cra2iTeT.UniMatch.model.dto.TeamTO;
import com.Cra2iTeT.UniMatch.model.vo.R;
import com.Cra2iTeT.UniMatch.service.ITeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Cra2iTeT
 * @since 2023/6/19 19:25
 */
@RestController
@RequestMapping("/team")
public class TeamController {

    private final ITeamService teamService;

    public TeamController(ITeamService teamService) {
        this.teamService = teamService;
    }

    @PostMapping()
    public R<Object> createNewTeam(@RequestBody TeamTO teamTO) {
        R<Object> res = new R<>();
        if (teamService.createNewTeam(teamTO)){
            res.setCode(SystemCode.TAGS_SUCCESS.getCode());
            res.setMsg(SystemCode.TAGS_SUCCESS.getMsg());
        }
        return res;
    }
}
