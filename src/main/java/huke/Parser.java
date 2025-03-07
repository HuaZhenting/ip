package huke;

import huke.exception.HukeException;
import huke.task.*;
import huke.command.*;

import java.time.format.DateTimeParseException;

/**
 * Parses user input to determine the appropriate command to execute.
 * The class is responsible for converting user commands into Command objects that the program can process.
 */
public class Parser {

    private final TaskList TaskList;
    private final Ui ui;
    private final Storage storage;

    /**
     * Constructs a Parser object with the specified TaskList, Ui, and Storage objects.
     *
     * @param TaskList The list of tasks that the user can interact with.
     * @param ui The Ui object for displaying information to the user.
     * @param storage The Storage object to handle saving/loading tasks.
     */
    public Parser(TaskList TaskList, Ui ui, Storage storage) {
        this.TaskList = TaskList;
        this.ui = ui;
        this.storage = storage;
    }

    /**
     * Parses the input command from the user.
     * It splits the input into the command type and its description (if any),
     * and returns the corresponding Command object for execution.
     *
     * @param input The user input string representing the command.
     * @return The Command object that corresponds to the user input.
     * @throws HukeException If the command is unknown or the input is invalid.
     */
    public Command parse(String input) throws HukeException {
        if (input.isEmpty()) {
            return null;
        }
        String[] parts = input.split(" ", 2);
        String command = parts[0].toLowerCase();
        String description = null;
        if (parts.length > 1) {
            description = parts[1];
        }
        Command cmd = getCommand(command, parts, description, input);
        if (cmd == null) {
            throw new HukeException(HukeException.unknownCommandError());
        }

        return cmd;
    }

    /**
     * Determines which command to create based on the input command type.
     *
     * @param command The command type (e.g., "todo", "deadline", etc.).
     * @param parts The parts of the user input split by space.
     * @param description The description or details provided by the user.
     * @param input The complete user input string.
     * @return The appropriate Command object corresponding to the user input.
     * @throws HukeException If there is an error in parsing the input or the command is invalid.
     */
    private Command getCommand(String command, String[] parts, String description, String input) throws HukeException {
        switch (command) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        case "todo":
            if (description == null) {
                throw new HukeException(HukeException.todoError());
            }
            return new AddCommand(new Todo(description));
        case "deadline":
            if (!input.toLowerCase().contains("/by ")) {
                throw new HukeException(HukeException.deadlineError());
            }
            try {
                String[] partDeadline = description.split("/by ", 2);
                return new AddCommand(new Deadline(partDeadline[0].trim(), partDeadline[1].trim()));
            } catch (DateTimeParseException e) {
                throw new HukeException(HukeException.deadlineError());
            }
        case "event":
            if (!input.toLowerCase().contains("/from") || !input.toLowerCase().contains("/to")) {
                throw new HukeException(HukeException.eventError());
            }

            String[] partEvent = description.split("/from|/to");
            if (partEvent.length < 3) {
                throw new HukeException(HukeException.eventError());
            }
            try {
                String startTimeString = partEvent[1].trim();
                String endTimeString = partEvent[2].trim();
                return new AddCommand(new Event(partEvent[0].trim(), startTimeString, endTimeString));
            } catch (DateTimeParseException e) {
                throw new HukeException(HukeException.eventError());
            }
        case "mark":
            return new MarkOrUnmarkCommand(parts, command);
        case "unmark":
            return new MarkOrUnmarkCommand(parts, command);
        case "delete":
            if (description == null) {
                throw new HukeException(HukeException.unknownError());
            }
            return new DeleteCommand(description);
        case "find":
            if (description == null) {
                throw new HukeException(HukeException.findError());
            }
            return new FindCommand(description);
        default:
            return null;
        }
    }
}
