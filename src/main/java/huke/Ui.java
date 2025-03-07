package huke;

import huke.task.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private final Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public String readCommand() {
        if (scanner.hasNextLine()) {
            return scanner.nextLine().trim();
        } else {
            return "";
        }
    }

    public static void printWelcome() {
        System.out.println("Hello! I'm huke!");
    }

    public static void printExit() {
        System.out.println("Bye! Hope to see you again!");
    }

    public void printError (String errorMessage) {
        System.out.println(errorMessage);
    }

    public void printList(ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("No tasks at the moment. Have fun!");
        } else {
            System.out.println("This is your list: ");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println("    " + (i + 1) + " " + tasks.get(i));
            }
        }
    }

    public void printAdd(Task task, int totalTasks) {
        System.out.println("Great! I've added this task for you:");
        System.out.println("  " + task);
    }

    public void printMark(Task task) {
        System.out.println("I've helped you marked the task:");
        System.out.println("  " + task);
    }

    public void printUnmark(Task task) {
        System.out.println("I've helped you unmarked the task:");
        System.out.println("  " + task);
    }

    public void printDelete(Task task, int totalTasks) {
        System.out.println("Finished a task? Great!");
        System.out.println("I've helped you remove it from your list:");
        System.out.println("  " + task);
    }
}
