package server.dao;



import server.entities.User;

import java.util.List;

public interface UserDao {
    List<User> getAll();

    void save(User user);

    User getById(Long id);

    User getByEmail(String email);

    boolean isUserAdmin (Long id);

    void addAdmin (Long id);

    void updateWithoutPassword (User user);

    void delete(Long id);

}
