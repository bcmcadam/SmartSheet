import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.xml.bind.Element;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver){
        super(driver);
    }

    WebDriverWait wait = new WebDriverWait(driver, 10);

    public WebElement logo = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("lga")));

    public void validateLogoIsPresent(){
        Assertions.assertTrue(logo.isDisplayed());
    }
}
