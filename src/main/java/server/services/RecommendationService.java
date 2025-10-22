package server.services;

import server.entities.Anime;

import java.util.List;
import java.util.Map;

public interface RecommendationService {
    List<Anime> getRecommendations (Map<Long, Integer> rates);
}
