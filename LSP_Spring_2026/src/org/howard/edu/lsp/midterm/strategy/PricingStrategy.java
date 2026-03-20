package org.howard.edu.lsp.midterm.strategy;

/**
 * Name: Amaya Keys
 * Strategy interface defining the contract for all pricing rules.
 *
 * <p>Each implementation encapsulates a single discount behavior,
 * allowing {@link PriceCalculator} to remain closed for modification
 * while the system stays open for extension via new implementations.</p>
 */

public interface PricingStrategy {

	/**
     * Applies the pricing rule to the given base price.
     *
     * @param price the original price before any discount; must be non-negative
     * @return the final price after the strategy's discount has been applied
     */
	
    double apply(double price);
    
}
