package noeasy.server.repository.team;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import noeasy.server.domain.QMember;
import noeasy.server.domain.QTeam;
import noeasy.server.domain.Team;

import javax.persistence.EntityManager;
import java.util.List;

import static noeasy.server.domain.QMember.member;
import static noeasy.server.domain.QTeam.*;

public class TeamDslRepositoryImpl implements TeamDslRepository{

    private final JPAQueryFactory queryFactory;

    public TeamDslRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<Team> findAllTeamBySearch(String keyword) {
        return queryFactory
                .selectFrom(team)
                .where(
                        nameLike(keyword)
                )
                .orderBy(team.id.desc())
                .fetch();
    }

    public BooleanExpression nameLike(String keyword) {
        if (keyword.isBlank()) return null;
        else return team.name.like(keyword);
    }

    @Override
    public Team findTeamByMemberEmail(String email){
        return queryFactory
                .selectFrom(team)
                .join(member)
                .on(member.team.id.eq(team.id))
                .where(
                        team.eq(
                                JPAExpressions
                                        .selectFrom(member.team)
                                        .where(member.email.eq(email))
                        )
                )
                .fetchOne();
    }
}
