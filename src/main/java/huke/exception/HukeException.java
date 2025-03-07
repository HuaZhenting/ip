package huke.exception;

public class HukeException extends Exception {

    public HukeException(String message) {
        super(message);
    }

    public static String todoError() {
        return "Hey, you are trying to add a todo with a wrong format!\n" +
                "Let me give you an example: todo play with Huke ";
    }

    public static String deadlineError() {
        return "Hey, you are trying to add a deadline with a wrong format!\n" +
                "Let me give you an example: deadline play with Huke /by 15/3/2024 2359";
    }

    public static String eventError() {
        return "Hey, you are trying to add an event with a wrong format!\n" +
                "Let me give you an example: event play with Huke /from 20/6/2025 0930 /to 20/6/2025 1200\n ";
    }
    public static String MarkedError() {
        return "You want to mark the task?\n" +
                "It is marked already! ";
    }

    public static String UnmarkedError() {
        return "You want to unmark the task?\n" +
                "It is unmarked already! ";
    }

    public static String unknownCommandError() {
        return "Not sure what you want.\n" +
                "Try start with: list | todo | event | deadline | mark | unmark | bye ";
    }

    public static String invalidIDError(int totalNumber) {
        return "Did you give me the wrong task number? \n" +
                "Let me give you a hint: \n" +
                "The task number should be an integer from 1 to " +
                (totalNumber - 1);
    }

    public static String unknownError() {
        return "Looks like there's an error!";
    }

    public static String findError() {
        return "You are looking for... what?\n" +
                "I don't understand! Keep to the format pls~";
    }
}