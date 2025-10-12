package server.dao;



import server.entities.User;

import java.util.List;

public interface UserDao {
    List<User> getAll();

    void save(User user);

    User getById(Integer id);

    User getByEmail(String email);

}
