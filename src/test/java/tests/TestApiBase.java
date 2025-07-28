package tests;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public class TestApiBase {
  @BeforeAll
  static void defaultConfig() {
    RestAssured.baseURI = "https://reqres.in";
    RestAssured.basePath = "/api";
  }
}