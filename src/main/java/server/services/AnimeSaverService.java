package server.services;

import server.entities.Anime;

public interface AnimeSaverService {
    void saveOrUpdate (Anime anime);

    Anime getWithGenresByID (Long animeId);
}
