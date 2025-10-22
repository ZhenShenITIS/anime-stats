package server.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
@AllArgsConstructor
@Builder
public class Genre {
    private Integer id;
    private  String name;

    @Override
    public boolean equals(Object obj) {
        if (!obj.getClass().equals(this.getClass())){
            return false;
        }
        Genre other = (Genre) obj;
        return this.id.equals(other.getId());
    }
}
