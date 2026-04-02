package org.howard.edu.lsp.assignment5;

/**
 * Name: Amaya Keys
 * JUnit 5 test suite for IntegerSet.java.
 * Verifies correctness of set operations, utility methods, and edge cases.
 */

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class IntegerSetTests {
	
	private IntegerSet set1;
    private IntegerSet set2;
    
    /**
     * Initializes two fresh IntegerSets before each test.
     * set1 = {1, 2, 3}, set2 = {2, 3, 4}
     */
    @BeforeEach
    public void setUp() {
        set1 = new IntegerSet();
        set1.add(1);
        set1.add(2);
        set1.add(3);

        set2 = new IntegerSet();
        set2.add(2);
        set2.add(3);
        set2.add(4);
    }
    
    /**
     * Verifies all four set operations return the correct new sets
     * and that the original sets are not modified.
     */
    @Test
    public void testSetOperations() {
        IntegerSet unionResult      = set1.union(set2);
        IntegerSet intersectResult  = set1.intersect(set2);
        IntegerSet diffResult       = set1.diff(set2);
        IntegerSet complementResult = set1.complement(set2);

        // Union: {1, 2, 3} ∪ {2, 3, 4} = {1, 2, 3, 4}
        assertEquals("[1, 2, 3, 4]", unionResult.toString(), "Union failed");

        // Intersection: {1, 2, 3} ∩ {2, 3, 4} = {2, 3}
        assertEquals("[2, 3]", intersectResult.toString(), "Intersection failed");

        // Difference: {1, 2, 3} − {2, 3, 4} = {1}
        assertEquals("[1]", diffResult.toString(), "Difference failed");

        // Complement: {2, 3, 4} − {1, 2, 3} = {4}
        assertEquals("[4]", complementResult.toString(), "Complement failed");

        // Originals must be unmodified
        assertEquals("[1, 2, 3]", set1.toString(), "set1 was modified");
        assertEquals("[2, 3, 4]", set2.toString(), "set2 was modified");
    }
    
    /**
     * Verifies that equals() correctly identifies equal and unequal sets.
     */
    @Test
    public void testEquals() {
        IntegerSet copy = new IntegerSet();
        copy.add(3);
        copy.add(1);
        copy.add(2);

        // Same elements in different insertion order must be equal
        assertTrue(set1.equals(copy), "Sets with same elements should be equal");

        // Sets with different elements must not be equal
        assertFalse(set1.equals(set2), "Sets with different elements should not be equal");

        // A set must equal itself
        assertTrue(set1.equals(set1), "A set should equal itself");
    }
    
    /**
     * Verifies that add() prevents duplicates and that remove() correctly
     * removes existing items while leaving the set unchanged for missing items.
     */
    @Test
    public void testAddAndRemove() {
        // Adding a duplicate should not change length
        set1.add(2);
        assertEquals(3, set1.length(), "Duplicate add should not increase length");

        // Removing an existing element should decrease length by 1
        set1.remove(2);
        assertEquals(2, set1.length(), "Length should decrease after remove");
        assertFalse(set1.contains(2), "Removed element should not be present");

        // Removing a non-existent element should leave the set unchanged
        set1.remove(99);
        assertEquals(2, set1.length(), "Removing absent element should not change length");
    }
    
    /**
     * Verifies utility methods for querying set properties.
     */
    @Test
    public void testUtilityMethods() {
        assertEquals(3, set1.largest(),  "Largest should be 3");
        assertEquals(1, set1.smallest(), "Smallest should be 1");
        assertEquals(3, set1.length(),   "Length should be 3");

        assertTrue(set1.contains(2),  "Set should contain 2");
        assertFalse(set1.contains(9), "Set should not contain 9");

        assertFalse(set1.isEmpty(), "Non-empty set should not be empty");

        set1.clear();
        assertTrue(set1.isEmpty(), "Cleared set should be empty");
    }
    
    /**
     * Verifies correct behavior when one or both sets are empty:
     * union with empty, intersection with empty, diff with empty,
     * complement with empty, and exceptions from largest/smallest.
     */
    @Test
    public void testEdgeCases() {
        IntegerSet empty = new IntegerSet();

        // Union with empty set should return a copy of set1
        assertEquals("[1, 2, 3]", set1.union(empty).toString(),
                "Union with empty set should equal set1");

        // Intersection with empty set should return empty set
        assertEquals("[]", set1.intersect(empty).toString(),
                "Intersection with empty set should be empty");

        // Difference against empty set should return copy of set1
        assertEquals("[1, 2, 3]", set1.diff(empty).toString(),
                "Difference with empty set should equal set1");

        // Complement of set1 relative to empty set should return empty set
        assertEquals("[]", set1.complement(empty).toString(),
                "Complement relative to empty set should be empty");

        // largest() and smallest() must throw on an empty set
        assertThrows(IllegalStateException.class, empty::largest,
                "largest() on empty set should throw IllegalStateException");
        assertThrows(IllegalStateException.class, empty::smallest,
                "smallest() on empty set should throw IllegalStateException");
    }

}
