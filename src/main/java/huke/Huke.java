package huke;

import huke.exception.MarkedException;
import huke.exception.TaskNotSpecifiedException;
import huke.exception.UnmarkedException;
import huke.exception.WrongFormatException;
import huke.task.Deadline;
import huke.task.Event;
import huke.task.Task;
import huke.task.Todo;

import java.util.Scanner;
import java.util.ArrayList;

public class Huke {
    private static ArrayList<Task> tasks = new ArrayList<>();

    public static void exit() {
        System.out.println(" Bye. Hope to see you again soon!");
        System.exit(0);
    }
    
    public static void main(String[] args) {
        System.out.println(" Hello! I'm huke.huke\n" + " What can I do for you?");
        Scanner in = new Scanner(System.in);
        while (true) {
            try {
                String command = in.nextLine();
                if (command.equals("bye")) {
                    exit();
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
            } catch (TaskNotSpecifiedException e) {
                System.out.println("Not sure what you want, please adhere to the format");;
            } catch (MarkedException e) {
                System.out.println("This huke.task.Task is already marked as Done!");;
            } catch (UnmarkedException e) {
                System.out.println("This Task is not done yet TAT");;
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Hummm, looks like you are referring to a non-existing task..");
            } catch (WrongFormatException e) {
                System.out.println("Please adhere to the format");
            }
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
    }

    private static void unmarkTask(String command) throws UnmarkedException, IndexOutOfBoundsException {
        int position = Integer.parseInt(command.substring(7)) - 1;
        if (position >= tasks.size() || position < 0) {
            throw new IndexOutOfBoundsException();
        } else if (tasks.get(position).getStatusIcon().equals(" ")) {
            throw new UnmarkedException();
        } else {
            tasks.get(position).setNotDone();
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println(tasks.get(position));
        }
    }

    private static void markTask(String command) throws MarkedException, IndexOutOfBoundsException{
        int position = Integer.parseInt(command.substring(5)) - 1;
        if (position >= tasks.size()|| (position < 0)) {
            throw new IndexOutOfBoundsException();
        } else if (tasks.get(position).getStatusIcon().equals("X")) {
            throw new MarkedException();
        } else {
            tasks.get(position).setDone();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(tasks.get(position));
        }
    }

    private static void addTask (String command) throws WrongFormatException, TaskNotSpecifiedException {
        Task newTask;
        if (command.startsWith("todo")) {
            String taskDescription = command.substring(4).trim();
            if (taskDescription.isEmpty()) {
                throw new TaskNotSpecifiedException();
            }
            newTask = new Todo(taskDescription);
        } else if (command.startsWith("deadline")) {
            String taskDetails = command.substring(8).trim();
            if (taskDetails.isEmpty() || !taskDetails.contains(" /by ")) {
                throw new WrongFormatException();
            }
            String[] deadline = taskDetails.split(" /by ");
            if (deadline.length != 2 || deadline[0].trim().isEmpty() || deadline[1].trim().isEmpty()) {
                throw new WrongFormatException();
            }
            newTask = new Deadline(deadline[0].trim(), deadline[1].trim());
        } else if (command.startsWith("event")) {
            String taskDetails = command.substring(5).trim();
            if (taskDetails.isEmpty() || !taskDetails.contains(" /from ") || !taskDetails.contains(" /to ")) {
                throw new WrongFormatException();
            }
            String[] event = taskDetails.split(" /from ", 2);
            if (event.length != 2 || event[0].trim().isEmpty()) {
                throw new WrongFormatException();
            }
            String[] eventTime = event[1].split(" /to ", 2);
            if (eventTime.length != 2 || eventTime[0].trim().isEmpty() || eventTime[1].trim().isEmpty()) {
                throw new WrongFormatException();
            }
            newTask = new Event(event[0].trim(), eventTime[0].trim(), eventTime[1].trim());
        } else {
            throw new TaskNotSpecifiedException();
        }
        tasks.add(newTask);
        System.out.println("added: " + command);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
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
