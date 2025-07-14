package tests.lesson_15;

import com.codeborne.selenide.Configuration;
import data.StatusCodeName;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.Condition.exactTextCaseSensitive;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class ParameterizedDemoQATests {

  @BeforeAll
  static void defaultConf() {
    Configuration.pageLoadStrategy = "eager";
    Configuration.browserSize = "1920x1080";
    Configuration.baseUrl = "https://demoqa.com";
  }

  @DisplayName("Проверка формы загрузки файлов")
  @ValueSource(strings = {"image.jpg", "picture.jpg"})
  @ParameterizedTest(name = "Проверка загрузки файла {0}")
  void uploadFileTest(String fileName) {
    open("/upload-download");
    $(".text-center").shouldHave(exactTextCaseSensitive("Upload and Download"));
    executeJavaScript("$('#fixedban').remove()");
    executeJavaScript("$('footer').remove()");

    $("#uploadFile").uploadFromClasspath(fileName);

    $("#uploadedFilePath").shouldHave(text("C:\\fakepath\\" + fileName));
  }

  @DisplayName("Проверка активных переключателей")
  @CsvSource({
          "Yes ,  [for=yesRadio]",
          "Impressive , [for=impressiveRadio]"
  })
  @ParameterizedTest(name = "Проверка нажатия переключателя {0}")
  void switchEnabledButtonsTest(String button, String elementSelector) {
    open("/radio-button");
    $(".text-center").shouldHave(exactTextCaseSensitive("Radio Button"));
    executeJavaScript("$('#fixedban').remove()");
    executeJavaScript("$('footer').remove()");

    $(elementSelector).click();

    $(".text-success").shouldHave(exactTextCaseSensitive(button));
  }

  @DisplayName("Проверка описания статус-кодов")
  @EnumSource(StatusCodeName.class)
  @ParameterizedTest
  void checkCodeDescriptionTest(StatusCodeName statusName) {
    open("/links");
    $(".text-center").shouldHave(exactTextCaseSensitive("Links"));
    executeJavaScript("$('#fixedban').remove()");
    executeJavaScript("$('footer').remove()");

    $$("a").find(text(statusName.description)).click();

    $("#linkResponse").shouldHave(text(statusName.description));
  }
}