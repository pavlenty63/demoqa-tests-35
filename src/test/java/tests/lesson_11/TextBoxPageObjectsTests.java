package tests.lesson_11;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pages.RegistrationPage;

public class TextBoxPageObjectsTests {

  RegistrationPage registrationPage = new RegistrationPage();

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
            .setFirstName("User")
            .setLastName("Test")
            .setGender("Male")
            .setUserNumber("9909909999")
            .clickSubmit()

            .checkPositiveResult("Student Name", "User Test")
            .checkPositiveResult("Gender", "Male")
            .checkPositiveResult("Mobile", "9909909999");
  }

  @Test
  void fullFillFormTest() {
    registrationPage.openPage()
            .deleteBanner()
            .setFirstName("User")
            .setLastName("Test")
            .setUserEmail("test@mail.ru")
            .setGender("Male")
            .setUserNumber("9909909999")
            .setBirthDate("17", "January", "2000")
            .setSubject("English")
            .setHobby("Reading")
            .uploadPicture("picture.jpg")
            .setAddress("Some street 1")
            .setState("NCR")
            .setCity("Delhi")
            .clickSubmit()

            .checkPositiveResult("Student Name", "User Test")
            .checkPositiveResult("Student Email", "test@mail.ru")
            .checkPositiveResult("Gender", "Male")
            .checkPositiveResult("Mobile", "9909909999")
            .checkPositiveResult("Date of Birth", "17 January,2000")
            .checkPositiveResult("Subjects", "English")
            .checkPositiveResult("Hobbies", "Reading")
            .checkPositiveResult("Picture", "picture.jpg")
            .checkPositiveResult("Address", "Some street 1")
            .checkPositiveResult("State and City", "NCR Delhi");
  }

  @Test
  void withoutLastNameFillFormTest() {
    registrationPage.openPage()
            .deleteBanner()
            .setFirstName("User")
            .setGender("Male")
            .setUserNumber("9909909999")
            .clickSubmit()

            .checkNegativeResult();
  }
}
