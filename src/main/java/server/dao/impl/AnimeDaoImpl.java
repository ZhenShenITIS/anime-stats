package server.dao.impl;

import lombok.AllArgsConstructor;
import server.dao.AnimeDao;
import server.entities.Anime;
import server.entities.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class AnimeDaoImpl implements AnimeDao {
    private final Connection connection;

    @Override
    public Anime getById(Long id) {
        String sql = "SELECT * FROM anime where id = ?";
        Anime anime;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                anime = Anime
                        .builder()
                        .id(resultSet.getLong("id"))
                        .title(resultSet.getString("title"))
                        .picturePath(resultSet.getString("picture_path"))
                        .synopsis(resultSet.getString("synopsis"))
                        .rank(resultSet.getInt("rank"))
                        .score(resultSet.getDouble("score"))
                        .build();
                return anime;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public void saveOrUpdate(Anime anime) {
        String sql = "insert into anime (id, title, picture_path, synopsis, rank, score) values (?, ?, ?, ?, ?, ?)" +
                " ON conflict (id) do update set rank = EXCLUDED.rank, score = EXCLUDED.score";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, anime.getId());
            preparedStatement.setString(2, anime.getTitle());
            preparedStatement.setString(3, anime.getPicturePath());
            preparedStatement.setString(4, anime.getSynopsis());
            preparedStatement.setInt(5, anime.getRank());
            preparedStatement.setDouble(6, anime.getScore());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Anime> getByGenreId(Integer id, int limit) {
        String sql = "SELECT * FROM anime JOIN anime_genre on anime.id = anime_genre.anime_id JOIN genre on anime_genre.genre_id = genre.id where genre_id = ?";
        try {
            Statement statement = connection.createStatement();
            ResultSet  resultSet = statement.executeQuery(sql);
            List<Anime> animeList = new ArrayList<>();
            if (resultSet != null) {
                while (resultSet.next() && limit > 0) {
                    animeList.add(Anime
                            .builder()
                                    .id(resultSet.getLong("anime.id"))
                                    .title(resultSet.getString("title"))
                                    .picturePath(resultSet.getString("picture_path"))
                                    .synopsis(resultSet.getString("synopsis"))
                                    .rank(resultSet.getInt("rank"))
                                    .score(resultSet.getDouble("score"))
                            .build());
                    limit--;
                }
            }
            return animeList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
