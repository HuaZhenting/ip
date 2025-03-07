package huke.command;

import huke.Storage;
import huke.Ui;
import huke.task.TaskList;

public class ListCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.listTasks();
    }
}