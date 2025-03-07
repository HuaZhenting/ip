package huke.task;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import huke.Storage;
import huke.Ui;
import huke.exception.HukeException;

public class TaskList {
    private ArrayList<Task> tasks;
    private Ui ui;

    public TaskList(Ui ui) {
        this.tasks = new ArrayList<>();
        this.ui = ui;
    }

    public TaskList(ArrayList<Task> tasks, Ui ui) {
        this.tasks = tasks;
        this.ui = ui;
    }

    public void listTasks() {
        ui.printList(tasks);
    }

    public void addTask(Task task) throws HukeException {
        if (task == null) {
            throw new HukeException(HukeException.unknownError());
        }
        tasks.add(task);
        ui.printAdd(task, tasks.size());
        Storage.saveTask(tasks);
    }

    public void markTask(int index) throws HukeException {
        if (index < 1 || index > tasks.size()) {
            throw new HukeException(HukeException.invalidIDError(tasks.size()));
        }
        if (tasks.get(index - 1).isDone) {
            throw new HukeException(HukeException.MarkedError());
        }
        Task task = tasks.get(index - 1).markAsDone();
        ui.printMark(task);
        Storage.saveTask(tasks);
    }

    public void unmarkTask(int index) throws HukeException {
        if (index < 1 || index > tasks.size()) {
            throw new HukeException(HukeException.invalidIDError(tasks.size()));
        }
        if (!tasks.get(index - 1).isDone) {
            throw new HukeException(HukeException.UnmarkedError());
        }
        Task task = tasks.get(index - 1).markAsUndone();
        ui.printUnmark(task);
        Storage.saveTask(tasks);
    }

    public void deleteTask(int index) throws HukeException {
        if (index < 1 || index > tasks.size()) {
            throw new HukeException(HukeException.invalidIDError(tasks.size()));
        }
        Task task = tasks.get(index - 1);
        tasks.remove(index - 1);
        ui.printDelete(task, tasks.size());
        Storage.saveTask(tasks);
    }

    public void findTask(String keyword) {
        List<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                matchingTasks.add(task);
            }
        }
        ui.showFindResult(matchingTasks);
    }
}