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
import pageobject.PersonalAccountPage;

import static org.apache.http.HttpStatus.SC_ACCEPTED;
import static org.junit.Assert.assertEquals;

public class PersonalAccountPageTest {
    WebDriver driver;
    LoginPage loginPage;
    HomePage homePage;
    PersonalAccountPage personalAccountPage;
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
        homePage = new HomePage(driver);
        personalAccountPage = new PersonalAccountPage(driver);
        userSteps = new UserSteps();
        email = RandomStringUtils.randomAlphanumeric(10) + "@yandex.ru";
        password = RandomStringUtils.randomAlphanumeric(6);
        name = RandomStringUtils.randomAlphabetic(8);
        accessToken = userSteps.createUser(email, password, name).extract().body().path("accessToken");
        loginPage.userLogin(email, password);
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.urlToBe(Urls.HOME_PAGE_URL));
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
    @DisplayName("Проверка перехода в личный кабинет с главной страницы")
    public void checkFollowingToThePersonalAccount() {
        homePage.clickOnTheButtonToPersonalAccount();
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.urlToBe(Urls.PERSONAL_ACCOUNT_PAGE_URL));
        assertEquals(Urls.PERSONAL_ACCOUNT_PAGE_URL, personalAccountPage.getLink());
    }

    @Test
    @DisplayName("Проверка выхода из аккаунта")
    public void checkLogOut() {
        homePage.clickOnTheButtonToPersonalAccount();
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.urlToBe(Urls.PERSONAL_ACCOUNT_PAGE_URL));
        personalAccountPage.clickExitButton();
        wait.until(ExpectedConditions.urlToBe(Urls.LOGIN_PAGE_URL));
        assertEquals(Urls.LOGIN_PAGE_URL, loginPage.getLink());
    }

    @Test
    @DisplayName("Проверка перехода в конструктор через кнопку - Конструктор")
    public void checkFollowingToTheConstructorByConstructorButton() {
        homePage.clickOnTheButtonToPersonalAccount();
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.urlToBe(Urls.PERSONAL_ACCOUNT_PAGE_URL));
        personalAccountPage.clickConstructorButton();
        assertEquals(Urls.HOME_PAGE_URL, personalAccountPage.getLink());
    }

    @Test
    @DisplayName("Проверка перехода в конструктор через нажатие по лого - Stellar Burgers")
    public void checkFollowingToTheConstructorByLogoButton() {
        homePage.clickOnTheButtonToPersonalAccount();
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.urlToBe(Urls.PERSONAL_ACCOUNT_PAGE_URL));
        personalAccountPage.clickLogo();
        assertEquals(Urls.HOME_PAGE_URL, personalAccountPage.getLink());
    }
}
