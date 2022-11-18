import constans.Urls;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pageobject.HomePage;

import static org.junit.Assert.assertTrue;

public class HomePageTest {
     WebDriver driver;
     HomePage homePage;

    @Before
    public void setUp() {
        driver = WebDriverFactory.getWebDriver();
        homePage = new HomePage(driver);
        driver.get(Urls.HOME_PAGE_URL);
        driver.manage().window().maximize();
    }

    @After
    public void teardown() {
        driver.quit();
    }

    @Test
    @DisplayName("Проверка на переключение вкладок ингредиентов - Булки")
    public void checkBunsButtonIsSelected() {
        homePage.clickOnTheSaucesButton();
        homePage.clickOnTheBunsButton();
        assertTrue(homePage.bunsOfTheConstructorIsDisplayed());
    }

    @Test
    @DisplayName("Проверка на переключение вкладок ингредиентов - Соусы")
    public void checkSaucesButtonIsSelected() {
        homePage.clickOnTheSaucesButton();
        assertTrue(homePage.saucesOfTheConstructorIsDisplayed());
    }

    @Test
    @DisplayName("Проверка на переключение вкладок ингредиентов - Начинки")
    public void checkFillingsIsSelected() {
        homePage.clickOnTheFillingsButton();
        assertTrue(homePage.fillingsOfTheConstructorIsDisplayed());
    }
}
