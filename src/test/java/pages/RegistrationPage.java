package pages;

import com.codeborne.selenide.SelenideElement;
import pages.components.CalendarComponent;
import pages.components.ResultsTableComponent;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationPage {

  CalendarComponent calendarComponent = new CalendarComponent();
  ResultsTableComponent resultsTableComponent = new ResultsTableComponent();

  private SelenideElement
          firstNameInput = $("#firstName"),
          lastNameInput = $("#lastName"),
          userEmailInput = $("#userEmail"),
          genderWrapper = $("#genterWrapper"),
          userNumberInput = $("#userNumber"),
          calendarInput = $("#dateOfBirthInput"),
          subjectInput = $("#subjectsInput"),
          hobbiesWrapper = $("#hobbiesWrapper"),
          uploadPicture = $("#uploadPicture"),
          addressInput = $("#currentAddress"),
          stateInput = $("#react-select-3-input"),
          cityInput = $("#react-select-4-input"),
          submit = $("#submit");

  public RegistrationPage openPage() {
    open("/automation-practice-form");
    $(".practice-form-wrapper").shouldHave(text("Student Registration Form"));

    return this;
  }

  public RegistrationPage deleteBanner() {
    executeJavaScript("$('#fixedban').remove()");
    executeJavaScript("$('footer').remove()");

    return this;
  }

  public RegistrationPage setFirstName(String value) {
    firstNameInput.setValue(value);

    return this;
  }

  public RegistrationPage setLastName(String value) {
    lastNameInput.setValue(value);

    return this;
  }

  public RegistrationPage setUserEmail(String value) {
    userEmailInput.setValue(value);

    return this;
  }

  public RegistrationPage setGender(String value) {
    genderWrapper.$(byText(value)).click();

    return this;
  }

  public RegistrationPage setUserNumber(String value) {
    userNumberInput.setValue(value);

    return this;
  }

  public RegistrationPage setBirthDate(String day, String month, String year) {
    calendarInput.click();
    calendarComponent.setDate(day, month, year);

    return this;
  }

  public RegistrationPage setSubject(String value) {
    subjectInput.setValue(value).pressEnter();

    return this;
  }

  public RegistrationPage setHobby(String value) {
    hobbiesWrapper.$(byText(value)).click();

    return this;
  }

  public RegistrationPage uploadPicture(String value) {
    uploadPicture.uploadFromClasspath(value);

    return this;
  }

  public RegistrationPage setAddress(String value) {
    addressInput.setValue(value);

    return this;
  }

  public RegistrationPage setState(String value) {
    stateInput.setValue(value).pressEnter();

    return this;
  }

  public RegistrationPage setCity(String value) {
    cityInput.setValue(value).pressEnter();

    return this;
  }

  public RegistrationPage clickSubmit() {
    submit.click();

    return this;
  }

  public RegistrationPage checkPositiveResult(String label, String value) {
    resultsTableComponent.submitedResult(label, value);

    return this;
  }

  public RegistrationPage checkNegativeResult() {
    resultsTableComponent.notSubmitedResult();

    return this;
  }
}
