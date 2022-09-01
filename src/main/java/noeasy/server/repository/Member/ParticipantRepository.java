package noeasy.server.repository.Member;

import noeasy.server.domain.Member;
import noeasy.server.domain.Participant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ParticipantRepository extends JpaRepository<Participant, Long> {
    public List<Participant> findByMember(Member member);
}
