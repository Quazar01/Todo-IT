package se.lexicon.DAO.Collections;

import se.lexicon.DAO.IdSequencer.TodoItemSequencer;
import se.lexicon.DAO.TodoItems;
import se.lexicon.Model.TodoItem;
import java.time.LocalDate;
import java.util.ArrayList;


public class TodoItemsCollection implements TodoItems {

    ArrayList<TodoItem> todoItems;

    // Constructor
    public TodoItemsCollection() {
        todoItems = new ArrayList<>();
    }

    @Override
    public TodoItem persist(TodoItem todoItem) {
        todoItem.setTodo_id(TodoItemSequencer.nextId());
        todoItems.add(todoItem);
        return todoItem;
    }

    @Override
    public TodoItem findById(int todoItemId) {
        if (todoItemId < 0) {
            throw new IllegalArgumentException("Invalid todoItemId: " + todoItemId);
        }
        for (TodoItem todoItem : todoItems) {
            if (todoItem.getTodo_id() == todoItemId) {
                return todoItem;
            }
        }
        return null;
    }

    @Override
    public ArrayList<TodoItem> findAllByDoneStatus(boolean done) {
        ArrayList<TodoItem> doneTodoItems = new ArrayList<>();
        for (TodoItem todoItem : todoItems) {
            if (todoItem.isDone() == done) {
                doneTodoItems.add(todoItem);
            }
        }
        return doneTodoItems;
    }

    @Override
    public ArrayList<TodoItem> findByTitleContains(String title) {
        ArrayList<TodoItem> todoItemsWithTitle = new ArrayList<>();
        for (TodoItem todoItem : todoItems) {
            if (todoItem.getTitle().contains(title)) {
                todoItemsWithTitle.add(todoItem);
            }
        }
        return todoItemsWithTitle;
    }

    @Override
    public ArrayList<TodoItem> findByPersonId(int personId) {
        if (personId < 0) {
            throw new IllegalArgumentException("Invalid personId: " + personId);
        }
        ArrayList<TodoItem> todoItemsByPersonId = new ArrayList<>();
        for (TodoItem todoItem : todoItems) {
            if (todoItem.getAssignee().getPerson_id() == personId) {
                todoItemsByPersonId.add(todoItem);
            }
        }
        return todoItemsByPersonId;
    }

    @Override
    public ArrayList<TodoItem> findByDeadlineBefore(LocalDate end) {
        ArrayList<TodoItem> todoItemsBeforeDeadline = new ArrayList<>();
        for (TodoItem todoItem : todoItems) {
            if (todoItem.getDeadLine().isBefore(end)) {
                todoItemsBeforeDeadline.add(todoItem);
            }
        }
        return todoItemsBeforeDeadline;
    }

    @Override
    public ArrayList<TodoItem> findByDeadlineAfter(LocalDate start) {
        ArrayList<TodoItem> todoItemsAfterDeadline = new ArrayList<>();
        for (TodoItem todoItem : todoItems) {
            if (todoItem.getDeadLine().isAfter(start)) {
                todoItemsAfterDeadline.add(todoItem);
            }
        }
        return todoItemsAfterDeadline;
    }

    @Override
    public void remove(int todoItemId) {
        if (todoItemId < 0) {
            throw new IllegalArgumentException("Invalid todoItemId: " + todoItemId);
        }
        todoItems.removeIf(todoItem -> todoItem.getTodo_id() == todoItemId);
    }
}
