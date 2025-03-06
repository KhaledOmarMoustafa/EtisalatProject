package org.example;

import com.applitools.eyes.selenium.Eyes;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.example.PreRequisites.driver;

/**
 * Unit test for simple App.
 */
public class AppTest extends JsonFetcher {

    private static final String SCREENSHOTS_DIR = "D:\\New folder\\Robusta\\Robusta\\screenshots\\Robusta\\";

    JsonFetcher jsonFetcher = new JsonFetcher();
    PreRequisites preRequisites = new PreRequisites();
    Login loginTest  = new Login();
    AddingProducts addingProducts = new AddingProducts();
    CheckCart checkCart = new CheckCart();
    CheckOut checkOut = new CheckOut();
    OverView overView = new OverView();
    Confirmation confirmation = new Confirmation();

    @BeforeClass
    public void preRequisites() throws Exception {
        jsonFetcher.jsonFetcher();
        preRequisites.openBrowser();

        String loginUrl = PreRequisites.webURL; // Replace with actual URL
        ZAPSecurityTest.scan(loginUrl);
        // Upload baseline images once at the beginning
       // uploadBaselines();
    }

   /* private void uploadBaselines() {
        try {
            System.out.println("Uploading baseline images...");
            // Log the path of the image being uploaded
           String path ="D:\\New folder\\Robusta\\Robusta\\screenshots\\Robusta\\Login_Page.png";
           // String loginPageImagePath = SCREENSHOTS_DIR + "Login_Page.png";
            System.out.println("Uploading image from path: " + path);

            // Upload baseline images for each test page
         //   preRequisites.uploadBaselineImage("SauceDemo", "LoginPage", loginPageImagePath);
            preRequisites.uploadBaselineImage("SauceDemo", "Login_Page",path);
            // Uncomment and verify other images as needed
            // preRequisites.uploadBaselineImage("SauceDemo", "ProductsPage", SCREENSHOTS_DIR + "products_baseline.png");
            // preRequisites.uploadBaselineImage("SauceDemo", "CartPage", SCREENSHOTS_DIR + "cart_baseline.png");
            // preRequisites.uploadBaselineImage("SauceDemo", "CheckoutPage", SCREENSHOTS_DIR + "checkout_baseline.png");
            // preRequisites.uploadBaselineImage("SauceDemo", "OverviewPage", SCREENSHOTS_DIR + "overview_baseline.png");
            // preRequisites.uploadBaselineImage("SauceDemo", "ConfirmationPage", SCREENSHOTS_DIR + "confirmation_baseline.png");

            System.out.println("Baseline images uploaded successfully");
        } catch (Exception e) {
            System.err.println("Failed to upload baseline images: " + e.getMessage());
            e.printStackTrace();
        }
    }*/

    @Test(priority = 1)
    public void login() throws InterruptedException {
        loginTest.login("en"); // Runs in French
        //loginTest.login("en"); // Runs in English

    }

    @Test(priority = 2)
    public void addingProducts() {
        preRequisites.applitoolsVisualTesting("SauceDemo", "AddingProductTest");

        addingProducts.addingProducts();
       // preRequisites.applitoolsVisualTesting("SauceDemo", "ProductsTest");
    }

    @Test(priority = 3)
    public void checkCart() {
        checkCart.checkCart();
      //  preRequisites.applitoolsVisualTesting("SauceDemo", "CartTest");
    }

    @Test(priority = 4)
    public void checkOut() {
        checkOut.checkOut();
      //  preRequisites.applitoolsVisualTesting("SauceDemo", "CheckoutTest");
    }

    @Test(priority = 5)
    public void checkOverView() {
        overView.overView();
     //   preRequisites.applitoolsVisualTesting("SauceDemo", "OverviewTest");
    }

    @Test(priority = 6)
    public void confirmation() {
        confirmation.confirmation();
       // preRequisites.applitoolsVisualTesting("SauceDemo", "ConfirmationTest");
    }

    @Test(priority = 7)
    public void closeBrowser() {
        preRequisites.closeBrowser();
    }
}