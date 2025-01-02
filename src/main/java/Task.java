import java.time.LocalDateTime;

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

    /**
     * Creates an empty Task with uninitialized fields.
     */
    public Task() {
    }

    /**
     * Creates a new Task with the provided ID and description. Sets its status
     * to "TODO" and its timestamps to the current time.
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
     * @return The task ID.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the ID of the task.
     *
     * @param id The task ID.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Returns the description of the task.
     *
     * @return The task description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the task.
     *
     * @param description The task description.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Returns the status of the task.
     *
     * @return The task status.
     */
    public TaskStatus getStatus() {
        return status;
    }

    /**
     * Sets the status of the task.
     *
     * @param status The task status.
     */
    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    /**
     * Returns the creation timestamp of the task.
     *
     * @return The creation timestamp.
     */
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    /**
     * Sets the creation timestamp of the task.
     *
     * @param createdAt The creation timestamp.
     */
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * Returns the update timestamp of the task.
     *
     * @return The update timestamp.
     */
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    /**
     * Sets the update timestamp of the task.
     *
     * @param updatedAt The update timestamp.
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
        return "Task [id=" + id + ", "
                + "description=" + description + ", "
                + "status=" + status + ", "
                + "createdAt=" + createdAt + ", "
                + "updatedAt=" + updatedAt + "]";
    }
}
