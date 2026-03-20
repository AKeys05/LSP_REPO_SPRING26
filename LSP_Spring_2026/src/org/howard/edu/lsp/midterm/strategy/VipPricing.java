package org.howard.edu.lsp.midterm.strategy;

/**
 * Name: Amaya Keys
 * Pricing strategy for VIP customers.
 *
 * <p>Applies a 20% discount to the base price.</p>
 */

public class VipPricing implements PricingStrategy {

	/** Discount multiplier representing a 20% reduction. */
    private static final double DISCOUNT = 0.80;

    /**
     * Applies a 20% VIP discount to the given price.
     *
     * @param price the original price
     * @return the price after a 20% discount
     */
    @Override
    public double apply(double price) {
        return price * DISCOUNT;
    }	
}
