package huke.command;

import huke.Ui;
import huke.exception.HukeException;
import huke.Storage;
import huke.task.TaskList;

/**
 * Represents a command that marks or unmarks a task in the Huke task management system.
 * This class is responsible for executing the mark or unmark operation on a specific task.
 */
public class MarkOrUnmarkCommand extends Command {
    private final String command;
    private final String[] parts;

    /**
     * Constructs a MarkOrUnmarkCommand with the given command parts and command type (mark/unmark).
     *
     * @param parts   The command parts, where the second element is the task number.
     * @param command The command type, either "mark" or "unmark".
     */
    public MarkOrUnmarkCommand(String[] parts, String command) {
        this.parts = parts;
        this.command = command;
    }

    /**
     * Executes the mark or unmark command by either marking or unmarking the task at the specified index.
     *
     * <p>If the task number is invalid or there are not enough arguments, an error message is displayed.
     * The task number is expected to be the second element in the parts array.</p>
     *
     * @param tasks   The TaskList containing all the tasks.
     * @param ui      The Ui instance for printing error or success messages.
     * @param storage The Storage instance for handling file operations (though not used here).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage)  {
        try {
            if (parts.length < 2) {
                throw new HukeException(HukeException.unknownCommandError());
            }
            if (command.equals("mark")) {
                tasks.markTask(Integer.parseInt(parts[1]));
            } else {
                tasks.unmarkTask(Integer.parseInt(parts[1]));
            }
        } catch (NumberFormatException e) {
            ui.printError("Invalid task number format. Please enter a valid number.");
        } catch (HukeException e) {
            ui.printError(e.getMessage());
        }
    }
}
