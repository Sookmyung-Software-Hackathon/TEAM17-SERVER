package noeasy.server.repository;

import noeasy.server.domain.Vote;
import noeasy.server.domain.VoteItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VoteItemRepository extends JpaRepository<VoteItem, Long> {
}
