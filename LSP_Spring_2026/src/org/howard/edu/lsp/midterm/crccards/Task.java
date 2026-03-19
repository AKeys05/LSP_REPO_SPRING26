package org.howard.edu.lsp.midterm.crccards;

/**
 * Name: Amaya Keys
 * Represents a task with an ID, description, and status.
 * The default status upon creation is "OPEN".
 * Valid status values are: OPEN, IN_PROGRESS, COMPLETE.
 */

public class Task {
	
	private String taskId;
    private String description;
    private String status;
    
    /**
     * Constructs a new Task with the given ID and description.
     * The status is initialized to "OPEN" by default.
     *
     * @param taskId      the unique identifier for this task
     * @param description a brief description of the task
     */
	public Task(String taskId, String description) {
		this.taskId = taskId;
        this.description = description;
        this.status = "OPEN";
	}

}
