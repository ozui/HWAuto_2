package practice4.tests;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import practice4.pages.EditPlayerPage;
import practice4.pages.LoginPage;
import practice4.pages.PlayersPage;

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
    private SoftAssert softAssert;

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
        softAssert = new SoftAssert();
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
    @DataProvider
    public Object[][] userDataForCreating() {
        return new Object[][]{
                {getRandomUsername(),
                        getRandomEmail(),
                        getRandomPass(),
                        getRandomName(),
                        getRandomName(),
                        getRandomCity(),
                        getRandomCountry(),
                        getRandomAddress(),
                        getRandomPhone()},
        };
    }

    @Test(dataProvider = "userDataForCreating")
    public void createUserTest(String username, String email, String password, String fname, String lname, String city, String country, String address, String phone) {
        playersPage.clickInsert();
        EditPlayerPage editPlayerPage = new EditPlayerPage(driver);
        editPlayerPage.setUsername(username);
        editPlayerPage.setEmail(email);
        editPlayerPage.setPassword(password);
        editPlayerPage.setConfirmPassword(password);
        editPlayerPage.setFirstName(fname);
        editPlayerPage.setLastName(lname);
        editPlayerPage.setCountry(country);
        editPlayerPage.setCity(city);
        editPlayerPage.setAddress(address);
        editPlayerPage.setPhone(phone);
        editPlayerPage.clickSave();

        Assert.assertEquals(driver.getCurrentUrl(), playersPage.URL, "After creating user players page is not opened");
        playersPage.searchByUsername(username);
        Assert.assertTrue(playersPage.isPlayerPresent(username), "Table doesn't contain player");
        playersPage.editUser(username);

        softAssert.assertEquals(editPlayerPage.getEmail(), email, "Actual email doesn't match expected one");
        softAssert.assertEquals(editPlayerPage.getFirstName(), fname, "Actual fname doesn't match expected one");
        softAssert.assertEquals(editPlayerPage.getLastName(), lname, "Actual lname doesn't match expected one");
        softAssert.assertEquals(editPlayerPage.getCountry(), country, "Actual country doesn't match expected one");
        softAssert.assertEquals(editPlayerPage.getCity(), city, "Actual city doesn't match expected one");
        softAssert.assertEquals(editPlayerPage.getAddress(), address, "Actual address doesn't match expected one");
        softAssert.assertEquals(editPlayerPage.getPhone(), phone, "Actual phone doesn't match expected one");
        softAssert.assertAll();

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

    @DataProvider
    public Object[][] userDataForEditing() {
        return new Object[][]{
                {getRandomEmail(),
                        getRandomName(),
                        getRandomName(),
                        getRandomCity(),
                        getRandomCountry(),
                        getRandomAddress(),
                        getRandomPhone()},
        };
    }

    @Test(dataProvider = "userDataForEditing")
    public void editUserTest(String email, String fname, String lname, String city, String country, String address, String phone) {
        playersPage.editUser("");
        EditPlayerPage editPlayerPage = new EditPlayerPage(driver);
        String username = editPlayerPage.getUsername();

        editPlayerPage.setEmail(email);
        editPlayerPage.setFirstName(fname);
        editPlayerPage.setLastName(lname);
        editPlayerPage.setCity(city);
        editPlayerPage.setCountry(country);
        editPlayerPage.setAddress(address);
        editPlayerPage.setPhone(phone);
        editPlayerPage.clickSave();

        Assert.assertEquals(driver.getCurrentUrl(), playersPage.URL, "After editing user players page is not opened");
        playersPage.searchByUsername(username);
        Assert.assertTrue(playersPage.isPlayerPresent(username), "Table doesn't contain player");
        playersPage.editUser(username);

        softAssert.assertEquals(editPlayerPage.getEmail(), email, "Actual email doesn't match expected one");
        softAssert.assertEquals(editPlayerPage.getFirstName(), fname, "Actual fname doesn't match expected one");
        softAssert.assertEquals(editPlayerPage.getLastName(), lname, "Actual lname doesn't match expected one");
        softAssert.assertEquals(editPlayerPage.getCountry(), country, "Actual country doesn't match expected one");
        softAssert.assertEquals(editPlayerPage.getCity(), city, "Actual city doesn't match expected one");
        softAssert.assertEquals(editPlayerPage.getAddress(), address, "Actual address doesn't match expected one");
        softAssert.assertEquals(editPlayerPage.getPhone(), phone, "Actual phone doesn't match expected one");
        softAssert.assertAll();
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

    @Parameters({"user_delete"})
    @Test
    public void deleteUserTest(String username) {
        playersPage.searchByUsername(username);
        playersPage.deleteUser(username);
        Assert.assertFalse(playersPage.isPlayerPresent(username), "Table doesn't contain player");
        softAssert.assertTrue(playersPage.isResultMessageDisplayed(), "Result message is not displayed");
        softAssert.assertEquals(playersPage.getResultMessage(), "Player has been deleted", "Result message is incorrect");

        playersPage.showDeletedUser();
        playersPage.searchByUsername(username);
        Assert.assertTrue(playersPage.isPlayerPresent(username), "Table doesn't contain player");
        softAssert.assertEquals(playersPage.getPlayersState(username), "Deleted", "User's state doesn't equals 'Deleted'");
        softAssert.assertAll();

    }

    /**
     * Pre-conditions:
     * 1.Show deleted checkbox is checked
     * 2.User "userdeletez" is deleted and present in result table
     * Steps to reproduce:
     * 1.Click activate user
     * 2.Verify that message "Player has been enabled successfully" is displayed
     * 3.Verify that user's state is equal to 'Active'
     * 4.Uncheck show deleted checkbox
     * 5.Find user and verify that table contains "userdeletez"
     */
    @Parameters({"user_delete"})
    @Test(dependsOnMethods = "deleteUserTest")
    public void activateUserTest(String username) {
        playersPage.activateUser(username);
        softAssert.assertTrue(playersPage.isResultMessageDisplayed(), "Result message is not displayed");
        softAssert.assertEquals(playersPage.getResultMessage(), "Player has been enabled successfully", "Result message is incorrect");

        playersPage.dontShowDeletedUser();
        playersPage.searchByUsername(username);
        softAssert.assertTrue(playersPage.isPlayerPresent(username), "Table doesn't contain player");
        softAssert.assertEquals(playersPage.getPlayersState(username), "Active", "User's state doesn't equals 'Active'");
        softAssert.assertAll();
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

}
