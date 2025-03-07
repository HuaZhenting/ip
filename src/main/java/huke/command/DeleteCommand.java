package huke.command;

import huke.exception.HukeException;
import huke.Storage;
import huke.Ui;
import huke.task.TaskList;

public class DeleteCommand extends Command {
    private final int index;

    public DeleteCommand(String indexStr) throws HukeException {
        try {
            this.index = Integer.parseInt(indexStr);
        } catch (NumberFormatException e) {
            throw new HukeException(HukeException.unknownError());
        }
    }

    @Override
    public void execute(TaskList TaskList, Ui ui, Storage storage) throws HukeException {
        TaskList.deleteTask(index);
    }
}