package practice4.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by Oksana on 30.11.2016.
 */
public class LoginPage {

    @FindBy(id = "username")
    private WebElement usernameField;
    @FindBy(id = "password")
    private WebElement passwordField;
    @FindBy(id = "logIn")
    private WebElement loginButton;
    @FindBy(xpath = "//*[@id='username-element']/ul/li")
    private WebElement usernameErrorContainer;
    @FindBy(xpath = "//*[@id='password-element']/ul/li")
    private WebElement passwordErrorContainer;

    private WebDriver driver;
    public static final String URL = "http://80.92.229.236:81/auth/login";

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void open() {
        driver.get(URL);
    }

    public void setUsername(String username) {
        usernameField.clear();
        usernameField.sendKeys(username);
    }

    public void setPassword(String password) {
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    public void clickLogin() {
        loginButton.click();
    }

    public String getUsernameErrorMessage() {
        return usernameErrorContainer.getText();
    }

    public String getPasswordErrorMessage(){
        return passwordErrorContainer.getText();
    }
    public boolean isUsernameErrorDisplayed(){
        return usernameErrorContainer.isDisplayed();
    }
    public boolean isPasswordErrorDisplayed(){
        return passwordErrorContainer.isDisplayed();
    }
}
