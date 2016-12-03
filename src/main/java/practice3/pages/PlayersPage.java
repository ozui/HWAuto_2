package practice3.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.NoSuchElementException;

/**
 * Created by Oksana on 30.11.2016.
 */
public class PlayersPage {
    private WebDriver driver;
    public static final String URL = "http://80.92.229.236:81/players";

    public PlayersPage(WebDriver driver) {
        this.driver = driver;
    }

    public void open() {
        driver.get(URL);
    }

    public String getTitle() {
        return driver.getTitle();
    }

    public void searchByUsername(String username) {
        driver.findElement(By.xpath("//input[contains(@id,'login')]")).clear();
        driver.findElement(By.xpath("//input[contains(@id,'login')]")).sendKeys(username);
        driver.findElement(By.xpath("//input[@value='Search']")).click();
    }

    public void clickInsert() {
        driver.findElement(By.xpath("//a[text()='Insert']")).click();
    }

    public void clickEdit(String username) {
        driver.findElement(By.xpath("//tr[.//a[contains(text(),'" + username + "')]]//a[.//img[@alt = 'Edit']]")).click();
    }

    public void clickDelete(String username) {
        driver.findElement(By.xpath("//tr[.//a[contains(text(),'" + username + "')]]//a[.//img[@alt = 'Delete']]")).click();
        driver.switchTo().alert().accept();
    }

    public void clickActivate(String username) {
        driver.findElement(By.xpath("//tr[.//a[contains(text(),'" + username + "')]]//a[.//img[@alt = 'Activate']]")).click();
        driver.switchTo().alert().accept();
    }

    public String getResultMessage() {
        return driver.findElement(By.xpath("//div[contains(@id,'datagrid_flashmessagespanel')]//li")).getText();
    }

    public boolean isResultMessageDisplayed() {
        return driver.findElement(By.xpath("//div[contains(@id,'datagrid_flashmessagespanel')]//li")).isDisplayed();
    }

    public String getPlayersState(String username) {
        return driver.findElement(By.xpath("//tr[.//a[text()='" + username + "']]//td[contains(text(),'Deleted') or contains(text(),'Active')]")).getText();
    }

    public boolean isPlayerPresent(String username) {
        try {
            driver.findElement(By.xpath("//tr[.//a[text()='" + username + "']]"));
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public void checkShowDeleted() {
        if (!driver.findElement(By.xpath("//input[contains(@id,'deleted')]")).isSelected()) {
            driver.findElement(By.xpath("//input[contains(@id,'deleted')]")).click();
        }
    }
}
