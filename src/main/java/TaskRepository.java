import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Serves as a repository for managing tasks stored in a JSON file.
 */
public class TaskRepository {
    private static final String ERROR_TASK_NOT_FOUND = "ERROR: Task not found";
    private static final Path FILE_PATH =
            Path.of("src/main/resources/tasks.json");
    private List<Task> tasks;

    /**
     * Initializes the repository by loading tasks from the JSON file. If the
     * file does not exist, it is created.
     */
    public TaskRepository() {
        try {
            if (!Files.exists(FILE_PATH)) {
                Files.createFile(FILE_PATH);
                tasks = new ArrayList<>();
            } else {
                String fileContent = Files.readString(FILE_PATH).trim();
                if (fileContent.isEmpty()) {
                    tasks = new ArrayList<>();
                } else {
                    tasks = JsonConverter.jsonArrayToTasks(fileContent);
                }
            }
        } catch (IOException e) {
            System.out.println("ERROR: Failed to load tasks: " +
                    e.getMessage());
        }
    }

    /**
     * Saves the updated list of tasks to the JSON file.
     *
     * @throws IOException If an I/O error occurs.
     */
    private void saveTasks() throws IOException {
        Files.writeString(FILE_PATH, JsonConverter.tasksToJsonArray(tasks));
    }

    /**
     * Adds a new task with the provided description, automatically assigning
     * it a unique ID.
     *
     * @param description The task description.
     */
    public void addTask(String description) {
        // Extract the highest existing ID to set a new unique ID
        int id = 0;
        if (!tasks.isEmpty()) {
            for (Task task : tasks) {
                id = Math.max(task.getId(), id);
            }
        }
        id++;

        // Create and add the new task
        Task task = new Task(id, description);
        tasks.add(task);
        try {
            saveTasks();
            System.out.println("Task \"" + description +
                    "\" added successfully with ID " + id);
        } catch (IOException e) {
            System.out.println("ERROR: Failed to add task: " + e.getMessage());
        }
    }

    /**
     * Updates the description of an existing task and sets its update
     * timestamp to the current time.
     *
     * @param id          The task ID.
     * @param description The new task description.
     */
    public void updateTask(int id, String description) {
        // Search for the task with the provided ID
        boolean taskExists = false;
        for (Task task : tasks) {
            if (task.getId() == id) {
                // If found, update the corresponding fields
                task.setDescription(description);
                task.setUpdatedAt(LocalDateTime.now());
                taskExists = true;
                break;
            }
        }

        // If found, save the changes
        if (taskExists) {
            try {
                saveTasks();
                System.out.println("Task with ID " + id + " updated to \"" +
                        description + "\" successfully");
            } catch (IOException e) {
                System.out.println("ERROR: Failed to update task: " +
                        e.getMessage());
            }
        } else {
            System.out.println(ERROR_TASK_NOT_FOUND);
        }
    }

    /**
     * Deletes an existing task.
     *
     * @param id The task ID.
     */
    public void deleteTask(int id) {
        // Search for the task with the provided ID
        boolean taskExists = false;
        for (Task task : tasks) {
            if (task.getId() == id) {
                // If found, remove it from the list
                tasks.remove(task);
                taskExists = true;
                break;
            }
        }

        // If found, save the changes
        if (taskExists) {
            try {
                saveTasks();
                System.out.println("Task with ID " + id +
                        " deleted successfully");
            } catch (IOException e) {
                System.out.println("ERROR: Failed to delete task: " +
                        e.getMessage());
            }
        } else {
            System.out.println(ERROR_TASK_NOT_FOUND);
        }
    }

    /**
     * Updates the status of an existing task and sets its update timestamp to
     * the current time.
     *
     * @param id     The task ID.
     * @param status The new task status.
     */
    public void markTask(int id, TaskStatus status) {
        // Search for the task with the provided ID
        boolean taskExists = false;
        for (Task task : tasks) {
            if (task.getId() == id) {
                // If found, update its status
                task.setStatus(status);
                task.setUpdatedAt(LocalDateTime.now());
                taskExists = true;
                break;
            }
        }

        // If found, save the changes
        if (taskExists) {
            try {
                saveTasks();
                System.out.println("Task with ID " + id + " marked as \"" +
                        status.getLabel() + "\" successfully");
            } catch (IOException e) {
                System.out.println("ERROR: Failed to update task status: " +
                        e.getMessage());
            }
        } else {
            System.out.println(ERROR_TASK_NOT_FOUND);
        }
    }

    /**
     * Prints all existing tasks.
     */
    public void listTasks() {
        for (Task task : tasks) {
            System.out.println(task.toString());
        }
    }

    /**
     * Prints all existing tasks with the provided status.
     *
     * @param status The task status.
     */
    public void listTasks(TaskStatus status) {
        for (Task task : tasks) {
            if (task.getStatus() == status) {
                System.out.println(task.toString());
            }
        }
    }
}
