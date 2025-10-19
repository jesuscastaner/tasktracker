import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with ID, description, status, and creation and update
 * timestamps.
 */
public class Task {
    private int id;
    private String description;
    private TaskStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private static final DateTimeFormatter DATE_TIME_FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    /**
     * Constructs a Task with uninitialized fields.
     */
    public Task() {
    }

    /**
     * Constructs a Task with the provided ID and description.
     * Sets its status to "To do" and its timestamps to the current date and
     * time.
     *
     * @param id          The task ID.
     * @param description The task description.
     */
    public Task(int id, String description) {
        this.id = id;
        this.description = description;
        status = TaskStatus.TODO;
        createdAt = LocalDateTime.now();
        updatedAt = createdAt;
    }

    /**
     * Returns the ID of the task.
     *
     * @return The ID of the task.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the ID of the task.
     *
     * @param id The ID of the task.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Returns the description of the task.
     *
     * @return The description of the task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the task.
     *
     * @param description The description of the task.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Returns the status of the task.
     *
     * @return The status of the task.
     */
    public TaskStatus getStatus() {
        return status;
    }

    /**
     * Sets the status of the task.
     *
     * @param status The status of the task.
     */
    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    /**
     * Returns the creation timestamp of the task.
     *
     * @return The creation timestamp of the task.
     */
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    /**
     * Sets the creation timestamp of the task.
     *
     * @param createdAt The creation timestamp of the task.
     */
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * Returns the update timestamp of the task.
     *
     * @return The update timestamp of the task.
     */
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    /**
     * Sets the update timestamp of the task.
     *
     * @param updatedAt The update timestamp of the task.
     */
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    /**
     * Returns a string representation of the task.
     *
     * @return A string representation of the task.
     */
    @Override
    public String toString() {
        return "ID " + id + ":" +
                "\n  Description: \"" + description + "\"" +
                "\n  Status: " + status.getLabel() +
                "\n  Created at: " + DATE_TIME_FORMATTER.format(createdAt) +
                "\n  Updated at: " + DATE_TIME_FORMATTER.format(updatedAt);
    }
}
