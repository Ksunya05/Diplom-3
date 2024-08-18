import browser.BrowserPage;
import createuser.User;
import createuser.UserApi;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.html5.LocalStorage;
import org.openqa.selenium.html5.WebStorage;
import pageobject.LoginPage;
import pageobject.MainPage;
import pageobject.RegistrationPage;

public class RegistrationUserTest extends BrowserPage {
    private String url = "https://stellarburgers.nomoreparties.site/";
    private User user;
    private String accessToken;
    MainPage mainPage;
    RegistrationPage registrationPage;
    LoginPage loginPage;
    WebDriver driver;

    @Before
    public void setUp() {

        BrowserPage browserPage = new BrowserPage();
        driver = browserPage.getWebdriver("chrome");
        mainPage = new MainPage(driver);
        registrationPage = new RegistrationPage(driver);
        loginPage = new LoginPage(driver);
        user = User.makeRandomUser();

    }

    @Test
    @DisplayName("Регистрация пользователя - позитивный кейс")
    public void testPositiveRegistration() {
        driver.get(url);
        mainPage.clickLoginButton();
        loginPage.clickRegistrationLink();
        registrationPage.waitForLoadRegisterPage();
        registrationPage.registerUser(user.getName(), user.getEmail(), user.getPassword());
        loginPage.waitForLoadLoginPage();
        loginPage.logInUser(user.getEmail(), user.getPassword());
        LocalStorage localStorage = ((WebStorage) driver).getLocalStorage();
        accessToken = localStorage.getItem("accessToken");

    }

    @Test
    @DisplayName("Регистрация пользователя - негативный кейс")
    public void testNegativeRegistration() {
        driver.get(url);
        mainPage.clickLoginButton();
        loginPage.clickRegistrationLink();
        registrationPage.waitForLoadRegisterPage();
        registrationPage.registerUser(user.getName(), user.getEmail(), "pass");
        Assert.assertTrue("Текст с ошибкой отсутствует", driver.findElement(registrationPage.uncorrectPasswordText).isDisplayed());

    }

    @After
    public void cleanUp() {
        if (accessToken != null) {
            UserApi.deleteUser(accessToken);
        }
        driver.quit();
    }

}