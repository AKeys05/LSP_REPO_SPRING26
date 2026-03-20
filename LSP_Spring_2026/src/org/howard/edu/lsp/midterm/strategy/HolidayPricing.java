package org.howard.edu.lsp.midterm.strategy;

/**
 * Name: Amaya Keys
 * Pricing strategy for holiday promotions.
 *
 * <p>Applies a 15% discount to the base price. This strategy is
 * independent of customer type and can be composed with other
 * strategies via {@link HolidayPricingDecorator} when stacking
 * discounts is required.</p>
 */

public class HolidayPricing implements PricingStrategy {
	/** Discount multiplier representing a 15% reduction. */
    private static final double DISCOUNT = 0.85;

    /**
     * Applies a 15% holiday discount to the given price.
     *
     * @param price the original price
     * @return the price after a 15% discount
     */
    @Override
    public double apply(double price) {
        return price * DISCOUNT;
    }
}
