package server.services;

import server.entities.Anime;

import java.util.Map;

public interface MyAnimeListService {
    Anime getAnimeById(Long animeId);

    Map<Long, Integer> getAnimeTop (int limit, int offset);
}
