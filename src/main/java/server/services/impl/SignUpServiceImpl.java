package server.services.impl;

import lombok.AllArgsConstructor;
import server.dao.UserDao;
import server.dto.UserRegistrationDto;
import server.entities.User;
import server.services.SignUpService;
import server.util.PasswordUtil;


@AllArgsConstructor
public class SignUpServiceImpl implements SignUpService {
    private UserDao userDao;

    @Override
    public boolean signUp(UserRegistrationDto userRegistrationDto) {
        if (userDao.getByLogin(userRegistrationDto.getLogin()) != null){
            return false;
        }
        String name = userRegistrationDto.getName();
        String email = userRegistrationDto.getEmail();
        String login = userRegistrationDto.getLogin();
        String password = PasswordUtil.encrypt(userRegistrationDto.getPassword());
        User user = User
                .builder()
                .name(name)
                .email(email)
                .login(login)
                .password(password)
                .build();
        userDao.save(user);
        return true;

    }
}
