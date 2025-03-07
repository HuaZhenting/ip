package huke.task;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
        this.taskForm = "T";
    }

    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String toFileFormat() {
        return this.taskForm + super.toFileFormat();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}