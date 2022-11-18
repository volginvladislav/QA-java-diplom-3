package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PersonalAccountPage {
    private WebDriver driver;

    private final By exitButton = By.xpath(".//button[text()='Выход']");
    private final By constructorButton = By.xpath(".//p[text()='Конструктор']/parent::a[@class='AppHeader_header__link__3D_hX']");
    private final By logo = By.xpath(".//div[@class='AppHeader_header__logo__2D0X2']/a[@href='/']");

    public PersonalAccountPage(WebDriver driver) {
        this.driver = driver;
    }
    public String getLink() {
        return driver.getCurrentUrl();
    }
    public void clickExitButton() {
        driver.findElement(exitButton).click();
    }
    public void clickConstructorButton() {
        driver.findElement(constructorButton).click();
    }
    public void clickLogo() {
        driver.findElement(logo).click();
    }
}
