package org.howard.edu.lsp.midterm.strategy;

/**
 * Name: Amaya Keys.
 * Driver class that demonstrates the Strategy-based pricing implementation.
 *
 * <p>Each customer type is paired with its corresponding {@link PricingStrategy}
 * and passed into a {@link PriceCalculator}. The calculated price is produced
 * entirely by the strategy at runtime — no results are hardcoded.</p>
 */

public class Driver {
	/**
     * Entry point. Demonstrates pricing output for all supported customer types
     * against a base purchase price of 100.0.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {

        final double purchasePrice = 100.0;

        PricingStrategy[] strategies = {
            new RegularPricing(),
            new MemberPricing(),
            new VipPricing(),
            new HolidayPricing()
        };

        String[] labels = {
            "REGULAR",
            "MEMBER",
            "VIP",
            "HOLIDAY"
        };

        for (int i = 0; i < strategies.length; i++) {
            PriceCalculator calculator = new PriceCalculator(strategies[i]);
            double result = calculator.calculatePrice(purchasePrice);
            System.out.println(labels[i] + ": " + result);
        }
    }
}
