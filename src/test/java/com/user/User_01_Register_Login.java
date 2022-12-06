package com.user;

import com.aventstack.extentreports.Status;
import commons.BaseTest;
import commons.GlobalConstants;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import pageObjects.*;
import reportConfig.ExtentTestManager;
import ultilities.DataHelper;
import ultilities.Environment;

import java.lang.reflect.Method;

public class User_01_Register_Login extends BaseTest {
    @Parameters({"browser", "evnName", "ipAddress", "portNumber", "osName", "osVersion"})
    @BeforeClass
    public void beforeClass(@Optional("firefox") String browserName, @Optional("local") String evnName, @Optional("Windows") String osName, @Optional("10") String osVersion, @Optional("localhost") String ipAddress, @Optional("4444") String portNumber) {
        String environmentName = System.getProperty("evn");
        ConfigFactory.setProperty("env", environmentName);
        env = ConfigFactory.create(Environment.class);

        driver = getBrowserDriver(browserName, env.userUrl(), evnName, osName, osVersion, ipAddress, portNumber);
        dataHelper = DataHelper.getDataHelper();

        userHomePage = PageGenerateManager.getUserHomePage(driver);

        adminUserName = "user01";
        adminPassword = "guru99com";

        firstName = dataHelper.getFirstName();
        lastName = dataHelper.getLastName();
        fullName = firstName + " " + lastName;
        emailAddress = dataHelper.getEmail();
        password = dataHelper.getPassword();
    }

    @Test
    public void TC_01_Create_Account(Method method) {
        ExtentTestManager.startTest(method.getName(), "Create Account");

        ExtentTestManager.getTest().log(Status.INFO, "Create Account - Step 01: Click to 'My Account' link");
        userHomePage.clickToMyAccountLink(driver);
        userLoginPage = PageGenerateManager.getUserLoginPage(driver);

        ExtentTestManager.getTest().log(Status.INFO, "Create Account - Step 02: Click to 'Create An Account' button");
        userRegisterPage = userLoginPage.clickToCreateAccountButton();

        ExtentTestManager.getTest().log(Status.INFO, "Create Account - Step 03: Input to 'First Name' text box with value is '" + firstName + "'");
        userRegisterPage.inputToTextboxById("firstname", firstName);

        ExtentTestManager.getTest().log(Status.INFO, "Create Account - Step 04: Input to 'Last Name' text box with value is '" + lastName + "'");
        userRegisterPage.inputToTextboxById("lastname", lastName);

        ExtentTestManager.getTest().log(Status.INFO, "Create Account - Step 05: Input to 'Email Address' text box with value is '" + emailAddress + "'");
        userRegisterPage.inputToTextboxById("email_address", emailAddress);

        ExtentTestManager.getTest().log(Status.INFO, "Create Account - Step 06: Input to 'Password' text box with value is '" + password + "'");
        userRegisterPage.inputToTextboxById("password", password);

        ExtentTestManager.getTest().log(Status.INFO, "Create Account - Step 07: Input to 'Confirm Password' text box with value is '" + password + "'");
        userRegisterPage.inputToTextboxById("confirmation", password);

        ExtentTestManager.getTest().log(Status.INFO, "Create Account - Step 08: Click to 'Register' button");
        userMyDashboardPage = userRegisterPage.clickToRegisterButton();

        ExtentTestManager.getTest().log(Status.INFO, "Create Account - Step 09: Verify text displayed after registered successfully");
        verifyTrue(userMyDashboardPage.isRegisterdSuccessMessageDisplayed());
    }

    @Test
    public void TC_02_Verify_User_Info(Method method) {
        ExtentTestManager.startTest(method.getName(), "Verify user info after registered");

        ExtentTestManager.getTest().log(Status.INFO, "Verify User Info - Step 01: Click to 'Account Information' link at menu link");
        userMyDashboardPage.clickToSidebarMenuLinkByMenuText(driver,"Account Information");
        userAccountInfoPage = PageGenerateManager.getUserAccountInfoPage(driver);

        ExtentTestManager.getTest().log(Status.INFO, "Verify User Info - Step 02: Verify data at 'First Name' text box is correctly with value '" + firstName + "'");
        verifyEquals(userAccountInfoPage.getTextboxValueByID("firstname"), firstName);

        ExtentTestManager.getTest().log(Status.INFO, "Verify User Info - Step 03: Verify data at 'Last Name' text box is correctly with value '" + lastName + "'");
        verifyEquals(userAccountInfoPage.getTextboxValueByID("lastname"), lastName);

        ExtentTestManager.getTest().log(Status.INFO, "Verify User Info - Step 04: Verify data at 'Email' text box is correctly with value '" + emailAddress + "'");
        verifyEquals(userAccountInfoPage.getTextboxValueByID("email"), emailAddress);

        ExtentTestManager.getTest().log(Status.INFO, "Verify User Info - Step 05: Click to 'Log out' link");
        userAccountInfoPage.clickToLogoutLink(driver);
        userHomePage = PageGenerateManager.getUserHomePage(driver);
    }

