package huke.command;

import huke.Storage;
import huke.TaskList;
import huke.Ui;
import huke.exception.HukeException;

public abstract class Command {

    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws HukeException;
}
