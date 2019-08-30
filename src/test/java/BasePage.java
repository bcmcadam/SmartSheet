import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {

    public WebDriver driver;

    public BasePage(WebDriver driver){
        this.driver = driver;
    }

    WebDriverWait wait (WebDriver d) {

        return new WebDriverWait(d, 10);
    }
}
