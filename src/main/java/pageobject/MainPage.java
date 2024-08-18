package pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {
    private final WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    //Локатор видимости главной страницы
    private final By stellarBurgerMainPage = By.xpath(".//h1[text()='Соберите бургер']");
    //Локатор для кнопки "Войти в аккаунт"
    private final By loginButton = By.xpath(".//button[text()='Войти в аккаунт']");
    //Локатор для кнопки "Личный кабинет"
    private final By accountButton = By.xpath(".//a[@href='/account']");
    //Локатор кнопки перехода к разделу "Булки"
    private final By bunsButton = By.xpath(".//span[@class='text text_type_main-default'][text()='Булки']");
    //Локатор кнопки перехода к разделу "Соусы"
    private final By sauceButton = By.xpath(".//span[@class='text text_type_main-default'][text()='Соусы']");
    //Локатор кнопки перехода к разделу "Начинки"
    private final By fillingButton = By.xpath(".//span[@class='text text_type_main-default'][text()='Начинки']");
    //Локатор текущего раздела
    private final By currentSection = By.xpath("//div[starts-with(@class, 'tab_tab__1SPyG tab_tab_type_current__2BEPc')]//span");
    //Локатор надписи "Соберите бургер"
    public final By inscriptionMakeBurger = By.xpath(".//h1[text()='Соберите бургер']");


    @Step("Ожидание загрузки главной страницы")
    public void waitForLoadMainPage() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(stellarBurgerMainPage));
    }

    @Step("Клик по кнопке 'Войти в аккаунт'")
    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    @Step("Клик по кнопке 'Личный кабинет'")
    public void clickAccountButton() {
        driver.findElement(accountButton).click();
    }

    @Step("Клик по кнопке 'Булки'")
    public void clickBunsButton() {
        driver.findElement(bunsButton).click();
    }

    @Step("Клик по кнопке 'Соусы'")
    public void clickSauceButton() {
        driver.findElement(sauceButton).click();
    }

    @Step("Клик по кнопке 'Начинки'")
    public void clickFillingButton() {
        driver.findElement(fillingButton).click();
    }

    @Step
    public String getTextCurrentSection() {
        return driver.findElement(currentSection).getText();
    }
}