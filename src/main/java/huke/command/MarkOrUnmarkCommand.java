package huke.command;

import huke.Ui;
import huke.exception.HukeException;
import huke.Storage;
import huke.task.TaskList;

public class MarkOrUnmarkCommand extends Command {
    private final String command;
    private final String[] parts;

    public MarkOrUnmarkCommand(String[] parts, String command) {
        this.parts = parts;
        this.command = command;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage)  {
        try {
            if (parts.length < 2) {
                throw new HukeException(HukeException.unknownCommandError());
            }
            if (command.equals("mark")) {
                tasks.markTask(Integer.parseInt(parts[1]));
            } else {
                tasks.unmarkTask(Integer.parseInt(parts[1]));
            }
        } catch (NumberFormatException e) {
            ui.printError("Invalid task number format. Please enter a valid number.");
        } catch (HukeException e) {
            ui.printError(e.getMessage());
        }
    }
}