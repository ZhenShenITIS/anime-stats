package server.constants;

public enum StringConstants {
    SHIKI_URL("https://shikimori.one/"),
    SHIKI_API_URL("https://shikimori.one/api/v2/"),
    SHIKI_API_USER_RATES_BY_ID_URL("https://shikimori.one/api/v2/user_rates?user_id=")
    ;
    private final String value;

    StringConstants(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
