package huke.command;

import huke.Storage;
import huke.Ui;
import huke.task.TaskList;

/**
 * Represents a command that exits the Huke task management system.
 * This class is responsible for printing the exit message and terminating the program.
 */
public class ExitCommand extends Command {

    /**
     * Executes the exit command by printing the exit message.
     * This method is responsible for displaying the goodbye message and signaling the system to terminate.
     *
     * @param tasks   The TaskList containing all tasks (not used in this command).
     * @param ui      The Ui instance for printing the exit message.
     * @param storage The Storage instance for handling file operations (not used in this command).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printExit();
    }

    /**
     * Returns whether the program should exit.
     *
     * @return true to indicate the program should exit.
     */
    public boolean isExit() {
        return true;
    }
}
