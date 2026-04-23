AI Tools Used:
Claude

Prompts Used (2–5 max):
1.Help me to write JUnit5 test cases based on the following code:

package org.howard.edu.lsp.finalexam.question3;

public class GradeCalculator {

    public double average(int score1, int score2, int score3) {
        validateScore(score1);
        validateScore(score2);
        validateScore(score3);
        return (score1 + score2 + score3) / 3.0;
    }

    public String letterGrade(double average) {
        if (average >= 90) return "A";
        else if (average >= 80) return "B";
        else if (average >= 70) return "C";
        else if (average >= 60) return "D";
        else return "F";
    }

    public boolean isPassing(double average) {
        return average >= 60;
    }

    private void validateScore(int score) {
        if (score < 0 || score > 100) {
            throw new IllegalArgumentException("Score must be between 0 and 100");
        }
    }
}

The test class must include: 1. One test for average() 2. One test for letterGrade() 3. One test for isPassing() 4. Two boundary-value tests 5. Two exception tests using assertThrows() 

Other requirements:
- Do NOT write a Driver 
- Use clear test method names 
- Focus on correctness and boundary cases

How AI Helped (2–3 sentences):
AI helped me generate the required code needed to properly test each test case and create a comprehensive test suite. It also helped to make sure that the code followed Junit5 conventions and specifications.

Reflection (1–2 sentences):
I learned how to analyze the objectives and structure of a given code snippet, generate corresponding tests that make up a test suite, and then format them according to JUnit5 in Eclipse.