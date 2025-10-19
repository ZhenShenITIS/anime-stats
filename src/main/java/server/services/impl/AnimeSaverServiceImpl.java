package server.services.impl;

import server.dao.AnimeDao;
import server.dao.GenreDao;
import server.entities.Anime;
import server.entities.Genre;
import server.services.AnimeSaverService;

public class AnimeSaverServiceImpl implements AnimeSaverService {
    AnimeDao animeDao;

    GenreDao genreDao;
    @Override
    public void saveOrUpdate(Anime anime) {
        animeDao.saveOrUpdate(anime);
        Long animeId = anime.getId();
        for (Genre genre : anime.getGenres()) {
            genreDao.saveOrUpdate(genre);
            genreDao.saveAnimeGenre(animeId, genre.getId());
        }
    }
}
