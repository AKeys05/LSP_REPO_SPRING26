package org.howard.edu.lsp.assignment6;

/**
 * Name: Amaya Keys
 * Represents a mathematical set of unique integers using an {@link ArrayList}
 * from the Java Collections Framework.
 *
 * <p>A set contains no duplicate elements. All set operation methods
 * ({@link #union}, {@link #intersect}, {@link #diff}, {@link #complement})
 * return a new {@code IntegerSet} and do not modify the original sets.</p>
 *
 * <p>Example usage:</p>
 * <pre>
 *     IntegerSet a = new IntegerSet();
 *     a.add(1);
 *     a.add(2);
 *     a.add(3);  // a = [1, 2, 3]
 * </pre>
 */

import java.util.ArrayList;
import java.util.Collections;

public class IntegerSet {
	
	private ArrayList<Integer> set;
	
	/**
     * Constructs an empty {@code IntegerSet}.
     */
	public IntegerSet() {
        set = new ArrayList<>();
    }
	
	/**
     * Removes all elements from this set.
     * The set will be empty after this call returns.
     */
	public void clear() {
        set.clear();
    }
	
	/**
     * Returns the number of elements in this set.
     *
     * @return the number of elements in this set
     */
	public int length() {
        return set.size();
    }
	
	/**
     * Compares this set to another {@code IntegerSet} for equality.
     * Two sets are equal if they contain exactly the same elements,
     * regardless of order.
     *
     * @param b the {@code IntegerSet} to compare with this set
     * @return {@code true} if both sets contain exactly the same elements;
     *         {@code false} otherwise
     */
	public boolean equals(IntegerSet b) {
        if (this.length() != b.length()) return false;
        return set.containsAll(b.set) && b.set.containsAll(set);
    }
	
	/**
     * Returns {@code true} if this set contains the specified value.
     *
     * @param value the integer value to search for
     * @return {@code true} if this set contains {@code value};
     *         {@code false} otherwise
     */
	public boolean contains(int value) {
        return set.contains(value);
    }
	
	/**
     * Returns the largest element in this set.
     *
     * @return the largest integer in this set
     * @throws IllegalStateException if this set is empty
     */
	public int largest() {
        if (isEmpty()) throw new IllegalStateException("Set is empty.");
        return Collections.max(set);
    }
	
	/**
     * Returns the smallest element in this set.
     *
     * @return the smallest integer in this set
     * @throws IllegalStateException if this set is empty
     */
	public int smallest() {
        if (isEmpty()) throw new IllegalStateException("Set is empty.");
        return Collections.min(set);
    }
	
	/**
     * Adds the specified item to this set if it is not already present.
     * Duplicate values are ignored to preserve set uniqueness.
     *
     * @param item the integer to add to this set
     */
	public void add(int item) {
        if (!set.contains(item)) set.add(item);
    }
	
	/**
     * Removes the specified item from this set if it is present.
     * If the item is not in the set, the set remains unchanged.
     *
     * @param item the integer to remove from this set
     */
	public void remove(int item) {
        set.remove(Integer.valueOf(item));
    }
	
	/**
     * Returns a new {@code IntegerSet} that is the union of this set and
     * the specified set (A ∪ B). The result contains all elements that
     * appear in either set, with no duplicates.
     *
     * <p>Neither the current set nor {@code intSetb} is modified.</p>
     *
     * @param intSetb the set to union with this set
     * @return a new {@code IntegerSet} containing all elements from both sets
     */
	public IntegerSet union(IntegerSet intSetb) {
        IntegerSet result = new IntegerSet();
        result.set.addAll(this.set);
        for (int item : intSetb.set) {
            if (!result.set.contains(item)) result.set.add(item);
        }
        return result;
    }
	
	/**
     * Returns a new {@code IntegerSet} that is the intersection of this set
     * and the specified set (A ∩ B). The result contains only elements that
     * are common to both sets.
     *
     * <p>Neither the current set nor {@code intSetb} is modified.</p>
     *
     * @param intSetb the set to intersect with this set
     * @return a new {@code IntegerSet} containing only elements found in both sets
     */
	public IntegerSet intersect(IntegerSet intSetb) {
        IntegerSet result = new IntegerSet();
        for (int item : this.set) {
            if (intSetb.set.contains(item)) result.set.add(item);
        }
        return result;
    }
	
	/**
     * Returns a new {@code IntegerSet} that is the difference of this set
     * and the specified set (A − B). The result contains elements that are
     * in the current set but not in {@code intSetb}.
     *
     * <p>Neither the current set nor {@code intSetb} is modified.</p>
     *
     * @param intSetb the set to subtract from this set
     * @return a new {@code IntegerSet} containing elements in this set but not in {@code intSetb}
     */
	public IntegerSet diff(IntegerSet intSetb) {
        IntegerSet result = new IntegerSet();
        for (int item : this.set) {
            if (!intSetb.set.contains(item)) result.set.add(item);
        }
        return result;
    }
	
	/**
     * Returns a new {@code IntegerSet} that is the complement of this set
     * relative to the specified set (B − A). The result contains elements
     * that are in {@code intSetb} but not in the current set.
     *
     * <p>Neither the current set nor {@code intSetb} is modified.</p>
     *
     * @param intSetb the set from which elements of this set are excluded
     * @return a new {@code IntegerSet} containing elements in {@code intSetb} but not in this set
     */
	public IntegerSet complement(IntegerSet intSetb) {
        IntegerSet result = new IntegerSet();
        for (int item : intSetb.set) {
            if (!this.set.contains(item)) result.set.add(item);
        }
        return result;
    }
	
	/**
     * Returns {@code true} if this set contains no elements.
     *
     * @return {@code true} if this set is empty; {@code false} otherwise
     */
	public boolean isEmpty() {
        return set.isEmpty();
    }
	
	/**
     * Returns a string representation of this set. Elements are listed in
     * ascending order, separated by a comma and a single space, and enclosed
     * in square brackets.
     *
     * <p>Examples:</p>
     * <pre>
     *     [1, 2, 3]   // non-empty set
     *     []          // empty set
     * </pre>
     *
     * @return a formatted string representation of this set
     */
	@Override
    public String toString() {
        Collections.sort(set);
        return set.toString();
    }
}
