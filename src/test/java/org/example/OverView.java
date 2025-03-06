package org.example;

import org.openqa.selenium.By;
import org.testng.Assert;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class OverView extends AddingProducts {

    public void overView() {
        // Step 1: Retrieve and validate item names and prices from the overview page
        String overviewItemOneName = PreRequisites.driver.findElement(By.xpath("(//div[@class=\"inventory_item_name\"])[1]")).getText();
        String overviewItemTwoName = PreRequisites.driver.findElement(By.xpath("(//div[@class=\"inventory_item_name\"])[2]")).getText();
        String overviewItemOnePrice = PreRequisites.driver.findElement(By.xpath("(//div[@class=\"inventory_item_price\"])[1]")).getText();
        String itemOnePriceAdjusted = overviewItemOnePrice.replace("$", "");
        String overviewItemTwoPrice = PreRequisites.driver.findElement(By.xpath("(//div[@class=\"inventory_item_price\"])[2]")).getText();
        String itemTwoPriceAdjusted = overviewItemTwoPrice.replace("$", "");

        // Assert that names and prices match expected values
        Assert.assertTrue(overviewItemOneName.equalsIgnoreCase(firstItem));
        Assert.assertEquals(itemOnePrice, itemOnePriceAdjusted);
        Assert.assertTrue(overviewItemTwoName.equalsIgnoreCase(secondItem));
        Assert.assertEquals(itemTwoPrice, itemTwoPriceAdjusted);

        // Step 2: Verify the subtotal matches the sum of the two items
        String totalAmount = PreRequisites.driver.findElement(By.xpath("//div[@class=\"summary_subtotal_label\"]")).getText();
        String removeDollarSign = totalAmount.replace("Item total: $", "");
        String[] parts = removeDollarSign.split("\\.");
        String beforeDecimal = parts[0];
        String afterDecimal = parts[1].substring(0, Math.min(2, parts[1].length()));
        String totalAmountBeforeAdjustment = beforeDecimal + "." + afterDecimal;

        double formattedAmount = Double.parseDouble(totalAmountBeforeAdjustment);
        double expectedTotal = Double.parseDouble(itemOnePrice) + Double.parseDouble(itemTwoPrice);

        BigDecimal expectedTotalRounded = new BigDecimal(expectedTotal).setScale(2, RoundingMode.HALF_UP);
        BigDecimal formattedAmountRounded = new BigDecimal(formattedAmount).setScale(2, RoundingMode.HALF_UP);
        Assert.assertEquals(formattedAmountRounded, expectedTotalRounded);

        // Step 3: Validate that tax is 8% of the subtotal
        String taxBeforeAdjustment = PreRequisites.driver.findElement(By.xpath("//div[@class=\"summary_tax_label\"]")).getText();
        String removeExtraWord = taxBeforeAdjustment.replace("Tax: $", "");
        double taxAmount = Double.parseDouble(removeExtraWord);
        BigDecimal formattedTaxRounded = new BigDecimal(taxAmount).setScale(2, RoundingMode.HALF_UP);

        double extractTaxFromTotalItems = (expectedTotal * 8) / 100;
        BigDecimal formattedExtractedAmount = new BigDecimal(extractTaxFromTotalItems).setScale(2, RoundingMode.HALF_UP);
        Assert.assertEquals(formattedTaxRounded, formattedExtractedAmount);

        // Step 4: Verify the total matches the subtotal + tax
        String totalAmountWithTaxes = PreRequisites.driver.findElement(By.xpath("(//div[@class=\"summary_total_label\"])[1]")).getText();
        String removeExtraSigns = totalAmountWithTaxes.replace("Total: $", "");
        double totalAmountAsDouble = Double.parseDouble(removeExtraSigns);

        double itemPlusTaxes = formattedAmount + taxAmount;
        BigDecimal formattedItemPlusTaxes = new BigDecimal(itemPlusTaxes).setScale(2, RoundingMode.HALF_UP);
        BigDecimal formattedTotalAmountAsDouble = new BigDecimal(totalAmountAsDouble).setScale(2, RoundingMode.HALF_UP);

        Assert.assertEquals(formattedTotalAmountAsDouble, formattedItemPlusTaxes);
    }
}