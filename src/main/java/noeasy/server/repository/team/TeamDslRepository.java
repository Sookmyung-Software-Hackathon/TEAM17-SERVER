package noeasy.server.repository.team;

import noeasy.server.domain.Team;

import java.util.List;

public interface TeamDslRepository {
    List<Team> findAllTeamBySearch(String keyword);
    Team findTeamByMemberEmail(String email);
}
