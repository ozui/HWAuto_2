package practice4.drag_and_drop;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by Oksana on 08.12.2016.
 */
public class DragAndDropTest {
    private WebDriver driver;
    private DragAndDropPage dragAndDropPage;
    private SoftAssert softAssert;

    @BeforeTest
    public void beforeTest() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        dragAndDropPage = new DragAndDropPage(driver);
        softAssert = new SoftAssert();
    }

    @BeforeMethod
    public void beforeMethod() {
        dragAndDropPage.open();
    }

    /**
     * 1. Drag any box to trash
     * 2. Verify alert text is "Are you sure that you want to delete?"
     * 3. Click cancel
     * 4. Verify that box wasn't deleted
     * 5. Drag box to trash
     * 6. Click OK
     * 7. Verify that element was deleted
     */
    @Test
    public void deleteItemTest() {
        dragAndDropPage.deleteItem(2);
        softAssert.assertEquals(dragAndDropPage.getAlertText(), "Are you sure that you want to delete?", "Alert text is incorrect");
        dragAndDropPage.dismissAlert();
        softAssert.assertTrue(dragAndDropPage.isElementPresent(2), "Element was deleted");
        dragAndDropPage.deleteItem(2);
        dragAndDropPage.acceptAlert();
        softAssert.assertFalse(dragAndDropPage.isElementPresent(2), "Element is still on page");
    }

    @DataProvider
    public Object[][] arrays() {
        return new Object[][]{
                {new ArrayList<String>(
                        Arrays.asList("1", "2", "3", "4", "5", "6", "7")),
                        new ArrayList<String>(
                                Arrays.asList("7", "6", "5", "4", "3", "2", "1"))}
        };
    }

    @Test(dataProvider = "arrays")
    public void sortItemsTest(List<String> sorted, List<String> reversed) {
        dragAndDropPage.sortItems();
        softAssert.assertEquals(dragAndDropPage.getItems(), sorted, "Items was sorted incorrectly");
        dragAndDropPage.reverseSortItems();
        softAssert.assertEquals(dragAndDropPage.getItems(), reversed, "Items was sorted incorrectly");
    }

    @AfterTest
    public void afterTest() {
        driver.quit();
    }

}
