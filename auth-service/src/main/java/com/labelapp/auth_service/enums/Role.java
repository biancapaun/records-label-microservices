package com.labelapp.auth_service.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Role {
    ADMIN("ADMIN"), GUEST("GUEST");

    private final String role;
}