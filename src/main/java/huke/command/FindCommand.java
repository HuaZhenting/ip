package huke.command;

import huke.Storage;
import huke.Ui;
import huke.task.TaskList;

/**
 * Represents a command that finds tasks in the Huke task management system based on a search keyword.
 * This class is responsible for searching and displaying tasks that match the given keyword.
 */
public class FindCommand extends Command {
    private final String description;

    /**
     * Constructs a FindCommand with the specified search keyword.
     *
     * @param description The keyword used to search for matching tasks.
     */
    public FindCommand(String description) {
        this.description = description;
    }

    /**
     * Executes the find command by searching for tasks that contain the specified keyword.
     *
     * <p>This method calls the {@link TaskList#findTask(String)} method to search for tasks that
     * match the given description. It will print the results using the Ui instance.</p>
     *
     * @param tasks   The TaskList containing all the tasks to search through.
     * @param ui      The Ui instance for displaying the search results.
     * @param storage The Storage instance for handling file operations (though not used here).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.findTask(description);
    }
}
