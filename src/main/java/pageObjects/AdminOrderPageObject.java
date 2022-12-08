package pageObjects;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.AdminOrderPageUI;
import pageUIs.BasePageUI;

public class AdminOrderPageObject extends BasePage {
    WebDriver driver;

    public AdminOrderPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public void selectItemAtOrderActionDropdownByName(WebDriver driver, String actionName) {
        waitForElementClickable(driver, AdminOrderPageUI.ACTIONS_AT_ORDER_PAGE_DROPDOWN);
        selectItemInDefaultDropdown(driver, AdminOrderPageUI.ACTIONS_AT_ORDER_PAGE_DROPDOWN, actionName);
    }

    public void selectItemAtStatusDropdown(String statusItemText) {
        waitForElementClickable(driver, AdminOrderPageUI.STATUS_ITEM_AT_STATUS_DROPDOWN_BY_NAME, statusItemText);
        selectItemInDefaultDropdown(driver,AdminOrderPageUI.STATUS_ITEM_AT_STATUS_DROPDOWN_BY_NAME, statusItemText);
    }

    public void selectFirstOrderByCheckbox() {
        waitForElementInvisible(driver, BasePageUI.LOADING_MASK);
        waitForElementClickable(driver, AdminOrderPageUI.FIRST_ORDER_CHECKBOX);
        checkToDefaultCheckboxRadio(driver, AdminOrderPageUI.FIRST_ORDER_CHECKBOX);
    }
}
