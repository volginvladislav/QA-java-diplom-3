import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebDriverFactory {
    public static WebDriver getWebDriver(){
        String driver = System.getProperty("browser");
        switch (driver){
            case "chrome":
                System.setProperty("webdriver.chrome.driver", "D:/WebDriver/bin/chromedriver.exe");
                return  new ChromeDriver();
            case "yandex":
                System.setProperty("webdriver.chrome.driver", "D:/WebDriver/bin/yandexdriver.exe");
                return  new ChromeDriver();
            default:
                throw new IllegalStateException("Неверный браузер");
        }
    }
}
