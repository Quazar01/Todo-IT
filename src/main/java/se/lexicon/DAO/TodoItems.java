package se.lexicon.DAO;

import se.lexicon.Model.TodoItem;

import java.time.LocalDate;
import java.util.ArrayList;


public interface TodoItems {
    TodoItem persist(TodoItem todoItem);
    TodoItem findById(int todoItemId);
    ArrayList<TodoItem> findAllByDoneStatus(boolean done);
    ArrayList<TodoItem> findByTitleContains(String title);
    ArrayList<TodoItem> findByPersonId(int personId);
    ArrayList<TodoItem> findByDeadlineBefore(LocalDate end);
    ArrayList<TodoItem> findByDeadlineAfter(LocalDate start);
    void remove(int todoItemId);

}
