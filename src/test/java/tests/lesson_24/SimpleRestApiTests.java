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
            .when()
            .get(users_path + "/3")
            .then().log().ifValidationFails()
            .statusCode(200)
            .body("data.first_name", equalTo("Emma"))
            .body("data.last_name", equalTo("Wong"))
            .body("data.email", equalTo("emma.wong@reqres.in"));
  }

  @Test
  @DisplayName("Обновление пользователя")
  void updateUserTest() {
    String userData = "{\"first_name\": \"Elza\", \"last_name\": \"Bong\", \"email\": \"elza.bong@reqres.in\"}";

    given().log().body()
            .header("x-api-key", api_key)
            .contentType(JSON)
            .body(userData)
            .when()
            .put(users_path + "/3")
            .then().log().body()
            .statusCode(200)
            .body("first_name", equalTo("Elza"))
            .body("last_name", equalTo("Bong"))
            .body("email", equalTo("elza.bong@reqres.in"))
            .body("updatedAt", notNullValue());
  }

  @Test
  @DisplayName("Обновление данных пользователя")
  void updateUserInfoTest() {
    String userData = "{\"job\": \"QA\"}";

    given().log().body()
            .header("x-api-key", api_key)
            .contentType(JSON)
            .body(userData)
            .when()
            .patch(users_path + "/3")
            .then().log().body()
            .statusCode(200)
            .body("job", equalTo("QA"))
            .body("updatedAt", notNullValue());
  }

  @Test
  @DisplayName("Удаление пользователя")
  void deleteUserTest() {
    given()
            .header("x-api-key", api_key)
            .when()
            .delete(users_path + "/3")
            .then().log().ifValidationFails()
            .statusCode(204);
  }

  @Test
  @DisplayName("Авторизация пользователя")
  void loginUnregisteredUserTest() {
    String userData = "{\"email\": \"emma.wong@reqres.in\", \"password\": \"12345\"}";

    given().log().body()
            .header("x-api-key", api_key)
            .contentType(JSON)
            .body(userData)
            .when()
            .post(login_path)
            .then().log().ifStatusCodeIsEqualTo(400)
            .statusCode(200)
            .body("token", notNullValue());

  }
}
