package server.dao.impl;

import lombok.AllArgsConstructor;
import server.dao.UserDao;
import server.entities.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class UserDaoImpl implements UserDao {

    private final DataSource dataSource;

    @Override
    public List<User> getAll() {
        String sql = "SELECT * FROM users";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            List<User> users = new ArrayList<>();

            while (resultSet.next()) {
                users.add(User
                        .builder()
                        .name(resultSet.getString("name"))
                        .id(resultSet.getLong("id"))
                        .email(resultSet.getString("email"))
                        .password(resultSet.getString("password"))
                        .build());
            }

            return users;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save(User user) {
        String sql = "insert into users (name, email, password) values (?, ?, ?)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public User getById(Integer id) {
        return null;
    }

    @Override
    public User getByEmail(String email) {
        String sql = "SELECT * FROM users where email = ?";
        User user;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, email);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    user = User
                            .builder()
                            .name(resultSet.getString("name"))
                            .id(resultSet.getLong("id"))
                            .email(resultSet.getString("email"))
                            .password(resultSet.getString("password"))
                            .build();
                    return user;
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
