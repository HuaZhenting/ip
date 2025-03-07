package huke.command;

import huke.exception.HukeException;
import huke.Storage;
import huke.Ui;
import huke.task.TaskList;

/**
 * Represents a command to delete a task from the task list.
 * This class handles the logic of deleting a task based on the provided task index.
 */
public class DeleteCommand extends Command {
    private final int index;

    /**
     * Constructs a DeleteCommand instance with the task index.
     * The task index is parsed from the given string.
     *
     * @param indexStr The string representation of the task index to delete.
     * @throws HukeException If the index is not a valid integer or if any other error occurs.
     */
    public DeleteCommand(String indexStr) throws HukeException {
        try {
            this.index = Integer.parseInt(indexStr);
        } catch (NumberFormatException e) {
            throw new HukeException(HukeException.unknownError());
        }
    }

    /**
     * Executes the delete command by removing the task at the specified index from the task list.
     *
     * @param tasks   The TaskList containing all tasks.
     * @param ui      The Ui instance for displaying feedback to the user.
     * @param storage The Storage instance for saving the updated task list.
     * @throws HukeException If the index is out of range or if the task deletion fails.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws HukeException {
        tasks.deleteTask(index);
    }
}
