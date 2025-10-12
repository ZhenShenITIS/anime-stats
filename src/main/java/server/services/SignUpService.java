package server.services;

import server.dto.UserRegistrationDto;

public interface SignUpService {
    boolean signUp (UserRegistrationDto userRegistrationDto);
}
