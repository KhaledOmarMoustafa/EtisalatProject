package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

public class AddingProducts {
    static String firstItem = "Sauce Labs Fleece Jacket";
    static String secondItem = "Sauce Labs Onesie";
    static String firstItemPrice;
    static String secondItemPrice;
    static String itemOnePrice;
    static String itemTwoPrice;


    public void addingProducts() {
        // Step 1: Locate the inventory container
        WebElement inventoryContainer = PreRequisites.driver.findElement(By.xpath("(//div[@id=\"inventory_container\"])[2]"));
        List<WebElement> inventoryContainerItems = inventoryContainer.findElements(By.xpath("(//div[@class=\"inventory_item\"])"));

        // Reset the item prices
        firstItemPrice = "";
        secondItemPrice = "";

        // Step 2: Iterate through the products
        for (int i = 1; i <= inventoryContainerItems.size(); i++) {
            // Retrieve the product name
            String itemName = PreRequisites.driver.findElement(By.xpath("(//div[@class=\"inventory_item_name\"])[" + i + "]")).getText();

            // Step 3: Match and process the first target item
            if (itemName.equalsIgnoreCase(firstItem)) {
                WebElement addToCartButton = PreRequisites.driver.findElement(By.xpath("(//button[@class=\"btn_primary btn_inventory\"])[" + i + "]"));
                firstItemPrice = PreRequisites.driver.findElement(By.xpath("(//div[@class=\"inventory_item_price\"])[" + i + "]")).getText();
                itemOnePrice = firstItemPrice.replace("$", "");
                addToCartButton.click(); // Add the item to the cart
            }
            // Step 4: Match and process the second target item
            else if (itemName.equalsIgnoreCase(secondItem)) {
                WebElement clickItem = PreRequisites.driver.findElement(By.xpath("(//div[@class=\"inventory_item_name\"])[" + i + "]"));
                clickItem.click(); // Navigate to the item's detail page
                secondItemPrice = PreRequisites.driver.findElement(By.xpath("//div[@class=\"inventory_details_price\"]")).getText();
                itemTwoPrice = secondItemPrice.replace("$", "");

                // Add the second item to the cart
                WebElement addToCartButton = PreRequisites.driver.findElement(By.cssSelector("button[class=\"btn_primary btn_inventory\"]"));
                addToCartButton.click();

                // Step 5: Validate the cart contains 2 items
                String cartItemQuantity = PreRequisites.driver.findElement(By.xpath("//span[@class=\"fa-layers-counter shopping_cart_badge\"]")).getText();
                Assert.assertEquals(cartItemQuantity, "2", "Cart does not contain the expected number of items");

                break; // Stop the loop after adding both items
            }
        }
    }
}