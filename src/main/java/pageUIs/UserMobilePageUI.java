package pageUIs;

public class UserMobilePageUI {
    public static final String PRODUCT_PRICE_AT_MOBILE_PAGE_BY_PRODUCT_NAME = "xpath=//a[text()='%s']/parent::h2/following-sibling::div[@class='price-box']//span[@class='price']";
    public static final String ADD_TO_CART_BUTTON_BY_PRODUCT_NAME = "xpath=//a[text()='%s']/parent::h2/following-sibling::div[@class='actions']/button";
    public static final String PRODUCT_IMAGE_AT_MOBILE_PAGE_BY_PRODUCT_NAME = "xpath=//a[text()='%s']/ancestor::div[@class='product-info']/preceding-sibling::a[@class='product-image']";
}
