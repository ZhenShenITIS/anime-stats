package server.services;



import server.dto.UserDto;

import java.util.List;

public interface UserService {
    List<UserDto> getAll();

    UserDto getByEmail(String login);

    UserDto getById (Long id);

    boolean isUserAdmin (String email);

    boolean isUserAdmin (Long id);

    void update(UserDto userDto);

    void delete(Long id);
}
