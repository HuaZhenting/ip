package huke.task;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void setDone() {
        this.isDone = true;
    }

    public void setNotDone() {
        this.isDone = false;
    }

    public abstract String toFileString();

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + description;
    }

    public boolean contains(String term) {
        return description.toLowerCase().contains(term.toLowerCase());
    }
}

