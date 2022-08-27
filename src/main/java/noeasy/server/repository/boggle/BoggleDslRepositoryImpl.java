package noeasy.server.repository.boggle;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import noeasy.server.domain.Boggle;
import noeasy.server.domain.type.TagType;

import javax.persistence.EntityManager;
import java.util.List;

import static noeasy.server.domain.QBoggle.*;

public class BoggleDslRepositoryImpl implements BoggleDslRepository {

    private final JPAQueryFactory queryFactory;

    public BoggleDslRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<Boggle> findAllBoggleBySearch(List<TagType> tags, String keyword) {
        return queryFactory
                .selectFrom(boggle)
                .where(
                        tagContain(tags),
                        titleLike(keyword)
                )
                .orderBy(boggle.id.desc())
                .fetch();
    }

    private BooleanExpression tagContain(List<TagType> tags) {
        if (tags.isEmpty()) return null;
        else return boggle.tag.in(tags);
    }

    private BooleanExpression titleLike(String keyword) {
        if (keyword.isBlank()) return null;
        else return boggle.title.like(keyword);
    }
}
