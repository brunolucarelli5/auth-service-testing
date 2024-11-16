package com.testing.backend.simulation;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

import io.gatling.javaapi.core.Simulation;
import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.http.HttpProtocolBuilder;

public class AuthSimulation extends Simulation {

    HttpProtocolBuilder httpProtocol = http
        .baseUrl("http://localhost:8080") // Base URL de tu aplicación
        .acceptHeader("application/json");

    ScenarioBuilder registerUserScenario = scenario("Register User Scenario")
        .exec(
            http("Register User Request")
                .post("/auth/register")
                .body(StringBody("{\"name\": \"John\", \"lastname\": \"Doe\", \"username\": \"johndoe\", \"email\": \"johndoe@example.com\", \"password\": \"password\", \"role\": \"CEO\"}"))
                .asJson()
                .check(status().is(200))
        );

    ScenarioBuilder loginUserScenario = scenario("Login User Scenario")
        .exec(
            http("Login User Request")
                .post("/auth/login")
                .body(StringBody("{\"email\": \"brunolucarelli5@gmail.com\", \"password\": \"bruno12345\"}"))
                .asJson()
                .check(status().is(200))
        );

    {
        setUp(
            registerUserScenario.injectOpen(rampUsers(1000).during(10)),
            loginUserScenario.injectOpen(rampUsers(1000).during(10))
        ).protocols(httpProtocol);
    }
}