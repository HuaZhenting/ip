package huke.command;

import huke.Storage;
import huke.Ui;
import huke.task.TaskList;

public class FindCommand extends Command {
    private final String description;

    public FindCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.findTask(description);
    }
}