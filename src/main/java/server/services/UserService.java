package server.services;



import server.dto.UserDto;

import java.util.List;

public interface UserService {
    List<UserDto> getAll();

    UserDto getByLogin(String login);
}
