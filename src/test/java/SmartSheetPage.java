import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SmartSheetPage extends BasePage {

    public SmartSheetPage(WebDriver driver){
        super(driver);
    }

    private  By columnsLocator = By.xpath
            ("//div[ contains (@class, 'clsTableHeadingText') and contains(@style, 'white-space: normal') and text()" +
                    "[contains(.," +
                    "'Column')]]");

    private By controlButtonLocator = By.xpath("//div[ contains (@class , " +
            "'clsTableHeadingControlsBackground') and not(contains(@style, 'visibility: hidden;'))]");
    //By locators for elements that need to be refreshed.

    private List<String> expectedColumnsAfterDeletion = Arrays.asList( "Column1", "Column2", "Column3", "Column4",
            "Column5", "Column6");
    private List<String> expectedAfterAdditions = Arrays.asList("New Column Left", "Column1", "New Column Right",
            "Column2", "Column3", "Column4", "Column5", "Column6");
    //Lists of expected Column name values to assert against at the end of the test

    public List<String> getColumnNames (List<WebElement> columns) {
        //extracts the column name string from each column in a list of columns
        List<String> columnNames = new ArrayList<String>();

        for (WebElement element : columns) {
            columnNames.add(element.getText());
        }
        return columnNames;
    }

    private void clickColumnByIndex (Integer index) {
        List<WebElement> columns = wait(driver).until(ExpectedConditions.presenceOfAllElementsLocatedBy
                (columnsLocator));
        columns.get(index)
                .click();
    }

    private void clickControlButton() {
        WebElement contolButton = driver.findElement(controlButtonLocator);
        contolButton.click();
        //finds and clicks the column options button
    }

    private void addColumn (String direction) {
        clickColumnByIndex(0);
        clickControlButton();
        driver.findElement(By.xpath("//td[ " +
                "contains (@class, 'clsStandardMenuText') and text()= 'Insert Column " + direction + "']"))
                .click();
        //clicks the insert column menu option based on the specified direction

        WebElement floatingWidow = wait(driver).until(ExpectedConditions.presenceOfElementLocated(By.className
                ("clsContent")));
        //Finds floatingWindow

        WebElement columnNameField =  floatingWidow.findElement(By.className("clsUserEnteredText"));
        //finds input within floating window

        columnNameField.sendKeys("New Column " + direction);
        //names column based on the specified direction from the existing column
        columnNameField.sendKeys(Keys.RETURN);
        //Submits
    }

    private void deleteColumnByIndex(Integer index) {
       clickColumnByIndex(index);
       clickControlButton();
        driver.findElement(By.xpath("//td[contains (@class, 'clsStandardMenuText') and text()= 'Delete Column']"))
                .click();
        //clicks the delete Column menu option
    }

    private void deleteAddedColumns() {
        deleteColumnByIndex(0);
        deleteColumnByIndex(1);
        //Since the columns are updated after each deletion, the index of the second added columns is 1 instead of 2
        // after the first column is deleted
    }

    public void validateAddingColumn() {
        addColumn("Right");
        addColumn("Left");
        //adds columns to the left and right of the first column

        List<WebElement> updatedColumns = wait(driver).until(ExpectedConditions.presenceOfAllElementsLocatedBy
                (columnsLocator));
        //grabs updated columns

        Assertions.assertEquals(expectedAfterAdditions, getColumnNames(updatedColumns));
        //Asserts that updated columns match our expectations after columms have been added.

        deleteAddedColumns();
        //deletes the columns we added

        updatedColumns = wait(driver).until(ExpectedConditions.presenceOfAllElementsLocatedBy
                (columnsLocator));
        //re-grabs updated columns

        Assertions.assertEquals(expectedColumnsAfterDeletion, getColumnNames(updatedColumns));
        //Asserts that updated columns match our expectations after columms have been deleted.
    }
}
