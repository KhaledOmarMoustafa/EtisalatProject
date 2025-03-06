package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class CheckOut {


    public void checkOut() {
        // Step 1: Locate and click the "Checkout" button
        WebElement clickCheckOut = PreRequisites.driver.findElement(By.xpath("//a[@class=\"btn_action checkout_button\"]"));
        clickCheckOut.click();

        // Step 2: Enter First Name
        WebElement enterFirstName = PreRequisites.driver.findElement(By.xpath("//input[@data-test=\"firstName\"]"));
        enterFirstName.sendKeys(JsonFetcher.firstName); // Use data fetched from JsonFetcher

        // Step 3: Enter Last Name
        WebElement enterLastName = PreRequisites.driver.findElement(By.xpath("//input[@data-test=\"lastName\"]"));
        enterLastName.sendKeys(JsonFetcher.lastName);

        // Step 4: Enter Postal Code
        WebElement enterPostalCode = PreRequisites.driver.findElement(By.xpath("//input[@data-test=\"postalCode\"]"));
        enterPostalCode.sendKeys(JsonFetcher.postalCode);

        // Step 5: Click the "Continue" button to navigate to the overview page
        WebElement clickContinue = PreRequisites.driver.findElement(By.xpath("//input[@class=\"btn_primary cart_button\"]"));
        clickContinue.click();
    }
}