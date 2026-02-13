/*
 * Name: Amaya Keys
 */

package org.howard.edu.lsp.assignment3;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ProductTransformer {
	
	public static String transform(Product product) {

        // 1. Uppercase name
        String newName = product.getName().toUpperCase();

        // 2. Discount electronics
        BigDecimal newPrice = product.getPrice();
        if (product.getCategory().equals("Electronics")) {
            newPrice = newPrice.multiply(new BigDecimal("0.90"));
        }
        newPrice = newPrice.setScale(2, RoundingMode.HALF_UP);

        // 3. Premium electronics
        String newCategory = product.getCategory();
        if (product.getCategory().equals("Electronics")
                && newPrice.compareTo(new BigDecimal("500.00")) > 0) {
            newCategory = "Premium Electronics";
        }

        // 4. Price range
        String priceRange;
        if (newPrice.compareTo(new BigDecimal("10.00")) <= 0) {
            priceRange = "Low";
        } else if (newPrice.compareTo(new BigDecimal("100.00")) <= 0) {
            priceRange = "Medium";
        } else if (newPrice.compareTo(new BigDecimal("500.00")) <= 0) {
            priceRange = "High";
        } else {
            priceRange = "Premium";
        }

        // Return CSV formatted row
        return product.getProductId() + "," +
               newName + "," +
               newPrice.toPlainString() + "," +
               newCategory + "," +
               priceRange;
    }

}
