package server.services;

import server.dto.UserDto;
import server.dto.UserLoginDto;

public interface LoginService {
    UserDto login (UserLoginDto userLoginDto);
}
