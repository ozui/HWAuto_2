package practice2;

import org.apache.commons.lang3.RandomStringUtils;

/**
 * Created by Oksana on 24.11.2016.
 */
public class Credentials {
    public static final String URL = "http://80.92.229.236:81/";

    public static final String USERNAME = getRandomUsername();
    public static final String PASSWORD = getRandomPass();
    public static final String EMAIL = getRandomEmail();
    public static final String FNAME = "First Name";
    public static final String LNAME = "Last Name";
    public static final String COUNTRY = "UKRAINE";
    public static final String CITY = "Kharkiv";
    public static final String ADDRESS = "Freedom Square 5/1";
    public static final String PHONE = "937-9992";

    public static final String EDITED_EMAIL = getRandomEmail();
    public static final String EDITED_FNAME = "Jim";
    public static final String EDITED_LNAME = "Beam";
    public static final String EDITED_COUNTRY = "CANADA";
    public static final String EDITED_CITY = "Frankfort";
    public static final String EDITED_ADDRESS = "216 Boxwood dr";
    public static final String EDITED_PHONE = "999-9898";

    public static final String INSERT_LINK = "//a[text()='Insert']";
    public static final String EDIT_LINK = "//tr[.//a[text()='" + Credentials.USERNAME + "']]//a[.//img[@alt = 'Edit']]";
    public static final String SEARCH_BUTTON = "//input[@value='Search']";


    public static final String LOGIN_USERNAME_FIELD = "username";
    public static final String LOGIN_PASSWORD_FIELD = "password";
    public static final String LOGIN_BUTTON = "logIn";

    public static final String USERNAME_FIELD = "//input[contains(@id,'login')]";
    public static final String EMAIL_FIELD = "//input[contains(@id,'us_email')]";
    public static final String PASSWORD_FIELD = "//input[contains(@id,'us_password')]";
    public static final String CONFIRM_PASSWORD_FIELD = "//input[contains(@id,'confirm_password')]";
    public static final String FNAME_FIELD = "//input[contains(@id,'us_fname')]";
    public static final String LNAME_FIELD = "//input[contains(@id,'us_lname')]";
    public static final String COUNTRY_FIELD = "//select[contains(@id,'us_country')]";
    public static final String CITY_FIELD = "//input[contains(@id,'us_city')]";
    public static final String ADDRESS_FIELD = "//textarea[contains(@id,'us_address')]";
    public static final String PHONE_FIELD = "//input[contains(@id,'us_phone')]";
    public static final String SAVE_BUTTON = "//input[@value='Save']";


    private static String getRandomEmail() {
        return RandomStringUtils.random(10, true, true) + "@test.com";
    }

    private static String getRandomPass() {
        return RandomStringUtils.random(10, true, true);
    }

    private static String getRandomUsername() {
        return RandomStringUtils.random(10, true, true);
    }


}
