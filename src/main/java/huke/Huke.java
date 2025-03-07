package huke;

import huke.command.Command;
import huke.exception.HukeException;
import huke.task.TaskList;

/**
 * Main class for running the Huke task management application.
 * The class interacts with the user interface, processes user commands,
 * loads and saves tasks from/to a file, and manages the core application logic.
 */
public class Huke {
    private final Ui ui;
    private final Storage storage;
    private TaskList tasks;
    private Parser parser;

    /**
     * Constructs a Huke application with the specified file path for task storage.
     *
     * @param filePath The file path where tasks will be stored and loaded from.
     */
    public Huke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadTask(), ui);
            parser = new Parser(tasks, ui, storage);
        } catch (HukeException e) {
            ui.printError(e.getMessage());
            tasks = new TaskList(ui); // Create an empty task list if loading tasks fails
        }
    }

    /**
     * Starts the Huke application and repeatedly reads user commands until an exit command is issued.
     * The application will process commands, update tasks, and provide feedback to the user.
     */
    public void run() {
        ui.printWelcome();  // Display a welcome message to the user
        boolean isExit = false;
        while (!isExit) {
            try {
                String cmd = ui.readUserCommand();  // Read the user command
                Command c = parser.parse(cmd);  // Parse the command into a corresponding Command object
                if (c == null) {
                    continue;  // Skip empty or invalid commands
                }
                c.execute(tasks, ui, storage);  // Execute the parsed command
                isExit = c.isExit();  // Check if the exit command was issued
            } catch (HukeException e) {
                ui.printError(e.getMessage());  // Display any errors that occur during execution
            }
        }
        ui.closeScanner();  // Close the scanner when exiting the application
    }

    /**
     * Main method to start the Huke application. This initializes the application
     * and runs it by creating a new instance of the Huke class.
     *
     * @param args Command-line arguments (not used in this implementation).
     */
    public static void main(String[] args) {
        new Huke("./data/huke.txt").run();  // Start the Huke application with the specified file path
    }
}
