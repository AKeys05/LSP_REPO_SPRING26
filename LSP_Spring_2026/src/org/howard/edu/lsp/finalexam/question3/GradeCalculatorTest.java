package org.howard.edu.lsp.finalexam.question3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GradeCalculatorTest {
	
	private GradeCalculator calc;

    @BeforeEach
    void setUp() {
        calc = new GradeCalculator();
    }

    // -------------------------------------------------------
    // 1. Test for average()
    // -------------------------------------------------------

    @Test
    @DisplayName("average() returns correct mean of three valid scores")
    void testAverageOfThreeValidScores() {
        double result = calc.average(70, 80, 90);
        assertEquals(80.0, result, 0.001,
                "Expected average of 70, 80, 90 to be 80.0");
    }

    // -------------------------------------------------------
    // 2. Test for letterGrade()
    // -------------------------------------------------------

    @Test
    @DisplayName("letterGrade() returns correct letter for each grade band")
    void testLetterGradeForEachBand() {
        assertEquals("A", calc.letterGrade(95.0),  "95 should be an A");
        assertEquals("B", calc.letterGrade(85.0),  "85 should be a B");
        assertEquals("C", calc.letterGrade(75.0),  "75 should be a C");
        assertEquals("D", calc.letterGrade(65.0),  "65 should be a D");
        assertEquals("F", calc.letterGrade(55.0),  "55 should be an F");
    }

    // -------------------------------------------------------
    // 3. Test for isPassing()
    // -------------------------------------------------------

    @Test
    @DisplayName("isPassing() returns true for passing average and false for failing")
    void testIsPassingReturnsTrueAbove60AndFalseBelow() {
        assertTrue(calc.isPassing(75.0),  "75 should be passing");
        assertFalse(calc.isPassing(59.9), "59.9 should be failing");
    }

    // -------------------------------------------------------
    // 4. Boundary-value tests
    // -------------------------------------------------------

    @Test
    @DisplayName("Boundary: average() with all scores at minimum boundary (0)")
    void testAverageAtMinimumBoundary() {
        double result = calc.average(0, 0, 0);
        assertEquals(0.0, result, 0.001,
                "Average of three zeros should be 0.0");
    }

    @Test
    @DisplayName("Boundary: average() with all scores at maximum boundary (100)")
    void testAverageAtMaximumBoundary() {
        double result = calc.average(100, 100, 100);
        assertEquals(100.0, result, 0.001,
                "Average of three 100s should be 100.0");
    }

    // -------------------------------------------------------
    // 5. Exception tests using assertThrows()
    // -------------------------------------------------------

    @Test
    @DisplayName("Exception: average() throws IllegalArgumentException for score below 0")
    void testAverageThrowsExceptionForScoreBelowZero() {
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> calc.average(-1, 50, 50),
                "Score of -1 should throw IllegalArgumentException"
        );
        assertEquals("Score must be between 0 and 100", ex.getMessage());
    }

    @Test
    @DisplayName("Exception: average() throws IllegalArgumentException for score above 100")
    void testAverageThrowsExceptionForScoreAbove100() {
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> calc.average(50, 101, 50),
                "Score of 101 should throw IllegalArgumentException"
        );
        assertEquals("Score must be between 0 and 100", ex.getMessage());
    }

}
