package com.ivanfranchin.springdatajparelationships.onetoone.simplepk.rest;

import com.ivanfranchin.springdatajparelationships.onetoone.simplepk.mapper.TeamMapper;
import com.ivanfranchin.springdatajparelationships.onetoone.simplepk.model.Team;
import com.ivanfranchin.springdatajparelationships.onetoone.simplepk.model.TeamDetail;
import com.ivanfranchin.springdatajparelationships.onetoone.simplepk.rest.dto.CreateTeamDetailRequest;
import com.ivanfranchin.springdatajparelationships.onetoone.simplepk.rest.dto.CreateTeamRequest;
import com.ivanfranchin.springdatajparelationships.onetoone.simplepk.rest.dto.TeamResponse;
import com.ivanfranchin.springdatajparelationships.onetoone.simplepk.rest.dto.UpdateTeamDetailRequest;
import com.ivanfranchin.springdatajparelationships.onetoone.simplepk.rest.dto.UpdateTeamRequest;
import com.ivanfranchin.springdatajparelationships.onetoone.simplepk.service.TeamService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/teams")
public class TeamDetailController {

    private final TeamService teamService;
    private final TeamMapper teamMapper;

    @GetMapping("/{teamId}")
    public TeamResponse getTeam(@PathVariable Long teamId) {
        Team team = teamService.validateAndGetTeam(teamId);
        return teamMapper.toTeamResponse(team);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public TeamResponse createTeam(@Valid @RequestBody CreateTeamRequest createTeamRequest) {
        Team team = teamMapper.toTeam(createTeamRequest);
        team = teamService.saveTeam(team);
        return teamMapper.toTeamResponse(team);
    }

    @PutMapping("/{teamId}")
    public TeamResponse updateTeam(@PathVariable Long teamId, @Valid @RequestBody UpdateTeamRequest updateTeamRequest) {
        Team team = teamService.validateAndGetTeam(teamId);
        teamMapper.updateTeamFromRequest(updateTeamRequest, team);
        teamService.saveTeam(team);
        return teamMapper.toTeamResponse(team);
    }

    @DeleteMapping("/{teamId}")
    public TeamResponse deleteTeam(@PathVariable Long teamId) {
        Team team = teamService.validateAndGetTeam(teamId);
        teamService.deleteTeam(team);
        return teamMapper.toTeamResponse(team);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{teamId}/team-details")
    public TeamResponse addTeamDetail(@PathVariable Long teamId,
                                      @Valid @RequestBody CreateTeamDetailRequest createTeamDetailRequest) {
        Team team = teamService.validateAndGetTeam(teamId);
        TeamDetail teamDetail = teamMapper.toTeamDetail(createTeamDetailRequest);
        team.addTeamDetail(teamDetail);
        team = teamService.saveTeam(team);
        return teamMapper.toTeamResponse(team);
    }

    @PutMapping("/{teamId}/team-details")
    public TeamResponse updateTeamDetail(@PathVariable Long teamId,
                                         @Valid @RequestBody UpdateTeamDetailRequest updateTeamDetailRequest) {
        Team team = teamService.validateAndGetTeam(teamId);
        TeamDetail teamDetail = team.getTeamDetail();
        teamMapper.updateTeamDetailFromRequest(updateTeamDetailRequest, teamDetail);
        team = teamService.saveTeam(team);
        return teamMapper.toTeamResponse(team);
    }

    @DeleteMapping("/{teamId}/team-details")
    public TeamResponse deleteTeamDetail(@PathVariable Long teamId) {
        Team team = teamService.validateAndGetTeam(teamId);
        team.removeTeamDetail();
        team = teamService.saveTeam(team);
        return teamMapper.toTeamResponse(team);
    }
}
