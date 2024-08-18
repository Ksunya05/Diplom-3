package pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    private final WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    //Локатор для кнопки "Конструктор"
    private final By constructorButton = By.xpath(".//a/p[text()='Конструктор']");
    //Локатор для кнопки "Логотип Stellar Burgers"
    private final By logoButton = By.xpath(".//div/a[@href='/']");
    //Локатор заголовка "Вход"
    public final By headerEntrance = By.xpath(".//main/div/h2[text()='Вход']");
    //Локатор поля "Email"
    private final By emailField = By.xpath(".//input[@name='name']");
    //Локатор поля "Пароль"
    private final By passwordField = By.xpath(".//input[@name='Пароль']");
    //Локатор для кнопки "Войти"
    private final By entranceButton = By.xpath(".//button[text()='Войти']");
    //Локатор для ссылки "Зарегистрироваться"
    private final By registrationLink = By.xpath(".//a[@href='/register' and text()='Зарегистрироваться']");
    //Локатор для ссылки "Восстановить пароль"
    private final By passwordLink = By.xpath(".//a[@href='/forgot-password' and text()='Восстановить пароль']");

    @Step("Метод ожидания загрузки страницы 'Вход'")
    public LoginPage waitForLoadLoginPage() {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(driver -> (driver.findElement(headerEntrance).getText() != null
                && !driver.findElement(headerEntrance).getText().isEmpty()
        ));
        return this;
    }

    @Step("Клик по кнопке 'Конструктор'")
    public void clickConstructorButton() {
        driver.findElement(constructorButton).click();
    }

    @Step("Клик по логотипу 'Stellar Burger'")
    public void clickLogoButton() {
        driver.findElement(logoButton).click();
    }

    @Step("Метод заполняет поле 'Email'")
    public void setEmail(String email) {
        driver.findElement(emailField).sendKeys(email);
    }

    @Step("Метод заполняет поле 'Пароль'")
    public void setPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    @Step("Метод клика по кнопке 'Войти'")
    public void clickEntranceButton() {
        driver.findElement(entranceButton).click();
    }

    @Step("Метод авторизации пользователя")
    public void logInUser(String email, String password) {
        setEmail(email);
        setPassword(password);
        clickEntranceButton();
    }

    @Step("Метод клика по ссылке 'Зарегистрироваться'")
    public void clickRegistrationLink() {
        driver.findElement(registrationLink).click();
    }

    @Step("Метод клика по ссылке 'Восстановить пароль'")
    public void clickPasswordLink() {
        driver.findElement(passwordLink).click();
    }

}