package ru.ifmo.se.lab4.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "usr")
public class User {

    @Id
    @GeneratedValue
    private Long id;

    private String username;

    private String password;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "owner")
    private List<HistoryEntry> history;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        history = new ArrayList<>();
    }

}
