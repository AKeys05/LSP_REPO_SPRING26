package org.howard.edu.lsp.midterm.crccards;

/**
 * Name: Amaya Keys
 * Manages a collection of {@link Task} objects.
 * Supports adding tasks, locating a task by ID, and retrieving tasks by status.
 * Duplicate task IDs are not permitted.
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TaskManager {

	private Map<String, Task> tasks;
	
	/**
     * Constructs a new TaskManager with an empty task collection.
     */
    public TaskManager() {
        tasks = new HashMap<>();
    }
    
    /**
     * Adds a new task to the manager.
     * The task's ID must be unique; if a task with the same ID already exists,
     * an {@link IllegalArgumentException} is thrown.
     *
     * @param task the task to add
     * @throws IllegalArgumentException if a task with the same ID already exists
     */
    public void addTask(Task task) {
        if (tasks.containsKey(task.getTaskId())) {
            throw new IllegalArgumentException(
                "Task with ID '" + task.getTaskId() + "' already exists."
            );
        }
        tasks.put(task.getTaskId(), task);
    }

    /**
     * Finds and returns the task with the specified ID.
     * Returns {@code null} if no task with the given ID exists.
     *
     * @param taskId the ID of the task to locate
     * @return the matching {@link Task}, or {@code null} if not found
     */
    public Task findTask(String taskId) {
        return tasks.get(taskId);
    }

    /**
     * Returns a list of all tasks whose status matches the specified value.
     * The comparison is case-sensitive. If no tasks match, an empty list is returned.
     *
     * @param status the status value to filter by (e.g., "OPEN", "IN_PROGRESS", "COMPLETE")
     * @return a {@link List} of {@link Task} objects with the matching status
     */
    public List<Task> getTasksByStatus(String status) {
        List<Task> result = new ArrayList<>();
        for (Task task : tasks.values()) {
            if (task.getStatus().equals(status)) {
                result.add(task);
            }
        }
        return result;
    }

}
