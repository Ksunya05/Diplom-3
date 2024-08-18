import browser.BrowserPage;
import createuser.User;
import createuser.UserApi;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.html5.LocalStorage;
import org.openqa.selenium.html5.WebStorage;
import pageobject.*;

public class LoginPageTest extends BrowserPage {
    WebDriver driver;
    private String url = "https://stellarburgers.nomoreparties.site/";
    private User user;
    private String accessToken;
    MainPage mainPage;
    LoginPage loginPage;
    RegistrationPage registrationPage;
    AccountPage accountPage;
    RecoveryPasswordPage recoveryPasswordPage;

    @Before
    public void setUp() {
        BrowserPage browserPage = new BrowserPage();
        driver = browserPage.getWebdriver("chrome");
        driver.get(url);
        user = User.makeRandomUser();
        UserApi.createUser(user);
        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);
        registrationPage = new RegistrationPage(driver);
        accountPage = new AccountPage(driver);
        recoveryPasswordPage = new RecoveryPasswordPage(driver);
    }

    @Test
    @DisplayName("Вход по кнопке 'Войти в аккаунт' на главной")
    public void testEntranceLoginButton() {
        mainPage.clickLoginButton();
        loginPage.waitForLoadLoginPage();
        loginPage.logInUser(user.getEmail(), user.getPassword());
        LocalStorage localStorage = ((WebStorage) driver).getLocalStorage();
        accessToken = localStorage.getItem("accessToken");
        mainPage.waitForLoadMainPage();

    }

    @Test
    @DisplayName("Вход через кнопку 'Личный кабинет'")
    public void testEntranceAccountButton() {
        mainPage.clickAccountButton();
        loginPage.logInUser(user.getEmail(), user.getPassword());
        LocalStorage localStorage = ((WebStorage) driver).getLocalStorage();
        accessToken = localStorage.getItem("accessToken");
        mainPage.waitForLoadMainPage();

    }

    @Test
    @DisplayName("Вход через кнопку в форме регистрации")
    public void testEntranceFromRegisterPage() {
        mainPage.clickAccountButton();
        loginPage.clickRegistrationLink();
        registrationPage.clickLoginLink();
        loginPage.logInUser(user.getEmail(), user.getPassword());
        LocalStorage localStorage = ((WebStorage) driver).getLocalStorage();
        accessToken = localStorage.getItem("accessToken");
        mainPage.waitForLoadMainPage();
    }

    @Test
    @DisplayName("Вход через кнопку в форме восстановления пароля")
    public void testEntranceFromRecoveryPage() {
        mainPage.clickAccountButton();
        loginPage.clickPasswordLink();
        recoveryPasswordPage.waitForLoadRecoveryPage();
        recoveryPasswordPage.clickLoginLink();
        loginPage.logInUser(user.getEmail(), user.getPassword());
        LocalStorage localStorage = ((WebStorage) driver).getLocalStorage();
        accessToken = localStorage.getItem("accessToken");
        mainPage.waitForLoadMainPage();
    }

    @After
    public void cleanUp() {
        if (accessToken != null) {
            UserApi.deleteUser(accessToken);
        }
        driver.quit();
    }
}
