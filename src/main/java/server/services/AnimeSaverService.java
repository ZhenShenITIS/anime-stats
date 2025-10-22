package server.services;

import server.entities.Anime;

import java.util.List;

public interface AnimeSaverService {
    void saveOrUpdate (Anime anime);

    Anime getWithGenresByID (Long animeId);

    List<Anime> getWithGenresByGenreId (Integer id, int limit);
}
