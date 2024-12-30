/**
 * Serves as the entry point for the application.
 */
public class Main {

    /**
     * Initializes the CommandProcessor instance and passes the command-line
     * arguments to be processed for execution.
     *
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        CommandProcessor commandProcessor = new CommandProcessor();
        commandProcessor.execute(args);
    }
}
