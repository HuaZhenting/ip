package huke.task;

/**
 * Represents a task in the Huke task management system.
 * A task has a description, a status indicating if it is done,
 * and a task form that defines its type (default is "T" for Todo).
 */
public class Task {
    protected String description;
    protected boolean isDone;
    protected String taskForm = "T";

    /**
     * Constructs a Task with the specified description and sets its status to undone.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructs a Task with the specified description and status.
     *
     * @param description The description of the task.
     * @param isDone The status of the task indicating if it is done.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns the status icon for the task.
     * Returns "X" if the task is done, or " " (a space) if the task is not done.
     *
     * @return The status icon representing the task's done status.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks the task as done.
     *
     * @return The current task marked as done.
     */
    public Task markAsDone() {
        isDone = true;
        return this;
    }

    /**
     * Marks the task as undone.
     *
     * @return The current task marked as undone.
     */
    public Task markAsUndone() {
        this.isDone = false;
        return this;
    }

    /**
     * Returns a formatted string representation of the task for saving to a file.
     * The format includes the task's done status and description.
     *
     * @return The formatted string for saving to a file.
     */
    public String toFileFormat() {
        return  " | " + (isDone ? "1" : "0") + " | " + description;
    }

    /**
     * Returns the description of the task.
     *
     * @return The description of the task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the string representation of the task.
     * Includes the task's status icon and description.
     *
     * @return The string representation of the task.
     */
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
