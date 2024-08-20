package pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AccountPage {
    private final WebDriver driver;

    public AccountPage(WebDriver driver) {
        this.driver = driver;
    }

    //Локатор надписи
    public final By profileInscription = By.xpath(".//nav/p[text()='В этом разделе вы можете изменить свои персональные данные']");

    //Локатор для кнопки "Выход"
    private final By exitButton = By.xpath(".//button[text()='Выход']");

    @Step("Ожидание загрузки страницы личного кабинета")
    public void waitForLoadAccountPage() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(profileInscription));
        driver.findElement(profileInscription);
    }

    @Step("Клик по кнопке 'Выйти'")
    public void clickExitButton() {
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(exitButton));
        driver.findElement(exitButton).click();

    }
}