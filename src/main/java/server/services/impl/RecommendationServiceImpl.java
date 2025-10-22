package server.services.impl;

import lombok.AllArgsConstructor;
import server.dao.AnimeDao;
import server.dao.GenreDao;
import server.entities.Anime;
import server.entities.Genre;
import server.services.AnimeSaverService;
import server.services.RecommendationService;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@AllArgsConstructor
public class RecommendationServiceImpl implements RecommendationService {
    private final AnimeSaverService animeSaverService;
    private final AnimeDao animeDao;

    @Override
    public List<Anime> getRecommendations(Map<Long, Integer> rates) {
        Map<Integer, Integer> genresRates = getGenresRates(rates);
        if (genresRates.isEmpty()) return List.of();
        List<Integer> sortedGenres = getSortedGenres(genresRates);
        List<Anime> candidates = getCandidatesBySortedGenres(sortedGenres);
        List<Anime> recommendations = getRecommendations(candidates, genresRates, getGenreWeight(sortedGenres, genresRates));
        return getAnimeWithoutAlreadyWatched(recommendations, rates);
    }

    private Map<Integer, Integer> getGenresRates (Map<Long, Integer> rates) {
        Map<Integer, Integer> genresRates = new HashMap<>();
        for (Long animeId : rates.keySet()) {
            Integer rateOfAnime = rates.get(animeId);
            if (rateOfAnime.equals(0)) continue;
            Anime anime = animeSaverService.getWithGenresByID(animeId);
            if (anime.getGenres() == null || anime.getGenres().isEmpty()) continue;
            for (Genre genre : anime.getGenres()) {
                if (genresRates.containsKey(genre.getId())) {
                    Integer rate = genresRates.get(genre.getId());
                    rate = rate + getRateOfGenre(rateOfAnime);
                    genresRates.put(genre.getId(), rate);
                } else {
                    genresRates.put(genre.getId(), getRateOfGenre(rateOfAnime));
                }
            }
        }
        return genresRates;
    }

    private List<Integer> getSortedGenres (Map<Integer, Integer> genresRates) {
        return genresRates.entrySet()
                .stream()
                .sorted(Map.Entry.<Integer, Integer>comparingByValue().reversed())
                .map(Map.Entry::getKey)
                .toList();
    }

    private List<Anime> getCandidatesBySortedGenres (List<Integer> sortedGenres) {
        Set<Anime> candidates = new LinkedHashSet<>();
        for (int i = 0; i < Math.min(sortedGenres.size(), 3); i++) {
            Integer genreId = sortedGenres.get(i);
            List<Anime> topAnimeByGenre = animeDao.getByGenreId(genreId, 250);
            candidates.addAll(topAnimeByGenre);
        }
        return new ArrayList<>(candidates);
    }

    private List<Anime> getRecommendations (List<Anime> candidates, Map<Integer, Integer> genresRates, double genreWeight) {
        return candidates
                .stream()
                .sorted(Comparator.comparing( a -> {
                    Anime anime = (Anime) a;
                    int sum = anime.getGenres().stream().mapToInt(g -> genresRates.getOrDefault(g.getId(), 0)).sum();
                    return anime.getScore()*genreWeight + sum;
                }).reversed())
                .toList();
    }

    private int getRateOfGenre (Integer rate) {
        return -10 + 2 * rate;
    }

    private double getGenreWeight (List<Integer> sortedGenres, Map<Integer, Integer> genresRates) {
        double sum = 0;
        int count = Math.min(sortedGenres.size(), 3);
        for (int i = 0; i < count; i++) {
            Integer genreId = sortedGenres.get(i);
            sum += genresRates.getOrDefault(genreId, 0);
        }
        return count == 0 ? 1.0 : sum / count;
    }

    private List<Anime> getAnimeWithoutAlreadyWatched (List<Anime> animeList, Map<Long, Integer> rates){
        return animeList.stream()
                .filter(anime -> !rates.containsKey(anime.getId()))
                .toList();
    }
}
