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
                .build())
                .toList();
    }

    @Override
    public UserDto getByEmail(String email) {
        User user = userDao.getByEmail(email);
        if (user != null) {
            return UserDto
                    .builder()
                    .email(user.getEmail())
                    .name(user.getName())
                    .build();
        }
        return null;
    }


}
