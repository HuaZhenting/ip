package huke.command;

import huke.exception.HukeException;
import huke.Storage;
import huke.Ui;
import huke.task.TaskList;

public abstract class Command {
    public boolean isExit() {
        return false;
    }

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws HukeException;
}