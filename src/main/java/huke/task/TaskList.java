package huke.task;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import huke.Storage;
import huke.Ui;
import huke.exception.HukeException;

/**
 * Represents a list of tasks in the Huke task management system.
 * Provides functionality to add, mark, unmark, delete, and find tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;
    private Ui ui;

    /**
     * Constructs an empty task list with the specified user interface.
     *
     * @param ui The user interface used to display messages to the user.
     */
    public TaskList(Ui ui) {
        this.tasks = new ArrayList<>();
        this.ui = ui;
    }

    /**
     * Constructs a task list with the given list of tasks and the specified user interface.
     *
     * @param tasks The list of tasks to initialize the task list with.
     * @param ui The user interface used to display messages to the user.
     */
    public TaskList(ArrayList<Task> tasks, Ui ui) {
        this.tasks = tasks;
        this.ui = ui;
    }

    /**
     * Displays the list of tasks to the user.
     */
    public void listTasks() {
        ui.printList(tasks);
    }

    /**
     * Adds a task to the list and saves the updated task list to the storage.
     *
     * @param task The task to be added.
     * @throws HukeException If the task is null or an unknown error occurs.
     */
    public void addTask(Task task) throws HukeException {
        if (task == null) {
            throw new HukeException(HukeException.unknownError());
        }
        tasks.add(task);
        ui.printAdd(task, tasks.size());
        Storage.saveTask(tasks);
    }

    /**
     * Marks a task as done based on the given index and saves the updated task list to the storage.
     *
     * @param index The index of the task to be marked as done.
     * @throws HukeException If the index is invalid or the task is already marked as done.
     */
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

    /**
     * Unmarks a task as undone based on the given index and saves the updated task list to the storage.
     *
     * @param index The index of the task to be unmarked as undone.
     * @throws HukeException If the index is invalid or the task is already marked as undone.
     */
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

    /**
     * Deletes a task from the list based on the given index and saves the updated task list to the storage.
     *
     * @param index The index of the task to be deleted.
     * @throws HukeException If the index is invalid.
     */
    public void deleteTask(int index) throws HukeException {
        if (index < 1 || index > tasks.size()) {
            throw new HukeException(HukeException.invalidIDError(tasks.size()));
        }
        Task task = tasks.get(index - 1);
        tasks.remove(index - 1);
        ui.printDelete(task, tasks.size());
        Storage.saveTask(tasks);
    }

    /**
     * Finds tasks that contain the given keyword in their descriptions and displays them to the user.
     *
     * @param keyword The keyword to search for in the task descriptions.
     */
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
