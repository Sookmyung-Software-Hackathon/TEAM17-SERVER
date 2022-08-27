package noeasy.server.repository.boggle;

import noeasy.server.domain.Boggle;

import java.util.List;

public interface BoggleDslRepository {
    List<Boggle> findAllBoggleBySearch(List<String> tags, String keyword);
}
