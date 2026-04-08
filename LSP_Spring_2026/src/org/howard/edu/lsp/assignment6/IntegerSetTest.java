package org.howard.edu.lsp.assignment6;

/**
 * Name: Amaya Keys
 * JUnit test suite for IntegerSet.java. 
 * Each method is covered by one normal case and one edge case.
 * Exception behavior is verified with assertThrows().
 */

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


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
    // add()
    // =========================================================================

    /** Normal: adding a new element increases length and the element is present. */
    @Test
    public void testAdd_normal() {
        set1.add(5);
        assertTrue(set1.contains(5), "Set should contain 5 after add");
        assertEquals(4, set1.length(), "Length should be 4 after adding new element");
    }

    /** Edge: adding a duplicate must not change the set. */
    @Test
    public void testAdd_duplicate() {
        set1.add(2);
        assertEquals(3, set1.length(), "Duplicate add should not increase length");
        assertEquals("[1, 2, 3]", set1.toString(), "Set should be unchanged after duplicate add");
    }

    // =========================================================================
    // remove()
    // =========================================================================

    /** Normal: removing a present element decreases length and the element is gone. */
    @Test
    public void testRemove_normal() {
        set1.remove(2);
        assertFalse(set1.contains(2), "Set should not contain 2 after remove");
        assertEquals(2, set1.length(), "Length should be 2 after remove");
    }

    /** Edge: removing a value not in the set must leave the set unchanged. */
    @Test
    public void testRemove_valueNotPresent() {
        set1.remove(99);
        assertEquals(3, set1.length(), "Length should be unchanged after removing absent value");
        assertEquals("[1, 2, 3]", set1.toString(), "Set should be unchanged after removing absent value");
    }

    // =========================================================================
    // equals()
    // =========================================================================

    /** Normal: two sets with the same elements in the same insertion order are equal. */
    @Test
    public void testEquals_normal() {
        IntegerSet other = new IntegerSet();
        other.add(1);
        other.add(2);
        other.add(3);
        assertTrue(set1.equals(other), "Sets with same elements should be equal");
    }

    /** Edge: same elements added in different order must still be equal. */
    @Test
    public void testEquals_sameElementsDifferentOrder() {
        IntegerSet other = new IntegerSet();
        other.add(3);
        other.add(1);
        other.add(2);
        assertTrue(set1.equals(other), "Sets with same elements in different order should be equal");
    }

    // =========================================================================
    // union()
    // =========================================================================

    /** Normal: union of two overlapping sets contains all unique elements. */
    @Test
    public void testUnion_normal() {
        IntegerSet result = set1.union(set2);
        assertEquals("[1, 2, 3, 4]", result.toString(), "Union should contain all unique elements");
    }

    /** Edge: union with an empty set must return a copy of the original set. */
    @Test
    public void testUnion_withEmptySet() {
        IntegerSet empty = new IntegerSet();
        IntegerSet result = set1.union(empty);
        assertEquals("[1, 2, 3]", result.toString(), "Union with empty set should equal original set");
    }

    // =========================================================================
    // intersect()
    // =========================================================================

    /** Normal: intersection of two overlapping sets contains only common elements. */
    @Test
    public void testIntersect_normal() {
        IntegerSet result = set1.intersect(set2);
        assertEquals("[2, 3]", result.toString(), "Intersection should contain only common elements");
    }

    /** Edge: intersection of two disjoint sets must return an empty set. */
    @Test
    public void testIntersect_noCommonElements() {
        IntegerSet disjoint = new IntegerSet();
        disjoint.add(7);
        disjoint.add(8);
        disjoint.add(9);
        IntegerSet result = set1.intersect(disjoint);
        assertEquals("[]", result.toString(), "Intersection of disjoint sets should be empty");
        assertTrue(result.isEmpty(), "Intersection of disjoint sets should be empty");
    }

    // =========================================================================
    // diff()
    // =========================================================================

    /** Normal: difference returns elements in set1 but not in set2. */
    @Test
    public void testDiff_normal() {
        IntegerSet result = set1.diff(set2);
        assertEquals("[1]", result.toString(), "Diff should contain elements in set1 but not set2");
    }

    /** Edge: difference of two identical sets must return an empty set. */
    @Test
    public void testDiff_identicalSets() {
        IntegerSet copy = new IntegerSet();
        copy.add(1);
        copy.add(2);
        copy.add(3);
        IntegerSet result = set1.diff(copy);
        assertEquals("[]", result.toString(), "Diff of identical sets should be empty");
        assertTrue(result.isEmpty(), "Diff of identical sets should be empty");
    }

    // =========================================================================
    // complement()
    // =========================================================================

    /** Normal: complement returns elements in set2 but not in set1. */
    @Test
    public void testComplement_normal() {
        IntegerSet result = set1.complement(set2);
        assertEquals("[4]", result.toString(), "Complement should contain elements in set2 but not set1");
    }

    /** Edge: complement of two disjoint sets must return all of set2. */
    @Test
    public void testComplement_disjointSets() {
        IntegerSet disjoint = new IntegerSet();
        disjoint.add(7);
        disjoint.add(8);
        disjoint.add(9);
        IntegerSet result = set1.complement(disjoint);
        assertEquals("[7, 8, 9]", result.toString(), "Complement of disjoint sets should equal set2");
    }

    // =========================================================================
    // contains()
    // =========================================================================

    /** Normal: contains() returns true for an element that is in the set. */
    @Test
    public void testContains_normal() {
        assertTrue(set1.contains(2), "Set should contain 2");
    }

    /** Edge: contains() returns false for a value not in the set. */
    @Test
    public void testContains_valueNotPresent() {
        assertFalse(set1.contains(99), "Set should not contain 99");
    }

    // =========================================================================
    // isEmpty()
    // =========================================================================

    /** Normal: a non-empty set must return false. */
    @Test
    public void testIsEmpty_nonEmpty() {
        assertFalse(set1.isEmpty(), "Non-empty set should not be empty");
    }

    /** Edge: a newly constructed set and a cleared set must both return true. */
    @Test
    public void testIsEmpty_emptySet() {
        IntegerSet empty = new IntegerSet();
        assertTrue(empty.isEmpty(), "Newly constructed set should be empty");

        set1.clear();
        assertTrue(set1.isEmpty(), "Set should be empty after clear()");
    }

    // =========================================================================
    // toString()
    // =========================================================================

    /** Normal: elements must appear in ascending order with correct formatting. */
    @Test
    public void testToString_normal() {
        IntegerSet s = new IntegerSet();
        s.add(5);
        s.add(1);
        s.add(3);
        assertEquals("[1, 3, 5]", s.toString(), "toString() must return elements in ascending order");
    }

    /** Edge: an empty set must return exactly "[]". */
    @Test
    public void testToString_emptySet() {
        IntegerSet empty = new IntegerSet();
        assertEquals("[]", empty.toString(), "toString() of empty set must return []");
    }

    // =========================================================================
    // largest()
    // =========================================================================

    /** Normal: largest() returns the maximum element in the set. */
    @Test
    public void testLargest_normal() {
        assertEquals(3, set1.largest(), "Largest should be 3");
    }

    /** Edge: largest() on a single-element set returns that element;
     *        largest() on an empty set must throw IllegalStateException. */
    @Test
    public void testLargest_singleElementAndEmpty() {
        IntegerSet single = new IntegerSet();
        single.add(42);
        assertEquals(42, single.largest(), "Largest of single-element set should be 42");

        IntegerSet empty = new IntegerSet();
        assertThrows(IllegalStateException.class, empty::largest,
                "largest() on empty set should throw IllegalStateException");
    }

    // =========================================================================
    // smallest()
    // =========================================================================

    /** Normal: smallest() returns the minimum element in the set. */
    @Test
    public void testSmallest_normal() {
        assertEquals(1, set1.smallest(), "Smallest should be 1");
    }

    /** Edge: smallest() on a single-element set returns that element;
     *        smallest() on an empty set must throw IllegalStateException. */
    @Test
    public void testSmallest_singleElementAndEmpty() {
        IntegerSet single = new IntegerSet();
        single.add(7);
        assertEquals(7, single.smallest(), "Smallest of single-element set should be 7");

        IntegerSet empty = new IntegerSet();
        assertThrows(IllegalStateException.class, empty::smallest,
                "smallest() on empty set should throw IllegalStateException");
    }
}