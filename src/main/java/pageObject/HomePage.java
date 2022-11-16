package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
    private WebDriver driver;

    private final By linkToThePersonalAccount = By.xpath(".//nav/a[@href='/account']");
    private final By accountLoginButton = By.xpath(".//button[text()='Войти в аккаунт']");
    private final By bunsButton = By.xpath(".//span[text()='Булки']");
    private final By saucesButton = By.xpath(".//span[text()='Соусы']");
    private final By fillingsButton = By.xpath(".//span[text()='Начинки']");
    private final By bunsOfTheConstructorIsSelected = By.xpath(".//span[text()='Булки']/parent::div[contains(@class,'tab_tab_type_current__2BEPc')]");
    private final By saucesOfTheConstructorIsSelected = By.xpath(".//span[text()='Соусы']/parent::div[contains(@class,'tab_tab_type_current__2BEPc')]");
    private final By fillingsOfTheConstructorIsSelected = By.xpath(".//span[text()='Начинки']/parent::div[contains(@class,'tab_tab_type_current__2BEPc')]");


    public HomePage(WebDriver driver) {
        this.driver = driver;
    }
    public String getLink() {
        return driver.getCurrentUrl();
    }
    public void clickOnTheButtonToPersonalAccount() {
        driver.findElement(linkToThePersonalAccount).click();
    }

    public void clickOnTheAccountLoginButton() {
        driver.findElement(accountLoginButton).click();
    }

    public void clickOnTheBunsButton() {
        driver.findElement(bunsButton).click();
    }

    public void clickOnTheSaucesButton() {
        driver.findElement(saucesButton).click();
    }

    public void clickOnTheFillingsButton() {
        driver.findElement(fillingsButton).click();
    }

    public boolean bunsOfTheConstructorIsDisplayed() {
        return driver.findElement(bunsOfTheConstructorIsSelected).isDisplayed();
    }
    public boolean saucesOfTheConstructorIsDisplayed() {
        return driver.findElement(saucesOfTheConstructorIsSelected).isDisplayed();
    }
    public boolean fillingsOfTheConstructorIsDisplayed() {
        return driver.findElement(fillingsOfTheConstructorIsSelected).isDisplayed();
    }
}
