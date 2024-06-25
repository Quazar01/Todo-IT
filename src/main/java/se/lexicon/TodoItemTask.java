package se.lexicon;

import java.util.Collections;

public class TodoItemTask {
    private int id;
    private boolean assigned; // Set to true if assignee is not null.
    private TodoItem todoItem; // Represents the TodoItem that is being assigned. // Not allowed to be null.
    private Person assignee; // Represents the person who is assigned to the TodoItem.
    private static int sequencer = 0;

    // Constructor
    public TodoItemTask(TodoItem todoItem, Person assignee) {
        this(todoItem);
        setAssignee(assignee);

        setAssigned(assignee);
    }

    // Constructor with no person assigned
    public TodoItemTask(TodoItem todoItem) {
        setId(id);
        setTodoItem(todoItem);
        setAssignee(null);

        setAssigned(null);
    }

    // Helper methods
    private void setId(int id) {
        this.id = sequencer + 1;
        sequencer++;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }
    public boolean isAssigned() {
        return assigned;
    }
    public void setAssigned(Person assignee) {
        this.assigned = assignee != null;
    }
    public TodoItem getTodoItem() {
        return todoItem;
    }
    public void setTodoItem(TodoItem todoItem) {
        if (todoItem == null) {
            throw new NullPointerException("todoItem must not be null");
        }
        this.todoItem = todoItem;
    }
    public Person getAssignee() {
        return assignee;
    }
    public void setAssignee(Person assignee) {
        this.assignee = assignee;
    }

    // Methods
    // getSummary() - Should return a description of the TodoItemTask.
    public String getSummary() {
        return "TodoItemTask{" +
                "id: " + id +
                ", assigned: " + assigned +
                "\ntodoItem: " + todoItem.getSummary() +
                // If assignee is null, return "No assignee" instead of the object.
                "\nassignee: " + (assignee == null ? "No assignee" : assignee.getFullName()) +
                '}';
    }
}
