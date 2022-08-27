package noeasy.server.repository.boggle;

import noeasy.server.domain.Boggle;
import noeasy.server.domain.type.TagType;

import java.util.List;

public interface BoggleDslRepository {
    List<Boggle> findAllBoggleBySearch(List<TagType> tags, String keyword);
}
