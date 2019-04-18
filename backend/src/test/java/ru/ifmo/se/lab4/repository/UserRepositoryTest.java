package ru.ifmo.se.lab4.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.ifmo.se.lab4.domain.User;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Test
    public void saveAndFindTest() {
        String username = "usernameRepoTest";
        String password = "passwordRepoTest";
        User user = new User(username, password);
        userRepository.save(user);

        User received = userRepository.findByUsername(username);
        assertNotEquals(null, received);
        assertEquals(username, received.getUsername());
        assertEquals(password, received.getPassword());
    }

}
