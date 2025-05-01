package com.group.greatreadsbe.dto.auth;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponseDTO {

    private Long id;
    private String email;
    private String role;
    private String token;

    public LoginResponseDTO(String email, String token, Long id, String role) {
        this.email = email;
        this.token = token;
        this.id = id;
        this.role = role;
    }
}
