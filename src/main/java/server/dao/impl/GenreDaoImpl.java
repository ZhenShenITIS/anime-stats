package server.dao.impl;

import lombok.AllArgsConstructor;
import server.dao.GenreDao;
import server.entities.Genre;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
@AllArgsConstructor
public class GenreDaoImpl implements GenreDao {
    private final DataSource dataSource;

    @Override
    public Genre getById(Integer id) {
        String sql = "SELECT id, name FROM genre WHERE id = ?";
        try (Connection connection = dataSource.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Genre.builder()
                            .id(rs.getInt("id"))
                            .name(rs.getString("name"))
                            .build();
                }
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Genre getByName(String name) {
        String sql = "SELECT id, name FROM genre WHERE name = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, name);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Genre.builder()
                            .id(rs.getInt("id"))
                            .name(rs.getString("name"))
                            .build();
                }
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void saveOrUpdate(Genre genre) {
        String sql = "INSERT INTO genre (id, name) VALUES (?, ?) " +
                "ON CONFLICT (id) DO UPDATE SET name = EXCLUDED.name";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, genre.getId());
            ps.setString(2, genre.getName());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Genre> getByAnimeId(Long animeId) {
        String sql = "SELECT DISTINCT g.id, g.name " +
                "FROM genre g " +
                "JOIN anime_genre ag ON ag.genre_id = g.id " +
                "WHERE ag.anime_id = ?";
        List<Genre> list = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, animeId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(Genre.builder()
                            .id(rs.getInt("id"))
                            .name(rs.getString("name"))
                            .build());
                }
            }
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void saveAnimeGenre(Long animeId, Integer genreId) {
        String sql = "INSERT INTO anime_genre (anime_id, genre_id) VALUES (?, ?)";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, animeId);
            ps.setInt(2, genreId);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
