package ru.ifmo.se.lab4.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ifmo.se.lab4.domain.User;
import ru.ifmo.se.lab4.dto.RegistrationResponseDto;
import ru.ifmo.se.lab4.service.RegistrationService;

@RestController
@RequestMapping("/registration")
public class RegistrationController {

    private final RegistrationService registrationService;

    @Autowired
    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping
    public RegistrationResponseDto register(@RequestBody User user) {
        return registrationService.registerUser(user);
    }

}
