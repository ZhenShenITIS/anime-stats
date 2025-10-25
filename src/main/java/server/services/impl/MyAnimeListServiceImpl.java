package server.services.impl;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import server.constants.StringConstants;
import server.entities.Anime;
import server.entities.Genre;
import server.services.MyAnimeListService;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyAnimeListServiceImpl implements MyAnimeListService {
    @Override
    public Anime getAnimeById(Long animeId) {
        String url = StringConstants.MAL_ANIME_NECESSARY_INFO.getValue().formatted(animeId);
        JSONObject response = null;
        try {
            response = new JSONObject(getResponse(url));
        } catch (JSONException e) {
            return null;
        }

        String pictureUrl = parsePicture(response);
        List<Genre> genreList = parseGenres(response);
        List<Anime> relatedAnimeList = parseRelatedAnime(response);
        Double score = 0d;
        if (response.has("mean")) {
            score = response.getDouble("mean");
        }

        return Anime
                .builder()
                .id(response.getLong("id"))
                .title(response.getString("title"))
                .picturePath(pictureUrl)
                .synopsis(response.getString("synopsis").replace("[Written by MAL Rewrite]", ""))
                .score(score)
                .rank(response.getInt("rank"))
                .genres(genreList)
                .relatedAnime(relatedAnimeList)
                .build();
    }

    @Override
    public Map<Long, Integer> getAnimeTop(int limit, int offset) {

        String url = StringConstants.MAL_RANKING_API.getValue() + "?ranking_type=all";
        if (limit > 0) {
            url = url + "&limit=" + limit;
        }
        if (offset > 0) {
            url = url + "&offset=" + offset;
        }
        JSONObject response = new JSONObject(getResponse(url));
        JSONArray jsonArray = response.getJSONArray("data");
        if (jsonArray.isEmpty()) {
            return Map.of();
        }
        Map<Long, Integer> map = new HashMap<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = (JSONObject) jsonArray.get(i);
            JSONObject node = jsonObject.getJSONObject("node");
            Long animeId = node.getLong("id");
            JSONObject ranking = jsonObject.getJSONObject("ranking");
            Integer rank = ranking.getInt("rank");
            map.put(animeId, rank);
        }
        return map;

    }

    private String getResponse (String url) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println("Sleep error");
        }
        HttpClient httpClient = HttpClient.newHttpClient();
        URI uri;
        try {
            uri = new URI(url);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        String MALToken = System.getenv("MAL_API_TOKEN");
        HttpRequest httpRequest = HttpRequest
                .newBuilder()
                .header("Authorization", "Bearer "+MALToken)
                .header("User-Agent", "AnimeListStat (contact=ewgenii20066@gmail.com)")
                .uri(uri)
                .build();
        HttpResponse<String> response;
        try {
            response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return response.body();

    }

    private List<Genre> parseGenres (JSONObject response) {

        if (!response.has("genres")) {
            return List.of();
        }
        JSONArray genres = response.getJSONArray("genres");
        List<Genre> genreList = new ArrayList<>();
        for (int i =0; i < genres.length(); i++){
            JSONObject JSONGenre = genres.getJSONObject(i);
            genreList.add(Genre
                    .builder()
                    .id(JSONGenre.getInt("id"))
                    .name(JSONGenre.getString("name"))
                    .build());
        }
        return genreList;
    }

    private List<Anime> parseRelatedAnime (JSONObject response) {
        JSONArray relatedAnime = response.getJSONArray("related_anime");
        List<Anime> relatedAnimeList = new ArrayList<>();
        if (!relatedAnime.isEmpty()) {
            for (int i = 0; i < relatedAnime.length(); i++) {
                JSONObject JSONRelatedAnime = relatedAnime.getJSONObject(i);
                JSONObject node = JSONRelatedAnime.getJSONObject("node");
                relatedAnimeList.add(Anime
                        .builder()
                        .id(node.getLong("id"))
                        .title(node.getString("title"))
                        .build());
            }
        }
        return relatedAnimeList;
    }

    private String parsePicture (JSONObject response) {
        JSONObject mainPicture = response.getJSONObject("main_picture");
        return mainPicture.getString("medium");
    }
}
