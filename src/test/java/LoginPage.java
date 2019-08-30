import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage{

    public LoginPage(WebDriver driver){
        super(driver);
    }

    public void logIn() {
        //Adding a very simple login method to get to the sheet. This page is not in the scope of the project

        WebElement emailField = wait(driver).until(ExpectedConditions.visibilityOfElementLocated(By.id(("loginEmail"))));
        WebElement continueButton = wait(driver).until(ExpectedConditions.visibilityOfElementLocated(By.id(
                ("formControl"))));

        emailField.sendKeys("bcmcadam@gmail.com");
        continueButton.click();

        WebElement passwordField = wait(driver).until(ExpectedConditions.visibilityOfElementLocated(By.id(("loginPassword"))));
        passwordField.sendKeys("TestProject");
        WebElement logInButton = wait(driver).until(ExpectedConditions.visibilityOfElementLocated(By.id(
                ("formControl"))));
        logInButton.click();
    }



}
