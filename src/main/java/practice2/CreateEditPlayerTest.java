package practice2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by Oksana on 24.11.2016.
 */
public class CreateEditPlayerTest {

    public static void main(String[] args) {
        PockerPlayer expectedPlayer = new PockerPlayer(Credentials.USERNAME, Credentials.EMAIL, Credentials.PASSWORD,
                Credentials.FNAME, Credentials.LNAME, Credentials.COUNTRY,
                Credentials.CITY, Credentials.ADDRESS, Credentials.PHONE);

        WebDriver driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get(Credentials.URL + "/auth/login");

        driver.findElement(By.id(Credentials.LOGIN_USERNAME_FIELD)).sendKeys("admin");
        driver.findElement(By.id(Credentials.LOGIN_PASSWORD_FIELD)).sendKeys("123");
        driver.findElement(By.id(Credentials.LOGIN_BUTTON)).click();

        driver.findElement(By.xpath(Credentials.INSERT_LINK)).click();
        createPlayer(expectedPlayer, driver);
        assertTitle(driver.getTitle(), "Players");

        findPlayerAndOpenEdit(driver, Credentials.USERNAME);
        PockerPlayer actualPlayer = getPlayerFromEdit(driver);
        assertPlayer(actualPlayer, expectedPlayer, "User data is displayed incorrectly after creating user");

        expectedPlayer = new PockerPlayer(Credentials.EDITED_EMAIL, Credentials.EDITED_FNAME, Credentials.EDITED_LNAME,
                Credentials.EDITED_COUNTRY, Credentials.EDITED_CITY,
                Credentials.EDITED_ADDRESS, Credentials.EDITED_PHONE);

        editPlayer(driver, expectedPlayer);

        findPlayerAndOpenEdit(driver, Credentials.USERNAME);
        actualPlayer = getPlayerFromEdit(driver);
        assertPlayer(actualPlayer, expectedPlayer, "User data is displayed incorrectly after editing user");


    }

    public static void createPlayer(PockerPlayer player, WebDriver driver) {

        driver.findElement(By.xpath(Credentials.USERNAME_FIELD)).sendKeys(player.getUsername());
        driver.findElement(By.xpath(Credentials.EMAIL_FIELD)).sendKeys(player.getEmail());
        driver.findElement(By.xpath(Credentials.PASSWORD_FIELD)).sendKeys(player.getPassword());
        driver.findElement(By.xpath(Credentials.CONFIRM_PASSWORD_FIELD)).sendKeys(player.getPassword());
        driver.findElement(By.xpath(Credentials.FNAME_FIELD)).sendKeys(player.getFname());
        driver.findElement(By.xpath(Credentials.LNAME_FIELD)).sendKeys(player.getLname());
        driver.findElement(By.xpath(Credentials.CITY_FIELD)).sendKeys(player.getCity());
        driver.findElement(By.xpath(Credentials.COUNTRY_FIELD)).sendKeys(player.getCountry());
        driver.findElement(By.xpath(Credentials.ADDRESS_FIELD)).sendKeys(player.getAddress());
        driver.findElement(By.xpath(Credentials.PHONE_FIELD)).sendKeys(player.getPhone());
        driver.findElement(By.xpath(Credentials.SAVE_BUTTON)).click();
    }

    public static void editPlayer(WebDriver driver, PockerPlayer player) {
        clearAndFillField(driver.findElement(By.xpath(Credentials.EMAIL_FIELD)), player.getEmail());
        clearAndFillField(driver.findElement(By.xpath(Credentials.FNAME_FIELD)), player.getFname());
        clearAndFillField(driver.findElement(By.xpath(Credentials.LNAME_FIELD)), player.getLname());
        clearAndFillField(driver.findElement(By.xpath(Credentials.CITY_FIELD)), player.getCity());
        driver.findElement(By.xpath(Credentials.COUNTRY_FIELD)).sendKeys(player.getCountry());
        clearAndFillField(driver.findElement(By.xpath(Credentials.ADDRESS_FIELD)), player.getAddress());
        clearAndFillField(driver.findElement(By.xpath(Credentials.PHONE_FIELD)), player.getPhone());
        driver.findElement(By.xpath(Credentials.SAVE_BUTTON)).click();
    }

