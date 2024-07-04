package se.lexicon.Model;

import java.time.LocalDate;
import java.util.Objects;

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
    public void setDeadLine(String stringDate) {
        if (stringDate == null || stringDate.isEmpty()) {
            throw new IllegalArgumentException("deadLine must not be null or empty");
        }
        LocalDate deadline = LocalDate.parse(stringDate);
        if (deadline.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("deadLine must not be before current date.");
        }
        this.deadLine = deadline;
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

    @Override
    public String toString() {
        return "TodoItem{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", taskDescription='" + taskDescription + '\'' +
                ", deadLine=" + deadLine +
                ", done=" + done +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TodoItem todoItem = (TodoItem) o;
        // Does not compare id, of course two different objects will have different id.
        // Do we have to compare creator as well? The TodoItem should not be different if it was created by different people!
        return done == todoItem.done && Objects.equals(title, todoItem.title) && Objects.equals(taskDescription, todoItem.taskDescription) && Objects.equals(deadLine, todoItem.deadLine);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, taskDescription, deadLine, done);
    }

}
