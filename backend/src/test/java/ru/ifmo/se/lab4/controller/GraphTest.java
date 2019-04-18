package ru.ifmo.se.lab4.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.ifmo.se.lab4.domain.HistoryEntry;
import ru.ifmo.se.lab4.dto.PointDto;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GraphTest {

    @BeforeClass
    public static void init() {
        RestAssured.baseURI = Data.BASE_URI;
        RestAssured.port = Data.PORT;
    }

    @Test
    public void historyEntrySerializationTest() throws IOException {
        String username = "usernameSerializationTest";
        String password = "passwordSerializationTest";

        AuthTest authTest = new AuthTest();
        authTest.register(username, password);

        RequestSpecification request = RestAssured.given();
        request.formParam("username", username);
        request.formParam("password", password);
        Response response = request.post("/login");

        assertEquals(302, response.getStatusCode());
        String jSessionId = response.getCookie("JSESSIONID");

        PointDto pointDto = new PointDto(1.0, 2.0, 3.0, true);

        response = addSamplePoint(jSessionId, pointDto);
        assertEquals(200, response.getStatusCode());

        ObjectMapper objectMapper = new ObjectMapper();
        HistoryEntry historyEntry = objectMapper.readValue(response.body().asString(), HistoryEntry.class);

        assertEquals(pointDto.getR(), historyEntry.getR(), 0.001);
        assertEquals(pointDto.getX(), historyEntry.getPoint().getX(), 0.001);
        assertEquals(pointDto.getY(), historyEntry.getPoint().getY(), 0.001);
    }

    private Response addSamplePoint(String jSessionId, PointDto pointDto) {
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        request.cookie("JSESSIONID", jSessionId);
        request.body(pointDto);
        return request.post("/graph/add-point");
    }

}
