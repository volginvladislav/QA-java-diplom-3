import api.UserSteps;
import constans.Urls;
import io.qameta.allure.junit4.DisplayName;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageobject.HomePage;
import pageobject.LoginPage;
import pageobject.PasswordRecoveryPage;
import pageobject.RegistrationPage;

import static org.apache.http.HttpStatus.SC_ACCEPTED;
import static org.junit.Assert.assertEquals;

public class LoginPageTest {

    WebDriver driver;
    LoginPage loginPage;
    HomePage homePage;
    String accessToken;
    UserSteps userSteps;
    String email;
    String password;
    String name;

    @Before
    public void setUp() {
        driver = WebDriverFactory.getWebDriver();
        driver.get(Urls.LOGIN_PAGE_URL);
        driver.manage().window().maximize();
        loginPage = new LoginPage(driver);
        userSteps = new UserSteps();
        homePage = new HomePage(driver);
        email = RandomStringUtils.randomAlphanumeric(10) + "@yandex.ru";
        password = RandomStringUtils.randomAlphanumeric(6);
        name = RandomStringUtils.randomAlphabetic(8);
        accessToken = userSteps.createUser(email, password, name).extract().body().path("accessToken");

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
    @DisplayName("Проверка авторизации с главной страницы (кнопка Личный кабинет)")
    public void checkLoginFromTheHomePageByPersonalAccountButton() {
        loginPage.clickLogo();
        homePage.clickOnTheButtonToPersonalAccount();
        loginPage.userLogin(email, password);
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.urlToBe(Urls.HOME_PAGE_URL));
        assertEquals(Urls.HOME_PAGE_URL, homePage.getLink());
    }

    @Test
    @DisplayName("Проверка авторизации с главной страницы (кнопка Войти в аккаунт)")
    public void checkLoginFromTheHomePage() {
        loginPage.clickLogo();
        homePage.clickOnTheAccountLoginButton();
        loginPage.userLogin(email, password);
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.urlToBe(Urls.HOME_PAGE_URL));
        assertEquals(Urls.HOME_PAGE_URL, homePage.getLink());
    }

    @Test
    @DisplayName("Проверка авторизации со страницы регистрации (кнопка Войти)")
    public void checkLoginFromTheRegistrationPage() {
        RegistrationPage registrationPage = new RegistrationPage(driver);
        loginPage.clickToTheRegistrationButton();
        registrationPage.clickLoginButton();
        loginPage.userLogin(email, password);
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.urlToBe(Urls.HOME_PAGE_URL));
        assertEquals(Urls.HOME_PAGE_URL, homePage.getLink());
    }

    @Test
    @DisplayName("Проверка авторизации со страницы восстановления пароля (кнопка Войти)")
    public void checkLoginFromTheRecoveryPage() {
        PasswordRecoveryPage passwordRecoveryPage = new PasswordRecoveryPage(driver);
        loginPage.clickToThePasswordRecoveryButton();
        passwordRecoveryPage.clickToTheLoginButton();
        loginPage.userLogin(email, password);
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.urlToBe(Urls.HOME_PAGE_URL));
        assertEquals(Urls.HOME_PAGE_URL, homePage.getLink());
    }
}
