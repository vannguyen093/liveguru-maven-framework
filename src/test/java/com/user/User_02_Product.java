package com.user;

import com.aventstack.extentreports.Status;
import commons.BaseTest;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import pageObjects.*;
import reportConfig.ExtentTestManager;
import ultilities.Environment;

import java.lang.reflect.Method;

public class User_02_Product extends BaseTest {
    @Parameters({"browser", "evnName", "ipAddress", "portNumber", "osName", "osVersion"})
    @BeforeClass
    public void beforeClass(@Optional("firefox") String browserName, @Optional("local") String evnName, @Optional("Windows") String osName, @Optional("10") String osVersion, @Optional("localhost") String ipAddress, @Optional("4444") String portNumber) {
        String environmentName = System.getProperty("evn");
        ConfigFactory.setProperty("env", environmentName);
        env = ConfigFactory.create(Environment.class);

        driver = getBrowserDriver(browserName, env.userUrl(), evnName, osName, osVersion, ipAddress, portNumber);

        userHomePage = PageGenerateManager.getUserHomePage(driver);
    }

    @Test
    public void TC_05_Compare_Price(Method method) {
        ExtentTestManager.startTest(method.getName(), "Compare price at list mobile and detail mobile");

        ExtentTestManager.getTest().log(Status.INFO, "Compare Price - Step 01: Click to 'Mobile' link");
        userHomePage.clickToHeaderMenuLinkByMenuText(driver, "Mobile");
        userMobilePage = PageGenerateManager.getUserMobilePage(driver);

        ExtentTestManager.getTest().log(Status.INFO, "Compare Price - Step 02: Get price of Sony Xperia at Mobile Page");
        sonyPriceAtList = userMobilePage.getProductPriceByProductTextAtMobilePage("Sony Xperia");

        ExtentTestManager.getTest().log(Status.INFO, "Compare Price - Step 03: Click to Sony Image");
        userProductDetailPage = userMobilePage.clickToImgByProductText("Sony Xperia");

        ExtentTestManager.getTest().log(Status.INFO, "Compare Price - Step 04: Get price of Sony Xperia at Product Detail Page");
        sonyPriceAtDetail = userProductDetailPage.getProductPriceAtProductDetailPage();

        ExtentTestManager.getTest().log(Status.INFO, "Compare Price - Step 05: Verify price of Sony Xperia at Mobile Page and Product Detail Page are equals");
        verifyEquals(sonyPriceAtList, sonyPriceAtDetail);
    }

    @Test
    public void TC_06_Verify_Discount_Coupon(Method method) {
        ExtentTestManager.startTest(method.getName(), "Verify discount coupon works correctly");

        ExtentTestManager.getTest().log(Status.INFO, "Verify Discount Coupon - Step 01: Click to 'Mobile' link");
        userProductDetailPage.clickToHeaderMenuLinkByMenuText(driver, "Mobile");
        userMobilePage = PageGenerateManager.getUserMobilePage(driver);

        ExtentTestManager.getTest().log(Status.INFO, "Verify Discount Coupon - Step 02: Click to 'Add To Cart' button");
        userCartPage = userMobilePage.clickToAddToCartButtonByProductName("Sony Xperia");

        ExtentTestManager.getTest().log(Status.INFO, "Verify Discount Coupon - Step 03: Verify the added successful message is displayed");
        verifyTrue(userCartPage.isSuccessfullyAddedMessageDisplayed());

        ExtentTestManager.getTest().log(Status.INFO, "Verify Discount Coupon - Step 04: Input to 'Discount Codes' text box with value 'GURU50'");
        userCartPage.inputToDiscountCodeTextBox("GURU50");

        ExtentTestManager.getTest().log(Status.INFO, "Verify Discount Coupon - Step 05: Verify the discount added successful text is displayed");
        verifyEquals(userCartPage.getAddedDiscountText(),"-$5.00");

        ExtentTestManager.getTest().log(Status.INFO, "Verify Discount Coupon - Step 06: Verify the 'Grand Total' is change correct when added the coupon code");
        verifyEquals(userCartPage.getGrandTotalText(), "$95.00");

        ExtentTestManager.getTest().log(Status.INFO, "Verify Discount Coupon - Step 06: Click to 'Remove item' icon");
        userCartPage.clickToRemoveItemIcon();
    }

    @Test
    public void TC_07_Verify_Quantity_Item_In_Cart(Method method) {
        ExtentTestManager.startTest(method.getName(), "Verify that cannot add more 500 items of product");

        ExtentTestManager.getTest().log(Status.INFO, "Verify Quantity Item - Step 01: Click to 'Mobile' link");
        userCartPage.clickToHeaderMenuLinkByMenuText(driver, "Mobile");
        userMobilePage = PageGenerateManager.getUserMobilePage(driver);

        ExtentTestManager.getTest().log(Status.INFO, "Verify Discount Coupon - Step 02: Click to 'Add To Cart' button");
        userCartPage = userMobilePage.clickToAddToCartButtonByProductName("Sony Xperia");

        ExtentTestManager.getTest().log(Status.INFO, "Verify Discount Coupon - Step 03: Input to 'QTY' text box with value '501'");
        ExtentTestManager.getTest().log(Status.INFO, "Verify Discount Coupon - Step 04: Click to 'Update' button");
        ExtentTestManager.getTest().log(Status.INFO, "Verify Discount Coupon - Step 05: Verify the error message is displayed");
        ExtentTestManager.getTest().log(Status.INFO, "Verify Discount Coupon - Step 05: Click to 'Empty Cart 'link");
        ExtentTestManager.getTest().log(Status.INFO, "Verify Discount Coupon - Step 05: Verify cart is empty");
    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        closeBrowserAndDriver();
    }

    private WebDriver driver;
    Environment env;
    private String sonyPriceAtList, sonyPriceAtDetail;
    UserHomePageObject userHomePage;
    UserMobilePageObject userMobilePage;
    UserProductDetailPageObject userProductDetailPage;
    UserCartPageObject userCartPage;
}
