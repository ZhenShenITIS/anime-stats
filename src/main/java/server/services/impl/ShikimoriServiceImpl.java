package server.services.impl;

import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import server.constants.StringConstants;
import server.services.ShikimoriService;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

public class ShikimoriServiceImpl implements ShikimoriService {
    @Override
    public Long getShikiUserIdByUsername(String username) {
        Document doc;
        try {
            doc = Jsoup.connect(StringConstants.SHIKI_URL.getValue() + username).get();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Element head = doc.selectFirst("div.profile-head");
        String userId = head.attr("data-user-id");
        return Long.parseLong(userId);
    }

    @Override
    public Map<Long, Integer> getAnimeRatesByShikiUserId(Long shikiUserId) {
        String response = getResponse(StringConstants.SHIKI_API_USER_RATES_BY_ID_URL.getValue()+shikiUserId.toString());
        JSONArray jsonArray = new JSONArray(response);
        if (jsonArray.isEmpty()){
            return Map.of();
        }
        Map<Long, Integer> ratesMap = new HashMap<>();

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = (JSONObject) jsonArray.get(i);
            if (!jsonObject.getString("target_type").equals("Anime")) {
                continue;
            }
            Integer score = jsonObject.getInt("score");
            if (score.equals(0)) {
                continue;
            }
            Long animeId = jsonObject.getLong("target_id");

            ratesMap.put(animeId, score);
        }

        return  ratesMap;


    }

    private String getResponse (String url) {
        HttpClient httpClient = HttpClient.newHttpClient();
        URI uri;
        try {
            uri = new URI(url);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        HttpRequest httpRequest = HttpRequest
                .newBuilder()
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
