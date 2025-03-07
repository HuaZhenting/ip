package huke.command;

import huke.exception.HukeException;
import huke.Storage;
import huke.Ui;
import huke.task.TaskList;

/**
 * Abstract class representing a command in the application.
 * All commands in the application should extend this class and implement the execute method.
 */
public abstract class Command {

    /**
     * Determines if the command is an exit command.
     * By default, this method returns false.
     * Subclasses can override this method to indicate whether the command should terminate the application.
     *
     * @return A boolean indicating if the command is an exit command. Default is false.
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the command. This method must be implemented by subclasses to define the specific behavior of the command.
     *
     * @param tasks   The TaskList containing all tasks to be operated on.
     * @param ui      The Ui instance for displaying feedback to the user.
     * @param storage The Storage instance for saving the updated state of tasks.
     * @throws HukeException If any error occurs during the execution of the command.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws HukeException;
}
