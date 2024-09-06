package se.lexicon.DAO;

import se.lexicon.Model.Person;
import se.lexicon.Model.TodoItem;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;


public interface TodoItems {
//    TodoItem persist(TodoItem todoItem);
//    TodoItem findById(int todoItemId);
//    ArrayList<TodoItem> findAllByDoneStatus(boolean done);
//    ArrayList<TodoItem> findByTitleContains(String title);
//    ArrayList<TodoItem> findByPersonId(int personId);
//    ArrayList<TodoItem> findByDeadlineBefore(LocalDate end);
//    ArrayList<TodoItem> findByDeadlineAfter(LocalDate start);
//    void remove(int todoItemId);

    TodoItem create(TodoItem todoItem);
    Collection<TodoItem> findAll();
    TodoItem findById(int todoItemId);
    Collection<TodoItem> findByDoneStatus(boolean done);
    Collection<TodoItem> findByAssignee(int personId);
    Collection<TodoItem> findByAssignee(Person person);
    Collection<TodoItem> findByUnassignedTodoItems();
    TodoItem update(TodoItem todoItem);
    boolean delete(int todoItemId);

}
