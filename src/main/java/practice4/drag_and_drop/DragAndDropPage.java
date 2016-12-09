package practice4.drag_and_drop;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Oksana on 08.12.2016.
 */
public class DragAndDropPage {
    @FindBy(xpath = "//li[@class='ui-state-default']")
    private List<WebElement> items;

    @FindBy(id = "drop")
    private WebElement trash;

    private WebDriver driver;
    private static final String URL = "file:///C:/Users/Oksana/Downloads/drag_and_drop2/drag_and_drop2/index.html";

    public DragAndDropPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void open() {
        driver.get(URL);
    }

    public void deleteItem(int item) {
        Actions action = new Actions(driver);
        WebElement draggable = null;
        for (WebElement i : items) {
            if (Integer.valueOf(i.getText()) == item) {
                draggable = i;
            }
        }
        action.dragAndDrop(draggable, trash).perform();
    }

    public String getAlertText() {
        return driver.switchTo().alert().getText();
    }

    public void acceptAlert() {
        driver.switchTo().alert().accept();
    }

    public void dismissAlert() {
        driver.switchTo().alert().dismiss();
    }

    public boolean isElementPresent(int item) {
        for (WebElement i : items) {
            if (Integer.valueOf(i.getText()) == item) {
                return true;
            }
        }
        return false;
    }

    public List<String> getItems() {
        List<String> itemsToString = new ArrayList<String>();
        for (WebElement item : items) {
            itemsToString.add(item.getText());
        }
        return itemsToString;
    }

    public void sortItems() {
        List<WebElement> elements = getSortedList();
        Actions actions = new Actions(driver);
        for (int i = 0; i < elements.size(); i++) {
            actions.clickAndHold(elements.get(i)).moveToElement(items.get(i)).release().perform();
        }

    }

    public void reverseSortItems() {
        List<WebElement> elements = getSortedList();
        Collections.reverse(elements);
        Actions actions = new Actions(driver);
        for (int i = 0; i < elements.size(); i++) {
            actions.clickAndHold(elements.get(i)).moveToElement(items.get(i)).release().perform();
        }
    }

    private List<WebElement> getSortedList() {
        List<WebElement> elements = new ArrayList<WebElement>(this.items);
        Collections.sort(elements, new Comparator<WebElement>() {
            public int compare(WebElement o1, WebElement o2) {
                int el1 = Integer.valueOf(o1.getText());
                int el2 = Integer.valueOf(o2.getText());
                return el1 < el2 ? -1 : el1 == el2 ? 0 : 1;
            }
        });
        return elements;
    }

}
