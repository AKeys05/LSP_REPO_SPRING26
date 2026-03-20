package org.howard.edu.lsp.midterm.strategy;

/**
 * Name: Amaya Keys
 * Calculates the final price for a given item using an injected pricing strategy.
 *
 * <p>{@code PriceCalculator} is intentionally ignorant of any specific discount
 * logic. It delegates all pricing decisions to the {@link PricingStrategy}
 * provided at construction time, satisfying both the Open/Closed Principle
 * and the Dependency Inversion Principle.</p>
 *
 * <p>Example usage:</p>
 * <pre>{@code
 * // Standard member discount
 * PriceCalculator calculator = new PriceCalculator(new MemberPricing());
 * double price = calculator.calculatePrice(100.0); // returns 90.0
 *
 * // VIP customer during a holiday — stacked discounts via decorator
 * PriceCalculator calculator = new PriceCalculator(new HolidayPricingDecorator(new VipPricing()));
 * double price = calculator.calculatePrice(100.0); // returns 76.0
 * }</pre>
 */

public class PriceCalculator {
	/** The pricing strategy used to compute the final price. */
    private final PricingStrategy strategy;

    /**
     * Constructs a {@code PriceCalculator} with the specified pricing strategy.
     *
     * @param strategy the {@link PricingStrategy} to apply when calculating prices;
     *                 must not be null
     */
    public PriceCalculator(PricingStrategy strategy) {
        this.strategy = strategy;
    }

    /**
     * Calculates the final price by delegating to the injected pricing strategy.
     *
     * @param price the base price before any discount; must be non-negative
     * @return the final price after the strategy's discount rule has been applied
     */
    public double calculatePrice(double price) {
        return strategy.apply(price);
    }
}
