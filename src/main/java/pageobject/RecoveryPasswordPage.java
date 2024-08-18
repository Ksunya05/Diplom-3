package pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RecoveryPasswordPage {
    private final WebDriver driver;

    public RecoveryPasswordPage(WebDriver driver) {
        this.driver = driver;
    }

    //Локатор для заголовка "Восстановление пароля"
    public final By recoverPassword = By.xpath(".//main/div/h2[text()='Восстановление пароля']");
    //Локатор для ссылки "Войти"
    private final By loginLink = By.xpath(".//div/p/a[@href='/login' and text()='Войти']");


    @Step("Метод ожидания загрузки страницы восстановления пароля")
    public RecoveryPasswordPage waitForLoadRecoveryPage() {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(recoverPassword));
        return this;
    }

    @Step("Клик по ссылке 'Войти'")
    public void clickLoginLink() {
        driver.findElement(loginLink).click();
    }
}
