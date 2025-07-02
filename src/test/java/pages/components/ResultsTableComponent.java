package pages.components;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class ResultsTableComponent {
  private SelenideElement
          resultsTable = $(".table-responsive");

  public void submitedResult(String label, String value) {
    resultsTable.$(byText(label)).parent().shouldHave(text(value));
  }

  public void notSubmitedResult() {
    resultsTable.shouldNotBe(visible);
  }
}
