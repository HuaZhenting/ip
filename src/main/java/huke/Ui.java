package huke;

public class Ui {
    public static void printWelcome() {
        System.out.println("Hello! I'm huke!");
    }

    public static void printExit() {
        System.out.println("Bye! Hope to see you again!");
    }

    public static void printNotSpecified() {
        System.out.println("Not sure what you want, please adhere to the format");
    }

    public static void printMarked() {
        System.out.println("This huke.task.Task is already marked as Done!");
    }

    public static void printUnmarked() {
        System.out.println("This Task is not done yet TAT");;
    }

    public static void printNonExist() {
        System.out.println("Hummm, looks like you are referring to a non-existing task..");
    }

    public static void printWrongFormat() {
        System.out.println("Please adhere to the format");    }
}
