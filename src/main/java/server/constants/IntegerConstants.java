package server.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum IntegerConstants {
    NUM_ANIME_TO_SAVE(14_000);
    private final int value;
}
