import java.util.Scanner;

public class Huke {
    public static void exit() {
        System.out.println(" Bye. Hope to see you again soon!");
        System.exit(0);
    }
    
    public static void main(String[] args) {
        System.out.println(" Hello! I'm Huke\n" + " What can I do for you?");
        Scanner in = new Scanner(System.in);
        Task[] tasks = new Task[100];
        int taskCount = 0;
        while (true) {
            try {
                String command = in.nextLine();
                if (command.equals("bye")) {
                    exit();
                } else if (command.equals("list")){
                    printTasks(taskCount, tasks);
                } else if (command.startsWith("mark")){
                    markTask(command, taskCount, tasks);
                } else if (command.startsWith("unmark")){
                    unmarkTask(command, taskCount, tasks);
                } else {
                    taskCount = addTask(tasks, taskCount, command);
                }
            } catch (TaskNotSpecifiedException e) {
                System.out.println("Not sure what you want, please adhere to the format");;
            } catch (MarkedException e) {
                System.out.println("This Task is already marked as Done!");;
            } catch (UnmarkedException e) {
                System.out.println("This Task is not done yet TAT");;
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Hummm, looks like you are referring to a non-existing task..");
            } catch (WrongFormatException e) {
                System.out.println("Please adhere to the format");
            }
        }
    }

    private static void unmarkTask(String command, int taskCount, Task[] tasks) throws UnmarkedException, IndexOutOfBoundsException {
        int position = Integer.parseInt(command.substring(7)) - 1;
        if (position >= taskCount) {
            throw new IndexOutOfBoundsException();
        } else if (tasks[position].getStatusIcon().equals(" ")) {
            throw new UnmarkedException();
        } else {
            tasks[position].setNotDone();
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println(tasks[position]);
        }
    }

    private static void markTask(String command, int taskCount, Task[] tasks) throws MarkedException, IndexOutOfBoundsException{
        int position = Integer.parseInt(command.substring(5)) - 1;
        if (position >= taskCount) {
            throw new IndexOutOfBoundsException();
        } else if (tasks[position].getStatusIcon().equals("X")) {
            throw new MarkedException();
        } else {
            tasks[position].setDone();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(tasks[position]);
        }
    }

    private static int addTask (Task[] tasks, int taskCount, String command) throws WrongFormatException, TaskNotSpecifiedException {
        if (command.startsWith("todo")) {
            String taskDescription = command.substring(4).trim();
            if (taskDescription.isEmpty()) {
                throw new TaskNotSpecifiedException();
            }
            tasks[taskCount] = new Todo(taskDescription);
        } else if (command.startsWith("deadline")) {
            String taskDetails = command.substring(8).trim();
            if (taskDetails.isEmpty() || !taskDetails.contains(" /by ")) {
                throw new WrongFormatException();
            }
            String[] deadline = taskDetails.split(" /by ");
            if (deadline.length != 2 || deadline[0].trim().isEmpty() || deadline[1].trim().isEmpty()) {
                throw new WrongFormatException();
            }
            tasks[taskCount] = new Deadline(deadline[0].trim(), deadline[1].trim());
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
            tasks[taskCount] = new Event(event[0].trim(), eventTime[0].trim(), eventTime[1].trim());

        } else {
            throw new TaskNotSpecifiedException();
        }
        taskCount += 1;
        System.out.println("added: " + command);
        System.out.println("Now you have " + taskCount + " tasks in the list.");
        return taskCount;
    }

    private static void printTasks(int taskCount, Task[] tasks) {
        for (int i = 0; i < taskCount; i ++) {
            System.out.println((i + 1) + "." + tasks[i]);
        }
    }
}
