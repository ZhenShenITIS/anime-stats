package server.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum IntegerConstants {
    NUM_ANIME_TO_SAVE(15_000),
    LIMIT_PER_REQUEST_TO_MAL(100)
    ;
    private final int value;
}
