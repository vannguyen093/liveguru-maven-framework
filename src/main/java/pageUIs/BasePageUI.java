package pageUIs;

public class BasePageUI {
    public static final String FOOTER_MENU_LINK_BY_MENU_TEXT = "xpath=//div[@class='footer']//a[text()='%s']";
    public static final String SIDEBAR_MENU_LINK_BY_MENU_TEXT = "xpath=//a[text()='%s']";
    public static final String HEADER_ACOUNT_LINK = "xpath=//div[@class='account-cart-wrapper']//span[text()='Account']";
    public static final String ACCOUNT_MENU_LINK_BY_MENU_TEXT = "xpath=//div[@class='links']//a[text()='%s']";
    public static final String HEADER_MENU_LINK_BY_MENU_TEXT = "xpath=//div[@id='header-nav']//a[text()='%s']";
    public static final String BUTTON_AT_ADMIN_SITE_BY_BUTTON_TITLE = "xpath=//button[@title='%s']";
    public static final String SUCCESS_DELETE_MESSAGE_AT_ADMIN_SITE_TEXT = "xpath=//div[@id='messages']//span";
    public static final String ADMIN_MENU_LINK_BY_MENU_TEXT = "xpath=//ul[@id='nav']//span[text()='%s']";
    public static final String SUB_ADMIN_MENU_LINK_BY_MENU_TEXT = "xpath=//li[contains(@class,'level1')]//span[text()='Orders']";
    public static final String ACTIONS_AT_CUSTOMER_DROPDOWN = "xpath=select#customerGrid_massaction-select";
    public static final String LOADING_MASK = "css=div#loading-mask";
}
