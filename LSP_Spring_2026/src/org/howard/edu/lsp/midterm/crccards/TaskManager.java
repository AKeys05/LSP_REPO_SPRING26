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

}
