package huke;

import huke.command.Command;
import huke.exception.HukeException;
import huke.task.TaskList;

public class Huke {
    private final Ui ui;
    private final Storage storage;
    private TaskList tasks;
    private Parser parser;

    public Huke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadTask(), ui);
            parser = new Parser(tasks, ui, storage);
        } catch (HukeException e) {
            ui.printError(e.getMessage());
            tasks = new TaskList(ui);
        }
    }

    public void run() {
        ui.printWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String cmd = ui.readUserCommand();
                Command c = parser.parse(cmd);
                if (c == null) {
                    continue;
                }
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (HukeException e) {
                ui.printError(e.getMessage());
            }
        }
        ui.closeScanner();
    }

    public static void main(String[] args) {
        new Huke("./data/huke.txt").run();
    }
}