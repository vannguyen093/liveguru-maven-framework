package pageObjects;

import org.openqa.selenium.WebDriver;
import pageUIs.AdminHomePageUI;

public class PageGenerateManager {
    public static UserHomePageObject getUserHomePage(WebDriver driver){
        return new UserHomePageObject(driver);
    }
    public static UserLoginPageObject getUserLoginPage(WebDriver driver){
        return new UserLoginPageObject(driver);
    }
    public static UserRegisterPageObject getUserRegisterPage(WebDriver driver){
        return new UserRegisterPageObject(driver);
    }
    public static UserMyDashboardPageObject getUserMyDashboardPage(WebDriver driver){
        return new UserMyDashboardPageObject(driver);
    }
    public static UserAccountInfoPageObject getUserAccountInfoPage(WebDriver driver){
        return new UserAccountInfoPageObject(driver);
    }
    public static UserMobilePageObject getUserMobilePage(WebDriver driver){
        return new UserMobilePageObject(driver);
    }
    public static UserProductDetailPageObject getUserProductDetailPage(WebDriver driver){
        return new UserProductDetailPageObject(driver);
    }
    public static UserCartPageObject getUserCartPage(WebDriver driver){
        return new UserCartPageObject(driver);
    }
    public static AdminLoginPageObject getAdminLoginPage(WebDriver driver){
        return new AdminLoginPageObject(driver);
    }
    public static AdminHomePageObject getAdminHomePage(WebDriver driver){
        return new AdminHomePageObject(driver);
    }
}
