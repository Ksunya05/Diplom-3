package browser;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Locale;

public class BrowserPage {

    public WebDriver getWebdriver(String browserName) {
        switch (browserName.toLowerCase(Locale.ROOT)) {
            case "chrome":
                System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
                return new ChromeDriver();
            case "yandex":
                System.setProperty("webdriver.chrome.driver", "src/test/resources/yandexdriver.exe");
                return new ChromeDriver();
            default:
                throw new IllegalArgumentException("invalid browser name" + browserName);
        }
    }
}

