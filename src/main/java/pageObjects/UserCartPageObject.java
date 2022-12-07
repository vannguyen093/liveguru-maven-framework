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

    public String getCartMessage() {
        waitForElementVisible(driver, UserCartPageUI.CART_MESSAGE_TEXT);
        return getElementText(driver, UserCartPageUI.CART_MESSAGE_TEXT);
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

    public void inputToQuantityTextBox(String value) {
        waitForElementClickable(driver, UserCartPageUI.QUANTITY_TEXTBOX);
        clickToElement(driver, UserCartPageUI.QUANTITY_TEXTBOX);

        waitForElementVisible(driver, UserCartPageUI.QUANTITY_TEXTBOX);
        sendkeysToElement(driver, UserCartPageUI.QUANTITY_TEXTBOX, value);
    }

    public void clickToUpdateButton() {
        waitForElementClickable(driver, UserCartPageUI.UPDATE_BUTTON);
        clickToElement(driver, UserCartPageUI.UPDATE_BUTTON);
    }

    public String getProductQTYErrMessage() {
        waitForElementVisible(driver, UserCartPageUI.PRODUCT_QUANTITY_ERR_MESSAGE_TEXT);
        return getElementText(driver, UserCartPageUI.PRODUCT_QUANTITY_ERR_MESSAGE_TEXT);
    }

    public void clickToEmptyCartButton() {
        waitForElementClickable(driver, UserCartPageUI.EMPTY_CART_BUTTON);
        clickToElement(driver, UserCartPageUI.EMPTY_CART_BUTTON);
    }

    public boolean isEmptyCartHeaderDisplayed() {
        waitForElementVisible(driver, UserCartPageUI.EMPTY_CART_HEADER_TEXT);
        return isElementDisplayed(driver, UserCartPageUI.EMPTY_CART_HEADER_TEXT);
    }

    public boolean isNoItemMessageDisplayed() {
        waitForElementVisible(driver, UserCartPageUI.NO_ITEM_IN_CART_TEXT);
        return isElementDisplayed(driver, UserCartPageUI.NO_ITEM_IN_CART_TEXT);
    }
}
