package huke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import huke.exception.HukeException;

/**
 * Represents a deadline task in the Huke task management system.
 * A deadline task has a description, a status (done or not), and a deadline.
 */
public class Deadline extends Task {
    protected LocalDateTime deadline;
    private String taskForm = "D";

    /**
     * Constructs a Deadline with the specified description and deadline.
     *
     * @param description The description of the deadline task.
     * @param deadlineString The deadline of the task as a string in the format "d/M/yyyy HHmm".
     * @throws HukeException If there is an error parsing the deadline string.
     */
    public Deadline(String description, String deadlineString) throws HukeException {
        super(description);
        setDeadline(deadlineString);
    }

    /**
     * Constructs a Deadline with the specified description, status, and deadline.
     *
     * @param description The description of the deadline task.
     * @param isDone The status of the task indicating whether it is done.
     * @param deadlineString The deadline of the task as a string in the format "d/M/yyyy HHmm".
     * @throws HukeException If there is an error parsing the deadline string.
     */
    public Deadline(String description, boolean isDone, String deadlineString) throws HukeException {
        super(description, isDone);
        setDeadline(deadlineString);
    }

    /**
     * Sets the deadline of the task from a string representation.
     *
     * @param deadlineString The deadline as a string in the format "d/M/yyyy HHmm".
     * @throws HukeException If the deadline string cannot be parsed.
     */
    public void setDeadline(String deadlineString) throws HukeException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
            this.deadline = LocalDateTime.parse(deadlineString, formatter);
        } catch (DateTimeParseException e) {
            throw new HukeException(HukeException.deadlineError());
        }
    }

    /**
     * Returns a formatted string representation of the deadline task for saving to a file.
     * The format includes the task form, done status, description, and deadline.
     *
     * @return The formatted string for saving to a file.
     */
    public String toFileFormat() {
        String deadlineTimeString = deadline.format(DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
        return taskForm + " | " + (isDone ? "1" : "0") + " | " + description + " | " + deadlineTimeString;
    }

    /**
     * Returns the string representation of the deadline task.
     * Includes the task's status icon, description, and formatted deadline.
     *
     * @return The string representation of the deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadline.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")) + ")";
    }

    /**
     * Returns the deadline of the task.
     *
     * @return The deadline as a LocalDateTime object.
     */
    public LocalDateTime getDeadline() {
        return deadline;
    }
}
