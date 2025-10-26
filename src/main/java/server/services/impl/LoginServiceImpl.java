package server.services.impl;

import lombok.AllArgsConstructor;
import server.dao.UserDao;
import server.dto.UserDto;
import server.dto.UserLoginDto;
import server.entities.User;
import server.services.LoginService;
import server.util.PasswordUtil;

@AllArgsConstructor
public class LoginServiceImpl implements LoginService {
    private UserDao userDao;
    @Override
    public UserDto login(UserLoginDto userLoginDto) {
        String email = userLoginDto.getEmail();
        String passwordHash = PasswordUtil.encrypt(userLoginDto.getPassword());
        User user = userDao.getByEmail(email);
        if (user != null && user.getPassword().equals(passwordHash)) {
            return UserDto
                    .builder()
                    .id(user.getId())
                    .name(user.getName())
                    .email(user.getEmail())
                    .build();

        }
        return null;
    }
}
