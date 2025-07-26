package tests.lesson_18;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.RegistrationPage;
import utils.GenerateFakeData;

@Tag("demoqa")
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
  }

  @Test
  void minFillFormTest() {
    registrationPage.openPage()
            .deleteBanner()
            .setFirstName(firstName)
            .setLastName(lastName)
            .setGender(gender)
            .setUserNumber(phone)
            .clickSubmit()

            .checkPositiveResult("Student Name", firstName + " " + lastName)
            .checkPositiveResult("Gender", gender)
            .checkPositiveResult("Mobile", phone);
  }

  @Test
  void fullFillFormTest() {
    registrationPage.openPage()
            .deleteBanner()
            .setFirstName(firstName)
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
            .clickSubmit()

            .checkPositiveResult("Student Name", firstName + " " + lastName)
            .checkPositiveResult("Student Email", email)
            .checkPositiveResult("Gender", gender)
            .checkPositiveResult("Mobile", phone)
            .checkPositiveResult("Date of Birth", day + " " + month + "," + year)
            .checkPositiveResult("Subjects", subject)
            .checkPositiveResult("Hobbies", hobby)
            .checkPositiveResult("Picture", picture)
            .checkPositiveResult("Address", address)
            .checkPositiveResult("State and City", state + " " + city);
  }

  @Test
  void withoutLastNameFillFormTest() {
    registrationPage.openPage()
            .deleteBanner()
            .setFirstName(firstName)
            .setGender(gender)
            .setUserNumber(phone)
            .clickSubmit()

            .checkNegativeResult();
  }
}
