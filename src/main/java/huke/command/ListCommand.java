package huke.command;

import huke.Storage;
import huke.Ui;
import huke.task.TaskList;

/**
 * Represents a command that lists all tasks in the Huke task management system.
 * This class is responsible for displaying all the tasks present in the task list.
 */
public class ListCommand extends Command {

    /**
     * Executes the list command by displaying all tasks in the task list.
     *
     * <p>This method calls the {@link TaskList#listTasks()} method to list all tasks currently
     * stored in the task management system.</p>
     *
     * @param tasks   The TaskList containing all the tasks.
     * @param ui      The Ui instance for printing the task list.
     * @param storage The Storage instance for handling file operations (though not used here).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.listTasks();
    }
}
