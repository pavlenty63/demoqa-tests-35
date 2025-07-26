package tests.lesson_18;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.RegistrationPage;
import utils.GenerateFakeData;

import static com.codeborne.selenide.logevents.SelenideLogger.step;

@Tag("demoqa")
@DisplayName("Тесты заполнения формы регистрации")
public class TextBoxJenkinsTests {

  RegistrationPage registrationPage = new RegistrationPage();

  GenerateFakeData faker = new GenerateFakeData();

  String firstName = faker.gerRandomFirstName(),
          lastName = faker.getRandomLastName(),
          email = faker.getRandomEmail(),
          gender = faker.getRandomGender(),
          phone = faker.getRandomPhoneNumber(),
          year = faker.getRandomYear(),
          month = faker.getRandomMonth(),
          day = faker.getRandomDay(month),
          subject = faker.getRandomSubject(),
          hobby = faker.getRandomHobby(),
          picture = faker.getRandomPhoto(),
          address = faker.getRandomAddress(),
          state = faker.getRandomState(),
          city = faker.getRandomCity(state);

  @BeforeAll
  static void defaultConf() {
    Configuration.pageLoadStrategy = "eager";
    Configuration.browserSize = "1920x1080";
    Configuration.baseUrl = "https://demoqa.com";
    Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub";
    SelenideLogger.addListener("allure", new AllureSelenide());
  }

  @Test
  @DisplayName("Проверка заполнения обязательных полей")
  void minFillFormTest() {
    step("Открываем форму регистрации", () -> {
      registrationPage.openPage()
              .deleteBanner();
    });

    step("Заполняем обязательные поля", () -> {
      registrationPage.setFirstName(firstName)
              .setLastName(lastName)
              .setGender(gender)
              .setUserNumber(phone)
              .clickSubmit();
    });

    step("Открылась таблица с обязательными данными", () -> {
      registrationPage.checkPositiveResult("Student Name", firstName + " " + lastName)
              .checkPositiveResult("Gender", gender)
              .checkPositiveResult("Mobile", phone);
    });
  }

  @Test
  @DisplayName("Проверка заполнения всех полей")
  void fullFillFormTest() {
    step("Открываем форму регистрации", () -> {
      registrationPage.openPage()
              .deleteBanner();
    });

    step("Заполняем все поля", () -> {
      registrationPage.setFirstName(firstName)
              .setLastName(lastName)
              .setUserEmail(email)
              .setGender(gender)
              .setUserNumber(phone)
              .setBirthDate(day, month, year)
              .setSubject(subject)
              .setHobby(hobby)
              .uploadPicture(picture)
              .setAddress(address)
              .setState(state)
              .setCity(city)
              .clickSubmit();
    });

    step("Открылась таблица со всеми данными", () -> {
      registrationPage.checkPositiveResult("Student Name", firstName + " " + lastName)
              .checkPositiveResult("Student Email", email)
              .checkPositiveResult("Gender", gender)
              .checkPositiveResult("Mobile", phone)
              .checkPositiveResult("Date of Birth", day + " " + month + "," + year)
              .checkPositiveResult("Subjects", subject)
              .checkPositiveResult("Hobbies", hobby)
              .checkPositiveResult("Picture", picture)
              .checkPositiveResult("Address", address)
              .checkPositiveResult("State and City", state + " " + city);
    });
  }

  @Test
  @DisplayName("Проверка без заполнения обязательного поля - фамилии")
  void withoutLastNameFillFormTest() {
    step("Открываем форму регистрации", () -> {
      registrationPage.openPage()
              .deleteBanner();
    });

    step("Заполняем обязательные поля без указания фамилии", () -> {
      registrationPage.setFirstName(firstName)
              .setGender(gender)
              .setUserNumber(phone)
              .clickSubmit();
    });

    step("Форма не сохранилась", () -> {
      registrationPage.checkNegativeResult();
    });
  }
}