    @Test
    public void TC_03_Verify_Account_At_Admin_Site(Method method) {
        ExtentTestManager.startTest(method.getName(), "Verify registered account at admin site");

        ExtentTestManager.getTest().log(Status.INFO, "Verify At Admin Site - Step 01: Open admin site");
        userHomePage.openPageUrl(driver, GlobalConstants.ADMIN_URL);
        adminLoginPage = PageGenerateManager.getAdminLoginPage(driver);

        ExtentTestManager.getTest().log(Status.INFO, "Verify At Admin Site - Step 02: Input to 'User Name' text box with value '" + adminUserName + "'");
        adminLoginPage.inputToTextboxById("username", adminUserName);

        ExtentTestManager.getTest().log(Status.INFO, "Verify At Admin Site - Step 03: Input to 'Password' text box with value '" + adminPassword + "'");
        adminLoginPage.inputToTextboxById("login", adminPassword);

        ExtentTestManager.getTest().log(Status.INFO, "Verify At Admin Site - Step 04: Click to 'Login' button");
        adminHomePage = adminLoginPage.clickToLoginButton();
        adminHomePage.closeAdminPopup();

        ExtentTestManager.getTest().log(Status.INFO, "Verify At Admin Site - Step 05: Input full name into Name filter with value '" + fullName + "'");
        adminHomePage.inputToFilterByFilterName("name", fullName);

        ExtentTestManager.getTest().log(Status.INFO, "Verify At Admin Site - Step 06: Verify the account name is displayed at admin site");
        verifyTrue(adminHomePage.isRegisteredAccountDisplayed(fullName, emailAddress));
    }

    @Test
    public void TC_04_Login_To_System(Method method) {
        ExtentTestManager.startTest(method.getName(), "Login to system after log out");

        ExtentTestManager.getTest().log(Status.INFO, "Login - Step 01: Open User site");
        adminHomePage.openPageUrl(driver, GlobalConstants.USER_URL);
        userHomePage = PageGenerateManager.getUserHomePage(driver);

        ExtentTestManager.getTest().log(Status.INFO, "Login - Step 02: Click to 'My Account' link");
        userHomePage.clickToMyAccountLink(driver);
        userLoginPage = PageGenerateManager.getUserLoginPage(driver);

        ExtentTestManager.getTest().log(Status.INFO, "Login - Step 03: Input to 'Email Address' text box with registered email: '" + emailAddress + "'");
        userLoginPage.inputToTextboxById("email", emailAddress);

        ExtentTestManager.getTest().log(Status.INFO, "Login - Step 04: Input to 'Password' text box with registered email: '" + password + "'");
        userLoginPage.inputToTextboxById("pass", password);

        ExtentTestManager.getTest().log(Status.INFO, "Login - Step 05: Click to 'Login' button");
        userMyDashboardPage = userLoginPage.clickToLoginButton();

        ExtentTestManager.getTest().log(Status.INFO, "Login - Step 06: Verify My Dashboard title is displayed");
        verifyTrue(userMyDashboardPage.isMyDashboardTitleDisplayed());
    }


    @AfterClass(alwaysRun = true)
    public void afterClass() {
        closeBrowserAndDriver();
    }

    private WebDriver driver;
    private String firstName, lastName, fullName, emailAddress, password;
    private String adminUserName, adminPassword;
    DataHelper dataHelper;
    Environment env;
    UserHomePageObject userHomePage;
    UserLoginPageObject userLoginPage;
    UserRegisterPageObject userRegisterPage;
    UserMyDashboardPageObject userMyDashboardPage;
    UserAccountInfoPageObject userAccountInfoPage;
    AdminLoginPageObject adminLoginPage;
    AdminHomePageObject adminHomePage;
}
