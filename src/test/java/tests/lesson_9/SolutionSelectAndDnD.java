package tests.lesson_9;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.DragAndDropOptions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exactTextCaseSensitive;
import static com.codeborne.selenide.Condition.textCaseSensitive;
import static com.codeborne.selenide.DragAndDropOptions.to;
import static com.codeborne.selenide.Selectors.byTagAndText;
import static com.codeborne.selenide.Selectors.withTagAndText;
import static com.codeborne.selenide.Selenide.*;

public class SolutionSelectAndDnD {

  @BeforeAll
  static void configuration() {
    Configuration.pageLoadStrategy = "eager";
    Configuration.browserSize = "1920x1080";
//    Configuration.holdBrowserOpen = true;
  }

  @Test
  void solutionSelect() {
    open("https://github.com/home");
    $(byTagAndText("button", "Solutions")).hover();
    $(byTagAndText("a", "Enterprises")).click();
    $("[data-testid=SubNav-root-heading]").shouldHave(textCaseSensitive("Enterprise"));
    //Пример из задания
    $("#hero-section-brand-heading").shouldHave(textCaseSensitive("The AI-powered developer platform"));
  }

  @Test
  void displacementByActions() {
    open("https://the-internet.herokuapp.com/drag_and_drop");
    $("#column-a").shouldHave(exactTextCaseSensitive("A"));
    $("#column-b").shouldHave(exactTextCaseSensitive("B"));
    actions().moveToElement($("#column-a")).clickAndHold().moveToElement($("#column-b")).release().perform();
    $("#column-a").shouldHave(exactTextCaseSensitive("B"));
    $("#column-b").shouldHave(exactTextCaseSensitive("A"));
  }

  @Test
  void displacementByDragAndDrop() {
    open("https://the-internet.herokuapp.com/drag_and_drop");
    $("#column-a").shouldHave(exactTextCaseSensitive("A"));
    $("#column-b").shouldHave(exactTextCaseSensitive("B"));
    $("#column-a").dragAndDrop(to("#column-b"));
    $("#column-a").shouldHave(exactTextCaseSensitive("B"));
    $("#column-b").shouldHave(exactTextCaseSensitive("A"));
  }
}
