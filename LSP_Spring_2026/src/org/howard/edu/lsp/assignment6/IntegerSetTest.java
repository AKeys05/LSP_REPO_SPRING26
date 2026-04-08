package org.howard.edu.lsp.assignment6;

/**
 * Name: Amaya Keys
 * JUnit test suite for IntegerSet.java.
 * Verifies correctness and robustness of set operations, utility methods, and edge cases.
 */

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.howard.edu.lsp.assignment5.IntegerSet;

public class IntegerSetTest {
	
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
    
    // =========================================================================
	// 5 REQUIRED JUNIT TESTS
	// =========================================================================
    
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
    
    // =========================================================================
 	// ADDITIONAL EDGE CASES
 	// =========================================================================
    
    /**
     * Verifies that toString() always returns elements in ascending order
     * regardless of insertion order, handles a single element, and returns
     * "[]" for an empty set.
     */
    @Test
    public void testToString() {
        IntegerSet s = new IntegerSet();
        s.add(5);
        s.add(1);
        s.add(3);
        s.add(2);
        s.add(4);
        assertEquals("[1, 2, 3, 4, 5]", s.toString(),
                "toString() must return elements in ascending order");

        IntegerSet single = new IntegerSet();
        single.add(42);
        assertEquals("[42]", single.toString(),
                "toString() on single-element set should be [42]");

        IntegerSet empty = new IntegerSet();
        assertEquals("[]", empty.toString(),
                "toString() on empty set should return []");
    }

    /**
     * Verifies that the set correctly handles negative integers and mixed
     * positive/negative values in all operations and ordering.
     */
    @Test
    public void testNegativeAndMixedValues() {
        IntegerSet neg = new IntegerSet();
        neg.add(-3);
        neg.add(-1);
        neg.add(-2);
        assertEquals("[-3, -2, -1]", neg.toString(),
                "Negative integers must be sorted in ascending order");
        assertEquals(-3, neg.smallest(), "Smallest of negatives should be -3");
        assertEquals(-1, neg.largest(),  "Largest of negatives should be -1");

        IntegerSet mixed = new IntegerSet();
        mixed.add(3);
        mixed.add(-1);
        mixed.add(0);
        mixed.add(-5);
        assertEquals("[-5, -1, 0, 3]", mixed.toString(),
                "Mixed values must be sorted correctly");

        IntegerSet other = new IntegerSet();
        other.add(0);
        other.add(-1);
        other.add(7);

        // Intersection of {-5,-1,0,3} and {-1,0,7} = {-1, 0}
        assertEquals("[-1, 0]", mixed.intersect(other).toString(),
                "Intersection with negatives should work correctly");
    }

    /**
     * Verifies that all set operations behave correctly when both operands
     * are empty sets.
     */
    @Test
    public void testBothSetsEmpty() {
        IntegerSet a = new IntegerSet();
        IntegerSet b = new IntegerSet();

        assertEquals("[]", a.union(b).toString(),       "union of two empty sets should be []");
        assertEquals("[]", a.intersect(b).toString(),   "intersect of two empty sets should be []");
        assertEquals("[]", a.diff(b).toString(),        "diff of two empty sets should be []");
        assertEquals("[]", a.complement(b).toString(),  "complement of two empty sets should be []");
        assertTrue(a.equals(b),                         "two empty sets should be equal");
        assertEquals(0, a.length(),                     "empty set length should be 0");
    }

    /**
     * Verifies edge-case results when both operands are identical sets:
     * diff and complement must be empty, union and intersect must equal the set.
     */
    @Test
    public void testIdenticalSets() {
        IntegerSet copy = new IntegerSet();
        copy.add(1);
        copy.add(2);
        copy.add(3);

        // Difference of identical sets must be empty
        assertEquals("[]", set1.diff(copy).toString(),
                "Difference of identical sets should be []");

        // Complement of identical sets must be empty
        assertEquals("[]", set1.complement(copy).toString(),
                "Complement of identical sets should be []");

        // Union of identical sets must equal the set itself
        assertEquals("[1, 2, 3]", set1.union(copy).toString(),
                "Union of identical sets should equal the set");

        // Intersection of identical sets must equal the set itself
        assertEquals("[1, 2, 3]", set1.intersect(copy).toString(),
                "Intersection of identical sets should equal the set");

        assertTrue(set1.equals(copy),
                "Identical sets should be equal");
    }

    /**
     * Verifies behavior when the two sets share no common elements:
     * intersection must be empty, diff must equal the original set,
     * and union must contain all elements from both.
     */
    @Test
    public void testDisjointSets() {
        IntegerSet a = new IntegerSet();
        a.add(1);
        a.add(2);
        a.add(3);

        IntegerSet b = new IntegerSet();
        b.add(4);
        b.add(5);
        b.add(6);

        assertEquals("[]", a.intersect(b).toString(),
                "Intersection of disjoint sets should be []");

        assertEquals("[1, 2, 3]", a.diff(b).toString(),
                "Diff of disjoint sets should equal set a");

        assertEquals("[4, 5, 6]", a.complement(b).toString(),
                "Complement of disjoint sets should equal set b");

        assertEquals("[1, 2, 3, 4, 5, 6]", a.union(b).toString(),
                "Union of disjoint sets should contain all elements");

        assertFalse(a.equals(b),
                "Disjoint sets should not be equal");
    }

    /**
     * Verifies behavior when one set is a proper subset of the other:
     * intersection equals the subset, diff of superset minus subset
     * yields the remaining elements, and complement of subset relative
     * to superset yields those same remaining elements.
     */
    @Test
    public void testSubsetRelationship() {
        IntegerSet superset = new IntegerSet();
        superset.add(1);
        superset.add(2);
        superset.add(3);
        superset.add(4);
        superset.add(5);

        IntegerSet subset = new IntegerSet();
        subset.add(2);
        subset.add(3);

        // Intersection of superset and subset must equal the subset
        assertEquals("[2, 3]", superset.intersect(subset).toString(),
                "Intersection with subset should equal the subset");

        // Diff: superset − subset = remaining elements
        assertEquals("[1, 4, 5]", superset.diff(subset).toString(),
                "Diff of superset minus subset should be remaining elements");

        // Complement: superset − subset from subset's perspective = empty
        assertEquals("[]", subset.diff(superset).toString(),
                "Subset minus superset should be empty");

        // Complement: elements in superset not in subset
        assertEquals("[1, 4, 5]", subset.complement(superset).toString(),
                "Complement of subset relative to superset should be remaining elements");
    }

    /**
     * Verifies that a set can be cleared and then reused correctly,
     * and that an element can be re-added after being removed.
     */
    @Test
    public void testClearAndReuse() {
        set1.clear();
        assertTrue(set1.isEmpty(),   "Set should be empty after clear()");
        assertEquals(0, set1.length(), "Length should be 0 after clear()");
        assertEquals("[]", set1.toString(), "toString() should be [] after clear()");

        // Re-add elements after clear
        set1.add(10);
        set1.add(20);
        assertEquals("[10, 20]", set1.toString(),
                "Set should work correctly after being cleared and re-populated");

        // Remove an element, then re-add it
        set1.remove(10);
        assertFalse(set1.contains(10), "10 should not be present after remove");
        set1.add(10);
        assertTrue(set1.contains(10), "10 should be present after re-add");
        assertEquals(2, set1.length(), "Length should be 2 after re-add");
    }

}
