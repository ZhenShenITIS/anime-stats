package server.dao.impl;

import lombok.AllArgsConstructor;
import server.dao.AnimeDao;
import server.entities.Anime;
import server.entities.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class AnimeDaoImpl implements AnimeDao {
    private final DataSource dataSource;

    @Override
    public Anime getById(Long id) {
        String sql = "SELECT * FROM anime where id = ?";
        Anime anime;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)
             ){

            preparedStatement.setLong(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()){
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

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)){

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
        String sql = "SELECT " +
                "anime.id as a_id, " +
                "anime.title as a_title, " +
                "anime.picture_path as a_picture_path, " +
                "anime.synopsis as a_synopsis, " +
                "anime.rank as a_rank, " +
                "anime.score as a_score " +
                "FROM anime " +
                "JOIN anime_genre on anime.id = anime_genre.anime_id " +
                "JOIN genre on anime_genre.genre_id = genre.id " +
                "where genre_id = ? " +
                "ORDER BY anime.score DESC " +
                "LIMIT ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ){
            statement.setInt(1, id);
            statement.setInt(2, limit);
            List<Anime> animeList = new ArrayList<>();

            try (ResultSet  resultSet = statement.executeQuery()){
                if (resultSet != null) {
                    while (resultSet.next()) {
                        animeList.add(Anime
                                .builder()
                                .id(resultSet.getLong("a_id"))
                                .title(resultSet.getString("a_title"))
                                .picturePath(resultSet.getString("a_picture_path"))
                                .synopsis(resultSet.getString("a_synopsis"))
                                .rank(resultSet.getInt("a_rank"))
                                .score(resultSet.getDouble("a_score"))
                                .build());
                    }
                }
            }
            return animeList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
