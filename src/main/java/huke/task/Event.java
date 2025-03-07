package huke.task;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import huke.exception.HukeException;

/**
 * Represents an event task in the Huke task management system.
 * An event has a description, status (done or not), a start time, and an end time.
 */
public class Event extends Task {
    protected LocalDateTime startTime;
    protected LocalDateTime endTime;
    private String taskForm = "E";

    /**
     * Constructs an Event with the specified description, start time, and end time.
     *
     * @param description The description of the event.
     * @param startTimeString The start time of the event as a string in the format "d/M/yyyy HHmm".
     * @param endTimeString The end time of the event as a string in the format "d/M/yyyy HHmm".
     * @throws HukeException If there is an error parsing the date-time strings.
     */
    public Event(String description, String startTimeString, String endTimeString) throws HukeException {
        super(description);
        setStartTime(startTimeString);
        setEndTime(endTimeString);
    }

    /**
     * Constructs an Event with the specified description, status, start time, and end time.
     *
     * @param description The description of the event.
     * @param isDone The status of the event indicating whether it is done.
     * @param startTimeString The start time of the event as a string in the format "d/M/yyyy HHmm".
     * @param endTimeString The end time of the event as a string in the format "d/M/yyyy HHmm".
     * @throws HukeException If there is an error parsing the date-time strings.
     */
    public Event(String description, boolean isDone, String startTimeString, String endTimeString) throws HukeException {
        super(description, isDone);
        setStartTime(startTimeString);
        setEndTime(endTimeString);
    }

    /**
     * Sets the start time of the event from a string representation.
     *
     * @param startTimeString The start time as a string in the format "d/M/yyyy HHmm".
     * @throws HukeException If the start time string cannot be parsed.
     */
    public void setStartTime(String startTimeString) throws HukeException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
            this.startTime = LocalDateTime.parse(startTimeString, formatter);
        } catch (DateTimeException e) {
            throw new HukeException(HukeException.eventError());
        }
    }

    /**
     * Sets the end time of the event from a string representation.
     *
     * @param endTimeString The end time as a string in the format "d/M/yyyy HHmm".
     * @throws HukeException If the end time string cannot be parsed.
     */
    public void setEndTime(String endTimeString) throws HukeException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
            this.endTime = LocalDateTime.parse(endTimeString, formatter);
        } catch (DateTimeException e) {
            throw new HukeException(HukeException.eventError());
        }
    }

    /**
     * Returns the start time of the event.
     *
     * @return The start time of the event as a LocalDateTime object.
     */
    public LocalDateTime getStartTime() {
        return startTime;
    }

    /**
     * Returns the end time of the event.
     *
     * @return The end time of the event as a LocalDateTime object.
     */
    public LocalDateTime getEndTime() {
        return endTime;
    }

    /**
     * Returns a formatted string representation of the event for saving to a file.
     * The format includes the task form, done status, description, start time, and end time.
     *
     * @return The formatted string for saving to a file.
     */
    public String toFileFormat() {
        String startTimeString = startTime.format(DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
        String endTimeString = endTime.format(DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));

        return taskForm + " | " + (isDone ? "1" : "0") + " | " + description + " | " + startTimeString + " | " + endTimeString;
    }

    /**
     * Returns the string representation of the event.
     * Includes the task's status icon, description, and formatted start and end times.
     *
     * @return The string representation of the event.
     */
    @Override
    public String toString() {
        return "[" + taskForm + "]" + super.toString() + " (from: " + startTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")) +
                " to: " + endTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")) + ")";
    }
}
