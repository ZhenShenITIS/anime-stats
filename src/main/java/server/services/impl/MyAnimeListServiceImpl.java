package server.services.impl;

import org.json.JSONArray;
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
        JSONObject response = new JSONObject(getResponse(url));
        JSONObject mainPicture = response.getJSONObject("main_picture");
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
        Anime anime = Anime
                .builder()
                .id(response.getLong("id"))
                .title(response.getString("title"))
                .picturePath("not saved in DB yet")
                .synopsis(response.getString("synopsis"))
                .score(response.getDouble("mean"))
                .rank(response.getInt("rank"))
                .genres(genreList)
                .build();

        return anime;
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
        HttpClient httpClient = HttpClient.newHttpClient();
        URI uri;
        try {
            uri = new URI(url);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        String MALToken = System.getenv("MAL_TOKEN");
        HttpRequest httpRequest = HttpRequest
                .newBuilder()
                .header("Authorization", "Bearer "+MALToken)
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
}
