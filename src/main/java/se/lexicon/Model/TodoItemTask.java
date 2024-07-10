package se.lexicon.Model;


import java.util.Objects;

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
        setAssigned(true);
    }

    // Constructor with no person assigned
    public TodoItemTask(TodoItem todoItem) {
        setId();
        setTodoItem(todoItem);
        setAssignee(null);

        setAssigned(false);
    }

    // Helper methods
    private void setId() {
        this.id = sequencer + 1;
        sequencer++;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        if (id < 0) {
            throw new IllegalArgumentException("id must not be less than 0");
        }
        this.id = id;
    }
    public boolean isAssigned() {
        return assigned;
    }
    public void setAssigned(boolean assigned) {
        this.assigned = assigned;
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
        setAssigned(true);
    }

    // Methods
    @Override
    public String toString() {
        return "TodoItemTask{" +
                "todoItem=" + todoItem +
                ", assigned=" + assigned +
                ", id=" + id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TodoItemTask that = (TodoItemTask) o;
        return id == that.id && assigned == that.assigned && Objects.equals(todoItem, that.todoItem);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, assigned);
    }

}
