package huke;

import java.util.List;
import java.util.Scanner;

import huke.task.Task;

/**
 * Represents the user interface that interacts with the user.
 * This class handles displaying messages, reading user input,
 * and showing task-related information to the user.
 */
public class Ui {
    private static final String dottedLine = "  ------------------------------";
    private final Scanner scanner;

    /**
     * Constructs a new Ui object with a Scanner for reading user input.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Reads a user command from the console input.
     *
     * @return The trimmed user command string.
     */
    public String readUserCommand() {
        if (scanner.hasNextLine()) {
            return scanner.nextLine().trim();
        } else {
            return "";
        }
    }

    /**
     * Prints a welcome message to the user.
     */
    public void printWelcome() {
        System.out.println("Hello! I'm Huke!");
    }

    /**
     * Prints an exit message when the user exits the program.
     */
    public void printExit() {
        System.out.println("Bye! Hope to see you again!");
    }

    /**
     * Prints an error message.
     *
     * @param Message The error message to be displayed.
     */
    public void printError(String Message) {
        System.out.println(Message);
    }

    /**
     * Prints the list of tasks to the console.
     *
     * @param tasks The list of tasks to display.
     */
    public void printList(List<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("No tasks at the moment. Have fun!");
        } else {
            System.out.println("This is your list: ");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println("  " + (i + 1) + " " + tasks.get(i));
            }
        }
    }

    /**
     * Prints a message confirming the addition of a new task.
     *
     * @param task The task that was added.
     * @param totalTasks The total number of tasks after addition.
     */
    public void printAdd(Task task, int totalTasks) {
        System.out.println("Great! I've added this task for you:");
        System.out.println("  " + task);
    }

    /**
     * Prints a message confirming that a task has been marked as done.
     *
     * @param task The task that was marked.
     */
    public void printMark(Task task) {
        System.out.println("I've helped you marked the task:");
        System.out.println("  " + task);
    }

    /**
     * Prints a message confirming that a task has been unmarked.
     *
     * @param task The task that was unmarked.
     */
    public void printUnmark(Task task) {
        System.out.println("I've helped you unmarked the task:");
        System.out.println("  " + task);
    }

    /**
     * Prints a message confirming the deletion of a task.
     *
     * @param task The task that was deleted.
     * @param totalTasks The total number of tasks after deletion.
     */
    public void printDelete(Task task, int totalTasks) {
        System.out.println("Finished a task? Great!");
        System.out.println("I've helped you remove it from your list:");
        System.out.println("  " + task);
    }

    /**
     * Displays the result of a search for tasks matching a keyword.
     *
     * @param tasks The list of tasks that match the search criteria.
     */
    public void showFindResult(List<Task> tasks) {
        if (tasks.size() == 0) {
            System.out.println("Nothing here!");
        } else {
            System.out.println("Here's what I found:");
            for (Task task : tasks) {
                System.out.println("    " + task);
            }
        }
    }

    /**
     * Closes the scanner used for reading user input.
     */
    public void closeScanner() {
        scanner.close();
    }
}
