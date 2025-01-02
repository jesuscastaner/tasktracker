import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Serves as a repository for managing tasks stored in a JSON file.
 */
public class TaskRepository {
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
}
