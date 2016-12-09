package practice4.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import practice4.pages.LoginPage;
import practice4.pages.PlayersPage;

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

    @Parameters({"username", "password", "title"})
    @Test
    public void positiveTest(String username, String password, String title) {
        loginPage.setUsername(username);
        loginPage.setPassword(password);
        loginPage.clickLogin();

        PlayersPage playersPage = new PlayersPage(driver);
        Assert.assertNotEquals(driver.getCurrentUrl(), LoginPage.URL, "You are still on login page");
        Assert.assertEquals(playersPage.getTitle(), title, "Wrong title after login");
    }

    /**
     * 1.Set username to "admin"
     * 2.Set password to "666"
     * 3.Click on login button
     * 4.Verify that error message is displayed
     * 5.Verify that error message equals "Invalid username or password"
     */

    @Parameters({"username", "wrong_password", "error_if_wrong_password"})
    @Test()
    public void negativeTestWrongPassword(String username, String password, String expectedMessage) {
        loginPage.setUsername(username);
        loginPage.setPassword(password);
        loginPage.clickLogin();

        Assert.assertTrue(loginPage.isUsernameErrorDisplayed(), "Error message is not displayed");
        Assert.assertEquals(loginPage.getUsernameErrorMessage(), expectedMessage, "Wrong error message");

    }

    /**
     * 1.Set username to "admin"
     * 2.Click on login button
     * 3.Verify that error message is displayed
     * 4.Verify that error message equals "Value is required and can't be empty"
     */

    @Parameters({"username", "error_if_blank"})
    @Test
    public void blankPasswordFieldTest(String username, String errorMsg) {
        loginPage.setUsername(username);
        loginPage.clickLogin();

        Assert.assertTrue(loginPage.isPasswordErrorDisplayed(), "Error message is not displayed");
        Assert.assertEquals(loginPage.getPasswordErrorMessage(), errorMsg, "Wrong error message");
    }

    /**
     * 1.Set password to "123"
     * 2.Click on login button
     * 3.Verify that error message is displayed
     * 4.Verify that error message equals "Value is required and can't be empty"
     */

    @Parameters({"password", "error_if_blank"})
    @Test
    public void blankUsernameFieldTest(String password, String errorMsg) {
        loginPage.setPassword(password);
        loginPage.clickLogin();

        Assert.assertTrue(loginPage.isUsernameErrorDisplayed(), "Error message is not displayed");
        Assert.assertEquals(loginPage.getUsernameErrorMessage(), errorMsg, "Wrong error message");
    }

    @AfterTest
    public void afterTests() {
        driver.quit();
    }


}
