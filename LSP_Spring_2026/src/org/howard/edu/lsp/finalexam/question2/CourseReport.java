package org.howard.edu.lsp.finalexam.question2;

/**
 * Name: Amaya Keys
 * Concrete report implementation that displays course information.
 *
 * <p>Like {@link StudentReport}, all fields are set inside {@link #loadData()}
 * rather than at declaration time, keeping the Template Method workflow intact.
 *
 * @see Report
 */

class CourseReport extends Report {
	/** The name or code of the course displayed in the report. */
    private String courseName;

    /** The number of students currently enrolled in the course. */
    private int enrollment;

    /**
     * Loads hard-coded course data into {@link #courseName} and
     * {@link #enrollment}.
     *
     * <p>In a production system this method would query a registrar database;
     * here it uses literal values for demonstration.
     */
    @Override
    protected void loadData() {
        courseName = "CSCI 363";
        enrollment = 45;
    }

    /**
     * Prints the report header, identifying this document as a Course Report.
     */
    @Override
    protected void formatHeader() {
        System.out.println("=== HEADER ===");
        System.out.println("Course Report");
        System.out.println();
    }

    /**
     * Prints the course name and current enrollment as the report body.
     *
     * <p>Relies on {@link #courseName} and {@link #enrollment} having been set
     * by {@link #loadData()}.
     */
    @Override
    protected void formatBody() {
        System.out.println("=== BODY ===");
        System.out.println("Course: " + courseName);
        System.out.println("Enrollment: " + enrollment);
        System.out.println();
    }

    /**
     * Prints a closing footer that signals the end of the Course Report.
     */
    @Override
    protected void formatFooter() {
        System.out.println("=== FOOTER ===");
        System.out.println("End of Course Report");
        System.out.println();
    }
}
