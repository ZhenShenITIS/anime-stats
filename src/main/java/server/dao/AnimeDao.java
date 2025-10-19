package server.dao;

import server.entities.Anime;

import java.util.List;

public interface AnimeDao {
    Anime getById (Long id);

    void saveOrUpdate (Anime anime);

    List<Anime> getByGenreId (Integer id, int limit);
}
