package huke;

import huke.exception.HukeException;
import huke.task.*;
import huke.command.*;

import java.time.format.DateTimeParseException;

public class Parser {

    private final TaskList TaskList;
    private final Ui ui;
    private final Storage storage;

    public Parser(TaskList TaskList, Ui ui, Storage storage) {
        this.TaskList = TaskList;
        this.ui = ui;
        this.storage = storage;
    }

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