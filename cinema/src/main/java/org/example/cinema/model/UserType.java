package org.example.cinema.model;

import org.springframework.security.core.GrantedAuthority;

public enum UserType implements GrantedAuthority{

    ROLE_ADMIN,
    ROLE_REGULAR;

    @Override
    public String getAuthority() {
        return name();
    }
}
