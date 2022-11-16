import api.UserSteps;
import constans.Urls;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageobject.LoginPage;
import pageobject.RegistrationPage;


import static org.apache.http.HttpStatus.SC_ACCEPTED;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RegistrationPageTest {
    WebDriver driver;
    RegistrationPage registrationPage;
    LoginPage loginPage;
    String accessToken;
    UserSteps userSteps;
    String email;
    String name;
    String password;
    Response response;

    @Before
    public void setUp() {
        driver = WebDriverFactory.getWebDriver();
        driver.get(Urls.REGISTER_PAGE_URL);
        driver.manage().window().maximize();
        registrationPage = new RegistrationPage(driver);
        loginPage = new LoginPage(driver);
        userSteps = new UserSteps();
        email = RandomStringUtils.randomAlphanumeric(10) + "@yandex.ru";
        password = RandomStringUtils.randomAlphanumeric(6);
        name = RandomStringUtils.randomAlphabetic(8);
    }

    @After
    public void teardown() {
        driver.quit();
        if (accessToken != null) {
            userSteps.deleteUser(accessToken)
                    .assertThat().statusCode(SC_ACCEPTED);
        }
    }

    @Test
    @DisplayName("Проверка регистрации пользователя с валидным паролем - 6 символов")
    public void checkRegistrationWithValidPassword() {
        registrationPage.userRegistration(name, email, password);
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.urlToBe(Urls.LOGIN_PAGE_URL));
        accessToken = userSteps.authorizationUser(email, password).extract().body().path("accessToken");
        assertTrue(driver.findElement(loginPage.getLoginButton()).isDisplayed());
    }

    @Test
    @DisplayName("Проверка регистрации нового пользователя с невалидным паролем - 5 символов")
    public void checkRegistrationWithInvalidPassword() {
        String wrongPassword = RandomStringUtils.randomAlphanumeric(5);
        registrationPage.userRegistration(name, email, wrongPassword);
        response = userSteps.authorizationUserWithInvalidPassword(email, wrongPassword);
        registrationPage.waitError();
        assertEquals("Некорректный пароль", registrationPage.getError());
        accessToken = response.path("accessToken");
    }
}

