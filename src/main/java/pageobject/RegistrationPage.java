package pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegistrationPage {
    private final WebDriver driver;

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
    }

    //Локатор для перехода на страницу "Регистрация"
    public final By registrationPage = By.xpath(".//div/h2[text()='Регистрация']");
    //Локатор поля "Имя"
    private final By nameField = By.xpath(".//div[./label[text()='Имя']]/input[@name='name']");
    //Локатор поля "Email"
    private final By emailField = By.xpath(".//div[./label[text()='Email']]/input[@name='name']");
    //Локатор поля "Пароль"
    private final By passwordField = By.xpath(".//div[./label[text()='Пароль']]/input[@name='Пароль']");
    //Локатор кнопки "Зарегистрироваться"
    private final By registrationButton = By.xpath(".//button[text()='Зарегистрироваться']");
    //Локатор для текста "Некорректный пароль"
    public final By uncorrectPasswordText = By.className("input__error");
    //Локатор для ссылки "Войти"
    private final By loginLink = By.xpath(".//div/p/a[@href='/login' and text()='Войти']");

    @Step("Метод заполняет поле 'Имя'")
    public void setName(String name) {
        driver.findElement(nameField).sendKeys(name);
    }

    @Step("Метод заполняет поле 'Email'")
    public void setEmail(String email) {
        driver.findElement(emailField).sendKeys(email);
    }

    @Step("Метод заполняет поле 'Пароль'")
    public void setPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    @Step("Метод клика по кнопке 'Зарегистрироваться'")
    public void clickRegistrationButton() {
        driver.findElement(registrationButton).click();
    }

    @Step("Клик по ссылке 'Войти'")
    public void clickLoginLink() {
        driver.findElement(loginLink).click();
    }

    @Step("Метод ожидания загрузки страницы регистрации")
    public RegistrationPage waitForLoadRegisterPage() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(registrationPage));

        return this;
    }

    @Step("Метод заполнения формы регистрации")
    public void registerUser(String name, String email, String password) {
        setName(name);
        setEmail(email);
        setPassword(password);
        clickRegistrationButton();
    }

}