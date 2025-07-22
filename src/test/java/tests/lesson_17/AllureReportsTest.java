package tests.lesson_17;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import steps.WebSteps;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.logevents.SelenideLogger.step;
import static org.openqa.selenium.By.linkText;

@DisplayName("Тесты отчетности прохождения шагов")
public class AllureReportsTest {

  @BeforeAll
  static void defaultConf() {
    Configuration.pageLoadStrategy = "eager";
    Configuration.browserSize = "1920x1080";
    Configuration.baseUrl = "https://github.com";
  }

  private static final String REPO = "pavlenty63/demoqa-tests-35";
  private static final int ISSUE = 1;

  @Test
  @DisplayName("Проверяем наличие Issue на Selenide")
  public void issueSelenideSearchTest() {
    SelenideLogger.addListener("allure", new AllureSelenide());

    open("/");

    $(".search-input").click();
    $("#query-builder-test").setValue(REPO).pressEnter();

    $(linkText(REPO)).click();
    $("#issues-tab").click();
    $(withText("#" + ISSUE)).should(Condition.exist);
  }

  @Test
  @DisplayName("Проверяем наличие Issue с Lambda-функцией")
  public void issueLambdaStepSearchTest() {
    SelenideLogger.addListener("allure", new AllureSelenide());

    step("Открываем главную страницу", () -> {
      open("/");
    });

    step("Ищем репозиторий" + REPO, () -> {
      $(".search-input").click();
      $("#query-builder-test").setValue(REPO).pressEnter();
    });

    step("Кликаем на найденный репозиторий" + REPO, () -> {
      $(linkText(REPO)).click();
    });

    step("Открываем вкладку Issues", () -> {
      $("#issues-tab").click();
    });

    step("Проверяем наличие Issue с номером " + ISSUE, () -> {
      $(withText("#1")).should(Condition.exist);
    });
  }

  @Test
  @DisplayName("Проверяем наличие Issue с аннотацией Step")
  public void issueAnnotationStepSearchTest() {
    SelenideLogger.addListener("allure", new AllureSelenide());
    WebSteps steps = new WebSteps();

    steps.openMainPage();
    steps.searchForRepository(REPO);
    steps.clickOnRepositoryLink(REPO);
    steps.openIssuesTab();
    steps.shouldSeeIssueWithNumber(ISSUE);
  }
}
