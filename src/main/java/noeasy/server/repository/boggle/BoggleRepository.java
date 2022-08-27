package noeasy.server.repository.boggle;

import noeasy.server.domain.Boggle;
import noeasy.server.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoggleRepository extends JpaRepository<Boggle, String>, BoggleDslRepository,  QuerydslPredicateExecutor<Boggle> {

    public List<Boggle> findByMember(Member member);
}
