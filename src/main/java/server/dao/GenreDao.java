package server.dao;

import server.entities.Genre;

import java.util.List;

public interface GenreDao {
    Genre getById (Integer id);

    Genre getByName (String name);

    void saveOrUpdate (Genre genre);

    List<Genre> getByAnimeId (Long id);

    void saveAnimeGenre (Long animeId, Integer genreId);

}
