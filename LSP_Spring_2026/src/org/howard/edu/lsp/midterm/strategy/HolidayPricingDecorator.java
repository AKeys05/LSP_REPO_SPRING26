package org.howard.edu.lsp.midterm.strategy;

/**
 * Name: Amaya Keys
 * Decorator that layers a holiday discount on top of any existing pricing strategy.
 *
 * <p>This follows the Decorator Pattern, allowing holiday pricing to be composed
 * with any {@link PricingStrategy} at runtime without modifying either class.
 * For example, a VIP customer shopping during a holiday can have both discounts
 * applied in sequence:</p>
 *
 * <pre>{@code
 * PricingStrategy vipHoliday = new HolidayPricingDecorator(new VipPricing());
 * PriceCalculator calculator = new PriceCalculator(vipHoliday);
 * }</pre>
 */

public class HolidayPricingDecorator implements PricingStrategy {
	/** The base strategy to apply before the holiday discount. */
    private final PricingStrategy base;

    /** Additional holiday discount multiplier representing a 5% reduction. */
    private static final double HOLIDAY_DISCOUNT = 0.95;

    /**
     * Constructs a holiday decorator wrapping the provided base strategy.
     *
     * @param base the underlying {@link PricingStrategy} to decorate; must not be null
     */
    public HolidayPricingDecorator(PricingStrategy base) {
        this.base = base;
    }

    /**
     * Applies the base strategy first, then layers on an additional 5% holiday discount.
     *
     * @param price the original price
     * @return the price after both the base strategy and holiday discount are applied
     */
    @Override
    public double apply(double price) {
        return base.apply(price) * HOLIDAY_DISCOUNT;
    }
}
