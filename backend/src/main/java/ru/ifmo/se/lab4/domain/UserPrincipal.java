package ru.ifmo.se.lab4.domain;

import lombok.Data;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;

@Data
public class UserPrincipal extends org.springframework.security.core.userdetails.User {

    private final User user;

    public UserPrincipal(User user) {
        super(user.getUsername(), user.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));
        this.user = user;
    }

}
