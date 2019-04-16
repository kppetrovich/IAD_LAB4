package ru.ifmo.se.lab4.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ifmo.se.lab4.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

}
