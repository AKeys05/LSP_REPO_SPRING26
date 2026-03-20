package org.howard.edu.lsp.midterm.strategy;

/**
 * Name: Amaya Keys
 * Pricing strategy for regular customers.
 *
 * <p>No discount is applied; the base price is returned as-is.</p>
 */

public class RegularPricing implements PricingStrategy {
	 /**
     * Returns the original price with no discount applied.
     *
     * @param price the original price
     * @return the original price unchanged
     */
    @Override
    public double apply(double price) {
        return price;
    }
}
