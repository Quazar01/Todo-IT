package se.lexicon.DAO;

import se.lexicon.Model.TodoItemTask;

import java.util.ArrayList;

public interface TodoItemTaskDAO {
    TodoItemTask persist(TodoItemTask todoItemTask);
    TodoItemTask findById(int todoItemTaskId);
    ArrayList<TodoItemTask> findAll();
    ArrayList<TodoItemTask> findByAssignedStatus(boolean done);
    ArrayList<TodoItemTask> findByPersonId(int personId);
    void remove(int todoItemTaskId);
}
