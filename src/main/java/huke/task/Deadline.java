package huke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import huke.exception.HukeException;

public class Deadline extends Task {
    protected LocalDateTime deadline;
    private String taskForm = "D";

    public Deadline(String description, String deadlineString) throws HukeException {
        super(description);
        setDeadline(deadlineString);
    }

    public Deadline(String description, boolean isDone, String deadlineString) throws HukeException {
        super(description, isDone);
        setDeadline(deadlineString);
    }

    public void setDeadline(String deadlineString) throws HukeException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
            this.deadline = LocalDateTime.parse(deadlineString, formatter);
        } catch (DateTimeParseException e) {
            throw new HukeException(HukeException.deadlineError());
        }
    }

    public String toFileFormat() {
        String deadlineTimeString = deadline.format(DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
        return taskForm + " | " + (isDone ? "1" : "0") + " | " + description + " | " + deadlineTimeString;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadline.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")) + ")";
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }
}