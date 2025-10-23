package server.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import server.dao.GenreDao;

@ToString
@Getter
@AllArgsConstructor
@Builder
public class Genre {
    private Integer id;
    private  String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof Genre)) return false;

        return (getId() != null) && (getId().equals(((Genre) o).getId()));
    }

    @Override
    public int hashCode() {
        return getId() != null ? getId().hashCode() : 0;
    }
}
