package com.ivanfranchin.springdatajparelationships.onetoone.simplepk.mapper;

import com.ivanfranchin.springdatajparelationships.onetoone.simplepk.model.Team;
import com.ivanfranchin.springdatajparelationships.onetoone.simplepk.model.TeamDetail;
import com.ivanfranchin.springdatajparelationships.onetoone.simplepk.rest.dto.CreateTeamDetailRequest;
import com.ivanfranchin.springdatajparelationships.onetoone.simplepk.rest.dto.CreateTeamRequest;
import com.ivanfranchin.springdatajparelationships.onetoone.simplepk.rest.dto.TeamResponse;
import com.ivanfranchin.springdatajparelationships.onetoone.simplepk.rest.dto.UpdateTeamDetailRequest;
import com.ivanfranchin.springdatajparelationships.onetoone.simplepk.rest.dto.UpdateTeamRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface TeamMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "teamDetail", ignore = true)
    Team toTeam(CreateTeamRequest createTeamRequest);

    TeamResponse toTeamResponse(Team team);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "teamDetail", ignore = true)
    void updateTeamFromRequest(UpdateTeamRequest updateTeamRequest, @MappingTarget Team team);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "team", ignore = true)
    TeamDetail toTeamDetail(CreateTeamDetailRequest createTeamDetailRequest);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "team", ignore = true)
    void updateTeamDetailFromRequest(UpdateTeamDetailRequest updateTeamDetailRequest,
                                     @MappingTarget TeamDetail teamDetail);
}
