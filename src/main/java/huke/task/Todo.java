package huke.task;

/**
 * Represents a to-do task in the Huke task management system.
 * A to-do task is a simple task that needs to be completed, with a description and a completion status.
 */
public class Todo extends Task {

    /**
     * Constructs a new to-do task with the specified description.
     * The task is initially marked as not done.
     *
     * @param description The description of the to-do task.
     */
    public Todo(String description) {
        super(description);
        this.taskForm = "T";  // Task identifier for a to-do task
    }

    /**
     * Constructs a new to-do task with the specified description and completion status.
     *
     * @param description The description of the to-do task.
     * @param isDone The completion status of the task. True if the task is done, false otherwise.
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Returns a string representation of the to-do task in a format suitable for saving to a file.
     * This method appends the task's form identifier (T) to the result of the {@link Task#toFileFormat()} method.
     *
     * @return A string representation of the to-do task for file storage.
     */
    @Override
    public String toFileFormat() {
        return this.taskForm + super.toFileFormat();  // Add "T" to the task's file format
    }

    /**
     * Returns a string representation of the to-do task in a human-readable format.
     * This method adds a "[T]" prefix to the result of the {@link Task#toString()} method.
     *
     * @return A string representation of the to-do task for display to the user.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();  // Add "[T]" to the task's string representation
    }
}
