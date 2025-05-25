import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class TextBoxTests {

  @BeforeAll
  static void beforeAll() {
    Configuration.pageLoadStrategy = "eager";
    Configuration.browserSize = "1920x1080";
    Configuration.baseUrl = "https://demoqa.com";
  }

  @Test
  void fillFormTest() {
    open("/automation-practice-form");
    $("#firstName").setValue("User");
    $("#lastName").setValue("Test");
    $("#userEmail").setValue("test@mail.ru");
    $("[for=gender-radio-1]").click();
    $("#userNumber").setValue("9909909999");
    $("#dateOfBirthInput").click();
    $(".react-datepicker__year-select").selectOption("2000");
    $(".react-datepicker__month-select").selectOption("January");
    $(".react-datepicker__day--017").click();
    $("#subjectsInput").setValue("English").pressEnter();
    $("[for=hobbies-checkbox-2]").click();
    $("#uploadPicture").uploadFromClasspath("picture.jpg");
    $("#currentAddress").setValue("Some street 1");
    $("#react-select-3-input").setValue("NCR").pressEnter();
    $("#react-select-4-input").setValue("Delhi").pressEnter();

    $("#submit").click();

    $(".table-responsive").$(byText("Student Name")).parent().shouldHave(text("User Test"));
    $(".table-responsive").$(byText("Student Email")).parent().shouldHave(text("test@mail.ru"));
    $(".table-responsive").$(byText("Gender")).parent().shouldHave(text("Male"));
    $(".table-responsive").$(byText("Mobile")).parent().shouldHave(text("9909909999"));
    $(".table-responsive").$(byText("Date of Birth")).parent().shouldHave(text("17 January,2000"));
    $(".table-responsive").$(byText("Subjects")).parent().shouldHave(text("English"));
    $(".table-responsive").$(byText("Hobbies")).parent().shouldHave(text("Reading"));
    $(".table-responsive").$(byText("Picture")).parent().shouldHave(text("picture.jpg"));
    $(".table-responsive").$(byText("Address")).parent().shouldHave(text("Some street 1"));
    $(".table-responsive").$(byText("State and City")).parent().shouldHave(text("NCR Delhi"));
  }
}
