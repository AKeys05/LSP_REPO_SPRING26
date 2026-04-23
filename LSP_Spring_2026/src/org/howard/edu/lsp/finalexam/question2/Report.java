package org.howard.edu.lsp.finalexam.question2;


/**
 * Name: Amaya Keys
 * Abstract base class that defines the <b>Template Method</b> design pattern
 * for generating reports.
 *
 * <p>The fixed workflow is enforced by {@link #generateReport()}, which calls
 * each step in order:
 * <ol>
 *   <li>{@link #loadData()}</li>
 *   <li>{@link #formatHeader()}</li>
 *   <li>{@link #formatBody()}</li>
 *   <li>{@link #formatFooter()}</li>
 * </ol>
 *
 * <p>Subclasses must implement all abstract steps to supply report-specific
 * data and formatting. The workflow itself cannot be overridden.
 */

abstract class Report {
	/**
     * <b>Template Method</b> — defines the fixed report-generation workflow.
     *
     * <p>This method is {@code final} to prevent subclasses from altering the
     * sequence of steps. Polymorphism is achieved through the abstract steps
     * implemented by each concrete subclass.
     */
    public final void generateReport() {
        loadData();
        formatHeader();
        formatBody();
        formatFooter();
    }

    /**
     * Loads and initialises the data needed to produce the report.
     * Subclasses must populate all fields required by
     * {@link #formatHeader()}, {@link #formatBody()}, and
     * {@link #formatFooter()} during this step.
     */
    protected abstract void loadData();

    /**
     * Formats and prints the report header section.
     * Invoked after {@link #loadData()} and before {@link #formatBody()}.
     */
    protected abstract void formatHeader();

    /**
     * Formats and prints the report body section.
     * Invoked after {@link #formatHeader()} and before {@link #formatFooter()}.
     */
    protected abstract void formatBody();

    /**
     * Formats and prints the report footer section.
     * Invoked last in the workflow.
     */
    protected abstract void formatFooter();
}

