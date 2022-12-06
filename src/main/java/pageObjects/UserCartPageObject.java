package pageObjects;

import commons.BasePage;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import pageUIs.UserCartPageUI;

public class UserCartPageObject extends BasePage {
    WebDriver driver;

    public UserCartPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isSuccessfullyAddedMessageDisplayed() {
        waitForElementVisible(driver, UserCartPageUI.PRODUCT_ADDED_SUCCESSFUL_MESSAGE_TEXT);
        return isElementDisplayed(driver, UserCartPageUI.PRODUCT_ADDED_SUCCESSFUL_MESSAGE_TEXT);
    }

    public void inputToDiscountCodeTextBox(String couponText) {
        waitForElementVisible(driver, UserCartPageUI.DISCOUNT_CODES_TEXTBOX);
        sendkeysToElement(driver, UserCartPageUI.DISCOUNT_CODES_TEXTBOX, couponText);
        pressKeyToElement(driver, UserCartPageUI.DISCOUNT_CODES_TEXTBOX, Keys.ENTER);
    }

    public String getAddedDiscountText() {
        waitForElementVisible(driver, UserCartPageUI.DISCOUNT_CODES_ADDED_TEXT);
        return getElementText(driver, UserCartPageUI.DISCOUNT_CODES_ADDED_TEXT);
    }

    public String getGrandTotalText() {
        waitForElementVisible(driver, UserCartPageUI.GRAND_TOTAL);
        return getElementText(driver, UserCartPageUI.GRAND_TOTAL);
    }

    public void clickToRemoveItemIcon() {
        waitForElementClickable(driver, UserCartPageUI.REMOVE_ITEM_ICON);
        clickToElement(driver, UserCartPageUI.REMOVE_ITEM_ICON);
    }
}
