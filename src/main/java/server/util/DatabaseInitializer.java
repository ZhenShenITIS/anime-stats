package server.util;


import lombok.AllArgsConstructor;
import server.constants.IntegerConstants;
import server.dao.AnimeDao;
import server.dao.GenreDao;
import server.services.MyAnimeListService;

@AllArgsConstructor
public class DatabaseInitializer {
    AnimeDao animeDao;

    GenreDao genreDao;

    MyAnimeListService myAnimeListService;

    public void init() {
        int numOfAnimeToSave = IntegerConstants.NUM_ANIME_TO_SAVE.getValue();
        while (numOfAnimeToSave > 0) {

        }
    }
}
