package server.services.impl;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import server.dao.UserDao;
import server.dto.UserDto;
import server.entities.User;
import server.services.UserService;

import java.util.List;

@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private UserDao userDao;

    @Override
    public List<UserDto> getAll() {
        return userDao.getAll().stream().map(u-> UserDto
                .builder()
                        .email(u.getEmail())
                        .name(u.getName())
                        .login(u.getLogin())
                .build())
                .toList();
    }

    @Override
    public UserDto getByLogin(String login) {
        User user = userDao.getByLogin(login);
        if (user != null) {
            return UserDto
                    .builder()
                    .login(user.getLogin())
                    .email(user.getEmail())
                    .name(user.getName())
                    .build();
        }
        return null;
    }


}
