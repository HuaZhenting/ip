package huke.command;

import huke.Ui;
import huke.exception.HukeException;
import huke.Storage;
import huke.task.Task;
import huke.task.TaskList;

/**
 * Represents a command to add a new task to the task list.
 * This command takes a task and adds it to the provided task list.
 */
public class AddCommand extends Command {
    private final Task task;

    /**
     * Constructs an AddCommand with the specified task.
     *
     * @param task The task to be added to the task list.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Executes the command to add the task to the task list.
     * This method adds the provided task to the TaskList and saves the updated task list using the Storage instance.
     *
     * @param tasks   The TaskList to which the task will be added.
     * @param ui      The Ui instance to provide feedback to the user.
     * @param storage The Storage instance to save the updated task list.
     * @throws HukeException If an error occurs during the task addition.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws HukeException {
        tasks.addTask(task);
    }
}
