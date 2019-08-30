import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.net.URL;

public class AddDeleteColumnsTest {
    private  WebDriver driver;

    @BeforeEach
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.navigate().to("https://app.smartsheet.com/sheets/W6wM8jf5qMf8FQ6XR6M8HwvhF5QcH3pQpXmMxWC1?view=grid");
    }

    @Test
    public void test_add_sheet() {
        new LoginPage(driver).logIn();
        new SmartSheetPage(driver)
                .validateAddingColumn();
    }

    @AfterEach
    public  void teardown() {
        driver.quit();
        System.clearProperty("webdriver.chrome.driver");
    }
}