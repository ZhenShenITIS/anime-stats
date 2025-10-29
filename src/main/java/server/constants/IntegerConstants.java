package server.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum IntegerConstants {
    NUM_ANIME_TO_SAVE(15000),
    LIMIT_PER_REQUEST_TO_MAL(50),
    OFFSET_FOR_ANIME_SAVE(0),
    DIRECTORIES_COUNT(300)
    ;
    private final int value;
}
