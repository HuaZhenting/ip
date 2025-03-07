package huke;

import huke.exception.HukeException;
import huke.task.Deadline;
import huke.task.Event;
import huke.task.Task;
import huke.task.Todo;

import java.util.Scanner;
import java.util.ArrayList;

public class Huke {
    private static ArrayList<Task> tasks;
    private Ui ui;

    public static void main(String[] args) {
        Ui ui = new Ui();
        ui.printWelcome();
        tasks = Storage.loadTasks();
        Scanner in = new Scanner(System.in);
        while (true) {
            try {
                String command = in.nextLine();
                if (command.equals("bye")) {
                    ui.printExit();
                    Storage.saveTasks(tasks);
                    System.exit(0);
                } else if (command.equals("list")){
                    printTasks();
                } else if (command.startsWith("mark")){
                    markTask(command);
                } else if (command.startsWith("delete")){
                    deleteTask(command);
                } else if (command.startsWith("unmark")){
                    unmarkTask(command);
                } else {
                    addTask(command);
                }
                Storage.saveTasks(tasks);
            } catch (HukeException e) {HukeException.DeadlineFormatMessage()}

        }
    }

    private static void deleteTask(String command) throws IndexOutOfBoundsException {
        int position = Integer.parseInt(command.substring(7)) - 1;
        if (position >= tasks.size() || position < 0) {
            throw new IndexOutOfBoundsException();
        } else {
            System.out.println("OK, I've deleted this task for you:");
            System.out.println(tasks.remove(position));
        }
        Storage.saveTasks(tasks);
    }

    private static void unmarkTask(String command) throws HukeException, IndexOutOfBoundsException {
        int position = Integer.parseInt(command.substring(7)) - 1;
        if (position >= tasks.size() || position < 0) {
            throw new IndexOutOfBoundsException();
        } else if (tasks.get(position).getStatusIcon().equals(" ")) {
            throw new HukeException();
        } else {
            tasks.get(position).setNotDone();
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println(tasks.get(position));
        }
        Storage.saveTasks(tasks);
    }

    private static void markTask(String command) throws HukeException, IndexOutOfBoundsException{
        int position = Integer.parseInt(command.substring(5)) - 1;
        if (position >= tasks.size()|| (position < 0)) {
            throw new IndexOutOfBoundsException();
        } else if (tasks.get(position).getStatusIcon().equals("X")) {
            throw new HukeException();
        } else {
            tasks.get(position).setDone();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(tasks.get(position));
        }
        Storage.saveTasks(tasks);
    }

    private static void addTask (String command) throws HukeException {
        Task newTask;
        if (command.startsWith("todo")) {
            String taskDescription = command.substring(4).trim();
            if (taskDescription.isEmpty()) {
                throw new HukeException();
            }
            newTask = new Todo(taskDescription);
        } else if (command.startsWith("deadline")) {
            String taskDetails = command.substring(8).trim();
            if (taskDetails.isEmpty() || !taskDetails.contains(" /by ")) {
                throw new HukeException();
            }
            String[] deadline = taskDetails.split(" /by ");
            if (deadline.length != 2 || deadline[0].trim().isEmpty() || deadline[1].trim().isEmpty()) {
                throw new HukeException();
            }
            newTask = new Deadline(deadline[0].trim(), deadline[1].trim());
        } else if (command.startsWith("event")) {
            String taskDetails = command.substring(5).trim();
            if (taskDetails.isEmpty() || !taskDetails.contains(" /from ") || !taskDetails.contains(" /to ")) {
                throw new HukeException();
            }
            String[] event = taskDetails.split(" /from ", 2);
            if (event.length != 2 || event[0].trim().isEmpty()) {
                throw new HukeException();
            }
            String[] eventTime = event[1].split(" /to ", 2);
            if (eventTime.length != 2 || eventTime[0].trim().isEmpty() || eventTime[1].trim().isEmpty()) {
                throw new HukeException();
            }
            newTask = new Event(event[0].trim(), eventTime[0].trim(), eventTime[1].trim());
        } else {
            throw new HukeException();
        }
        tasks.add(newTask);
        System.out.println("added: " + command);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        Storage.saveTasks(tasks);
    }

    private static void printTasks() {
        if (tasks.isEmpty()) {
            System.out.println("Now you have an empty list!");
            return;
        }
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i));
        }
    }
}
