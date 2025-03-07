package huke.exception;

/**
 * Represents a custom exception class for the Huke task management system.
 * This exception is thrown for various error scenarios like invalid commands, task formatting errors, and task marking errors.
 */
public class HukeException extends Exception {

    /**
     * Constructs a new HukeException with the specified detail message.
     *
     * @param message The detail message of the exception.
     */
    public HukeException(String message) {
        super(message);
    }

    /**
     * Returns an error message for a todo task with an incorrect format.
     *
     * @return The error message for a wrongly formatted todo task.
     */
    public static String todoError() {
        return "Hey, you are trying to add a todo with a wrong format!\n" +
                "Let me give you an example: todo play with Huke ";
    }

    /**
     * Returns an error message for a deadline task with an incorrect format.
     *
     * @return The error message for a wrongly formatted deadline task.
     */
    public static String deadlineError() {
        return "Hey, you are trying to add a deadline with a wrong format!\n" +
                "Let me give you an example: deadline play with Huke /by 15/3/2024 2359";
    }

    /**
     * Returns an error message for an event task with an incorrect format.
     *
     * @return The error message for a wrongly formatted event task.
     */
    public static String eventError() {
        return "Hey, you are trying to add an event with a wrong format!\n" +
                "Let me give you an example: event play with Huke /from 20/6/2025 0930 /to 20/6/2025 1200\n ";
    }

    /**
     * Returns an error message when trying to mark a task that is already marked.
     *
     * @return The error message when the task is already marked as done.
     */
    public static String MarkedError() {
        return "You want to mark the task?\n" +
                "It is marked already! ";
    }

    /**
     * Returns an error message when trying to unmark a task that is already unmarked.
     *
     * @return The error message when the task is already unmarked.
     */
    public static String UnmarkedError() {
        return "You want to unmark the task?\n" +
                "It is unmarked already! ";
    }

    /**
     * Returns an error message when an unknown command is provided by the user.
     *
     * @return The error message for an unknown command.
     */
    public static String unknownCommandError() {
        return "Not sure what you want.\n" +
                "Try start with: list | todo | event | deadline | mark | unmark | bye ";
    }

    /**
     * Returns an error message when an invalid task ID is provided.
     *
     * @param totalNumber The total number of tasks available.
     * @return The error message indicating that the task number is invalid.
     */
    public static String invalidIDError(int totalNumber) {
        return "Did you give me the wrong task number? \n" +
                "Let me give you a hint: \n" +
                "The task number should be an integer from 1 to " +
                (totalNumber - 1);
    }

    /**
     * Returns a generic error message for unknown errors.
     *
     * @return The error message for an unknown error.
     */
    public static String unknownError() {
        return "Looks like there's an error!";
    }

    /**
     * Returns an error message when the user provides an invalid search query for finding tasks.
     *
     * @return The error message for an invalid find query.
     */
    public static String findError() {
        return "You are looking for... what?\n" +
                "I don't understand! Keep to the format pls~";
    }
}
