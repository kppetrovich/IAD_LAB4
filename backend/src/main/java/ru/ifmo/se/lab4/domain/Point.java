package ru.ifmo.se.lab4.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "point")
@NoArgsConstructor
public class Point {

    @Id
    @GeneratedValue
    private Long id;

    private double x;

    private double y;

}
