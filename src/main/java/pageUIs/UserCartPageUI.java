package pageUIs;

public class UserCartPageUI {
    public static final String PRODUCT_ADDED_SUCCESSFUL_MESSAGE_TEXT = "xpath=//ul[@class='messages']//span";
    public static final String DISCOUNT_CODES_TEXTBOX = "css=input#coupon_code";
    public static final String DISCOUNT_CODES_ADDED_TEXT = "xpath=//table[@id='shopping-cart-totals-table']/tbody//td[contains(text(), 'Discount')]/following-sibling::td/span";
    public static final String GRAND_TOTAL = "xpath=//table[@id='shopping-cart-totals-table']/tfoot/tr//strong[contains(text(),'Grand Total')]/parent::td/following-sibling::td/strong/span";
    public static final String REMOVE_ITEM_ICON = "xpath=//table[@id='shopping-cart-table']//td[@class='a-center product-cart-remove last']/a[@class='btn-remove btn-remove2']";
}
