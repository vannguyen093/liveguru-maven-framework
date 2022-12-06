package pageObjects;

import commons.BasePage;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import pageUIs.AdminHomePageUI;

public class AdminHomePageObject extends BasePage {
    WebDriver driver;

    public AdminHomePageObject(WebDriver driver) {
        this.driver = driver;
    }

    public void closeAdminPopup() {
        waitForElementClickable(driver, AdminHomePageUI.CLOSE_POPUP_BUTTON);
        clickToElement(driver, AdminHomePageUI.CLOSE_POPUP_BUTTON);
    }

    public void inputToFilterByFilterName(String filterName, String value) {
        waitForElementVisible(driver, AdminHomePageUI.FILTER_TEXTBOX_BY_FILTER_NAME, filterName);
        sendkeysToElement(driver, AdminHomePageUI.FILTER_TEXTBOX_BY_FILTER_NAME, value, filterName);
        pressKeyToElement(driver, AdminHomePageUI.FILTER_TEXTBOX_BY_FILTER_NAME, Keys.ENTER, filterName);
    }

    public boolean isRegisteredAccountDisplayed(String fullNameText, String emailAddressText) {
        waitForElementVisible(driver, AdminHomePageUI.CUSTOMER_TEXT_AT_ROW, fullNameText, emailAddressText);
        return isElementDisplayed(driver, AdminHomePageUI.CUSTOMER_TEXT_AT_ROW, fullNameText, emailAddressText);
    }
}
