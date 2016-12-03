package practice3.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


/**
 * Created by Oksana on 02.12.2016.
 */
public class EditPlayerPage {
    private WebDriver driver;
    @FindBy(xpath = "//input[contains(@id,'login')]")
    private WebElement usernameField;
    @FindBy(xpath = "//input[contains(@id,'us_email')]")
    private WebElement emailField;
    @FindBy(xpath = "//input[contains(@id,'us_password')]")
    private WebElement passwordField;
    @FindBy(xpath = "//input[contains(@id,'confirm_password')]")
    private WebElement confirmPasswordField;
    @FindBy(xpath = "//input[contains(@id,'us_fname')]")
    private WebElement fnameField;
    @FindBy(xpath = "//input[contains(@id,'us_lname')]")
    private WebElement lnameField;
    @FindBy(xpath = "//input[contains(@id,'us_city')]")
    private WebElement cityField;
    @FindBy(xpath = "//textarea[contains(@id,'us_address')]")
    private WebElement addressField;
    @FindBy(xpath = "//input[contains(@id,'us_phone')]")
    private WebElement phoneField;
    @FindBy(xpath = "//select[contains(@id,'us_country')]")
    private WebElement countryField;

    public EditPlayerPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void setUsername(String username) {
        usernameField.clear();
        usernameField.sendKeys(username);
    }

    public void setEmail(String email) {
        emailField.clear();
        emailField.sendKeys(email);
    }

    public void setPassword(String password) {
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    public void setConfirmPassword(String password) {
        confirmPasswordField.clear();
        confirmPasswordField.sendKeys(password);
    }

    public void setCity(String city) {
        cityField.clear();
        cityField.sendKeys(city);
    }

    public void setCountry(String country) {
        countryField.sendKeys(country);
    }

    public void setAddress(String address) {
        addressField.clear();
        addressField.sendKeys(address);
    }

    public void setPhone(String phone) {
        phoneField.clear();
        phoneField.sendKeys(phone);
    }

    public void setFirstName(String firstName) {
        fnameField.clear();
        fnameField.sendKeys(firstName);
    }

    public void setLastName(String lastName) {
        lnameField.clear();
        lnameField.sendKeys(lastName);
    }

    public String getEmail() {
        return emailField.getAttribute("value");
    }

    public String getCity() {
        return cityField.getAttribute("value");
    }

    public String getCountry() {
        return countryField.findElement(By.xpath("./option[@selected]")).getAttribute("label");
    }

    public String getAddress() {
        return addressField.getAttribute("value");
    }

    public String getPhone() {
        return phoneField.getAttribute("value");
    }

    public String getFirstName() {
        return fnameField.getAttribute("value");
    }

    public String getLastName() {
        return lnameField.getAttribute("value");
    }

    public String getUsername(){
        return usernameField.getAttribute("value");
    }

    public void clickSave() {
        driver.findElement(By.xpath("//input[@value='Save']")).click();
    }
}
