package com.user;

import com.aventstack.extentreports.Status;
import commons.BaseTest;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import pageObjects.PageGenerateManager;
import pageObjects.UserHomePageObject;
import pageObjects.UserLoginPageObject;
import pageObjects.UserRegisterPageObject;
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

        firstName = dataHelper.getFirstName();
        lastName = dataHelper.getLastName();
        emailAddress = dataHelper.getEmail();
        password = dataHelper.getPassword();
    }

    @Test
    public void User_01_Create_Account(Method method) {
        ExtentTestManager.startTest(method.getName(), "Create Account");

        ExtentTestManager.getTest().log(Status.INFO, "Create Account - Step 01: Click to 'My Account' link");
        userLoginPage = userHomePage.clickToMyAccountLink(driver);

        ExtentTestManager.getTest().log(Status.INFO, "Create Account - Step 02: Click to 'Create An Account' button");
        userRegisterPage = userLoginPage.clickToButtonByTitle("Create An Account");

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
        userRegisterPage.clickToButtonByTitle("Register");
    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        closeBrowserAndDriver();
    }

    private WebDriver driver;
    private String firstName, lastName, emailAddress, password;
    private String userID, userPassword;
    DataHelper dataHelper;
    Environment env;
    UserHomePageObject userHomePage;
    UserLoginPageObject userLoginPage;
    UserRegisterPageObject userRegisterPage;
}
