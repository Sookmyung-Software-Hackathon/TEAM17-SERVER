package noeasy.server.repository;

import noeasy.server.domain.Member;
import noeasy.server.domain.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {

    List<Vote> findByTeamId(String teamId);
}
