package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserApiIT {

    @LocalServerPort
    private int port;

    @Test
    public void getUser_returns200_andMatchesSchema() {
        given()
            .port(port)
        .when()
            .get("/users/1")
        .then()
            .statusCode(200)
            .body("name", equalTo("Alice"))
            .body(matchesJsonSchemaInClasspath("schemas/user-schema.json"));
    }

    @Test
    public void getUser_returns404_whenUserNotFound() {
        given()
            .port(port)
        .when()
            .get("/users/99999")
        .then()
            .statusCode(404);
    }

    @Test
    public void createUser_returns201Status() {
        Map<String, Object> request = new HashMap<>();
        request.put("name", "NewUser");
        request.put("email", "new@example.com");

        given()
            .port(port)
            .contentType("application/json")
            .body(request)
        .when()
            .post("/users")
        .then()
            .statusCode(201)
            .body("id", equalTo(100))
            .body("name", equalTo("NewUser"));
    }
}
