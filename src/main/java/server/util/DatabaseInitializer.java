package server.util;


import lombok.AllArgsConstructor;
import server.constants.IntegerConstants;
import server.dao.AnimeDao;
import server.dao.GenreDao;
import server.entities.Anime;
import server.services.AnimeSaverService;
import server.services.MyAnimeListService;

import java.util.Map;

@AllArgsConstructor
public class DatabaseInitializer {
    AnimeSaverService animeSaverService;

    MyAnimeListService myAnimeListService;

    public void init() {
        int numOfAnimeToSave = IntegerConstants.NUM_ANIME_TO_SAVE.getValue();
        int limit = IntegerConstants.LIMIT_PER_REQUEST_TO_MAL.getValue();
        int offset = IntegerConstants.OFFSET_FOR_ANIME_SAVE.getValue();

        while (numOfAnimeToSave > 0) {
            Map<Long, Integer> animeTop = myAnimeListService.getAnimeTop(limit, offset);
            for (Long id : animeTop.keySet()) {
                Anime anime = myAnimeListService.getAnimeById(id);
                if (anime == null) continue;
                animeSaverService.saveOrUpdate(anime);
            }
            offset = offset + limit;
            numOfAnimeToSave = numOfAnimeToSave - limit;
        }
    }
}
