package ru.ifmo.se.lab4.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PointDto {

    private double x;

    private double y;

    private double r;

    private boolean isInArea;

}
