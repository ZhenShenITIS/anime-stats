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
                        .id(u.getId())
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
                    .id(user.getId())
                    .email(user.getEmail())
                    .name(user.getName())
                    .build();
        }
        return null;
    }

    @Override
    public boolean isUserAdmin(String email) {
        User user = userDao.getByEmail(email);
        if (user == null) {
            return false;
        }
        return userDao.isUserAdmin(user.getId());
    }


    @Override
    public boolean isUserAdmin(Long id) {
        return userDao.isUserAdmin(id);
    }

    @Override
    public void update(UserDto userDto) {
        userDao.updateWithoutPassword(User
                .builder()
                        .name(userDto.getName())
                        .email(userDto.getEmail())
                        .id(userDto.getId())
                .build());
    }

    @Override
    public void delete(Long id) {
        userDao.delete(id);
    }

    @Override
    public UserDto getById(Long id) {
        User user = userDao.getById(id);
        return UserDto
                .builder()
                .email(user.getEmail())
                .name(user.getName())
                .id(user.getId())
                .build();
    }
}
