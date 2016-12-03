package practice3.tests;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import practice3.pages.EditPlayerPage;
import practice3.pages.LoginPage;
import practice3.pages.PlayersPage;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created by Oksana on 02.12.2016.
 */
public class CRUDUserTests {

    private WebDriver driver;
    private PlayersPage playersPage;

    @BeforeTest
    public void setUp() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);

        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        loginPage.setUsername("admin");
        loginPage.setPassword("123");
        loginPage.clickLogin();
    }

    /**
     * Pre-condition
     * 1. Players Page is opened
     */
    @BeforeMethod
    public void beforeMethod() {
        playersPage = new PlayersPage(driver);
        playersPage.open();
    }

    /**
     * Steps to reproduce:
     * 1. Click Insert
     * 2. Set fields:
     * Username
     * Email
     * Password
     * Confirm Password.
     * First Name
     * Last Name
     * City
     * Country
     * Address
     * Phone
     * 3. Click save
     * 4. Verify that the Players Page is opened
     * 5. Find user by username
     * 6. Open Edit Page for this user
     * 7. Verify the contents of the fields:
     * Email
     * First Name
     * Last Name
     * City
     * Country
     * Address
     * Phone
     */

    @Test
    public void createUserTest() {
        String expectedUsername = getRandomUsername();
        String expectedEmail = getRandomEmail();
        String expectedPass = getRandomPass();

        playersPage.clickInsert();
        EditPlayerPage editPlayerPage = new EditPlayerPage(driver);
        editPlayerPage.setUsername(expectedUsername);
        editPlayerPage.setEmail(expectedEmail);
        editPlayerPage.setPassword(expectedPass);
        editPlayerPage.setConfirmPassword(expectedPass);
        editPlayerPage.setFirstName("First Name");
        editPlayerPage.setLastName("Last Name");
        editPlayerPage.setCountry("UKRAINE");
        editPlayerPage.setCity("Kharkiv");
        editPlayerPage.setAddress("Freedom Square 5/1");
        editPlayerPage.setPhone("050-20-30-40");
        editPlayerPage.clickSave();

        Assert.assertEquals(driver.getCurrentUrl(), playersPage.URL, "After creating user players page is not opened");
        playersPage.searchByUsername(expectedUsername);
        playersPage.clickEdit(expectedUsername);

        StringBuilder errors = new StringBuilder();
        assertSoft(editPlayerPage.getEmail(), expectedEmail, "Actual email doesn't match expected one", errors);
        assertSoft(editPlayerPage.getFirstName(), "First Name", "Actual fname doesn't match expected one", errors);
        assertSoft(editPlayerPage.getLastName(), "Last Name", "Actual lname doesn't match expected one", errors);
        assertSoft(editPlayerPage.getCountry(), "UKRAINE", "Actual country doesn't match expected one", errors);
        assertSoft(editPlayerPage.getCity(), "Kharkiv", "Actual city doesn't match expected one", errors);
        assertSoft(editPlayerPage.getAddress(), "Freedom Square 5/1", "Actual address doesn't match expected one", errors);
        assertSoft(editPlayerPage.getPhone(), "050-20-30-40", "Actual phone doesn't match expected one", errors);

        if (!"".equals(errors)) {
            throw new AssertionError(errors);
        }

    }

    /**
     * Steps to reproduce:
     * 1. Click Edit for first player in table
     * 2. Set fields:
     * Email
     * First Name
     * Last Name
     * City
     * Country
     * Address
     * Phone
     * 3. Click save
     * 4. Verify that the Players Page is opened
     * 5. Find user by username
     * 6. Open Edit Page for this user
     * 7. Verify the contents of the fields:
     * Email
     * First Name
     * Last Name
     * City
     * Country
     * Address
     * Phone
     */

    @Test
    public void editUser() {
        playersPage.clickEdit("");
        EditPlayerPage editPlayerPage = new EditPlayerPage(driver);
        String expectedEmail = getRandomEmail();
        String expectedFname = getRandomName();
        String expectedLname = getRandomName();
        String expectedCity = getRandomCity();
        String expectedCountry = getRandomCountry();
        String expectedAddress = getRandomAddress();
        String expectedPhone = getRandomPhone();
        String expectedUsername = editPlayerPage.getUsername();

        editPlayerPage.setEmail(expectedEmail);
        editPlayerPage.setFirstName(expectedFname);
        editPlayerPage.setLastName(expectedLname);
        editPlayerPage.setCity(expectedCity);
        editPlayerPage.setCountry(expectedCountry);
        editPlayerPage.setAddress(expectedAddress);
        editPlayerPage.setPhone(expectedPhone);
        editPlayerPage.clickSave();

        Assert.assertEquals(driver.getCurrentUrl(), playersPage.URL, "After editing user players page is not opened");
        playersPage.searchByUsername(expectedUsername);
        playersPage.clickEdit(expectedUsername);

        StringBuilder errors = new StringBuilder();
        assertSoft(editPlayerPage.getEmail(), expectedEmail, "Actual email doesn't match expected one", errors);
        assertSoft(editPlayerPage.getFirstName(), expectedFname, "Actual fname doesn't match expected one", errors);
        assertSoft(editPlayerPage.getLastName(), expectedLname, "Actual lname doesn't match expected one", errors);
        assertSoft(editPlayerPage.getCountry(), expectedCountry, "Actual country doesn't match expected one", errors);
        assertSoft(editPlayerPage.getCity(), expectedCity, "Actual city doesn't match expected one", errors);
        assertSoft(editPlayerPage.getAddress(), expectedAddress, "Actual address doesn't match expected one", errors);
        assertSoft(editPlayerPage.getPhone(), expectedPhone, "Actual phone doesn't match expected one", errors);

        if (!"".equals(errors)) {
            throw new AssertionError(errors);
        }

    }

    /**
     * Pre-conditions:
     * 1.User "userdeletez" exists
     * Steps to reproduce:
     * 1.Find user "userdeletez"
     * 2.Click delete
     * 3.Verify that updated table doesn't contain user "userdeletez"
     * 4.Verify that message "Player has been deleted" is displayed
     * 5.Check "Show deleted" checkbox and find user "userdeletez"
     * 6.Verify that user is displayed
     * 7.Verify that player's state is equal to "Deleted"
     */

    @Test(groups = "delete")
    public void deleteUserTest() {
        playersPage.searchByUsername("userdeletez");
        playersPage.clickDelete("userdeletez");
        Assert.assertFalse(playersPage.isPlayerPresent("userdeletez"));
        Assert.assertTrue(playersPage.isResultMessageDisplayed(), "Result message is not displayed");
        Assert.assertEquals(playersPage.getResultMessage(), "Player has been deleted", "Result message is incorrect");

        playersPage.checkShowDeleted();
        playersPage.searchByUsername("userdeletez");
        Assert.assertTrue(playersPage.isPlayerPresent("userdeletez"));
        Assert.assertEquals(playersPage.getPlayersState("userdeletez"), "Deleted", "User's state doesn't equals 'Deleted'");

    }

    /**
     * Post-conditions:
     * 1.Activate user "userdeletez"
     */

    @AfterGroups(groups = "delete")
    public void afterDelete() {
        playersPage.clickActivate("userdeletez");
    }


    @AfterTest
    public void afterTest() {

        driver.quit();
    }

    private static String getRandomEmail() {
        return RandomStringUtils.random(10, true, true) + "@test.com";
    }

    private static String getRandomPass() {
        return RandomStringUtils.random(10, true, true);
    }

    private static String getRandomUsername() {
        return RandomStringUtils.random(10, true, true);
    }

    private static String getRandomCity() {
        return RandomStringUtils.randomAlphabetic(5) + "-city";
    }

    private static String getRandomName() {
        return RandomStringUtils.randomAlphabetic(5) + "-name";
    }

    private static String getRandomPhone() {
        return RandomStringUtils.randomNumeric(9);
    }

    private static String getRandomAddress() {
        return "Address" + RandomStringUtils.randomNumeric(3);
    }

    private static String getRandomCountry() {
        List<String> countries = new ArrayList<String>();
        countries.add("UKRAINE");
        countries.add("BELARUS");
        countries.add("BELIZE");
        countries.add("BULGARIA");
        countries.add("COLOMBIA");
        countries.add("CYPRUS");
        countries.add("GAMBIA");
        countries.add("GUATEMALA");
        countries.add("INDONESIA");
        countries.add("KAZAKHSTAN");
        countries.add("KIRIBATI");
        return countries.get(new Random().nextInt(countries.size()));
    }

    public static void assertSoft(String actualResult, String expectedResult, String message, StringBuilder verificationErrors) {
        try {
            Assert.assertEquals(actualResult, expectedResult, message);
        } catch (AssertionError e) {
            verificationErrors.append(e.toString() + "\n");
        }
    }
}
