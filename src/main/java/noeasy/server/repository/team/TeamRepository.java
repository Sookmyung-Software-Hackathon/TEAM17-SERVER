package noeasy.server.repository.team;

import noeasy.server.domain.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends JpaRepository<Team, String>, TeamDslRepository, QuerydslPredicateExecutor<Team> {
}
