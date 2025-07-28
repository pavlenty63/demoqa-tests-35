package tests.lesson_24;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tests.TestApiBase;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;

@DisplayName("Простые тесты API")
public class SimpleRestApiTests extends TestApiBase {
  String api_key = "reqres-free-v1";
  String users_path = "/users";
  String login_path = "/login";

  @Test
  @DisplayName("Получение информации о пользователе")
  void getUserInfoTest() {
    given()
            .header("x-api-key", api_key)
            .log().uri()
            .log().body()
            .when()
            .get(users_path + "/3")
            .then()
            .statusCode(200)
            .body("data.first_name", equalTo("Emma"))
            .body("data.last_name", equalTo("Wong"))
            .body("data.email", equalTo("emma.wong@reqres.in"))
            .log().status()
            .log().body();
  }

  @Test
  @DisplayName("Обновление пользователя")
  void updateUserTest() {
    String userData = "{\"first_name\": \"Elza\", \"last_name\": \"Bong\", \"email\": \"elza.bong@reqres.in\"}";

    given()
            .header("x-api-key", api_key)
            .contentType(JSON)
            .body(userData)
            .log().uri()
            .log().body()
            .when()
            .put(users_path + "/3")
            .then()
            .statusCode(200)
            .body("first_name", equalTo("Elza"))
            .body("last_name", equalTo("Bong"))
            .body("email", equalTo("elza.bong@reqres.in"))
            .body("updatedAt", notNullValue())
            .log().status()
            .log().body();
  }

  @Test
  @DisplayName("Обновление данных пользователя")
  void updateUserInfoTest() {
    String userData = "{\"job\": \"QA\"}";

    given()
            .header("x-api-key", api_key)
            .contentType(JSON)
            .body(userData)
            .log().uri()
            .log().body()
            .when()
            .patch(users_path + "/3")
            .then()
            .statusCode(200)
            .body("job", equalTo("QA"))
            .body("updatedAt", notNullValue())
            .log().status()
            .log().body();
  }

  @Test
  @DisplayName("Удаление пользователя")
  void deleteUserTest() {
    given()
            .header("x-api-key", api_key)
            .log().uri()
            .log().body()
            .when()
            .delete(users_path + "/3")
            .then()
            .statusCode(204)
            .log().status()
            .log().body();
  }

  @Test
  @DisplayName("Авторизация несуществующего пользователя")
  void loginUnregisteredUserTest() {
    String userData = "{\"username\": \"Elza\", \"email\": \"elza.bong@reqres.in\", \"password\": \"12345\"}";

    given()
            .header("x-api-key", api_key)
            .contentType(JSON)
            .body(userData)
            .log().uri()
            .log().body()
            .when()
            .post(login_path)
            .then()
            .statusCode(400)
            .body("error", equalTo("user not found"))
            .log().status()
            .log().body();
  }
}
