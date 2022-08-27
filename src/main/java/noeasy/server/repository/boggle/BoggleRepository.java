package noeasy.server.repository.boggle;

import noeasy.server.domain.Boggle;
import noeasy.server.domain.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BoggleRepository extends JpaRepository<Boggle, String> {
}
