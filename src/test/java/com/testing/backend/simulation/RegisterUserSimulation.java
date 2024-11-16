package com.testing.backend.simulation;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

import io.gatling.javaapi.core.Simulation;
import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.http.HttpProtocolBuilder;

public class RegisterUserSimulation extends Simulation {

    HttpProtocolBuilder httpProtocol = http
        .baseUrl("http://localhost:8080") // Base URL of your application
        .acceptHeader("application/json");

    ScenarioBuilder scn = scenario("Register User Scenario")
        .exec(
            http("Register User Request")
                .post("/auth/register")
                .body(StringBody("{\"name\": \"John\", \"lastname\": \"Doe\", \"username\": \"johndoe\", \"email\": \"johndoe@example.com\", \"password\": \"password\", \"role\": \"CEO\"}"))
                .asJson()
                .check(status().is(200))
        );

    {
        setUp(
            scn.injectOpen(rampUsers(1000).during(10))
        ).protocols(httpProtocol);
    }
}