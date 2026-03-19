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
	
    /**
     * Returns the unique identifier for this task.
     *
     * @return the task ID
     */
    public String getTaskId() {
        return taskId;
    }

    /**
     * Returns the description of this task.
     *
     * @return the task description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the current status of this task.
     *
     * @return the task status
     */
    public String getStatus() {
        return status;
    }

    /**
     * Updates the status of this task.
     * Valid values are "OPEN", "IN_PROGRESS", and "COMPLETE" (case-sensitive).
     * If the provided value is not one of the valid statuses, the status is set to "UNKNOWN".
     *
     * @param status the new status to assign to this task
     */
    public void setStatus(String status) {
        if (status.equals("OPEN") || status.equals("IN_PROGRESS") || status.equals("COMPLETE")) {
            this.status = status;
        } else {
            this.status = "UNKNOWN";
        }
    }

    /**
     * Returns a string representation of this task in the format:
     * taskId description [status]
     * <p>
     * Example: {@code T1 Write report [OPEN]}
     *
     * @return formatted string representation of the task
     */
    @Override
    public String toString() {
        return taskId + " " + description + " [" + status + "]";
    }

}
