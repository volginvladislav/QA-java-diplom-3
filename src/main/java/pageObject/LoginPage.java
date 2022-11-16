package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private WebDriver driver;

    private final By emailField = By.xpath(".//label[text()='Email']/parent::div/input");
    private final By passwordField = By.xpath(".//label[text()='Пароль']/parent::div/input");
    private final By loginButton = By.xpath(".//button[text()='Войти']");
    private final By registrationButton = By.xpath(".//a[@href='/register' and text()='Зарегистрироваться']");
    private final By passwordRecoveryButton = By.xpath(".//a[text()='Восстановить пароль']");
    private final By logo = By.xpath(".//div[@class='AppHeader_header__logo__2D0X2']/a[@href='/']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }
    public String getLink() {
        return driver.getCurrentUrl();
    }
    public void setEmail(String email) {
        driver.findElement(emailField).sendKeys(email);
    }

    public void setPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }
    public void clickToTheLoginButton() {
        driver.findElement(loginButton).click();
    }
    public void userLogin(String email, String password) {
        setEmail(email);
        setPassword(password);
        clickToTheLoginButton();
    }
    public void clickToTheRegistrationButton() {
        driver.findElement(registrationButton).click();
    }

    public void clickToThePasswordRecoveryButton() {
        driver.findElement(passwordRecoveryButton).click();
    }

    public void clickLogo() {
        driver.findElement(logo).click();
    }

    public By getLoginButton() {
        return loginButton;
    }

}
