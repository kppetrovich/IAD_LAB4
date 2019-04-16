package ru.ifmo.se.lab4.repository;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.ifmo.se.lab4.domain.User;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AuthTest {

    @BeforeClass
    public static void init() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 27525;
    }

    @Test
    public void registerAndLoginTest() {
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        request.body(new User("username", "password"));

        Response response = request.post("/registration");
        assertEquals(200, response.getStatusCode());
        assertEquals("{\"success\":true,\"message\":\"You've been successfully registered\"}",
                response.body().print());
    }

    @Test
    public void usernameAlreadyExistsTest() {
        String username = "usernameExists";
        String password = "passwordExists";

        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        request.body(new User(username, password));

        Response response = request.post("/registration");

        assertEquals(200, response.getStatusCode());
        assertEquals("{\"success\":true,\"message\":\"You've been successfully registered\"}",
                response.body().print());

        RequestSpecification requestSecond = RestAssured.given();
        requestSecond.header("Content-Type", "application/json");
        requestSecond.body(new User(username, password));

        Response responseSecond = requestSecond.post("/registration");

        assertEquals(200, responseSecond.getStatusCode());
        assertEquals("{\"success\":false,\"message\":\"Username is already taken\"}",
                responseSecond.body().print());
    }

}
