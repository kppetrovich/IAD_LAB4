package ru.ifmo.se.lab4.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.ifmo.se.lab4.domain.User;
import ru.ifmo.se.lab4.dto.RegistrationResponseDto;
import ru.ifmo.se.lab4.repository.UserRepository;

@Service
public class RegistrationService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public RegistrationService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public RegistrationResponseDto registerUser(User user) {
        User received = userRepository.findByUsername(user.getUsername());
        if (received != null) {
            return new RegistrationResponseDto(false, "Username is already taken");
        }

        if (user.getUsername().length() < 3) {
            return new RegistrationResponseDto(false, "Username's length can't be less than 3");
        }
        if (user.getPassword().length() < 3) {
            return new RegistrationResponseDto(false, "Password's length can't be less than 3");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);

        return new RegistrationResponseDto(true, "You've been successfully registered");
    }

}
