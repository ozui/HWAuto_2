package practice3.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import practice3.pages.LoginPage;
import practice3.pages.PlayersPage;

import java.util.concurrent.TimeUnit;

/**
 * Created by Oksana on 30.11.2016.
 */
public class LoginTests {
    private WebDriver driver;
    private LoginPage loginPage;

    @BeforeTest
    public void setUp() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }

    /**
     * Precondition
     * 1.Open application Login Page URL
     */
    @BeforeMethod
    public void beforeMethod() {
        loginPage = new LoginPage(driver);
        loginPage.open();
    }

    /**
     * 1.Set username to "admin"
     * 2.Set password to "123"
     * 3.Click on login button
     * 4.Verify that the title of opened page is equal to "Players"
     * 5.Verify that URL is not equal to Login URL
     */

    @Test
    public void positiveTest() {
        loginPage.setUsername("admin");
        loginPage.setPassword("123");
        loginPage.clickLogin();

        PlayersPage playersPage = new PlayersPage(driver);
        Assert.assertEquals(playersPage.getTitle(), "Players", "Wrong title after login");
        Assert.assertNotEquals(driver.getCurrentUrl(), LoginPage.URL, "You are still on login page");
    }

    /**
     * 1.Set username to "admin"
     * 2.Set password to "666"
     * 3.Click on login button
     * 4.Verify that error message is displayed
     * 5.Verify that error message equals "Invalid username or password"
     */

    @Test
    public void negativeTestWrongPassword() {
        loginPage.setUsername("admin");
        loginPage.setPassword("666");
        loginPage.clickLogin();

        Assert.assertTrue(loginPage.isErrorDisplayed(), "Error message is not displayed");
        Assert.assertEquals(loginPage.getErrorMessage(), "Invalid username or password", "Wrong error message");

    }

    /**
     * 1.Set username to "admin"
     * 2.Click on login button
     * 3.Verify that error message is displayed
     * 4.Verify that error message equals "Invalid username or password"
     */

    @Test
    public void blankPasswordFieldTest() {
        loginPage.setUsername("admin");
        loginPage.clickLogin();

        Assert.assertTrue(loginPage.isErrorDisplayed(), "Error message is not displayed");
        Assert.assertEquals(loginPage.getErrorMessage(), "Value is required and can't be empty", "Wrong error message");
    }

    /**
     * 1.Set password to "123"
     * 2.Click on login button
     * 3.Verify that error message is displayed
     * 4.Verify that error message equals "Invalid username or password"
     */

    @Test
    public void blankUsernameFieldTest() {
        loginPage.setPassword("123");
        loginPage.clickLogin();

        Assert.assertTrue(loginPage.isErrorDisplayed(), "Error message is not displayed");
        Assert.assertEquals(loginPage.getErrorMessage(), "Value is required and can't be empty", "Wrong error message");
    }

    @AfterTest
    public void afterTests() {
        driver.quit();
    }


}
