package se.lexicon;

import java.time.LocalDate;

public class TodoItem {
    private int id;
    private String title; // Not allowed to be null.
    private String taskDescription; // is used to hold further information.
    private LocalDate deadLine; // Not allowed to be null.
    // TodoItem is considered overdue if deadLine current date > deadLine.
    private boolean done; // default value is false.
    private Person creator; // Represents the person who created the TodoItem.
    private static int sequencer = 0;

    // Constructor
    public TodoItem(String title, String taskDescription, Person creator) {
        setId();
        setTitle(title);
        setDescription(taskDescription);
        this.done = false;
        if (creator == null) {
            throw new NullPointerException("creator must not be null");
        }
        this.creator = creator;
    }

    // Helper methods
    private void setId() {
        this.id = sequencer + 1;
        sequencer++;
    }
    private void validateStringInput(String input, String paramName) {
        if (input == null || input.trim().isEmpty()) {
            throw new IllegalArgumentException(paramName + " must not be null or empty");
        }
    }

    // Getters and Setters
    public int getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        validateStringInput(title, "title");
        this.title = title;
    }
    public String getDescription() {
        return taskDescription;
    }
    public void setDescription(String description) {
        validateStringInput(description, "description");
        this.taskDescription = description;
    }
    public LocalDate getDeadLine() {
        return deadLine;
    }
    public void setDeadLine(LocalDate deadLine) {
        if (deadLine == null || deadLine.isBefore(LocalDate.now())) {
            throw new NullPointerException("deadLine must not be null");
        }
        this.deadLine = deadLine;
    }
    public boolean isDone() {
        return done;
    }
    public void setDone(boolean done) {
        if (done == this.done){
            throw new IllegalArgumentException("done is already set to " + done);
        } else {
            this.done = done;
        }
    }
    public Person getAssignee() {
        return creator;
    }
    public void setAssignee(Person creator) {
        if (creator == null) {
            throw new NullPointerException("creator must not be null");
        }
        this.creator = creator;
    }
    public boolean isOverDue() {
        return LocalDate.now().isAfter(deadLine);
    }
    public String getSummary() {
        return "TodoItem{" +
                "id :" + id +
                ", title: " + title +
                "\nDescription: " + taskDescription +
                "\ndeadline: " + (deadLine == null ? "No deadline set yet." : deadLine) +
                ", done: " + done +
                "\ncreator:" + creator.getFullName() +
                '}';
    }

}
