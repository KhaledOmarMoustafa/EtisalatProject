package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class Login {
    public void login(String language) {
        //https://www.youtube.com/watch?v=5gU_2y0818U this link is how to setup the healinum and make it fix any broken locator


        // Fetch localized button text
        String loginButtonText = CsvFetcher.getLocalizedText("login_button", language);

        // Define credentials
        String usernameText = "standard_user";
        String passwordText = "secret_sauce";

        // Step 1: Locate the username field and input the username
        WebElement usernameField = PreRequisites.driver.findElement(By.cssSelector("input[id='user-name']"));
        usernameField.sendKeys(usernameText);

        // Step 2: Locate the password field and input the password
        WebElement passwordField = PreRequisites.driver.findElement(By.xpath("(//input[@data-test='password'])"));
        passwordField.sendKeys(passwordText);

        // Step 3: Locate and click the login button
        WebElement clickLogin = PreRequisites.driver.findElement(By.xpath("//input[@value='" + loginButtonText.toUpperCase() + "']"));
        clickLogin.click();

        Assert.assertEquals(1,2);

    }
}