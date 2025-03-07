package huke.command;

import huke.Storage;
import huke.Ui;
import huke.task.TaskList;

public class ExitCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printExit();
    }

    public boolean isExit() {
        return true;
    }
}