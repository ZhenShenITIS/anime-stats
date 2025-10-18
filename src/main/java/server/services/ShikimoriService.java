package server.services;

import java.util.Map;

public interface ShikimoriService {
    Long getShikiUserIdByUsername (String username);

    Map<Long, Integer> getAnimeRatesByShikiUserId (Long shikiUserId);
}
