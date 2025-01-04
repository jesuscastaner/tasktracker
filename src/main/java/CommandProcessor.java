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
                            taskRepository.listTasks(TaskStatus.TODO);
                            break;
                        case "in-progress":
                            taskRepository.listTasks(TaskStatus.IN_PROGRESS);
                            break;
                        case "done":
                            taskRepository.listTasks(TaskStatus.DONE);
                            break;
                        default:
                            System.out.println(ERROR_INVALID_STATUS +
                                    ". Use: list <todo|in-progress|done>");
                            break;
                    }
                } else {
                    taskRepository.listTasks();
                }
                break;

            case "help":
                /*
                Command to display help information.
                Provides a summary of all available commands and their usage.
                 */
                System.out.println("Available commands:");
                System.out.println("  add \"<description>\"          " +
                        "- Add a new task with given description.");
                System.out.println("  update <id> \"<description>\"  " +
                        "- Update description of task with given ID.");
                System.out.println("  delete <id>                  " +
                        "- Delete task with given ID.");
                System.out.println("  mark-todo <id>               " +
                        "- Mark task with given ID as \"TODO\".");
                System.out.println("  mark-in-progress <id>        " +
                        "- Mark task with given ID as \"IN_PROGRESS\".");
                System.out.println("  mark-done <id>               " +
                        "- Mark task with given ID as \"DONE\".");
                System.out.println("  list                         " +
                        "- List all tasks.");
                System.out.println("  list <todo|in-progress|done> " +
                        "- List tasks filtered by status.");
                System.out.println("  help                         " +
                        "- Display this help message.");
                break;
            default:
                System.out.println(ERROR_UNKNOWN_COMMAND);
                break;
        }
    }
}
