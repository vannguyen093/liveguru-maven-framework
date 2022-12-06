package pageObjects;

import commons.BasePage;
import org.openqa.selenium.WebDriver;

public class UserRegisterPageObject extends BasePage {
    WebDriver driver;

    public UserRegisterPageObject(WebDriver driver) {
        this.driver = driver;
    }
}
