package pageObjects;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.AdminDownloadedInvoiceTabPageUI;

public class AdminDownloadedInvoiceTabPageObject extends BasePage {
    WebDriver driver;

    public AdminDownloadedInvoiceTabPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isDownloadedInvoiceTabDisplayed() {
        switchToWindowByTitle(driver, getPageTitle(driver));
        return isElementDisplayed(driver, AdminDownloadedInvoiceTabPageUI.ORDER_NUMBER);
    }

    public AdminOrderPageObject closeDownloadedInvoiceTab() {
        closeAllWindowsWithoutParent(driver, getPageID(driver));
        return PageGenerateManager.getAdminOrderPage(driver);
    }
}
