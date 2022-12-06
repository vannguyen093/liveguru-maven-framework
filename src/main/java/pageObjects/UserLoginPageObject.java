package pageObjects;

import commons.BasePage;
import org.openqa.selenium.WebDriver;

public class UserLoginPageObject extends BasePage {
    WebDriver driver;

    public UserLoginPageObject(WebDriver driver) {
        this.driver = driver;
    }
}
