package noeasy.server.repository.team.service;

import lombok.RequiredArgsConstructor;
import noeasy.server.domain.Team;
import noeasy.server.domain.dto.TeamRequestDto;
import noeasy.server.domain.dto.TeamResponseDto;
import noeasy.server.repository.VoteRepository;
import noeasy.server.repository.team.TeamRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TeamService {
    private final TeamRepository teamRepository;

    public List<TeamResponseDto> readTeamRanking() {
        List<Team> teamList = teamRepository.findAll(Sort.by("temperature"));
        return teamList.stream().map( e -> new TeamResponseDto(e)).collect(Collectors.toList());
    }

    public List<TeamResponseDto> readAll(String keyword) {
        return teamRepository.findAllTeamBySearch(keyword).stream().map( e -> new TeamResponseDto(e)).collect(Collectors.toList());
    }

    public TeamResponseDto createTeam(TeamRequestDto requestDto) {
        Team team = new Team(requestDto);
        teamRepository.save(team);

        return new TeamResponseDto(team);
    }
}
