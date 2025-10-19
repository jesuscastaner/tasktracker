/**
 * Represents the status of a task.
 */
public enum TaskStatus {
    TODO("To do"),
    IN_PROGRESS("In progress"),
    DONE("Done");

    private final String label;

    /**
     * Constructs a TaskStatus with the provided human-readable label.
     *
     * @param label The human-readable label for the task status.
     */
    TaskStatus(String label) {
        this.label = label;
    }

    /**
     * Returns the human-readable label for the task status.
     *
     * @return The human-readable label for the task status.
     */
    public String getLabel() {
        return label;
    }
}
