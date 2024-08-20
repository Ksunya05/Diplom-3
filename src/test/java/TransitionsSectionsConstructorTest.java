import browser.WebDriverManager;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pageobject.MainPage;

public class TransitionsSectionsConstructorTest extends WebDriverManager {
    WebDriver driver;
    MainPage mainPage;
    private String url = "https://stellarburgers.nomoreparties.site/";

    @Before
    public void setUp() {
        WebDriverManager browserPage = new WebDriverManager();
        driver = browserPage.getWebdriver("chrome");
        driver.get(url);
        mainPage = new MainPage(driver);
    }

    @Test
    @DisplayName("Переход в раздел 'Булки'")
    public void testTransitionSectionBuns() {
        mainPage.waitForLoadMainPage();
        mainPage.clickSauceButton();
        mainPage.clickBunsButton();
        Assert.assertEquals("Булки", mainPage.getTextCurrentSection());
    }

    @Test
    @DisplayName("Переход в раздел 'Соусы'")
    public void testTransitionSectionSauce() {
        mainPage.waitForLoadMainPage();
        mainPage.clickSauceButton();
        Assert.assertEquals("Соусы", mainPage.getTextCurrentSection());
    }

    @Test
    @DisplayName("Переход в раздел 'Начинки'")
    public void testTransitionSectionFillings() {
        mainPage.waitForLoadMainPage();
        mainPage.clickFillingButton();
        Assert.assertEquals("Начинки", mainPage.getTextCurrentSection());
    }

    @After
    public void cleanUp() {
        driver.quit();
    }
}
