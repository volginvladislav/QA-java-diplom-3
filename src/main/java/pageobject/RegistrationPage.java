package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class RegistrationPage {
    private WebDriver driver;

    private final By registrationButton = By.xpath(".//button[text()='Зарегистрироваться']");
    private final By nameField = By.xpath(".//label[text()='Имя']/parent::div/input");
    private final By emailField = By.xpath(".//label[text()='Email']/parent::div/input");
    private final By passwordField = By.xpath(".//label[text()='Пароль']/parent::div/input");
    private final By loginButton = By.xpath(".//a[text()='Войти']");
    private final By invalidPassword = By.xpath(".//p[text()='Некорректный пароль']");

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
    }
    public void setName(String name) {
        driver.findElement(nameField).sendKeys(name);
    }
    public void setEmail(String email) {
        driver.findElement(emailField).sendKeys(email);
    }
    public void setPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }
    public void clickRegistrationButton() {
        driver.findElement(registrationButton).click();
    }
    public void userRegistration(String name, String email, String password) {
        setName(name);
        setEmail(email);
        setPassword(password);
        clickRegistrationButton();
    }
    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }
    public String getError() {
        WebElement element = driver.findElement(invalidPassword);
        return element.getText();
    }
    public void waitError() {
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOfElementLocated(invalidPassword));
    }

}
