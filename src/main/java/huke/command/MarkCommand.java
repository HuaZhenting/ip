package huke.command;

import huke.Ui;
import huke.exception.HukeException;
import huke.Storage;
import huke.task.TaskList;

/**
 * Represents a command that marks a task in the Huke task management system.
 * This class is responsible for executing the mark operation on a specific task.
 */
public class MarkCommand extends Command {
    private final String[] parts;

    /**
     * Constructs a MarkCommand with the given command parts.
     *
     * @param parts   The command parts, where the second element is the task number.
     */
    public MarkCommand(String[] parts) {
        this.parts = parts;
    }

    /**
     * Executes the mark command by marking the task at the specified index.
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
            tasks.markTask(Integer.parseInt(parts[1]));
        } catch (NumberFormatException e) {
            ui.printError("Invalid task number format. Please enter a valid number.");
        } catch (HukeException e) {
            ui.printError(e.getMessage());
        }
    }
}