    public static PockerPlayer getPlayerFromEdit(WebDriver driver) {
        String email = driver.findElement(By.xpath(Credentials.EMAIL_FIELD)).getAttribute("value");
        String fname = driver.findElement(By.xpath(Credentials.FNAME_FIELD)).getAttribute("value");
        String lname = driver.findElement(By.xpath(Credentials.LNAME_FIELD)).getAttribute("value");
        String city = driver.findElement(By.xpath(Credentials.CITY_FIELD)).getAttribute("value");
        List<WebElement> countryOptions = driver.findElements(By.xpath(Credentials.COUNTRY_FIELD + "/option"));
        String country = null;
        for (WebElement option : countryOptions) {
            if (option.isSelected()) {
                country = option.getAttribute("label");
            }
        }
        String address = driver.findElement(By.xpath(Credentials.ADDRESS_FIELD)).getAttribute("value");
        String phone = driver.findElement(By.xpath(Credentials.PHONE_FIELD)).getAttribute("value");
        return new PockerPlayer(email, fname, lname, country, city, address, phone);
    }

    public static void findPlayerAndOpenEdit(WebDriver driver, String username) {
        clearAndFillField(driver.findElement(By.xpath(Credentials.USERNAME_FIELD)), username);
        driver.findElement(By.xpath(Credentials.SEARCH_BUTTON)).click();
        driver.findElement(By.xpath(Credentials.EDIT_LINK)).click();
    }

    public static void assertTitle(String actualTitle, String expectedTitle) {
        if (!actualTitle.equals(expectedTitle)) {
            System.err.print("Actual title: " + actualTitle
                    + "\n" + "Expected title: " + expectedTitle);
        }
    }

    public static void clearAndFillField(WebElement element, String value) {
        element.clear();
        element.sendKeys(value);
    }

    public static void assertPlayer(PockerPlayer actualPlayer, PockerPlayer expectedPlayer, String message) {
        if (!actualPlayer.equals(expectedPlayer)) {
            System.err.print(message + "\n");
            if (!actualPlayer.getFname().equals(expectedPlayer.getFname())) {
                System.err.print("Actual fname: " + actualPlayer.getFname()
                        + "\t" + "Expected fname: " + expectedPlayer.getFname() + "\n");
            }
            if (!actualPlayer.getLname().equals(expectedPlayer.getLname())) {
                System.err.print("Actual lname: " + actualPlayer.getLname()
                        + "\t" + "Expected lname: " + expectedPlayer.getLname() + "\n");
            }
            if (!actualPlayer.getCity().equals(expectedPlayer.getCity())) {
                System.err.print("Actual city: " + actualPlayer.getCity()
                        + "\t" + "Expected fname: " + expectedPlayer.getCity() + "\n");
            }
            if (!actualPlayer.getCountry().equals(expectedPlayer.getCountry())) {
                System.err.print("Actual country: " + actualPlayer.getCountry()
                        + "\t" + "Expected country: " + expectedPlayer.getCountry() + "\n");
            }
            if (!actualPlayer.getAddress().equals(expectedPlayer.getAddress())) {
                System.err.print("Actual address: " + actualPlayer.getAddress()
                        + "\t" + "Expected address: " + expectedPlayer.getAddress() + "\n");
            }
            if (!actualPlayer.getPhone().equals(expectedPlayer.getPhone())) {
                System.err.print("Actual phone: " + actualPlayer.getPhone()
                        + "\t" + "Expected phone: " + expectedPlayer.getPhone() + "\n");
            }
            System.err.print("\n");
        }
    }


}
