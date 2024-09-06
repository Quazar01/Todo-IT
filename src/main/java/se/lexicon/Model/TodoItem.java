package se.lexicon.Model;

import java.time.LocalDate;
import java.util.Objects;

public class TodoItem {
    private int todo_id;
    private String title; // Not allowed to be null.
    private String description; // is used to hold further information.
    private LocalDate deadLine; // Not allowed to be null.
    // TodoItem is considered overdue if deadLine current date > deadLine.
    private boolean done; // default value is false.
    private Person creator; // Represents the person who created the TodoItem.
    private static int sequencer = 0;

    // Constructor
    public TodoItem(String title, String description, Person creator) {
        setTitle(title);
        setDescription(description);

        this.done = false;
        if (creator == null) {
            throw new NullPointerException("creator must not be null");
        }
        this.creator = creator;
    }

    // Helper methods
    private void setId() {
        this.todo_id = sequencer + 1;
        sequencer++;
    }
    private void validateStringInput(String input, String paramName) {
        if (input == null || input.trim().isEmpty()) {
            throw new IllegalArgumentException(paramName + " must not be null or empty");
        }
    }

    // Getters and Setters
    public int getTodo_id() {
        return todo_id;
    }
    public void setTodo_id(int todo_id) {
        if (todo_id < 0) {
            throw new IllegalArgumentException("id must not be less than 0");
        }
        this.todo_id = todo_id;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        validateStringInput(title, "title");
        this.title = title;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        validateStringInput(description, "description");
        this.description = description;
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
                "id=" + todo_id +
                ", title='" + title + '\'' +
                ", taskDescription='" + description + '\'' +
                ", deadLine=" + deadLine +
                ", done=" + done +
                ", creator=" + creator +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TodoItem todoItem = (TodoItem) o;
        // Does not compare id, of course two different objects will have different id.
        // Do we have to compare creator as well? The TodoItem should not be different if it was created by different people!
        return done == todoItem.done && Objects.equals(title, todoItem.title) && Objects.equals(description, todoItem.description) && Objects.equals(deadLine, todoItem.deadLine);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, description, deadLine, done);
    }

}
