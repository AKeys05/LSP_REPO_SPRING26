package org.howard.edu.lsp.midterm.strategy;

/**
 * Name: Amaya Keys
 * Pricing strategy for member customers.
 *
 * <p>Applies a 10% discount to the base price.</p>
 */

public class MemberPricing implements PricingStrategy{
	/** Discount multiplier representing a 10% reduction. */
    private static final double DISCOUNT = 0.90;

    /**
     * Applies a 10% member discount to the given price.
     *
     * @param price the original price
     * @return the price after a 10% discount
     */
    @Override
    public double apply(double price) {
        return price * DISCOUNT;
    }
}
