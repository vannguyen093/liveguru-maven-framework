package com.admin;

import com.aventstack.extentreports.Status;
import commons.BaseTest;
import commons.GlobalConstants;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import pageObjects.*;
import reportConfig.ExtentTestManager;
import ultilities.Environment;

import java.lang.reflect.Method;

public class Admin_02_Invoice_Product_Review extends BaseTest{
    private WebDriver driver;
    private String adminUserName, adminPassword;
    Environment env;
    UserHomePageObject userHomePage;
    UserLoginPageObject userLoginPage;
    UserRegisterPageObject userRegisterPage;
    UserMyDashboardPageObject userMyDashboardPage;
    UserAccountInfoPageObject userAccountInfoPage;
    AdminLoginPageObject adminLoginPage;
    AdminHomePageObject adminHomePage;
    AdminOrderPageObject adminOrderPage;
    AdminDownloadedInvoiceTabPageObject adminDownloadedInvoiceTabPage;

    @Parameters({"browser", "evnName", "ipAddress", "portNumber", "osName", "osVersion"})
    @BeforeClass
    public void beforeClass(@Optional("firefox") String browserName, @Optional("local") String evnName, @Optional("Windows") String osName, @Optional("10") String osVersion, @Optional("localhost") String ipAddress, @Optional("4444") String portNumber) {
        String environmentName = System.getProperty("evn");
        ConfigFactory.setProperty("env", environmentName);
        env = ConfigFactory.create(Environment.class);

        driver = getBrowserDriver(browserName, env.adminUrl(), evnName, osName, osVersion, ipAddress, portNumber);

        adminLoginPage = PageGenerateManager.getAdminLoginPage(driver);

        adminUserName = "user01";
        adminPassword = "guru99com";
    }

    @Test
    public void TC_01_Invoice(Method method) {
        ExtentTestManager.startTest(method.getName(), "Verify Invoice can be print");

        ExtentTestManager.getTest().log(Status.INFO, "Invoice - Step 01: Input to 'User Name' text box with value '" + adminUserName + "'");
        adminLoginPage.inputToTextboxById("username", adminUserName);

        ExtentTestManager.getTest().log(Status.INFO, "Invoice - Step 02: Input to 'Password' text box with value '" + adminPassword + "'");
        adminLoginPage.inputToTextboxById("login", adminPassword);

        ExtentTestManager.getTest().log(Status.INFO, "Invoice - Step 03: Click to 'Login' button");
        adminHomePage = adminLoginPage.clickToLoginButton();
        adminHomePage.closeAdminPopup();

        ExtentTestManager.getTest().log(Status.INFO, "Invoice - Step 04: Move to 'Orders' from 'Sales' menu");
        adminHomePage.clickToSubMenuBySubMenuText(driver,"Sales","Orders");
        adminOrderPage = PageGenerateManager.getAdminOrderPage(driver);

        ExtentTestManager.getTest().log(Status.INFO, "Invoice - Step 05: Select 'Canceled' in 'Status' dropdown");
        adminOrderPage.selectItemAtStatusDropdown("Canceled");

        ExtentTestManager.getTest().log(Status.INFO, "Invoice - Step 06: Click to 'Search' button");
        adminOrderPage.clickToButtonAtAdminSiteByButtonTitle(driver,"Search");

        ExtentTestManager.getTest().log(Status.INFO, "Invoice - Step 07: Select the first order with the checkbox");
        adminOrderPage.selectFirstOrderByCheckbox();

        ExtentTestManager.getTest().log(Status.INFO, "Invoice - Step 08: Select 'Print invoices' at 'Actions' dropdown");
        adminOrderPage.selectItemAtOrderActionDropdownByName(driver,"Print Invoices");

        ExtentTestManager.getTest().log(Status.INFO, "Invoice - Step 09: Click to 'Submit' button");
        adminOrderPage.clickToButtonAtAdminSiteByButtonTitle(driver,"Submit");

        ExtentTestManager.getTest().log(Status.INFO, "Invoice - Step 10: Verify the error message is displayed");
        verifyTrue(adminOrderPage.isMessageAtAdminSiteDisplayed(driver));

        ExtentTestManager.getTest().log(Status.INFO, "Invoice - Step 11: Select 'Complete' in 'Status' dropdown");
        adminOrderPage.selectItemAtStatusDropdown("Complete");

        ExtentTestManager.getTest().log(Status.INFO, "Invoice - Step 12: Click to 'Search' button");
        adminOrderPage.clickToButtonAtAdminSiteByButtonTitle(driver,"Search");

        ExtentTestManager.getTest().log(Status.INFO, "Invoice - Step 13: Select the first order with the checkbox");
        adminOrderPage.selectFirstOrderByCheckbox();

        ExtentTestManager.getTest().log(Status.INFO, "Invoice - Step 14: Select 'Print invoices' at 'Actions' dropdown");
        adminOrderPage.selectItemAtOrderActionDropdownByName(driver,"Print Invoices");
        adminDownloadedInvoiceTabPage = PageGenerateManager.getDownloadedInvoiceTabPage(driver);

        ExtentTestManager.getTest().log(Status.INFO, "Invoice - Step 15: Click to 'Submit' button");
        adminOrderPage.clickToButtonAtAdminSiteByButtonTitle(driver,"Submit");

        ExtentTestManager.getTest().log(Status.INFO, "Invoice - Step 16: Verify the new tab downloaded invoice is displayed");
        verifyTrue(adminDownloadedInvoiceTabPage.isDownloadedInvoiceTabDisplayed());

        ExtentTestManager.getTest().log(Status.INFO, "Invoice - Step 17: Close the downloaded invoice tab");
        adminOrderPage = adminDownloadedInvoiceTabPage.closeDownloadedInvoiceTab();
    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        closeBrowserAndDriver();
    }
}
