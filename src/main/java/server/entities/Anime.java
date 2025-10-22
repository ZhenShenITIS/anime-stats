package server.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@ToString
@Getter
@Setter
@AllArgsConstructor
@Builder
public class Anime {
    private Long id;
    private String title;
    private String picturePath;
    private String synopsis;
    private Double score;
    private Integer rank;
    private List<Genre> genres;
    private List<Anime> relatedAnime;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof Anime)) return false;

        return (getId() != null) && (getId().equals(((Anime) o).getId()));
    }

    @Override
    public int hashCode() {
        return getId() != null ? getId().hashCode() : 0;
    }
}
