package ru.ifmo.se.lab4.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
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
    @JoinColumn(name = "owner_id", nullable = false)
    private User owner;

    public HistoryEntry(Point point, double r) {
        this.point = point;
        this.r = r;
    }

}
