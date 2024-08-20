import browser.WebDriverManager;
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
import pageobject.AccountPage;
import pageobject.LoginPage;
import pageobject.MainPage;
import pageobject.RegistrationPage;

public class TransitionsPersonalAccountTest extends WebDriverManager {
    WebDriver driver;
    private String url = "https://stellarburgers.nomoreparties.site/";
    private User user;
    private String accessToken;
    MainPage mainPage;
    LoginPage loginPage;
    RegistrationPage registrationPage;
    AccountPage accountPage;


    @Before
    public void setUp() {
        WebDriverManager browserPage = new WebDriverManager();
        driver = browserPage.getWebdriver("chrome");
        driver.get(url);
        user = User.makeRandomUser();
        UserApi.createUser(user);
        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);
        registrationPage = new RegistrationPage(driver);
        accountPage = new AccountPage(driver);
    }

    @Test
    @DisplayName("Переход в личный кабинет по клику на 'Личный кабинет'")
    public void testTransitionToAccountPage() {
        mainPage.waitForLoadMainPage();
        mainPage.clickLoginButton();
        loginPage.waitForLoadLoginPage();
        Assert.assertTrue(driver.findElement(loginPage.headerEntrance).isDisplayed());
    }

    @Test
    @DisplayName("Переход из личного кабинета в конструктор")
    public void testTransitionFromAccountPageToConstructor() {
        mainPage.waitForLoadMainPage();
        mainPage.clickLoginButton();
        loginPage.waitForLoadLoginPage();
        loginPage.clickConstructorButton();
        mainPage.waitForLoadMainPage();
        Assert.assertTrue(driver.findElement(mainPage.inscriptionMakeBurger).isDisplayed());
    }

    @Test
    @DisplayName("Переход по клику на логотип Stellar Burgers")
    public void testTransitionFromAccountPageToStellarBurger() {
        mainPage.clickLoginButton();
        loginPage.waitForLoadLoginPage();
        loginPage.clickLogoButton();
        mainPage.waitForLoadMainPage();
        Assert.assertTrue(driver.findElement(mainPage.inscriptionMakeBurger).isDisplayed());
    }

    @Test
    @DisplayName("Выход по кнопке 'Выйти' в личном кабинете")
    public void testExitAccountPage() {
        mainPage.clickAccountButton();
        loginPage.waitForLoadLoginPage();
        loginPage.logInUser(user.getEmail(), user.getPassword());
        LocalStorage localStorage = ((WebStorage) driver).getLocalStorage();
        accessToken = localStorage.getItem("accessToken");
        mainPage.waitForLoadMainPage();
        mainPage.clickAccountButton();
        accountPage.waitForLoadAccountPage();
        accountPage.clickExitButton();
        loginPage.waitForLoadLoginPage();
        Assert.assertTrue(driver.findElement(loginPage.headerEntrance).isDisplayed());
    }

    @After
    public void cleanUp() {
        if (accessToken != null) {
            UserApi.deleteUser(accessToken);
        }
        driver.quit();
    }
}
