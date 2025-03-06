package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class CheckCart extends AddingProducts {


    public void checkCart() {
        // Step 1: Click the cart icon to navigate to the cart page
        WebElement clickCart = PreRequisites.driver.findElement(By.xpath("//*[@class=\"shopping_cart_link fa-layers fa-fw\"]"));
        clickCart.click();

        // Step 2: Retrieve the names and prices of the items in the cart
        String firstItemInCart = PreRequisites.driver.findElement(By.xpath("(//div[@class=\"inventory_item_name\"])[1]")).getText();
        String firstItemInCartPrice = PreRequisites.driver.findElement(By.xpath("(//div[@class=\"inventory_item_price\"])[1]")).getText();
        String secondItemInCart = PreRequisites.driver.findElement(By.xpath("(//div[@class=\"inventory_item_name\"])[2]")).getText();
        String secondItemInCartPrice = PreRequisites.driver.findElement(By.xpath("(//div[@class=\"inventory_item_price\"])[2]")).getText();

        // Step 3: Validate the names and prices of the items in the cart
        Assert.assertTrue(firstItemInCart.equalsIgnoreCase(firstItem), "First item name mismatch in cart");
        Assert.assertEquals(itemOnePrice, firstItemInCartPrice, "First item price mismatch in cart");
        Assert.assertTrue(secondItemInCart.equalsIgnoreCase(secondItem), "Second item name mismatch in cart");
        Assert.assertEquals(itemTwoPrice, secondItemInCartPrice, "Second item price mismatch in cart");
    }
}