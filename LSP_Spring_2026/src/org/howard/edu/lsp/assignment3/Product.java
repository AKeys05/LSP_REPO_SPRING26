/*
 * Name: Amaya Keys
 */

package org.howard.edu.lsp.assignment3;

import java.math.BigDecimal;

/**
 * Represents a product record from the CSV file.
 * 
 * <p>
 * This class encapsulates the data fields of a product:
 * productId, name, price, and category. It provides getters and setters for
 * these fields.
 * </p>
 * 
 */
public class Product {
    private int productId;
    private String name;
    private BigDecimal price;
    private String category;

    /**
     * Constructs a Product object with the specified fields.
     * 
     * @param productId the product ID
     * @param name      the product name
     * @param price     the product price
     * @param category  the product category
     */
    public Product(int productId, String name, BigDecimal price, String category) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.category = category;
    }
    
    /** @return the product ID */
    public int getProductId() { return productId; }
    
    /** @return the product name */
    public String getName() { return name; }
    
    /** @return the product price */
    public BigDecimal getPrice() { return price; }
    
    /** @return the product category */
    public String getCategory() { return category; }

    /** @param name set the product name */
    public void setName(String name) { this.name = name; }
    
    /** @param price set the product price */
    public void setPrice(BigDecimal price) { this.price = price; }
    
    /** @param category set the product category */
    public void setCategory(String category) { this.category = category; }

}
