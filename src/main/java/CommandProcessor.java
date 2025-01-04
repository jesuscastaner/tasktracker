import java.util.Arrays;

/**
 * Processes and executes commands entered via the command-line.
 */
public class CommandProcessor {
    private static final String ERROR_INVALID_ARGUMENTS =
            "ERROR: Invalid argument(s)";
    private static final String ERROR_INVALID_ID = "ERROR: Invalid ID";
    private static final String ERROR_INVALID_STATUS = "ERROR: Invalid status";
    private static final String ERROR_UNKNOWN_COMMAND =
            "ERROR: Command not recognized";

    private final TaskRepository taskRepository;

    /**
     * Creates a CommandProcessor and initializes the task repository.
     */
    CommandProcessor() {
        taskRepository = new TaskRepository();
    }
    /**
     * Processes a command and executes the corresponding action.
     *
     * @param args Command-line arguments, where the first element of the array
     *             is the command name and the rest are its arguments.
     */
    public void execute(String[] args) {
        String command = args[0];
        String[] arguments = Arrays.copyOfRange(args, 1, args.length);

        switch (command.toLowerCase()) {
            case "add":
                /*
                Command to add a task.
                Expects one argument: "<description>".
                 */
                if (arguments.length == 1) {
                    String description = arguments[0];
                    taskRepository.addTask(description);
                } else {
                    System.out.println(ERROR_INVALID_ARGUMENTS +
                            ". Use: add \"<description>\"");
                }
                break;

            case "update":
                /*
                Command to update a task.
                Expects two arguments: <id> and "<description>".
                 */
                if (arguments.length == 2) {
                    try {
                        int id = Integer.parseInt(arguments[0]);
                        String description = arguments[1];
                        taskRepository.updateTask(id, description);
                    } catch (NumberFormatException e) {
                        System.out.println(ERROR_INVALID_ID);
                    }
                } else {
                    System.out.println(ERROR_INVALID_ARGUMENTS +
                            ". Use: update <id> \"<description>\"");
                }
                break;

            case "delete":
                /*
                Command to delete a task.
                Expects one argument: <id>.
                 */
                if (arguments.length == 1) {
                    try {
                        int id = Integer.parseInt(arguments[0]);
                        taskRepository.deleteTask(id);
                    } catch (NumberFormatException e) {
                        System.out.println(ERROR_INVALID_ID);
                    }
                } else {
                    System.out.println(ERROR_INVALID_ARGUMENTS +
                            ". Use: delete <id>");
                }
                break;

            case "mark-todo":
                /*
                Command to mark a task as "To do".
                Expects one argument: <id>.
                 */
                if (arguments.length == 1) {
                    try {
                        int id = Integer.parseInt(arguments[0]);
                        taskRepository.markTask(id, TaskStatus.TODO);
                    } catch (NumberFormatException e) {
                        System.out.println(ERROR_INVALID_ID);
                    }
                } else {
                    System.out.println(ERROR_INVALID_ARGUMENTS +
                            ". Use: mark-todo <id>");
                }
                break;

            case "mark-in-progress":
                /*
                Command to mark a task as "In progress".
                Expects one argument: <id>.
                 */
                if (arguments.length == 1) {
                    try {
                        int id = Integer.parseInt(arguments[0]);
                        taskRepository.markTask(id, TaskStatus.IN_PROGRESS);
                    } catch (NumberFormatException e) {
                        System.out.println(ERROR_INVALID_ID);
                    }
                } else {
                    System.out.println(ERROR_INVALID_ARGUMENTS +
                            ". Use: mark-in-progress <id>");
                }
                break;

            case "mark-done":
                /*
                Command to mark a task as "Done".
                Expects one argument: <id>.
                 */
                if (arguments.length == 1) {
                    try {
                        int id = Integer.parseInt(arguments[0]);
                        taskRepository.markTask(id, TaskStatus.DONE);
                    } catch (NumberFormatException e) {
                        System.out.println(ERROR_INVALID_ID);
                    }
                } else {
                    System.out.println(ERROR_INVALID_ARGUMENTS +
                            ". Use: mark-done <id>");
                }
                break;

            case "list":
                /*
                Command to list tasks.
                Expects one optional argument for filtering by status.
                If no argument is provided, all tasks will be listed.
                 */
                if (arguments.length == 1) {
                    switch (arguments[0].toLowerCase()) {
                        case "todo":
                            System.out.println("List of tasks marked as " +
                                    "\"To do\":");
                            // TODO: implementation
                            break;
                        case "in-progress":
                            System.out.println("List of tasks marked as " +
                                    "\"In progress\":");
                            // TODO: implementation
                            break;
                        case "done":
                            System.out.println("List of tasks marked as " +
                                    "\"Done\":");
                            // TODO: implementation
                            break;
                        default:
                            System.out.println(ERROR_INVALID_STATUS +
                                    ". Use: list <todo|in-progress|done>");
                            break;
                    }
                } else {
                    System.out.println("List of all tasks:");
                    // TODO: implementation
                }
                break;

            default:
                System.out.println(ERROR_UNKNOWN_COMMAND);
                break;
        }
    }
}
