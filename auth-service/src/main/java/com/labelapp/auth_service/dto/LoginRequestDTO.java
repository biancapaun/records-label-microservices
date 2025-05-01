package com.group.greatreadsbe.dto.auth;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class LoginRequestDTO {
    @NotNull(message = "email must be not-null")
    @Email
    private String email;
    private String password;
}
