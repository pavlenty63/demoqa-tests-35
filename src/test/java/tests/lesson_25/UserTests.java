package tests.lesson_25;

import models.LoginResponseModel;
import models.UserModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import tests.TestApiBase;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static specs.UserActionsSpec.*;

@Tag("api")
@DisplayName("Тесты API с моделями и спецификациями")
public class UserTests extends TestApiBase {

  @Test
  @DisplayName("Получение информации о пользователе")
  void getUserInfoTest() {
    step("Выполнить запрос", () ->
            given(requestSpec)
                    .when()
                    .get("/users/3")
                    .then()
                    .spec(responseSpec200)
                    .body("data.first_name", equalTo("Emma"))
                    .body("data.last_name", equalTo("Wong"))
                    .body("data.email", equalTo("emma.wong@reqres.in")));
  }

  @Test
  @DisplayName("Обновление пользователя")
  void updateUserTest() {
    UserModel userData = new UserModel();
    userData.setFirstName("Elza");
    userData.setLastName("Bong");
    userData.setEmail("elza.bong@reqres.in");

    UserModel response = step("Выполнить запрос", () ->
            given(requestSpec)
                    .body(userData)
                    .when()
                    .put("/users/3")
                    .then()
                    .spec(responseSpec200)
                    .extract().as(UserModel.class));

    step("Проверить ответ", () -> {
      assertThat(response.getFirstName()).isEqualTo(userData.getFirstName());
      assertThat(response.getLastName()).isEqualTo(userData.getLastName());
      assertThat(response.getEmail()).isEqualTo(userData.getEmail());
      assertThat(response.getUpdatedAt()).isNotNull();
    });
  }

  @Test
  @DisplayName("Обновление данных пользователя")
  void updateUserInfoTest() {
    UserModel userData = new UserModel();
    userData.setJob("QA");

    UserModel response = step("Выполнить запрос", () ->
            given(requestSpec)
                    .body(userData)
                    .when()
                    .patch("/users/3")
                    .then()
                    .spec(responseSpec200)
                    .extract().as(UserModel.class));

    step("Проверить ответ", () -> {
      assertThat(response.getJob()).isEqualTo(userData.getJob());
      assertThat(response.getUpdatedAt()).isNotNull();
    });
  }

  @Test
  @DisplayName("Удаление пользователя")
  void deleteUserTest() {
    step("Выполнить запрос", () ->
            given(requestSpec)
                    .when()
                    .delete("/users/3")
                    .then()
                    .spec(responseSpec204));
  }

  @Test
  @DisplayName("Авторизация пользователя")
  void successfulLoginUserTest() {
    UserModel userData = new UserModel();
    userData.setEmail("emma.wong@reqres.in");
    userData.setPassword("12345");

    LoginResponseModel response = step("Выполнить запрос", () ->
            given(requestSpec)
                    .body(userData)
                    .when()
                    .post("/login")
                    .then()
                    .spec(responseSpec200)
                    .extract().as(LoginResponseModel.class));

    step("Проверить ответ", () ->
            assertEquals("QpwL5tke4Pnpja7X3", response.getToken()));

  }
}
