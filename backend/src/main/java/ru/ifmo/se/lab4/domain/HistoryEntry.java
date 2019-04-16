package ru.ifmo.se.lab4.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "history_entry")
@NoArgsConstructor
public class HistoryEntry {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Point point;

    private boolean isInArea;

    private double r;

    @ManyToOne
    private User owner;

}
