package huke.exception;

public class HukeException extends Exception {

    public HukeException(String message) {
        super(message);
    }

    public static String todoFormatMessage() {
        return " Hey, you are trying to add a todo with a wrong format!\n" +
                " Let me give you an example: todo praise Huke ";
    }

    public static String DeadlineFormatMessage() {
        return " Hey, you are trying to add a deadline with a wrong format!\n" +
                " Let me give you an example: deadline play with Huke /by today ";
    }

    public static String EventFormatMessage() {
        return " Hey, you are trying to add an event with a wrong format!\n" +
                " Let me give you an example: event play with Huke /from today /to forever ";
    }

    public static String markingMessage() {
        return " You want to mark or unmark a task?\n " +
                " Let me give you an example: mark 1";
    }

    public static String markedMessage() {
        return " You want to mark the task?\n " +
                " It is marked already! ";
    }

    public static String unmarkedMessage() {
        return " You want to unmark the task?\n " +
                " It is unmarked already! ";
    }

    public static String invalidIDMessage(int totalNumber) {
        return " Did you give me the wrong task number? \n " +
                " Let me give you a hint: \n " +
                " The task number should be an integer from 1 to " +
                (totalNumber - 1);
    }

    public static String unknownCommandMessage() {
        return " Not sure what you want.\n" +
                " Try start with: list | todo | event | deadline | mark | unmark | bye ";
    }

    public static String unknownErrorMessage() {
        return " Looks like there's an error!";
    }
}
