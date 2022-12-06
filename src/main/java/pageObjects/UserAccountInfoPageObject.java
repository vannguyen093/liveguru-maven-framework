package pageObjects;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.UserAccountInfoPageUI;

public class UserAccountInfoPageObject extends BasePage {
    WebDriver driver;

    public UserAccountInfoPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public String getTextboxValueByID(String textboxID) {
        waitForElementVisible(driver, UserAccountInfoPageUI.REGISTERED_ACCOUNT_INFO_BY_ID, textboxID);
        return getElementAttribute(driver, UserAccountInfoPageUI.REGISTERED_ACCOUNT_INFO_BY_ID, "value", textboxID);
    }
}
