package pageUIs;

public class UserCartPageUI {
    public static final String CART_MESSAGE_TEXT = "xpath=//ul[@class='messages']//span";
    public static final String DISCOUNT_CODES_TEXTBOX = "css=input#coupon_code";
    public static final String DISCOUNT_CODES_ADDED_TEXT = "xpath=//table[@id='shopping-cart-totals-table']/tbody//td[contains(text(), 'Discount')]/following-sibling::td/span";
    public static final String GRAND_TOTAL = "xpath=//table[@id='shopping-cart-totals-table']/tfoot/tr//strong[contains(text(),'Grand Total')]/parent::td/following-sibling::td/strong/span";
    public static final String REMOVE_ITEM_ICON = "xpath=//table[@id='shopping-cart-table']//td[@class='a-center product-cart-remove last']/a[@class='btn-remove btn-remove2']";
    public static final String QUANTITY_TEXTBOX = "xpath=//table[@id='shopping-cart-table']//td[@class='product-cart-actions']/input[@class='input-text qty']";
    public static final String UPDATE_BUTTON = "xpath=//table[@id='shopping-cart-table']//td[@class='product-cart-actions']/button[@class='button btn-update']";
    public static final String PRODUCT_QUANTITY_ERR_MESSAGE_TEXT = "xpath=//table[@id='shopping-cart-table']//td[@class='product-cart-info']/p";
    public static final String EMPTY_CART_BUTTON = "xpath=//table[@id='shopping-cart-table']//td[@class='a-right cart-footer-actions last']/button[@id='empty_cart_button']";
    public static final String EMPTY_CART_HEADER_TEXT = "xpath=//div[@class='page-title']/h1";
    public static final String NO_ITEM_IN_CART_TEXT = "xpath=//div[@class='cart-empty']//a/parent::p/preceding-sibling::p";
}
