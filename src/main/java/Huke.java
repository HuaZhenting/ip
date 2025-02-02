import java.util.Scanner;

public class Huke {
    public static void exit() {
        System.out.println(" Bye. Hope to see you again soon!");
        System.exit(0);
    }
    
    public static void main(String[] args) {
        System.out.println(" Hello! I'm Huke\n" + " What can I do for you?");
        Scanner in = new Scanner(System.in);
        String[] tasks = new String[100];
        int taskCount = 0;
        while (true) {
            String command = in.nextLine();
            if (command.equals("bye")) {
                exit();
            } else if (command.equals("list")){
                printTasks(taskCount, tasks);
            } else {
                taskCount = addTask(tasks, taskCount, command);
            }
        }
    }

    private static int addTask(String[] tasks, int taskCount, String command) {
        tasks[taskCount] = command;
        taskCount += 1;
        System.out.println("added: " + command);
        return taskCount;
    }

    private static void printTasks(int taskCount, String[] tasks) {
        for (int i = 0; i < taskCount; i ++) {
            System.out.println((i + 1) + ". " + tasks[i]);
        }
    }
}
