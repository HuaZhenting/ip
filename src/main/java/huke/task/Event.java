package huke.task;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import huke.exception.HukeException;

public class Event extends Task {
    protected LocalDateTime startTime;
    protected LocalDateTime endTime;
    private String taskForm = "E";

    public Event(String description, String startTimeString, String endTimeString) throws HukeException {
        super(description);
        setStartTime(startTimeString);
        setEndTime(endTimeString);
    }

    public Event(String description, boolean isDone, String startTimeString, String endTimeString) throws HukeException {
        super(description, isDone);
        setStartTime(startTimeString);
        setEndTime(endTimeString);
    }

    public void setStartTime(String startTimeString) throws HukeException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
            this.startTime = LocalDateTime.parse(startTimeString, formatter);
        } catch (DateTimeException e) {
            throw new HukeException(HukeException.eventError());
        }
    }

    public void setEndTime(String endTimeString) throws HukeException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
            this.endTime = LocalDateTime.parse(endTimeString, formatter);
        } catch (DateTimeException e) {
            throw new HukeException(HukeException.eventError());
        }
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public String toFileFormat() {
        String startTimeString = startTime.format(DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
        String endTimeString = endTime.format(DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));

        return taskForm + " | " + (isDone ? "1" : "0") + " | " + description + " | " + startTimeString + " | " + endTimeString;
    }

    @Override
    public String toString() {
        return "[" + taskForm + "]" + super.toString() + " (from: " + startTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")) +
                " to: " + endTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")) + ")";
    }
}