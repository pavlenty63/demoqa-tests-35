package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Map;

public class TestBase {

  @BeforeAll
  static void defaultConf() {
    Configuration.baseUrl = "https://demoqa.com";
    Configuration.pageLoadStrategy = "eager";
    Configuration.remote = System.getProperty("selenoid_url");
    Configuration.browser = System.getProperty("browser", "chrome");
    Configuration.browserVersion = System.getProperty("browser_version", "128.0");
    Configuration.browserSize = System.getProperty("browser_size", "1920x1080");

    DesiredCapabilities capabilities = new DesiredCapabilities();
    capabilities.setCapability("selenoid:options", Map.<String, Object>of(
            "enableVNC", true,
            "enableVideo", true
    ));
    Configuration.browserCapabilities = capabilities;
  }

  @BeforeEach
  void testConf() {
    SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
  }

  @AfterEach
  void addAttachments() {
    Attach.screenshotAs("Final screenshot");
    Attach.pageSource();
    Attach.browserConsoleLogs();
    Attach.addVideo();
  }
}
