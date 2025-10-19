package server.constants;

public enum StringConstants {
    SHIKI_URL("https://shikimori.one/"),
    SHIKI_API_URL("https://shikimori.one/api/v2/"),
    SHIKI_API_USER_RATES_BY_ID_URL("https://shikimori.one/api/v2/user_rates?user_id="),
    MAL_RANKING_API("https://api.myanimelist.net/v2/anime/ranking"),
    MAL_ANIME_NECESSARY_INFO("https://api.myanimelist.net/v2/anime/%s?fields=id,title,main_picture,synopsis,mean,rank,genres,related_anime"),
    FILE_PREFIX("images")
    ;
    private final String value;

    StringConstants(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
