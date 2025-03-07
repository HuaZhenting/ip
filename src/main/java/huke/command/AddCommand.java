package huke.command;

import huke.Ui;
import huke.exception.HukeException;
import huke.Storage;
import huke.task.Task;
import huke.task.TaskList;

public class AddCommand extends Command {
    private final Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws HukeException {
        tasks.addTask(task);
    }
}