package org.howard.edu.lsp.finalexam.question2;

import java.util.ArrayList;
import java.util.List;

/**
 * Name: Amaya Keys
 * Driver class that demonstrates the Template Method pattern through
 * polymorphism.
 *
 * <p>Both {@link StudentReport} and {@link CourseReport} are stored in a
 * {@code List<Report>}, then iterated uniformly. Each call to
 * {@link Report#generateReport()} dispatches to the correct subclass
 * implementation at runtime, showcasing runtime polymorphism.
 */

public class Driver {
	/**
     * Application entry point.
     *
     * <p>Builds a {@code List<Report>} containing one {@link StudentReport}
     * and one {@link CourseReport}, then calls {@link Report#generateReport()}
     * on each — demonstrating that a single, consistent interface drives
     * completely different output depending on the concrete type.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        // Declare collection using the abstract type — demonstrates polymorphism
        List<Report> reports = new ArrayList<>();

        reports.add(new StudentReport());
        reports.add(new CourseReport());

        // Uniform iteration — the correct workflow executes for each subtype
        for (Report report : reports) {
            report.generateReport();
        }
    }
}
