package pageObjects;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.UserProductDetailPageUI;

public class UserProductDetailPageObject extends BasePage {
    WebDriver driver;

    public UserProductDetailPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public String getProductPriceAtProductDetailPage() {
        waitForElementVisible(driver, UserProductDetailPageUI.PRODUCT_PRICE_AT_PDETAIL_PAGE);
        return getElementText(driver, UserProductDetailPageUI.PRODUCT_PRICE_AT_PDETAIL_PAGE);
    }
}
