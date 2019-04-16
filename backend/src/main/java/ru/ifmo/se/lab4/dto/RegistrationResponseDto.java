package ru.ifmo.se.lab4.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegistrationResponseDto {

    private boolean success;

    private String message;

}
