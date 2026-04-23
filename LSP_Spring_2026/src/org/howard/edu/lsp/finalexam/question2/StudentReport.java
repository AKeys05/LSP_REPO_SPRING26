package org.howard.edu.lsp.finalexam.question2;

/**
 * Name: Amaya Keys
 * Concrete report implementation that displays student information.
 *
 * <p>Data fields are intentionally left uninitialised at declaration time;
 * they are populated inside {@link #loadData()} to honour the Template Method
 * contract and demonstrate the correct workflow sequence.
 *
 * @see Report
 */

class StudentReport extends Report {
	/** The full name of the student displayed in the report. */
    private String studentName;

    /** The student's Grade Point Average, displayed to one decimal place. */
    private double gpa;

    /**
     * Loads hard-coded student data into {@link #studentName} and {@link #gpa}.
     *
     * <p>In a production system this method would read from a database or
     * external data source; here it uses literal values for demonstration.
     */
    @Override
    protected void loadData() {
        studentName = "John Doe";
        gpa = 3.8;
    }

    /**
     * Prints the report header, identifying this document as a Student Report.
     */
    @Override
    protected void formatHeader() {
        System.out.println("=== HEADER ===");
        System.out.println("Student Report");
        System.out.println();
    }

    /**
     * Prints the student's name and GPA as the report body.
     *
     * <p>Relies on {@link #studentName} and {@link #gpa} having been set
     * by {@link #loadData()}.
     */
    @Override
    protected void formatBody() {
        System.out.println("=== BODY ===");
        System.out.println("Student Name: " + studentName);
        System.out.printf("GPA: %.1f%n", gpa);
        System.out.println();
    }

    /**
     * Prints a closing footer that signals the end of the Student Report.
     */
    @Override
    protected void formatFooter() {
        System.out.println("=== FOOTER ===");
        System.out.println("End of Student Report");
        System.out.println();
    }
}
