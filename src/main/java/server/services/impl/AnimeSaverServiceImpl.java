package server.services.impl;

import lombok.AllArgsConstructor;
import server.dao.AnimeDao;
import server.dao.GenreDao;
import server.entities.Anime;
import server.entities.Genre;
import server.services.AnimeSaverService;
import server.util.ImageSaverUtil;

import java.util.List;

@AllArgsConstructor
public class AnimeSaverServiceImpl implements AnimeSaverService {
    AnimeDao animeDao;

    GenreDao genreDao;
    @Override
    public void saveOrUpdate(Anime anime) {
        String pathToFile = ImageSaverUtil.save(anime.getPicturePath());
        anime.setPicturePath(pathToFile);
        animeDao.saveOrUpdate(anime);
        Long animeId = anime.getId();
        for (Genre genre : anime.getGenres()) {
            genreDao.saveOrUpdate(genre);
            genreDao.saveAnimeGenre(animeId, genre.getId());
        }
    }

    @Override
    public Anime getWithGenresByID(Long animeId) {
        Anime anime = animeDao.getById(animeId);
        if (anime == null) return null;
        List<Genre> genres = genreDao.getByAnimeId(animeId);
        anime.setGenres(genres);
        return anime;
    }

    @Override
    public List<Anime> getWithGenresByGenreId(Integer id, int limit) {
        return animeDao.getByGenreId(id, limit).stream().map(a -> getWithGenresByID(a.getId())).toList();
    }
}
