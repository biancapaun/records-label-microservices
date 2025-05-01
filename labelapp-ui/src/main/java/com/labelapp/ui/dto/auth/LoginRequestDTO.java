package com.labelapp.ui.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequestDTO {
    @NotNull(message = "email must be not-null")
    @Email
    private String email;
    private String password;
}
