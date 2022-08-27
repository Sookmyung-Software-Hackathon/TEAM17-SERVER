package noeasy.server.repository.boggle;

import noeasy.server.domain.Boggle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface BoggleRepository extends JpaRepository<Boggle, String>, BoggleDslRepository,  QuerydslPredicateExecutor<Boggle> {
}
