package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class SelenideWikiSearch {

  @BeforeAll
  static void configuration() {
    Configuration.pageLoadStrategy = "eager";
    Configuration.browserSize = "1920x1080";
    Configuration.baseUrl = "https://github.com/";
//    Configuration.holdBrowserOpen = true;
  }

  @Test
  void WikiSearchTest() {
    String firstExample = """
              @ExtendWith({SoftAssertsExtension.class})
              class Tests {
                @Test
                void test() {
                  Configuration.assertionMode = SOFT;
                  open("page.html");
              
                  $("#first").should(visible).click();
                  $("#second").should(visible).click();
                }
              }
              """;
    String secondExample = """
            class Tests {
              @RegisterExtension
              static SoftAssertsExtension softAsserts = new SoftAssertsExtension();
                        
              @Test
              void test() {
                Configuration.assertionMode = SOFT;
                open("page.html");
                        
                $("#first").should(visible).click();
                $("#second").should(visible).click();
              }
            }
            """;
    open("/selenide");
    $("#org-repositories").find(byText("selenide")).click();
    $("#wiki-tab").click();
    $("#wiki-body").shouldHave(textCaseSensitive("Soft assertions"));
    $(byTagAndText("a", "Soft assertions")).click();
    $(byTagAndText("h4", "3. Using JUnit5 extend test class:"))
            .parent().sibling(0).$("pre").shouldHave(text(firstExample));
    $(byTagAndText("h4", "3. Using JUnit5 extend test class:"))
            .parent().sibling(2).$("pre").shouldHave(text(secondExample));
  }
}
