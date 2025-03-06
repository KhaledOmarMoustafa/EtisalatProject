package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class Confirmation {
    public void confirmation() {
        // Locate and click the "Finish" button on the confirmation page
        WebElement clickFinishButton = PreRequisites.driver.findElement(By.xpath("//a[@class=\"btn_action cart_button\"]"));
        clickFinishButton.click();

        // Retrieve the confirmation message displayed after completing the purchase
        String confirmationMessage = PreRequisites.driver.findElement(By.xpath("//h2[@class=\"complete-header\"]")).getText();

        // Verify that the confirmation message matches the expected text
        Assert.assertEquals(confirmationMessage, "THANK YOU FOR YOUR ORDER");
    }
}