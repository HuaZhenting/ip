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
        }
    }

    private static void unmarkTask(String command, int taskCount, Task[] tasks) {
        int position = Integer.parseInt(command.substring(7)) - 1;
        if (position >= taskCount) {
            System.out.println("Task does not exist");
        } else if (tasks[position].getStatusIcon().equals(" ")) {
            System.out.println("Task already marked not done");
        } else {
            tasks[position].setNotDone();
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println(tasks[position]);
        }
    }

    private static void markTask(String command, int taskCount, Task[] tasks) {
        int position = Integer.parseInt(command.substring(5)) - 1;
        if (position >= taskCount) {
            System.out.println("Task does not exist");
        } else if (tasks[position].getStatusIcon().equals("X")) {
            System.out.println("Task already marked done");
        } else {
            tasks[position].setDone();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(tasks[position]);
        }
    }

    private static int addTask(Task[] tasks, int taskCount, String command) {
        if (command.startsWith("todo")) {
            tasks[taskCount] = new Todo(command.substring(5));
        } else if (command.startsWith("deadline")) {
            String[] deadline = command.substring(9).split(" /by ");
            tasks[taskCount] = new Deadline(deadline[0], deadline[1]);
        } else if (command.startsWith("event")) {
            String[] event = command.substring(6).split(" /from ");
            String[] eventTime = event[1].split(" /to ");
            tasks[taskCount] = new Event(event[0], eventTime[0], eventTime[1]);
        } else {
            System.out.println("Add failed. Please specify a type.");
            return taskCount;
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
