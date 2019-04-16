package ru.ifmo.se.lab4.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import java.util.*;

@Getter
@Setter
@Entity
@Table(name = "user")
public class User extends org.springframework.security.core.userdetails.User {

    @Id
    @GeneratedValue
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "owner")
    private List<HistoryEntry> history;

    public User(String username, String password) {
        super(username, password, Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));
    }

}
