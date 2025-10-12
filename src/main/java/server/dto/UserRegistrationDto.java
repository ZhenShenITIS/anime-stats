package server.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class UserRegistrationDto {
    private String name;
    private String email;
    private String login;
    private String password;
}
