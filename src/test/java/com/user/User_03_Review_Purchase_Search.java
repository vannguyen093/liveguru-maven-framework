package com.user;

import com.aventstack.extentreports.Status;
import commons.BaseTest;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import pageObjects.*;
import reportConfig.ExtentTestManager;
import ultilities.DataHelper;
import ultilities.Environment;

import java.lang.reflect.Method;

public class User_03_Review_Purchase_Search extends BaseTest {
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
        address = dataHelper.getAddress();
        country = "United States";
        state = "California";
        city = dataHelper.getCity();
        zip = dataHelper.getZip();
        telephone = dataHelper.getTelephone();

        yourThoughtText = "This is my review " + generateFakeNumber();
        yourReviewText = "Review " + generateFakeNumber();

        userHomePage.clickToMyAccountLink(driver);
        userLoginPage = PageGenerateManager.getUserLoginPage(driver);

        userRegisterPage = userLoginPage.clickToCreateAccountButton();
        userRegisterPage.inputToTextboxById("firstname", firstName);
        userRegisterPage.inputToTextboxById("lastname", lastName);
        userRegisterPage.inputToTextboxById("email_address", emailAddress);
        userRegisterPage.inputToTextboxById("password", password);
        userRegisterPage.inputToTextboxById("confirmation", password);
        userMyDashboardPage = userRegisterPage.clickToRegisterButton();
    }

    @Test
    public void TC_10_Review_Product(Method method) {
        ExtentTestManager.startTest(method.getName(), "Review a product");

        ExtentTestManager.getTest().log(Status.INFO, "Review Product - Step 01: Click to 'TV' link");
        userMyDashboardPage.clickToHeaderMenuLinkByMenuText(driver, "TV");
        userTVPage = PageGenerateManager.getUserTVPage(driver);

        ExtentTestManager.getTest().log(Status.INFO, "Review Product - Step 02: Click to 'Samsung LCD' title");
        userProductDetailPage = userTVPage.clickToProductTitleByProductName("Samsung LCD");

        ExtentTestManager.getTest().log(Status.INFO, "Review Product - Step 03: Click to 'Add Your Review' link");
        userReviewPage = userProductDetailPage.clickToAddYourReviewLink();

        ExtentTestManager.getTest().log(Status.INFO, "Review Product - Step 04: Leave required fields blank and click to 'Submit Review' button");
        userReviewPage.clickToSubmitReviewButton();

        ExtentTestManager.getTest().log(Status.INFO, "Review Product - Step 05: Verify error messages are displayed");
        verifyTrue(userReviewPage.isErrorMessageAtRatingRadioDisplayed());
        verifyTrue(userReviewPage.isErrorMessageByLabelTextDisplayed("Let us know your thoughts"));
        verifyTrue(userReviewPage.isErrorMessageByLabelTextDisplayed("Summary of Your Review"));

        ExtentTestManager.getTest().log(Status.INFO, "Review Product - Step 06: Verify error messages are displayed");
        userReviewPage.selectRatingStarRadioByNumber("3");

        ExtentTestManager.getTest().log(Status.INFO, "Review Product - Step 07: Input to 'Thought' text box with value '" + yourThoughtText + "'");
        userReviewPage.inputToThoughtTextArea(yourThoughtText);

        ExtentTestManager.getTest().log(Status.INFO, "Review Product - Step 08: Input to 'Your review' text box with value '" + yourReviewText + "'");
        userReviewPage.inputToReviewTextbox(yourReviewText);

        ExtentTestManager.getTest().log(Status.INFO, "Review Product - Step 09: Input to 'Your nickname' text box with value '" + firstName + "'");
        userReviewPage.inputToNickNameTextbox(firstName);

        ExtentTestManager.getTest().log(Status.INFO, "Review Product - Step 10: Click to 'Submit Review' button");
        userReviewPage.clickToSubmitReviewButton();

        ExtentTestManager.getTest().log(Status.INFO, "Review Product - Step 11: Verify the successfully added review message is displayed");
        verifyTrue(userReviewPage.isSuccessfullReviewMessageDisplayed());
    }

    @Test
    public void TC_11_Purchase_Product(Method method) {
        ExtentTestManager.startTest(method.getName(), "Purchase Product");

        ExtentTestManager.getTest().log(Status.INFO, "Purchase Product - Step 01: Click to 'TV' link");
        userReviewPage.clickToHeaderMenuLinkByMenuText(driver, "TV");
        userTVPage = PageGenerateManager.getUserTVPage(driver);

        ExtentTestManager.getTest().log(Status.INFO, "Purchase Product - Step 02: Click to 'Add to cart' button at product named 'Samsung LCD'");
        userCartPage = userTVPage.clickToAddToCartButtonByProductName("Samsung LCD");

        ExtentTestManager.getTest().log(Status.INFO, "Purchase Product - Step 03: Select value at 'Country' dropdown with value '" + country + "'");
        userCartPage.selectItemAtEstimatetDropdownByDropdownID("country", country);

        ExtentTestManager.getTest().log(Status.INFO, "Purchase Product - Step 03: Select value at 'State/Province' dropdown with value '" + state + "'");
        userCartPage.selectItemAtEstimatetDropdownByDropdownID("region_id", state);

        ExtentTestManager.getTest().log(Status.INFO, "Purchase Product - Step 03: Input to 'Zip' text box with value '" + zip + "'");
        userCartPage.inputToZipTextBox(zip);

        ExtentTestManager.getTest().log(Status.INFO, "Purchase Product - Step 03: Click to 'Estimate' link");
        userCartPage.clickToEstimateLink();

        ExtentTestManager.getTest().log(Status.INFO, "Purchase Product - Step 03: Verify 'Shipping cost' is displayed");
        verifyEquals(userCartPage.getShippingCostText(),"$5.00");

        ExtentTestManager.getTest().log(Status.INFO, "Purchase Product - Step 03: Click to 'Update Total' button");
        userCartPage.clickToUpdateTotalButton();

        ExtentTestManager.getTest().log(Status.INFO, "Purchase Product - Step 03: Verify shipping cost is added to total with value '$705'");
        verifyEquals(userCartPage.getGrandTotalText(),"$705.00");

        ExtentTestManager.getTest().log(Status.INFO, "Purchase Product - Step 03: Click to 'Proceed To Checkout' button");
        userCheckOutPage = userCartPage.clickToProceedToCheckoutButton();

        ExtentTestManager.getTest().log(Status.INFO, "Purchase Product - Step 03: Input to 'Address' text box with value '" + address + "'");
        userCheckOutPage.inputToTextboxByTitle("Street Address", address);

        ExtentTestManager.getTest().log(Status.INFO, "Purchase Product - Step 03: Input to 'City' text box with value '" + city + "'");
        userCheckOutPage.inputToTextboxByTitle("City", city);

        ExtentTestManager.getTest().log(Status.INFO, "Purchase Product - Step 03: Select value at 'State/Province' dropdown with value '" + state + "'");
        userCartPage.selectItemAtCheckOutDropdown(state);

        ExtentTestManager.getTest().log(Status.INFO, "Purchase Product - Step 03: Input to 'Zip' text box with value '" + zip + "'");
        userCheckOutPage.inputToTextboxByTitle("Zip", zip);

        ExtentTestManager.getTest().log(Status.INFO, "Purchase Product - Step 03: Input to 'Telephone' text box with value '" + telephone + "'");
        userCheckOutPage.inputToTextboxByTitle("Telephone", telephone);

        ExtentTestManager.getTest().log(Status.INFO, "Purchase Product - Step 03: Click to 'Continue' button at 'Billing Information' tab");
        ExtentTestManager.getTest().log(Status.INFO, "Purchase Product - Step 03: Click to 'Continue' button at 'Shiping Method' tab");
        ExtentTestManager.getTest().log(Status.INFO, "Purchase Product - Step 03: Click to 'Check/ Money order' radio at 'Payment Information' tab");
        ExtentTestManager.getTest().log(Status.INFO, "Purchase Product - Step 03: Click to 'Place Order' button");
        ExtentTestManager.getTest().log(Status.INFO, "Purchase Product - Step 03: Verify the successfully purchase product message is displayed");

    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        closeBrowserAndDriver();
    }

    private WebDriver driver;
    Environment env;
    DataHelper dataHelper;
    private String firstName, lastName, emailAddress, password;
    private String address, country, state, city, zip, telephone;
    private String yourThoughtText, yourReviewText;
    UserHomePageObject userHomePage;
    UserMobilePageObject userMobilePage;
    UserProductDetailPageObject userProductDetailPage;
    UserCartPageObject userCartPage;
    UserLoginPageObject userLoginPage;
    UserRegisterPageObject userRegisterPage;
    UserMyDashboardPageObject userMyDashboardPage;
    UserTVPageObject userTVPage;
    UserReviewPageObject userReviewPage;
    UserWishlistPageObject userWishlistPage;
}
