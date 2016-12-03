package practice3.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Created by Oksana on 30.11.2016.
 */
public class LoginPage {
    private WebDriver driver;
    public static final String URL = "http://80.92.229.236:81/auth/login";

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void open() {
        driver.get(URL);
    }

    public void setUsername(String username) {
        WebElement element = driver.findElement(By.id("username"));
        element.clear();
        element.sendKeys(username);
    }

    public void setPassword(String password) {
        WebElement element = driver.findElement(By.id("password"));
        element.clear();
        element.sendKeys(password);
    }

    public void clickLogin() {
        driver.findElement(By.id("logIn")).click();
    }

    public String getErrorMessage() {
        return driver.findElement(By.xpath("//ul[@class='errors']/li")).getText();
    }

    public boolean isErrorDisplayed(){
        return driver.findElement(By.xpath("//ul[@class='errors']/li")).isDisplayed();
    }
}
