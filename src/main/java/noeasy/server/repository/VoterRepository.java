package noeasy.server.repository;

import noeasy.server.domain.Voter;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VoterRepository extends JpaRepository<Voter, Long> {

    Long countByVoteId(Long voteId);
    Long countByVoteItemId(Long voteItemId);
    Optional<Voter> findByMemberIdAndVoteId(String memberId, Long VoteId);
}
