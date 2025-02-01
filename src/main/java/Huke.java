import java.util.Scanner;

public class Huke {

    public static void echo(String inputCommand) {
        System.out.println(inputCommand);
    }

    public static void exit() {
        System.out.println(" Bye. Hope to see you again soon!");
        System.exit(0);
    }
    public static void main(String[] args) {
        System.out.println(" Hello! I'm Huke\n" + " What can I do for you?");
        Scanner in = new Scanner(System.in);
        while (true) {
            String command = in.nextLine();
            if (command.equals("bye")) {
                exit();
            } else {
                echo(command);
            }
        }
    }
}
