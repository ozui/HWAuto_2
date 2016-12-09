package practice4.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by Oksana on 30.11.2016.
 */
public class PlayersPage {
    @FindBy(xpath = "//input[contains(@id,'login')]")
    private WebElement playerSearchField;
    @FindBy(xpath = "//div[contains(@id,'datagrid_flashmessagespanel')]//li")
    private WebElement resultMessageContainer;
    @FindBy(xpath = "//input[contains(@id,'deleted')]")
    private WebElement showDeletedCheckbox;
    @FindBy(xpath = "//a[text()='Insert']")
    private WebElement insertButton;
    @FindBy(xpath = "//input[@value='Search']")
    private WebElement searchButton;


    private WebDriver driver;
    public static final String URL = "http://80.92.229.236:81/players";

    public PlayersPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void open() {
        driver.get(URL);
    }

    public String getTitle() {
        return driver.getTitle();
    }

    public void searchByUsername(String username) {
        playerSearchField.clear();
        playerSearchField.sendKeys(username);
        searchButton.click();
    }

    public void clickInsert() {
        insertButton.click();
    }

    //todo: don't know how to do it with FindBy
    public void editUser(String username) {
        driver.findElement(By.xpath("//tr[.//a[contains(text(),'" + username + "')]]//a[.//img[@alt = 'Edit']]")).click();
    }

    public void deleteUser(String username) {
        driver.findElement(By.xpath("//tr[.//a[contains(text(),'" + username + "')]]//a[.//img[@alt = 'Delete']]")).click();
        driver.switchTo().alert().accept();
    }

    public void activateUser(String username) {
        driver.findElement(By.xpath("//tr[.//a[contains(text(),'" + username + "')]]//a[.//img[@alt = 'Activate']]")).click();
        driver.switchTo().alert().accept();
    }

    public String getResultMessage() {
        return resultMessageContainer.getText();
    }

    public boolean isResultMessageDisplayed() {
        return resultMessageContainer.isDisplayed();
    }

    public String getPlayersState(String username) {
        return driver.findElement(By.xpath("//tr[.//a[text()='" + username + "']]//td[contains(text(),'Deleted') or contains(text(),'Active')]")).getText();
    }

    public boolean isPlayerPresent(String username) {
        return driver.findElements(By.xpath("//tr[.//a[text()='" + username + "']]")).size() > 0;
    }

    public void showDeletedUser() {
        if (!showDeletedCheckbox.isSelected()) {
            showDeletedCheckbox.click();
        }
    }

    public void dontShowDeletedUser() {
        if (showDeletedCheckbox.isSelected()) {
            showDeletedCheckbox.click();
        }
    }
}
